package com.example.cricketplayers.apis

import com.example.cricketplayers.constants.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {
    fun getInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).build()
    }

}