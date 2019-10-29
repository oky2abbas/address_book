package com.oky2abbas.person.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oky2abbas.person.common.base.BaseViewModel
import com.oky2abbas.person.common.ext.errorMessage
import com.oky2abbas.person.common.ext.toArrayObject
import com.oky2abbas.person.common.ext.toObject
import com.oky2abbas.person.domain.model.LitePerson
import com.oky2abbas.person.domain.model.Person
import com.oky2abbas.person.domain.repo.PersonRepo
import javax.inject.Inject

class PersonVM @Inject constructor(
    private val repo: PersonRepo
) : BaseViewModel() {

    private val livePersonList = MutableLiveData<List<Person>>()
    private val livePerson = MutableLiveData<Person>()

    fun livePersonList(): LiveData<List<Person>> = livePersonList
    fun livePerson(): LiveData<Person> = livePerson

    fun getPersonList() = repo.getPersonList().subscribe({
        if (it.code() != 200) {
            liveError.value = it.errorMessage()
            return@subscribe
        }

        livePersonList.value = it.toArrayObject(Person::class.java)
    }, { liveError.value = it.message }).addToComposite()

    fun addPerson(person: LitePerson) = repo.addPerson(person).subscribe({
        if (!it.isSuccessful) {
            liveError.value = it.errorMessage()
            return@subscribe
        }
        livePerson.value = it.toObject(Person::class.java)
    }, { liveError.value = it.message }).addToComposite()
}