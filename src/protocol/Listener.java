/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jet
 */
public class Listener extends Thread{
    
    
    private PrintWriter pw;
    private BufferedReader reader;
    private Socket soc;
    
    public Listener(Socket soc) throws IOException{
        this.soc = soc;
        this.reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
    }
    
    public void run(){
        String line= "";
        String[] list;
        int new_x;
        int new_y;
        
        try {
            while((line= this.reader.readLine()) != null){
                
                list = line.split(",");
                new_x = Integer.parseInt(list[0]);
                new_y = Integer.parseInt(list[0]);
                //update sheep
                    
                //updateseep
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
    }
    
 
}
