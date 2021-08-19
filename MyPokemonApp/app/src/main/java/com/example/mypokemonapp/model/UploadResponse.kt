package com.example.mypokemonapp.model

import android.service.voice.AlwaysOnHotwordDetector

data class UploadResponse(
val message: String,
val payload: Payload,
val Status: Int,

)

data class Payload(
    val downlowndUri: String,
    val fileid : String,
    val fileName: String,
    val fileType: String,
    val uploadStatus: Boolean
)
