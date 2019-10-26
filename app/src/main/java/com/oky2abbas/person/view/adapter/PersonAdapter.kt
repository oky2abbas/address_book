package com.oky2abbas.person.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oky2abbas.person.R
import com.oky2abbas.person.domain.model.Person
import com.oky2abbas.person.view.callback.PersonCallback
import com.quiz.prize.app.view.adapter.VHolder
import kotlinx.android.synthetic.main.main_item.view.*

class PersonAdapter : RecyclerView.Adapter<VHolder>() {
    private var personList = listOf<Person>()

    fun injectList(newList: List<Person>) {
        val diffCallback = PersonCallback(personList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.personList = newList

        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun getItemId(position: Int): Long {
        return personList[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        val item = holder.itemView
        val person = personList[position]

        item.txtName.text = person.firstName
        item.txtPhone.text = person.coordinateMobile
        item.txtAddress.text = person.address
    }
}