package com.kashapovrush.network.dto

import com.google.gson.annotations.SerializedName

data class ResponseUsers(
    @SerializedName("items") val users: List<UserDto>
)
