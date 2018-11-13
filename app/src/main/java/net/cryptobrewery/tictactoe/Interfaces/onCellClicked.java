package net.cryptobrewery.tictactoe.Interfaces;

import net.cryptobrewery.tictactoe.Models.Cell;

public interface onCellClicked {
    void clicked(int pos, Cell btn, Cell[][] btnArray);
}
