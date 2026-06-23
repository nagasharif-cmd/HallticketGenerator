package com.example.hallticketgen.model.Db.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class HallTicket(
    @PrimaryKey
    val hallTicketNumber: String,
    val rollNumber: String,
    val examId: Int
)