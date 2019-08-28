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
 * Command to make the drone rotate in a counter-clockwise direction by a specified amount of degrees.
 */
public class CcwCommand implements Command {

    private static final String COMMAND = "ccw";
    private static final String SPACE = " ";

    private final TelloClient telloClient;
    private final int degrees;

    /**
     * Creates a ccw command.
     *
     * @param telloClient  the tello client
     * @param degrees      the amount of degrees, minimum 1, maximum 3600
     */
    public CcwCommand(final TelloClient telloClient, final int degrees) {
        Assert.degrees(degrees);
        this.telloClient = telloClient;
        this.degrees = degrees;
    }

    /**
     * Executes the ccw {@link Command}.
     *
     * @return 'ok' if everything is okay, 'error' otherwise
     * @throws IOException if sending the command or receiving the return value fails
     */
    public String execute() throws IOException {
        return telloClient.sendCommand(COMMAND + SPACE + degrees);
    }


}
