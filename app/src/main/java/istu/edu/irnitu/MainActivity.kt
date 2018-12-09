package istu.edu.irnitu

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import istu.edu.irnitu.presentation.presenter.MainScreenPresenter
import istu.edu.irnitu.presentation.view.MainScreenView
import istu.edu.irnitu.ui.adapters.BottomBarAdapter
import istu.edu.irnitu.ui.fragment.EventsFragment
import istu.edu.irnitu.ui.fragment.NewsFragment
import istu.edu.irnitu.ui.fragment.ResourcesFragment
import istu.edu.irnitu.ui.fragment.ScheduleFragment
import istu.edu.irnitu.ui.fragment.SettingsFragment
import istu.edu.irnitu.utils.RestoringStateSupportFragmentNavigator
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.BackTo
import javax.inject.Inject

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
