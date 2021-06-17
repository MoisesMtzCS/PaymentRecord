package cs.med.mtz.moises.paymentrecord.data.dao

import androidx.room.*
import cs.med.mtz.moises.paymentrecord.data.dto.ClientDTO

@Dao
interface ClientDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clientDTO: ClientDTO): Long

    @Query("SELECT * FROM client_table WHERE id_customer = :id")
    suspend fun getById(id: Int): ClientDTO

    @Query("SELECT * FROM client_table")
    suspend fun getClients(): List<ClientDTO>


    @Delete
    suspend fun deleteClient(clientDTO: ClientDTO)

}