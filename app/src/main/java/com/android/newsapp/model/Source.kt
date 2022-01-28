package com.android.newsapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source(
    @SerialName("id")
    val id: Any,
    @SerialName("name")
    val name: String
)