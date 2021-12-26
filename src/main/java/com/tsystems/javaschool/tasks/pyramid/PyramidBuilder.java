package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws CannotBuildPyramidException if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        if (inputNumbers.contains(null)) throw new CannotBuildPyramidException();
        int height = getHeight(inputNumbers.size());

        Collections.sort(inputNumbers);

        int[][] result = new int[height][2 * height - 1];

        int index = 0;
        for(int i = 0; i < height; i++) {
            for(int j = height - i - 1; j < height + i; j += 2) {
                result[i][j] = inputNumbers.get(index++);
            }
        }
        return result;
    }

    private static int getHeight(int size) {
        if (size == 0) return 0;

        double res = (Math.sqrt(1 + 8 * size) - 1) / 2;
        if (res - (int) res != 0.0) throw new CannotBuildPyramidException();

        return (int) res;
    }
}
