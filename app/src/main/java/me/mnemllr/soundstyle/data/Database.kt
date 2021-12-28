package me.mnemllr.soundstyle.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import me.mnemllr.soundstyle.util.Logger
import me.mnemllr.soundstyle.util.className
import java.lang.Exception

class Database {

    companion object {
        const val SONG_LIST_ID = "SONG_LIST"

        private val log = Logger(this::class.className)
    }

    private val firestore = FirebaseFirestore.getInstance()
    private val songList = firestore.collection(SONG_LIST_ID)

    suspend fun getFullSongList(): List<Song> {
        log.info("getFullSongList")
        return try {
           songList.get().await().toObjects(Song::class.java)
        } catch (e: Exception) {
            log.fatal("getFullSongList", e)
            emptyList()
        }
    }
}