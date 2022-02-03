package com.example.hellogames

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_list)


        //val baseUrl = "https://education.3ie.fr/android/ressources/api/game/list.json"
        val baseUrl = "https://education.3ie.fr/android/ressources/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service: ApiInterface = retrofitClient.create(ApiInterface::class.java)

        val callback: Callback<List<GameObject>> = object : Callback<List<GameObject>> {
            override fun onResponse(
                call: Call<List<GameObject>>,
                response: Response<List<GameObject>>
            ) {
                if (response.isSuccessful) {
                    val data: List<GameObject> = response.body()!!
                    Toast.makeText(this@GameListActivity, "Nb : " + data.size, Toast.LENGTH_SHORT).show()

                    val gameRecyclerView: RecyclerView = findViewById(R.id.activity_game_list)
                    gameRecyclerView.setHasFixedSize(true)
                    gameRecyclerView.layoutManager =
                        LinearLayoutManager(this@GameListActivity, LinearLayoutManager.VERTICAL, false)
                    gameRecyclerView.addItemDecoration(
                        DividerItemDecoration(
                            this@GameListActivity,
                            LinearLayoutManager.VERTICAL
                        )
                    )

                    val onItemClickListener = View.OnClickListener {
                        val position = it.tag as Int
                        val clickedItem = data[position]
                        Toast.makeText(
                            this@GameListActivity,
                            "Clicked " + clickedItem.name,
                            Toast.LENGTH_SHORT
                        ).show()

                        val explicitIntent = Intent(this@GameListActivity, GameInfoActivity::class.java)
                        explicitIntent.putExtra("MESSAGE", position)
                        startActivity(explicitIntent)
                    }
                    gameRecyclerView.adapter =
                        GameAdapter(data, this@GameListActivity, onItemClickListener)
                }
            }

            override fun onFailure(call: Call<List<GameObject>>, t: Throwable) {
                Toast.makeText(this@GameListActivity, "Error", Toast.LENGTH_SHORT).show()
            }

        }

        service.listOfGames().enqueue(callback)
    }
}
