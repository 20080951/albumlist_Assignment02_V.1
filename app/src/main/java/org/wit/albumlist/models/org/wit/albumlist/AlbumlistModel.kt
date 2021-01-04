package org.wit.albumlist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumlistModel(var id: Long = 0,
                          var title: String = "",
                          var description: String = "",
                          var genre: String = "",
                          var artist: String = "" ) : Parcelable
