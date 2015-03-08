package cn.scdx.cc.jshell.command;

import java.util.List;

/**
 * @author scdxcc
 * @email scdxcc@163.com
 * @date 14-11-19
 */
public class Command {
    private CommandType commandType;
    private List<String> argumentList;
    private String target;

    public Command(CommandType commandType, List<String> argumentList, String target) {
        this.commandType = commandType;
        this.argumentList = argumentList;
        this.target = target;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public List<String> getArgumentList() {
        return argumentList;
    }

    public void setArgumentList(List<String> argumentList) {
        this.argumentList = argumentList;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
