package istu.edu.irnitu.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import istu.edu.irnitu.R
import istu.edu.irnitu.entity.Class

class ScheduleAdapter(
    private val schedule: List<Class>,
    private val day: String
) : RecyclerView.Adapter<ScheduleAdapter.BaseScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, itemViewType: Int): BaseScheduleViewHolder {
        return when (ScheduleItemTypes.values()[itemViewType]) {
            ScheduleItemTypes.TITLE -> BaseScheduleViewHolder.DayTitleViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_day_title, parent, false)
            )
            ScheduleItemTypes.SINGLE -> BaseScheduleViewHolder.ClassItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_class, parent, false)
            )
            ScheduleItemTypes.DOUBLE -> BaseScheduleViewHolder.ClassItemDoubledViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_class_double, parent, false)
            )
            ScheduleItemTypes.END -> BaseScheduleViewHolder.DayEndViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_day_list_end, parent, false)
            )
        }
    }

    override fun getItemCount(): Int = schedule.size + 2

    override fun onBindViewHolder(vh: BaseScheduleViewHolder, position: Int) {
        val klass = if (position in 1..(itemCount - 2)) schedule[position - 1] else null
        when (vh) {
            is BaseScheduleViewHolder.DayTitleViewHolder -> vh.bind(day)
            is BaseScheduleViewHolder.ClassItemViewHolder -> vh.bind(klass!!)
            is BaseScheduleViewHolder.ClassItemDoubledViewHolder -> vh.bind(klass!!)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ScheduleItemTypes.TITLE.ordinal
        } else if (position == itemCount - 1) {
            ScheduleItemTypes.END.ordinal
        } else {
            if (schedule[position - 1].room.contains('@')) {
                ScheduleItemTypes.DOUBLE.ordinal
            } else {
                ScheduleItemTypes.SINGLE.ordinal
            }
        }
    }

    sealed class BaseScheduleViewHolder(internal val view: View) : RecyclerView.ViewHolder(view) {

        class DayTitleViewHolder(view: View) : BaseScheduleViewHolder(view) {
            private val day: TextView = view.findViewById(R.id.dayTitle)

            fun bind(day: String) {
                this.day.text = day
            }
        }

        class DayEndViewHolder(view: View) : BaseScheduleViewHolder(view)

        class ClassItemViewHolder(view: View) : BaseScheduleViewHolder(view) {
            private val title: TextView = view.findViewById(R.id.classTitle)
            private val type: TextView = view.findViewById(R.id.classType)
            private val teacher: TextView = view.findViewById(R.id.classTeacher)
            private val room: TextView = view.findViewById(R.id.classRoom)
            private val startHour: TextView = view.findViewById(R.id.classStartHour)
            private val startMinute: TextView = view.findViewById(R.id.classStartMinute)
            private val endTime: TextView = view.findViewById(R.id.classEndTime)

            fun bind(klass: Class) {
                title.text = klass.title
                type.text = getReadableClassType(klass.type).readableTitle
                teacher.text = klass.teacher
                room.text = klass.room
                val startTimeSplitted = klass.beginTime.split(':')
                startHour.text = startTimeSplitted[0]
                startMinute.text = startTimeSplitted[1]
                endTime.text = klass.endTime
            }
        }

        class ClassItemDoubledViewHolder(view: View) : BaseScheduleViewHolder(view) {
            private val title: TextView = view.findViewById(R.id.classTitle)
            private val title2: TextView = view.findViewById(R.id.classTitle2)
            private val type: TextView = view.findViewById(R.id.classType)
            private val type2: TextView = view.findViewById(R.id.classType2)
            private val teacher: TextView = view.findViewById(R.id.classTeacher)
            private val teacher2: TextView = view.findViewById(R.id.classTeacher2)
            private val room: TextView = view.findViewById(R.id.classRoom)
            private val room2: TextView = view.findViewById(R.id.classRoom2)
            private val startHour: TextView = view.findViewById(R.id.classStartHour)
            private val startMinute: TextView = view.findViewById(R.id.classStartMinute)
            private val endTime: TextView = view.findViewById(R.id.classEndTime)

            fun bind(klass: Class) {
                val titles = klass.title.split('@')
                title.text = titles[0]
                title2.text = titles[1]

                val types = klass.type.split('@')
                type.text = getReadableClassType(types[0]).readableTitle
                type2.text = getReadableClassType(types[1]).readableTitle

                val teachers = klass.teacher.split('@')
                teacher.text = teachers[0]
                teacher2.text = teachers[1]

                val rooms = klass.room.split('@')
                room.text = rooms[0]
                room2.text = rooms[1]

                val startTimeSplitted = klass.beginTime.split(':')
                startHour.text = startTimeSplitted[0]
                startMinute.text = startTimeSplitted[1]

                endTime.text = klass.endTime
            }
        }

        fun getReadableClassType(classType: String): ClassType {
            Log.d("BaseScheduleViewHolder", "classType=$classType")
            return ClassType.values().find { it.title == classType.trim() }!!
        }
    }

    enum class ScheduleItemTypes {
        TITLE,
        SINGLE,
        DOUBLE,
        END
    }

    enum class ClassType(val title: String, val readableTitle: String) {
        LECTURE("1. Лекция", "Лекция"),
        PRACTICE("2. Практ.", "Практика"),
        LAB("3. Лаб. раб.", "Лабораторная работа")
    }

}