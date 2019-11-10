package command;

import common.DukeException;
import logic.command.Command;
import logic.command.CommandOutput;
import logic.parser.edit.EditTaskDateTimeParser;
import model.Model;
import model.ModelController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author JasonChanWQ

public class EditTaskDateTimeCommandTest {

    @Test
    public void editTaskDateTimeCommand_indexNotInTaskList_throwsException() throws DukeException {
        Model model = new ModelController();
        for (int i = model.getTaskListSize() - 1; i >= 0; i--)  {
            model.deleteTask(i);
        }
        model.addTask("Create event poster");
        Command command = EditTaskDateTimeParser.parseEditTaskDateTime("5 /to 10/10/2020 1010");
        assertThrows(DukeException.class, () -> command.execute(model));
    }

    @Test
    public void editTaskDateTimeCommand_negativeIndex_throwsException() throws DukeException {
        Model model = new ModelController();
        for (int i = model.getTaskListSize() - 1; i >= 0; i--)  {
            model.deleteTask(i);
        }
        model.addTask("Create event poster");
        Command command = EditTaskDateTimeParser.parseEditTaskDateTime("-1 /to 10/10/2020 1010");
        assertThrows(DukeException.class, () -> command.execute(model));
    }

    @Test
    public void editTaskDateTimeCommand_dateTimeBehindCurrentTime_throwsException() throws DukeException {
        Model model = new ModelController();
        for (int i = model.getTaskListSize() - 1; i >= 0; i--)  {
            model.deleteTask(i);
        }
        model.addTask("Create event poster");
        Command command = EditTaskDateTimeParser.parseEditTaskDateTime("1 /to 10/10/1920 1010");
        assertThrows(DukeException.class, () -> command.execute(model));
    }

    @Test
    public void editTaskDateTimeCommand_success() throws DukeException {
        Model model = new ModelController();
        for (int i = model.getTaskListSize() - 1; i >= 0; i--)  {
            model.deleteTask(i);
        }
        //model.getTaskList().clear();
        model.addTask("Create event poster");
        Command command = EditTaskDateTimeParser.parseEditTaskDateTime("1 /to 10/10/2020 1010");
        CommandOutput out = command.execute(model);
        CommandOutput expected = new CommandOutput(
                "The deadline has been changed to: Sat Oct 10 10:10:00 SGT 2020", false);
        //assertTrue(model.getTaskDateTimeByIdOnList(1).toString().equals("Sat Oct 10 10:10:00 SGT 2020"));
        assertTrue(out instanceof CommandOutput);
        assertEquals(expected.isExit(), out.isExit());
        assertEquals(expected.getOutputToUser(), out.getOutputToUser());
        assertEquals(expected, out);
        //assertEquals("The deadline has been changed to: Sat Oct 10 10:10:00 SGT 2020",
        //        out.getOutputToUser());
    }

}
