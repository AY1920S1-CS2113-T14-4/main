package logic.command.edit;

import common.DukeException;
import logic.command.Command;
import logic.command.CommandOutput;
import model.Model;

public class EditTaskDesCommand extends Command {

    private static final String UPDATE_MSSAGE = "You have update the description of task: ";
    private static final String SET_MSSAGE = "You have set the description of task: ";
    private static final String NO_UPDATE_MSSAGE = "No update, they are the same.";
    public static final String INDEX_NOT_IN_MEMlIST_MESSAGE = "Index is not within the task list";
    private int taskIndexInList;
    private String des;

    public EditTaskDesCommand(int taskIndexInList, String des) {
        this.taskIndexInList = taskIndexInList;
        this.des = des;
    }


    //@@author yuyanglin28

    /**
     * This method is to edit task description
     * @param model Model interface
     * @return set message or update message
     * @throws DukeException throw exception when there is no update
     */
    @Override
    public CommandOutput execute(Model model) throws DukeException {
        if (!checkTaskIndex(taskIndexInList, model)) {
            return new CommandOutput(taskIndexInList + INDEX_NOT_IN_MEMlIST_MESSAGE);
        } else {
            String oldDes = model.updateTaskDes(taskIndexInList - 1, des);
            String taskName = model.getTaskNameById(taskIndexInList - 1);
            if (oldDes == null) {
                model.save();
                return new CommandOutput(SET_MSSAGE + "[" + taskName + "]"
                        + " to " + "[[" + des + "]]");
            } else if (oldDes.equals(des)) {
                throw new DukeException(NO_UPDATE_MSSAGE);
            } else {
                model.save();
                return new CommandOutput(UPDATE_MSSAGE + "[" + taskName + "]"
                        + " from " + "[[" + oldDes + "]]" + " to " + "[[" + des + "]]");
            }
        }

    }
}
