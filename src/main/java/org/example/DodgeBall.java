package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.model.Direction;
import org.example.model.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DodgeBall {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public String playAllGamesFromJsonInput(String request) {
        Input[] inputs = objectMapper.readValue(request, Input[].class);
        return playAllGames(inputs);
    }

    @SneakyThrows
    public String playAllGamesFromTxtInput(String request) {
        String[] data = request.split("\n");
        int whereWeAreInData = 0;

        int numberOfGames = Integer.parseInt(data[whereWeAreInData++]);
        Input[] inputs = new Input[numberOfGames];

        for (int i = 0; i < numberOfGames; i++) {
            int numberOfPlayers = Integer.parseInt(data[whereWeAreInData++]);
            Input input = new Input(numberOfPlayers);

            for (int j = 0; j < numberOfPlayers; j++) {
                String[] playerPosition = data[whereWeAreInData++].split(" ");
                Long x = Long.parseLong(playerPosition[0]);
                Long y = Long.parseLong(playerPosition[1]);
                input.addPosition(x, y, j);
            }

            Direction direction = Direction.valueOf(data[whereWeAreInData++]);
            input.setStartingDirection(direction);

            Integer starterPlayer = Integer.parseInt(data[whereWeAreInData++]);
            input.setStartingPlayer(starterPlayer);

            inputs[i] = input;
        }

        return playAllGames(inputs);

    }

    private String playAllGames(Input[] inputs) {
        return Arrays.stream(inputs)
                .map(this::playAGame)
                .collect(Collectors.joining("\n"));
    }

    private String playAGame(Input input) {
        Integer startingPlayerIndex = input.getStartingPlayer() - 1;
        Direction startingDirection = input.getStartingDirection();

        List<Integer> playersOutInOrder = new ArrayList<>();

        passTheBall(startingPlayerIndex, startingDirection, playersOutInOrder, input.getPlayers());

        return (playersOutInOrder.size() - 1) + " " + (playersOutInOrder.getLast() + 1);
    }

    private void passTheBall(Integer currentPlayerByIndex, Direction startingDirection, List<Integer> playersOutInOrder, Long[][] players) {
        Direction[] rotatedDirections = Direction.getDirectionsStartingAfter(startingDirection);
        playersOutInOrder.add(currentPlayerByIndex);

        for (Direction currentDirection : rotatedDirections) {

            //Find the interesting players by Angle
            List<Integer> interestedIndexesOfPlayersByAngle = new LinkedList<>();
            for (int i = 0; i < players.length; i++) {
                if (!playersOutInOrder.contains(i) &&
                        isPlayerOnTheCorrectDirectionOnTheLineDefinedByDirectionAndCurrentPlayer(currentDirection, players[currentPlayerByIndex], players[i])) {
                    interestedIndexesOfPlayersByAngle.add(i);
                }
            }

            //Choosing the closest player if we have more interesting ones
            if (!interestedIndexesOfPlayersByAngle.isEmpty()) {
                int closestPlayerByIndex = interestedIndexesOfPlayersByAngle.getFirst();
                double closestDistance = distance(players[currentPlayerByIndex], players[closestPlayerByIndex]);
                for (Integer integer : interestedIndexesOfPlayersByAngle) {
                    double currentDistance = distance(players[currentPlayerByIndex], players[integer]);
                    if (currentDistance < closestDistance) {
                        closestDistance = currentDistance;
                        closestPlayerByIndex = integer;
                    }
                }

                passTheBall(closestPlayerByIndex, currentDirection.findTheOppositeDirection(), playersOutInOrder, players);
                break;
            }
        }
    }

    private double distance(Long[] player1, Long[] player2) {
        return Math.sqrt(Math.pow(player1[0] - player2[0], 2) + Math.pow(player1[1] - player2[1], 2));
    }

    private boolean isZeroAngleBetweenDirectionVectorAndPlayerVector(Direction direction, Long[] currentPlayer, Long[] checkedPlayer) {
        //Normalizing Direction Vector
        Integer[] directionVector = direction.getVector();
        double directionVectorLength = Math.sqrt(directionVector[0] * directionVector[0] + directionVector[1] * directionVector[1]);
        double normalizedDirectionVectorX = directionVector[0] / directionVectorLength;
        double normalizedDirectionVectorY = directionVector[1] / directionVectorLength;

        //Normalizing Vector From CurrentPlayer to CheckedPlayer
        long px = checkedPlayer[0] - currentPlayer[0];
        long py = checkedPlayer[1] - currentPlayer[1];
        double playerLength = Math.sqrt(px * px + py * py);
        double normalizedPlayerVectorX = px / playerLength;
        double normalizedPlayerVectorY = py / playerLength;

        //True must be returned if dotProduct is 1
        double dotProduct = normalizedDirectionVectorX * normalizedPlayerVectorX + normalizedDirectionVectorY * normalizedPlayerVectorY;
        return Math.abs(dotProduct - 1) < 1e-15;
    }

    private boolean isPlayerOnTheCorrectDirectionOnTheLineDefinedByDirectionAndCurrentPlayer(Direction direction, Long[] currentPlayer, Long[] checkedPlayer) {
        Integer[] directionVector = direction.getVector();

        Long[] vectorToChecked = {checkedPlayer[0] - currentPlayer[0], checkedPlayer[1] - currentPlayer[1]};

        if (directionVector[0] * vectorToChecked[0] < 0 || directionVector[1] * vectorToChecked[1] < 0) {
            return false;
        }

        return (directionVector[0] * vectorToChecked[1] - directionVector[1] * vectorToChecked[0]) == 0;
    }
}
