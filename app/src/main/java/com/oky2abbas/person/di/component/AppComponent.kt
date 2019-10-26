package com.oky2abbas.person.di.component

import android.app.Application
import com.google.gson.Gson
import com.oky2abbas.person.core.App
import com.oky2abbas.person.di.module.AppModule
import com.oky2abbas.person.di.module.BindingModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BindingModel::class
    ]
)

interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
    fun gSon(): Gson
}