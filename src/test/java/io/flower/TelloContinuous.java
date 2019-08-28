package io.flower;

import com.github.bdg91.tello.client.TelloClient;
import com.github.bdg91.tello.command.control.*;
import com.github.bdg91.tello.command.model.Cmd;
import com.github.bdg91.tello.command.model.FlipDirection;
import com.github.bdg91.tello.command.read.*;
import com.github.bdg91.tello.command.set.SetSpeedCommand;
import com.github.bdg91.tello.server.TelloServer;

import java.util.Scanner;

public class TelloContinuous {

    public static void main(String[] args) {
        TelloClient client = null;
        TelloServer server = null;

        int PAUSE_TIME = 1000;
        String MIN_10 = "10";
        String MIN_20 = "20";

        String[] withOptions;
        String line;
        Scanner command = new Scanner(System.in);
        System.out.println("Enter command: ");
        boolean running = true;
        while (running) {
            String cmd;
            String options = null;
            line = command.nextLine().trim();
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            withOptions = line.split(" ");
            cmd = withOptions[0];
            if (withOptions.length == 2) {
                options = withOptions[1];
            }
            Cmd XXX;
            try {
                XXX = Cmd.valueOf(cmd);
                switch (XXX) {
                    case restart:
                        client = TelloClient.getInstance(true);
                        new CommandCommand(client).direct();
                        Thread.sleep(PAUSE_TIME);
                        if (!Cmd.startAll.equals(XXX)) {
                            break;
                        }
                    case startAll:
                    case start:
                        client = TelloClient.getInstance(false);
                        new CommandCommand(client).direct();
                        Thread.sleep(PAUSE_TIME);
                        if (!Cmd.startAll.equals(XXX)) {
                            break;
                        }
                    case startLogger:
                        client = TelloClient.getInstance(false);
                        server = new TelloServer();
                        server.start();
                        new StreamOnCommand(client).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;
                    case showLogs:
                        server.show();
                        break;
                    case hideLogs:
                        server.hide();
                        break;
                    case decolla:
                    case takeoff:
                        new TakeOffCommand(client).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;
                    case avanti:
                    case forward:
                        if (options == null) {
                            options = MIN_20;
                        }
                        new ForwardCommand(client, Integer.valueOf(options)).direct();
                        break;
                    case indietro:
                    case back:
                        if (options == null) {
                            options = MIN_20;
                        }
                        new BackCommand(client, Integer.valueOf(options)).direct();
                        break;
                    case su:
                    case up:
                        if (options == null) {
                            options = MIN_20;
                        }
                        new UpCommand(client, Integer.valueOf(options)).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;
                    case giu:
                    case down:
                        if (options == null) {
                            options = MIN_20;
                        }
                        new DownCommand(client, Integer.valueOf(options)).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;
                    case sx:
                    case left:
                        if (options == null) {
                            options = MIN_20;
                        }
                        new LeftCommand(client, Integer.valueOf(options)).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;
                    case dx:
                    case right:
                        if (options == null) {
                            options = MIN_20;
                        }
                        new RightCommand(client, Integer.valueOf(options)).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;
                    case atterra:
                    case land:
                        new LandCommand(client).direct();
                        break;
                    case gira:
                    case cw:
                        if (options == null) {
                            options = MIN_20;
                        }
                        new CwCommand(client, Integer.valueOf(options)).direct();
                        break;
                    case go:
                        System.out.println("go cmd " + options);
                        String x = MIN_20, y = MIN_20, z = MIN_20, speed = MIN_10;
                        if (withOptions.length > 2) x = withOptions[1];
                        if (withOptions.length > 3) y = withOptions[2];
                        if (withOptions.length > 4) z = withOptions[3];
                        if (withOptions.length > 5) speed = withOptions[4];
                        new GoCommand(client,
                                Integer.valueOf(x),
                                Integer.valueOf(y),
                                Integer.valueOf(z),
                                Integer.valueOf(speed)).direct();
                        break;
                    case ccw:
                        if (options == null) {
                            options = MIN_20;
                        }
                        new CcwCommand(client, Integer.valueOf(options)).direct();
                        break;
                    case battery:
                        new ReadBatteryCommand(client).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;
                    case attitude:
                        new ReadAttitudeCommand(client).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;
                    case acceleration:
                        new ReadAccelerationCommand(client).direct();
                        Thread.sleep(PAUSE_TIME);
                    case baro:
                        new ReadBaroCommand(client).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;

                    case flip:
                    case flipl:
                        new FlipCommand(client, FlipDirection.LEFT).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;
                    case flipb:
                        new FlipCommand(client, FlipDirection.BACK).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;
                    case flipf:
                        new FlipCommand(client, FlipDirection.FORWARD).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;
                    case flipr:
                        new FlipCommand(client, FlipDirection.RIGHT).direct();
                        Thread.sleep(PAUSE_TIME);
                        break;


                    case speed:
                        new ReadSpeedCommand(client).direct();
                        break;
                    case sspeed:
                        if (options == null) {
                            options = MIN_10;
                        }
                        new SetSpeedCommand(client, Integer.valueOf(options)).direct();
                        break;

                    case tof:
                        new ReadTofCommand(client).direct();
                        break;

                    case temp:
                        new ReadTempCommand(client).direct();
                        break;
                    case time:
                        new ReadTimeCommand(client).direct();
                    case wifi:
                        new ReadWifiCommand(client).direct();
                        break;
                    case height:
                        new ReadHeightCommand(client).direct();
                        break;
                    case command:
                        new CommandCommand(client).direct();
                        break;

                    case streamon:
                        new StreamOnCommand(client).direct();
                        break;
                    case emergency:
                        new EmergencyCommand(client).direct();
                        break;
                    case streamoff:
                        new StreamOffCommand(client).direct();
                        break;

                    case stopAll:
                    case stop:
                        if (client != null) {
                            client.close();
                            client = null;
                        }
                        if (!Cmd.stopAll.equals(XXX)) {
                            break;
                        }
                    case stopLogger:
                        if (server != null) {
                            server.close();
                            server = null;
                        }
                        break;

                    case exit:
                        running = false;
                        System.out.println("Application is Closing");
                        break;
                    case help:
                        Cmd.help();
                        break;
                    default:
                        System.out.println("Command not recognized!");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        command.close();
    }

}
