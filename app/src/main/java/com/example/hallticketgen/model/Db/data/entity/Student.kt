package com.example.hallticketgen.model.Db.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
                    @PrimaryKey
                   val rollNumber: String,
                   val name: String,
                   val className: String)