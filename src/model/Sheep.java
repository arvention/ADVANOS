/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.PrintWriter;
import threadpool.Task;
/**
 *
 * @author Arces
 */
public class Sheep implements Task{
    private int x, y, id;
    private PrintWriter pw;
    
    public Sheep(int x, int y, int id,PrintWriter pw) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.pw = pw;
    }
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public void work() {
        pw.println(this.x+","+this.y);
    }
}
