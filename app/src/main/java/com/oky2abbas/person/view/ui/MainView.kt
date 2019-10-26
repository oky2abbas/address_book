package com.oky2abbas.person.view.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.oky2abbas.person.R
import com.oky2abbas.person.common.base.BaseActivity
import com.oky2abbas.person.common.ext.go
import com.oky2abbas.person.view.adapter.PersonAdapter
import com.oky2abbas.person.view.state.ViewState
import com.oky2abbas.person.viewModel.PersonVM
import kotlinx.android.synthetic.main.main_view.*
import javax.inject.Inject

class MainView : BaseActivity() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: PersonAdapter

    private val personVM by lazy {
        ViewModelProviders.of(this, vmFactory).get(PersonVM::class.java)
    }

    override fun layoutRes(): Int {
        return R.layout.main_view
    }

    override fun viewHandler(savedInstanceState: Bundle?) {
        listenToAddPerson()
        observerPersonList()


        personVM.getPersonList()
    }

    private fun listenToAddPerson() = fabAdd.setOnClickListener {

    }

    private fun observerPersonList() = personVM.livePersonList()
        .observe(this, Observer {
            flpMain.go(ViewState.Content.index)

            adapter.injectList(it)
            rcyPerson.adapter = adapter
        })
}
