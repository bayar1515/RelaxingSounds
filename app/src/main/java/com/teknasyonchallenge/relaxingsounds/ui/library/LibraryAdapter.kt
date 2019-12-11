package com.teknasyonchallenge.relaxingsounds.ui.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teknasyonchallenge.relaxingsounds.R
import com.teknasyonchallenge.relaxingsounds.model.VoiceCategoryModel
import com.teknasyonchallenge.relaxingsounds.model.VoiceModel
import kotlinx.android.synthetic.main.card_library.view.*

class LibraryAdapter(): RecyclerView.Adapter<LibraryAdapter.ViewHolder>() {
    private var list:ArrayList<VoiceCategoryModel> = ArrayList()
    private lateinit var listener: ILibrary
    constructor(list:ArrayList<VoiceCategoryModel>,listener:ILibrary):this() {
        this.list = list
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_library, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].getName()
        when(list[position].getCategoryId()){
            0 -> { holder.image.setImageResource(R.drawable.ic_category_wolf) }
            1 -> { holder.image.setImageResource(R.drawable.ic_category_cicada) }
            2 -> { holder.image.setImageResource(R.drawable.ic_category_cricket) }
            3 -> { holder.image.setImageResource(R.drawable.ic_category_frog) }
            4 -> { holder.image.setImageResource(R.drawable.ic_category_bird)}
            5 -> { holder.image.setImageResource(R.drawable.ic_category_fire)}
            6 -> { holder.image.setImageResource(R.drawable.ic_category_water)}
            7 -> { holder.image.setImageResource(R.drawable.ic_category_weather) }
        }

        holder.itemView.setOnClickListener { list[position].getName()?.let { it1 ->
            listener.itemClicked(list[position].getVoices(),
                it1
            )
        } }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.title
        val image = itemView.image
    }

    interface ILibrary{
        fun itemClicked(voiceList:ArrayList<VoiceModel>,title:String)
    }
}