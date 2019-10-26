package com.oky2abbas.addressbook.common.ext

import com.github.oky2abbas.ktx.basic.printError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oky2abbas.addressbook.core.App
import com.oky2abbas.addressbook.domain.model.BodyMessage
import okhttp3.ResponseBody
import retrofit2.Response


fun <T> Gson.fromJsonArray(jsonArray: String, clazz: Class<T>): List<T> {
    val typeOfT = TypeToken.getParameterized(List::class.java, clazz).type
    return Gson().fromJson(jsonArray, typeOfT)
}

fun Response<ResponseBody>.errorMessage(): String {
    val gSon = App.appComponent.gSon()
    val json = errorBody()?.string()!!
    ("${code()},  -> $json").printError()

    return gSon.fromJson(json, BodyMessage::class.java).message
}

fun Response<ResponseBody>.bodyMessage(): String {
    val gSon = App.appComponent.gSon()
    val json = body()?.string()!!
    ("${code()},  -> $json").printError()

    return gSon.fromJson(json, BodyMessage::class.java).message
}

fun Response<ResponseBody>.errorObject(): BodyMessage {
    val gSon = App.appComponent.gSon()
    val json = errorBody()?.string()!!
    ("${code()},  -> $json").printError()

    return gSon.fromJson(json, BodyMessage::class.java)
}


fun <T> Response<ResponseBody>.toObject(clazz: Class<T>): T {
    val gSon = App.appComponent.gSon()
    val json = body()?.string()!!
    return gSon.fromJson(json, clazz)
}

fun <T> Response<ResponseBody>.toArrayObject(clazz: Class<T>): List<T> {
    val gSon = App.appComponent.gSon()
    val json = body()?.string()!!
    return gSon.fromJsonArray(json, clazz)
}
