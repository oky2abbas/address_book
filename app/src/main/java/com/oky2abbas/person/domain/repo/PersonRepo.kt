package com.quiz.prize.app.domain.repo

import com.github.oky2abbas.ktx.rx.newThread
import com.oky2abbas.person.domain.api.PersonAPI
import com.oky2abbas.person.domain.model.LitePerson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepo @Inject constructor(
    private val api: PersonAPI
) {

    fun getPersonList() =
        api.getPersonList().newThread()

    fun addPerson(person: LitePerson) =
        api.addPerson(person).newThread()
}