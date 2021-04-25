package com.example.tictactoemvc.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.tictactoemvc.R
import com.example.tictactoemvc.model.AppDatabase
import com.example.tictactoemvc.view.GameView
import com.example.tictactoemvc.view.GameViewImp

class MainActivity : AppCompatActivity(), GameView.FieldSelectedListener, GameView.ReadyClickedListener {

    private lateinit var usedGameView: GameView

    private lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usedGameView = GameViewImp(layoutInflater)
        setContentView(usedGameView.getRootView())
        usedGameView.setFieldListener(this)
        usedGameView.setReadyListener(this)
    }

    override fun onFieldSelected() {
        Log.d("debug", "field selected")
    }

    override fun onReadyClicked() {
        TODO("Not yet implemented")
    }
}