package istu.edu.irnitu.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import istu.edu.irnitu.ui.fragment.*
import istu.edu.irnitu.utils.SmartFragmentStatePagerAdapter


class BottomBarAdapter(fragmentManager: FragmentManager) :
    SmartFragmentStatePagerAdapter(fragmentManager) {
    private val fragments = ArrayList<Fragment>()

    init {
        fragments.addAll(
            listOf(
                ResourcesFragment.newInstance(),
                NewsFragment.newInstance(),
                EventsFragment.newInstance(),
                ScheduleFragment.newInstance(),
                SettingsFragment.newInstance()
            )
        )
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
}