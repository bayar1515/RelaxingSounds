package com.teknasyonchallenge.relaxingsounds.utils

import android.content.Context
import android.media.MediaPlayer
import com.teknasyonchallenge.relaxingsounds.R
import com.teknasyonchallenge.relaxingsounds.model.VoiceModel
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class SharedController() {
    private lateinit var context: Context
    constructor(context: Context):this(){
        this.context = context
    }

    fun getAssetJsonData(): String {
        var json: String = ""
        json = try {
            val `is`: InputStream = context.assets.open("voices.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
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

    fun getRefactoredList(list:ArrayList<VoiceModel>):ArrayList<VoiceModel> {
        for (i in list){
            when(i.getVoiceId()){
                0 -> { i.setVoiceMedia(MediaPlayer.create(context, R.raw.wolf)) }
                1 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.cicada))}
                2 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.cricket))}
                3 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.frog1))}
                4 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.frog2))}
                5 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.bird1))}
                6 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.bird2))}
                7 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.bird3))}
                8 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.bird4))}
                9 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.fire1))}
                10 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.fire2))}
                11 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.water1))}
                12 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.water2))}
                13 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.water3))}
                14 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.water4))}
                15 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.water5))}
                16 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.air1))}
                17 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.air2))}
                18 -> {i.setVoiceMedia(MediaPlayer.create(context, R.raw.air3))}
            }
        }

        return list
    }
}