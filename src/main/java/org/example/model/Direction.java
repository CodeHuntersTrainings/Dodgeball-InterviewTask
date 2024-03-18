package org.example.model;

import lombok.Getter;

@Getter
public enum Direction {
    N(0, new Integer[]{0, 1}),
    NE(1, new Integer[]{1, 1}),
    E(2, new Integer[]{1, 0}),
    SE(3, new Integer[]{1, -1}),
    S(4, new Integer[]{0, -1}),
    SW(5, new Integer[]{-1, -1}),
    W(6, new Integer[]{-1, 0}),
    NW(7, new Integer[]{-1, 1});

    private final Integer clockwiseOrder;
    private final Integer[] vector;

    Direction(Integer clockwiseOrder, Integer[] vector) {
        this.clockwiseOrder = clockwiseOrder;
        this.vector = vector;
    }

    public static Direction[] getDirectionsStartingAfter(Direction startingDirection) {
        Direction[] directions = Direction.values();
        Direction[] rotatedDirections = new Direction[directions.length];

        int index = (startingDirection.ordinal() + 1) % directions.length;

        for (int i = 0; i < directions.length; i++) {
            rotatedDirections[i] = directions[index];
            index = (index + 1) % directions.length;
        }

        return rotatedDirections;
    }

    public Direction findTheOppositeDirection() {
        int indexOfNextDirection = (this.ordinal() + 4) % 8;
        return Direction.values()[indexOfNextDirection];
    }
}
