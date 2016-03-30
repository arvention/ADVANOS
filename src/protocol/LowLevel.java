/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol;

/**
 *
 * @author Jet
 */
public interface LowLevel {
    public void send(byte[] b);
    public byte[] receive();
}
