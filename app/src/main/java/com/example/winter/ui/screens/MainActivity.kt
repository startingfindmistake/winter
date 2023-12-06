package com.example.winter.ui.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.install.model.AppUpdateType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var appUpdateManager: AppUpdateManager
    private val updateType = AppUpdateType.IMMEDIATE

    @Inject
    lateinit var appBioMetricManager: AppBioMetricManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        if(updateType == AppUpdateType.FLEXIBLE) {
            appUpdateManager.registerListener(installStateUpdatedListener)
        }
        checkForAppUpdates()
        viewModel.checkTrashNote {
            Toast.makeText(this, "쓰레기통에서 삭제 $it", Toast.LENGTH_SHORT).show()
        }
        installSplashScreen().apply {
            setKeepOnScreenCondition(
                viewModel.loading.value
            )
        }
        setContent {
            NotifyTheme {
                NotifyApp()
            }
        }

        setObservers()
    }


}