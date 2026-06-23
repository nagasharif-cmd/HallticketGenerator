package com.example.hallticketgen

import com.example.hallticketgen.model.Db.data.dao.ExamDao
import com.example.hallticketgen.model.Db.data.dao.HallTicketDao
import com.example.hallticketgen.model.Db.data.dao.StudentDao
import com.example.hallticketgen.model.Db.data.entity.HallTicket
import com.example.hallticketgen.model.Db.data.entity.HallTicketDetails

class HallTicketRepository(
    private val studentDao: StudentDao,
    private val examDao: ExamDao,
    private val hallTicketDao: HallTicketDao
) {

    suspend fun generateTicket(
        rollNo: String
    ): HallTicketDetails? {

        val student =
            studentDao.getStudent(rollNo)
                ?: return null

        val existing =
            hallTicketDao.getTicket(rollNo)

        if (existing != null) {

            val exam =
                examDao.getExam()
                    ?: return null

            return HallTicketDetails(
                hallTicketNumber = existing.hallTicketNumber,
                rollNumber = student.rollNumber,
                studentName = student.name,
                className = student.className,
                examName = exam.examName,
                examDate = exam.examDate,
                location = exam.location
            )
        }

        val exam =
            examDao.getExam()
                ?: return null

        val ticket =
            HallTicket(
                hallTicketNumber =
                    "HT-${System.currentTimeMillis()}",
                rollNumber = rollNo,
                examId = exam.examId
            )

        hallTicketDao.insert(ticket)

        return HallTicketDetails(
            hallTicketNumber = ticket.hallTicketNumber,
            rollNumber = student.rollNumber,
            studentName = student.name,
            className = student.className,
            examName = exam.examName,
            examDate = exam.examDate,
            location = exam.location
        )
    }
}