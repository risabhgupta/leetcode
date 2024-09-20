/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var luckyNumbers = function (matrix) {
    var minInRow = [];
    var maxInCol = [];
    var result = [];

    for (var row = 0; row < matrix.length; row++) {
        for (var col = 0; col < matrix[row].length; col++) {
            if (minInRow[row] == null) {
                minInRow[row] = Number.MAX_SAFE_INTEGER;
            }
            minInRow[row] = Math.min(minInRow[row], matrix[row][col]);
            if (maxInCol[col] == null) {
                maxInCol[col] = 0;
            }
            maxInCol[col] = Math.max(maxInCol[col], matrix[row][col]);
        }
    }

    for (var row = 0; row < minInRow.length; row++) {
        for (var col = 0; col < maxInCol.length; col++) {
            if (minInRow[row] == maxInCol[col]) {
                result.push(matrix[row][col]);
            }
        }
    }

    return result;

};

luckyNumbers([[3, 7, 8], [9, 11, 13], [15, 16, 17]]);