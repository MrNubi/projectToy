package com.beyond.projecttoy.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.beyond.projecttoy.R
import com.beyond.projecttoy.databinding.ActivityBoardEditBinding
import com.beyond.projecttoy.util.FBA
import com.beyond.projecttoy.util.FBRef

import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.lang.Exception

class BoardEditActivity : AppCompatActivity() {

    private lateinit var key:String

    private lateinit var binding : ActivityBoardEditBinding
    var storage = Firebase.storage
    val storageRef = storage.reference

    private val TAG = BoardEditActivity::class.java.simpleName

    private lateinit var writerUid : String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_edit)

        key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)

        binding.editBtn.setOnClickListener {
            editBoardData(key)
        }


    }

    private fun editBoardData(key : String){

       var k = storageRef.child("${FBA.getUid()}.png").downloadUrl

        FBRef.boardRef
            .child(key)
            .setValue(
                BoardModel(binding.titleArea.text.toString(),
                    binding.contentArea.text.toString(),
                    writerUid,
                    FBA.getTime())
            )

        Toast.makeText(this, "수정완료", Toast.LENGTH_LONG).show()

        finish()

    }

    private fun getImageData(key : String){

        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageViewFromFB = binding.imageArea

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if(task.isSuccessful) {

                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)

            } else {

            }
        })


    }

    private fun getBoardData(key : String){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val dataModel = dataSnapshot.getValue(BoardModel::class.java)
//                Log.d(TAG, dataModel.toString())
//                Log.d(TAG, dataModel!!.title)
//                Log.d(TAG, dataModel!!.time)

                binding.titleArea.setText(dataModel?.title)
                binding.contentArea.setText(dataModel?.content)
                writerUid = dataModel!!.uid


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        FBRef.boardRef.child(key).addValueEventListener(postListener)


    }






}