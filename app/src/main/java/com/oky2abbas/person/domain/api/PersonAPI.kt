package com.oky2abbas.person.domain.api

import com.oky2abbas.person.domain.model.LitePerson
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface PersonAPI {
    @GET("karfarmas/address")
    fun getPersonList(): Flowable<Response<ResponseBody>>

    @POST("karfarmas/address")
    @Headers("Content-Type: application/json")
    fun addPerson(@Body person: LitePerson): Flowable<Response<ResponseBody>>
}