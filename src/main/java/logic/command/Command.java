package logic.command;


import model.Model;
import common.DukeException;

//@@author JustinChia1997
/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {


    /**
     * Executes the command and returns the output message. Every command MUST be passed a model
     *
     * @return feedback message of the operation result for display
     */
    public abstract CommandOutput execute(Model model) throws DukeException;


}