package com.example.hallticketgen.model.Db.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hallticketgen.model.Db.data.entity.Exam

@Dao
interface ExamDao {
    @Insert
    suspend fun insert(
        exam: Exam
    ): Long

    @Query(
        "SELECT * FROM Exam LIMIT 1"
    )
    suspend fun getExam(): Exam?
}