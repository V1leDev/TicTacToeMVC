package com.example.tictactoemvc.controller

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.tictactoemvc.R
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
        if (gs.gameStarted) {
            val playingField = findViewById<Button>(s)
            playingField.isEnabled = false
            drawSymbols(playingField)
            val winner_symbol = checkWin()
            if (winner_symbol != "") {
                storeWinner(winner_symbol)
                // reset game after win
                reset()
            }
            checkTie()
        } else {
            Toast.makeText(
                this,
                "Game has not started yet. Please input usernames and click ready",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    private fun storeWinner(winner_symbol : String){
        Toast.makeText(
            this,
            winner_symbol,
            Toast.LENGTH_SHORT
        )
            .show()
    }

    private fun checkTie() {
        for (field in usedGameView.fieldList) {
            if (findViewById<Button>(field).text == "") {
                gs.tie = false
                break
            } else {
                gs.tie = true
            }
        }
        if (gs.tie) {
            // reset game after tie
            reset()
        }
    }

    private fun reset() {
        if (gs.tie) {
            Toast.makeText(
                this,
                "Tie!",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        resetGameState()
        resetGameView()
    }

    private fun resetGameView() {
        editTextPlayer1.isEnabled = true
        editTextPlayer1.setText("")
        editTextPlayer2.isEnabled = true
        editTextPlayer2.setText("")
        ready1.setBackgroundColor(Color.RED)
        ready1.isClickable = true
        ready2.setBackgroundColor(Color.RED)
        ready2.isClickable = true
        for (field in usedGameView.fieldList) {
            findViewById<Button>(field).isEnabled = true
            findViewById<Button>(field).text = ""
        }
    }

    private fun resetGameState() {
        gs.ready1 = false
        gs.ready2 = false
        gs.username1 = ""
        gs.username2 = ""
        gs.turn = ""
        gs.gameStarted = false
        gs.tie = false
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
            Toast.makeText(this, "$player has empty username", Toast.LENGTH_SHORT).show()
            Log.d("debug", "$player has empty username")
            return false
        }
        if (player == "Player 1" && username == editTextPlayer2.text.toString()) {
            Toast.makeText(this, "$player has same username as Player 2", Toast.LENGTH_SHORT)
                .show()
            Log.d("debug", "$player has same username as Player 2")
            return false
        }
        if (player == "Player 2" && username == editTextPlayer1.text.toString()) {
            Toast.makeText(this, "$player has same username as Player 1", Toast.LENGTH_SHORT)
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
                Toast.LENGTH_SHORT
            ).show()
        } else if (gs.turn == "player2") {
            Toast.makeText(
                this,
                "GAME STARTED! " + gs.username2 + " has the first turn",
                Toast.LENGTH_SHORT
            ).show()
        }

        gs.gameStarted = true
    }

    private fun drawSymbols(playingField: Button) {
        if (gs.turn == "player1") {
            playingField.text = "X"
            gs.turn = "player2"
        } else if (gs.turn == "player2") {
            playingField.text = "O"
            gs.turn = "player1"
        }
    }

    private fun checkWin(): String {
        if (buttonField1.text.toString() == buttonField4.text.toString() && buttonField4.text.toString() == buttonField7.text.toString()) {
            return buttonField1.text.toString()
        }
        if (buttonField2.text.toString() == buttonField5.text.toString() && buttonField5.text.toString() == buttonField8.text.toString()) {
            return buttonField2.text.toString()
        }
        if (buttonField3.text.toString() == buttonField6.text.toString() && buttonField6.text.toString() == buttonField9.text.toString()) {
            return buttonField3.text.toString()
        }
        if (buttonField1.text.toString() == buttonField2.text.toString() && buttonField2.text.toString() == buttonField3.text.toString()) {
            return buttonField1.text.toString()
        }
        if (buttonField4.text.toString() == buttonField5.text.toString() && buttonField5.text.toString() == buttonField6.text.toString()) {
            return buttonField4.text.toString()
        }
        if (buttonField7.text.toString() == buttonField8.text.toString() && buttonField8.text.toString() == buttonField9.text.toString()) {
            return buttonField7.text.toString()
        }
        if (buttonField1.text.toString() == buttonField5.text.toString() && buttonField5.text.toString() == buttonField9.text.toString()) {
            return buttonField1.text.toString()
        }
        if (buttonField7.text.toString() == buttonField5.text.toString() && buttonField5.text.toString() == buttonField3.text.toString()) {
            return buttonField7.text.toString()
        }
        return ""
    }
}