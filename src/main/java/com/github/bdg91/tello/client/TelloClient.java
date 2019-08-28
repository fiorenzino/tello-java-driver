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

package com.github.bdg91.tello.client;

import java.io.IOException;
import java.net.*;

public class TelloClient {

    private static TelloClient instance;

    private static final String IP_ADDRESS = "192.168.10.1";
    private static final int PORT = 8889;

    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;

    private TelloClient() {
        try {
            datagramSocket = new DatagramSocket();
            inetAddress = InetAddress.getByName(IP_ADDRESS);
        } catch (SocketException | UnknownHostException exception) {
            // TODO Handle error gracefully.
            exception.printStackTrace();
        }
    }

    /**
     * Gets an instance of the TelloClient.
     *
     * @return a TelloClient
     */
    public static TelloClient getInstance(boolean force) {
        if (force || instance == null) {
            instance = new TelloClient();
        }
        return instance;
    }

    /**
     * Sends a command to the socket.
     *
     * @param message the message to send
     * @return the response from the socket
     * @throws IOException if the sending or receiving of the command fails
     */
    public String sendCommand(final String message) throws IOException {
        final byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, inetAddress, PORT);
        datagramSocket.send(packet);
        packet = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    public void close() {
        if (datagramSocket != null && !datagramSocket.isClosed()) {
            datagramSocket.close();
        }
    }

}
