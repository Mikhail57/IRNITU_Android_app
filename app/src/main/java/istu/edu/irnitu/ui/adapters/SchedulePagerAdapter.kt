package istu.edu.irnitu.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import istu.edu.irnitu.ui.fragment.ScheduleDayVPFragment
import java.util.*

class SchedulePagerAdapter(
    fragmentManager: FragmentManager,
    private val group: String
) : FragmentPagerAdapter(fragmentManager) {

    private val calendar = GregorianCalendar()

    override fun getItem(position: Int): Fragment {
        val day = getDayForPosition(position)
        return ScheduleDayVPFragment.newInstance(day, group)
    }

    override fun getCount(): Int = 365 / 2

    private fun getDayForPosition(position: Int): Int {
        val calendarForDay = calendar.clone() as Calendar
        calendarForDay.add(Calendar.DAY_OF_YEAR, position)
        val week = calendarForDay.get(Calendar.WEEK_OF_YEAR) % 2
        return getDay(calendarForDay.get(Calendar.DAY_OF_WEEK)) + week * 7
    }

    private fun getDay(calendarDay: Int) = if (calendarDay > 1) calendarDay - 1 else 7

}