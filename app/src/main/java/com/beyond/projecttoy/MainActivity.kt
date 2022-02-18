package com.beyond.projecttoy

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.beyond.projecttoy.board.BoardActivity
import com.beyond.projecttoy.board.BoardinnerActivity

import com.beyond.projecttoy.databinding.ActivityMainBinding
import com.beyond.projecttoy.setting.SettingActivity
import com.beyond.projecttoy.util.FBA
import com.beyond.projecttoy.util.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import splitties.activities.start

class MainActivity : AppCompatActivity() {

    private var database = Firebase.database

     var GS = ""


    private var UID = ""

    var nigimi = ""

    private lateinit var binding: ActivityMainBinding
    private val LvDataList = mutableListOf<LvModel>()
    private val LvKeyList = mutableListOf<String>()
    private lateinit var LVadapter : MainLVadapter
    val storageRef = FirebaseStorage.getInstance().reference





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
             setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        LVadapter = MainLVadapter(LvDataList)

        binding.mainLV.adapter = LVadapter

        binding.mainLV.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, BoardinnerActivity::class.java)
            intent.putExtra("key", LvKeyList[position])
            Log.d("슈팅스타", LvKeyList[position])


            startActivity(intent)
        }

        binding.Writebtn.setOnClickListener {

            start<BoardActivity>()


        }


        getFBBoardData()



    var    myRefNick = FBRef.proNickRef.child("${FBA.getUid()}")

        myRefNick.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue()
                Log.d(TAG, "Value is: $value")


            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }



        })







        findViewById<ImageView>(R.id.settingBtn).setOnClickListener {

            FBRef.iconRef.child("${FBA.getUid()}").get().addOnSuccessListener {
                Log.i("햝", "Got value ${it.value}")
               var iconCode = it.value.toString()
                nigimi = iconCode
                UID = FBA.getUid()
                Log.d("록귀", nigimi)
                val intent = Intent(this, SettingActivity::class.java)
                intent.putExtra("key",UID)
                intent.putExtra("k",nigimi)
                startActivity(intent)




            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }



        }











    }

    private fun getFBBoardData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                LvDataList.clear()
                // Get Post object and use the values to update the UI
                // val post = dataSnapshot.getValue<Post>()
                // ...
                for (dataModel in dataSnapshot.children){

//                    dataModel.key

                    Log.d(TAG, dataModel.toString())

                    val item = dataModel.getValue(LvModel::class.java)
                    LvDataList.add(item!!)
                    LvKeyList.add(dataModel.key.toString())




                }
                LvKeyList.reverse()
                LvDataList.reverse()

                LVadapter.notifyDataSetChanged()
                Log.d("곽", LvDataList.toString())



            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "갸갸거겨", databaseError.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)

    }





}