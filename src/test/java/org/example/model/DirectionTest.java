package org.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    void testFindTheOppositeDirection() {
        Assertions.assertEquals(Direction.S, Direction.N.findTheOppositeDirection());
        Assertions.assertEquals(Direction.SE, Direction.NW.findTheOppositeDirection());
        Assertions.assertEquals(Direction.N, Direction.S.findTheOppositeDirection());
        Assertions.assertEquals(Direction.NW, Direction.SE.findTheOppositeDirection());
    }

}