package org.wit.albumlist.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.albumlist.helpers.*
import org.wit.albumlist.models.AlbumlistModel
import org.wit.albumlist.models.AlbumlistStore
import java.util.*

val JSON_FILE = "albumlists.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<AlbumlistModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class AlbumlistJSONStore : AlbumlistStore, AnkoLogger {

    val context: Context
    var albumlists = mutableListOf<AlbumlistModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<AlbumlistModel> {
        return albumlists
    }

    override fun create(albumlist: AlbumlistModel) {
        albumlist.id = generateRandomId()
        albumlists.add(albumlist)
        serialize()
    }


    override fun update(albumlist: AlbumlistModel) {
        // todo
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(albumlists, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        albumlists = Gson().fromJson(jsonString, listType)
    }
}