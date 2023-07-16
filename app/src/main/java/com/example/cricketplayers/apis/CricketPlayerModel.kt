package com.example.cricketplayers.apis

data class CricketPlayerModel(
    val apikey: String,
    val `data`: Data,
    val info: Info,
    val status: String
)