package richardvalvona.uk.android.russianconjugations.database;

import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.Word;

import static richardvalvona.uk.android.russianconjugations.database.AdjectivesTable.COLUMN_INDEX_INS_ALT_F;

/**
 * Created by richard on 28/03/17.
 */

public class AdjectivesTable extends DatabaseTable {

    private final static AssignedArrayList<DatabaseColumn> columns = new AssignedArrayList<>();

    public final static int COLUMN_INDEX_TAG = columns.addItem(new DatabaseColumn("tag", "VARCHAR").setLength(4));

    public final static int COLUMN_INDEX_NOM_M = columns.addItem(new DatabaseColumn("dec_nom_m", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_GEN_M = columns.addItem(new DatabaseColumn("dec_gen_m", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_DAT_M = columns.addItem(new DatabaseColumn("dec_dat_m", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_INS_M = columns.addItem(new DatabaseColumn("dec_ins_m", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_PRE_M = columns.addItem(new DatabaseColumn("dec_pre_m", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_NOM_F = columns.addItem(new DatabaseColumn("dec_nom_f", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_ACC_F = columns.addItem(new DatabaseColumn("dec_acc_f", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_GEN_F = columns.addItem(new DatabaseColumn("dec_gen_f", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_INS_ALT_F = columns.addItem(new DatabaseColumn("dec_int_alt_f", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_NOM_N = columns.addItem(new DatabaseColumn("dec_nom_n", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_NOM_P = columns.addItem(new DatabaseColumn("dec_nom_p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_GEN_P = columns.addItem(new DatabaseColumn("dec_gen_p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_DAT_P = columns.addItem(new DatabaseColumn("dec_dat_p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_INS_P = columns.addItem(new DatabaseColumn("dec_ins_p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_SHORT_M = columns.addItem(new DatabaseColumn("dec_short_m", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_SHORT_F = columns.addItem(new DatabaseColumn("dec_short_f", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_SHORT_N = columns.addItem(new DatabaseColumn("dec_short_n", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_SHORT_P = columns.addItem(new DatabaseColumn("dec_short_p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_COMP = columns.addItem(new DatabaseColumn("dec_comp", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_SUP = columns.addItem(new DatabaseColumn("dec_sup", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_ENGLISH_WORDS = columns.addItem(new DatabaseColumn("english_words", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_ANTONYMS = columns.addItem(new DatabaseColumn("antonyms", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_CATEGORIES = columns.addItem(new DatabaseColumn("categories", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_DERIVED_TERMS = columns.addItem(new DatabaseColumn("derived_terms", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_RELATED_TERMS = columns.addItem(new DatabaseColumn("related_terms", "VARCHAR").setLength(80));


    private static int[] indicesForMatching = new int[] {
            COLUMN_INDEX_NOM_M, COLUMN_INDEX_GEN_M, COLUMN_INDEX_DAT_M, COLUMN_INDEX_INS_M, COLUMN_INDEX_PRE_M,
            COLUMN_INDEX_NOM_F, COLUMN_INDEX_ACC_F, COLUMN_INDEX_GEN_F, COLUMN_INDEX_INS_ALT_F,
            COLUMN_INDEX_NOM_N,
            COLUMN_INDEX_NOM_P, COLUMN_INDEX_GEN_P, COLUMN_INDEX_DAT_P, COLUMN_INDEX_INS_P,
            COLUMN_INDEX_SHORT_M, COLUMN_INDEX_SHORT_F, COLUMN_INDEX_SHORT_N, COLUMN_INDEX_SHORT_P,
            COLUMN_INDEX_COMP, COLUMN_INDEX_SUP,
    };

    AdjectivesTable() {
        super(TABLE_NAME_ADJECTIVES, columns, Language.russian);
    }

    public static int getColumnIndex(int gender, int wordCase, int singularOrPlural, boolean animate, boolean alt) {
        if (singularOrPlural == NUMBER_P) {
            switch (wordCase) {
                case CASE_N: return COLUMN_INDEX_NOM_P;
                case CASE_A: return animate ? COLUMN_INDEX_GEN_P : COLUMN_INDEX_NOM_P;
                case CASE_G: return COLUMN_INDEX_GEN_P;
                case CASE_D: return COLUMN_INDEX_DAT_P;
                case CASE_I: return COLUMN_INDEX_INS_P;
                case CASE_P: return COLUMN_INDEX_GEN_P;
            }
        }
        else {
            if (gender == GENDER_M) {
                switch (wordCase) {
                    case CASE_N: return COLUMN_INDEX_NOM_M;
                    case CASE_A: return animate ? COLUMN_INDEX_GEN_M : COLUMN_INDEX_NOM_M;
                    case CASE_G: return COLUMN_INDEX_GEN_M;
                    case CASE_D: return COLUMN_INDEX_DAT_M;
                    case CASE_I: return COLUMN_INDEX_INS_M;
                    case CASE_P: return COLUMN_INDEX_PRE_M;
                }
            }
            else if (gender == GENDER_F) {
                switch (wordCase) {
                    case CASE_N: return COLUMN_INDEX_NOM_F;
                    case CASE_A: return COLUMN_INDEX_ACC_F;
                    case CASE_G: return COLUMN_INDEX_GEN_F;
                    case CASE_D: return COLUMN_INDEX_GEN_F;
                    case CASE_I: return alt ? COLUMN_INDEX_INS_ALT_F : COLUMN_INDEX_GEN_F;
                    case CASE_P: return COLUMN_INDEX_GEN_F;
                }
            }
            else if (gender == GENDER_N) {
                switch (wordCase) {
                    case CASE_N: return COLUMN_INDEX_NOM_N;
                    case CASE_A: return COLUMN_INDEX_NOM_N;
                    case CASE_G: return COLUMN_INDEX_GEN_M;
                    case CASE_D: return COLUMN_INDEX_DAT_M;
                    case CASE_I: return COLUMN_INDEX_INS_M;
                    case CASE_P: return COLUMN_INDEX_PRE_M;
                }
            }
        }

        return -1;
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
        return word.getWordForm(COLUMN_INDEX_NOM_M);
    }

    @Override
    public int getTranslationsColumnIndex(Language lang) throws NullPointerException {
        if (lang == Language.english) return COLUMN_INDEX_ENGLISH_WORDS;
        return 0;
    }
}
