package cn.scdx.cc.jshell;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cn.scdx.cc.jshell.command.Command;
import cn.scdx.cc.jshell.command.executor.CommandExecutor;
import cn.scdx.cc.jshell.command.executor.GrepCommandExecutor;
import cn.scdx.cc.jshell.command.executor.WcCommandExecutor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Closer;
import cn.scdx.cc.jshell.command.CommandType;
import cn.scdx.cc.jshell.command.executor.CatCommandExecutor;

/**
 * @author scdxcc
 * @email scdxcc@163.com
 * @date 14-11-19
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static Splitter pipeSplitter = Splitter.on("|").trimResults().omitEmptyStrings();
    private static Splitter commandElementSplitter = Splitter.on(CharMatcher.WHITESPACE).trimResults()
            .omitEmptyStrings();

    public static void main(String[] args) {
        try {
            String commandLine = new Scanner(System.in).nextLine();
            Map<CommandType, CommandExecutor> commandExecutorMap = initCommandExecutorMap();
            List<Command> commandList = parseCommand(commandLine);
            CloseableIterator<String> result = execute(commandExecutorMap, commandList);
            outputConsole(System.out, result);
        } catch (Exception e) {
            LOGGER.error("an error occurred during execute command", e);
        }
    }

    private static Map<CommandType, CommandExecutor> initCommandExecutorMap() {
        Map<CommandType, CommandExecutor> CommandExecutorMap = Maps.newHashMap();
        CommandExecutorMap.put(CommandType.CAT, new CatCommandExecutor());
        CommandExecutorMap.put(CommandType.GREP, new GrepCommandExecutor());
        CommandExecutorMap.put(CommandType.WC, new WcCommandExecutor());
        return CommandExecutorMap;
    }

    private static List<Command> parseCommand(String commandLine) {
        List<String> commandStringList = pipeSplitter.splitToList(commandLine);
        Preconditions.checkState(commandStringList.size() > 0, "there are not command");
        return Lists.transform(commandStringList, new Function<String, Command>() {
            @Override
            public Command apply(String commandString) {
                List<String> elementList = commandElementSplitter.splitToList(commandString);
                Preconditions.checkState(elementList.size() > 0, "incorrect command format");
                CommandType commandType = CommandType.valueOf(StringUtils.upperCase(elementList.get(0)));
                Preconditions.checkNotNull(commandType, "command type does not exist");
                List<String> argumentList = elementList.subList(1, elementList.size());
                String target = elementList.get(elementList.size() - 1);
                return new Command(commandType, argumentList, target);
            }
        });
    }

    private static CloseableIterator<String> execute(Map<CommandType, CommandExecutor> commandExecutorMap,
            List<Command> commandList) {
        CloseableIterator<String> result = null;
        for (Command command : commandList) {
            result = commandExecutorMap.get(command.getCommandType()).pipeExecute(command, result);
        }
        return result;
    }

    private static void outputConsole(PrintStream target, CloseableIterator<String> result) throws IOException {
        Closer closer = Closer.create();
        try {
            closer.register(result);
            while (result.hasNext()) {
                target.println(result.next());
            }
        } finally {
            closer.close();
        }
    }
}
