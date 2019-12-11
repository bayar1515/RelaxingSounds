package com.teknasyonchallenge.relaxingsounds.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.teknasyonchallenge.relaxingsounds.model.VoiceModel

class DBHelper(val context: Context) : SQLiteOpenHelper(context,DBHelper.DATABASE_NAME,null,DBHelper.DATABASE_VERSION) {
    private val TABLE_NAME="voice"
    private val COL_VOICE_ID = "voice_id"
    private val COL_NAME = "name"
    private val COL_VOICE = "voice"
    private val COL_LENGTH = "length"
    companion object {
        private val DATABASE_NAME = "SQLITE_DATABASE"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_VOICE_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME  VARCHAR(256),$COL_VOICE  VARCHAR(256),$COL_LENGTH   VARCHAR(256))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(voiceModel:VoiceModel) {
        val sqliteDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_VOICE_ID, voiceModel.getVoiceId())
        contentValues.put(COL_NAME, voiceModel.getName())
        contentValues.put(COL_VOICE, voiceModel.getVoice())
        contentValues.put(COL_LENGTH, voiceModel.getLength())

        val result = sqliteDB.insert(TABLE_NAME, null, contentValues)
    }

    fun readData():ArrayList<VoiceModel>{
        val userList = arrayListOf<VoiceModel>()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = sqliteDB.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                val voice = VoiceModel()
                voice.setVoiceId(result.getString(result.getColumnIndex(COL_VOICE_ID)).toInt())
                voice.setName(result.getString(result.getColumnIndex(COL_NAME)))
                voice.setVoice(result.getString(result.getColumnIndex(COL_VOICE)))
                voice.setLength(result.getString(result.getColumnIndex(COL_LENGTH)))
                userList.add(voice)
            }while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return userList
    }
    fun deleteAllData(){
        val sqliteDB = this.writableDatabase
        sqliteDB.delete(TABLE_NAME,null,null)
        sqliteDB.close()

    }

    fun update(voice: VoiceModel) {
        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                val cv = ContentValues()
                cv.put(COL_NAME,(result.getString(result.getColumnIndex(COL_LENGTH))+voice.getName()))
                cv.put(COL_VOICE,(result.getString(result.getColumnIndex(COL_LENGTH))+voice.getVoice()))
                cv.put(COL_LENGTH,(result.getString(result.getColumnIndex(COL_LENGTH))+voice.getLength()))

                db.update(TABLE_NAME,cv, "$COL_VOICE_ID=? AND $COL_NAME=?",
                    arrayOf(result.getString(result.getColumnIndex(COL_VOICE_ID)),
                        result.getString(result.getColumnIndex(COL_NAME))))
            }while (result.moveToNext())
        }

        result.close()
        db.close()
    }

    fun delete(voiceModel: VoiceModel):Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME,COL_VOICE_ID +"=?", arrayOf(voiceModel.getVoiceId().toString()))
        if(result == 1)
            return true
        return false
    }


}