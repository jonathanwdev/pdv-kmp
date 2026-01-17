package com.poc.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<PocPdvDatabase> {
        val dbFile = context.applicationContext.getDatabasePath(PocPdvDatabase.DB_NAME)
        return Room.databaseBuilder(
            context.applicationContext,
            dbFile.absolutePath
        )
    }
}