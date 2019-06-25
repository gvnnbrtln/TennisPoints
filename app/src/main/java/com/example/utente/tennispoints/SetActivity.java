package com.example.utente.tennispoints;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SetActivity extends AppCompatActivity {

    private String player_name_1, player_name_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        if (getIntent().getExtras() != null) {
            player_name_1 = getIntent().getStringExtra("player_name_1");
            player_name_2 = getIntent().getStringExtra("player_name_2");
        }

        Button button1 = findViewById(R.id.button1);
        Button button3 = findViewById(R.id.button3);
        Button button5 = findViewById(R.id.button5);

        button1.setOnClickListener(SetOnClickListener);
        button3.setOnClickListener(SetOnClickListener);
        button5.setOnClickListener(SetOnClickListener);
    }

    private View.OnClickListener SetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int set_number = 1;

            switch (v.getId()) {
                case R.id.button1: {
                    set_number = 1;
                    break;
                }

                case R.id.button3: {
                    set_number = 2;
                    break;
                }

                case R.id.button5: {
                    set_number = 3;
                    break;
                }
            }

            Intent intent = new Intent(SetActivity.this, ServiceActivity.class);
            intent.putExtra("player_name_1", player_name_1);
            intent.putExtra("player_name_2", player_name_2);
            intent.putExtra("set_number", set_number);
            startActivity(intent);
        }
    };
}
