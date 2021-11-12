package com.db.converters

import androidx.room.TypeConverter
import java.time.*

/**
 * Allows for the saving of LocalDate in the database by defining a converter
 */
class LocalDateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofEpochSecond(value, 0, ZoneOffset.UTC) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.let { date.toEpochSecond(ZoneOffset.UTC) }
    }
}