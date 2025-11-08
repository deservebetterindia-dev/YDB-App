package com.deservebetter.ydbandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deservebetter.ydbandroid.data.model.AdminDashboardData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import android.content.Context
import com.deservebetter.ydbandroid.R
import javax.inject.Inject

@HiltViewModel
class AdminOverviewViewModel @Inject constructor() : ViewModel() {

    private val _dashboardData = MutableStateFlow<AdminDashboardData?>(null)
    val dashboardData: StateFlow<AdminDashboardData?> = _dashboardData.asStateFlow()

    fun loadDashboardData(context: Context) {
        viewModelScope.launch {
            val jsonString = withContext(Dispatchers.IO) {
                context.assets.open("admin_dashboard.json").bufferedReader().use { it.readText() }
            }
            _dashboardData.value = Json.decodeFromString(jsonString)
        }
    }
}
