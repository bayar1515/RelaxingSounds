package com.teknasyonchallenge.relaxingsounds.ui.library

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teknasyonchallenge.relaxingsounds.R
import com.teknasyonchallenge.relaxingsounds.model.VoiceModel
import kotlinx.android.synthetic.main.card_category_detail.view.*

class CategoryDetailAdapter(): RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder>() {
    private lateinit var listener: ICategoryDetail
    private var list:ArrayList<VoiceModel> = ArrayList()
    private lateinit var context: Context
    constructor(list:ArrayList<VoiceModel>,listener: ICategoryDetail,context:Context):this() {
        this.list = list
        this.listener = listener
        this.context = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_category_detail, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].getName()
        holder.length.text = String.format(context.resources.getString(R.string.length_holder),list[position].getLength())
        holder.add.setOnClickListener { listener.addButtonClicked(list[position]) }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.name
        val length = itemView.length
        val add = itemView.addButton
    }

    interface ICategoryDetail {
        fun addButtonClicked(voiceModel: VoiceModel)
    }
}