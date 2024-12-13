package com.example.roomcronoapp.repository

import com.example.roomcronoapp.model.Cronos
import com.example.roomcronoapp.room.CronosDataBaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CronosRepository @Inject constructor(private val cronosDataBaseDao : CronosDataBaseDao) {

    suspend fun addCronos(crono : Cronos) = cronosDataBaseDao.insert(crono)
    suspend fun updateCronos(crono : Cronos) = cronosDataBaseDao.update(crono)
    suspend fun deleteCronos(crono : Cronos) = cronosDataBaseDao.delete(crono)
    fun getAllCronos() : Flow<List<Cronos>> = cronosDataBaseDao.getCronos().flowOn(Dispatchers.IO).conflate()
    fun getCronoById(id:Long) : Flow<Cronos> = cronosDataBaseDao.getCronosById(id).flowOn(Dispatchers.IO).conflate()

}