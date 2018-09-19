package istu.edu.irnitu.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.arellomobile.mvp.presenter.InjectPresenter
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.SelectGroupView
import istu.edu.irnitu.presentation.presenter.SelectGroupPresenter

import                  com.arellomobile.mvp.MvpAppCompatActivity;


class SelectGroupActivity : MvpAppCompatActivity(), SelectGroupView {
    companion object {
        const val TAG = "SelectGroupActivity"
        fun getIntent(context: Context): Intent = Intent(context, SelectGroupActivity::class.java)
    }

    @InjectPresenter
    lateinit var mSelectGroupPresenter: SelectGroupPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_group)
    }
}
