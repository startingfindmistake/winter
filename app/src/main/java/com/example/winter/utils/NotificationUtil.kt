package com.example.winter.utils

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.winter.R
import com.example.winter.domain.models.Note

fun NotificationManager.sendReminderNote(context: Context, note: Note) {
    val notification = NotificationCompat.Builder(context, "1").apply {
        setSmallIcon(R.drawable.notify_icon)
        setContentTitle(note.title)
        setContentText(note.note)
        setAutoCancel(true)
    }. build()
    notify(note.id, notification)
}