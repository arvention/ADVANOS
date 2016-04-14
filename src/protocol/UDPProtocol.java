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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Game;
import model.UDPGame;
import threadpool.ThreadPool;

/**
 *
 * @author Jet
 */
public class UDPProtocol extends Thread{
    
    private ThreadPool pool;
    private int port;
    private int server_id;
    private DatagramSocket socket;
    private boolean life;
    private InetAddress all_address;
    private int all_port;
    public static AtomicInteger num_clients = new AtomicInteger(0);
    private UDPGame game;
    
    public UDPProtocol(int worker, int tasks,int port,int id,UDPGame game) throws SocketException, UnknownHostException{
        this.game= game;
        this.port = port;
        this.server_id = id;
        byte[] b = new byte[1024*5];
        this.life = true;
        this.all_address = InetAddress.getByName("224.2.2.3");
        this.all_port = 8889;
        socket = new DatagramSocket(this.port);
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
                //System.out.println("Receiving");
                this.socket.receive(packet);
                //System.out.println("Received!");
                h = new Hand(packet.getData(), packet.getAddress(),packet.getPort(),this.server_id,game,this);
                pool.putTask(h);
                packet.setData(new byte[1024*1]);
                
            } catch (IOException ex) {
                Logger.getLogger(UDPProtocol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    public synchronized void send(DatagramPacket p) throws IOException{
        this.socket.send(p);
    }
    
    public void broadcast(byte[] b) throws IOException{
        DatagramPacket outPacket = new DatagramPacket(b, b.length, this.all_address, this.all_port);
        this.socket.send(outPacket);
    }
    
    
    
}
