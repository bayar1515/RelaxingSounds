package com.teknasyonchallenge.relaxingsounds.model

import android.media.MediaPlayer
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class VoiceModel {
    @SerializedName("voice_id")
    @Expose
    private var voiceId: Int? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("voice")
    @Expose
    private var voice: String? = null
    @SerializedName("length")
    @Expose
    private var length: String? = null
    private var isVoicePlaying = false

    private var voiceMedia:MediaPlayer? = null

    fun getVoiceMedia():MediaPlayer {
        return this.voiceMedia!!
    }

    fun setVoiceMedia(voiceMedia:MediaPlayer){
        this.voiceMedia = voiceMedia
        this.voiceMedia!!.setVolume(0.50F,0.50F)
    }

    fun setIsPlaying(isVoicePlaying:Boolean){
        this.isVoicePlaying = isVoicePlaying
    }

    fun isVoicePlaying():Boolean {
        return isVoicePlaying
    }

    fun getVoiceId(): Int? {
        return voiceId
    }

    fun setVoiceId(voiceId: Int?) {
        this.voiceId = voiceId
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getVoice(): String? {
        return voice
    }

    fun setVoice(voice: String?) {
        this.voice = voice
    }

    fun getLength(): String? {
        return length
    }

    fun setLength(length: String?) {
        this.length = length
    }
}