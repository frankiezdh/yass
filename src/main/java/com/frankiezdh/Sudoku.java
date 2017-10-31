package com.frankiezdh;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sudoku {
    private final static int MAX = 9;

    private int[][] cells = new int[MAX][MAX];

    private void set(int x, int y, int value) {
        cells[x][y] = value;
    }

    private int get(int x, int y) {
        return cells[x][y];
    }

    void set(Position p, int value) {
        set(p.getX(), p.getY(), value);
    }

    private int get(Position p) {
        return get(p.getX(), p.getY());
    }

    Position findFirst() {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (get(i, j) == 0) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    private Set<Integer> findHorizontal(Position p) {
        Set<Integer> result = new TreeSet<>();
        for (int i = 0; i < MAX; i++) {
            int value = get(i, p.getY());
            if (value != 0) {
                result.add(value);
            }
        }
        return result;
    }

    private Set<Integer> findVertical(Position p) {
        Set<Integer> result = new TreeSet<>();
        for (int j = 0; j < MAX; j++) {
            int value = get(p.getX(), j);
            if (value != 0) {
                result.add(value);
            }
        }
        return result;
    }

    private Set<Integer> findSquare(Position p) {
        Set<Integer> result = new TreeSet<>();

        int xMin = p.getX() / 3 * 3;
        int yMin = p.getY() / 3 * 3;

        for (int i = xMin; i < xMin + 3; i++) {
            for (int j = yMin; j < yMin + 3; j++) {
                int value = get(i, j);
                if (value != 0) {
                    result.add(value);
                }
            }
        }
        return result;
    }

    Set<Integer> findAvailable(Position p) {
        Set<Integer> all = findHorizontal(p);
        all.addAll(findVertical(p));
        all.addAll(findSquare(p));

        return IntStream
                .rangeClosed(1, MAX)
                .boxed()
                .filter(x -> !all.contains(x))
                .collect(Collectors.toSet());
    }

    void clear(Position p) {
        set(p, 0);
    }

    boolean valid(Position p, Set<Integer> available) {
        return !(get(p) == 0 && available.isEmpty());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                sb.append(this.get(i, j));
                if (j != MAX - 1) {
                    sb.append(",");
                }
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }
}
