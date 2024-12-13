package database.users;

import database.Register;

public class UserMaker {
    private Student student;
    private Teacher teacher;
    private Administrator admin;
    private UsersTypes userType;
    private Register register;

    //factory

    public UserMaker(UsersTypes userType, int id, Register register) {
        this.userType = userType;
        this.register = register;
        establishCurrentUser(id);
    }

    private void establishCurrentUser(int id) {
        switch (userType) {
            case STUDENT -> {
                //binarysearch register.students
            }
            case TEACHER -> {
                //binarySearch register.teachers
            }
            case ADMIN -> {
                //binarySearch register.admins
            }
        }
    }

    public void showOptions() {

    }

    public void showSubjects() {
        switch (userType) {
            case STUDENT: student.showSubjects(); break;
            case TEACHER: teacher.showSubjects(); break;
            case ADMIN: admin.showSubjects(); break;
        }
    }

    public void showAssignments() {
        switch (userType) {
            case STUDENT: student.showAssignments(); break;
            case TEACHER: teacher.showAssignments(); break;
            default: System.out.println("No such function."); break;
        }
    }

    public void showStudents() {
        if (userType == UsersTypes.ADMIN) {
            admin.showStudents();
        } else {
            System.out.println("No such function.");
        }
    }

    public int getID() {
        return switch (userType) {
            case STUDENT -> student.getID();
            case TEACHER -> teacher.getID();
            case ADMIN -> admin.getID();
        };
    }
}
