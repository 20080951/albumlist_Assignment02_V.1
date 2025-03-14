package org.wit.albumlist.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_albumlist.view.*
import kotlinx.android.synthetic.main.card_albumlist.view.*
import kotlinx.android.synthetic.main.card_albumlist.view.albumlistTitle
import kotlinx.android.synthetic.main.card_albumlist.view.description
import kotlinx.android.synthetic.main.card_albumlist.view.genre
import kotlinx.android.synthetic.main.card_albumlist.view.artist
import org.wit.albumlist.R
import org.wit.albumlist.helpers.readImageFromPath
import org.wit.albumlist.models.AlbumlistModel

interface AlbumlistListener {
    fun onAlbumlistClick(albumlist: AlbumlistModel)
}

class AlbumlistAdapter constructor(
    private var albumlists: List<AlbumlistModel>,
    private val listener: AlbumlistListener
) : RecyclerView.Adapter<AlbumlistAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_albumlist,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val albumlist = albumlists[holder.adapterPosition]
        holder.bind(albumlist, listener)
    }

    override fun getItemCount(): Int = albumlists.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(albumlist: AlbumlistModel, listener: AlbumlistListener) {
            itemView.albumlistTitle.text = albumlist.title
            itemView.description.text = albumlist.description
            itemView.artist.text = albumlist.genre
            itemView.artist.text = albumlist.artist
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, albumlist.image))
            itemView.setOnClickListener { listener.onAlbumlistClick(albumlist) }



            itemView.setOnClickListener { listener.onAlbumlistClick(albumlist) }
        }
    }
}