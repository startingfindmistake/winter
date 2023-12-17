package com.example.winter

import android.app.Application
import com.example.winter.services.notification.createNotificationChannel

class winterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel(this)
    }
}
