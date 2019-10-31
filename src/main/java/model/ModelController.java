package model;

import com.google.gson.Gson;
import storage.Storage;
import common.DukeException;

import java.util.ArrayList;


public class ModelController implements Model {
    private TasksManager tasksManager;
    private MemberManager memberManager;
    private Gson gson;
    private Storage storage;

    /**
     * Handles model changes.
     */
    public ModelController() {
        //TODO change to loading from storage
        storage = new Storage();
        tasksManager = new TasksManager(storage.loadTasks());
        memberManager = new MemberManager(storage.loadMembers());
    }

    //@@author JustinChia1997

    /**
     * Loads the data from storage into memory
     */
    @Override
    public void load() {
        //TODO
    }

    /**
     * Saves the data into a persistent json object
     */
    @Override
    public void save() {
        storage.saveTasks(tasksManager.getTaskList());
        storage.saveMembers(memberManager.getMemberList());
    }

    //=================Task interfaces=============================================
    @Override
    public ArrayList<Task> getTaskList() {
        return tasksManager.getTaskList();
    }

    public int getTaskListSize() {
        return tasksManager.getTaskListSize();
    }

    public String getTaskNameById(int index) {
        return tasksManager.getTaskNameById(index);
    }

    @Override
    public TasksManager getTasksManager() {
        return tasksManager;
    }

    @Override
    public Task addTask(String name) throws DukeException {
        Task newTask = tasksManager.addTask(name);
        storage.saveTasks(tasksManager.getTaskList());
        storage.loadTasks();
        return newTask;
    }

    @Override
    public boolean hasTask(String name) throws DukeException {
        return tasksManager.hasTask(name);

    }

    @Override
    public boolean addTaskReqSkill(String taskName, String skillName) {
        return tasksManager.addReqSkill(taskName, skillName);
    }

    /**
     * javadoc please
     * */
    public String updateTaskDes(int index, String des) {
        String oldDes = tasksManager.getTaskDes(index);
        tasksManager.updateTaskDes(index, des);
        return oldDes;
    }


    public String getTasksByKeyword(String keyword) {
        return tasksManager.getTasksByKeyword(keyword);
    }

    public String scheduleTeamAll() {
        return tasksManager.scheduleTeamAll();
    }

    public String scheduleTeamTodo() {
        return tasksManager.scheduleTeamTodo();
    }


    //=================Member interfaces=============================================

    @Override
    public ArrayList<Member> getMemberList() {
        return memberManager.getMemberList();
    }

    @Override
    public int getMemberListSize() {
        return memberManager.getMemberListSize();
    }

    @Override
    public String getMemberNameById(int index) {
        return memberManager.getMemberNameById(index);
    }

    @Override
    public MemberManager getMemberManager() {
        return memberManager;
    }

    @Override
    public void addMember(String name) throws DukeException {
        memberManager.addMember(name);
        storage.saveMembers(memberManager.getMemberList());
    }

    @Override
    public boolean hasMember(String name) throws DukeException {
        return memberManager.hasMember(name);
    }

    @Override
    public boolean addMemberSkill(String memberName, String skillName) {
        return memberManager.addSkill(memberName, skillName);
    }

    @Override
    public String updateMemberBio(int index, String bio) {
        String oldBio = memberManager.getMemberBio(index);
        memberManager.updateMemberBio(index, bio);
        return oldBio;
    }

    @Override
    public String updateMemberEmail(int index, String email) throws DukeException {
        String oldEmail = memberManager.getMemberEmail(index);
        memberManager.updateMemberEmail(index, email);
        return oldEmail;
    }

    @Override
    public String updateMemberPhone(int index, String phone) {
        String oldPhone = memberManager.getMemberPhone(index);
        memberManager.updateMemberPhone(index, phone);
        return oldPhone;
    }


    //============================ Task and member interfaces =================================

    //@@author JustinChia1997
    @Override
    public void link(int taskIndex, String memberName) {
        tasksManager.getTaskById(taskIndex).addMember(memberName);
        //TODO consider uuid for linking
        memberManager.getMemberByName(memberName).addTask(tasksManager.getTaskById(taskIndex).getName());
    }

    @Override
    public void unlink(int taskIndex, String memberName) {
        tasksManager.getTaskById(taskIndex).deleteMember(memberName);
        memberManager.getMemberByName(memberName).deleteTask(memberName);
    }

    //@@author yuyanglin28

    /**
     * This method is to delete member in member list and also in task list corresponding member name
     *
     * @return if success (the member name exists), return true.
     * if fail (the member name doesn't exist), return false.
     */
    @Override
    public String deleteMember(int index) {
        String name = memberManager.getMemberNameById(index);
        tasksManager.deleteMemberInTasks(name);
        Member toDelete = memberManager.getMemberById(index);
        memberManager.deleteMember(toDelete);
        return name;
    }

    //@@author yuyanglin28

    /**
     * javadoc
     */
    @Override
    public String deleteTask(int index) {
        String name = tasksManager.getTaskNameById(index);
        memberManager.deleteTaskInMembers(name);
        Task toDelete = tasksManager.getTaskByName(name);
        tasksManager.deleteTask(toDelete);
        return name;
    }

    public String scheduleMemberAll(String memberName) {
        ArrayList<String> tasksName = memberManager.getTaskListOfMember(memberName);
        return tasksManager.scheduleAllTasks(tasksName);
    }

    public String scheduleMemberTodo(String memberName) {
        ArrayList<String> tasksName = memberManager.getTaskListOfMember(memberName);
        return tasksManager.scheduleTodoTasks(tasksName);
    }
}