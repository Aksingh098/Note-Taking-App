package com.example.notetakingapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1,
    /*
    Database schema version.
    Increase this when the database structure changes
    and a migration is required.
    */
)
abstract class NotesDataBase : RoomDatabase() {
    /*
    'abstract' because Room generates the actual implementation
    of this class during compilation.
    */

    abstract fun NotesDao(): NoteDao

    companion object {
        // Contains members that belong to the class itself,
        // rather than to individual NotesDataBase objects.
        @Volatile
        private var Instance: NotesDataBase? = null
        /*
        @Volatile ensures that changes to Instance are immediately
        visible to all threads.
        */

        fun getDatabase(context: Context): NotesDataBase {
            return Instance ?: synchronized(this)
            /*
            If Instance already exists:
            return the existing database.

            If Instance is null:
            enter synchronized block and create it.

            This prevents multiple database instances from
            being created when multiple threads call this function.
            */
            {
                Room.databaseBuilder(context, NotesDataBase::class.java, "notes_database")
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { Instance = it }
                /*
                Stores the newly created database instance
                in Instance so future calls reuse the same object.
                */
            }
        }
    }

}