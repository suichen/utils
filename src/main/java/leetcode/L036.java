package leetcode;


import java.util.Set;

public class L036 {
    public boolean isValidSudoku(char[][] board) {
        if(board == null) {
            return true;
        }

        int[][] squares = new int[10][10];

        for (int i = 0; i < 9; i++) {
            int[] row = new int[10];
            int[] column = new int[10];

            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int rowCh = board[i][j]-'0';
                    int index = (i/3)*3+j/3;
                    if (row[rowCh] == 1 || squares[index][rowCh] == 1) {
                        return false;
                    }
                    row[rowCh] = 1;
                    squares[index][rowCh]=1;
                }

                if (board[j][i] != '.') {
                    int colCh = board[j][i] - '0';
                    if(column[colCh] == 1) {
                        return false;
                    }
                    column[colCh] = 1;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println("("+i+","+j+") "+"("+(i/3*3+j/3)+","+((i%3*3+j%3))+")");
            }
        }
    }
}
