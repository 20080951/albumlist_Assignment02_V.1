package org.wit.albumlist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_albumlist.*
import kotlinx.android.synthetic.main.activity_albumlist_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.albumlist.R
import org.wit.albumlist.helpers.readImage
import org.wit.albumlist.helpers.readImageFromPath
import org.wit.albumlist.helpers.showImagePicker
import org.wit.albumlist.main.MainApp
import org.wit.albumlist.models.AlbumlistModel

class AlbumlistActivity : AppCompatActivity(), AnkoLogger {

    var albumlist = AlbumlistModel()
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albumlist)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        info("Albumlist App started..")

        app = application as MainApp

        var edit = false

        if (intent.hasExtra("albumlist_edit")) {
            edit = true
            albumlist = intent.extras?.getParcelable<AlbumlistModel>("albumlist_edit")!!
            albumlistTitle.setText(albumlist.title)
            description.setText(albumlist.description)
            genre.setText(albumlist.genre)
            artist.setText(albumlist.artist)
            albumlistImage.setImageBitmap(readImageFromPath(this, albumlist.image))
            btnAdd.setText(R.string.save_albumlist)
        }

        btnAdd.setOnClickListener() {
            albumlist.title = albumlistTitle.text.toString()
            albumlist.description = description.text.toString()
            albumlist.genre = genre.text.toString()
            albumlist.artist = artist.text.toString()
            if (albumlist.title.isEmpty()) {
                toast(R.string.enter_albumlist_title)
            } else {
                if (edit) {
                    app.albumlists.update(albumlist.copy())
                } else {
                    app.albumlists.create(albumlist.copy())
                }
            }
            info("add Button Pressed: $albumlistTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }
        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_albumlist, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    albumlist.image = data.getData().toString()
                    albumlistImage.setImageBitmap(readImage(this,resultCode,data))
                }
            }
        }

    }
}