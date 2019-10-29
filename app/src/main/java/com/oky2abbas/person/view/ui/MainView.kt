package com.oky2abbas.person.view.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.oky2abbas.ktx.widget.callActivity
import com.oky2abbas.person.R
import com.oky2abbas.person.common.base.BaseActivity
import com.oky2abbas.person.common.ext.*
import com.oky2abbas.person.view.adapter.PersonAdapter
import com.oky2abbas.person.view.bus.PersonBus
import com.oky2abbas.person.view.state.ViewState
import com.oky2abbas.person.viewModel.PersonVM
import kotlinx.android.synthetic.main.main_view.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class MainView : BaseActivity() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: PersonAdapter

    private val personVM by lazy { vmFactory.get(this, PersonVM::class.java) }

    override fun layoutRes(): Int {
        return R.layout.main_view
    }

    override fun onDestroy() {
        unRegisterBus()
        super.onDestroy()
    }

    override fun viewHandler(savedInstanceState: Bundle?) {
        registerBus()

        listenerToShowAddView()
        subscribeToGetPersonList()
        subscribeToError()

        personVM.getPersonList()
    }

    private fun listenerToShowAddView() = fabAdd.setOnClickListener {
        callActivity(RegisterView())
    }

    private fun subscribeToGetPersonList() = personVM.livePersonList()
        .observe(this, Observer {
            flpMain.go(ViewState.Two.index)
            adapter.injectList(it)
            rcyPerson.adapter = adapter
        })

    private fun subscribeToError() = personVM.liveError()
        .observe(this, Observer {
            personVM.getPersonList()
            showMessage(it)
        })

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun subscribeToPersonBus(event: PersonBus) {
        if (rcyPerson.adapter != null) {
            rcyPerson.scrollToPosition(
                adapter.add(event.person)
            )
        }
    }
}
