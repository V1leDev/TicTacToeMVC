package com.example.tictactoemvc.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigInteger

@Entity
data class Score(
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val username: String?,
    val points: BigInteger?
)