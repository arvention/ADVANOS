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
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jet
 */
public class UpdLow implements LowLevel{
    
    private DatagramSocket server;
    private byte[] receiveData = new byte[1024];
    private byte[] sendData = new byte[1024];
    public InetAddress lastReceived;
    
    
    public UpdLow(int port){
        try {
            this.server = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(UpdLow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void send(byte[] b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] receive() {
        DatagramPacket dp = new DatagramPacket(this.receiveData,this.receiveData.length);
        try {
            this.server.receive(dp);
            this.lastReceived = dp.getAddress();
        } catch (IOException ex) {
            Logger.getLogger(UpdLow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dp.getData();
    }
    

    
}
