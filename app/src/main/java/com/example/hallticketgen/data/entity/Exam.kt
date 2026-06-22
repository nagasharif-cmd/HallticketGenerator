package com.example.hallticketgen.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Exam(
    @PrimaryKey(autoGenerate = true)
    val examId: Int =0,
    val examName: String,
    val examDate: String,
    val location: String
)