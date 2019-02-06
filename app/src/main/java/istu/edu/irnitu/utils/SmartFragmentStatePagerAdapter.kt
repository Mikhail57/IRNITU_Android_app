package istu.edu.irnitu.utils

import android.view.ViewGroup
import android.util.SparseArray
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


/**
 * Extension of FragmentStatePagerAdapter which intelligently caches
 * all active fragments and manages the fragment lifecycles.
 * Usage involves extending from SmartFragmentStatePagerAdapter as you would any other PagerAdapter.
 *
 * Copied from https://gist.github.com/nesquena/c715c9b22fb873b1d259
 */
abstract class SmartFragmentStatePagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    // Sparse array to keep track of registered fragments in memory
    private val registeredFragments = SparseArray<androidx.fragment.app.Fragment>()

    // Register the fragment when the item is instantiated
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as androidx.fragment.app.Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    // Unregister when the item is inactive
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    // Returns the fragment for the position (if instantiated)
    fun getRegisteredFragment(position: Int): androidx.fragment.app.Fragment {
        return registeredFragments.get(position)
    }
}