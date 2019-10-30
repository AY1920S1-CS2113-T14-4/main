package logic.parser.schedule;

import common.DukeException;
import logic.command.Command;
import logic.command.ScheduleMemberAllCommand;

public class ScheduleMemberAllParser {

    private static final String SCHEDULE_USAGE = "Usage: schedule [team/member] [all/todo] {member name}";


    //@@author yuyanglin28
    /**
     * parse schedule member all
     * @param argument content after all, member name
     * @return ScheduleMemberAllCommand
     * @throws DukeException exception
     */
    public static Command parseScheduleMemberAll(String argument) throws DukeException {
        if (argument.equals("")) {
            throw new DukeException(SCHEDULE_USAGE);
        } else {
            String memberName = argument.trim();
            return new ScheduleMemberAllCommand(memberName);
        }
    }
}
