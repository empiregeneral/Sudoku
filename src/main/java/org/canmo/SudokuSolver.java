package org.canmo;

/**
 * @author Administrator
 */

public enum SudokuSolver{


    BACKTRACE() {
        @Override
        protected boolean solver(int[] sudoku, int n) {
            return Backtrace.getResult(sudoku, n);
        }
    },

    DANCING_LINK() {
        @Override
        protected boolean solver(int[] sudoku, int n) {
            return true;
        }
    };


    protected abstract boolean solver(int[] sudoku, int n);

    public static void print(int[] sudoku) {
        System.out.println(puzzleToString(sudoku));
    }

    public static String puzzleToString(int[] sudoku) {
        final String NL = System.getProperties().getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sudoku.length; i++) {
            sb.append(" ");
            if (sudoku[i] == 0) {
                sb.append('.');
            } else {
                sb.append(sudoku[i]);
            }
            if (i == 80) {
                sb.append(NL);
            } else if (i % 9 == 8){
                sb.append(NL);
                if ( i  % 27 == 26) {
                    sb.append("-------|-------|-------").append(NL);
                }
            } else if (i % 3 == 2) {
                sb.append(" |");
            }
        }

        return sb.toString();
    }
}
