package cs.med.mtz.moises.paymentrecord.data.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        // value?.let { Date(it) }
        if (value == null)
            return null
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

}