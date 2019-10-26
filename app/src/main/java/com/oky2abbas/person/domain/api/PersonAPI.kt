package com.oky2abbas.person.domain.api

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface PersonAPI {
    @GET("karfarmas/address")
    fun getPersonList(): Flowable<Response<ResponseBody>>
}