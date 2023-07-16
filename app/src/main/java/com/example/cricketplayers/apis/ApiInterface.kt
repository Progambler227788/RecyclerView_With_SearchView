package com.example.cricketplayers.apis

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/v1/players_info")
    suspend fun getPlayers(
        @Query ("apikey") apikey :String,
        @Query ("id") id : String
    ) : Response<CricketPlayerModel>
}