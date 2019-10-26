package com.oky2abbas.addressbook.di.module

import androidx.lifecycle.ViewModelProvider
import com.oky2abbas.addressbook.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class VMFModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(PlayerVM::class)
//    abstract fun bindPlayerVM(vm: PlayerVM): ViewModel

    @Binds
    abstract fun bindViewModelFactory(
        viewModelFactory: ViewModelFactory
    ): ViewModelProvider.Factory
}