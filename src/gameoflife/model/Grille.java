/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.model;

import java.util.Random;

/**
 *
 * @author rafiki
 */
public class Grille implements MyInterface {

    private int cellCount = 0;
    private int iterationNbr = 0;
    private int[] grilleInt;
    private final int grilleSize;
    private int[] resInt;

    public Grille(int grilleSize) {
        this.grilleSize = grilleSize;
        this.grilleInt = new int[this.grilleSize];

    }

    private void fillMask() {
        for (int i = 0; i < grilleSize; i++) {
            int mask = 1;
            mask = mask << i;
            grilleInt[i] = mask;
        }
    }

    private void fillRandom() {
        Random r = new Random();
        for (int i = 0; i < grilleSize; i++) {
            grilleInt[i] = 0;
            grilleInt[i] = r.nextInt(Integer.MAX_VALUE);
//            System.out.println(grilleInt[i]);
//            System.out.println(Integer.toBinaryString(i));
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int yy = 0; yy < grilleSize; yy++) {
            str += Integer.toBinaryString(grilleInt[yy]) + "\n";
        }

        return str;
    }

    @Override
    public void reset() {
        System.out.println("reset();");
        cellCount=0;
        fillRandom();
        iterationNbr = 0;
        
    }

    @Override
    public void iteration() {
        System.out.println("iteration++;");
        resInt = new int[grilleSize];
        cellCount = 0;
        for (int yy = 0; yy < grilleSize; yy++) {
            for (int xx = 0; xx < grilleSize; xx++) {
                int mask = 1 << xx;
                int r = mask & grilleInt[yy];
                if (r == 0) {
                    //System.out.println("r=0");
                    DeadCellSort(xx, yy);
                    //une case morte doit elle le rester ou une cellule doit naitre ?
                } else {
                    //System.out.println("r!=0");
                    lifeCellSort(xx, yy);
                    cellCount++;
                    //une case vivante doit elle survivre ou vivre ?
                }
            }
        }
        replaceArray();
        iterationNbr++;
    }

    @Override
    public int getGrilleSize() {
        return grilleSize;
    }

    @Override
    public int[] getGrilleForDraw(boolean reverse) {
        int[] toDraw = new int[grilleSize];

        for (int i = 0; i < grilleSize; i++) {
            if (reverse) {
                toDraw[i] = Integer.reverse(grilleInt[i]);
            } else {
                toDraw[i] = grilleInt[i];
            }
        }
        return toDraw;

    }

    private void fill() {
        grilleInt = new int[grilleSize];
        int midle = 0;
        grilleInt[midle++] = 0b1100_0000_0000_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b1010_0000_0000_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b0101_0000_0000_0000_0000_1100_0000_0000;
        grilleInt[midle++] = 0b0010_0000_0000_0000_0001_0010_0000_0000;
        grilleInt[midle++] = 0b0000_0000_0000_0000_0001_0100_0000_0000;
        grilleInt[midle++] = 0b0000_0000_0011_0000_0000_1000_0000_0000;
        grilleInt[midle++] = 0b0000_0000_0100_1000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b0000_0000_0100_1000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b0110_0000_0011_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b1001_0000_0000_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b0101_0000_0000_0000_1100_0000_0000_0000;
        grilleInt[midle++] = 0b0010_1100_0000_0001_0010_0000_0000_0000;
        grilleInt[midle++] = 0b0001_0010_0000_0000_1001_0000_0000_0000;
        grilleInt[midle++] = 0b0000_1010_0000_0000_0110_0000_0000_0000;
        grilleInt[midle++] = 0b0000_0100_0000_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b0000_0000_0000_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b0000_0000_0000_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b0000_0100_0000_0000_0100_0000_1100_0000;
        grilleInt[midle++] = 0b0000_0100_0011_0000_1010_0000_1010_0000;
        grilleInt[midle++] = 0b0000_0100_0011_0000_0100_0000_0110_0000;
        grilleInt[midle++] = 0b0000_0000_0000_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b0000_0000_0000_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b0000_0000_0000_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b1110_0000_0000_0000_0110_0000_0100_0000;
        grilleInt[midle++] = 0b1000_0000_0000_0000_1001_0000_1010_0000;
        grilleInt[midle++] = 0b0100_0000_0000_0000_0110_0000_0101_0000;
        grilleInt[midle++] = 0b0000_0000_0000_0000_0000_0000_0010_0000;
        grilleInt[midle++] = 0b0000_1100_0000_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b0000_1000_0000_0000_0000_0000_0000_0000;
        grilleInt[midle++] = 0b0000_0001_0000_0000_0000_1110_0000_0000;
        grilleInt[midle++] = 0b0000_0011_0000_0000_0000_0111_0000_0000;
        grilleInt[midle++] = 0b0000_0000_0000_0000_0000_0000_0000_0000;

    }

    private void DeadCellSort(int xx, int yy) {
        int aliveCell = AliveCellAround(xx, yy);
        int mask = 1 << xx;
        if (aliveCell == 3) {
            resInt[yy] = resInt[yy] | mask;
        }
    }

    private int AliveCellAround(int xx, int yy) {
        int aliveCellNext = 0;
        int next, prev;
        prev = xx + 1;
        next = xx - 1;
        if (prev < 0) {
            prev = grilleSize - 1;
        }
        //prev=Integer.max(0, prev);
        next %= grilleSize;
        int mask = 1 << xx;
        int maskPrev = 1 << prev;
        int ResPrev = maskPrev & grilleInt[yy];
        if (ResPrev != 0) {
            aliveCellNext++;
        }
        int maskNext = 1 << next;
        int resNext = maskNext & grilleInt[yy];
        if (resNext != 0) {
            aliveCellNext++;
        }

        int up = yy - 1;
        int down = yy + 1;
        if (up < 0) {
            up = grilleSize - 1;
        }
        //up = Integer.max(0, up);
        down %= grilleSize;

        int resUpPrev = maskPrev & grilleInt[up];
        if (resUpPrev != 0) {
            aliveCellNext++;
        }
        int resup = mask & grilleInt[up];
        if (resup != 0) {
            aliveCellNext++;
        }
        int resUpNext = maskNext & grilleInt[up];
        if (resUpNext != 0) {
            aliveCellNext++;
        }

        int resDownPrev = maskPrev & grilleInt[down];
        if (resDownPrev != 0) {
            aliveCellNext++;
        }
        int resDown = mask & grilleInt[down];
        if (resDown != 0) {
            aliveCellNext++;
        }
        int resDownNext = maskNext & grilleInt[down];
        if (resDownNext != 0) {
            aliveCellNext++;
        }

        return aliveCellNext;
    }

    private void lifeCellSort(int xx, int yy) {
        int aliveCell = AliveCellAround(xx, yy);
        int mask = 1 << xx;
        if (aliveCell == 2 || aliveCell == 3) {
            resInt[yy] = resInt[yy] | mask;
        } else {
            int d = ~mask;
            resInt[yy] = resInt[yy] & d;

        }
    }

    private void replaceArray() {
        grilleInt = new int[grilleSize];
        for (int i = 0; i < grilleSize; i++) {
            grilleInt[i] = resInt[i];
        }
    }

    public int getCellCount() {
        return cellCount;
    }

    public int getIterationNbr() {
        return iterationNbr;
    }

    public String getIntString() {
        String str ="";
        for(int i:grilleInt){
            str=str.concat(i+"\n");
        }
        return str;
    }
}
