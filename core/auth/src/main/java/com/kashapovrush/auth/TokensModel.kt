package com.kashapovrush.auth

data class TokensModel(
    val accessToken: String,
    val refreshToken: String,
    val idToken: String
)