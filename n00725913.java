import java.util.List;
import java.io.File;
/*
   Michael Frederick
   N00725913
   Data Structures
   COP3530
   Ken Martin
   
   Assignment 6
*/

public class n00725913
{
    public static void main(String[] args) {
        Program program = new Program();
        program.runProgram(args);
    }//end main()
} // end class n00725913

class Program{
    File initialFile;
    File insertionFile;
    File deletionFile;
    
    int numberOfStrings;
    int arraySize;
    
    public void runProgram(String[] args){
        boolean valid = initializeFiles(args);
        if(valid){
            //DO ALL THE THINGS
        }
        return;
    }
    
    public int findNumberOfStrings(){
        int number;
        
        return number;
    }
    
    public int determineArraySize(int nonPrime){
        int prime = 0;
        
        return prime;
    }
    
    public boolean initializeFiles(String[] args){
        boolean valid = true;
        this.initialFile = new File(args[0]);
        if(!initialFile.exists()){
            System.out.printf("Intial File Not Found%n");
            valid = false;
        }
        this.insertionFile = new File(args[1]);
        if(!this.insertionFile.exists()){
            System.out.printf("Insertion File Not Found%n");
            valid = false;
        }
        this.deletionFile = new File(args[1]);
        if(!this.deletionFile.exists()){
            System.out.printf("Deletion File Not Found%n");
            valid = false;
        }
        return valid;
    }
}
