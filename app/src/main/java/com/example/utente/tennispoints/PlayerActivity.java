package com.example.utente.tennispoints;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;


public class PlayerActivity extends AppCompatActivity{

    private int player_number;
    private String player_name_1, player_name_2;
    private EditText edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        if (getIntent().getExtras() != null) {
            player_number = getIntent().getIntExtra("player_number", 1);
            player_name_1 = getIntent().getStringExtra("player_name_1");
        }

        edit_text = findViewById(R.id.edit_text);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player_number == 1) {
                    player_name_1 = edit_text.getText().toString();
                    player_number++;
                    Intent intent = new Intent(PlayerActivity.this, PlayerActivity.class);
                    intent.putExtra("player_number", player_number);
                    intent.putExtra("player_name_1", player_name_1);
                    intent.putExtra("player_name_2", player_name_2);
                    startActivity(intent);
                }
                else if (player_number == 2) {
                    player_name_2 = edit_text.getText().toString();
                    Intent intent = new Intent(PlayerActivity.this, SetActivity.class);
                    intent.putExtra("player_name_1", player_name_1);
                    intent.putExtra("player_name_2", player_name_2);
                    startActivity(intent);
                }
            }
        });
    }
}
