package com.ss.kodeindi.utils

import android.animation.ObjectAnimator
import android.util.DisplayMetrics
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.facebook.shimmer.ShimmerFrameLayout


fun View.convertDpToPixel(dp: Float): Float {
    return dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun View.convertPixelsToDp(px: Float): Float {
    return px / (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun View.fadeIn() {
    this.isVisible = false
    ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1f).apply {
        duration = 300
    }.start()
}

fun View.fadeOut() {
    ObjectAnimator.ofFloat(this, View.ALPHA, 1f, 0f).apply {
        duration = 300
        doOnEnd {
            this@fadeOut.isVisible = false
        }
    }.start()
}

fun ShimmerFrameLayout.fadeIn() {
    isVisible = true
    startShimmer()
    ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1f).apply {
        duration = 300
    }.start()
}

fun ShimmerFrameLayout.fadeOut() {
    ObjectAnimator.ofFloat(this, View.ALPHA, 1f, 0f).apply {
        duration = 300
        doOnEnd {
            this@fadeOut.stopShimmer()
            this@fadeOut.isVisible = false
        }
    }.start()
}