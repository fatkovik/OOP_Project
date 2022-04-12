import java.util.ArrayList;
import java.util.List;

public class ComparatorForSort <T extends Comparable<? super T>> {

    public List<T> mergesort(List<T> list) {

        if (list.size() <= 1)
            return list;
        else {
            List<T> left = new ArrayList<T>();
            List<T> right = new ArrayList<T>();

            int middle = list.size() / 2; //int division
            for (int i = 0; i < middle; i++)
                left.add(list.get(i));
            for (int i = middle; i < list.size(); i++)
                right.add(list.get(i));

            return merge(mergesort(left), mergesort(right));
        }
    }

    //used by mergesort to do merging
    private List<T> merge(List<T> a, List<T> b) {
        List<T> ret = new ArrayList<T>();    
        int a_idx = 0, b_idx = 0;            

        while (a_idx + 1 <= a.size() || b_idx + 1 <= b.size())
            if (a_idx + 1 <= a.size() && b_idx + 1 <= b.size()) {
                if (a.get(a_idx).compareTo(b.get(b_idx)) <= 0.0) {
                    ret.add(a.get(a_idx));
                    a_idx++;
                } else {
                    ret.add(b.get(b_idx));
                    b_idx++;
                }
            } else if (a_idx + 1 <= a.size()) {
                ret.add(a.get(a_idx));
                a_idx++;
            } else if (b_idx + 1 <= b.size()) {
                ret.add(b.get(b_idx));
                b_idx++;
            }

        return ret;
    }
    public static void main(String[] args) {
        ComparatorForSort<Integer> p1 = new ComparatorForSort<>();

        ArrayList<Integer> l1 = new ArrayList<>();

        l1.add(12);
        l1.add(43);
        l1.add(77);

        l1 = (ArrayList<Integer>) p1.mergesort(l1);

        for (Integer element : l1) {
            System.out.println(element);
        }
    }

}
