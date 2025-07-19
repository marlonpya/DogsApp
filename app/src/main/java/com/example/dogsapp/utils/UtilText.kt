package com.example.dogsapp.utils

fun Int.toAge() = "$this ${if (this == 1) "year" else "years"} old"
fun Int.toAgeAlmost() = "$this ${if (this == 1) "year" else "years"} old"
