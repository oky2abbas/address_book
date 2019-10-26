package com.oky2abbas.person.core

import android.content.Context
import android.content.res.Configuration
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.oky2abbas.person.R
import com.oky2abbas.person.utils.LocaleUtils
import com.oky2abbas.person.di.component.AppComponent
import com.oky2abbas.person.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class App : DaggerApplication() {

    companion object {
        lateinit var normalFont: Typeface
        lateinit var appComponent: AppComponent
        lateinit var instance: App
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        normalFont = ResourcesCompat.getFont(applicationContext, R.font.font_normal)!!
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.setLocale(this)
    }
}