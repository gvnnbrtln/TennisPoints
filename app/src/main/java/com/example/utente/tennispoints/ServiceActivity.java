package com.example.utente.tennispoints;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ServiceActivity extends AppCompatActivity {

    private String player_name_1, player_name_2;
    private int set_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        if (getIntent().getExtras() != null) {
            player_name_1 = getIntent().getStringExtra("player_name_1");
            player_name_2 = getIntent().getStringExtra("player_name_2");
            set_number = getIntent().getIntExtra("set_number", 1);
        }

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setText("Service "+player_name_1);
        button2.setText("Service "+player_name_2);

        button1.setOnClickListener(ServiceOnClickListener);
        button2.setOnClickListener(ServiceOnClickListener);
    }

    private View.OnClickListener ServiceOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            boolean service = true;

            switch (v.getId()) {
                case R.id.button1: {
                    service = true;
                    break;
                }

                case R.id.button2: {
                    service = false;
                    break;
                }
            }

            Intent intent = new Intent(ServiceActivity.this, MatchActivity.class);
            intent.putExtra("player_name_1", player_name_1);
            intent.putExtra("player_name_2", player_name_2);
            intent.putExtra("set_number", set_number);
            intent.putExtra("service", service);
            startActivity(intent);
        }
    };
}
