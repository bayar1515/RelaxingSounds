package com.teknasyonchallenge.relaxingsounds.ui.favorites

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.recyclerview.widget.RecyclerView
import com.teknasyonchallenge.relaxingsounds.R
import com.teknasyonchallenge.relaxingsounds.model.VoiceModel
import kotlinx.android.synthetic.main.card_favorites.view.*

class FavoritesAdapter(): RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private var list:ArrayList<VoiceModel> = ArrayList()
    private lateinit var listener: IFavorites
    constructor(listener:IFavorites):this() {
        this.list = list
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_favorites, parent, false)
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
                listener.changeVoice(list[position].getVoiceMedia(),seekBarNormalize(p1))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

    }

    fun seekBarNormalize(progress:Int):Float {
        when(progress) {
            0 -> { return 0.0F }
            1 -> { return 0.05F}
            2 -> { return 0.10F}
            3 -> { return 0.15F}
            4 -> { return 0.20F}
            5 -> { return 0.25F}
            6 -> { return 0.30F}
            7 -> { return 0.35F}
            8 -> { return 0.40F}
            9 -> { return 0.45F}
            10 -> { return 0.50F}
            11 -> { return 0.55F}
            12 -> {return 0.60F }
            13 -> { return 0.65F}
            14 -> { return 0.70F}
            15 -> { return 0.75F}
            16 -> { return 0.80F}
            17 -> { return 0.85F}
            18 -> { return 0.90F}
            19 -> { return 0.95F}
        }
        return 1F
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