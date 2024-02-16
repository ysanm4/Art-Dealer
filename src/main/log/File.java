package main.log;

//https://www.w3schools.com/java/java_methods.asp
//https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
//https://www.educative.io/answers/what-is-the-printstacktrace-method-in-java

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class File {
    private static BufferedWriter writer;
    private static final Logger LOGGER = Logger.getLogger(File.class.getName());
    public static void openFile(){
        try {
            writer = new BufferedWriter(new FileWriter("usage_data.txt", true));

            // Add date stamp to the first new line
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String dateStamp = dateFormat.format(new Date());
            writer.write(dateStamp);
            writer.newLine();
        } catch (IOException e){
            LOGGER.log(Level.SEVERE, "Error opening file", e);
        }
    }

    public static void writeToFile(String line){
        try{
            writer.write(line);
            writer.newLine();
        } catch(IOException e){
            LOGGER.log(Level.SEVERE, "Error writing to file", e);
        }
    }

    public static void closeFile(){
        try{
            writer.close();
        } catch (IOException e){
            LOGGER.log(Level.SEVERE, "Error closing file", e);
        }
    }

}
