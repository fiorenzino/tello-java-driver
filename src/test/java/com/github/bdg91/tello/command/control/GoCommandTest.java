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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoCommandTest {

    @Mock
    private TelloClient telloClient;

    private static final String COMMAND = "go";
    private static final String SPACE = " ";
    private static final int POSITION_X = 50;
    private static final int POSITION_Y = 50;
    private static final int POSITION_Z = 50;
    private static final int SPEED = 50;

    private Command command;

    @Before
    public void setUp() {
        command = new GoCommand(telloClient, POSITION_X, POSITION_Y, POSITION_Z, SPEED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_position_x_to_low() {
        command = new GoCommand(telloClient, 19, POSITION_Y, POSITION_Z, SPEED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_position_x_to_high() {
        command = new GoCommand(telloClient, 501, POSITION_Y, POSITION_Z, SPEED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_position_y_to_low() {
        command = new GoCommand(telloClient, POSITION_X, 19, POSITION_Z, SPEED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_position_y_to_high() {
        command = new GoCommand(telloClient, POSITION_X, 501, POSITION_Z, SPEED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_position_z_to_low() {
        command = new GoCommand(telloClient, POSITION_X, POSITION_Y, 19, SPEED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_position_z_to_high() {
        command = new GoCommand(telloClient, POSITION_X, POSITION_Y, 501, SPEED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_position_speed_to_low() {
        command = new GoCommand(telloClient, POSITION_X, POSITION_Y, POSITION_Z, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_position_speed_to_high() {
        command = new GoCommand(telloClient, POSITION_X, POSITION_Y, POSITION_Z, 101);
    }

    @Test(expected = IOException.class)
    public void testExecute_io_exception() throws Exception {
        when(telloClient.sendCommand(COMMAND + SPACE + POSITION_X + SPACE + POSITION_Y + SPACE + POSITION_Z + SPACE + SPEED)).thenThrow(IOException.class);

        command.execute();
    }

    @Test
    public void testExecute() throws IOException {
        command.execute();

        verify(telloClient).sendCommand(COMMAND + SPACE + POSITION_X + SPACE + POSITION_Y + SPACE + POSITION_Z + SPACE + SPEED);
    }
}
