package net.cryptobrewery.tictactoe.Models;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import net.cryptobrewery.tictactoe.Adapters.GridAdapter;
import net.cryptobrewery.tictactoe.Interfaces.IGame;
import net.cryptobrewery.tictactoe.Interfaces.onCellClicked;

public class Game implements IGame{

    public  enum State {
        X,O,NONE
    }
    private Boolean X,O;
    private onCellClicked cellClicked;
    private RecyclerView view;
    private Context ctx;
    private int moves;
    private GridAdapter gridAdapter;

    public Game(RecyclerView view, Context ctx) {
        this.view = view;
        this.ctx = ctx;
    }

    @Override
    public void resetGame() {
       gridAdapter.setListener(cellClicked);
       gridAdapter.resetCells();
       moves = 0;
    }

    @Override
    public void startGame() {
        //init
        X=true;
        O=false;
        moves = 0;
        //listeners
        cellClicked = new onCellClicked() {
            @Override
            public void clicked(int pos, Cell cell, Cell[][] btnArray) {
                if(cell.getState() != State.NONE){
                    Toast.makeText(ctx, "This Is Already Played", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(X) {
                    cell.getBtn().setText("X");
                    cell.setState(State.X);
                    X=false;
                    O=true;
                    checkWinner(pos,3,State.X,btnArray);
                }else{
                    if(O){
                        cell.getBtn().setText("O");
                        cell.setState(State.O);
                        X=true;
                        O=false;
                        checkWinner(pos,3,State.O,btnArray);
                    }
                }
            }
        };
        //Setting Views
        gridAdapter = new GridAdapter(cellClicked);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ctx,3);
        view.setLayoutManager(gridLayoutManager);
        view.setAdapter(gridAdapter);
        view.forceLayout();
    }

    @Override
    public void GameOver(State Winner) {
        gridAdapter.setListener(null);
        Toast.makeText(ctx, "The winner is: " + Winner, Toast.LENGTH_SHORT).show();
    }

    private void checkWinner(int pos, int n, State s, Cell[][] btnArray){
        //Increment Moves
        moves++;
        //get x y coordinates from position (1 dimension to 2 dimensions translator)
        int x=-1,y=-1;
        if(pos >= 0 && pos<= 2 ){
            x=0;
            y= pos;
        }
        if(pos >=3 && pos <= 5){
            x=1;
            y= pos - 3;
        }
        if(pos >= 6){
            x=2;
            y= pos -6;
        }

        //check if cur player is winner

        //check the columns
        for(int i = 0; i < n; i++){
            if(btnArray[x][i].getState() != s)
                break;
            if(i == n-1){
                GameOver(s);
            }
        }

        //check the rows
        for(int i = 0; i < n; i++){
            if(btnArray[i][y].getState() != s)
                break;
            if(i == n-1){
                GameOver(s);
            }
        }

        //check the diagonals
        if(x == y){
            for(int i = 0; i < n; i++){
                if(btnArray[i][i].getState() != s)
                    break;
                if(i == n-1){
                    GameOver(s);
                }
            }
        }

        if(x + y == n - 1){
            for(int i = 0; i < n; i++){
                if(btnArray[i][(n-1)-i].getState() != s)
                    break;
                if(i == n-1){
                    GameOver(s);

                }
            }
        }

        //maybe its a draw , check it
        if(moves == (Math.pow(n, 2) )){
            Toast.makeText(ctx, "Draw", Toast.LENGTH_SHORT).show();
            gridAdapter.setListener(null);
        }
    }

}
