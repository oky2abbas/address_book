package com.oky2abbas.person.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        VMFModule::class,
        RestModule::class,
        ServiceModule::class,
        AdapterModule::class]
)
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}