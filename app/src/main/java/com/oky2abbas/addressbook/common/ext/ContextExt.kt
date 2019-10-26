package com.oky2abbas.addressbook.common.ext

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.github.oky2abbas.ktx.basic.printError
import com.oky2abbas.addressbook.R
import kotlinx.android.synthetic.main.lay_toast.view.*

fun Context.showMessage(message: String) {
    message.printError()

    val layView = LayoutInflater.from(this)
        .inflate(R.layout.lay_toast, null)
    layView.txtToast.text = message

    val toast = Toast.makeText(this.applicationContext, message, Toast.LENGTH_SHORT)
    toast.view = layView

    toast.show()
}