package logic.parser.schedule;

import common.DukeException;
import logic.command.Command;
import logic.command.ScheduleMemberAllCommand;
import logic.command.ScheduleMemberTodoCommand;

public class ScheduleMemberTodoParser {

    private static final String SCHEDULE_USAGE = "Usage: schedule [team/member] [all/todo] {member name}";

    public static Command parseScheduleMemberTodo(String argument) throws DukeException {
        if (argument.equals("")) {
            throw new DukeException(SCHEDULE_USAGE);
        } else {
            String memberName = argument.trim();
            return new ScheduleMemberTodoCommand(memberName);
        }
    }
}
