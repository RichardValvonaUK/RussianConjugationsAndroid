package richardvalvona.uk.android.russianconjugations.database;

import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.Word;

/**
 * Created by richard on 02/05/17.
 */

public class NounsWithRemnantCasesTable extends DatabaseTable {

    private final static AssignedArrayList<DatabaseColumn> columns = new AssignedArrayList<>();

    public final static int COLUMN_INDEX_TAG = columns.addItem(new DatabaseColumn("tag", "VARCHAR").setLength(4));
    public final static int COLUMN_INDEX_CASE = columns.addItem(new DatabaseColumn("dec_case", "VARCHAR").setLength(3));
    public final static int COLUMN_INDEX_S_N = columns.addItem(new DatabaseColumn("dec_s_n", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_S_FOR_CASE = columns.addItem(new DatabaseColumn("dec_s_for_case", "VARCHAR").setLength(DEFAULT_LENGTH));

    NounsWithRemnantCasesTable() {
        super(TABLE_NAME_NOUNS_WITH_REMNANTS, columns, Language.russian);
    }

    @Override
    public int[] wordFormIndicesForMatching() {
        return new int[0];
    }

    @Override
    public DatabaseColumn getTagColumn() {
        return null;
    }

    @Override
    public String getDictForm(Word word) {
        return null;
    }

    @Override
    public int getTranslationsColumnIndex(Language lang) {
        return 0;
    }
}
