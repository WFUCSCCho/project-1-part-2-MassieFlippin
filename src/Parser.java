/**
 @file: Parser.java
 @description: This program implements a parser class that reads commands from a file and processes them to operate on a BST
 The commands include insert, remove, seek, and print. The properties in the BST are organized by Price.
 This program also writes to a separate file "./result.txt"

 My data set was downloaded from Kaggle and edited for formatting purposes. The link to the original data set on kaggle can be found
 Here: https://www.kaggle.com/datasets/shudhanshusingh/real-estate-properties-dataset

 @author: Massie Flippin
 @date: September 26th , 2024
 ************************/
import java.io.*;
import java.util.Scanner;

public class Parser {

    //Create a BST tree to store RealEstateData objects
    private BST<RealEstateData> mybst = new BST<>();
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
    public void operate_BST(String[] command) throws FileNotFoundException {
        //path to the result.txt file. Not used but could be used in the future
        String newfile = "./result.txt";
        //path to the csv file that contains the real estate data
        String csvFile = "C:\\Users\\mflip\\IdeaProjects\\project-1-part-2-MassieFlippin\\properties.updated.csv";
        //An array to hold all of the information
        String[] data = null;
        //scanner to read the file
        Scanner csvScanner = new Scanner(new File(csvFile));
        //handles different commands
        switch (command[0]) {
            // insert case: inserts into BST and logs the result
            case "insert" -> {
                //skips the header line
                csvScanner.nextLine();
                //loop through the CSV file to find the matching ID
                while(csvScanner.hasNextLine()){
                    String line = csvScanner.nextLine().trim();//trims away any data
                    if(line.isEmpty()) continue;//skip the empty lines
                    data = line.split(",", -1);
                    //parse through the CSV fild to find the matching ID
                    try {
                        if (data[0].equals(command[1])) {
                            int ID = Integer.parseInt(data[0]);
                            String possessionStatus = data[1];
                            String commercial = data[2];
                            String developer = data[3];
                            int price = Integer.parseInt(data[4]);
                            int sqftprice = Integer.parseInt(data[5]);
                            String funished = data[6];
                            int bathroom = Integer.parseInt(data[7]);
                            String facing = data[8];
                            String transaction = data[9];
                            String type = data[10];
                            String city = data[11];
                            int bedrooms = Integer.parseInt(data[12]);
                            int floors = Integer.parseInt(data[13]);
                            String isPrimeLocatoin = data[14];
                            String lifespan = data[15];

                            //create and insert the object into the BST
                            RealEstateData newRealEstate = new RealEstateData(ID, possessionStatus, commercial, developer, price, sqftprice, funished, bathroom, facing, transaction, type, city, bedrooms, floors, isPrimeLocatoin, lifespan);

                            mybst.insert(newRealEstate);
                            writeToFile("\n", "./result.txt");
                            writeToFile("Inserted: " + newRealEstate, "./result.txt");
                        }
                    }
                    catch (NumberFormatException e){
                        writeToFile("\n", "./result.txt");
                        writeToFile("Invalid Insert ", "./result.txt");
                        writeToFile("\n", "./result.txt");
                    }
                }

            }
            case "remove"->{
                //remove case: parse through the CSV file to find the matching data. If it is stored in the BST remove
                csvScanner.nextLine();
                while(csvScanner.hasNextLine()){
                    String line = csvScanner.nextLine().trim();//trims away any data
                    if(line.isEmpty()) continue;
                    data = line.split(",", -1);
                    try{
                    if(data[0].equals(command[1])) {
                        int ID = Integer.parseInt(data[0]);
                        String possessionStatus = data[1];
                        String commercial = data[2];
                        String developer = data[3];
                        int price = Integer.parseInt(data[4]);
                        int sqftprice = Integer.parseInt(data[5]);
                        String funished = data[6];
                        int bathroom = Integer.parseInt(data[7]);
                        String facing = data[8];
                        String transaction = data[9];
                        String type = data[10];
                        String city = data[11];
                        int bedrooms = Integer.parseInt(data[12]);
                        int floors = Integer.parseInt(data[13]);
                        String isPrimeLocatoin = data[14];
                        String lifespan = data[15];


                        RealEstateData newRealEstate = new RealEstateData(ID, possessionStatus, commercial, developer, price, sqftprice, funished, bathroom, facing, transaction, type, city, bedrooms, floors, isPrimeLocatoin, lifespan);

                        Object result = mybst.remove(newRealEstate);
                        writeToFile("\n", "./result.txt");
                        writeToFile("Removed: " + newRealEstate, "./result.txt");
                        writeToFile("\n", "./result.txt");
                    }

                    }
                    catch(NumberFormatException e){
                        writeToFile("\n", "./result.txt");
                        writeToFile("Error: Invalid Remove", "./result.txt");
                        writeToFile("\n", "./result.txt");
                    }
                }
            }
            case "print" ->{
                //print statement with a StringBuilder collecting the content of the BST
                StringBuilder treeContent = new StringBuilder();
                //for statement appending the values of the bst
                for(RealEstateData realEstate : mybst){
                    treeContent.append(realEstate).append("\n");
                    treeContent.append(" ");
                }
                //writing to the file and printing the result
                writeToFile("\n", "./result.txt");
                writeToFile("Printing Result:" + "\n" + treeContent.toString(), "./result.txt");
                writeToFile("\n", "./result.txt");
            }
            case "seek" ->{
                //seeks the
                csvScanner.nextLine();
                while(csvScanner.hasNextLine()){
                    String line = csvScanner.nextLine().trim();
                    if(line.isEmpty()) continue;
                    data = line.split(",", -1);
                    try {
                        if (data[0].equals(command[1])) {
                            //creates the RealEstateData object to search for
                            int ID = Integer.parseInt(data[0]);
                            String possessionStatus = data[1];
                            String commercial = data[2];
                            String developer = data[3];
                            int price = Integer.parseInt(data[4]);
                            int sqftprice = Integer.parseInt(data[5]);
                            String funished = data[6];
                            int bathroom = Integer.parseInt(data[7]);
                            String facing = data[8];
                            String transaction = data[9];
                            String type = data[10];
                            String city = data[11];
                            int bedrooms = Integer.parseInt(data[12]);
                            int floors = Integer.parseInt(data[13]);
                            String isPrimeLocatoin = data[14];
                            String lifespan = data[15];
                            RealEstateData newRealEstate = new RealEstateData(ID, possessionStatus, commercial, developer, price, sqftprice, funished, bathroom, facing, transaction, type, city, bedrooms, floors, isPrimeLocatoin, lifespan);
                            mybst.find(newRealEstate);
                            writeToFile("\n", "./result.txt");
                            writeToFile("Searching ID: " + ID, "./result.txt");
                            writeToFile("\n", "./result.txt");
                            writeToFile("Found ID: " + ID, "./result.txt");
                            writeToFile("\n", "./result.txt");
                        }

                    }
                    catch (NumberFormatException e){
                        writeToFile("\n", "./result.txt");
                        writeToFile("Invalid ID Search", "./result.txt");
                        writeToFile("\n", "./result.txt");
                    }
                }

            }
            // call writeToFile
            // default case to handle Invalid Command
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
