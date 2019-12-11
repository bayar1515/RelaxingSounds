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
import com.teknasyonchallenge.relaxingsounds.ui.HomeActivity
import com.teknasyonchallenge.relaxingsounds.utils.DBHelper
import com.teknasyonchallenge.relaxingsounds.utils.SharedController

class FavoritesFragment : Fragment(),FavoritesAdapter.IFavorites {

    private  val db by lazy { DBHelper(activity as Activity)  }
    private lateinit var adapter: FavoritesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var noDataText:TextView
    private var  favoriteList:ArrayList<VoiceModel> = ArrayList()
    private lateinit var sharedController: SharedController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        sharedController = SharedController(activity as Activity)
        (activity as HomeActivity).setAppBarTitle(resources.getString(R.string.my_favorites))

        noDataText = view.findViewById(R.id.notDataTextView)
        recyclerView = view.findViewById(R.id.recyclerView)
        favoriteList = sharedController.getRefactoredList(db.readData())

        if (favoriteList.size > 0) {
            noDataText.visibility = View.GONE
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = FavoritesAdapter(this,activity as Activity)
            recyclerView.adapter = adapter
            adapter.setList(favoriteList)
        }else
           noDataText.visibility = View.VISIBLE

        return view
    }

    override fun removeItem(voiceModel: VoiceModel) {
        if (db.delete(voiceModel)) {
            favoriteList = sharedController.getRefactoredList(db.readData())
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
