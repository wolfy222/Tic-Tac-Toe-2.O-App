package com.example.TicTacToe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // 1: yellow, 0: red, 2: empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;
    int huh=0;
    boolean gameActive = true;
    Boolean whatsGoingOn =false;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
                huh++;
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.red);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 0;

            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            Button playAgainButton = (Button) findViewById(R.id.playAgainButton);


            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    // Somone has won!
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {
                        winner = "Red has won!";
                        winnerTextView.setText(winner);
                        winnerTextView.setTextColor(getResources().getColor(R.color.RED));
                    } else {
                        winner = "Yellow has won!";
                        winnerTextView.setText(winner);
                       winnerTextView.setTextColor(getResources().getColor(R.color.YELLOW));
                    }

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                    winnerTextView.animate().rotation(720).setDuration(500);

                }
            }
        }
         if(huh==9 && gameActive==true)
        {
            Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
            playAgainButton.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Nobody won :(", Toast.LENGTH_SHORT).show();
        }
    }

    public void playAgain(View view) {
        huh=0;
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);
        winnerTextView.animate().rotation(-360);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
