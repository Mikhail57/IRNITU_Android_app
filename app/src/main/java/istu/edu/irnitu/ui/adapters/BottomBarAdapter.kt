package istu.edu.irnitu.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

    override fun getItem(position: Int): androidx.fragment.app.Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
}