package com.oky2abbas.person.di.module

import com.oky2abbas.person.domain.api.PersonAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {
    @Singleton
    @Provides
    fun providePersonAPI(retrofit: Retrofit): PersonAPI {
        return retrofit.create(PersonAPI::class.java)
    }
}