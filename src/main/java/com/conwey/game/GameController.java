package com.conwey.game;

import java.util.concurrent.atomic.AtomicInteger;

public class GameController {
    private static AtomicInteger idSequence = new AtomicInteger(0);

    public Game createGame(Game game) {
        game.setId(idSequence.getAndIncrement());
        return game;
    }
}
