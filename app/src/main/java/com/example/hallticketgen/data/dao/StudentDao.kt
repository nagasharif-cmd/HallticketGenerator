package com.example.hallticketgen.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hallticketgen.data.entity.Student


@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: Student): Long

    @Query(
        "SELECT * FROM Student WHERE rollNumber = :rollNo"
    )
    suspend fun getStudent(
        rollNo: String
    ): Student?
}