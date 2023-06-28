package com.monopoly.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private int position;

    @Column(name = "buy_price")
    private double buyPrice;

    @Column(name = "rent")
    private double rent;

    public boolean isOwned() {
        return owned;
    }
    @Column(name = "is_owned")
    private boolean owned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Player owner;

    // Constructors, getter/setter methods, and other fields (if needed)
}

