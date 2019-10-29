package com.oky2abbas.person.common.ext

import com.github.oky2abbas.ktx.basic.printError
import com.google.gson.reflect.TypeToken
import com.oky2abbas.person.core.App
import com.oky2abbas.person.domain.model.BodyMessage
import okhttp3.ResponseBody
import retrofit2.Response


fun Response<ResponseBody>.errorMessage(): String {
    val gSon = App.appComponent.gSon()
    val json = errorBody()?.string()!!
    ("${code()},  -> $json").printError()

    return gSon.fromJson(json, BodyMessage::class.java).message
}


fun <T> Response<ResponseBody>.toObject(clazz: Class<T>): T {
    val gSon = App.appComponent.gSon()
    val json = body()?.string()!!
    return gSon.fromJson(json, clazz)
}

fun <T> Response<ResponseBody>.toArrayObject(clazz: Class<T>): List<T> {
    val gSon = App.appComponent.gSon()
    val json = body()?.string()!!
    val typeOfT = TypeToken.getParameterized(List::class.java, clazz).type
    return gSon.fromJson(json, typeOfT)
}