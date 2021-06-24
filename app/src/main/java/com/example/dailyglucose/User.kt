package com.example.dailyglucose

class User (val id: String = "",
            val name: String = "",
            val email: String = "",
            var historiaGlukozy: MutableList<String> = mutableListOf(),
            var historiaInsuliny: MutableList<String> = mutableListOf())