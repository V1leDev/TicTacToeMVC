package com.example.tictactoemvc.view

import android.view.View

interface GameView {
    fun getRootView(): View

    interface FieldSelectedListener {
        fun onFieldSelected()
    }

    interface ReadyClickedListener {
        fun onReadyClicked(s: Int)
    }

    fun setFieldListener(listener: FieldSelectedListener)
    fun setReadyListener(listener: ReadyClickedListener)
}
