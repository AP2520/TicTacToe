package com.example.ttt

import com.example.tictactoe.HardBoard

class EasyBoard {

    companion object {
        const val PLAYER = "X"
        const val COMPUTER = "O"
    }

    val board = Array(3) { arrayOfNulls<String>(3) }

    fun hasComputerWon() : Boolean {

        if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == HardBoard.COMPUTER ||
            board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == HardBoard.COMPUTER
        ) {
            return true
        }

        for(i in board.indices) {
            if(board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == HardBoard.COMPUTER ||
                board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == HardBoard.COMPUTER
            ) {
                return true
            }
        }
        return false
    }

    fun hasPlayerWon() : Boolean {

        if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == HardBoard.PLAYER ||
            board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == HardBoard.PLAYER
        ) {
            return true
        }

        for(i in board.indices) {
            if(board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == HardBoard.PLAYER ||
                board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == HardBoard.PLAYER
            ) {
                return true
            }
        }
        return false
    }

    val isGameOver : Boolean
        get() = hasComputerWon() || hasPlayerWon() || availableCells.isEmpty()

    val availableCells : List<Cell>
        get(){
            val cells = mutableListOf<Cell>()
            for(i in board.indices) {
                for(j in board.indices) {
                    if(board[i][j].isNullOrEmpty()) {
                        cells.add(Cell(i, j))
                    }
                }
            }
            return cells
        }

    // We pass the cell where we need to place our move and the Player who is making the move
    fun placeMove(cell: Cell, player: String) {
        board[cell.i][cell.j] = player
    }
}