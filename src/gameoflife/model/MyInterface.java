/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.model;



/**
 *
 * @author rafiki
 */
public interface MyInterface {
    public void reset();
    public void iteration();
    
    /**
     *
     * @return taille de la grille en nbr de cellules.
     * la grille est toujours un carré.
     */
    public int getGrilleSize();
    public int[] getGrilleForDraw(boolean reverse);


    
    
    
}
