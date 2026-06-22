package com.example.hallticketgen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.hallticketgen.Db.AppDatabase
import com.example.hallticketgen.data.entity.Exam
import com.example.hallticketgen.data.entity.Student
import com.example.hallticketgen.model.HallTicketScreen
import com.example.hallticketgen.ui.theme.HallTIcketGenTheme

import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getDatabase(this)

        val repository = HallTicketRepository(
            db.studentDao(),
            db.examDao(),
            db.hallTicketDao()
        )
        lifecycleScope.launch {

            db.studentDao().insert(
                Student(
                    rollNumber = "22CS101",
                    name = "Shaik",
                    className = "BTech"
                )
            )

            db.examDao().insert(
                Exam(
                    examName = "Android Development",
                    examDate = "25 June",
                    location = "Hyderabad",

                )
            )
        }
        enableEdgeToEdge()
        setContent {
            HallTIcketGenTheme {
                HallTicketScreen(
                    repository = repository
                )
            }
        }
    }
}

