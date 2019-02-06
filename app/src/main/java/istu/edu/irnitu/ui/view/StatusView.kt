package istu.edu.irnitu.ui.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.constraintlayout.widget.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import istu.edu.irnitu.R

/**
 * TODO: document your custom view class.
 */
class StatusView : FrameLayout {
    private lateinit var text: TextView
    private lateinit var button: Button
    private lateinit var image: ImageView

    /**
     * The status text
     */
    var statusText: String? = null

    /**
     * The status action button text
     */
    var statusActionButtonText: String? = null

    /**
     * The status color
     */
    var statusColor: Int = Color.RED

    /**
     * The status image drawable
     */
    var statusDrawable: Drawable? = null

    /**
     * The onclick action of button
     */
    var buttonOnClickListener: OnClickListener? = null


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.StatusView, defStyle, 0
        )

        statusText = a.getString(
            R.styleable.StatusView_statusText
        )
        statusActionButtonText = a.getString(
            R.styleable.StatusView_statusButtonText
        )
        statusColor = a.getColor(
            R.styleable.StatusView_statusColor,
            statusColor
        )
        statusDrawable = a.getDrawable(
            R.styleable.StatusView_statusDrawable
        )

        a.recycle()

        View.inflate(context, R.layout.view_status, this)
        text = findViewById(R.id.statusText)
        button = findViewById(R.id.statusButton)
        image = findViewById(R.id.statusImage)

        text.text = statusText
        button.text = statusActionButtonText
        button.setOnClickListener {
            buttonOnClickListener?.onClick(it)
        }
        image.setImageDrawable(statusDrawable)
    }

}
