package istu.edu.irnitu.utils

import android.view.View

//@FunctionalInterface
interface OnItemClickListener<I, T> {
    fun onClick(view: View?, position: I, item: T)
}