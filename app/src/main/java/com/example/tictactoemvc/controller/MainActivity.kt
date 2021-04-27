package com.example.tictactoemvc.controller

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
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

    override fun onFieldSelected(s: Int) {
        val playingField = findViewById<Button>(s)
        if (gs.turn == "player1") {
            playingField.text = "X"
            gs.turn = "player2"
        } else if (gs.turn == "player2") {
            playingField.text = "O"
            gs.turn = "player1"
        }
    }

    override fun onReadyClicked(s: Int) {
        val readyButton = findViewById<Button>(s)
        var usernameOkay = false


        // update GameState ready button states
        if (readyButton == ready1 && checkUsernameValidity(
                editTextPlayer1.text.toString(),
                "Player 1"
            )
        ) {
            gs.ready1 = true
            usernameOkay = true
            editTextPlayer1.isEnabled = false
            gs.username1 = editTextPlayer1.text.toString()
        } else if (readyButton == ready2 && checkUsernameValidity(
                editTextPlayer2.text.toString(),
                "Player 2"
            )
        ) {
            gs.ready2 = true
            usernameOkay = true
            editTextPlayer2.isEnabled = false
            gs.username2 = editTextPlayer2.text.toString()
        }
        if (usernameOkay) {
            readyButton.setBackgroundColor(Color.GREEN)
            readyButton.isClickable = false
        } else {
            return
        }


        if (gs.ready1 && gs.ready2) {
            startGame()
        }

    }

    private fun checkUsernameValidity(username: String, player: String): Boolean {
        if (username == "") {
            Toast.makeText(this, "$player has empty username", Toast.LENGTH_LONG).show()
            Log.d("debug", "$player has empty username")
            return false
        }
        if (player == "Player 1" && username == editTextPlayer2.text.toString()) {
            Toast.makeText(this, "$player has same username as Player 2", Toast.LENGTH_LONG)
                .show()
            Log.d("debug", "$player has same username as Player 2")
            return false
        }
        if (player == "Player 2" && username == editTextPlayer1.text.toString()) {
            Toast.makeText(this, "$player has same username as Player 1", Toast.LENGTH_LONG)
                .show()
            Log.d("debug", "$player has same username as Player 1")
            return false
        }
        return true

    }

    private fun startGame() {
        Log.d("debug", "Game started")
        Log.d("debug", "Player 1: " + gs.username1)
        Log.d("debug", "Player 2: " + gs.username2)

        // randomly select who starts the game
        val random = (1..2).random()
        Log.d("debug", random.toString())

        if (random == 1) {
            gs.turn = "player1"
        } else if (random == 2) {
            gs.turn = "player2"
        }
        if (gs.turn == "player1") {
            Toast.makeText(
                this,
                "GAME STARTED! " + gs.username1 + " has the first turn",
                Toast.LENGTH_LONG
            ).show()
        } else if (gs.turn == "player2") {
            Toast.makeText(
                this,
                "GAME STARTED! " + gs.username2 + " has the first turn",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}