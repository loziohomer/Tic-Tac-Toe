package com.example.tris.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tris.R;
import com.example.tris.callback.Updatable;
import com.example.tris.exception.ChoiseException;
import com.example.tris.exception.DrawException;
import com.example.tris.exception.VictoryExeption;
import com.example.tris.model.Match;
import com.example.tris.model.Player;
import com.example.tris.util.Values;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Updatable, View.OnClickListener {
    private Player player1;
    private Player player2;

    private TextView player1Name;
    private TextView player2Name;

    private TextView player1Points;
    private TextView player2Points;

    private Match match;
    private ImageView[] uiBoard;

    private Button newGame;
    private TextView round;
    private int game = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewBinding();

        Intent intent = getIntent();
        String player1Name = intent.getStringExtra(Values.PLAYER_1);
        String player2Name = intent.getStringExtra(Values.PLAYER_2);

        player1 = new Player(player1Name, R.color.red);
        player2 = new Player(player2Name, R.color.blue);

        init();
    }

    @Override
    public void onClick(View view) {
        try {
            if (view.getId() == uiBoard[0].getId()) {
                match.choose(0, this);
            } else if (view.getId() == uiBoard[1].getId()) {
                match.choose(1, this);
            } else if (view.getId() == uiBoard[2].getId()) {
                match.choose(2, this);
            } else if (view.getId() == uiBoard[3].getId()) {
                match.choose(3, this);
            } else if (view.getId() == uiBoard[4].getId()) {
                match.choose(4, this);
            } else if (view.getId() == uiBoard[5].getId()) {
                match.choose(5, this);
            } else if (view.getId() == uiBoard[6].getId()) {
                match.choose(6, this);
            } else if (view.getId() == uiBoard[7].getId()) {
                match.choose(7, this);
            } else if (view.getId() == uiBoard[8].getId()) {
                match.choose(8, this);
            }
        } catch (ChoiseException e) {
            Toast.makeText(this, R.string.already_chosen, Toast.LENGTH_SHORT).show();
        } catch (VictoryExeption | DrawException e) {
            new AlertDialog.Builder(this)
                    .setTitle("GAME OVER!")
                    .setMessage(e.getMessage())
                    .setCancelable(true)
                    .create().show();
            player1Points.setText(String.format(Locale.US,"%.1f", player1.getPoints()));
            player2Points.setText(String.format(Locale.US,"%.1f", player2.getPoints()));
            newGame.setVisibility(View.VISIBLE);
        }

        if (view.getId() == newGame.getId()) {
            new AlertDialog.Builder(this)
                    .setTitle("NEW GAME")
                    .setMessage("Do you want to start a new game?")
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startNewGame();
                }
            }).setCancelable(false).create().show();
        }
    }

    @Override
    public void update(int[] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i] != 0) {
                uiBoard[i].setColorFilter(getResources().getColor(board[i], getTheme()));
            }
        }

    }

    private void startNewGame() {
        newGame.setVisibility(View.GONE);
        game++;
        match = game % 2 == 0 ? new Match(player2, player1, round) : new Match(player1, player2, round);
        for (int i = 0; i < 9; i++) {
            uiBoard[i].setColorFilter(getResources().getColor(R.color.white, getTheme()));
        }
    }

    private void init() {
        player1Name.setTextColor(getResources().getColor(player1.getColor(), getTheme()));
        player2Name.setTextColor(getResources().getColor(player2.getColor(), getTheme()));
        player1Name.setText(player1.getName());
        player2Name.setText(player2.getName());
        player1Points.setText(String.format(Locale.US,"%.1f", player1.getPoints()));
        player2Points.setText(String.format(Locale.US,"%.1f", player2.getPoints()));
        match = new Match(player1, player2, round);

        for (ImageView v : uiBoard) {
            v.setOnClickListener(this);
        }

        newGame.setOnClickListener(this);
    }

    private void viewBinding() {
        player1Name = findViewById(R.id.player_1_name);
        player2Name = findViewById(R.id.player_2_name);
        player1Points = findViewById(R.id.player_1_points);
        player2Points = findViewById(R.id.player_2_points);

        ImageView box1 = findViewById(R.id.box1);
        ImageView box2 = findViewById(R.id.box2);
        ImageView box3 = findViewById(R.id.box3);
        ImageView box4 = findViewById(R.id.box4);
        ImageView box5 = findViewById(R.id.box5);
        ImageView box6 = findViewById(R.id.box6);
        ImageView box7 = findViewById(R.id.box7);
        ImageView box8 = findViewById(R.id.box8);
        ImageView box9 = findViewById(R.id.box9);

        round = findViewById(R.id.round);

        uiBoard = new ImageView[]{box1, box2, box3, box4, box5, box6, box7, box8, box9};

        newGame = findViewById(R.id.new_game);
    }

}