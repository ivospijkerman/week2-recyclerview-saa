package com.example.movierecycler.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.movierecycler.domain.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Movie::class], version = 3, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MyRoomDatabase? = null

        fun getInstance(context: Context): MyRoomDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        val instance = Room.databaseBuilder(
                            context.applicationContext,
                            MyRoomDatabase::class.java,
                            "myRoomDatabase"
                        ).addCallback(MyRoomDataBasePopulator())
                            .fallbackToDestructiveMigration()
                            .build()
                        INSTANCE = instance
                    }
                }
            }

            return INSTANCE!!
        }
    }

    private class MyRoomDataBasePopulator : RoomDatabase.Callback() {

        private fun addItems() {

            INSTANCE?.let {
                GlobalScope.launch {
                    val movieDao = it.movieDao()
                    movieDao.deleteAll()
                    movieDao.insert(Movie(5, "The Shawshank Redemption", "F. Darabont", 1994))
                    movieDao.insert(Movie(-100, "The Godfather", "F.F. Coppolla", 1972))
                    movieDao.insert(Movie(9, "The Godfather II", "F.F. Coppolla", 1974))
                }
            }
        }

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            addItems()
        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)

            addItems()
        }
    }
}