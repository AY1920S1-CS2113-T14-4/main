package logic;

import logic.command.Command;
import logic.command.CommandOutput;
import logic.parser.NewParser;
import model.Model;
import model.Task;
import common.DukeException;
import storage.Storage;

import java.util.ArrayList;

public class LogicController {

    protected ArrayList<Task> tasks;
    protected Storage storage;
    protected Model model;


    public LogicController(Model model) {
        this.model = model;
    }

    //@@ author JustinChia1997
    /**
     * Runs the necessary command.
     * */
    public CommandOutput execute(String fullCommand) throws DukeException {
        try {
            CommandOutput commandResult;
            Command command = NewParser.parseCommand(fullCommand);
            commandResult = command.execute(model);
            return commandResult;
        } catch (DukeException e) {
            throw e;
        }
    }
}
