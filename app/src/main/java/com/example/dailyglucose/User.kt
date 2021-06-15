package com.example.dailyglucose

class User (val id: String = "",
            val name: String = "",
            val email: String = "",
            val historiaGlukozy: List<String> = listOf<String>(),
            val historiaInsuliny: List<String> = listOf<String>() )