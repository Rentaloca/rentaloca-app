package com.rentaloca.rentalocaapp.model

data class UserModel(
    val fullname: String,
    val email: String,
    val password: String,
    val notelpon: String,
    val alamat: String,
    val norekening: String,
    val isLogin: Boolean
)
