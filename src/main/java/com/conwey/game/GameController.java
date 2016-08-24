package com.conwey.game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {
    private static AtomicInteger idSequence = new AtomicInteger(0);
    private Map<Integer, Game> games = new ConcurrentHashMap<>();

    public Game createGame(Game game) {
        game.setId(idSequence.getAndIncrement());
        game.init();
        games.put(game.getId(), game);
        return game;
    }

    public Game getGame(Integer id) {
        return games.get(id);
    }

    public void deleteGame(Integer id) {
        games.remove(id);
    }
}
