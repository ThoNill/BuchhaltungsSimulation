package buchhaltung.modell;

/**
 * Objekt mit eindeutiger Objektid
 * 
 * @author Thomas Nill
 * 
 */
public class UID {
    private static long s_id = 1;
    private long id;

    public UID() {
        id = s_id++;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof UID) {
            return ((UID) obj).id == id;
        }
        ;
        return false;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}
