package com.oky2abbas.person.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oky2abbas.person.R
import com.oky2abbas.person.domain.model.Person
import com.oky2abbas.person.view.callback.PersonCallback
import kotlinx.android.synthetic.main.main_item.view.*

class PersonAdapter : RecyclerView.Adapter<VHolder>() {
    private var personList = mutableListOf<Person>()

    fun injectList(newList: List<Person>) {
        val diffCallback = PersonCallback(personList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.personList = newList.toMutableList()

        diffResult.dispatchUpdatesTo(this)
    }

    fun add(person: Person): Int {
        person.isNew = true
        personList.add(person)
        notifyDataSetChanged()
        return personList.indexOf(person)
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

        if (person.isNew)
            item.crdPerson.setCardBackgroundColor(Color.YELLOW)
        else item.crdPerson.setCardBackgroundColor(Color.parseColor("#f0f0f0"))

        item.txtName.text = ("${person.firstName} ${person.lastName}")
        item.txtPhone.text = person.coordinateMobile
        item.txtAddress.text = person.address
    }
}