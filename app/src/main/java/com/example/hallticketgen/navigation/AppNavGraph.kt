package com.example.hallticketgen.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.hallticketgen.view.HallTicketScreen
import com.example.hallticketgen.view.HomeScreen
import com.example.hallticketgen.viewmodel.HallTicketViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun AppNavGraph(
    viewModel: HallTicketViewModel
) {

    val navController =
        rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        composable(
            Routes.HOME
        ) {

            HomeScreen(

                onGenerateClick = { rollNo ->

                    viewModel.generate(
                        rollNo
                    )

                    navController.navigate(
                        Routes.HALL_TICKET
                    )
                }
            )
        }

        composable(
            Routes.HALL_TICKET
        ) {

            val hallTicket by
            viewModel.hallTicket.collectAsState()

            hallTicket?.let {

                HallTicketScreen(
                    hallTicket = it
                )
            }
        }
    }
}