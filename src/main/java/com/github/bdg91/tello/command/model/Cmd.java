package com.github.bdg91.tello.command.model;

public enum Cmd {
    back, ccw, command, cw, down, emergency,

    // default FLIP => l
    flip,
    /*
        LEFT("l"),
        RIGHT("r"),
        FORWARD("f"),
        BACK("b");
     */
    flipl,
    flipr,
    flipf,
    flipb,
    forward, go, land, left, right, streamoff, streamon, takeoff, up,

    decolla, avanti, indietro, dx, sx, su, giu, gira, atterra,

    showLogs, hideLogs,

    //read status
    acceleration,
    attitude,
    baro,
    battery,
    height,
    speed,
    temp,
    time,
    tof,
    wifi,

    // set speed
    sspeed,


    //extra
    help, startAll, start, restart, startLogger, stop, stopLogger, stopAll, exit;


    public static void help() {
        System.out.println("HELP: ");
        for (Cmd cmdH : values()) {
            System.out.println(cmdH);
        }
        System.out.println("...........");
    }
}
