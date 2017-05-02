package richardvalvona.uk.android.russianconjugations.database;

/**
 * Created by richard on 29/10/16.
 */

public class DatabaseColumn {
    private DatabaseTable table;
    private int index;

    public final String name;
    public final String type;

    private Integer length = null;

    DatabaseColumn(String name, String type) {
        this.table = null;
        this.name = name;
        this.type = type;
    }

    public String getCreateCode() {

        String lengthCode;

        if (length == null) {
            lengthCode = "";
        }
        else {
            lengthCode = "(" + length + ")";
        }

        return name + " " + type + lengthCode;
    }

    public void setTable(DatabaseTable table) {
        this.table = table;
    }

    public DatabaseTable getTable() {
        return table;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public DatabaseColumn setLength(Integer length) {
        this.length = length;
        return this;
    }
}
