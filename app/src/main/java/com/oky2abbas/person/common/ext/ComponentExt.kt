package com.oky2abbas.person.common.ext

import android.view.View
import android.widget.TextView
import android.widget.ViewFlipper


fun ViewFlipper.go(index: Int) {
    this.displayedChild = index
}

fun TextView.clear() {
    this.text = ""
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}