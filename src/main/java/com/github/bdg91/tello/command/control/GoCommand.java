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

package com.github.bdg91.tello.command.control;

import com.github.bdg91.tello.client.TelloClient;
import com.github.bdg91.tello.command.Command;
import com.github.bdg91.tello.util.Assert;

import java.io.IOException;

/**
 * Command to make the drone fly to position x, y, z by a specified speed.
 */
public class GoCommand implements Command {

    private static final String COMMAND = "go";
    private static final String SPACE = " ";

    private final TelloClient telloClient;
    private final int positionX;
    private final int positionY;
    private final int positionZ;
    private final int speed;

    /**
     * Creates a go command
     *
     * @param telloClient the tello client
     * @param positionX   the x position, minimum 20, maximum 500
     * @param positionY   the y position, minimum 20, maximum 500
     * @param positionZ   the z position, minimum 20, maximum 500
     * @param speed       the speed, minimum 10, maximum 100
     */
    public GoCommand(final TelloClient telloClient, final int positionX, final int positionY, final int positionZ, final int speed) {
        Assert.distance(positionX);
        Assert.distance(positionY);
        Assert.distance(positionZ);
        Assert.speed(speed);

        this.telloClient = telloClient;
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        this.speed = speed;
    }

    /**
     * Executes the go {@link Command}.
     *
     * @return 'ok' if everything is okay, 'error' otherwise
     * @throws IOException if sending the command or receiving the return value fails
     */
    public String execute() throws IOException {
        return telloClient.sendCommand(COMMAND + SPACE + positionX + SPACE + positionY + SPACE + positionZ + SPACE + speed);
    }


}
