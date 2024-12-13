package com.example.roomcronoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomcronoapp.model.Cronos
import kotlinx.coroutines.flow.Flow

@Dao
interface CronosDataBaseDao {

    //CRUD

    @Query("SELECT * FROM cronos")
    fun getCronos() : Flow<List<Cronos>>

    @Query("SELECT * FROM cronos WHERE ID  = :Id")
    fun getCronosById(Id: Long) : Flow<Cronos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cronos: Cronos)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(cronos: Cronos)

    @Delete
    suspend fun delete(cronos: Cronos)

}