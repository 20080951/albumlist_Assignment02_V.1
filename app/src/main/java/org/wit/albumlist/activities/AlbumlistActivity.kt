package org.wit.albumlist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_albumlist.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.albumlist.R
import org.wit.albumlist.models.AlbumlistModel




class AlbumlistActivity : AppCompatActivity(), AnkoLogger {

    var albumlist = AlbumlistModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albumlist)

        btnAdd.setOnClickListener() {
            albumlist.title = albumlistTitle.text.toString()
            if (albumlist.title.isNotEmpty()) {
                info("add Button Pressed: $albumlist")
            }
            else {
                toast ("Please Enter a title")
            }
        }
    }
}