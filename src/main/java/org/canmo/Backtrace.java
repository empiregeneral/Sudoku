package org.canmo;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Backtrace extends AbstractSudokuSolver implements PrintSudoku {
    private boolean hasEnd;
    private int count = 1;

    public Backtrace(int[] puzzle, int n) {
        super(puzzle, n);
        hasEnd = solver(this.puzzle, this.n);
    }

    @Override
    protected boolean solver(int[][] sudoku, int n) {
        int row = -1;
        int col = -1;
        boolean isEnd = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sudoku[i][j] == 0) {
                    row = i;
                    col = j;

                    isEnd = false;
                    break;
                }
            }

            if (!isEnd) {
                break;
            }
        }

        if (isEnd) {
            return true;
        }

        for (int num = 1; num <= n; num++) {
            if (isValid(sudoku, row, col, num)) {
                sudoku[row][col] = num;
                if (solver(sudoku, n)) {
                    return true;
                } else {
                    puzzle[row][col] = 0;
                }
            }
        }
        return false;
    }

    public static boolean getResult(int[] puzzle, int n) {
        return new Backtrace(puzzle, n).hasEnd;
    }

    public static void printResult(int[] puzzle, int n) {
        Readable readable = new Backtrace(puzzle, n);
        Scanner output = new Scanner(readable);
        while(output.hasNextLine()) {
            System.out.println(output.nextLine());
        }
    }

    public static int[] getSolution(int[] puzzle, int n) {
        int[][] sudoku = new Backtrace(puzzle, n).puzzle;
        return Arrays.stream(sudoku).flatMapToInt(arr -> Arrays.stream(arr)).toArray();
    }

    @Override
    public void printSudoku(int[][] result) {
        int[] tmp = Arrays.stream(result).flatMapToInt(arr -> Arrays.stream(arr)).toArray();
        SudokuSolver.print(tmp);
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        if (--count < 0) {
            return -1;
        }

        int[] tmp = Arrays.stream(this.puzzle).flatMapToInt(arr -> Arrays.stream(arr)).toArray();
        cb.append(SudokuSolver.puzzleToString(tmp));

        return 10;
    }
}
