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

package com.github.bdg91.tello.util;

public class Assert {

    private Assert() {
        // Hide constructor
    }

    /**
     * Checks if the specified distance is valid.
     *
     * @param distanceInCm the distance in cm
     */
    public static void distance(final int distanceInCm) {
        if (distanceInCm < 20 | distanceInCm > 500) {
            throw new IllegalArgumentException("The minimum allowed distance is 20, the maximum allowed distance is 500.");
        }
    }

    /**
     * Checks if the specified amount of degrees is valid.
     *
     * @param degrees the amount of degrees
     */
    public static void degrees(final int degrees) {
        if (degrees < 1 | degrees > 3600) {
            throw new IllegalArgumentException("The minimum amount of degrees is 1, the maximum amount of degrees is 3600.");
        }
    }

    /**
     * Checks if the specified speed is valid.
     *
     * @param speed the speed in cm/s
     */
    public static void speed(final int speed) {
        if (speed < 10 | speed > 100) {
            throw new IllegalArgumentException("The minimum speed is 1, the maximum speed is 100.");
        }
    }

}
