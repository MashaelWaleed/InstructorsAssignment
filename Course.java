/*
Name:Mashael waleed 
ID:
Section Number:BBC
Email:
Date:12/4/2023
Assignment:P1_Instructor assignment.
 */

package Instructor_assignment;

//Course class(linked list).....................................................
public class Course {

  private String courseName;
  private int courseNumber;
  private int numOfInstructors;
  private Instructor head;
  //to make serch on coordinator in any class faster
  private Instructor CoursecoordInstructor;
  //to make adding Instructors faster 
  private Instructor topInList;
  //////////////////////////////////////////////////////////////////////////////
  //constructors
    public Course(){
    this(null,0,null);}
  
    
    public Course(String courseName, int courseNumber, Instructor head) {
        this.courseName = courseName;
        this.courseNumber = courseNumber; }
  //////////////////////////////////////////////////////////////////////////////

    public int getNumOfInstructors() {
        return numOfInstructors;
    }

    public void setNumOfInstructors(int numOfInstructors) {
        this.numOfInstructors = numOfInstructors;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setcoordInstructor(Instructor cordinator) {
        this.CoursecoordInstructor = cordinator;
    }

    public Instructor getcoordInstructor() {
        return CoursecoordInstructor;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Instructor getHead() {
        return head;
    }

    public void setHead(Instructor head) {
        this.head = head;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////
     public boolean isEmpty()
     {return (head==null);
     }
    ///////////////////////////////////////////////////////////////////////////////////////////
     public StringBuilder PrintAllNode(){
        Instructor helpPtr=head;
         StringBuilder AllInfo=new StringBuilder("Instructors in CPCS"+this.courseNumber+" Course\n");
         AllInfo.append("ID	        Name 	                     Coordinator\n");
         
         while(helpPtr!=null){
         AllInfo.append(helpPtr.printInfo());
         helpPtr=helpPtr.getNext();}
         //return all info of insructors in one course
       return AllInfo; 
     }
    
    
    //////////////////////////////////Add ne instructer in the List///////////////////////////
    public void addInstructor(Instructor newInstr){
       
    if(isEmpty())
    {head=newInstr;
     topInList=head; }
    else{
    topInList.setNext(newInstr);
    topInList=topInList.getNext();
    }
    //increase number of instructors
    numOfInstructors++;
    }
 ////////////////////////////////Determine coordinators based on ID////////////////////////////// 
   public void setcoordInstructor (int ID){
   Instructor cordinator=searchInstructorByID(ID);
   if (cordinator!=null){
   cordinator.setCoordinator(true);
   this.CoursecoordInstructor=cordinator;}}
 
 //////////////////////////////////Search instructor based on ID////////////////////////////////
   
    public Instructor searchInstructorByID(int ID){
      return searchInstructorByID(head,ID); }
   //------------------------------------------- 
   private Instructor searchInstructorByID(Instructor head,int ID){
    Instructor helpPtr=head;
    while(helpPtr!=null){
    if(helpPtr.getID()==ID)
        return helpPtr;
    
     helpPtr=helpPtr.getNext();}
    return helpPtr;
    }
   ///////////////////////////Search for previous instructor to my target based on ID/////////////////////////
     public Instructor PrevInstructorByID(int ID){
      return FindPrevInstructorByID(head,ID);}
     //--------------------------------------
    private Instructor FindPrevInstructorByID(Instructor head,int ID){
    Instructor helpPtr=head;
    if(isHead(ID)){
    return  head;
    }
    else{
    while(helpPtr.getNext()!=null){
    if(helpPtr.getNext().getID()==ID)
        return helpPtr;
    
     helpPtr=helpPtr.getNext();}
       
       return null;
    }
   }
   ////////////////////////////////////////swap between to Instructors///////////////////////////////////////
   
   public static void swapInstructor(Instructor inst,Instructor inst2,boolean ishead1,boolean isHead2,Course course1,Course course2){
                 Instructor tempIns=null;
                 String Name=null;
                 
                 //swap between heads..............................................................................
                 if(ishead1&&isHead2){
                  //swap nexts of heads   
                   tempIns= inst.getNext();
                   inst.setNext(inst2.getNext());
                   inst2.setNext(tempIns);
                 
                   //no prev
                   //swap head in courses
                   course1.setHead(inst2);
                   course2.setHead(inst);
                   
                   //swap course names
                   Name=inst.getCourse();
                   inst.setCourse(inst2.getCourse());
                   inst2.setCourse(Name);
                   
                   
                 }
                 
                 
                 //we have the head inst1.........................................................................
                 else if(ishead1&&!isHead2){
                 //swap next
                 tempIns=inst.getNext();
                 inst.setNext(inst2.getNext().getNext());//inst2 is accully the prev node of our target
                 inst2.getNext().setNext(tempIns);
                 
                 //swap prev(inst 1 have no prev)just set prev of inst2 to refer to inst1
                 inst2.setNext(inst);
                 
                 //swap courses names
                 Name=inst.getCourse();
                 inst.setCourse(inst2.getNext().getCourse());//inst2 is accully the prev node of our target
                 inst2.getNext().setCourse(Name);
                 
                 //the head of course 1 will change 
                 course1.setHead(inst2.getNext());
                }
                 
                 
                 //we have the head inst2................................................................................
                 if(!ishead1&&isHead2){
                 //swap next
                 tempIns=inst2.getNext();
                 inst2.setNext(inst.getNext().getNext());//inst2 is actually the prev node of our target
                 inst.getNext().setNext(tempIns);
                 
                 //swap prev(inst 2 have no prev)just set prev of inst1 to refer to inst2
                 inst.setNext(inst2);
                 
                //swap courses names
                 Name=inst2.getCourse();
                 inst2.setCourse(inst.getNext().getCourse());//inst1 is actually the prev node of our target
                 inst.getNext().setCourse(Name);
                 
                 //the head of course 2 will change 
                 course2.setHead(inst.getNext());
                 }
                 
                 //none of instructors are head
                 else{
                 
                 //swap node using pre nodes       
                  tempIns=inst.getNext();
                  inst.setNext(inst2.getNext());
                  inst2.setNext(tempIns);
                  
                 //swap their next
                   tempIns= inst.getNext().getNext();
                   inst.getNext().setNext(inst2.getNext().getNext());
                   inst2.getNext().setNext(tempIns);
                 
                 //swap course names
                   Name=inst.getNext().getCourse();
                  inst.getNext().setCourse(inst2.getNext().getCourse());
                  inst2.getNext().setCourse(Name);
                 }
                  
   }
  ///////////////////////////////////delete instructor  based on ID//////////////////////////////////////// 
   
   public Instructor deleteInstructorByID(int ID){
       //return Instruction to the main 
     return deleteInstructorByID(head,ID);}
   //-----------------------------------------------
   
   private Instructor deleteInstructorByID(Instructor head,int ID){
       Instructor deletedNode=null; 
       //delete if not empty
       if(!isEmpty()){
     // if it was the first node
      if(head.getID()==ID){
           if(head.getCoordinator()){
            return head; }
       deletedNode= head;
       this.head=head.getNext();
       numOfInstructors--;
       return deletedNode; }
   else{
//help pointer to use it while traverseing  
        Instructor helpPtr=head; 
       // Traverse to correct deletion point   
    while(helpPtr.getNext()!=null) {
     if(helpPtr.getNext().getID()==ID){ 
          if(helpPtr.getNext().getCoordinator()){
              return  helpPtr.getNext(); }
             //save to send to main
            deletedNode=helpPtr.getNext();
            helpPtr.setNext(helpPtr.getNext().getNext()); 
            numOfInstructors--;
            return deletedNode;}
          //move to next instructors
           helpPtr=helpPtr.getNext(); } } }
     //if Course is empty
     return null;}
  ///////////////////////////////////////////determine if node is head///////////////////////////////////////

public  boolean isHead(int testID) {
return testID==this.head.getID();

}  
   
}
