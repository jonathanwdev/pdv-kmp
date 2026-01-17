package com.poc.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.poc.core.database.utils.appDataDirectory
import java.io.File



actual class DatabaseFactory {


    actual fun create(): RoomDatabase.Builder<PocPdvDatabase> {
        val directory = appDataDirectory

        if(!directory.exists()) {
            directory.mkdirs()
        }

        val dbFile = File(directory, PocPdvDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)

    }
}