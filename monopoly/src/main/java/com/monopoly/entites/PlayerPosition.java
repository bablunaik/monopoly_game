package com.monopoly.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player_position")
public class PlayerPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long positionId;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "place_id")
    private Long placeId;

    @OneToOne
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    private Player player;

    @Column(name = "position")
    private int position;

    // Constructors, getter/setter methods, and other fields (if needed)
}
