package org.wit.albumlist.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.albumlist.models.AlbumlistStore

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class AlbumlistMemStore : AlbumlistStore, AnkoLogger {

    val albumlists = ArrayList<AlbumlistModel>()

    override fun findAll(): List<AlbumlistModel> {
        return albumlists
    }

    override fun create(albumlist: AlbumlistModel) {
        albumlist.id = getId()
        albumlists.add(albumlist)
        logAll()
    }

    override fun update(albumlist: AlbumlistModel) {
        var foundAlbumlist: AlbumlistModel? = albumlists.find { p -> p.id == albumlist.id }
        if (foundAlbumlist != null) {
            foundAlbumlist.title = albumlist.title
            foundAlbumlist.description = albumlist.description
            foundAlbumlist.genre = albumlist.genre
            foundAlbumlist.artist = albumlist.artist
            logAll()
        }
    }

    fun logAll() {
        albumlists.forEach { info("${it}") }
    }
}