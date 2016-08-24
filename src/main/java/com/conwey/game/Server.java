package com.conwey.game;

import com.google.gson.Gson;

import static spark.Spark.post;

public class Server {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        Gson gson = new Gson();
        post("/game", (req, res) -> {
            Game game = gson.fromJson(req.body(), Game.class);
            return gameController.createGame(game);
        });
    }


}
