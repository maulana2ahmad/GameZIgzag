package com.redudant.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 red
    int ActivePlayer = 0;

    boolean gameIsActive = true;

    //2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //winning
    int[][] winningPosotions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        int tappeCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappeCounter] == 2 && gameIsActive) {

            gameState[tappeCounter] = ActivePlayer;

            counter.setTranslationY(-1000f);

            if (ActivePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                ActivePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                ActivePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPosotions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    // sameone has won!
                    gameIsActive = false;

                    String winner = "Red";

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    }

                    TextView winnerMassage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMassage.setText(winner + "  has won");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                } else {
                    boolean gameOver = true;

                    for (int counterState : gameState) {
                        if (counterState == 2) gameOver = false;
                    }

                    if (gameOver) {

                        TextView winnerMassage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMassage.setText("It's a draw");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }

            }

        }
    }

    public void PlayAgain(View view) {
        gameIsActive = true;

        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        //0 = yellow, 1 = red
        ActivePlayer = 0;

        //2 means unplayed
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
}
