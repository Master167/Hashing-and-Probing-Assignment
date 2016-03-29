import java.util.List;
import java.util.Scanner;
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
    File searchFile;
    File deletionFile;
    
    int numberOfStrings;
    int arraySize;
    
    public void runProgram(String[] args){
        boolean valid = initializeFiles(args);
        if(valid){
            this.numberOfStrings = findNumberOfStrings();
        }
        System.out.printf("%nEnd of Program%n");
    }
    
    // Method from pg. 541 of Data Structures and Algorithms in Java by Robert Lafore
    private int getNextPrime(int min){
        for(int i = min + 1; i < Integer.MAX_VALUE - 1; i++){
            if(isPrime(i)){
                return i;
            }
        }
        return -1;
    }
    
    // Method from pg. 541 of Data Structures and Algorithms in Java by Robert Lafore
    private boolean isPrime(int p){
        for(int i = 2; (i * i) <= p; i++){
            if( (p % i) == 0){
                return false;
            }
        }
        return true;
    }
    
    private int findNumberOfStrings(){
        int number = 0;
        try{
            Scanner fileScanner = new Scanner(this.initialFile);
            while (fileScanner.hasNext()){
                number++;
                //I'm not inserting the values yet
                fileScanner.nextLine();
            }
        }
        catch (Exception ex){
            //Something bad happened
            System.out.printf(ex.toString());
        }
        
        return number;
    }
    
    private int determineArraySize(int nonPrime){
        int prime = 0;
        
        return prime;
    }
    
    private boolean initializeFiles(String[] args){
        boolean valid = true;
        this.initialFile = new File(args[0]);
        if(!initialFile.exists()){
            System.out.printf("Intial File Not Found%n");
            valid = false;
        }
        this.searchFile = new File(args[1]);
        if(!this.searchFile.exists()){
            System.out.printf("Search File Not Found%n");
            valid = false;
        }
        this.deletionFile = new File(args[1]);
        if(!this.deletionFile.exists()){
            System.out.printf("Deletion File Not Found%n");
            valid = false;
        }
        return valid;
    }
}//end of class Program
