package org.canmo;

public abstract class AbstractSudokuSolver {
    protected int[][] puzzle;
    protected final int n;

    public AbstractSudokuSolver(int[] sudoku, int n) {
        this.n = n;
        this.puzzle = transformArray(sudoku, n);
    }

    private int[][] transformArray(int[] arr, int n) {
        assert arr.length % n == 0;
        int[][] result = new int[n][n];
        for (int index = 0; index < arr.length; index++) {
            int num = arr[index];
            int row = index / n;
            int col = index % n;
            result[row][col] = num;
        }

        return result;
    }

    /**
     * @Description 运用回溯法解决sudoku问题
     * @param puzzle
     * @return boolean
     */
    protected abstract boolean solver(int[][] puzzle, int n);

    protected boolean isValid(int[][] puzzle, int row, int col, int num) {
        for (int d = 0; d < puzzle[0].length; d++) {
            if (puzzle[row][d] == num) {
                return false;
            }
        }

        for (int r = 0; r < puzzle.length; r++) {
            if (puzzle[r][col] == num) {
                return false;
            }
        }

        int sqrt = (int)Math.sqrt(puzzle.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;
        for (int i = boxRowStart; i < boxRowStart + sqrt;i++) {
            for (int j = boxColStart; j < boxColStart + sqrt; j++) {
                if (puzzle[i][j] == num) {
                    return false;
                }
            }
        }


        return true;
    }
}
