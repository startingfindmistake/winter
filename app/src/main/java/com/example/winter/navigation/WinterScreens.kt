package com.example.winter.navigation

sealed class WinterScreens (val name: String){
    data object Notes : WinterScreens("notes")
    data object AddEditNotes : WinterScreens("add_edit_note")
    data object TodoHome : WinterScreens("todo_home")
    data object Settings : WinterScreens("setting")
    data object TrashNoteScreen : WinterScreens("trash_note_route")
}