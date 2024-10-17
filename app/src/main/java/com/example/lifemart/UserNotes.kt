package com.example.lifemart

data class UserNotes(
    val noteTitle : String?=null,
    val noteDescription : String?=null
){
    constructor():this("","")
}