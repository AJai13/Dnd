package up.dndfront.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Update
import up.dndback.Personagem

@Dao
interface PersonagemDAO {
    @Insert
    suspend fun insert(personagem: Personagem)

    @Delete
    suspend fun delete(personagem: Personagem)

    @Update
    suspend fun update(personagem: Personagem)

    @Query("SELECT * FROM personagem")
    fun getAllPersonagem(): List<Personagem>
}