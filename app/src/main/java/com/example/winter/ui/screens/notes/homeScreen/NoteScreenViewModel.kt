package com.example.winter.ui.screens.notes.homeScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.winter.domain.models.Note
import com.example.winter.domain.models.TrashNote
import com.example.winter.domain.repository.NoteRepository
import com.example.winter.domain.repository.trash.TrashNoteRepo
import com.example.winter.services.DispatcherProvider
import com.example.winter.services.alarm.AlarmInfo
import com.example.winter.services.alarm.AlarmScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(
    application: Application,
    private val homeRepository: NoteRepository,
    private val trashNote: TrashNoteRepo,
    private val dispatcherProvider: DispatcherProvider,
    private val alarmScheduler: AlarmScheduler,
) : AndroidViewModel(application) {

    var listOfNotes = homeRepository.getAllNotesFromRoom().asLiveData().map { notes ->
        notes.filterNot { it.isMovedToTrash }
    }

    fun deleteNote(noteId: Int, onSuccess: () -> Unit) {
        viewModelScope.launch(dispatcherProvider.io) {
            val note = homeRepository.getNoteById(noteId) ?: return@launch
            moveToTrash(noteId)
            homeRepository.updateNoteInRoom(note.copy(isMovedToTrash = true))
            withContext(dispatcherProvider.main) {
                onSuccess()
            }
        }
    }

    private suspend fun moveToTrash(noteId: Int) {
        trashNote.upsertTrashNote(TrashNote(noteId, LocalDateTime.now()))
        val getNoteById = homeRepository.getNoteById(noteId) ?: return
        if (getNoteById.reminderDateTime != null) {
            alarmScheduler.cancelAlarm(AlarmInfo(getNoteById.id, 0))
        }
    }

    fun deleteListOfNote(noteList: List<Note>) {
        viewModelScope.launch(dispatcherProvider.io) {
            noteList.forEach {
                moveToTrash(it.id)
                homeRepository.updateNoteInRoom(it.copy(isMovedToTrash = true))
            }
        }
    }
}
