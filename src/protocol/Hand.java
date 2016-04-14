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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Game;
import model.Sheep;
import model.UDPGame;
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
    private int server_id;
    private UDPGame game;
    private UDPProtocol up;
    private AtomicInteger num_clients;
    
    public Hand(byte[] data, InetAddress ad,int port, int server_id,UDPGame game,UDPProtocol up,AtomicInteger clients){
        this.up= up;
        this.game = game;
        this.d = data;
        this.ad = ad;
        this.port = port;
        this.server_id = server_id;
        this.num_clients = clients;
        
    }
    
    @Override
    public void work() {
        int tempx,tempy;
        ByteBuffer bf = ByteBuffer.wrap(this.d);
        long mil = bf.getLong();
        Sheep sheep;
        if(mil == -1){
            //create sheep
            int gen_id;
            int initialx=1;
            int initialy=1;
            ByteBuffer to_send = ByteBuffer.allocate(200);
                    
            gen_id = game.createSheep(initialx, initialy);
            //to_send.putInt(server_id);
            to_send.putInt(gen_id);
            to_send.putInt(initialx);
            to_send.putInt(initialy);
            byte[] buffer = to_send.array();
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length,this.ad,this.port); 
            try {
                this.up.send(packet);            
                
                this.num_clients.incrementAndGet();
                System.out.println(this.server_id + ": number of clients:" + this.num_clients);
            } catch (IOException ex) {
                Logger.getLogger(Hand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(mil == -2){
            try {
                ByteBuffer to_send = ByteBuffer.allocate(200);
                to_send.putInt(server_id);
                byte[] buffer = to_send.array();
                DatagramPacket packet = new DatagramPacket(buffer,buffer.length,this.ad,this.port);    
                this.up.send(packet);
            } catch (IOException ex) {
                Logger.getLogger(Hand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            int id = bf.getInt();
            //System.out.println(id);
            Sheep s = this.game.getSheeps().get(id-1);
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
