package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

import static com.github.javarushcommunity.jrtb.command.CommandName.*;

public class CommandContainer {
    private final Command unknownCommand;
    private final ImmutableMap<String, Command> commandMap;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();


//        commandMap.put(START.getCommandName() ,new StartCommand(sendBotMessageService));
//        commandMap.put(STOP.getCommandName() ,new StopCommand(sendBotMessageService));
//        commandMap.put(HELP.getCommandName() ,new HelpCommand(sendBotMessageService));
//        commandMap.put(NO.getCommandName() ,new NoCommand(sendBotMessageService));

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }   
}
