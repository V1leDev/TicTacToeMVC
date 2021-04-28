package com.example.tictactoemvc.view

import android.view.View

interface GameView {
    fun getRootView(): View
    var fieldList: ArrayList<Int>

    interface FieldSelectedListener {
        fun onFieldSelected(s: Int)
    }

    interface ReadyClickedListener {
        fun onReadyClicked(s: Int)
    }

    fun setFieldListener(listener: FieldSelectedListener)
    fun setReadyListener(listener: ReadyClickedListener)
}
