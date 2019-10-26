package com.oky2abbas.person.di.module

import com.oky2abbas.person.view.adapter.PersonAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides
    fun providePersonAdapter() = PersonAdapter()
}