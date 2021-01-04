package org.wit.albumlist.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.albumlist.models.AlbumlistMemStore
import org.wit.albumlist.models.AlbumlistModel

class MainApp : Application(), AnkoLogger {


    val albumlists = AlbumlistMemStore()
    override fun onCreate() {
        super.onCreate()
        info("Albumlist started")

    }
}