/**
 * The <code>SigmaAirDriver</code> class contains a main
 * method to help the user interface with the SigmaAir class.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */
import java.io.*;
import java.util.Scanner;

public class SigmaAirDriver implements Serializable {
    final static String DATA_FILE = "sigma_air.obj"; // object file to hold data

    /**
     * Menu-driven program that allows the user to interface with the SigmaAir class. The program stores information
     * within a .obj file (default is sigma_air.obj) to allow for data continuation after the program has been
     * terminated.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;

        System.out.println("Welcome to the SigmaAirDriver! Let's get started!");
        System.out.println("\nNOTE: All City names are case sensitive! Please type carefully and try again if you" +
                " receive an error!");

        SigmaAir mySigmaAir;
        try { // get data from .obj database
            FileInputStream file = new FileInputStream(DATA_FILE);
            ObjectInputStream fin = new ObjectInputStream(file);
            // readObject() returns Object, so must typecast to SigmaAir
            mySigmaAir = (SigmaAir) fin.readObject();
            System.out.println("\nData successfully loaded from " + DATA_FILE + ".");
            fin.close();
        }
        catch (IOException ex) { // make new SigmaAIr if error
            System.out.println("\nError with getting input from " + DATA_FILE + ". Creating a new SigmaAir instance.");
            mySigmaAir = new SigmaAir();
        }
        catch (ClassNotFoundException ex) { // make new SigmaAIr if not found
            System.out.println("Could not find " + DATA_FILE + ". Creating a new SigmaAir instance.");
            mySigmaAir = new SigmaAir();
        }

        while (true) { // while loop for menu to continue running unless quit
            System.out.println("\n\nMain Menu: \n" +
                    "(A) Add City \n" +
                    "(B) Add Connection \n" +
                    "(C) Load all Cities \n" +
                    "(D) Load all Connections \n" +
                    "(E) Print all Cities \n" +
                    "(F) Print all Connections \n" +
                    "(G) Remove Connection \n" +
                    "(H) Find Shortest Path \n" +
                    "(Q) Quit \n");

            System.out.print("\nEnter a selection: ");
            userInput = sc.next().toUpperCase(); // get the user input and set to uppercase

            switch(userInput) {
                case "A": // Add a City
                    try {
                        System.out.print("Enter the name of the city: ");
                        sc.nextLine();
                        String city = sc.nextLine();
                        mySigmaAir.addCity(city);
                    }
                    catch (Exception ex) {
                        System.out.println("There was an error in your input. Please try again.");
                    }
                    break;
                case "B": // Add a Connection
                    try {
                        System.out.print("Enter source city: ");
                        sc.nextLine();
                        String cityFrom = sc.nextLine();
                        System.out.print("Enter destination city: ");
                        String cityTo = sc.nextLine();
                        mySigmaAir.addConnection(cityFrom, cityTo);
                    }
                    catch (Exception ex) {
                        System.out.println("There was an error in your input. Please try again.");
                    }
                    break;
                case "C": // Load all cities from file
                    try {
                        System.out.print("Enter file name: ");
                        String filename = sc.next();
                        mySigmaAir.loadAllCities(filename);
                    }
                    catch (Exception ex) {
                        System.out.println("There was an error in your input. Please try again.");
                    }
                    break;
                case "D": // Load all connections from file
                    try {
                        System.out.print("Enter file name: ");
                        String filename = sc.next();
                        mySigmaAir.loadAllConnections(filename);
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                        System.out.println("There was an error in your input. Please try again.");
                    }
                    break;
                case "E": // Print all Cities
                    while (true) {
                        try {
                            System.out.println("\nPlease select the sorting system for Cities: " +
                                    "\nMenu: \n" +
                                    "(EA) Sort Cities by Name \n" +
                                    "(EB) Sort Cities by Latitude \n" +
                                    "(EC) Sort Cities by Longtitude \n" +
                                    "(Q) Quit \n");
                            System.out.print("\nEnter a selection: ");
                            userInput = sc.next().toUpperCase();

                            if (userInput.equals("EA")) {
                                mySigmaAir.printAllCities(new NameComparator());
                            } else if (userInput.equals("EB")) {
                                mySigmaAir.printAllCities(new LatComparator());
                            } else if (userInput.equals("EC")) {
                                mySigmaAir.printAllCities(new LngComparator());
                            } else if (userInput.equals("Q")) {
                                System.out.println("\nExiting submenu...");
                                break;
                            } else {
                                System.out.println("\nInvalid input. Please try again.");
                            }
                        } catch (Exception ex) {
                            System.out.println("There was an error in your input. Please try again!");
                        }
                    }
                    break;
                case "F": // Print all Connections
                    mySigmaAir.printAllConnections();
                    break;
                case "G": // Remove a Connection
                    try {
                        System.out.print("Enter source city: ");
                        sc.nextLine();
                        String cityFrom = sc.nextLine();
                        System.out.print("Enter destination city: ");
                        String cityTo = sc.nextLine();
                        mySigmaAir.removeConnection(cityFrom, cityTo);
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                        System.out.println("There was an error in your input. Please try again.");
                    }
                    break;
                case "H": // Find shortest path
                    try {
                        System.out.print("Enter source city: ");
                        sc.nextLine();
                        String cityFrom = sc.nextLine();
                        System.out.print("Enter destination city: ");
                        String cityTo = sc.nextLine();
                        System.out.println("Shortest path from " + cityFrom + " to " + cityTo + ": ");
                        System.out.println(mySigmaAir.shortestPath(cityFrom, cityTo));
                    }
                    catch (Exception ex) {
                        System.out.println("There was an error in your input. Please try again.");
                    }
                    break;
                case "Q": // Terminates the program and save the data to DATA_FILE
                    // end the program if userInput is "Q"
                    boolean quitSafely = true;
                    try { // store HashedLibrary object in .obj
                        FileOutputStream file = new FileOutputStream(DATA_FILE);
                        ObjectOutputStream fout = new ObjectOutputStream(file);
                        fout.writeObject(mySigmaAir); // Writes mySigmaAir to DATA_FILE
                        fout.close();
                    }
                    catch (IOException ex){
                        System.out.println("\nUnable to save the current SigmaAir instance to " + DATA_FILE + ".");
                        quitSafely = false;
                    }

                    if (!quitSafely) { // if unable to save, ask to continue with program or quit without saving
                        System.out.print("\nThere was an error in saving your current SigmaAir instance. " +
                                "Continue quitting? " +
                                "(Y/N): ");
                        userInput = sc.next().toUpperCase(); // get the user input and set to uppercase
                        if (userInput.toUpperCase().equals("Y")) {
                            System.out.println("\nProgram terminating without saving...");
                            System.exit(0);
                        }
                        else {
                            System.out.println("\nContinuing with the program...");
                        }
                    }
                    else {
                        System.out.println("\nCurrent data saved in " + DATA_FILE + ".");
                        System.out.println("Program terminating normally...");
                        System.exit(0);
                    }
                    break;
                default: // if userInput is none of the above
                    System.out.println("\nInvalid Selected Operation. Please try again!\n");
                    break;
            } // end switch
        } // end while

    }
}
