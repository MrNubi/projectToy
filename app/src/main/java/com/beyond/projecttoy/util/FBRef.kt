package com.beyond.projecttoy.util

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBRef {


    companion object{

        private val database = Firebase.database

        val category1 = database.getReference("contents")

        val bookMarkRef = database.getReference("bookmark_list")

        val commentRef = database.getReference("comment")

        val iconRef = database.getReference("icon")

        val ProfileRef = database.getReference("Profile")

        val re_commentRef = database.getReference("re_comment")



        val boardRef = database.getReference("board")


}}