package student.course.registration.system;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

class Student
{
    private int studentID;
    private String studentName;
    private List<Course> registeredCourses;
    
    Student(int sID, String sName)
    {
        this.studentID = sID;
        this.studentName = sName;
        this.registeredCourses = new ArrayList<>();
    }
    
    int getStudentID()
    {
        return studentID;
    }
    String getStudentName()
    {
        return studentName;
    }
    List<Course> getRegisteredCourses()
    {
        return registeredCourses;
    }
    void registerCourse(Course c)
    {
        if(c.registerStudent())
        {
            registeredCourses.add(c);
            System.out.println("Course registered: "+ c.getTitle());
        }
        else{
            System.out.println("Course is full: "+ c.getTitle());
        }
    }
    void dropCourse(Course c)
    {
        if(registeredCourses.contains(c)){
            c.dropStudent();
            registeredCourses.remove(c);
            System.out.println("Course dropped: "+c.getTitle());
        }
        else{
            System.out.println("Course not found "+ c.getTitle());
        }
    }
}

class Course
{
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int availableSlots;
    
    Course(String code, String title, String description, int capacity, String schedule)
    {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.availableSlots = capacity;
    }
    String getCode(){
        return code;
    }
    String getTitle(){
        return title;
    }
    String getDescription(){
        return description;
    }
    String getSchedule(){
        return schedule;
    }
    int getAvailableSlots(){
        return availableSlots;
    }
    boolean registerStudent(){
        if(availableSlots > 0){
            availableSlots--;
            return true;
        }
        return false;
    }
    void dropStudent(){
        if(availableSlots < capacity){
            availableSlots++;
        }
    }
    
    @Override
    public String toString(){
        return "Course Code: " + code + "\n" + "Title: " + title + "\n" + "Description: " + description + "\n" + "Available Slots: " + availableSlots + "/" + capacity + "\n"
                + "Schedule: " + schedule;
    }
}

public class StudentCourseRegistrationSystem 
{
    public static void main(String[] args) 
    {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("MySQL-101","Relational DataBase Management System","Learn the basics of RDBMS",15,"Thursday to Saturday 10:00 AM to 11:00 AM"));
        courses.add(new Course("M1-104","Discrite Mathematics","Set, Relations, Functions, P & C, tree and Graph Theory",18,"Monday to Saturday 11:15 to 12:15 PM"));
        courses.add(new Course("DSA-105","Data Structures and algorithams with Java","Linear and non-linear DS",20,"Monday to Wednesday 10:00 AM to 11:00 AM"));
    
        List<Student> students = new ArrayList<>();
        students.add(new Student(1001,"Bob Roy"));
        students.add(new Student(1002,"Anny Smith"));
        
        Scanner sc = new Scanner(System.in);
        
        while(true){
            System.out.println("\n"+"1. View available Courses");
            System.out.println("\n"+"2. Register for a Course");
            System.out.println("\n"+"3. Drop a course");
            System.out.println("\n"+"4. Exit");
            int choice = sc.nextInt();
            
            switch(choice){
                case 1:
                        System.out.println("\n"+"Availabel Courses: ");
                        for(Course cr: courses){
                            System.out.println(cr);
                            System.out.println("------------------------------------------------------------");
                        }   break;
                        
                case 2:
                        System.out.print("Enter Student ID: ");
                        int s_id = sc.nextInt();
                        Student stud = findStudent(students,s_id);
                        if(stud != null){
                            System.out.println("\n Available Courses: ");
                            for(int i = 0; i < courses.size(); i++)
                                System.out.println(i + 1 + ". " + courses.get(i).getTitle());
                            System.out.println("Select a course (1-" + courses.size() + "): ");
                            int courseChoice = sc.nextInt() - 1;
                            if(courseChoice >= 0 && courseChoice < courses.size())
                                stud.registerCourse(courses.get(courseChoice));
                            else
                                System.out.println("Invalid course Selection.");    
                        }
                        else{
                            System.out.println("Student not found.");
                        }   break;
                    
                case 3: 
                        System.out.print("Enter student ID: ");
                        int studentIDDrop = sc.nextInt();
                        Student studentDrop = findStudent(students, studentIDDrop);
                        if(studentDrop != null)
                        {
                            List<Course> studentCourses = studentDrop.getRegisteredCourses();
                            if(!studentCourses.isEmpty())
                            {
                                System.out.println("\n Registered Courses: ");
                                for(int i=0; i < studentCourses.size(); i++)
                                    System.out.println(i + 1 + ". " + studentCourses.get(i).getTitle());
                                System.out.print("Select a course to drop (1-" + studentCourses.size() + "): ");
                                int courseChoiceDrop = sc.nextInt() - 1;
                                
                                if (courseChoiceDrop >=0 && courseChoiceDrop < studentCourses.size())
                                    studentDrop.dropCourse(studentCourses.get(courseChoiceDrop));
                                else
                                    System.out.println("Invalid course selection. ");
                            }
                            else{
                                System.out.println("No registered courses to drop.");
                            }
                            
                        }
                        else{
                            System.out.println("Student not found.");
                        }       break;
                        
                        
                case 4: System.out.println("Exiting the system.");
                        sc.close(); return;
                default: System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
       
    }
    
    static Student findStudent(List<Student> students, int studentID)
    {
        for(Student student : students){
            if(student.getStudentID() == studentID)
                return student;
        }
        return null;        
        }
}
    
    
    
