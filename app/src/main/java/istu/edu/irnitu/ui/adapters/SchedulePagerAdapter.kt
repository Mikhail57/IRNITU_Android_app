package istu.edu.irnitu.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.text.format.DateFormat
import android.util.Log
import istu.edu.irnitu.ui.fragment.ScheduleDayViewPagerFragment
import java.util.*

class SchedulePagerAdapter(
    fragmentManager: FragmentManager,
    private val group: String
) : FragmentPagerAdapter(fragmentManager) {

    private val calendar = GregorianCalendar()

    override fun getItem(position: Int): Fragment {
        val calendar = getCalendarForPosition(position)
        val day = getDayForCalendar(calendar)
        val readableDate = getReadableDateStringForCalendar(calendar)
        Log.w(TAG, "Day: $day, readable: $readableDate")
        return ScheduleDayViewPagerFragment.newInstance(day, readableDate, group)
    }

    override fun getCount(): Int = 365 / 2

    private fun getCalendarForPosition(position: Int): Calendar {
        val calendarForDay = calendar.clone() as Calendar
        calendarForDay.add(Calendar.DAY_OF_YEAR, position)
        return calendarForDay
    }

    private fun getReadableDateStringForCalendar(calendar: Calendar): String {
        return DateFormat.format("EEEE, dd MMMM", calendar).toString()
    }

    private fun getDayForCalendar(calendar: Calendar): Int {
        val week = (calendar.get(Calendar.WEEK_OF_YEAR) % 2) xor 1
        return getDay(calendar.get(Calendar.DAY_OF_WEEK)) + week * 7
    }

    private fun getDay(calendarDay: Int) = if (calendarDay > 1) calendarDay - 1 else 7


    companion object {
        const val TAG = "SchedulePagerAdapter"
    }

}