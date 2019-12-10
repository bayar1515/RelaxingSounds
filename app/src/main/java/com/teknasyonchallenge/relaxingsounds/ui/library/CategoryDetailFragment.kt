package com.teknasyonchallenge.relaxingsounds.ui.library
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.teknasyonchallenge.relaxingsounds.R
import com.teknasyonchallenge.relaxingsounds.model.VoiceModel

class CategoryDetailFragment : Fragment(),CategoryDetailAdapter.ICategoryDetail {
    private  var voiceList:ArrayList<VoiceModel> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            voiceList = arguments?.getSerializable("voice_list") as ArrayList<VoiceModel>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category_detail, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = CategoryDetailAdapter(voiceList,this,activity as Activity)
        recyclerView.adapter = adapter

        return view
    }

    override fun addButtonClicked() {

    }

    companion object {
        @JvmStatic
        fun newInstance(voiceList: ArrayList<VoiceModel>) =
            CategoryDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("voice_list", voiceList)
                }
            }
    }
}
