package com.example.ttt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_easy.*
import kotlin.random.Random

class EasyActivity : AppCompatActivity() {

    private val boardCells = Array(3){ arrayOfNulls<ImageView>(3) }

    var board = EasyBoard()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy)
        loadBoard()

        button_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button_restart.setOnClickListener {

            // Create a new board on restarting
            board = EasyBoard()
            text_view_result.text = ""

            // mapBoardToUI to empty the board
            mapBoardToUI()
        }
    }

    fun mapBoardToUI() {
        button_back.isClickable = false
        for(i in board.board.indices) {
            for(j in board.board.indices) {
                when(board.board[i][j]) {
                    EasyBoard.PLAYER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.cross)
                        boardCells[i][j]?.isEnabled = false
                    }
                    EasyBoard.COMPUTER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled = false
                    }
                    else -> {
                        boardCells[i][j]?.setImageResource(0)
                        boardCells[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }

    private fun loadBoard() {
        // Initialize each ImageView and set parameters to each ImageView, set Background color
        for(i in boardCells.indices) {
            for(j in boardCells.indices) {
                boardCells[i][j] = ImageView(this)
                boardCells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 225
                    height = 225
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 5
                    rightMargin = 5
                }
                boardCells[i][j]?.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

                // Adding the ClickListener to each cell
                boardCells[i][j]?.setOnClickListener(cellClickListener(i, j))

                // Adding the ImageView to the GridLayout
                layout_board.addView(this.boardCells[i][j])
            }
        }
    }

    inner class cellClickListener(
        private val i: Int, private val j: Int) : View.OnClickListener {

        override fun onClick(p0: View?) {
            if(!board.isGameOver) {
                val cell = Cell(i, j)
                board.placeMove(cell, EasyBoard.PLAYER)

                if(board.availableCells.isNotEmpty()) {
                    val cCell = board.availableCells[Random.nextInt(0, board.availableCells.size)]
                    board.placeMove(cCell, EasyBoard.COMPUTER)
                }
                mapBoardToUI()
            }

            when{
                board.hasComputerWon() -> {
                    text_view_result.text = "AI Won"
                    button_back.isClickable = true
                }
                board.hasPlayerWon() -> {
                    text_view_result.text = "Player Won"
                    button_back.isClickable = true
                }
                board.isGameOver -> {
                    text_view_result.text = "Game Tied"
                    button_back.isClickable = true
                }
            }
        }
    }
}

