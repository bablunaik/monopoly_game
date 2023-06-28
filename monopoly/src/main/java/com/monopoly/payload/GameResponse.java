package com.monopoly.payload;

import java.time.LocalDateTime;

public class GameResponse {
    private Long id;
    private LocalDateTime startTime;

    public GameResponse(Long id, LocalDateTime startTime) {
        this.id = id;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    // Other setter and getter methods, if needed
}
