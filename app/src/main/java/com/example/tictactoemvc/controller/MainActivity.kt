package com.example.tictactoemvc.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoemvc.R
import com.example.tictactoemvc.model.AppDatabase
import com.example.tictactoemvc.view.GameView
import com.example.tictactoemvc.view.GameViewImp

class MainActivity : AppCompatActivity() {

    private lateinit var usedGameView: GameView

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

    }
}