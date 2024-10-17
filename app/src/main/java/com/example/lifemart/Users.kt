package com.example.lifemart

import android.provider.ContactsContract.CommonDataKinds.Nickname


data class Users(
    val pic:String?=null,
    val fullName:String?=null,
    val workMail:String?=null,
    val workNumber:String?=null,
    val position:String?=null,
    val nickname:String?=null
)
