package com.teknasyonchallenge.relaxingsounds.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teknasyonchallenge.relaxingsounds.R

class FavoritesAdapter(): RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_favorites, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}