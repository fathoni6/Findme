package com.findme.tugaspam.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    var nim: String = "",
    var nama: String = "",
    var jurusan: String = "",
    var nohp: String = ""
) : Parcelable