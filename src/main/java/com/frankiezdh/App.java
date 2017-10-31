package com.frankiezdh;

public class App {
    public static void main(String[] args) {
        Solver solver = new Solver();
        Sudoku sudoku = new Sudoku();
        boolean result = solver.solve(sudoku);
        System.out.println(result);
        System.out.println(sudoku);
    }
}
