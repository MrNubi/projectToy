package com.beyond.projecttoy

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.beyond.projecttoy.setting.SettingActivity
import com.beyond.projecttoy.util.FBA
import com.beyond.projecttoy.util.FBRef
import com.beyond.projecttoy.util.FBRef.Companion.storage
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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




        title.setOnClickListener {
        Log.d("걍","$time")
                }


//0,13,26
        FBRef.boardRef.get().addOnSuccessListener {
            Log.i("햝", "Got value ${it.value.toString()}")

            var TK = it.value.toString()
            var enum = p0*13
            var YY = ArrayList<String>()
            var T = Lvlist.size - 1


    
//            var TK2=TK.replace("{", "").replace("}", "").replace("=","")
//            var TK2=TK.replace("{", "").replace("}", "").replace("=","")
//            Log.i("햝_TK", "${TK}")
//            Log.i("햝_TK2", "${TK2}")


//            var YT = if(p0 == 0) p0 else p0*2-1
//            val R : String = TK2.toString().split(",")[YT]
//            Log.i("햝_TK3", "${R}")
//
//            var YU = TK2.toString().split(",")
//            var YY = YU[p0*6].split("=")[0]

//            var TK3= mutableListOf<String>()
//            for (molamola in TK){
//
//                TK3.add("$YY")
//                Log.d("햝10", "$YY")
//
//            }
//
//
//
//
//            Log.i("햝", "${R.toString()}")
//            Log.i("햝2", "${YY}")






            val storage = Firebase.storage


            val storageReference = Firebase.storage.reference.child(Lvlist[p0].key+".png")
            Log.d("햝_KEY", Lvlist[p0].key+".png")

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