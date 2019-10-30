package logic.parser.schedule;

import common.DukeException;
import logic.command.Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleCommandParser {

    private static final Pattern BASIC_ADD_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String TEAM = "team";
    private static final String MEMBER = "member";
    private static final String SCHEDULE_USAGE = "Usage: schedule [team/member] [all/todo] {member name}";

    //@@author yuyanglin28
    /**
     * parse the schedule command
     * @param partialCommand content after schedule
     * @return a schedule related command
     * @throws DukeException exception
     */
    public static Command parseScheduleCommand(String partialCommand) throws DukeException {

        final Matcher matcher = BASIC_ADD_COMMAND_FORMAT.matcher(partialCommand.trim());
        if (!matcher.matches()) {
            throw new DukeException("Message is invalid");
        }

        String scheduleType = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        scheduleType = scheduleType.trim();

        switch (scheduleType) {
        case TEAM:
            return ScheduleTeamParser.parseScheduleTeam(arguments);
        case MEMBER:
            return ScheduleMemberParser.parseScheduleMember(arguments);
        default:
            throw new DukeException(SCHEDULE_USAGE);
        }

    }
}
