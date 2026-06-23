package com.example.hallticketgen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.hallticketgen.model.Db.AppDatabase
import com.example.hallticketgen.model.Db.data.entity.Exam
import com.example.hallticketgen.model.Db.data.entity.Student
import com.example.hallticketgen.navigation.AppNavGraph
import com.example.hallticketgen.view.HallTicketScreen
import com.example.hallticketgen.ui.theme.HallTIcketGenTheme
import com.example.hallticketgen.view.HomeScreen
import com.example.hallticketgen.viewmodel.HallTicketViewModel
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

        val viewModel = HallTicketViewModel(
            repository
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
                    examId = 0,
                    examName = "Android Development",
                    examDate = "25 June",
                    location = "Hyderabad"
                )
            )
        }

        enableEdgeToEdge()

        setContent {

            HallTIcketGenTheme {

                AppNavGraph(
                    viewModel = viewModel
                )
            }
        }
    }
}