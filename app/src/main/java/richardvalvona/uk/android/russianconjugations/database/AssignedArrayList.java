package richardvalvona.uk.android.russianconjugations.database;

import java.util.ArrayList;

/**
 * Created by richard on 29/03/17.
 */

public class AssignedArrayList<T> {

    private ArrayList<T> items = new ArrayList<>();

    public int addItem(T item) {
        items.add(item);
        return items.size() - 1;
    }

    public T getItem(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }
}

