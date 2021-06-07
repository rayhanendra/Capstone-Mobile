package com.example.capstonemobile.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManagement(context: Context) {
    companion object{
        private val PREF_NAME = "CapstoneMobile"
        private val IS_LOGIN = "IsLoggedIn"
        private val IS_FIRST = "isFirstOpen"

        val key_id = "id"
        val key_email = "EMAIL"
        val key_password = "password"
    }
    var pref: SharedPreferences

    var editor: SharedPreferences.Editor

    var PRIVATE_MODE = 0

    init {
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = pref.edit()
    }

    val phase: String
        get() = pref.getString("phase","").toString()

    val user: HashMap<String,String>
        get() {
            val  user = HashMap<String , String>()
            user[key_id] = pref.getString(key_id, "").toString()
            user[key_email] = pref.getString(key_email, "").toString()
            user[key_password] = pref.getString(key_password, "").toString()

            return user
        }

    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)

    val isFirstOpen: Boolean
        get() = pref.getBoolean(IS_FIRST, false)

    fun setPhase(name: String){
        editor.putString("phase",name)
        editor.commit()
    }

    fun createLogin(id: String){
        editor.putBoolean(IS_LOGIN,true)
        editor.putString(key_id,id)
        editor.commit()
    }

    fun checkLogin(): Boolean = this.isLoggedIn
    fun checkFirst(): Boolean = this.isFirstOpen
}