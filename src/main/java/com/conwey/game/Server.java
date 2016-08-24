package com.conwey.game;

import com.google.gson.Gson;
import spark.Request;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;

public class Server {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        Gson gson = new Gson();
        post("/game", (req, res) -> {
            Game game = gson.fromJson(req.body(), Game.class);
            return gameController.createGame(game);
        });
        get("/game/:id", (req, res) -> gameController.getGame(getId(req)));
        delete("/game/:id", (req, res) -> gameController.deleteGame(getId(req)));

    }

    private static Integer getId(Request req) {
        return Integer.valueOf(req.params("id"));
    }


}
