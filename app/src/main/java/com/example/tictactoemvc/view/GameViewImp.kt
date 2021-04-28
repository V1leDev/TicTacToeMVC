package com.example.tictactoemvc.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.example.tictactoemvc.R

class GameViewImp(layoutInflater: LayoutInflater) : GameView, View.OnClickListener {
    private var rootView = layoutInflater.inflate(R.layout.activity_main, null)
    var fieldSelectedListener: GameView.FieldSelectedListener? = null
    var readyClickedListener: GameView.ReadyClickedListener? = null
    override var fieldList: ArrayList<Int> = arrayListOf<Int>(
        R.id.buttonField1,
        R.id.buttonField2,
        R.id.buttonField3,
        R.id.buttonField4,
        R.id.buttonField5,
        R.id.buttonField6,
        R.id.buttonField7,
        R.id.buttonField8,
        R.id.buttonField9
    )

    init {
        rootView.findViewById<Button>(R.id.ready1).setOnClickListener {
            readyClickedListener?.onReadyClicked(R.id.ready1)
        }

        rootView.findViewById<Button>(R.id.ready2).setOnClickListener {
            readyClickedListener?.onReadyClicked(R.id.ready2)
        }

        rootView.findViewById<Button>(R.id.buttonField1).setOnClickListener {
            Log.d("debug", "field1 selected")
            fieldSelectedListener?.onFieldSelected(R.id.buttonField1)
        }
        rootView.findViewById<Button>(R.id.buttonField2).setOnClickListener {
            Log.d("debug", "field2 selected")
            fieldSelectedListener?.onFieldSelected(R.id.buttonField2)
        }
        rootView.findViewById<Button>(R.id.buttonField3).setOnClickListener {
            Log.d("debug", "field3 selected")
            fieldSelectedListener?.onFieldSelected(R.id.buttonField3)
        }
        rootView.findViewById<Button>(R.id.buttonField4).setOnClickListener {
            Log.d("debug", "field4 selected")
            fieldSelectedListener?.onFieldSelected(R.id.buttonField4)
        }
        rootView.findViewById<Button>(R.id.buttonField5).setOnClickListener {
            Log.d("debug", "field5 selected")
            fieldSelectedListener?.onFieldSelected(R.id.buttonField5)
        }
        rootView.findViewById<Button>(R.id.buttonField6).setOnClickListener {
            Log.d("debug", "field6 selected")
            fieldSelectedListener?.onFieldSelected(R.id.buttonField6)
        }
        rootView.findViewById<Button>(R.id.buttonField7).setOnClickListener {
            Log.d("debug", "field7 selected")
            fieldSelectedListener?.onFieldSelected(R.id.buttonField7)
        }
        rootView.findViewById<Button>(R.id.buttonField8).setOnClickListener {
            Log.d("debug", "field8 selected")
            fieldSelectedListener?.onFieldSelected(R.id.buttonField8)
        }
        rootView.findViewById<Button>(R.id.buttonField9).setOnClickListener {
            Log.d("debug", "field9 selected")
            fieldSelectedListener?.onFieldSelected(R.id.buttonField9)
        }
    }


    override fun getRootView(): View = rootView

    override fun setFieldListener(listener: GameView.FieldSelectedListener) {
        fieldSelectedListener = listener
    }

    override fun setReadyListener(listener: GameView.ReadyClickedListener) {
        readyClickedListener = listener
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}