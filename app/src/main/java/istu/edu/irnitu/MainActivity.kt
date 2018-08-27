package istu.edu.irnitu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import istu.edu.irnitu.ui.fragment.events.EventsFragment
import istu.edu.irnitu.ui.fragment.news.NewsFragment
import istu.edu.irnitu.ui.fragment.resources.ResourcesFragment
import istu.edu.irnitu.ui.fragment.schedule.ScheduleFragment
import istu.edu.irnitu.ui.fragment.settings.SettingsFragment
import istu.edu.irnitu.utils.RestoringStateSupportFragmentNavigator
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.BackTo
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private var eventsFragment: EventsFragment? = null
    private var newsFragment: NewsFragment? = null
    private var resourcesFragment: ResourcesFragment? = null
    private var scheduleFragment: ScheduleFragment? = null
    private var settingsFragment: SettingsFragment? = null

    private lateinit var navigator: SupportFragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        Application.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initContainers()

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.eventsFragment -> router.navigateTo(TabScreens.EVENTS)
                R.id.newsFragment -> router.navigateTo(TabScreens.NEWS)
                R.id.resourcesFragment -> router.navigateTo(TabScreens.RESOURCES)
                R.id.scheduleFragment -> router.navigateTo(TabScreens.SCHEDULE)
                R.id.settingsFragment -> router.navigateTo(TabScreens.SETTINGS)
                else -> return@setOnNavigationItemSelectedListener false
            }
            return@setOnNavigationItemSelectedListener true
        }

    }

    private fun initContainers() {
        navigator = object : RestoringStateSupportFragmentNavigator(supportFragmentManager, R.id.container) {
            override fun createFragment(screenKey: String, data: Any?): Fragment =
                    when (screenKey) {
                        TabScreens.EVENTS -> EventsFragment.newInstance()
                        TabScreens.NEWS -> NewsFragment.newInstance()
                        TabScreens.RESOURCES -> ResourcesFragment.newInstance()
                        TabScreens.SCHEDULE -> ScheduleFragment.newInstance()
                        TabScreens.SETTINGS -> SettingsFragment.newInstance()
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
                if (localStackCopy.size > 0) {
                    val id = getIdFromTabName(localStackCopy.peek())
                    if (id != 0) {
                        bottomNavigation.selectedItemId = id
                    }
                    fragmentManager.popBackStack()
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
                        TabScreens.EVENTS -> R.id.eventsFragment
                        TabScreens.NEWS -> R.id.newsFragment
                        TabScreens.RESOURCES -> R.id.resourcesFragment
                        TabScreens.SCHEDULE -> R.id.scheduleFragment
                        TabScreens.SETTINGS -> R.id.settingsFragment
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
