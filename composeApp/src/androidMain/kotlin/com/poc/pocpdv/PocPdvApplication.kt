package com.poc.pocpdv

import android.app.Application
import com.poc.pocpdv.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class PocPdvApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@PocPdvApplication)
            androidLogger()
        }
    }
}