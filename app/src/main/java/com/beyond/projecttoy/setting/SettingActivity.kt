package com.beyond.projecttoy.setting

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.beyond.projecttoy.LvModel
import com.beyond.projecttoy.MainActivity

import com.beyond.projecttoy.R
import com.beyond.projecttoy.board.BoardinnerActivity
import com.beyond.projecttoy.comment.CommentLVAdapter
import com.beyond.projecttoy.comment.CommentModel
import com.beyond.projecttoy.databinding.ActivitySettingBinding
import com.beyond.projecttoy.splash
import com.beyond.projecttoy.util.FBA
import com.beyond.projecttoy.util.FBRef
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.security.AccessController.getContext

class SettingActivity : AppCompatActivity() {

    private val keyList = mutableListOf<String>()

    private lateinit var iconCode:String

    private lateinit var userid :String

    private val iconCodeList = mutableListOf<String>()

    private lateinit var settingAdapter : SettingAdapter





    private lateinit var auth: FirebaseAuth

    private lateinit var storage: FirebaseStorage

    private lateinit var binding: ActivitySettingBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)

        auth = Firebase.auth

        storage = Firebase.storage
        database = Firebase.database.reference



        // Create a storage reference from our app
        val storageRef = storage.reference








        var key = intent.getStringExtra("key")
        Log.d("여기","$key")










    val uid = FBA.getUid().toString()











        getIconImageData(key.toString())
        geticoncodeData()
        getImageData(key.toString())

        settingAdapter = SettingAdapter(iconCodeList)
        binding.invisable.adapter = settingAdapter
        iconCode = iconCodeList.toString()

        Log.d("캴라2", iconCodeList?.toString())




















        val ICdialog = findViewById<ImageView>(R.id.Icon_choice_btn)
        ICdialog.setOnClickListener {

            val mDialogView = LayoutInflater.from(this).inflate(R.layout.icon_sellect_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)


           val mAlterDialog = mBuilder.show()

            mAlterDialog.findViewById<ImageView>(R.id.icon_10)!!.setOnClickListener {
                val key = intent.getStringExtra("key")

                val database = Firebase.database
                val myRef = database.getReference("icon")
                val model = DialogDataModel("10","$key")

                myRef.child("$key").setValue("10")


            }
            mAlterDialog.findViewById<ImageView>(R.id.icon_9)!!.setOnClickListener {
                val key = intent.getStringExtra("key")

                val database = Firebase.database
                val myRef = database.getReference("icon")
                val model = DialogDataModel("10","$key")

                myRef.child("$key").setValue("9")


            }
            mAlterDialog.findViewById<ImageView>(R.id.icon_8)!!.setOnClickListener {
                val key = intent.getStringExtra("key")

                val database = Firebase.database
                val myRef = database.getReference("icon")
                val model = DialogDataModel("10","$key")

                myRef.child("$key").setValue("8")


            }
            mAlterDialog.findViewById<ImageView>(R.id.icon_7)!!.setOnClickListener {
                val key = intent.getStringExtra("key")

                val database = Firebase.database
                val myRef = database.getReference("icon")
                val model = DialogDataModel("10","$key")

                myRef.child("$key").setValue("7")


            }
            mAlterDialog.findViewById<ImageView>(R.id.icon_6)!!.setOnClickListener {
                val key = intent.getStringExtra("key")

                val database = Firebase.database
                val myRef = database.getReference("icon")
                val model = DialogDataModel("10","$key")

                myRef.child("$key").setValue("6")


            }
            mAlterDialog.findViewById<ImageView>(R.id.icon_5)!!.setOnClickListener {
                val key = intent.getStringExtra("key")

                val database = Firebase.database
                val myRef = database.getReference("icon")
                val model = DialogDataModel("10","$key")

                myRef.child("$key").setValue("5")


            }
            mAlterDialog.findViewById<ImageView>(R.id.icon_4)!!.setOnClickListener {
                val key = intent.getStringExtra("key")

                val database = Firebase.database
                val myRef = database.getReference("icon")
                val model = DialogDataModel("10","$key")

                myRef.child("$key").setValue("4")


            }
            mAlterDialog.findViewById<ImageView>(R.id.icon_3)!!.setOnClickListener {
                val key = intent.getStringExtra("key")

                val database = Firebase.database
                val myRef = database.getReference("icon")
                val model = DialogDataModel("10","$key")

                myRef.child("$key").setValue("3")


            }
            mAlterDialog.findViewById<ImageView>(R.id.icon_2)!!.setOnClickListener {
                val key = intent.getStringExtra("key")

                val database = Firebase.database
                val myRef = database.getReference("icon")
                val model = DialogDataModel("10","$key")

                myRef.child("$key").setValue("2")


            }
            mAlterDialog.findViewById<ImageView>(R.id.icon_1)!!.setOnClickListener {
                val key = intent.getStringExtra("key")

                val database = Firebase.database
                val myRef = database.getReference("icon")
                val model = DialogDataModel("10","$key")

                myRef.child("$key").setValue("1")


            }

        }



        val logoutBtn: Button = findViewById(R.id.logoutBtn)
        logoutBtn.setOnClickListener {

            auth.signOut()

            Toast.makeText(this, "로그아웃", Toast.LENGTH_LONG).show()

            val intent = Intent(this, splash::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

        val k = FBRef.commentRef


        binding.profileImg.setOnClickListener {





            Log.d("할", "머니")
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)

            imageUPload(key.toString())
        }















    }
    private fun imageUPload(key: String){
        val storage = Firebase.storage


        val storageRef = storage.reference

// Create a reference to "mountains.jpg"
        val mountainsRef = storageRef.child(key +"profile.png")

        val imageView = binding.profileImg
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)//<- 경로설정
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK && requestCode == 100){
            Log.d("꾸꾸", "꺄꺄")
            val BEI1 = findViewById<ImageView>(R.id.profileImg)
            Glide.with(this).load(data?.data).into(BEI1)

        }

    }





    private fun getIconImageData(iconCode : String){
        val key = intent.getStringExtra("key")


        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child("9.jpg")

        // ImageView in your Activity
        val imageViewFromFB = binding.IconShowBtn

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if(task.isSuccessful) {

                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)

            } else {

                Toast.makeText(this, "이미지가..죽었어!", Toast.LENGTH_LONG).show()

            }
        })


    }


    private fun geticoncodeData(){

        var key = FBA.getUid().toString()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // Get Post object and use the values to update the UI
                 val post = dataSnapshot.getValue()

                for (dataModel in dataSnapshot.children) {

                    val item = dataModel.getValue().toString()
                    iconCodeList.add(item)

                    iconCode = iconCodeList.toString()



                    Log.d("캴라", iconCodeList.toString())

                }
                settingAdapter.notifyDataSetChanged()
            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "갸갸거겨", databaseError.toException())
            }
        }
        FBRef.iconRef.addValueEventListener(postListener)


    }

    private fun getImageData(key : String){

        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key +"profile.png")


        // ImageView in your Activity
        val imageViewFromFB = binding.profileImg

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if(task.isSuccessful) {

                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)

            } else {

                Toast.makeText(this, "tryyyy", Toast.LENGTH_LONG).show()
            }
        })


    }





}