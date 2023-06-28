package com.monopoly.repositories;

import com.monopoly.entites.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place,Long> {

    Place findByPosition(int position);
}
