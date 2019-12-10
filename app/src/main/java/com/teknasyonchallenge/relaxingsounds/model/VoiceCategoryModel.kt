package com.teknasyonchallenge.relaxingsounds.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VoiceCategoryModel  {
    @SerializedName("category_id")
    @Expose
    private var categoryId: Int? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("voices")
    @Expose
    private var voices: ArrayList<VoiceModel> = ArrayList()

    fun getCategoryId(): Int? {
        return categoryId
    }

    fun setCategoryId(categoryId: Int?) {
        this.categoryId = categoryId
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getVoices(): ArrayList<VoiceModel> {
        return voices
    }

    fun setVoices(voices: ArrayList<VoiceModel>) {
        this.voices = voices
    }
}