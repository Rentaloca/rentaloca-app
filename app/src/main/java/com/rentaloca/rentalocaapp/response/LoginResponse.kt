package com.rentaloca.rentalocaapp.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("error")
    var error: Boolean,

    @field:SerializedName("data")
    var data: Data
)

data class Data(
    @field:SerializedName("name")
    var name: String,

    @field:SerializedName("access_token")
    var token: String
)

