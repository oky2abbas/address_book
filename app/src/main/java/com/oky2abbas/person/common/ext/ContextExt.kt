package com.oky2abbas.person.common.ext

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.github.oky2abbas.ktx.basic.printError
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.oky2abbas.person.R
import kotlinx.android.synthetic.main.lay_toast.view.*

fun Context.showMessage(message: String) {
    message.printError()

    val layView = LayoutInflater.from(this)
        .inflate(R.layout.lay_toast, null)
    layView.txtToast.text = message

    val toast = Toast.makeText(this.applicationContext, message, Toast.LENGTH_SHORT)
    toast.view = layView
    toast.setGravity(Gravity.TOP, 0, 0)

    toast.show()
}

fun Context.getVectorBitmapDescriptor(vectorResId: Int) =
    ContextCompat.getDrawable(this, vectorResId)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }