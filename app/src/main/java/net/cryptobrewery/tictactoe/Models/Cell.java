package net.cryptobrewery.tictactoe.Models;

import android.widget.Button;

public class Cell {
    private Game.State State;
    private Button Btn;

    public Cell(Game.State state, Button btn) {
        State = state;
        Btn = btn;
    }

    public Game.State getState() {
        return State;
    }

    public void setState(Game.State state) {
        State = state;
    }

    public Button getBtn() {
        return Btn;
    }

}
