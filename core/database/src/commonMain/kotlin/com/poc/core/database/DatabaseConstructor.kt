package com.poc.core.database

import androidx.room.RoomDatabaseConstructor


@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DatabaseConstructor: RoomDatabaseConstructor<PocPdvDatabase> {
    override fun initialize(): PocPdvDatabase
}