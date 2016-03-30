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
            this.arraySize = getNextPrime(this.numberOfStrings);
            
            //Create Hash Tables for Linear Probing and Quadratic probing
            
            //Fill the tables
            
            //Find strings in those tables
            
            //Delete strings in those tables
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

//Taken from Data Structures and Algorithms in Java by Robert Lafore
////////////////////////////////////////////////////////////////
class DataItem
   {                                // (could have more data)
   private String data;               // data item (key)
   private int probeLength;
//--------------------------------------------------------------
   public DataItem(String str){ 
       this.data = str; 
       this.probeLength = 0;
   }
//--------------------------------------------------------------
   public String getKey()
      { return data; }
//--------------------------------------------------------------
   public int getProbeLength(){
       return this.probeLength;
   }
//--------------------------------------------------------------
   public void increaseProbeLength(){
       this.probeLength++;
   }
//--------------------------------------------------------------
   }  // end class DataItem
////////////////////////////////////////////////////////////////
class HashTable
   {
   private DataItem[] hashArray;    // array holds hash table
   private int arraySize;
   private DataItem nonItem;        // for deleted items
// -------------------------------------------------------------
   public HashTable(int size)       // constructor
      {
      arraySize = size;
      hashArray = new DataItem[arraySize];
      nonItem = new DataItem("");   // deleted item key is -1
      }
// -------------------------------------------------------------
   public void displayTable()
      {
      System.out.print("Table: ");
      for(int j=0; j<arraySize; j++)
         {
         if(hashArray[j] != null)
            System.out.print(hashArray[j].getKey() + " ");
         else
            System.out.print("** ");
         }
      System.out.println("");
      System.out.printf("displayTable: Rewrite this function%n");
      }
// -------------------------------------------------------------
   public int hashFunc(String key)
      {
        System.out.printf("hashFunc: Rewrite this function%n");
        return -1;
      }
// -------------------------------------------------------------
   public void insertLinear(DataItem item) // insert a DataItem
   // (assumes table not full)
      {
      String key = item.getKey();      // extract key
      int hashVal = hashFunc(key);  // hash the key
                                    // until empty cell or -1,
      while(hashArray[hashVal] != null &&
            hashArray[hashVal].getKey() != this.nonItem.getKey())
         {
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
         }
      hashArray[hashVal] = item;    // insert item
      }  // end insert()
// -------------------------------------------------------------
   public DataItem deleteLinear(String key)  // delete a DataItem
      {
      int hashVal = hashFunc(key);  // hash the key

      while(hashArray[hashVal] != null)  // until empty cell,
        {                               // found the key?
         if(hashArray[hashVal].getKey() == key){
            DataItem temp = hashArray[hashVal]; // save item
            hashArray[hashVal] = nonItem;       // delete item
            return temp;                        // return item
        }
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
        }
      return null;                  // can't find item
      }  // end delete()
// -------------------------------------------------------------
   public DataItem findLinear(String key)    // find item with key
      {
      int hashVal = hashFunc(key);  // hash the key

      while(hashArray[hashVal] != null)  // until empty cell,
         {                               // found the key?
         if(hashArray[hashVal].getKey().equals(key)){
            return hashArray[hashVal];   // yes, return item
         }
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
         }
      return null;                  // can't find item
      }
// -------------------------------------------------------------
   }  // end class HashTable
////////////////////////////////////////////////////////////////
