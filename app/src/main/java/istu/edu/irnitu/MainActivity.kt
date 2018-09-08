package istu.edu.irnitu

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import istu.edu.irnitu.presentation.presenter.MainScreenPresenter
import istu.edu.irnitu.presentation.view.MainScreenView
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

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var navigator: SupportFragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        Application.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initContainers()

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.eventsFragment -> presenter.onTabClick(Tabs.EVENTS)
                R.id.newsFragment -> presenter.onTabClick(Tabs.NEWS)
                R.id.resourcesFragment -> presenter.onTabClick(Tabs.RESOURCES)
                R.id.scheduleFragment -> presenter.onTabClick(Tabs.SCHEDULE)
                R.id.settingsFragment -> presenter.onTabClick(Tabs.SETTINGS)
                else -> return@setOnNavigationItemSelectedListener false
            }
            return@setOnNavigationItemSelectedListener true
        }
        bottomNavigation.selectedItemId = R.id.resourcesFragment
    }

    override fun goToTab(tab: Tabs) {
//        val id = when (tab) {
//            Tabs.EVENTS -> R.id.eventsFragment
//            Tabs.NEWS -> R.id.newsFragment
//            Tabs.RESOURCES -> R.id.resourcesFragment
//            Tabs.SCHEDULE -> R.id.scheduleFragment
//            Tabs.SETTINGS -> R.id.settingsFragment
//        }
//        if (id != bottomNavigation.selectedItemId)
//            bottomNavigation.selectedItemId = id
    }

    override fun onBackPressed() = presenter.onBackPressed()

    private fun initContainers() {
        navigator = object : RestoringStateSupportFragmentNavigator(supportFragmentManager, R.id.container) {
            override fun createFragment(screenKey: String, data: Any?): Fragment =
                    when (screenKey) {
                        Tabs.EVENTS.name -> EventsFragment.newInstance()
                        Tabs.NEWS.name -> NewsFragment.newInstance()
                        Tabs.RESOURCES.name -> ResourcesFragment.newInstance()
                        Tabs.SCHEDULE.name -> ScheduleFragment.newInstance()
                        Tabs.SETTINGS.name -> SettingsFragment.newInstance()
                        else -> TODO("LOL")
                    }

            override fun showSystemMessage(message: String?) {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }

            override fun exit() {
                this@MainActivity.finish()
            }

            override fun back() {
                Log.e("Navigator", "BACK CALLED")
                if (localStackCopy.size > 1) {
                    val id = getIdFromTabName(localStackCopy.peek())
                    if (id != 0) {
                        bottomNavigation.selectedItemId = id
                    }
                    supportFragmentManager.popBackStack()
                    localStackCopy.pop()
                } else {
                    exit()
                }
            }

            override fun backTo(command: BackTo?) {
                Log.e("Navigator", "BACK_TO CALLED")
                super.backTo(command)
            }


            private fun getIdFromTabName(name: String?): Int =
                    when (name) {
                        Tabs.EVENTS.name -> R.id.eventsFragment
                        Tabs.NEWS.name -> R.id.newsFragment
                        Tabs.RESOURCES.name -> R.id.resourcesFragment
                        Tabs.SCHEDULE.name -> R.id.scheduleFragment
                        Tabs.SETTINGS.name -> R.id.settingsFragment
                        else -> 0
                    }

        }
        navigatorHolder.setNavigator(navigator)


//        val fm = supportFragmentManager
//
//        eventsFragment = fm.findFragmentByTag(EventsFragment.TAG) as EventsFragment?
//        if (eventsFragment == null) {
//            eventsFragment = EventsFragment.newInstance()
//            fm.beginTransaction()
//                    .add(R.id.container, eventsFragment, EventsFragment.TAG)
//                    .detach(eventsFragment)
//                    .commit()
//        }
//
//        newsFragment = fm.findFragmentByTag(NewsFragment.TAG) as NewsFragment?
//        if (newsFragment == null) {
//            newsFragment = NewsFragment.newInstance()
//            fm.beginTransaction()
//                    .add(R.id.container, newsFragment, NewsFragment.TAG)
//                    .detach(newsFragment)
//                    .commit()
//        }
//
//        resourcesFragment = fm.findFragmentByTag(ResourcesFragment.TAG) as ResourcesFragment?
//        if (resourcesFragment == null) {
//            resourcesFragment = ResourcesFragment.newInstance()
//            fm.beginTransaction()
//                    .add(R.id.container, resourcesFragment, ResourcesFragment.TAG)
//                    .detach(resourcesFragment)
//                    .commit()
//        }
//
//        scheduleFragment = fm.findFragmentByTag(ScheduleFragment.TAG) as ScheduleFragment?
//        if (scheduleFragment == null) {
//            scheduleFragment = ScheduleFragment.newInstance()
//            fm.beginTransaction()
//                    .add(R.id.container, scheduleFragment, ScheduleFragment.TAG)
//                    .detach(scheduleFragment)
//                    .commit()
//        }
//
//        settingsFragment = fm.findFragmentByTag(SettingsFragment.TAG) as SettingsFragment?
//        if (settingsFragment == null) {
//            settingsFragment = SettingsFragment.newInstance()
//            fm.beginTransaction()
//                    .add(R.id.container, settingsFragment, SettingsFragment.TAG)
//                    .detach(settingsFragment)
//                    .commit()
//        }

    }
}
