package com.sau.dsa.termproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight {
    final List<int[]> moveset = Arrays.asList(
            new int[]{-1, -2},
            new int[]{-2, -1},
            new int[]{-2, +1},
            new int[]{-1, +2},
            new int[]{+1, -2},
            new int[]{+2, -1},
            new int[]{+2, +1},
            new int[]{+1, +2}
    );
    int xPosition;
    int yPosition;
    final List<Integer> legalMoves = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
    List<int[]> possibleMoves = new ArrayList(8);

    public Knight(int[] position) {
        if (position.length != 2) {
//            throw new Exception("given position has to be exactly two numbers");
        }
        setxPosition(position[0]);
        setyPosition(position[1]);
        findPossibleMoves();
    }

    private List findPossibleMoves() {
        for (int[] move: moveset) {
            int newX = getxPosition() + move[0];
            int newY = getyPosition() + move[1];

            if (legalMoves.contains(newX) && legalMoves.contains(newY)) {
                possibleMoves.add(new int[]{newX, newY});
            }
        }
        return possibleMoves;
    }

    @Override
    public String toString() {
        return "Knight{ " +
                "xPosition= " + xPosition +
                ", yPosition= " + yPosition +
                ", possibleMoves= " + printPossibleMoves() +
                " }";
    }

    private String printPossibleMoves() {
        String possibleMovesString = "[ ";
        for (int[] move: possibleMoves) {
            possibleMovesString = possibleMovesString + Arrays.toString(move) + ", ";
        }
        possibleMovesString = possibleMovesString.substring(0, possibleMovesString.length() - 2)
                + " ]";
        return possibleMovesString;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}