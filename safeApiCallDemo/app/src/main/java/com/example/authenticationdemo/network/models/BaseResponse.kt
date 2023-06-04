package com.example.authenticationdemo.network.models

import com.google.gson.annotations.SerializedName

abstract class BaseResponse<T : Any>(
    val code : Int,
    val message : String,
    val data : T
)