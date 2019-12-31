package com.zhomprass.zhomprasslimited.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhomprass.zhomprasslimited.Models.RelatedProduct

@Database(entities = arrayOf(RelatedProduct::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): CartDao
}