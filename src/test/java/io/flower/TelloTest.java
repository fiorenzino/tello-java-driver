package io.flower;

import com.github.bdg91.tello.client.TelloClient;
import com.github.bdg91.tello.command.Command;
import com.github.bdg91.tello.command.control.CommandCommand;
import com.github.bdg91.tello.command.control.ForwardCommand;
import com.github.bdg91.tello.command.control.LandCommand;
import com.github.bdg91.tello.command.control.TakeOffCommand;

import java.util.Arrays;
import java.util.List;

public class TelloTest {
    public static void main(String[] args) {
        TelloClient client = TelloClient.getInstance(false);
        Command commandCommand = new CommandCommand(client);
        Command takeOffCommand = new TakeOffCommand(client);
        Command forwardCommand = new ForwardCommand(client, 200);
        Command landCommand = new LandCommand(client);

        List<Command> flightPlan = Arrays.asList(commandCommand, takeOffCommand, forwardCommand, landCommand);

        flightPlan.forEach(command -> {
            try {
                command.execute();
                Thread.sleep(5000);
            } catch (Exception exception) {
                // Exception handling
            }
        });
    }

}
