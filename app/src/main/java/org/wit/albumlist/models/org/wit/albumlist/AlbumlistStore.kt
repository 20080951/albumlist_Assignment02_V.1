package org.wit.albumlist.models

import org.wit.albumlist.models.AlbumlistModel

interface AlbumlistStore {
    fun findAll(): List<AlbumlistModel>
    fun create(albumlist: AlbumlistModel)
    fun update(albumlist: AlbumlistModel)
}