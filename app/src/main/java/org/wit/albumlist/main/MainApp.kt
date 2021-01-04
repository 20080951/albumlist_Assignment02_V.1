package org.wit.albumlist.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.albumlist.models.AlbumlistJSONStore
import org.wit.albumlist.models.AlbumlistMemStore
import org.wit.albumlist.models.AlbumlistModel
import org.wit.albumlist.models.AlbumlistStore

class MainApp : Application(), AnkoLogger {

    lateinit var albumlists: AlbumlistStore


    override fun onCreate() {
        super.onCreate()
        albumlists = AlbumlistJSONStore(applicationContext)
        info("Albumlist started")

    }
}