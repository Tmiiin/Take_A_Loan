package com.example.takealoan

import android.content.Context
import android.content.SharedPreferences

class Preferences(mContext: Context)  {

    companion object {
        val FILE_NAME: String = "preferences"
        val PREF_TOKEN: String = "token"
        }

    var preferences: SharedPreferences?  = mContext.getSharedPreferences(FILE_NAME, 0)

    private fun getEditor(): SharedPreferences.Editor {
        return preferences!!.edit()
    }

    fun setToken(data: String?) {
        getEditor().putString(PREF_TOKEN, data).commit()
    }

    fun getToken(): String? {
        return preferences!!.getString(PREF_TOKEN, "")
    }
}