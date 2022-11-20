import javax.swing.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row;
    int col;
    int mine;


    MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.mine = (col * row) / 4;
    }


    public void run() {
        int row, col;
        System.out.println("Mayınların konumu");
        String[][] mineField = createMineField();
        getField(mineField);
        Scanner inp = new Scanner(System.in);
        System.out.println("====================");
        String[][] field = createField();
        System.out.println("Mayın tarlasına hoşgeldiniz..!!");
        while (true) {

            getField(field);
            if (isWin(field)){
                break;
            }
            System.out.print("Satır sayısını giriniz: ");
            row = inp.nextInt();
            System.out.print("Sütun sayısını giriniz: ");
            col = inp.nextInt();
            System.out.println("====================");

            if ((row < 0 || row > this.row - 1) || (col < 0 || col > this.col - 1)) {
                System.out.println("Lütfen doğru aralıkta satır ve sütun giriniz !");
                continue;
            }
            int spot = isSafe(mineField, row, col);
            if (spot == -1) {
                System.out.println("Game Over");
                break;
            }
            field[row][col] = String.valueOf(spot);
        }
        System.out.println("Oyunu kazandınız tebrikler");
    }

    public boolean isWin(String[][] array) {
        int check = 0;
        for (String[] i : array) {
            for (String j : i) {
                if (j.equals("-"))
                    check++;
            }
        }
        return check == this.mine;
    }

    public int isSafe(String[][] array, int row/*1*/, int col/*2*/) {
        int mine = 0;
        if (array[row][col].equals("*")) {
            return -1;
        } else {
            int tempRow = --row; //0
            int tempCol;
            col--; // 1
            int j = 0;
            while (j < 3) {
                tempCol = col + j++; //1
                row = tempRow;
                for (int i = 0; i < 3; i++) {
                    if (tempCol < 0 || tempCol >= this.col) {
                        break;
                    } else {
                        if (row < 0 || row >= this.row) {
                            row++;
                            continue;
                        } else {
                            if (array[row][tempCol].equals("*")) {
                                mine++;
                            }
                            row++;
                        }
                    }
                }
            }
        }
        return mine;
    }

    public String[][] createField() {
        String[][] temp = new String[this.row][this.col];
        for (int i = 0; i < temp.length; i++) { // - leri eklediğimiz yer
            for (int j = 0; j < temp[0].length; j++) {
                temp[i][j] = "-";
            }
        }
        return temp;
    }

    public String[][] createMineField() { // Mayın tarlası oluşturup ekrana yazma
        Random rnd = new Random();
        String[][] game = new String[this.row][this.col];
        int rowNumber, colNumber;
        for (int i = 0; i < game.length; i++) { // - leri eklediğimiz yer
            for (int j = 0; j < game[0].length; j++) {
                game[i][j] = "-";
            }
        }
        for (int j = 0; j < this.mine; j++) { // Yıldızları eklediğimiz yer
            rowNumber = rnd.nextInt(this.row);
            colNumber = rnd.nextInt(this.col);
            if (!game[rowNumber][colNumber].equals("*")) {
                game[rowNumber][colNumber] = "*";
            } else {
                j--;
            }
        }
        return game;
    }

    void getField(String[][] arr) {
        for (String[] i : arr) {
            for (String j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

}
