package istu.edu.irnitu

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import istu.edu.irnitu.presentation.presenter.MainScreenPresenter
import istu.edu.irnitu.presentation.view.MainScreenView
import istu.edu.irnitu.ui.adapters.BottomBarAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), MainScreenView {

    @InjectPresenter
    lateinit var presenter: MainScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        Application.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val vpAdapter = BottomBarAdapter(supportFragmentManager)
        container.adapter = vpAdapter
        bottomNavigation.setupWithViewPager(container)
    }

    override fun goToTab(tab: Tabs) {
    }

    override fun onBackPressed() = presenter.onBackPressed()
}
