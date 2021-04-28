package com.example.tictactoemvc.view

import android.view.LayoutInflater
import android.view.View
import com.example.tictactoemvc.R

class ScoreViewImp(layoutInflater: LayoutInflater) : ScoreView {

    private var rootView = layoutInflater.inflate(R.layout.activity_score, null)

    override fun getRootView(): View {
        return rootView
    }
}