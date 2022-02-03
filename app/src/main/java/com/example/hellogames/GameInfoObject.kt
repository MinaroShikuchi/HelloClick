package com.example.hellogames

data class GameInfoObject(
    val id: Int,
    val name: String,
    val type: String,
    val players: String,
    val year: Int,
    val picture: String, val description_en: String
) {
}