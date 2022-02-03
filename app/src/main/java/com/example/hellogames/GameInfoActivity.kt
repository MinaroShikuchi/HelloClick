package com.example.hellogames

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)
        val extras = intent.extras
        val gameId = extras!!.getString("MESSAGE","Default")

        val nameTextView : TextView = findViewById(R.id.game_info_label_name)
        val typeTextView : TextView = findViewById(R.id.game_info_type)
        val nbPlayerTextView : TextView = findViewById(R.id.game_info_nbplayer)
        val yearTextView : TextView = findViewById(R.id.game_info_label_year)

        //API : https://education.3ie.fr/android/ressources/api/game/list.json
        val baseUrl = "https://education.3ie.fr/android/ressources/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service : ApiInterface = retrofitClient.create(ApiInterface::class.java)

        val callback : Callback<GameInfoObject> = object : Callback<GameInfoObject> {
            override fun onResponse(
                call: Call<GameInfoObject>,
                response: Response<GameInfoObject>
            ) {
                if(response.isSuccessful){
                    val data : GameInfoObject = response.body()!!

                    nameTextView.text = data.name
                    typeTextView.text = data.type
                    nbPlayerTextView.text = data.players
                    yearTextView.text = data.year.toString()


                }
            }
            override fun onFailure(call: Call<GameInfoObject>, t: Throwable) {
                Toast.makeText(this@GameInfoActivity, "Error", Toast.LENGTH_SHORT).show()
            }

        }

        service.getGameInfo(gameId).enqueue(callback)
    }
}