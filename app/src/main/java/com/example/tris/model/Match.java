package com.example.tris.model;

import android.widget.TextView;

import com.example.tris.callback.Updatable;
import com.example.tris.exception.ChoiseException;
import com.example.tris.exception.DrawException;
import com.example.tris.exception.VictoryExeption;

public class Match {
    private final int[] board;
    private final Player player1;
    private final Player player2;
    private int round = 0;
    private boolean matchEnded;
    private TextView roundTv;

    public Match(Player player1, Player player2, TextView roundTv) {
        this.player1 = player1;
        this.player2 = player2;
        this.roundTv = roundTv;
        board = new int[9];

        roundTv.setTextColor(roundTv.getContext().getResources().getColor(player1.getColor(), roundTv.getContext().getTheme()));
        roundTv.setText("Up to ".concat(player1.getName()));
    }

    public void choose(int square, Updatable callback) throws ChoiseException, VictoryExeption, DrawException {
        if (!matchEnded) {
            Player player = round % 2 == 0 ? player1 : player2;
            Player notPlayer = round % 2 != 0 ? player1 : player2;

            if (board[square] != 0) {
                throw new ChoiseException("Square already chosen");
            }

            board[square] = player.getColor();
            callback.update(board);
            round++;

            roundTv.setTextColor(roundTv.getContext().getResources().getColor(notPlayer.getColor(), roundTv.getContext().getTheme()));
            roundTv.setText("Up to ".concat(notPlayer.getName()));


            if (checkWin()) {
                player.winner();
                matchEnded = true;
                throw new VictoryExeption(player.getName() + " wins!");
            }

            if (round == 9) {
                matchEnded = true;
                player1.drawer();
                player2.drawer();
                throw new DrawException("Match draw");
            }


        }

    }

    private boolean checkWin() {
        return board[0] != 0 && board[0] == board[1] && board[1] == board[2] ||
                board[3] != 0 && board[3] == board[4] && board[4] == board[5] ||
                board[6] != 0 && board[6] == board[7] && board[7] == board[8] ||
                board[0] != 0 && board[0] == board[3] && board[3] == board[6] ||
                board[1] != 0 && board[1] == board[4] && board[4] == board[7] ||
                board[2] != 0 && board[2] == board[5] && board[5] == board[8] ||
                board[0] != 0 && board[0] == board[4] && board[4] == board[8] ||
                board[2] != 0 && board[2] == board[4] && board[4] == board[6];
    }
}
