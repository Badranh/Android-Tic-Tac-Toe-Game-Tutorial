package net.cryptobrewery.tictactoe.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import net.cryptobrewery.tictactoe.Models.Game;
import net.cryptobrewery.tictactoe.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init
        Button btnNewGame = findViewById(R.id.PlayAgain);
        RecyclerView gridView = findViewById(R.id.GridView);
        //init Game
         final Game game = new Game(gridView,this);
        game.startGame();
        //set Listeners
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.resetGame();
            }
        });

    }
}
