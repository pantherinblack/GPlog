import java.util.Comparator;

public class PLogDateComparator implements Comparator<PLog> {
    @Override
    public int compare(PLog o1, PLog o2) {
        int compare = o1.getDate().compareTo(o2.getDate());
        if (compare==0){
            compare = o1.getDocName().compareTo(o2.getDocName());
        }
        return compare;
    }
}
