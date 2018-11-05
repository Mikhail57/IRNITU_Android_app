package istu.edu.irnitu.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import istu.edu.irnitu.R
import istu.edu.irnitu.entity.Class

class ScheduleAdapter(
    private val schedule: List<Class>,
    private val day: String
) : RecyclerView.Adapter<ScheduleAdapter.BaseScheduleViewHolder>() {
    override fun onCreateViewHolder(view: ViewGroup, itemViewType: Int): BaseScheduleViewHolder {
        return when (ScheduleItemTypes.values()[itemViewType]) {
            ScheduleItemTypes.TITLE -> BaseScheduleViewHolder.DayTitleviewHolder(view)
            ScheduleItemTypes.SINGLE -> BaseScheduleViewHolder.ClassItemViewHolder(view)
            ScheduleItemTypes.DOUBLE -> BaseScheduleViewHolder.ClassItemDoubledViewHolder(view)
        }
    }

    override fun getItemCount(): Int = schedule.size + 1

    override fun onBindViewHolder(vh: BaseScheduleViewHolder, position: Int) {
        val klass = if (position > 1) schedule[position - 1] else null
        when (vh) {
            is BaseScheduleViewHolder.DayTitleviewHolder -> vh.bind(day)
            is BaseScheduleViewHolder.ClassItemViewHolder -> vh.bind(klass!!)
            is BaseScheduleViewHolder.ClassItemDoubledViewHolder -> vh.bind(klass!!)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ScheduleItemTypes.TITLE.ordinal
        } else {
            if (schedule[position - 1].room.contains('@')) {
                ScheduleItemTypes.DOUBLE.ordinal
            } else {
                ScheduleItemTypes.SINGLE.ordinal
            }
        }
    }

    sealed class BaseScheduleViewHolder(internal val view: View) : RecyclerView.ViewHolder(view) {

        class DayTitleviewHolder(view: View) : BaseScheduleViewHolder(view) {
            private val day: TextView = view.findViewById(R.id.title)

            fun bind(day: String) {
                this.day.text = day
            }
        }

        class ClassItemViewHolder(view: View) : BaseScheduleViewHolder(view) {
            private val title: TextView = view.findViewById(R.id.classTitle)
            private val teacher: TextView = view.findViewById(R.id.classTeacher)
            private val startHour: TextView = view.findViewById(R.id.classStartHour)
            private val startMinute: TextView = view.findViewById(R.id.classStartMinute)
            private val endTime: TextView = view.findViewById(R.id.classEndTime)

            fun bind(klass: Class) {
                title.text = klass.title
                teacher.text = klass.teacher
                val startTimeSplited = klass.beginTime.split(':')
                startHour.text = startTimeSplited[0]
                startMinute.text = startTimeSplited[1]
                endTime.text = klass.endTime
            }
        }

        class ClassItemDoubledViewHolder(view: View) : BaseScheduleViewHolder(view) {

            fun bind(klass: Class) {

            }
        }
    }

    enum class ScheduleItemTypes {
        TITLE,
        SINGLE,
        DOUBLE
    }

}