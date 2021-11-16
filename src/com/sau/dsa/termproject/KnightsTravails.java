package com.sau.dsa.termproject;

import java.lang.reflect.Array;
import java.util.*;

public class KnightsTravails {
    DistanceInfo[][] distanceInfo = (DistanceInfo[][]) Array.newInstance(DistanceInfo.class, 8, 8);
    List<int[]> solution = new ArrayList<int[]>();

    public void knightsMoves(int[] start, int[] finish) {
        solution.add(start);
        distanceInfo[finish[0]][finish[1]].distance = 0;

        Knight endKnight = new Knight(finish);
        // using queue data structure so that we can use FIFO
        Queue<Knight> queue = new LinkedList<>();
        queue.add(endKnight);

        findPossibleSolutions(queue, start);

        findShortestPath(start, finish);

    }

    private void findPossibleSolutions(Queue<Knight> queue, int[] start) {
        Boolean solutionFound = false;

        while (!solutionFound) {
            Knight position = queue.remove();
            for (int[] move: position.possibleMoves) {
                if (Arrays.equals(move, start)) {
                    System.out.println("solution found: " + Arrays.toString(move));
//                    solutionFound = true;
                }
//                checking if the position has been visited
                if (distanceInfo[move[0]][move[1]].distance == null) {
//                    taking previous distance and incrementing the distance
                    distanceInfo[move[0]][move[1]].distance =
                            distanceInfo[position.xPosition][position.yPosition].distance + 1;
                    distanceInfo[move[0]][move[1]].parent = position;
                    queue.add(new Knight(move));
                }
            }
            if (queue.size() < 1) {
                solutionFound = true;
            }
        }

        System.out.println(distanceInfo);
    }

    private void findShortestPath(int[] start, int[] finish) {
        Knight startKnight = new Knight(start);

        // smallest distance is 63 as one move is taken by the starting step
        int smallestDistance = 63;
        int largestDistance = 0;
        int[] shortestPath = null;
        while (!Arrays.equals(shortestPath,finish)) {
            for (int[] move: startKnight.possibleMoves) {
                if (distanceInfo[move[0]][move[1]].distance != null &&
                distanceInfo[move[0]][move[1]].distance > largestDistance) {
                    largestDistance = distanceInfo[move[0]][move[1]].distance;
                    shortestPath = move;
                }
            }
            solution.add(shortestPath);
            startKnight = new Knight(shortestPath);
        }
        System.out.println("The shortest path from " + Arrays.toString(start) + " to " + Arrays.toString(finish) + " is " + solution.size() + " : "
                + Arrays.deepToString(solution.toArray()));
    }

    private void initializeDistanceInfo() {
        for (int i = 0; i < distanceInfo.length; i++) {
            for (int j = 0; j < distanceInfo[i].length; j++) {
                distanceInfo[i][j] = new DistanceInfo();
            }
        }
    }

    public KnightsTravails() {
        initializeDistanceInfo();
    }


}



