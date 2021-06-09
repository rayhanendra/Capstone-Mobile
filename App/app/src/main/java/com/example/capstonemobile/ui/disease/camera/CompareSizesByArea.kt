package com.example.capstonemobile.ui.disease.camera

import android.util.Size
import java.lang.Long.signum

import java.util.Comparator

internal class CompareSizesByArea : Comparator<Size> {

    override fun compare(lhs: Size, rhs: Size) =
            signum(lhs.width.toLong() * lhs.height - rhs.width.toLong() * rhs.height)

}
