package com.example.winter.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.winter.domain.models.Note
import com.example.winter.domain.models.TrashNote

data class TrashNoteWithNotes(
    @Embedded val trashNote: TrashNote,
    @Relation(
        parentColumn = "noteId",
        entityColumn = "id"
    )
    val note: Note,
)
