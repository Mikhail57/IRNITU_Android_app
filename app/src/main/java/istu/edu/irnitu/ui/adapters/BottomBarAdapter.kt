package istu.edu.irnitu.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import istu.edu.irnitu.utils.SmartFragmentStatePagerAdapter


class BottomBarAdapter(fragmentManager: FragmentManager) :
    SmartFragmentStatePagerAdapter(fragmentManager) {
    private val fragments = ArrayList<Fragment>()

    // Our custom method that populates this Adapter with Fragments
    fun addFragments(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
}