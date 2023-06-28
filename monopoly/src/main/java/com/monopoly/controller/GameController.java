package com.monopoly.controller;

import com.monopoly.entites.Game;
import com.monopoly.entites.Player;
import com.monopoly.payload.GameResponse;
import com.monopoly.payload.PlayerResponse;
import com.monopoly.payload.WinnerResponse;
import com.monopoly.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create-game")
    public ResponseEntity<GameResponse> createGame() {
        Game game = gameService.createGame();
        return ResponseEntity.ok(new GameResponse(game.getGameId(), game.getStartTime()));
    }

    @PostMapping("/roll-die/{playerId}")
    public ResponseEntity<PlayerResponse> rollDieAndMove(@PathVariable String playerId) {
        Player player = gameService.rollDiceAndMove(playerId);
        return ResponseEntity.ok(new PlayerResponse(player.getPlayerId(), player.getName(), (int) player.getBalance()));
    }

    @GetMapping("/check-winner/{gameId}")
    public ResponseEntity<WinnerResponse> checkWinner(@PathVariable Long gameId) {
        boolean isWinner = gameService.checkWinner(gameId);
        if (isWinner) {
            return ResponseEntity.ok(new WinnerResponse("Game has a winner."));
        } else {
            return ResponseEntity.ok(new WinnerResponse("No winner yet."));
        }
    }
}
