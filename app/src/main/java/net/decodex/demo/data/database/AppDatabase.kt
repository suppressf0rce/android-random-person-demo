package net.decodex.demo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.decodex.demo.data.database.dao.UserDao
import net.decodex.demo.data.database.model.User

@Database(
    entities = [User::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao

}
