package org.wit.albumlist.models



interface AlbumlistStore {
    fun findAll(): List<AlbumlistModel>
    fun create(albumlist: AlbumlistModel)
    fun update(albumlist: AlbumlistModel)
}