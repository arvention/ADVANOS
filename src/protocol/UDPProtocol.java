/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Game;
import threadpool.ThreadPool;

/**
 *
 * @author Jet
 */
public class UDPProtocol extends Thread{
    
    private ThreadPool pool;
    private final int port =1108;
    private static DatagramSocket socket;
    private boolean life;
    private InetAddress all_address;
    private int all_port;
    
    public UDPProtocol(int worker, int tasks) throws SocketException, UnknownHostException{
        byte[] b = new byte[1024*5];
        this.life = true;
        this.all_address = InetAddress.getByName("224.2.2.3");
        this.all_port = 8889;
        
        UDPProtocol.socket = new DatagramSocket(this.port);
        this.pool = new ThreadPool(worker, tasks);
        this.pool.start();
    } 
         
     
    public void run(){
        System.out.println("Server running");
        byte[] buffer = new byte[1024*1];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        Hand h;
        while(this.life){
            try {
                System.out.println("Receiving");
                UDPProtocol.socket.receive(packet);
                System.out.println("Received!");
                h = new Hand(packet.getData(), packet.getAddress(),packet.getPort());
                pool.putTask(h);
                packet.setData(new byte[1024*1]);
                
            } catch (IOException ex) {
                Logger.getLogger(UDPProtocol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    public static synchronized void send(DatagramPacket p) throws IOException{
        UDPProtocol.socket.send(p);
    }
    
    public void broadcast(byte[] b) throws IOException{
        DatagramPacket outPacket = new DatagramPacket(b, b.length, this.all_address, this.all_port);
        UDPProtocol.socket.send(outPacket);
    }
    
    
    
}
