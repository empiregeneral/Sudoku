package org.canmo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final int GRID_SIZE = 3;

    public static final int ROW_COL_SEC_SIZE = (GRID_SIZE * GRID_SIZE);

    public static final int SEC_GROUP_SIZE = (ROW_COL_SEC_SIZE * GRID_SIZE);

    public static final int BOARD_SIZE = (ROW_COL_SEC_SIZE * ROW_COL_SEC_SIZE);

    public static final int POSSIBILITY_SIZE = (BOARD_SIZE * ROW_COL_SEC_SIZE);

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[][] puzzle = readFromStdin(reader);
        for (int i = 0; i < puzzle.length; i++) {
            System.out.println("Sudoku puzzle:  ");
            SudokuSolver.print(puzzle[i]);
            if (SudokuSolver.BACKTRACE.solver(puzzle[i], 9)) {
                System.out.println("Solved Sudoku puzzle: ");
                Backtrace.printResult(puzzle[i], 9);
                System.out.println("************************");
            } else {
                System.out.println("Sudoku puzzle has no solution.");
            }
        }
        System.out.println("Elapse time: " + (System.currentTimeMillis() - start) + " ms.");
    }

    private static int[][] readFromStdin(BufferedReader reader) throws IOException {
        synchronized (reader) {
            int[][] puzzles = new int[200][BOARD_SIZE];
            String line;
            for (int index = 0; (line = reader.readLine()) != null; index++) {
                int[] puzzle = new int[BOARD_SIZE];
                for (int i = 0; i < BOARD_SIZE; i++) {
                    char ch = line.charAt(i);
                    if ( ch == '.' || ch == '0') {
                        puzzle[i] = 0;
                    } else {
                        puzzle[i] = ch - '0';
                    }
                }
                puzzles[index] = puzzle;
            }
            return puzzles;
        }
    }
}
