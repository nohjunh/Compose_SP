package com.example.authenticationdemo.utils

import com.example.authenticationdemo.network.ApiResponse
import com.example.authenticationdemo.network.ApiResult
import com.example.authenticationdemo.network.ErrorResponse
import com.example.authenticationdemo.network.onException
import com.example.authenticationdemo.network.onSuccess
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull

object Constants {

    const val BASE_URL = "https://raw.githubusercontent.com/itmmckernan/triviaJSON/master/"


}