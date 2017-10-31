package com.frankiezdh;

import java.util.Set;

class Solver {
    boolean solve(Sudoku s) {
        if (s == null) {
            return false;
        }

        Position p = s.findFirst();
        if (p == null) {
            return true;
        }

        Set<Integer> availableList = s.findAvailable(p);
        if (!s.valid(p, availableList)) {
            return false;
        }

        for (Integer value : availableList) {
            s.set(p, value);
            if (solve(s)) {
                return true;
            }
        }
        s.clear(p);
        return false;
    }
}
