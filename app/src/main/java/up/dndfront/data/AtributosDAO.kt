package up.dndfront.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import up.dndfront.Atributos

@Dao
interface AtributosDAO {

    @Insert
    suspend fun insert(atributos: Atributos)

    @Query("SELECT * FROM atributos")
    suspend fun getAllAtributos(): List<Atributos>
}