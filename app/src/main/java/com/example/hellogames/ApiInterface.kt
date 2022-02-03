package com.example.hellogames

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("api/game/list.json")
    fun listOfGames() : Call<List<GameObject>>

    @GET("api/game/details{game_id}.json")
    fun getGameInfo(@Path("game_id") game_id: String) : Call<GameInfoObject>

}