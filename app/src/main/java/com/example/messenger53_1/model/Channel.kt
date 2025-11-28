package com.example.messenger53_1.model

data class Channel(
    val id: String = "",
    val name: String,
    val createdAT:Long = System.currentTimeMillis()
)