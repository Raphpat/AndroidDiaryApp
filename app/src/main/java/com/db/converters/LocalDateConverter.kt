package com.db.converters

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

/**
 * Allows for the saving of LocalDate in the database by defining a converter
 */
class LocalDateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let {  Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDate() }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.let { date.toEpochDay() }
    }
}