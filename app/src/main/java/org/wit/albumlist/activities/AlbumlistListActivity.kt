package org.wit.albumlist.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_albumlist_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.albumlist.R
import org.wit.albumlist.activities.AlbumlistActivity
import org.wit.albumlist.activities.AlbumlistAdapter
import org.wit.albumlist.activities.AlbumlistListener
import org.wit.albumlist.main.MainApp
import org.wit.albumlist.models.AlbumlistModel

class AlbumlistListActivity : AppCompatActivity(), AlbumlistListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albumlist_list)
        app = application as MainApp
        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
       loadAlbumlists()
    }
    private fun loadAlbumlists(){
        showAlbumlists(app.albumlists.findAll())
    }
    fun showAlbumlists (albumlists: List<AlbumlistModel>){
        recyclerView.adapter = AlbumlistAdapter(albumlists, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<AlbumlistActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAlbumlistClick(albumlist: AlbumlistModel) {
        startActivityForResult(intentFor<AlbumlistActivity>().putExtra("albumlist_edit", albumlist), 0)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}