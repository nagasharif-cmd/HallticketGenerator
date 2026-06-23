package com.example.hallticketgen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hallticketgen.HallTicketRepository
import com.example.hallticketgen.model.Db.data.entity.HallTicketDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HallTicketViewModel(
    private val repository: HallTicketRepository
) : ViewModel() {

    private val _hallTicket =
        MutableStateFlow<HallTicketDetails?>(null)

    val hallTicket: StateFlow<HallTicketDetails?> =
        _hallTicket.asStateFlow()

    fun generate(
        rollNo: String
    ) {

        viewModelScope.launch {

            _hallTicket.value =
                repository.generateTicket(
                    rollNo
                )
        }
    }
}