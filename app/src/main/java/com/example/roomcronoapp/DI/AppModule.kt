package com.example.roomcronoapp.DI

import android.content.Context
import androidx.room.Room
import com.example.roomcronoapp.room.CronosDataBase
import com.example.roomcronoapp.room.CronosDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesCronosDao(cronosDataBase: CronosDataBase) : CronosDataBaseDao{
        return cronosDataBase.cronosDao()
    }

    @Singleton
    @Provides
    fun providesCronosDataBase(@ApplicationContext context: Context) : CronosDataBase {
        return Room.databaseBuilder(
            context,
            CronosDataBase::class.java, "crono_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}