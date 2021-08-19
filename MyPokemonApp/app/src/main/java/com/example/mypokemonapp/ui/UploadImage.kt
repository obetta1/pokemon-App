package com.example.mypokemonapp.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mypokemonapp.R
import com.example.mypokemonapp.UploadRequestBody
import com.example.mypokemonapp.getFileNmae
import com.example.mypokemonapp.model.UploadResponse
import com.example.mypokemonapp.network.UploadApi
import com.example.mypokemonapp.snackbar
import kotlinx.android.synthetic.main.activity_upload_image.*
import kotlinx.android.synthetic.main.fragment_recycler_list.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class UploadImage : AppCompatActivity(), UploadRequestBody.UploadCallBack {
    private var selectedImage: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)

        image_view.setOnClickListener {
            openImageChooser()
        }

        button_upload.setOnClickListener {
            uploadImage()
        }
    }
// a function that opens an image folder to select the image to upload
    private fun openImageChooser(){
        Intent(Intent.ACTION_PICK).also{
            it.type = "image/*"
            val minmeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, minmeTypes)
            startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_IMAGE_PICKER->{
                    selectedImage = data?.data
                    image_view.setImageURI(selectedImage)
                }
            }

        }

    }

    fun uploadImage(){
        if(selectedImage==null){
            layout_root.snackbar("please select an image to upload")
            return
        }
val percelFileDescriptor = contentResolver
    .openAssetFileDescriptor(selectedImage!!, "r", null)?:return
        val file = File(cacheDir, contentResolver.getFileNmae(selectedImage!!))

        // create file input and output streem
        val inputStream = FileInputStream(percelFileDescriptor.fileDescriptor)
        val  outPutStream = FileOutputStream(file)
        inputStream.copyTo(outPutStream)

        progress_bar.progress = 0
        val body = UploadRequestBody(file, "image" ,this)

        UploadApi().uploadImage(
            MultipartBody.Part.createFormData("file", file.name, body),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "json")
        ).enqueue(object : Callback<UploadResponse> {
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                layout_root.snackbar(t.message!!)
                progress_bar.progress = 0
            }

            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                response.body()?.let {
                    layout_root.snackbar(it.message)
                    progress_bar.progress = 100
                }
            }
        })


    }
    companion object{
        private const val REQUEST_CODE_IMAGE_PICKER = 100
    }

    override fun onProgressUpdate(percentage: Int) {
                progress_bar.progress = percentage
    }
}