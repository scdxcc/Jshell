package cn.scdx.cc.jshell.command;

/**
 * @author scdxcc
 * @email scdxcc@163.com
 * @date 14-11-19
 */
public class CommandExecuteException extends RuntimeException {

    public CommandExecuteException(String message) {
        super(message);
    }

    public CommandExecuteException(Throwable throwable) {
        super(throwable);
    }

    public CommandExecuteException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
