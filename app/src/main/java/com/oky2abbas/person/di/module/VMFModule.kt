package com.oky2abbas.person.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oky2abbas.person.di.key.ViewModelKey
import com.oky2abbas.person.factory.ViewModelFactory
import com.oky2abbas.person.viewModel.PersonVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMFModule {

    @Binds
    @IntoMap
    @ViewModelKey(PersonVM::class)
    abstract fun bindPersonVM(vm: PersonVM): ViewModel

    @Binds
    abstract fun bindViewModelFactory(
        viewModelFactory: ViewModelFactory
    ): ViewModelProvider.Factory
}