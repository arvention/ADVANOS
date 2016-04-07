/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Game;
import model.Sheep;
import threadpool.Task;
import threadpool.Worker;
/**
 *
 * @author Jet
 */
public class Hand implements Task {

    private byte[] d;
    private InetAddress ad;
    private int port;
    
    public Hand(byte[] data, InetAddress ad,int port){
        this.d = data;
        this.ad = ad;
        this.port = port;
    }
    
    @Override
    public void work() {
        int tempx,tempy;
        ByteBuffer bf = ByteBuffer.wrap(this.d);
        long mil = bf.getLong();
        Sheep sheep;
        if(mil == -1){
            //create sheep
            int id = Game.gameInstance.generateID();
            int initialx=1;
            int initialy=1;
            ByteBuffer to_send = ByteBuffer.allocate(200);
            to_send.putInt(id);
            to_send.putInt(initialx);
            to_send.putInt(initialy);
            byte[] buffer = to_send.array();
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length,this.ad,this.port); 
            try {
                UDPProtocol.send(packet);            
                sheep = new Sheep(initialx,initialy,id,mil);
                Game.gameInstance.addSheep(sheep);
            } catch (IOException ex) {
                Logger.getLogger(Hand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            int id = bf.getInt();
            Sheep s = Game.gameInstance.getSheeps().get(id);
            //if(s.mil < mil){
            tempx = bf.getInt() + s.getX();
            tempy = bf.getInt() + s.getY();
            
            if(tempx < 60 && tempx>= 0 && tempy < 60 && tempy >=0 ){
                s.setX(tempx);
                s.setY(tempy);
            }
            
            //} 
                
        }
            
    }
    
    
    
   
}
