package up.dndfront.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import up.dndback.Personagem

@Database(entities = [Personagem::class], version = 1, exportSchema = false)
abstract class PersonagemDB : RoomDatabase() {

    abstract fun personagemDAO(): PersonagemDAO

    companion object {

        @Volatile
        private var INSTANCIA: PersonagemDB? = null

        fun getDatabase(context: Context): PersonagemDB {
            return INSTANCIA ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    PersonagemDB::class.java,
                    "personagemDB"
                ).allowMainThreadQueries().build()
                INSTANCIA = instancia
                return instancia
            }
        }
    }
}