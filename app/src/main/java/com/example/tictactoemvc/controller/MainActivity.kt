package com.example.tictactoemvc.controller

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.tictactoemvc.model.AppDatabase
import com.example.tictactoemvc.model.GameState
import com.example.tictactoemvc.view.GameView
import com.example.tictactoemvc.view.GameViewImp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GameView.FieldSelectedListener,
    GameView.ReadyClickedListener {

    private lateinit var usedGameView: GameView

    private lateinit var db: AppDatabase

    private var gs = GameState()

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

    override fun onReadyClicked(s: Int) {
        var readyButton = findViewById<Button>(s)

        readyButton.setBackgroundColor(Color.GREEN)
        readyButton.isClickable = false

        // update GameState ready button states
        if (readyButton == ready1) {
            gs.ready1 = true
        } else if (readyButton == ready2) {
            gs.ready2 = true
        }

        if (gs.ready1 && gs.ready2) {
            startGame()
        }

    }

    private fun startGame() {
        Log.d("debug", "Game started")

        // TODO: Check if username is empty

        // get usernames
        gs.username1 = editTextPlayer1.text.toString()
        gs.username2 = editTextPlayer2.text.toString()

        Log.d("debug", gs.username1)
        Log.d("debug", gs.username2)

        // make username input fields unmodifiable
        editTextPlayer1.isEnabled = false
        editTextPlayer2.isEnabled = false
    }
}