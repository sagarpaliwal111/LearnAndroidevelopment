package com.sagarpaliwal.learnAndroiddevelopement.activity

import android.app.Application
import com.onesignal.OneSignal


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
     /********************************** Use for notification - One signal *******************************************/
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

    companion object {
        private const val ONESIGNAL_APP_ID = "6bea2628-fb1d-4e1a-9b78-5c7f03cb797f"
    }
}