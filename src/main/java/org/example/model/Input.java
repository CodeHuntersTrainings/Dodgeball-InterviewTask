package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input {
    private Long[][] players;
    private Direction startingDirection;
    private Integer startingPlayer;

    public Input(Integer length) {
        players = new Long[length][2];
    }

    public void addPosition(Long x, Long y, Integer index) {
        players[index][0] = x;
        players[index][1] = y;
    }
}
