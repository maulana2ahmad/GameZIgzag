# GameZIgzag


## Priview Game
![g](https://user-images.githubusercontent.com/43386555/54133489-c120d800-4448-11e9-992e-1dc4eb16d2aa.gif)


### Play Again
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
