package com.example.tictactoemvc.view

import android.view.View

interface GameView {
    fun getRootView(): View

    interface FieldSelectedListener {
        fun onFieldSelected()
    }

    interface ReadyClickedListener {
        fun onReadyClicked()
    }

    fun setFieldListener(listener: FieldSelectedListener)
    fun setReadyListener(listener: ReadyClickedListener)
}
