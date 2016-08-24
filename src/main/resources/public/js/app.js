var app = (function () {

    var gameId;

    var blinkerData = [[false, false, false, false, false],
        [false, false, true, false, false],
        [false, false, true, false, false],
        [false, false, true, false, false],
        [false, false, false, false, false]];

    var beaconData = [[false, false, false, false, false, false],
        [false, true, true, false, false, false],
        [false, true, true, false, false, false],
        [false, false, false, true, true, false],
        [false, false, false, true, true, false],
        [false, false, false, false, false, false]];

    var draw = function (field) {
        var board = document.getElementById("board");
        var ctx = board.getContext("2d");
        var sellSize = board.width / field.length;
        ctx.fillStyle = "#FFFFFF";
        ctx.fillRect(0, 0, board.width, board.height);
        ctx.fillStyle = "#FF0000";

        for (var i = 0; i < field.length; i++) {
            for (var j = 0; j < field[i].length; j++) {
                if (field[i][j]) {
                    ctx.fillRect(j * sellSize, i * sellSize, sellSize, sellSize);
                }
            }
        }
    };

    var createGame = function () {
        load("POST", "/game", function (data) {
            gameId = data.id;
            document.getElementById('gen').value = 1;
            draw(data.field);
        }, '{"width": ' + document.getElementById('width').value + ', "height" :' + document.getElementById('height').value + '}')
    };

    var example = function (d) {
        load("POST", "/game", function (data) {
            gameId = data.id;
            document.getElementById('gen').value = 1;
            draw(data.field);
        }, '{"width": ' + d.length + ', "height" :' + d[0].length + ' , "field": ' + JSON.stringify(d) + '}')
    };

    var generation = function () {
        var gen = document.getElementById('gen');
        load("GET", "/game/" + gameId + "/generation/" + gen.value, function (data) {
            gen.value = 1 + Number(gen.value);
            draw(data.field);
        });
    };

    var deleteGame = function () {
        load("DELETE", "/game/" + gameId);
    };

    var load = function (method, path, callback, body) {
        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == XMLHttpRequest.DONE) {
                callback(JSON.parse(xmlhttp.responseText));
            }
        };

        xmlhttp.open(method, path, true);
        xmlhttp.send(body);
    };

    var timer;
    var go = function () {
        if (timer == null) {
            timer = setInterval(function () {
                generation()
            }, 500);
        } else {
            clearInterval(timer);
            timer = null;
        }
    };

    return {
        create: createGame,
        stop: deleteGame,
        generation: generation,
        go: go,
        blinker: function () {
            example(blinkerData)
        },
        beacon: function () {
            example(beaconData)
        },
        custom: function () {
            example(JSON.parse(document.getElementById('custom').value))
        }

    }
})();