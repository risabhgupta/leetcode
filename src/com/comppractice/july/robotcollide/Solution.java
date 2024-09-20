package com.comppractice.july.robotcollide;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().survivedRobotsHealths(
                new int[] { 11, 44, 16 },
                new int[] { 1, 20, 17 },
                "RLR"
        ));
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        List<Robot> robots = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            robots.add(new Robot(healths[i], positions[i], directions.charAt(i), i));
        }
        robots.sort(Comparator.comparingInt(Robot::getPosition));

        //Robots sorted as per position
        Stack<Robot> robotStack = new Stack<>();
        for (Robot robot : robots) {
            if (robotStack.isEmpty()) {
                robotStack.push(robot);
            } else {
                Robot older = robotStack.peek();
                Robot newer = robot;
                while (newer.getDirection() == 'L' && older.getDirection() == 'R') {
                    if (older.getHealth() > newer.getHealth()) {
                        older.setHealth(older.getHealth() - 1);
                        newer = null;
                        break;
                    } else if (older.getHealth() < newer.getHealth()) {
                        newer.setHealth(newer.getHealth() - 1);
                        robotStack.pop();
                        if (!robotStack.isEmpty()) {
                            older = robotStack.peek();
                        } else {
                            break;
                        }
                    } else {
                        robotStack.pop();
                        newer = null;
                        break;
                    }
                }
                if (newer != null) {
                    robotStack.push(newer);
                }
            }
        }

        robotStack.sort(Comparator.comparing(Robot::getGivenIndex));

        return robotStack.stream().map(Robot::getHealth).collect(Collectors.toList());

    }
}

class Robot {
    int givenIndex;
    int health;
    int position;
    char direction;

    Robot(final int health, final int position, final char direction, final int givenIndex) {
        this.health = health;
        this.position = position;
        this.direction = direction;
        this.givenIndex = givenIndex;
    }

    int getHealth() {
        return health;
    }

    void setHealth(final int health) {
        this.health = health;
    }

    int getPosition() {
        return position;
    }

    void setPosition(final int position) {
        this.position = position;
    }

    char getDirection() {
        return direction;
    }

    void setDirection(final char direction) {
        this.direction = direction;
    }

    int getGivenIndex() {
        return givenIndex;
    }

    void setGivenIndex(final int givenIndex) {
        this.givenIndex = givenIndex;
    }
}
