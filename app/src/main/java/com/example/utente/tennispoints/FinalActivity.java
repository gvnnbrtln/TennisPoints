package com.example.utente.tennispoints;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity{

    private String player_name_1, player_name_2;
    private int sets_1, sets_2;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        if (getIntent().getExtras() != null) {
            player_name_1 = getIntent().getStringExtra("player_name_1");
            player_name_2 = getIntent().getStringExtra("player_name_2");
            sets_1 = getIntent().getIntExtra("sets_1", 0);
            sets_2 = getIntent().getIntExtra("sets_2", 0);
        }

        TextView text_view_1 = findViewById(R.id.text_view_1);
        TextView text_view_2 = findViewById(R.id.text_view_2);
        Button button = findViewById(R.id.button);
        text_view_1.setText(player_name_1+" vs "+player_name_2);
        text_view_2.setText(sets_1+" vs "+sets_2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalActivity.this, PlayerActivity.class);
                intent.putExtra("player_number", 1);
                startActivity(intent);
            }
        });
    }
}
