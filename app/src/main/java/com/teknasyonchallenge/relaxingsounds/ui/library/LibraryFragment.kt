package com.teknasyonchallenge.relaxingsounds.ui.library

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.teknasyonchallenge.relaxingsounds.R
import com.teknasyonchallenge.relaxingsounds.model.DataModel
import com.teknasyonchallenge.relaxingsounds.model.VoiceModel
import com.teknasyonchallenge.relaxingsounds.ui.HomeActivity
import com.teknasyonchallenge.relaxingsounds.utils.SharedController

class LibraryFragment : Fragment(),LibraryAdapter.ILibrary {
    private lateinit var sharedController: SharedController
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        (activity as HomeActivity).setAppBarTitle(resources.getString(R.string.library))

        sharedController = SharedController(activity as Activity)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)


        val data = sharedController.getAssetJsonData()
        val voiceCategoryModel = Gson().fromJson(data, DataModel::class.java)
        val adapter = LibraryAdapter(voiceCategoryModel.getData(),this)
        recyclerView.adapter = adapter


        return view
    }

    override fun itemClicked(voiceList: ArrayList<VoiceModel>,title:String) {
        (activity as HomeActivity).openFragment(CategoryDetailFragment.newInstance(voiceList,title))
    }
}
