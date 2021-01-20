package org.canmo;

import java.util.Iterator;

public interface Generator extends Iterator {
    int[][] createSudoku(int[] puzzle, int size);
}
