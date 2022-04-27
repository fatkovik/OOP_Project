import java.util.ArrayList;
import java.util.List;

public class ComparatorForSort <T extends Comparable<? super T>> {

    public List<T> mergesort(List<T> list) {

        if (list.size() <= 1)
            return list;
        else {
            List<T> left = new ArrayList<T>();
            List<T> right = new ArrayList<T>();

            int divide = list.size() / 2;
            for (int i = 0; i < divide; i++)
                left.add(list.get(i));
            for (int i = divide; i < list.size(); i++)
                right.add(list.get(i));

            return merge(mergesort(left), mergesort(right));
        }
    }

    //used by mergesort to do merging
    private List<T> merge(List<T> a, List<T> b) {
        List<T> sortedList = new ArrayList<T>();
        int leftIterator = 0, rightIterator = 0;

        while (leftIterator + 1 <= a.size() || rightIterator + 1 <= b.size())
            if (leftIterator + 1 <= a.size() && rightIterator + 1 <= b.size()) {
                if (a.get(leftIterator).compareTo(b.get(rightIterator)) <= 0.0) {
                    sortedList.add(a.get(leftIterator));
                    leftIterator++;
                } else {
                    sortedList.add(b.get(rightIterator));
                    rightIterator++;
                }
            } else if (leftIterator + 1 <= a.size()) {
                sortedList.add(a.get(leftIterator));
                leftIterator++;
            } else if (rightIterator + 1 <= b.size()) {
                sortedList.add(b.get(rightIterator));
                rightIterator++;
            }

        return sortedList;
    }
}