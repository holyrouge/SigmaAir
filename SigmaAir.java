/**
 * The <code>SigmaAir</code> class contains methods to
 * represent an application to store and analyze information
 * about connections between airports.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import com.cse214.geocoder.GeocodeResponse;
import com.cse214.geocoder.Geocoder;
import java.util.Collections;

public class SigmaAir implements Serializable {
    public static final int MAX_CITIES = 100; // max number of cities
    private ArrayList<City> cities = new ArrayList<>(); // array list of cities
    private double[][] connections = new double[MAX_CITIES][MAX_CITIES]; // possible connections betweem cities
    private int nextAvailIndex; // next free spot in connections

    /**
     * Returns an instance of SigmaAir.
     *
     * <dt><b>Postcondition:</b><db>
     *     This SigmaAir object and all declared variables have been initialized.
     */
    public SigmaAir() {
        // initializes adjacency matrix
        connections = new double[MAX_CITIES][MAX_CITIES];
        nextAvailIndex = 0;
    }

    /**
     * Adds the City with the given name to the list of cities.
     *
     * @param city
     *      The name of a city
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>SigmaAir</code> has been initialized.
     */
    public void addCity(String city){
        try {
            Geocoder geocoder = new Geocoder();
            GeocodeResponse geocodeResponse;
            String addr;
            double lat;
            double lng;

            geocodeResponse = geocoder.geocode(city);
            addr = geocodeResponse.getFormattedAddress();
            lat = geocodeResponse.getLat();
            lng = geocodeResponse.getLng();

            LatLng newLocation = new LatLng(lat, lng);
            City newCity = new City(city, newLocation, nextAvailIndex);
            int i = -1;

            for (int j = 0; j < cities.size(); j++) {
                if (cities.get(j).getName().equals(city)) {
                    i = j;
                }
            }

            if (i >= 0) {
                System.out.println(newCity.getName() + " already exists within the list of cities.");
            }
            else {
                cities.add(newCity);
                nextAvailIndex++;
                System.out.println(newCity.getName() + " has been added: (" + lat + ", " + lng + ")");
            }
        }
        catch (Exception e) {
            System.out.println("Invalid City Name. City could not be added.");
        }
    }

    /**
     * Adds a connection from first given city to the second given city.
     *
     * @param cityFrom
     *      Name of the source city
     * @param cityTo
     *      Name of the destination city
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>SigmaAir</code> has been initialized.
     */
    public void addConnection(String cityFrom, String cityTo) {
        int i = -1, j = -1;
        for (int k = 0; k < cities.size(); k++) {
            if (cities.get(k).getName().equals(cityFrom)) {
                i = k;
            }

            if (cities.get(k).getName().equals(cityTo)) {
                j = k;
            }
        }

        if (i >= 0 && j >= 0) {

            connections[i][j] = LatLng.calculateDistance(cities.get(i).getLocation(), cities.get(j).getLocation());
            System.out.println(cityFrom + " --> " + cityTo + " added: " + connections[i][j]);
        }
        else {
            System.out.println("Either " + cityFrom + " or " + cityTo + " have not been loaded into the system.");
        }
    }

    /**
     * Removes the connection from first given city to the second given city, if it exists.
     *
     * @param cityFrom
     *      Name of the source city
     * @param cityTo
     *      Name of the destination city
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>SigmaAir</code> has been initialized.
     */
    public void removeConnection(String cityFrom, String cityTo) {
        int i = -1, j = -1;
        for (int k = 0; k < cities.size(); k++) {
            if (cities.get(k).getName().equals(cityFrom)) {
                i = k;
            }

            if (cities.get(k).getName().equals(cityTo)) {
                j = k;
            }
        }

        if (i >= 0 && j >= 0) {
            connections[i][j] = Double.POSITIVE_INFINITY;
            System.out.println("Connection from " + cityFrom + " to " + cityTo + " has been removed!");
        }
        else {
            System.out.println("Either " + cityFrom + " or " + cityTo + " have not been loaded into the system.");
        }
    }

    /**
     * Returns the shortest path from the first given city to the second given city.
     *
     * @param cityFrom
     *      Name of the source city
     * @param cityTo
     *      Name of the destination city
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>SigmaAir</code> has been initialized.
     *
     * @return
     *      Returns a String with the connections necessary for the shortest path from <code>cityFrom</code> to
     *      <code>cityTo</code>.
     */
    public String shortestPath(String cityFrom, String cityTo) {
        int x = -1, y = -1;
        for (int k = 0; k < cities.size(); k++) {
            if (cities.get(k).getName().equals(cityFrom)) {
                x = k;
            }

            if (cities.get(k).getName().equals(cityTo)) {
                y = k;
            }
        }

        if (x >= 0 && y >= 0) {
            String output;
            double totalDistance = 0;
            ArrayList<String> path = new ArrayList<>();

            return cityFrom + " --> " + cityTo + " : " + connections[x][y];
        }
        else {
            return "Either " + cityFrom + " or " + cityTo + " have not been loaded into the system.";
        }
    }

    /**
     * Prints all the current inputted Cities to the console in the order given by the comparator
     *
     * @param comp
     *      Comparator with which to order the list of cities
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>SigmaAir</code> has been initialized.
     */
    public void printAllCities(Comparator comp) {
        System.out.println("Cities:\n" +
                "City Name                   Latitude        Longitude\n" +
                "-------------------------------------------------------------");
        Collections.sort(cities, comp);
        for (int i = 0; i < cities.size(); i++) {
            System.out.println(cities.get(i).toString());
        }
    }

    /**
     * Prints all the current inputted connections.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>SigmaAir</code> has been initialized.
     */
    public void printAllConnections() {
        System.out.println("Connections:\n" +
                "Route                               Distance\n" +
                "----------------------------------------------------------");
        for (int i = 0; i < connections.length; i++) {
            for (int j = 0; j < connections[i].length; j++) {
                if (connections[i][j] > 0.00 && connections[i][j] != Double.POSITIVE_INFINITY) {
                    System.out.println(String.format("%s --> %-20s %5.2f", cities.get(i).getName(),
                            cities.get(j).getName(), connections[i][j]));
                }
            }
        }

    }

    /**
     * Loads all cities into the system from the given file.
     *
     * @param filename
     *      The name of the file with the list of cities
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>SigmaAir</code> has been initialized.
     */
    public void loadAllCities(String filename) {
        Scanner reader = null;
        try {
            File file = new File(filename);
            reader = new Scanner(file);
        }
        catch (IOException ex) {
            System.out.println("Given file could not be found.");
        }

        String line;
        while (reader.hasNextLine()) {
            line = reader.nextLine();
            addCity(line);
        }
    }

    /**
     * Loads all connections into the system from the given file.
     *
     * @param filename
     *      The name of the file with the list of connections
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>SigmaAir</code> has been initialized.
     */
    public void loadAllConnections(String filename) {
        Scanner reader = null;
        try {
            File file = new File(filename);
            reader = new Scanner(file);
        }
        catch (IOException ex) {
            System.out.println("Given file could not be found.");
        }

        String line;
        while (reader.hasNextLine()) {
            line = reader.nextLine();
            String[] connectionCities = line.split(",");

            addConnection(connectionCities[0], connectionCities[1]);
        }
    }
}
