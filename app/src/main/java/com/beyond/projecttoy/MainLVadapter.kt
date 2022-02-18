package com.beyond.projecttoy

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.beyond.projecttoy.setting.SettingActivity
import com.beyond.projecttoy.util.FBA
import com.beyond.projecttoy.util.FBRef
import com.beyond.projecttoy.util.FBRef.Companion.storage
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class MainLVadapter(val Lvlist : MutableList<LvModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return Lvlist.size
    }

    override fun getItem(p0: Int): Any {
        return Lvlist[p0]

    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, c1: View?, p2: ViewGroup?): View {


        var view = c1
        if (c1 == null) {

            view =
                LayoutInflater.from(p2?.context).inflate(R.layout.main_lv_item, p2, false)



        }

        val content = view?.findViewById<TextView>(R.id.listViewContentArea)
        val time = view?.findViewById<TextView>(R.id.listViewTimeArea)
        val title = view?.findViewById<TextView>(R.id.listViewTitleArea)
        val img = view?.findViewById<ImageView>(R.id.Img_Main)



// ImageView in your Activity

// Download directly from StorageReference using Glide
// (See MyAppGlideModule for Loader registration)



        img!!.setImageResource(Lvlist[p0].img)
        time!!.text = Lvlist[p0].time
        content!!.text = Lvlist[p0].content
        title!!.text = Lvlist[p0].title



        FBRef.boardRef.get().addOnSuccessListener {
            Log.i("햝", "Got value ${it.key.toString()}")
            var YT = p0*6
            val R : String = it.value.toString().split(",")[YT]

//            var YU = it.value.toString().split(",")
//            var YY = YU[p0*6].split("=")[0]
//
//
//
//
//            Log.i("햝", "${R.toString()}")
//            Log.i("햝2", "${YY}")
//
//
//            val key = FBRef.boardRef.push().key.toString()
//            Log.d("캬옹_key", key)


            val storage = Firebase.storage


            val storageReference = Firebase.storage.reference.child("-Mw2cpSDse1awCIS3ha8.png")

            Log.d("캬옹", p0.toString())

            // ImageView in your Activity
            val imageViewFromFB = view?.findViewById<ImageView>(com.beyond.projecttoy.R.id.Img_Main)

            storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
                if(task.isSuccessful) {

                    Glide.with(view!!)
                        .load(task.result)
                        .into(img)

                } else {

                    img.isVisible = false
                }
            })





        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }





        return view!!



    }
}