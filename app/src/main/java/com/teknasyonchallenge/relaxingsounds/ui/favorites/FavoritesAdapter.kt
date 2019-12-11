package com.teknasyonchallenge.relaxingsounds.ui.favorites

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.recyclerview.widget.RecyclerView
import com.teknasyonchallenge.relaxingsounds.R
import com.teknasyonchallenge.relaxingsounds.model.VoiceModel
import com.teknasyonchallenge.relaxingsounds.utils.SharedController
import kotlinx.android.synthetic.main.card_favorites.view.*

class FavoritesAdapter(): RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private var list:ArrayList<VoiceModel> = ArrayList()
    private lateinit var listener: IFavorites
    private lateinit var context: Context
    private lateinit var sharedController:SharedController
    constructor(listener:IFavorites,context:Context):this() {
        this.listener = listener
        this.context = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_favorites, parent, false)
        sharedController = SharedController(context)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].getName()
        holder.length.text = list[position].getLength()
        holder.remove.setOnClickListener { listener.removeItem(list[position]) }
        holder.playPause.setOnClickListener {
            if (list[position].isVoicePlaying()){
                holder.playPause.setImageResource(R.drawable.ic_play)
                list[position].setIsPlaying(false)
                listener.pauseVoice(list[position].getVoiceMedia())
            }else {
                holder.playPause.setImageResource(R.drawable.ic_pause)
                list[position].setIsPlaying(true)
                listener.playVoice(list[position].getVoiceMedia())
            }
        }
        holder.changeVoice.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                listener.changeVoice(list[position].getVoiceMedia(),sharedController.seekBarNormalize(p1))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

    }

    fun setList(list:ArrayList<VoiceModel>){
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.name
        val length = itemView.length
        val changeVoice = itemView.changeVoice
        val remove = itemView.remove
        val playPause = itemView.playPause
    }

    interface IFavorites{
        fun removeItem(voiceModel: VoiceModel)
        fun playVoice(mediaPlayer: MediaPlayer)
        fun pauseVoice(mediaPlayer: MediaPlayer)
        fun changeVoice(mediaPlayer: MediaPlayer,progress:Float)
    }
}