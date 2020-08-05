package com.midtronics.project.brads_deal.application

import android.app.Application
import android.util.Log
import com.midtronics.project.brads_deal.injection.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application(){
    val TAG = AppApplication::class.java.canonicalName

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    /**
     * Inject context and app modules with Koin
     */
    private fun setUpKoin(){
        Log.d(TAG, "Setting up KOIN")
        startKoin {
            androidContext(this@AppApplication)
            modules(appModules)
        }
    }
}