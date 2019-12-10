package com.teknasyonchallenge.relaxingsounds.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class DataModel {
    @SerializedName("data")
    @Expose
    private var data: ArrayList<VoiceCategoryModel> = ArrayList()

    fun getData(): ArrayList<VoiceCategoryModel> {
        return data
    }

    fun setData(data: ArrayList<VoiceCategoryModel>) {
        this.data = data
    }
}