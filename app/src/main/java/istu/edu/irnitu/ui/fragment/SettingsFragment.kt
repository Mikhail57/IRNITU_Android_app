package istu.edu.irnitu.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.SettingsView
import istu.edu.irnitu.presentation.presenter.SettingsPresenter

import com.arellomobile.mvp.presenter.InjectPresenter

class SettingsFragment : MvpAppCompatFragment(), SettingsView {
    companion object {
        const val TAG = "SettingsFragment"

        fun newInstance(): SettingsFragment {
            val fragment: SettingsFragment = SettingsFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mSettingsPresenter: SettingsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
