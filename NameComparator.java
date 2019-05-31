/**
 * The <code>NameComparator</code> class implements the
 * Comparator interface to compare Cities based on their names.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */
import java.util.Comparator;

public class NameComparator implements Comparator {
    /**
     * Returns the comparison of the given City objects based on their names.
     *
     * @param o1
     *      The first City object
     * @param o2
     *      The second City object
     *
     * <dt><b>Precondition:</b><db>
     *     The <code>o1</code> and <code>o2</code> are instances of <code>City</code>.
     *
     * @return
     *      Returns the comparison of the given City objects based on their names.
     */
    public int compare(Object o1, Object o2) {
        City c1 = (City) o1;
        City c2 = (City) o2;
        return c1.getName().compareTo(c2.getName());
    }
}
