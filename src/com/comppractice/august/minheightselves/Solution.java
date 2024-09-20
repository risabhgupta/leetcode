package com.comppractice.august.minheightselves;

import java.util.Arrays;


class Solution {
    public static void main(String[] args) {
        System.out.println(new com.comppractice.august.minheightselves.Solution().minHeightShelves(
                new int[][] { { 1, 1 }, { 2, 3 }, { 2, 3 }, { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 2 } }, 4));

        System.out.println(new com.comppractice.august.minheightselves.Solution().minHeightShelves(
                new int[][] { { 1, 3 }, { 2, 4 }, { 3, 2 } }, 6));

        System.out.println(new com.comppractice.august.minheightselves.Solution().minHeightShelves(
                new int[][] { { 7, 3 }, { 8, 7 }, { 2, 7 }, {2, 5} }, 10
        ));
    }

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int[] dp = new int[books.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = books[0][1];
        return getHeightAfterPlacing(books, books[0][1], books[0][1], 1, shelfWidth - books[0][0], shelfWidth,
                dp);
    }

    public int getHeightAfterPlacing(int[][] books, int totalHeight, int heightOfCurrentSelf, int index,
            int shelfWidthRemaining, int shelfWidth, int[] dp) {
        if (index >= books.length) {
            return totalHeight;
        } else if (shelfWidthRemaining < 0) {
            return Integer.MAX_VALUE;
        } else {
            //if placedIn new self
            int totalHeightIfPlacedInNewShelf;
            if (dp[index] != Integer.MAX_VALUE) {
                totalHeightIfPlacedInNewShelf = dp[index] + totalHeight;
            } else {
                totalHeightIfPlacedInNewShelf = getHeightAfterPlacing(books, totalHeight + books[index][1],
                        books[index][1], index + 1, shelfWidth - books[index][0], shelfWidth, dp);
                dp[index] = Math.min(totalHeightIfPlacedInNewShelf - totalHeight, dp[index]);
            }

            //if placed in same shelf
            int totalHeightIfPlacedInSameShelf = getHeightAfterPlacing(books,
                    totalHeight - heightOfCurrentSelf + Math.max(heightOfCurrentSelf, books[index][1]),
                    Math.max(heightOfCurrentSelf, books[index][1]), index + 1, shelfWidthRemaining - books[index][0],
                    shelfWidth, dp);

            return Math.min(totalHeightIfPlacedInNewShelf, totalHeightIfPlacedInSameShelf);

        }
    }
}