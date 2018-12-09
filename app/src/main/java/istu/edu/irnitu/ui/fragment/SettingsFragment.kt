package istu.edu.irnitu.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.SettingsView
import istu.edu.irnitu.presentation.presenter.SettingsPresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : MvpAppCompatFragment(), SettingsView, OnDialogClickListener {
    companion object {
        const val TAG = "SettingsFragment"
        fun newInstance(): SettingsFragment {
            val fragment = SettingsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }

    @InjectPresenter
    lateinit var mSettingsPresenter: SettingsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deleteSchedule.setOnClickListener {
            mSettingsPresenter.onDeleteScheduleClick()
        }
    }

    override fun showAreYouSureDialog() {
        val dialog = AreYouSureDialogFragment()
        dialog.onClickListener = this
        dialog.show(fragmentManager, "sure")
    }

    override fun onClick() {
        mSettingsPresenter.deleteSchedule()
        Toast.makeText(context, "Расписание удалено...", Toast.LENGTH_SHORT).show()
    }

    class AreYouSureDialogFragment : DialogFragment() {
        var onClickListener: OnDialogClickListener? = null

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity!!)
            builder.setMessage("Вы уверены, что хотите удалить расписание?")
                .setPositiveButton(
                    "Да"
                ) { _, _ ->
                    onClickListener?.onClick()
                }
                .setNegativeButton(
                    "Нет"
                ) { _, _ ->
                    dismiss()
                }
            return builder.create()
        }
    }
}

interface OnDialogClickListener {
    fun onClick()
}
