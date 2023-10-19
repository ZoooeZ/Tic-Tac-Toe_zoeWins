package com.example.zoewins;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0: zoe with flower, 1: zoe with glasses , 2: empty
    int activePlayer = 0 ;

    int[] gameStatus = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int playedTimes = 0;

    boolean gameActive = true;

    public void dropIn(View view) {


        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // Toast.makeText(this, Integer.toString(playedTimes), Toast.LENGTH_SHORT).show();

        if(gameStatus[tappedCounter] == 2 && gameActive ) {

            playedTimes++;

            gameStatus[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.zone);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.ztwo);

                activePlayer = 0;
            }



            counter.animate().rotation(360).translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                String winner = "";

                if (gameStatus[winningPosition[0]] == gameStatus[winningPosition[1]] && gameStatus[winningPosition[1]] == gameStatus[winningPosition[2]] && gameStatus[winningPosition[0]] != 2) {

                    //someone has won
                    gameActive = false;


                    if (activePlayer == 1) {

                        winner = "Zoe";

                    } else {

                        winner = "Zoe";
                    }


                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText("Ohooooo " + winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);

                }else if(playedTimes == 9){

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText("Better luck next time:)");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }

            }

        }

    }

    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }
        activePlayer = 0;

        for (int j = 0 ; j < gameStatus.length; j++) {

            gameStatus[j] = 2;
        }

        gameActive = true;

        playedTimes = 0;

    }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
}