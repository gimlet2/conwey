package com.conwey.game;

import com.conwey.game.exceptions.GameNotFoundException;

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
        checkGameExists(id);
        return games.get(id);
    }

    public Game getGameGeneration(Integer id, Integer generation) {
        checkGameExists(id);
        Game game = games.get(id);
        return game.generation(generation);
    }

    public void deleteGame(Integer id) {
        checkGameExists(id);
        games.remove(id);
    }

    private void checkGameExists(Integer id) {
        if (!games.containsKey(id)) {
            throw new GameNotFoundException();
        }
    }
}
