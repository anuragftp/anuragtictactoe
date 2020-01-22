package com.example.tictoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // 0-X
    // 1-O
    int activePlayer =0;
    int [] gameState ={2,2,2,2,2,2,2,2,2};
    //state meaning
    //0-X
    //1-O
    //2-null state
    int [][] winState ={{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void playerTap(View view)
    {
        ImageView img = (ImageView) view;
        int tagTapped =Integer.parseInt(img.getTag().toString());
        if(!gameActive)
        {
            gameReset(view);
        }
        if(gameState[tagTapped]== 2)
        {
            gameState[tagTapped] =activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0)
            {
                img.setImageResource(R.drawable.grid2);
                activePlayer=1;
                TextView status = findViewById(R.id.status);
                status.setText("O's turn - Tap to play");
            }
            else
            {
                img.setImageResource(R.drawable.grid4);
                activePlayer =0;
                TextView status = findViewById(R.id.status);
                status.setText("X's turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //check if any player  has won
        for(int [] winPosition: winState)
        {
            if(gameState[winPosition[0]]==gameState[winPosition[1]]&&gameState[winPosition[1]]==gameState[winPosition[2]]&&gameState[winPosition[0]]!=2)
            {
                //somebody has won! -find
                String winnerStr;
                gameActive=false;
                if(gameState[winPosition[0]]==0)
                {
                    winnerStr= "X has Won";
                }
                else
                {
                    winnerStr = "O has Won";
                }
                //update  the Status bar.
                TextView status =findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }

    }
    public void gameReset(View view)
    {
        gameActive = true;
        activePlayer = 0;
        for (int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's turn - Tap to play");


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
