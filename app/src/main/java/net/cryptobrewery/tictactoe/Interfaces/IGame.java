package net.cryptobrewery.tictactoe.Interfaces;

import net.cryptobrewery.tictactoe.Models.Game;

public interface IGame {
    void resetGame();
    void startGame();
    void GameOver(Game.State Winner);
}
