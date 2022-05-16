package com.example.tris.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tris.R;
import com.example.tris.util.Values;

public class StarterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText player1Et;
    private EditText player2Et;
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter_activity);
        viewBinding();

        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == startBtn.getId()) {
            String player1 = player1Et.getText().toString().trim();
            String player2 = player2Et.getText().toString().trim();

            if (!player1.isEmpty() && !player2.isEmpty()) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(Values.PLAYER_1, player1);
                intent.putExtra(Values.PLAYER_2, player2);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Insert two names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void viewBinding() {
        player1Et = findViewById(R.id.player_1_name);
        player2Et = findViewById(R.id.player_2_name);
        startBtn = findViewById(R.id.start_btn);
    }
}