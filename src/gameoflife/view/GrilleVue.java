/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.view;

import gameoflife.model.Grille;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author rafiki
 */
public class GrilleVue extends JPanel implements MouseListener {

    private final static String TAG = GrilleVue.class.getName();
    private int cellRenderSize = 16;
    JButton reset, iteration,toString;
    JLabel label;
    JRadioButton invertDraw,dispGraf,dispInt;
    private Grille grille = new Grille(32);
    Rectangle drawingRectangle = new Rectangle();
    private int[] graf;
    private int grafOfset=40;
    Color grafColor = new Color(33, 96, 33, 96);
    private final int grafW=1;
    
    

    public GrilleVue() {
        dispGraf = new JRadioButton("Graf");
        dispGraf.setSelected(false);
        dispInt=new JRadioButton("Int");
        dispInt.setSelected(false);
        invertDraw=new JRadioButton("invert draw");
        invertDraw.setSelected(true);
        reset = new JButton("reset();");
        toString = new JButton("toString();");
        iteration = new JButton("iteration++;");
        label = new JLabel("test");
        reset.addMouseListener(this);
        iteration.addMouseListener(this);
        invertDraw.addMouseListener(this);
        dispInt.addMouseListener(this);
        dispGraf.addMouseListener(this);
        toString.addMouseListener(this);
        this.addMouseListener(this);

        
    }

    public GrilleVue init(int width, int height) {
        setSize(width, height);
        setMaximumSize(new java.awt.Dimension(width, height));
        setPreferredSize(new java.awt.Dimension(width, height));
        setMinimumSize(new java.awt.Dimension(width, height));
        add(dispGraf);
        add(dispInt);
        add(invertDraw);
        add(reset);
        add(iteration);
        add(toString);
        add(label);
        setVisible(true);

        createRectangle();
        graf=new int[width/4];
        return this;
    }
    @Override
    public void paintComponent(Graphics g) {
        drawGrid(g);
        drawContent(g);
        g.setColor(Color.red);
        g.drawString("Iter: " + grille.getIterationNbr() + " Cells: " + grille.getCellCount() + "\n", getX() + 5, getY() + 35);
        if (dispInt.isSelected()) {
            g.setColor(Color.BLACK);
            for (int i = 0; i < grille.getGrilleForDraw(false).length; i++) {
                g.drawString("" + grille.getGrilleForDraw(false)[i], getX() - 5, (i * 16) + (getY() + 70));
            }
        }
        fill();
        if (dispGraf.isSelected()) {
            displayGraf(g);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == reset) {
            //drawGrid(g);
            grille.reset();
            
            graf=new int[getWidth()/grafW];
            
        } else if (e.getSource() == iteration) {
            
            grille.iteration();

            
        }else if (e.getSource() == toString) {
            
            System.out.println(grille);
        }
        if(drawingRectangle.contains(e.getPoint())){
                int posinCellX = (e.getPoint().x-drawingRectangle.x)/cellRenderSize;

                System.out.println(posinCellX);
            System.out.println("inside");
        }
        label.setText(e.getPoint().toString());
        getParent().repaint();
    }
// <editor-fold defaultstate="collapsed" desc="MouseListener">     

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(new java.awt.Cursor(1));

        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(new java.awt.Cursor(0));

    }// </editor-fold>

    private void drawGrid(Graphics g) {

        g.drawRect(drawingRectangle.x, drawingRectangle.y, drawingRectangle.width, drawingRectangle.height);
        for (int i = 0; i < grille.getGrilleSize(); i++) {
            g.drawLine(drawingRectangle.x, drawingRectangle.y + (i * cellRenderSize), drawingRectangle.x + drawingRectangle.width, drawingRectangle.y + (i * cellRenderSize));
        }
        for (int i = 0; i < grille.getGrilleSize(); i++) {
            g.drawLine(drawingRectangle.x + (i * cellRenderSize), drawingRectangle.y, drawingRectangle.x + (i * cellRenderSize), drawingRectangle.y + drawingRectangle.height);
        }

    }

    private void createRectangle() {
        int size = grille.getGrilleSize();
        int posX = getWidth() - (size * cellRenderSize);
        posX >>= 1;//*0.5
        
        int posY;
        posY = getHeight() - (size * cellRenderSize);
        posY >>= 1;
        this.drawingRectangle.setBounds(posX, posY, size * cellRenderSize, size * cellRenderSize);
    }

    private void drawContent(Graphics g) {
        //Color c = g.getColor();
        
        int[] array = grille.getGrilleForDraw(invertDraw.isSelected());
        int size = grille.getGrilleSize();
        for (int yy = 0; yy < size; yy++) {
            for (int xx = 0; xx < size; xx++) {
                int mask = 1 << xx;
                int res = array[yy] & mask;
                if (res != 0) {//=1
                    g.setColor(Color.GRAY);
                    g.fillOval(drawingRectangle.x + (xx * cellRenderSize), drawingRectangle.y + (yy * cellRenderSize), cellRenderSize, cellRenderSize);
                }
            }
        }
    }

    private void displayGraf(Graphics g) {
        g.setColor(grafColor);
        for(int i = 0;i<graf.length;i++){
            g.fillRect((0+i)*grafW, getY()+572, grafW, -graf[i]);
        }
        
        
    }

    private void fill() {
        graf[grille.getIterationNbr()%graf.length]=grille.getCellCount();
    }



}
