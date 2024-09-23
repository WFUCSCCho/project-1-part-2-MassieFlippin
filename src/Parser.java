/**
 @file: BST.java
 @description: This program implements a parser class that reads commands from a file and processes them to operate on a BST
 The commands include insert, remove, searc, and print. This program also writes to a separate file "./result.txt"
 @author: Massie Flippin
 @date: September 19th , 2024
 ************************/

import java.io.*;
import java.util.Scanner;

public class Parser {

    //Create a BST tree of Integer type
    private BST<Integer> mybst = new BST<>();
    //Constructor that takes the filename as input and calls the process method.
    public Parser(String filename) throws FileNotFoundException {
        process(new File(filename));
    }

    // Implement the process method
    // Remove redundant spaces for each input command
    public void process(File input) throws FileNotFoundException {
        //Scanner to accept the command file
        Scanner scanner = new Scanner(input);
        //Read the file line by line.
        while(scanner.hasNextLine()){
            String line = scanner.nextLine().trim();
            if(!line.isEmpty()){ // Ignores the blank lines
                //Replace multiple spaces between words with a single space
                String cleanedLine = line.replaceAll("\\s+", " ");

                //Split the command into parts and pass it to operate_BST method
                String[] command = cleanedLine.split(" ");
                //call operate_BST method;
                operate_BST(command);
            }
        }
    }

    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) {
        //evaluates expression and executes all statements that follow the matching case label
        switch (command[0]) {
            // insert case: inserts into BST and logs the result
            case "insert" -> {
                if (command.length == 2) {
                    try {
                        int value = Integer.parseInt(command[1]); //parse int value
                        mybst.insert(value); //insert command
                        writeToFile("Insert:" + value, "./result.txt"); //log the result
                        writeToFile("\n", "./result.txt");
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command" + "\n", "./result.txt");
                    }
                }
            }
            case "remove" -> {
                if (command.length == 2) { // if the command has a valid second part it will remove.
                    try {
                        int value = Integer.parseInt(command[1]); // parse
                        Integer removedValue = mybst.remove(value); // remove from BST
                        // if else statements removing and if the value is not stated then it will say so.
                        if (removedValue == null) {
                            writeToFile("Error Removing Value: " + value, "./result.txt");
                            writeToFile("\n", "./result.txt");
                        }
                        else if (removedValue == value) {
                            writeToFile("Removed:" + removedValue, "./result.txt");
                            writeToFile("\n", "./result.txt");

                        } else {
                            writeToFile("Error Removing Value: ", value + "./result.txt");
                            writeToFile("\n", "./result.txt");
                        }
                    }
                    catch (NumberFormatException e) {
                        writeToFile("Invalid Command", "./result.txt");
                    }
                }
            }
            case "search" -> {
                if (command.length == 2) {
                    //will search for the command within the BST

                    try {
                        int value = Integer.parseInt(command[1]);
                        //finding value in the BST
                        Object result = mybst.find(value);
                        writeToFile("Searching Value: " + value, "./result.txt");
                        writeToFile("\n", "./result.txt");
                        boolean found = result != null;
                        // if else statements evaluating if the value was found
                        if (found) {
                            writeToFile("Found Value" + value, "./result.txt");
                        } else {
                            writeToFile("Error: Not Found " + value, "./result.txt");
                            writeToFile("\n", "./result.txt");
                        }
                    }
                    catch (NumberFormatException e) {
                        writeToFile("Invalid Command", "./result.txt");
                    }
                }
            }
            case "print" ->{
                //print statement with a StringBuilder collecting the content of the BST
                StringBuilder treeContent = new StringBuilder();
                //for statement appending the values of the bst
                for(int value: mybst){
                    treeContent.append(value);
                    treeContent.append(" ");
                }
                //writing to the file and printing the result
                writeToFile("Printing Result:" + "\n" + treeContent, "./result.txt");
                writeToFile("\n", "./result.txt");
            }
            // call writeToFile
            // default case for Invalid Command
            default -> writeToFile("Invalid Command" + "\n", "./result.txt");

        }

    }


    // Implement the writeToFile method
    // Generate the result file
    public void writeToFile(String content, String filePath) {
        try {
            //FileWriter and BufferedWriter to assure that when writeToFile is called it can write to a file.
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            fileWriter.write(content);
            bufferedWriter.close();
            fileWriter.close();
        }
        catch (IOException ignored) {}
    }
}

