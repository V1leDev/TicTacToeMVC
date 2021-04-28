package com.example.tictactoemvc.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Score::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scoreDAO(): ScoreDAO
}