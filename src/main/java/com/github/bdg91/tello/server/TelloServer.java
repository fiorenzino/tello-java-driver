/*
 * MIT License
 *
 * Copyright © 2019 Bas de Groot
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.bdg91.tello.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TelloServer extends Thread {

    private DatagramSocket datagramSocket;
    private int port = 8890;
    private boolean running = false;
    private boolean showingLogs = false;
    private byte[] buf = new byte[256];


    public TelloServer() {
        try {
            datagramSocket = new DatagramSocket(this.port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        running = true;
        String msg;

        while (running) {
            if (showingLogs)
                System.out.println("log server is waiting for logs...");
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                datagramSocket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            msg = new String(packet.getData(), 0, packet.getLength());
            if (showingLogs)
                System.out.println("Message from " + packet.getAddress().getHostAddress() + ": " + msg);
        }
        if (datagramSocket != null && !datagramSocket.isClosed()) {
            datagramSocket.close();
        }
        System.out.println("log server is closed...");

    }


    public void close() {
        this.running = false;
        System.out.println("Machine is stopping.");
    }

    public void show() {
        this.showingLogs = true;
        System.out.println("show logs: ok");
    }

    public void hide() {
        this.showingLogs = false;
        System.out.println("hide logs: ok");
    }
}
