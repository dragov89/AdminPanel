package com.example.adminpanel.data

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {

    abstract fun userListDao(): UserListDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private val DB_NAME = "patrol_tablet_db.db"
        fun getInstance(application: Application): AppDatabase {

            INSTANCE?.let { return it }
             synchronized(LOCK){
                 INSTANCE?.let {
                     return it
                 }
                 val db = Room.databaseBuilder(
                     application,
                     AppDatabase::class.java,
                     DB_NAME
                 ).build()
                 INSTANCE = db
                 return db
             }

        }
    }
}