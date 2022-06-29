package com.sardorbek.dastyorapp.models

data class SuccesModel(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val token_type: String
)