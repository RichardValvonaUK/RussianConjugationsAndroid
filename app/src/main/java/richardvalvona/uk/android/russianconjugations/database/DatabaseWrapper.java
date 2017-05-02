package richardvalvona.uk.android.russianconjugations.database;

import android.content.ContentValues;

/**
 * Created by richard on 01/04/17.
 */

public interface DatabaseWrapper {
    long insert(String table, String nullColumnHack, ContentValues values);
}
