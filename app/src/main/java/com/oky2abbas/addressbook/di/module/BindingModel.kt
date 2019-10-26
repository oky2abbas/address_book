package com.oky2abbas.addressbook.di.module

import com.oky2abbas.addressbook.view.ui.MainView
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BindingModel {
    @ContributesAndroidInjector
    abstract fun bindMainView(): MainView
}