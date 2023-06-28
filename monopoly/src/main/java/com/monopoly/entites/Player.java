package com.monopoly.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "name")
    private String name;

    @Column(name = "cash_balance")
    private double cashBalance;

    @Column(name = "balance")
    private double balance;

    @Column(name = "owned")
    private boolean owned;

    @ManyToOne
    @JoinColumn(name = "game_id", insertable = false, updatable = false)
    private Game game;

    // Constructors, getter/setter methods, and other fields (if needed)
}
