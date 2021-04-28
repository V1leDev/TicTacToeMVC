package com.example.tictactoemvc.model

import androidx.room.Dao
import androidx.room.Query
import java.math.BigInteger

@Dao
interface ScoreDAO {
    /***
     * Gets all scores from database
     */
    @Query("SELECT * FROM Score")
    fun getAllScores(): List<Score>

    /***
     * Inserts new score into the database
     */
    @Query("INSERT INTO Score(username, points) VALUES(:username,:points)")
    fun insertScore(username: String?, points: Int)

    /***
     * Deletes all scores from database
     */
    @Query("DELETE FROM Score")
    fun deleteScores()

    /***
     * Updates existing score in database
     */
    @Query("UPDATE Score SET points=:points WHERE username=:username")
    fun updateScore(username: String?, points: Int)
}