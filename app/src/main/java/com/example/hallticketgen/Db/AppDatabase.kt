package com.example.hallticketgen.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.hallticketgen.data.dao.ExamDao
import com.example.hallticketgen.data.dao.HallTicketDao
import com.example.hallticketgen.data.dao.StudentDao
import com.example.hallticketgen.data.entity.Exam
import com.example.hallticketgen.data.entity.HallTicket
import com.example.hallticketgen.data.entity.Student

@Database( entities = [
    Student::class,
    Exam::class,
    HallTicket::class

],

    exportSchema = false,

            version = 1)
abstract  class AppDatabase : RoomDatabase()
{
    abstract fun studentDao(): StudentDao

    abstract fun examDao(): ExamDao

    abstract fun hallTicketDao(): HallTicketDao
    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "hall_ticket_db"
                    ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
