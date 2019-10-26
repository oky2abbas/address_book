package com.quiz.prize.app.domain.repo

import com.github.oky2abbas.ktx.rx.newThread
import com.oky2abbas.person.domain.api.PersonAPI
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepo @Inject constructor(
    private val api: PersonAPI
) {

    fun getPersonList(): Flowable<Response<ResponseBody>> =
        api.getPersonList().newThread()
}