package com.oky2abbas.person.common.ext

import android.widget.ViewFlipper


fun ViewFlipper.go(index: Int) {
    this.displayedChild = index
}