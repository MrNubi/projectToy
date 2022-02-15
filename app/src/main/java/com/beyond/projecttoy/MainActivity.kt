package com.beyond.projecttoy

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.database.ValueEventListener
import splitties.activities.start

class MainActivity : AppCompatActivity() {

    private var UID = ""

    private var nigimi = ""

    private lateinit var binding: ActivityMainBinding
    private val LvDataList = mutableListOf<LvModel>()
    private val LvKeyList = mutableListOf<String>()
    private lateinit var LVadapter : MainLVadapter





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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




        findViewById<ImageView>(R.id.settingBtn).setOnClickListener {

            FBRef.iconRef.get().addOnSuccessListener {
                Log.i("햝", "Got value ${it.value}")
               var iconCode = it.value.toString()
                nigimi = iconCode
                Log.d("록귀", iconCode)

            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }


            UID = FBA.getUid()
            val intent = Intent(this, SettingActivity::class.java)
            intent.putExtra("key",UID)
            intent.putExtra("k",nigimi)
            startActivity(intent)
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
                Log.d(TAG, LvDataList.toString())



            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "갸갸거겨", databaseError.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)

    }



}