package com.example.cricketplayers.apis

data class Info(
    val cache: Int,
    val credits: Int,
    val hitsLimit: Int,
    val hitsToday: Int,
    val hitsUsed: Int,
    val queryTime: Double,
    val s: Int,
    val server: Int
)