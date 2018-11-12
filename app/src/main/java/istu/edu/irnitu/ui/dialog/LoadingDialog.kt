package istu.edu.irnitu.ui.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import istu.edu.irnitu.R

class LoadingDialog : DialogFragment() {
    private var title: String? = null
    private var text: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = arguments?.getString(ARG_TITLE_PARAM)
        text = arguments?.getString(ARG_TEXT_PARAM)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_loading, container, false)
        dialog.setTitle(title)
        dialog.setCancelable(false)
        isCancelable = false
        view.apply {
            findViewById<TextView>(R.id.dialogContent).text = text
        }

        return view
    }

    companion object {
        private const val TAG = "LoadingFragment"

        private const val ARG_TITLE_PARAM = "title"
        private const val ARG_TEXT_PARAM = "text"

        fun newInstance(title: String, text: String): LoadingDialog =
            LoadingDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE_PARAM, title)
                    putString(ARG_TEXT_PARAM, text)
                }
            }
    }
}