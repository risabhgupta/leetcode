package com.comppractice.august.detonatebombs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maximumDetonation(
                new int[][] { { 1, 1, 100000 }, { 100000, 100000, 1 } }));
    }

    static class Bomb {
        List<Bomb> connectedBomb = new ArrayList<>();
    }

    public int maximumDetonation(int[][] bombs) {
        Map<Integer, Bomb> bombMap = new HashMap<>();
        for (int i = 0; i < bombs.length; i++) {
            Bomb sourceBomb = bombMap.computeIfAbsent(i, k -> new Bomb());
            for (int j = 0; j < bombs.length; j++) {
                if (i != j && bombConnectedToBombB(bombs[i], bombs[j])) {
                    sourceBomb.connectedBomb.add(bombMap.computeIfAbsent(j, k -> new Bomb()));
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for (Bomb bomb : bombMap.values()) {
            result = Math.max(result, countOfDetonation(bomb));
        }

        return result;
    }

    private boolean bombConnectedToBombB(int[] source, int[] remote) {
        long distanceSquare =
                (long) (remote[0] - source[0]) * (remote[0] - source[0]) + (long) (remote[1] - source[1]) * (remote[1]
                        - source[1]);
        return distanceSquare <= (long) source[2] * source[2];
    }

    private int countOfDetonation(Bomb source) {
        Queue<Bomb> detonatedQueue = new LinkedList<>();
        detonatedQueue.offer(source);

        Set<Bomb> detonatedBombs = new HashSet<>();

        int detonatedBombCount = 0;
        while (!detonatedQueue.isEmpty()) {
            Bomb detonatedBomb = detonatedQueue.poll();
            if (!detonatedBombs.contains(detonatedBomb)) {
                detonatedQueue.addAll(detonatedBomb.connectedBomb);
                detonatedBombs.add(detonatedBomb);
                detonatedBombCount++;
            }
        }

        return detonatedBombCount;
    }
}
