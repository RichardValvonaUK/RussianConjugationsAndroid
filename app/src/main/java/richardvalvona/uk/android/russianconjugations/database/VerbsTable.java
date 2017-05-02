package richardvalvona.uk.android.russianconjugations.database;

import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.Word;

/**
 * Created by richard on 29/03/17.
 */

public class VerbsTable extends DatabaseTable {

    private final static AssignedArrayList<DatabaseColumn> columns = new AssignedArrayList<>();

    public final static String ASPECT_PERFECTIVE = "pf";
    public final static String ASPECT_IMPERFECTIVE = "impf";
    public final static String ASPECT_BOTH = "both";

    public final static int COLUMN_INDEX_TAG = columns.addItem(new DatabaseColumn("tag", "VARCHAR").setLength(4));
    public final static int COLUMN_INDEX_INFINITIVE = columns.addItem(new DatabaseColumn("infinitive", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_ASPECT = columns.addItem(new DatabaseColumn("aspect", "VARCHAR").setLength(1));
    public final static int COLUMN_INDEX_PRES_1S = columns.addItem(new DatabaseColumn("pres_1s", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PRES_2S = columns.addItem(new DatabaseColumn("pres_2s", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PRES_3S = columns.addItem(new DatabaseColumn("pres_3s", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PRES_1P = columns.addItem(new DatabaseColumn("pres_1p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PRES_2P = columns.addItem(new DatabaseColumn("pres_2p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PRES_3P = columns.addItem(new DatabaseColumn("pres_3p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_FUT_1S = columns.addItem(new DatabaseColumn("fut_1s", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_FUT_2S = columns.addItem(new DatabaseColumn("fut_2s", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_FUT_3S = columns.addItem(new DatabaseColumn("fut_3s", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_FUT_1P = columns.addItem(new DatabaseColumn("fut_1p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_FUT_2P = columns.addItem(new DatabaseColumn("fut_2p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_FUT_3P = columns.addItem(new DatabaseColumn("fut_3p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PAST_M = columns.addItem(new DatabaseColumn("past_m", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PAST_F = columns.addItem(new DatabaseColumn("past_f", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PAST_N = columns.addItem(new DatabaseColumn("past_n", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PAST_P = columns.addItem(new DatabaseColumn("past_p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_IMP_S = columns.addItem(new DatabaseColumn("imp_s", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_IMP_P = columns.addItem(new DatabaseColumn("imp_p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PRES_ACT = columns.addItem(new DatabaseColumn("pres_act", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PAST_ACT = columns.addItem(new DatabaseColumn("past_act", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PRES_ADV = columns.addItem(new DatabaseColumn("pres_adv", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PAST_ADV = columns.addItem(new DatabaseColumn("past_adv", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PAST_ADV_SHORT = columns.addItem(new DatabaseColumn("past_adv_short", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PRES_PAS = columns.addItem(new DatabaseColumn("pres_pas", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PAST_PAS = columns.addItem(new DatabaseColumn("past_pas", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PARTNERS = columns.addItem(new DatabaseColumn("partners", "VARCHAR").setLength(120));
    public final static int COLUMN_INDEX_ENGLISH_WORDS = columns.addItem(new DatabaseColumn("english_words", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_ANTONYMS = columns.addItem(new DatabaseColumn("antonyms", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_CATEGORIES = columns.addItem(new DatabaseColumn("categories", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_DERIVED_TERMS = columns.addItem(new DatabaseColumn("derived_terms", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_RELATED_TERMS = columns.addItem(new DatabaseColumn("related_terms", "VARCHAR").setLength(80));


    private static int[] indicesForMatching = new int[] {
            COLUMN_INDEX_INFINITIVE,
            COLUMN_INDEX_PRES_1S, COLUMN_INDEX_PRES_2S, COLUMN_INDEX_PRES_3S,
            COLUMN_INDEX_PRES_1P, COLUMN_INDEX_PRES_2P, COLUMN_INDEX_PRES_3P,
            COLUMN_INDEX_FUT_1S, COLUMN_INDEX_FUT_2S, COLUMN_INDEX_FUT_3S,
            COLUMN_INDEX_FUT_1P, COLUMN_INDEX_FUT_2P, COLUMN_INDEX_FUT_3P,
            COLUMN_INDEX_PAST_M, COLUMN_INDEX_PAST_F, COLUMN_INDEX_PAST_N, COLUMN_INDEX_PAST_P,
            COLUMN_INDEX_IMP_S, COLUMN_INDEX_IMP_P,
            COLUMN_INDEX_PRES_ACT, COLUMN_INDEX_PAST_ACT,
            COLUMN_INDEX_PRES_ADV, COLUMN_INDEX_PAST_ADV, COLUMN_INDEX_PAST_ADV_SHORT,
            COLUMN_INDEX_PRES_PAS, COLUMN_INDEX_PAST_PAS,
    };

    VerbsTable() {
        super(TABLE_NAME_VERBS, columns, Language.russian);
    }


    @Override
    public int[] wordFormIndicesForMatching() {
        return indicesForMatching;
    }

    @Override
    public DatabaseColumn getTagColumn() {
        return columns.getItem(COLUMN_INDEX_TAG);
    }

    @Override
    public String getDictForm(Word word) {
        return word.getWordForm(COLUMN_INDEX_INFINITIVE);
    }

    @Override
    public int getTranslationsColumnIndex(Language lang) {
        if (lang == Language.english) return COLUMN_INDEX_ENGLISH_WORDS;
        return 0;
    }
}
