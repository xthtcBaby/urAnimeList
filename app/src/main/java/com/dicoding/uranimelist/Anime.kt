package com.dicoding.uranimelist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    var title: String,
    var genre: String,
    var synopsis: String,
    var image: String
) : Parcelable
