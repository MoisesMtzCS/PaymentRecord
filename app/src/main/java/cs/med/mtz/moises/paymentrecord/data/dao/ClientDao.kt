package cs.med.mtz.moises.paymentrecord.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cs.med.mtz.moises.paymentrecord.data.dto.ClientDTO

@Dao
interface ClientDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clientDTO: ClientDTO): Long

    @Query("SELECT * FROM client_table WHERE id_customer = :id")
    suspend fun getById(id: Int): ClientDTO
}