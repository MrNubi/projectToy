package com.beyond.projecttoy.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.beyond.projecttoy.R
import com.beyond.projecttoy.comment.CommentLVAdapter
import com.beyond.projecttoy.comment.CommentModel
import com.beyond.projecttoy.databinding.ActivityBoardReReplyBinding
import com.beyond.projecttoy.util.FBA
import com.beyond.projecttoy.util.FBRef
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class Board_Re_Reply_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardReReplyBinding
    private lateinit var title :String
    private lateinit var time :String
    private lateinit var re_CommentLVAdapter: CommentLVAdapter
    private val re_commentDataList = mutableListOf<CommentModel>()

    private lateinit var key:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_re_reply)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_re_reply)


        title = intent.getStringExtra("title123").toString()
        time = intent.getStringExtra("time123").toString()
        key =  intent.getStringExtra("key").toString()
        Log.d("뭐여", title)
        Log.d("뭐여2", time)
        var time2 = time.split(".").toString()
        var time3 = time.split(".")[0]
        var time4 = time.split(".")[1]
        var time5 = time.split(".")[2]
        var time6 = time5.split(";")[0]
        var time7 = time5.split(";")[1].split(":")[0]
        var time8 = time5.split(";")[1].split(":")[1]

        var TT = "$time3$time4$time5$time6$time7$time8"

        Log.d("뭐여4", time2)
        Log.d("뭐여5", TT.toString())

        binding.reCommentBtn.setOnClickListener {
            insertComment(key, TT)
        }

        getCommentData(key, TT)




        binding.ReReplyTitle.text = title!!
        binding.REReplyTime.text = time!!





        re_CommentLVAdapter = CommentLVAdapter(re_commentDataList)
        binding.reCommentLV.adapter = re_CommentLVAdapter







        binding.reCommentLV






    }

    fun getCommentData(key : String, Time : String){


        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                re_commentDataList.clear()

                for (dataModel in dataSnapshot.children) {

                    val item = dataModel.getValue(CommentModel::class.java)
                    re_commentDataList.add(item!!)

                }

                re_CommentLVAdapter.notifyDataSetChanged()


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("캬옹", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.re_commentRef.child(key).child(Time).addValueEventListener(postListener)


    }

    fun insertComment(key : String, TT : String){
        // comment
        //   - BoardKey
        //        - CommentKey
        //            - CommentData
        //            - CommentData
        //            - CommentData
        FBRef.re_commentRef
            .child(key)
            .child(TT)
            .push()
            .setValue(
                CommentModel(
                    binding.reCommentArea.text.toString(),
                    FBA.getTime()
                )
            )

        Toast.makeText(this, "댓글 입력 완료", Toast.LENGTH_SHORT).show()
        binding.reCommentArea.setText("")

    }

//    private fun getImageData(key : String){
//
//        // Reference to an image file in Cloud Storage
//        val storageReference = Firebase.storage.reference.child(key + ".png")
//
//        // ImageView in your Activity
//        val imageViewFromFB =
//
//        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
//            if(task.isSuccessful) {
//
//                Glide.with(this)
//                    .load(task.result)
//                    .into(imageViewFromFB)
//
//            } else {
//
//                binding.getImageArea.isVisible = false
//            }
//        })
//
//
//    }


}