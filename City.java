import java.io.Serializable;

/**
 * The <code>City</code> class represents information
 * about a city within an airport analysis application. It
 * contains data about the city's name and location.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */

public class City implements Serializable {
    private static int cityCount; // the number of City objects
    private String name; // the name of the City
    private LatLng location; // the lat/lng coordinates of the City
    private int indexPos; // the index position of the City

    /**
     * Returns an instance of City.
     *
     * <dt><b>Postcondition:</b><db>
     *     This City has been initialized with template values.
     */
    public City() {
        cityCount++;
        name = "";
        location = new LatLng();
        indexPos = 0;
    }

    /**
     * Returns an instance of City.
     *
     * @param name
     *      The name of the City
     * @param location
     *      The lat/lng coordinates of the City
     * @param indexPos
     *      The position of the City in the adjacency matrix
     *
     * <dt><b>Postcondition:</b><db>
     *     This City has been initialized with given values. The <code>indexPos</code> is a 0 or greater.
     *
     * @throws IllegalArgumentException
     *      Indicates that given <code>indexPos</code> is less than 0.
     */
    public City(String name, LatLng location, int indexPos) throws IllegalArgumentException {
        cityCount = indexPos + 1;
        this.name = name;
        this.location = location;
        if (indexPos < 0)
            new IllegalArgumentException("Invalid indexPos. Must be 0 or greater.");
        this.indexPos = indexPos;
    }

    /**
     * Returns the number of City objects.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>City</code> has been initialized.
     *
     * @return
     *      Returns the number of City objects.
     */
    public static int getCityCount() {
        return cityCount;
    }

    /**
     * Returns the name of the City.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>City</code> has been initialized.
     *
     * @return
     *      Returns a String with the City's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the City.
     *
     * @param name
     *      The name of the City
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>City</code> has been initialized.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the latitude and longitude of the City.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>City</code> has been initialized.
     *
     * @return
     *      Returns a LatLng object with the City's latitude and longitude.
     */
    public LatLng getLocation() {
        return location;
    }

    /**
     * Sets the latitude and longitude coordinates of the City.
     *
     * @param location
     *      The lat/lng coordinates of the City.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>City</code> has been initialized.
     */
    public void setLocation(LatLng location) {
        this.location = location;
    }

    /**
     * Returns the position of the City in the adjacency matrix.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>City</code> has been initialized.
     *
     * @return
     *      Returns the position of the City in the adjacency matrix.
     */
    public int getIndexPos() {
        return indexPos;
    }

    /**
     * Sets the position of the City in the adjacency matrix.
     *
     * @param indexPos
     *      The position of the City in the adjacency matrix.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>City</code> has been initialized. The <code>indexPos</code> is a 0 or greater.
     *
     * @throws IllegalArgumentException
     *      Indicates that given <code>indexPos</code> is less than 0.
     */
    public void setIndexPos(int indexPos) throws IllegalArgumentException {
        if (indexPos < 0)
            new IllegalArgumentException("Invalid indexPos. Must be 0 or greater.");
        this.indexPos = indexPos;
    }

    /**
     * Returns the information on the City.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>City</code> has been initialized.
     *
     * @return
     *      Returns the information on the City as a formatted String.
     */
    public String toString() {
        return String.format("%-16s%20f%20f", name, location.getLat(), location.getLng());
    }
}
