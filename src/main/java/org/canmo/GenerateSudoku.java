package org.canmo;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Administrator
 */
public class GenerateSudoku implements Generator {
    public static final int GRID_SIZE = 3;

    public static final int ROW_COL_SEC_SIZE = (GRID_SIZE * GRID_SIZE);

    public static final int SEC_GROUP_SIZE = (ROW_COL_SEC_SIZE * GRID_SIZE);

    public static final int BOARD_SIZE = (ROW_COL_SEC_SIZE * ROW_COL_SEC_SIZE);

    public static final int POSSIBILITY_SIZE = (BOARD_SIZE * ROW_COL_SEC_SIZE);

    private int[][] sudoku;
    private int[] puzzle;
    private int size;

    public GenerateSudoku(int size) {
        this.size = size;
        this.sudoku = new int[this.size][this.size];
        this.puzzle = new int[this.size * this.size];
        this.sudoku = createSudoku(puzzle, this.size);
    }



    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public int[][] createSudoku(int[] puzzle, int size) {
        int[] tmp = new int[9];
        fillIncrementing(tmp, size);

        for (int j = 0; j < ROW_COL_SEC_SIZE; j = j + 4) {
            shuffleArray(tmp, size);
            for (int i = 0; i < ROW_COL_SEC_SIZE; i++) {
                int cellId = sectionToCell(j ,i);
                puzzle[cellId] = tmp[i];
            }
        }

        // Backtrace solved puzzle
        if (SudokuSolver.BACKTRACE.solver(puzzle, 9)) {
            Backtrace.printResult(puzzle, 9);
        }

        int[] tmp2 = Backtrace.getSolution(puzzle, 9);
        for (int i = 0; i < puzzle.length; i++) {
            int cellId = new Random().nextInt(9 * 9);
            if (tmp2[cellId] != 0 ) {
                tmp2[cellId] = 0;
            }
        }

        return transformArray(tmp2, 9);
    }



    static int[] fillIncrementing(int[] array, int size) {
        for (int i = 0; i < size; i++) {
            array[i] = i+1;
        }
        return array;
    }

    static void shuffleArray(int[] array, int size) {
        for (int i = 0; i < size; i++) {
            int tailSize = size - i;
            int randTailPos = Math.abs(new Random().nextInt()) % tailSize + i;
            int temp = array[i];
            array[i] = array[randTailPos];
            array[randTailPos] = temp;
        }
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

    public static void main(String[] args) {
        GenerateSudoku sudoku = new GenerateSudoku(9);
    }

    /**
     * Given a section (0-8) and an offset into that section (0-8) calculate the
     * cell (0-80)
     */
    static int sectionToCell(int section, int offset) {
        return (sectionToFirstCell(section)
                + ((offset / GRID_SIZE) * ROW_COL_SEC_SIZE)
                + (offset % GRID_SIZE));
    }

    /**
     * Given a section (0-8) calculate the first cell (0-80) of that section.
     */
    static int sectionToFirstCell(int section) {
        return ((section % GRID_SIZE * GRID_SIZE)
                + (section / GRID_SIZE * SEC_GROUP_SIZE));
    }

}
