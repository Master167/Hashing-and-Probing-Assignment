import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
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
            HashTable linearTable = new HashTable("A", this.arraySize);
            HashTable quadTable = new HashTable("B", this.arraySize);
            
            //Fill the tables
            linearTable = fillTableLinear(this.initialFile, linearTable);
            linearTable.displayTable();
            
            //Find strings in those tables
            
            //Delete strings in those tables
        }
        System.out.printf("%nEnd of Program%n");
    }
    
    private HashTable fillTableLinear(File file, HashTable table){
        try{
            Scanner fileReader = new Scanner(file);
            for(int i = 0; i < this.numberOfStrings; i++){
                table.insertLinear(new DataItem(fileReader.nextLine()));
            }
        }
        catch(FileNotFoundException ex){
            System.out.printf(ex.toString());
        }
        
        return table;
    }
    
    private HashTable fillTableQuad(File file, HashTable table){
        return table;
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
    
    private boolean initializeFiles(String[] args){
        boolean valid = true;
        this.initialFile = new File(args[0]);
        if(!initialFile.exists()){
            System.out.printf("Initial File Not Found%n");
            valid = false;
        }
        this.searchFile = new File(args[1]);
        if(!this.searchFile.exists()){
            System.out.printf("Search File Not Found%n");
            valid = false;
        }
        this.deletionFile = new File(args[2]);
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
   public void setProbeLength(int newLength){
       this.probeLength = newLength;
   }
//--------------------------------------------------------------
   }  // end class DataItem
////////////////////////////////////////////////////////////////
class HashTable
   {
   private DataItem[] hashArray;    // array holds hash table
   private int arraySize;
   private DataItem nonItem;        // for deleted items
   private String tableName;
// -------------------------------------------------------------
   public HashTable(String tableName, int size)       // constructor
      {
      this.arraySize = size;
      this.hashArray = new DataItem[arraySize];
      this.nonItem = new DataItem("");   // deleted item key is the empty string
      this.tableName = tableName;
      }
// -------------------------------------------------------------
   public void displayTable()
      {
      System.out.printf("%s%n", this.tableName);
      System.out.printf("%s | %-40s | %s |%n", "Index", "String", "Probe Length for Insertion");
      for(int j=0; j<arraySize; j++)
         {
            if(hashArray[j] != null){
                System.out.printf("%5d | %-40s | %-13d%n", j, hashArray[j].getKey(), hashArray[j].getProbeLength());
            }
         }
      }
// -------------------------------------------------------------
   public int hashFunc(String key)
      {
        char[] charArray = key.toCharArray();
        int hashValue = charArray[0];
        int temp;
        for(int i = 1; i < charArray.length; i++){
            temp = charArray[i];
            hashValue = (hashValue * 26 + temp) % arraySize;
        }
        return hashValue;
      }
// -------------------------------------------------------------
   public void insertLinear(DataItem item) // insert a DataItem
   // (assumes table not full)
      {
      String key = item.getKey();      // extract key
      int hashVal = hashFunc(key);  // hash the key
                                    // until empty cell or -1,
      while(hashArray[hashVal] != null &&
            !hashArray[hashVal].getKey().equals(this.nonItem.getKey()))
         {
         item.increaseProbeLength();
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
         }
      item.increaseProbeLength();
      hashArray[hashVal] = item;    // insert item
      }  // end insert()
// -------------------------------------------------------------
   public DataItem deleteLinear(String key)  // delete a DataItem
      {
      int hashVal = hashFunc(key);  // hash the key

      while(hashArray[hashVal] != null)  // until empty cell,
        {                               // found the key?
         if(hashArray[hashVal].getKey().equals(key)){
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
   public void insertQuad(DataItem item) // insert a DataItem
   // (assumes table not full)
      {
      int length = 1;
      int index;
      String key = item.getKey();      // extract key
      int hashVal = hashFunc(key);  // hash the key
                                    // until empty cell or -1,
      index = hashVal;
      while(hashArray[index] != null &&
            !hashArray[index].getKey().equals(this.nonItem.getKey()))
         {
         length++;
         index = hashVal + (length * length);
         index %= arraySize;      // wraparound if necessary
         }
      item.setProbeLength(length);
      hashArray[index] = item;    // insert item
      }  // end insert()
// -------------------------------------------------------------
   public DataItem deleteQuad(String key)  // delete a DataItem
      {
      int hashVal = hashFunc(key);  // hash the key
      int index = hashVal;
      int length = 1;

      while(hashArray[index] != null)  // until empty cell,
        {                               // found the key?
         if(hashArray[index].getKey().equals(key)){
            DataItem temp = hashArray[index]; // save item
            temp.setProbeLength(length);
            hashArray[index] = nonItem;       // delete item
            return temp;                        // return item
         }//end if
         length++;
         index = hashVal + (length * length);   // go to next cell
         index %= arraySize;      // wraparound if necessary
        }
      DataItem fail = new DataItem(nonItem.getKey());
      fail.setProbeLength(length);
      return fail;                  // can't find item
      }  // end delete()
// -------------------------------------------------------------
   public DataItem findQuad(String key)    // find item with key
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
