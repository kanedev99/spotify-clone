package foit.thotrinh.spotifyclone.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import foit.thotrinh.spotifyclone.data.entities.Song
import foit.thotrinh.spotifyclone.util.Constants.SONG_COLLECTION
import kotlinx.coroutines.tasks.await

class MediaDatabase {
    private val fileStore = FirebaseFirestore.getInstance()
    private val songCollection = fileStore.collection(SONG_COLLECTION)

    suspend fun getAllSongs(): List<Song> {
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}