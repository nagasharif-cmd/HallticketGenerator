package com.example.hallticketgen.model

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hallticketgen.HallTicketRepository
import com.example.hallticketgen.data.entity.HallTicketDetails
import kotlinx.coroutines.launch

@Composable
fun HallTicketScreen(
    repository: HallTicketRepository
) {

    var rollNo by remember {
        mutableStateOf("")
    }

    var hallTicket by remember {
        mutableStateOf<HallTicketDetails?>(null)
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = rollNo,
            onValueChange = {
                rollNo = it
            },
            label = {
                Text("Roll Number")
            }
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = {

                scope.launch {

                    hallTicket =
                        repository.generateTicket(
                            rollNo
                        )
                }
            }
        ) {
            Text("Generate Hall Ticket")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        hallTicket?.let { ticket ->

            Column {

                Text(
                    text = "Hall Ticket Number : ${ticket.hallTicketNumber}"
                )

                Text(
                    text = "Student Name : ${ticket.studentName}"
                )

                Text(
                    text = "Roll Number : ${ticket.rollNumber}"
                )

                Text(
                    text = "Class : ${ticket.className}"
                )

                Text(
                    text = "Exam : ${ticket.examName}"
                )

                Text(
                    text = "Date : ${ticket.examDate}"
                )

                Text(
                    text = "Location : ${ticket.location}"
                )
            }
        }
    }
}