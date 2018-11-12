package istu.edu.irnitu.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

class SelectDialog : DialogFragment() {

    var onItemClickListener: DialogInterface.OnClickListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments?.getString(ARG_TITLE_PARAM)
        val items = arguments?.getStringArray(ARG_ITEMS_PARAM)
        return AlertDialog.Builder(context!!).apply {
            setTitle(title)
            setItems(items) { dialog, which ->
                onItemClickListener?.onClick(dialog, which)
                dismiss()
            }
        }.create()
    }

    companion object {
        const val ARG_TITLE_PARAM = "title"
        const val ARG_ITEMS_PARAM = "items"

        fun newInstance(
            title: String,
            items: List<String>,
            onItemClickListener: DialogInterface.OnClickListener
        ) =
            SelectDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE_PARAM, title)
                    putStringArray(ARG_ITEMS_PARAM, items.toTypedArray())
                }
                this.onItemClickListener = onItemClickListener
            }
    }
}