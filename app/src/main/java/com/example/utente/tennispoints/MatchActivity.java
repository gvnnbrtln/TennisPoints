package com.example.utente.tennispoints;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MatchActivity extends AppCompatActivity {

    private String player_name_1, player_name_2;
    private int set_number;
    private boolean service;
    private boolean service_tie;
    private String points_1 = "0", points_2 = "0";
    private int points_tie_1 = 0, points_tie_2 = 0;
    private int games_1 = 0, games_2 = 0;
    private int sets_1 = 0, sets_2 = 0;
    private boolean tie_break = false;
    private TextView player_1, player_2;
    private TextView service_1, service_2;
    private TextView tv_sets_1, tv_sets_2;
    private TextView tv_games_1, tv_games_2;
    private TextView tv_points_1, tv_points_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        if (getIntent().getExtras() != null) {
            player_name_1 = getIntent().getStringExtra("player_name_1");
            player_name_2 = getIntent().getStringExtra("player_name_2");
            set_number = getIntent().getIntExtra("set_number", 1);
            service = getIntent().getBooleanExtra("service", true);
        }

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        player_1 = findViewById(R.id.player_name_1);
        player_2 = findViewById(R.id.player_name_2);
        service_1 = findViewById(R.id.service_1);
        service_2 = findViewById(R.id.service_2);
        tv_sets_1 = findViewById(R.id.sets_1);
        tv_sets_2 = findViewById(R.id.sets_2);
        tv_games_1 = findViewById(R.id.games_1);
        tv_games_2 = findViewById(R.id.games_2);
        tv_points_1 = findViewById(R.id.points_1);
        tv_points_2 = findViewById(R.id.points_2);

        player_1.setText(player_name_1);
        player_2.setText(player_name_2);

        if (service) {
            service_1.setText("*");
            service_2.setText(" ");
        } else {
            service_1.setText(" ");
            service_2.setText("*");
        }

        button1.setOnClickListener(MatchOnClickListener);
        button2.setOnClickListener(MatchOnClickListener);
    }

    private View.OnClickListener MatchOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button1: {
                    PointPlayerOne();
                    break;
                }

                case R.id.button2: {
                    PointPlayerTwo();
                    break;
                }
            }
        }
    };

    private void PointPlayerOne() {

        if (!tie_break) { // normal game

            // set service
            if (service) {
                service_1.setText("*");
                service_2.setText(" ");
            }
            else {
                service_1.setText(" ");
                service_2.setText("*");
            }

            // set the score
            if (points_1.equals("0")) { // 0-x
                points_1 = "15";
                tv_points_1.setText(points_1);
            }
            else if (points_1.equals("15")) { // 15-x
                points_1 = "30";
                tv_points_1.setText(points_1);
            }
            else if (points_1.equals("30")) { // 30-x
                points_1 = "40";
                tv_points_1.setText(points_1);
            }
            else if (points_1.equals("40")) { // 40-x
                if (points_2.equals("Ad")) { // 40-Ad
                    points_2 = "40";
                    tv_points_2.setText(points_2);
                }
                else if (points_2.equals("40")) { // 40-40
                    points_1 = "Ad";
                    tv_points_1.setText(points_1);
                }
                else { // 40-0 || 40-15 || 40-30 -> game
                    GameUpdateOne();
                }
            }
            else if (points_1.equals("Ad")){ // Ad-40
                GameUpdateOne();
            }
        }
        else { // tie-break

            // set service
            if (service_tie) {
                service_1.setText("*");
                service_2.setText(" ");
            }
            else {
                service_1.setText(" ");
                service_2.setText("*");
            }

            // set the tie-break score
            points_tie_1++;
            tv_points_1.setText(String.valueOf(points_tie_1));

            // check the tie_service
            if ((points_tie_1 + points_tie_2) % 2 != 0) { // if tie-break points is odd check the tie_service
                service_tie = !service_tie;
            }
            // set service
            if (service_tie) {
                service_1.setText("*");
                service_2.setText(" ");
            }
            else {
                service_1.setText(" ");
                service_2.setText("*");
            }

            if ((points_tie_1 >= 7) && (points_tie_1 - points_tie_2 >= 2)) { // set
                tie_break = false;
                GameUpdateOne();
            }
        }
    };

    private void PointPlayerTwo() {

        if (!tie_break) { // normal game

            // set service
            if (service) {
                service_1.setText("*");
                service_2.setText(" ");
            }
            else {
                service_1.setText(" ");
                service_2.setText("*");
            }

            // set the score
            if (points_2.equals("0")) { // 0-x
                points_2 = "15";
                tv_points_2.setText(points_2);
            }
            else if (points_2.equals("15")) { // 15-x
                points_2 = "30";
                tv_points_2.setText(points_2);
            }
            else if (points_2.equals("30")) { // 30-x
                points_2 = "40";
                tv_points_2.setText(points_2);
            }
            else if (points_2.equals("40")) { // 40-x
                if (points_1.equals("Ad")) { // 40-Ad
                    points_1 = "40";
                    tv_points_1.setText(points_1);
                }
                else if (points_1.equals("40")) { // 40-40
                    points_2 = "Ad";
                    tv_points_2.setText(points_2);
                }
                else { // 40-0 || 40-15 || 40-30 -> game
                    GameUpdateTwo();
                }
            }
            else if (points_2.equals("Ad")){ // Ad-40
                GameUpdateTwo();
            }
        }
        else { // tie-break

            // set service
            if (service_tie) {
                service_1.setText("*");
                service_2.setText(" ");
            }
            else {
                service_1.setText(" ");
                service_2.setText("*");
            }

            // set the tie-break score
            points_tie_2++;
            tv_points_2.setText(String.valueOf(points_tie_2));

            // check the tie_service
            if ((points_tie_1 + points_tie_2) % 2 != 0) { // if tie-break points is odd check the tie_service
                service_tie = !service_tie;
            }
            // set service
            if (service_tie) {
                service_1.setText("*");
                service_2.setText(" ");
            }
            else {
                service_1.setText(" ");
                service_2.setText("*");
            }

            if ((points_tie_2 >= 7) && (points_tie_2 - points_tie_1 >= 2)) { // set
                tie_break = false;
                GameUpdateTwo();
            }
        }
    };

    private void GameUpdateOne() {
        games_1++;
        points_1 = "0";
        points_2 = "0";
        tv_games_1.setText(String.valueOf(games_1));
        tv_points_1.setText(points_1);
        tv_points_2.setText(points_2);

        // set service after game update
        service = !service;
        if (service) {
            service_1.setText("*");
            service_2.setText(" ");
        }
        else {
            service_1.setText(" ");
            service_2.setText("*");
        }

        // set the score after game update
        if ((games_1 == 6) && (games_2 == 6)) { // tie-break
            tie_break = true;
            service_tie = service;
            points_tie_1 = 0;
            points_tie_2 = 0;
            tv_points_1.setText(String.valueOf(points_tie_1));
            tv_points_2.setText(String.valueOf(points_tie_2));
        }
        else if ((games_1 == 7) || (games_1 == 6 && games_2 <= 4)) { // set
            sets_1++;
            games_1 = 0;
            games_2 = 0;
            tv_sets_1.setText(String.valueOf(sets_1));
            tv_games_1.setText(String.valueOf(games_1));
            tv_games_2.setText(String.valueOf(games_2));

            if (sets_1 == set_number)
                GameSetMatch();
        }
    };

    private void GameUpdateTwo() {
        games_2++;
        points_1 = "0";
        points_2 = "0";
        tv_games_2.setText(String.valueOf(games_2));
        tv_points_1.setText(points_1);
        tv_points_2.setText(points_2);

        // set service after game update
        service = !service;
        if (service) {
            service_1.setText("*");
            service_2.setText(" ");
        }
        else {
            service_1.setText(" ");
            service_2.setText("*");
        }

        // set the score after game update
        if ((games_1 == 6) && (games_2 == 6)) { // tie-break
            tie_break = true;
            service_tie = service;
            points_tie_1 = 0;
            points_tie_2 = 0;
            tv_points_1.setText(String.valueOf(points_tie_1));
            tv_points_2.setText(String.valueOf(points_tie_2));
        }
        else if ((games_2 == 7) || (games_2 == 6 && games_1 <= 4)) { // set
            sets_2++;
            games_1 = 0;
            games_2 = 0;
            tv_sets_2.setText(String.valueOf(sets_2));
            tv_games_1.setText(String.valueOf(games_1));
            tv_games_2.setText(String.valueOf(games_2));

            if (sets_2 == set_number)
                GameSetMatch();
        }
    };

    private void GameSetMatch() {
        Intent intent = new Intent(MatchActivity.this, FinalActivity.class);
        intent.putExtra("player_name_1", player_name_1);
        intent.putExtra("player_name_2", player_name_2);
        intent.putExtra("sets_1", sets_1);
        intent.putExtra("sets_2", sets_2);
        startActivity(intent);
    };
}