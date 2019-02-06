package istu.edu.irnitu.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class Class(
    val title: String,
    val room: String,
    @ColumnInfo(name = "group_title")
    val group: String,
    val teacher: String,
    val teacherId: String,
    val beginTime: String,
    val endTime: String,
    val course: Int,
    val faculty: String,
    val dayBegin: String,
    val dayEnd: String,
    val type: String,
    /*
      day 1-7, everyWeek 1 - только по нечетным
      day 1-7, everyWeek 2 - каждую неделю
      day 8-14, everyWeek 1 - только по четным
    */
    val day: Int,
    val everyWeek: Int,

    @PrimaryKey(autoGenerate = true)
    val index: Int = 0
)