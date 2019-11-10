package logic.command.list;

import logic.command.Command;
import logic.command.CommandOutput;
import model.Model;

public class ListTasksPicNumCommand extends Command {

    private static final String SUCCESS_MESSAGE = "Here are all the tasks in order of num of PICs:";

    //@@author yuyanglin28
    /**
     * This method is to list all tasks in order of number of PICs (person in charge)
     * @param model Model interface
     * @return a sorted task list in order of number of PICs
     */
    @Override
    public CommandOutput execute(Model model) {
        String tasks = model.tasksAllInorderPicNum();
        if (tasks.equals("")) {
            return new CommandOutput(ListTasksCommand.EMPTY_TASKS_LIST);
        } else {
            return new CommandOutput(SUCCESS_MESSAGE + tasks);
        }
    }
}
