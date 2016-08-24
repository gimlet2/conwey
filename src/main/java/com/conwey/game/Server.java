package com.conwey.game;

import com.conwey.game.exceptions.GameNotFoundException;
import com.google.gson.Gson;
import spark.Request;

import static spark.Spark.delete;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class Server {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        JsonTransformer jsonTransformer = new JsonTransformer();
        Gson gson = new Gson();
        staticFileLocation("/public");
        post("/game", (req, res) -> {
            Game game = gson.fromJson(req.body(), Game.class);
            return gameController.createGame(game);
        }, jsonTransformer);

        get("/game/:id", (req, res) -> gameController.getGame(getIntParam("id", req)), jsonTransformer);

        get("/game/:id/generation/:generation",
                (req, res) ->
                        gameController.getGameGeneration(getIntParam("id", req), getIntParam("generation", req)), jsonTransformer);

        delete("/game/:id", (req, res) -> {
            gameController.deleteGame(getIntParam("id", req));
            return "";
        });

        exception(GameNotFoundException.class, (exception, request, response) -> response.status(404));
    }

    private static Integer getIntParam(String name, Request req) {
        return Integer.valueOf(req.params(name));
    }

}
