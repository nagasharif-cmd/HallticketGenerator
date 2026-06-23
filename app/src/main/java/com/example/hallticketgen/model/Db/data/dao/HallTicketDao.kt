package com.example.hallticketgen.model.Db.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hallticketgen.model.Db.data.entity.HallTicket


@Dao
interface HallTicketDao {
    @Insert
    suspend fun insert(
        hallTicket: HallTicket
    ): Long

    @Query(
        "SELECT * FROM HallTicket WHERE rollNumber = :rollNo"
    )
    suspend fun getTicket(
        rollNo: String
    ): HallTicket?
}