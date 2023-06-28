package com.monopoly.repositories;

import com.monopoly.entites.Game;
import com.monopoly.entites.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface PlayerRepository extends JpaRepository<Player,Long> {
    List<Player> findByGame(Game game);
}
