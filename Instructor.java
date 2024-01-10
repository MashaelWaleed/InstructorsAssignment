/*
Name:Mashael waleed
ID:
Section Number:BBC
Email:
Date:12/4/2023
Assignment:P1_Instructor assignment.
 */
package Instructor_assignment;
//Instructor class (node).......................................................
public class Instructor {
private int ID;
private String Fname;
private String Lname;
private String Course;  
private Boolean Coordinator;
private Instructor next;


 public Instructor(int ID, String Fname, String Lname){
    this( ID,Fname,Lname,null);
    }
    public Instructor(int ID, String Fname, String Lname,String Course){
    this( ID,Fname,Lname,Course,false,null);
    }

    public Instructor(int ID, String Fname, String Lname,String Course, Boolean Coordinator, Instructor next) {
        this.ID = ID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.Course = Course;
        this.Coordinator = Coordinator;
        this.next = next;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String Course) {
        this.Course = Course;
    }

    public Boolean getCoordinator() {
        return Coordinator;
    }

    public void setCoordinator(Boolean Coordinator) {
        this.Coordinator = Coordinator;
    }

    public Instructor getNext() {
        return next;
    }

    public void setNext(Instructor next) {
        this.next = next;
    }

///////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Instructor{" + "ID=" + ID + ", Fname=" + Fname + ", Lname=" + Lname + ", Course=" + Course + ", Coordinator=" + Coordinator + ", next=" + next + '}';
    }
///////////////////////////////////////////////////////////////////////////////////////////
    public String isCoordinator(){
    if(Coordinator) 
        return "Yes";
    else
        return "No";
   }
  /////////////////////////////////////////////////////////////////////////////////////////  
    public String printInfo(){
    return String.format("%03d",ID)+"           "+String.format("%-35s%-10s\n",fullName(),isCoordinator());
    
    }
  //////////////////////////////////////////////////////////////////////////////////////////  
    public String fullName(){
    return Fname+" "+Lname;
    }
   /////////////////////////////////////////////////////////////////////////////////////////// 
    public String getIDFormated(){
    return String.format("%03d",ID);
    }
}

