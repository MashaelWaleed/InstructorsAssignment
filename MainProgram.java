/*
Name:Mashael waleed 
ID:
Section Number:BBC
Email:
Date:12/4/2023
Assignment:P1_Instructor assignment.
 */
package Instructor_assignment;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MainProgram {
    //declare static varibles to use in my program.............................
       static int numInstructors,numCourses,numOfCoord, courseNumber, Id,Id2;
       static String courseName="";static String[] name; static String command;
       static Instructor inst  =null;static Instructor inst2 =null;
       static Course [] courses;static String [] coursesNames;
       static Course  targetCourse1;static Course  targetCourse2;
       static boolean isHead1;static boolean isHead2;
       static int no1=0;static int no2=0;static int no3=0;static int no4=0;
    ///////////////////////////////////////////////////////////////////////////////////////////////
       
        //create file object to read from first file............................
       static File f_command=new File("Commands.txt");
       static Scanner input1;
        //create file object to read from second file...........................
       static File f_intial=new File("IntialInformation.txt");
       static Scanner input2 ;
         //create PrintWriter...................................................
       static PrintWriter output;
       
    ////////////////////////////////////////////////////////////////////////////////////////////////
       
    //start main method
    public static void main(String[] args) throws FileNotFoundException {
        
       if(f_command.exists()&&f_intial.exists()) {
           
      input1=new Scanner(f_command);
      input2=new Scanner(f_intial); 
      output=new PrintWriter("Output.txt");
      //Print the header in output file.........................................
      output.println("Welcome to Course Coordination System");
      output.println("-------------------------------------------------------------");
      output.flush();
     ////////////////////////////////////////////////////////////////////////////////////////////////
     
     //Start reading commands...................................................
         STARTUP();
     //==============================================================================================             
        do{
            //continue reading the rest of commands.............................
            command=input1.next();
        
            //switch to determine the correct case..............................
            switch(command){
    //==============================================================================================          
             case "DISPLAY_ALL_COURSES":       
             DISPLAY_ALL_COURSES();
             break;
    //==============================================================================================          
             case "DISPLAY_COURSE_FOR_INSTRUCTOR":
             DISPLAY_COURSE_FOR_INSTRUCTOR();
             break;
    //==============================================================================================          
             case "NUM_OF_INSTRUCTORS": 
             NUM_OF_INSTRUCTORS();
             break; 
    //==============================================================================================          
             case "DELETE_INSTRUCTOR":   
             DELETE_INSTRUCTOR();
             break;
    //==============================================================================================        
             case "SWAP_": 
                 SWAP_INSTRUCTOR();
             break;
    //==============================================================================================        
             case "QUIT":
             QUIT();    
             break;
    //==============================================================================================  
         
                 
         } }while(!command.equalsIgnoreCase("QUIT"));
    }//if one of files not exists show error massage............................
       else
           System.out.println("Error:some files do not exist !");
    
    
    }
    
     ///////////////////////////////////////////////////////////////////////////////////////////////
     public static void STARTUP(){
       //read first command.....................................................
       command=input1.next();
       //fill with info in second file
       numInstructors=input2.nextInt(); numCourses=input2.nextInt(); numOfCoord=input2.nextInt();
       //-----------------------------------------------------------------------
       //create arrays to store my linked lists (courses) and their names
       courses=new Course[numCourses]; String [] coursesNames=new String [numOfCoord]; 
       // create  linked lists
        for(int i=0;i<numCourses;i++){
        coursesNames[i]= input2.next();
        courseNumber=Integer.parseInt(coursesNames[i].replaceAll("[\\D]",""));
        courses[i]=new Course(null,courseNumber,null); }
                            
        //----------------------------------------------------------------------
        //store coordinators ID
        int[] CoordID=new int [numOfCoord];
        for(int i=0;i<CoordID.length;i++){
        CoordID[i]=input2.nextInt(); }
        //----------------------------------------------------------------------
        //add instructors to courses
        for(int i=0;i<numCourses;i++){
        for(int j=0;j<5;j++) {
        Id= input2.nextInt(); 
        //create new instructor
        courses[i].addInstructor(new Instructor(Id,input2.next(),input2.next(),coursesNames[i]));} 
        //determine if the instructor coordinator or not
        courses[i].setcoordInstructor(CoordID[i]); }
        //to move to nextLine  
        input2.nextLine();
        //--------------------------------------
        for(int i=0;i<numCourses;i++){
        courses[i].setCourseName( input2.nextLine()); 
       }
    
}
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static void DISPLAY_ALL_COURSES(){
          output.println();
          output.println("    Information of Instructors in Each Class");
          for(int i=0;i<numCourses;i++){
          output.println(courses[i].PrintAllNode()); }
          output.flush();
    
    }
    
      ///////////////////////////////////////////////////////////////////////////////////////////////
   public static void DISPLAY_COURSE_FOR_INSTRUCTOR(){
        // print this only in first time........................................
        if(no1==0){
        output.println("\n\n");     
        output.println("    Instructor Information");
        no1++;}
        //read info ............................................................
        Id= input1.nextInt();
        //search in the instructor with this ID in courses......................
        for(int i=0;i<numCourses;i++){
        inst= courses[i].searchInstructorByID(Id); 
        if(inst!=null){
        //display an instructor information ....................................   
        output.println("\"Instructor: "+ inst.fullName()+","+inst.getIDFormated()
                +", is assigned to "+ courses[i].getCourseName()
                          +", coordinated by "+courses[i].getcoordInstructor().getIDFormated()
                +" " +courses[i].getcoordInstructor().fullName() );
         break;}}
        //no instructor found...................................................
        if(inst == null)
        output.println("No instructor of this ID is found");
        output.flush();
   
   
   } 
    
   
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static void NUM_OF_INSTRUCTORS(){
    
       // print this only in first time.........................................
        if(no2==0){
        output.println("\n\n");     
        output.println("    Number of Instructors in Each Course");
        no2++;}
                  
        courseName=input1.next();
        courseNumber=Integer.parseInt(courseName.replaceAll("[\\D]",""));
        //search in the instructor with this ID in courses......................
        for(int i=0;i<numCourses;i++){
        if(courses[i].getCourseNumber()==courseNumber){
        name= courses[i].getCourseName().split(command);
        output.print("Number of Instructors in "+String.format("%-30s" ,name[0])+": "
                +String.format("%3d" ,courses[i].getNumOfInstructors())+"\n");
        break; }
                  }
     }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    
     public static void DELETE_INSTRUCTOR(){
    
               // print this only in first time.................................
                  if(no3==0){
                 output.println("\n\n");     
                 output.println("    Delete Instructor");
                 no3++;}
                 Id= input1.nextInt();
                 //search in the instructor with this ID in courses.............
                 for(int i=0;i<courses.length;i++){
                 inst= courses[i].deleteInstructorByID(Id);
                 if(inst!=null){
                 if(inst.getCoordinator())
                 output.println("You cannot delete a coordinator");
                 else
                 output.println(inst.getCourse()+" is not assigned to "+inst.fullName());
                 break;}
                  }
                 
     }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static void SWAP_INSTRUCTOR(){
    
           input1.next();
                 // print this only in first time...............................
                 if(no4==0){
                 output.println("\n\n");     
                 output.println("    Swap Instructors ");
                 no4++;}
                 
                 //read Ids.....................................................
                 Id =input1.nextInt();
                 Id2 =input1.nextInt();
               
                  //search for first Instructor.................................
                  for(int i=0;i<numCourses;i++){
                  inst= courses[i].searchInstructorByID(Id); 
                
                  if(inst!=null){
                     //store the course in case we swapped between to coordinators
                     targetCourse1=courses[i]; 
                     //if head we will use the instructer itself in swapping
                     //if not we will use pre instructor
                     isHead1=courses[i].isHead(inst.getID());
                  break;
                  }}
                  //search for second Instructor................................
                  for(int i=0;i<numCourses;i++){
                  inst2= courses[i].searchInstructorByID(Id2); 
                  if(inst2!=null){
                     //store the course in case we swapped between to coordinators
                     targetCourse2=courses[i];
                     //if head we will use the instructer itself in swapping
                     //if not we will use pre instructor
                     isHead2=courses[i].isHead(inst2.getID());
                      
                  break;
                  } }
                  
                  //test cases..................................................
              
                  if(inst!=null&&inst2!=null){
                  //no swap.....................................................
                  if(inst.getCoordinator()&&!inst2.getCoordinator()){
                    output.println("Coordinator "+ inst.fullName() +" can not be swapped with instructor "+inst2.fullName()); }
                  
                  else  if(inst2.getCoordinator()&&!inst.getCoordinator()){
                    output.println("Coordinator "+ inst2.fullName() +" can not be swapped with instructor "+inst.fullName()); }
                  
                  
                   //swap in other cases........................................
                  else{
                   
                  //search for prev of first Instructor.........................
                  //if it is the head return the instructer itself..............
                  for(int i=0;i<numCourses;i++){
                  inst= courses[i].PrevInstructorByID(Id); 
                  if(inst!=null){
                  
                  break; }}
                  //search for prev for second Instructor.......................
                  //if it is the head return the instructer itself..............
                  for(int i=0;i<numCourses;i++){
                  inst2= courses[i].PrevInstructorByID(Id2); 
                  if(inst2!=null){
                  break;
                  } }
                  
                  //use swap method.............................................
                   Course.swapInstructor(inst,inst2,isHead1,isHead2,targetCourse1,targetCourse2);  
                  //both instructors are coordinator----------------------------
                  if(inst.getCoordinator()&&inst2.getCoordinator()){
                  //display depends on head or not(if head we use the instructor itself in swapping method
                        targetCourse1.setcoordInstructor((isHead2)?inst2:inst2.getNext());
                        targetCourse2.setcoordInstructor((isHead1)?inst:inst.getNext());
                         
                       output.println("Coordinator "+((isHead2)?inst2.fullName():inst2.getNext().fullName())
                               +" is now in "+((isHead2)?inst2.getCourse():inst2.getNext().getCourse())
                               +", Coordinator "+((isHead1)?inst.fullName():inst.getNext().fullName())
                               +" is now in "+((isHead1)?inst.getCourse():inst.getNext().getCourse()));   }
                  
                  //normal instructors------------------------------------------
                  else{
                 //display depends on head or not(if head we use the instructor itself in swapping method   
                          output.println("\""+((isHead2)?inst2.fullName():inst2.getNext().fullName())
                                  +" is now in "+((isHead2)?inst2.getCourse():inst2.getNext().getCourse())
                                  +", "+((isHead1)?inst.fullName():inst.getNext().fullName())
                                  +" is now in "+((isHead1)?inst.getCourse():inst.getNext().getCourse())+"\"");}
                  } }
                          output.flush();
                  } 
public static void QUIT()
{
        output.println("=================================" );
        output.println("          Best Wishes             ");
        output.println("=================================" );
        output.flush(); output.close();
        input1.close();input2.close();
}

}
    

