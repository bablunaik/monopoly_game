package com.monopoly.service;


import com.monopoly.entites.Game;
import com.monopoly.entites.Place;
import com.monopoly.entites.Player;
import com.monopoly.repositories.GameRepository;
import com.monopoly.repositories.PlaceRepository;
import com.monopoly.repositories.PlayerPositionRepository;
import com.monopoly.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final PlaceRepository placeRepository;
    private final PlayerPositionRepository playerPositionRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository,
                       PlaceRepository placeRepository, PlayerPositionRepository playerPositionRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.placeRepository = placeRepository;
        this.playerPositionRepository = playerPositionRepository;
    }

    public Game createGame() {
        Game game = new Game();
        // Set the start time and other necessary properties
        game.setStartTime(LocalDateTime.now());
        return gameRepository.save(game);
    }

    public Player rollDiceAndMove(String playerId) {
        // Implement the logic to roll the dice, update the player's position, and perform actions based on the landed place
        Player player = playerRepository.findById(Long.parseLong(playerId)).orElse(null);
        if (player == null) {
            throw new IllegalArgumentException("Invalid player ID");
        }

        // Roll the dice and update player's position
        int dice1 = rollDice();
        int dice2 = rollDice();
        int total = dice1 + dice2;

        int currentPosition = playerPositionRepository.getPlayerPosition(playerId);
        int newPosition = (currentPosition + total) % 10;
        playerPositionRepository.updatePlayerPosition(playerId, newPosition);

        // Perform actions based on the landed place
        Place place = placeRepository.findByPosition(newPosition);
        if (place == null) {
            throw new IllegalStateException("Invalid place position");
        }

        if (!place.isOwned()) {
            // Auto purchase the place
            player.setBalance(player.getBalance() - place.getBuyPrice());
            place.setOwned(true);
            place.setOwner(player);
        } else if (place.getOwner().getPlayerId() != player.getPlayerId()) {
            // Pay rent to the owner
            player.setBalance(player.getBalance() - place.getRent());
            Player owner = place.getOwner();
            owner.setBalance(owner.getBalance() + place.getRent());
        }

        return player;
    }

    public boolean checkWinner(Long gameId) {
        // Implement the logic to determine the winner based on bankruptcy or highest cash balance
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game == null) {
            throw new IllegalArgumentException("Invalid game ID");
        }

        List<Player> players = playerRepository.findByGame(game);
        Player winner = null;
        int maxBalance = Integer.MIN_VALUE;
        boolean bankruptcy = false;

        for (Player player : players) {
            if (player.getBalance() > maxBalance) {
                maxBalance = (int) player.getBalance();
                winner = player;
            }
            if (player.getBalance() < 0) {
                bankruptcy = true;
                break;
            }
        }

        if (bankruptcy || winner == null) {
            return false; // No winner yet
        }

        game.setEndTime(LocalDateTime.now());
        game.setWinner(winner);
        gameRepository.save(game);
        return true;
    }

    private int rollDice() {
        // Implement the logic to roll a dice and return the value
        return (int) (Math.random() * 6) + 1;
    }
}
