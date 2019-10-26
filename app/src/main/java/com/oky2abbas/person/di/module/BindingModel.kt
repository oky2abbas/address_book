package com.oky2abbas.person.di.module

import com.oky2abbas.person.view.ui.MainView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModel {
    @ContributesAndroidInjector
    abstract fun bindMainView(): MainView
}