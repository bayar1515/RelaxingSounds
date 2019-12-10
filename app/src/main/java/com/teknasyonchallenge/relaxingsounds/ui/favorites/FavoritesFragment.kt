package com.teknasyonchallenge.relaxingsounds.ui.favorites
import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.teknasyonchallenge.relaxingsounds.R
import com.teknasyonchallenge.relaxingsounds.model.VoiceModel
import com.teknasyonchallenge.relaxingsounds.utils.DBHelper

class FavoritesFragment : Fragment(),FavoritesAdapter.IFavorites {

    private  val db by lazy { DBHelper(activity as Activity)  }
    private lateinit var adapter: FavoritesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var noDataText:TextView
    private lateinit var mediaPlayer:MediaPlayer
    private var  favoriteList:ArrayList<VoiceModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    fun getRefactoredList(list:ArrayList<VoiceModel>):ArrayList<VoiceModel> {
        for (i in list){
            when(i.getVoiceId()){
                0 -> { i.setVoiceMedia(MediaPlayer.create(context,R.raw.wolf)) }
                1 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.cicada))}
                2 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.cricket))}
                3 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.frog1))}
                4 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.frog2))}
                5 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.bird1))}
                6 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.bird2))}
                7 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.bird3))}
                8 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.bird4))}
                9 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.fire1))}
                10 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.fire2))}
                11 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.water1))}
                12 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.water2))}
                13 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.water3))}
                14 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.water4))}
                15 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.water5))}
                16 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.air1))}
                17 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.air2))}
                18 -> {i.setVoiceMedia(MediaPlayer.create(context,R.raw.air3))}
            }
        }

        return list
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)






        noDataText = view.findViewById(R.id.notDataTextView)
        recyclerView = view.findViewById(R.id.recyclerView)
        favoriteList =getRefactoredList(db.readData())







        if (favoriteList.size > 0) {
            noDataText.visibility = View.GONE
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = FavoritesAdapter(this)
            recyclerView.adapter = adapter
            adapter.setList(favoriteList)
        }else
           noDataText.visibility = View.VISIBLE




        return view
    }

    override fun removeItem(voiceModel: VoiceModel) {
        if (db.delete(voiceModel)) {
            favoriteList = db.readData()
            adapter.setList(favoriteList)
            if (favoriteList.size == 0)
                noDataText.visibility = View.VISIBLE
            else
                noDataText.visibility = View.GONE
            Toast.makeText(activity,resources.getString(R.string.favorite_voice_remove_message),Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(activity,resources.getString(R.string.favorite_voice_remove_error_message),Toast.LENGTH_SHORT).show()
    }

    override fun pauseVoice(mediaPlayer: MediaPlayer) {
        mediaPlayer.pause()
    }

    override fun playVoice(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
    }

    override fun changeVoice(mediaPlayer: MediaPlayer,progress: Float) {
        mediaPlayer.setVolume(progress,progress)
    }


}
