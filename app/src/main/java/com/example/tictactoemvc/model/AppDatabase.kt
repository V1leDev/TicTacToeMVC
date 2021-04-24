package com.example.tictactoemvc.model

import androidx.room.Database

@Database(entities = [Score::class], version = 2)
abstract class AppDatabase {
    abstract fun scoreDAO(): ScoreDAO;
}