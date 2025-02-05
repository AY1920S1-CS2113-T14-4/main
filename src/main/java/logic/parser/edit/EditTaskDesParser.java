package logic.parser.edit;

import common.DukeException;
import logic.command.Command;
import logic.command.edit.EditMemberBioCommand;
import logic.command.edit.EditTaskDesCommand;
import logic.parser.ArgumentTokenizer;

import java.text.ParseException;
import java.util.HashMap;

public class EditTaskDesParser {

    private static final String CHANGE_NO_EMPTY = "Put description after /to";

    //@@author yuyanglin28
    /**
     * parse edit task description logic.command
     * @param partialCommand logic.command after des, [index] /to [new des]
     * @return edit task des logic.command
     * @throws DukeException throw exception when task index is empty or change content is empty
     */
    public static Command parseEditTaskDes(String partialCommand) throws DukeException {

        HashMap<String, String> argumentMultimap = ArgumentTokenizer.tokenize(partialCommand);
        String name = argumentMultimap.get("");
        String changeContent = argumentMultimap.get("/to");

        if (name.length() == 0) {
            throw new DukeException(EditTaskParser.INDEX_NO_EMPTY + "\n" + EditTaskParser.EDIT_USAGE);
        } else if (changeContent == null || changeContent.length() == 0) {
            throw new DukeException(CHANGE_NO_EMPTY + "\n" + EditTaskParser.EDIT_USAGE);
        } else {
            name = name.trim();
            try {
                int indexInList = Integer.parseInt(name);
                changeContent = changeContent.trim();
                return new EditTaskDesCommand(indexInList, changeContent);
            } catch (Exception e) {
                throw new DukeException(EditTaskParser.GET_INDEX_FAIL + "\n"
                        + EditTaskParser.EDIT_USAGE);
            }
        }
    }
}
