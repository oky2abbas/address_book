package com.oky2abbas.person.core

import android.content.res.Configuration
import com.google.android.gms.maps.MapsInitializer
import com.oky2abbas.person.di.component.AppComponent
import com.oky2abbas.person.di.component.DaggerAppComponent
import com.oky2abbas.person.utils.LocaleUtils
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class App : DaggerApplication() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var instance: App
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        MapsInitializer.initialize(this)
        instance = this
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.setLocale(this)
    }
}