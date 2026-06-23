package com.example.hallticketgen.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hallticketgen.model.Db.data.entity.HallTicketDetails


@Composable
fun HallTicketScreen(
    hallTicket: HallTicketDetails
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column {

                Surface(
                    color = Color(0xFF5B3FD0),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "HALL TICKET",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        )

                        Text(
                            text = "ABC UNIVERSITY",
                            color = Color.White
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Hall Ticket Number"
                        )

                        Text(
                            text = hallTicket.hallTicketNumber,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "STUDENT DETAILS",
                        color = Color(0xFF5B3FD0),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    DetailRow(
                        "Name",
                        hallTicket.studentName
                    )

                    DetailRow(
                        "Roll No",
                        hallTicket.rollNumber
                    )

                    DetailRow(
                        "Class",
                        hallTicket.className
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text(
                        text = "EXAM DETAILS",
                        color = Color(0xFF5B3FD0),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    DetailRow(
                        "Exam",
                        hallTicket.examName
                    )

                    DetailRow(
                        "Date",
                        hallTicket.examDate
                    )

                    DetailRow(
                        "Location",
                        hallTicket.location
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text(
                        text = "INSTRUCTIONS",
                        color = Color(0xFF5B3FD0),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text("• Reach 30 minutes before exam")
                    Text("• Carry valid ID card")
                    Text("• Mobile phones not allowed")
                    Text("• Follow exam hall rules")

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Column(
                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Text("____________")
                            Text("Student Signature")
                        }

                        Column(
                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Text("____________")
                            Text("Invigilator")
                        }
                    }
                }
            }
        }
    }
}

@Composable fun DetailRow( label: String, value: String )
{
    Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
    )
    { Text( text = label,
        modifier = Modifier.weight(1f)
    )
        Text( text = value, modifier = Modifier.weight(1f)
        )
    }
}