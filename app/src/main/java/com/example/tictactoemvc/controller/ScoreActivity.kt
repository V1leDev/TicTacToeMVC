package com.example.tictactoemvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.room.Room
import com.example.tictactoemvc.model.AppDatabase
import com.example.tictactoemvc.view.ScoreView
import com.example.tictactoemvc.view.ScoreViewImp
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {
    private lateinit var usedScoreView : ScoreView
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usedScoreView = ScoreViewImp(layoutInflater)
        setContentView(usedScoreView.getRootView())

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "note").fallbackToDestructiveMigration().allowMainThreadQueries().build()

        loadScores()
    }

    private fun loadScores(){
        var scores  = db.scoreDAO().getAllScores()

        for (score in scores){
            val dataView = TextView(this)
            dataView.text = score.username + ": " + score.points
            dataView.textSize = 25F
            scrollView.addView(dataView)
        }
    }
}