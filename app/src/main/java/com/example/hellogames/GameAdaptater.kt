package com.example.hellogames

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GameAdapter(
    private val data: List<GameObject>,
    private val context: Activity,
    private val onItemClickListener: View.OnClickListener
) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameImageView: ImageView = itemView.findViewById(R.id.row_game_image)
        val gameTextView: TextView = itemView.findViewById(R.id.row_game_title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowView: View = LayoutInflater.from(context).inflate(R.layout.row_game, parent, false)
        rowView.setOnClickListener(onItemClickListener)
        return ViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.gameTextView.text = data[position].name
        Glide.with(context)
            .load(data[position].picture)
            .into(holder.gameImageView)
        holder.itemView.tag = position
    }

    override fun getItemCount(): Int {
        return data.size
    }


}