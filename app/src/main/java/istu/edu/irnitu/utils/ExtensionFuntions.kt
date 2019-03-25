package istu.edu.irnitu.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView

fun SharedPreferences.getStringOrNull(key: String): String? = this.getString(key, null)

fun browseUrl(context: Context, url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(browserIntent)
}

fun SliderLayout.addSliders(list: Iterable<BaseSliderView>) {
    list.forEach {
        this.addSlider(it)
    }
}
