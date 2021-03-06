/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Game;
import model.Sheep;

/**
 *
 * @author Jet
 */
public class Listener extends Thread{
    
    
    private PrintWriter pw;
    private BufferedReader reader;
    private Socket soc;
   
    private Sheep sheep;
    
    public Listener(Socket soc) throws IOException{
        this.soc = soc;
        this.reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        
        this.pw = new PrintWriter(new 
                BufferedWriter(
                    new OutputStreamWriter(soc.getOutputStream())), true);
        int id = Game.gameInstance.generateID();
        int initialx=1;
        int initialy=1;
        this.sheep = new Sheep(initialx,initialy,id,pw);
        Game.gameInstance.addSheep(sheep);
        this.pw.println(id+","+initialx+","+initialy);
    }
    
    public void run(){
        String line= "";
        String[] list;
        int new_x;
        int new_y;
        int update_x;
        int update_y;
        
        try {
            while((line= this.reader.readLine()) != null){

                list = line.split(",");
                new_x = Integer.parseInt(list[0]);
                new_y = Integer.parseInt(list[1]);
                update_x = this.sheep.getX() + new_x;
                update_y = this.sheep.getY() + new_y;
                //update sheep
                if(update_x < 60 && update_x>= 0 && update_y < 60 && update_y >=0 ){
                    this.sheep.setX(this.sheep.getX() + new_x);
                    this.sheep.setY(this.sheep.getY() + new_y);
                }
                //updateseep
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Game.gameInstance.remove(this.sheep);
        }   
        
    }
    
 
}
