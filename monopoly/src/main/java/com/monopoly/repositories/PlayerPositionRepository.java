package com.monopoly.repositories;

import com.monopoly.entites.PlayerPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface PlayerPositionRepository extends JpaRepository<PlayerPosition, Long> {

    @Query("SELECT p.position FROM PlayerPosition p WHERE p.player.id = :playerId")
    int getPlayerPosition(@Param("playerId") String playerId);

    @Modifying
    @Query("UPDATE PlayerPosition p SET p.position = :position WHERE p.player.id = :playerId")
    void updatePlayerPosition(@Param("playerId") String playerId, @Param("position") int position);
}

