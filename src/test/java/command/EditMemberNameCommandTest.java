package command;

import common.DukeException;
import logic.command.Command;
import logic.command.CommandOutput;
import logic.parser.edit.EditMemberNameParser;
import model.Model;
import model.ModelController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditMemberNameCommandTest {

    @Test
    public void editMemberNameCommand_inputNameNotInMemberList_throwsException() throws DukeException {
        Model model = new ModelController();
        for (int i = model.getMemberListSize() - 1; i >= 0; i--)  {
            model.deleteMember(i);
        }
        model.addMember("John");
        Command command = EditMemberNameParser.parseEditMemberName("Jack /to Jacky");
        assertThrows(DukeException.class, () -> command.execute(model));
    }

    @Test
    public void editMemberNameCommand_inputNameAlreadyInMemberList_throwsException() throws DukeException {
        Model model = new ModelController();
        for (int i = model.getMemberListSize() - 1; i >= 0; i--)  {
            model.deleteMember(i);
        }
        model.addMember("John");
        model.addMember("Jack");
        Command command = EditMemberNameParser.parseEditMemberName("Jack /to John");
        assertThrows(DukeException.class, () -> command.execute(model));
    }

    @Test
    public void editMemberNameCommandSuccess() throws DukeException {
        Model model = new ModelController();
        for (int i = model.getMemberListSize() - 1; i >= 0; i--)  {
            model.deleteMember(i);
        }
        model.addMember("John");
        model.addMember("Jack");
        Command command = EditMemberNameParser.parseEditMemberName("Jack /to Jason");
        CommandOutput out = command.execute(model);
        assertThrows(DukeException.class, () -> command.execute(model));
        assertEquals("Jack has been renamed to: Jason", out.getOutputToUser());
    }
}
