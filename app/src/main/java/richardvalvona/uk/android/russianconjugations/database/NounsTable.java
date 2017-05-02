package richardvalvona.uk.android.russianconjugations.database;

import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.Word;

/**
 * Created by richard on 29/10/16.
 */

public class NounsTable extends DatabaseTable {

    private final static AssignedArrayList<DatabaseColumn> columns = new AssignedArrayList<>();

    public final static int COLUMN_INDEX_TAG = columns.addItem(new DatabaseColumn("tag", "VARCHAR").setLength(4));
    public final static int COLUMN_INDEX_DEC_TYPE = columns.addItem(new DatabaseColumn("dec_type", "VARCHAR").setLength(1));
    public final static int COLUMN_INDEX_GENDER = columns.addItem(new DatabaseColumn("gender", "VARCHAR").setLength(1));
    public final static int COLUMN_INDEX_ANIMATE = columns.addItem(new DatabaseColumn("is_animate", "VARCHAR").setLength(1));
    public final static int COLUMN_INDEX_S_N = columns.addItem(new DatabaseColumn("dec_s_n", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_S_A = columns.addItem(new DatabaseColumn("dec_s_a", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_S_G = columns.addItem(new DatabaseColumn("dec_s_g", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_S_D = columns.addItem(new DatabaseColumn("dec_s_d", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_S_I = columns.addItem(new DatabaseColumn("dec_s_i", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_S_I_ALT = columns.addItem(new DatabaseColumn("dec_s_i_alt", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_S_P = columns.addItem(new DatabaseColumn("dec_s_p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_P_N = columns.addItem(new DatabaseColumn("dec_p_n", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_P_A = columns.addItem(new DatabaseColumn("dec_p_a", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_P_G = columns.addItem(new DatabaseColumn("dec_p_g", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_P_D = columns.addItem(new DatabaseColumn("dec_p_d", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_P_I = columns.addItem(new DatabaseColumn("dec_p_i", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_P_P = columns.addItem(new DatabaseColumn("dec_p_p", "VARCHAR").setLength(DEFAULT_LENGTH));
    public final static int COLUMN_INDEX_ENGLISH_WORDS = columns.addItem(new DatabaseColumn("english_words", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_ANTONYMS = columns.addItem(new DatabaseColumn("antonyms", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_CATEGORIES = columns.addItem(new DatabaseColumn("categories", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_DERIVED_TERMS = columns.addItem(new DatabaseColumn("derived_terms", "VARCHAR").setLength(80));
    public final static int COLUMN_INDEX_RELATED_TERMS = columns.addItem(new DatabaseColumn("related_terms", "VARCHAR").setLength(80));

    // These terms are not in the nouns table but can be treated as such with a join from another table
//    public final static int COLUMN_INDEX_S_PAR = columns.addItem(new DatabaseColumn("dec_s_par", "VARCHAR").setLength(DEFAULT_LENGTH).setAsVirtual());
//    public final static int COLUMN_INDEX_S_VOC = columns.addItem(new DatabaseColumn("dec_s_voc", "VARCHAR").setLength(DEFAULT_LENGTH).setAsVirtual());
//    public final static int COLUMN_INDEX_S_LOC = columns.addItem(new DatabaseColumn("dec_s_loc", "VARCHAR").setLength(DEFAULT_LENGTH).setAsVirtual());


    private static int[] indicesForMatching = new int[] {
        COLUMN_INDEX_S_N, COLUMN_INDEX_S_A, COLUMN_INDEX_S_G,
        COLUMN_INDEX_S_D, COLUMN_INDEX_S_I, COLUMN_INDEX_S_I_ALT, COLUMN_INDEX_S_P,
        COLUMN_INDEX_P_N, COLUMN_INDEX_P_A, COLUMN_INDEX_P_G,
        COLUMN_INDEX_P_D, COLUMN_INDEX_P_I, COLUMN_INDEX_P_P,
    };

    public static int getColumnIndex(int wordCase, int singularOrPlural, int gender, boolean animate, boolean alt) {
        if (alt) {
            switch (singularOrPlural) {
                case NUMBER_S: {
                    switch (wordCase) {
                        case CASE_I:
                            return COLUMN_INDEX_S_I_ALT;
                    }
                    break;
                }
            }
        }
        else {
            switch (singularOrPlural) {
                case NUMBER_S: {
                    switch (wordCase) {
                        case CASE_N:
                            return COLUMN_INDEX_S_N;
                        case CASE_A: {
                            switch (gender) {
                                case GENDER_M:
                                    return animate ? COLUMN_INDEX_S_G : COLUMN_INDEX_S_N;
                                case GENDER_N:
                                    return COLUMN_INDEX_S_N;
                                default:
                                    return COLUMN_INDEX_S_A;
                            }
                        }
                        case CASE_G:
                            return COLUMN_INDEX_S_G;
                        case CASE_D:
                            return COLUMN_INDEX_S_D;
                        case CASE_I:
                            return COLUMN_INDEX_S_I;
                        case CASE_P:
                            return COLUMN_INDEX_S_P;
                    }
                    break;
                }
                case NUMBER_P: {
                    switch (wordCase) {
                        case CASE_N:
                            return COLUMN_INDEX_P_N;
                        case CASE_A:
                            return animate ? COLUMN_INDEX_P_G : COLUMN_INDEX_P_N;
                        case CASE_G:
                            return COLUMN_INDEX_P_G;
                        case CASE_D:
                            return COLUMN_INDEX_P_D;
                        case CASE_I:
                            return COLUMN_INDEX_P_I;
                        case CASE_P:
                            return COLUMN_INDEX_P_P;
                    }
                    break;
                }
            }
        }

        return -1;
    }

    NounsTable() {
        super(TABLE_NAME_NOUNS, columns, Language.russian);
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
        String nomS = word.getWordForm(COLUMN_INDEX_S_N);
        String nomP = word.getWordForm(COLUMN_INDEX_P_N);

        return nomS.equals("") ? nomP : nomS;
    }

    @Override
    public int getTranslationsColumnIndex(Language lang) {
        if (lang == Language.english) return COLUMN_INDEX_ENGLISH_WORDS;
        return 0;
    }

    public boolean isAnimate(Word word) {
        return word.getWordForm(COLUMN_INDEX_ANIMATE).equals("1");
    }

    public boolean singularExists(Word word) {
        return !word.getWordForm(COLUMN_INDEX_S_N).equals("");
    }

    public boolean pluralExists(Word word) {
        return !word.getWordForm(COLUMN_INDEX_P_N).equals("");
    }

    public boolean isIndeclinable(Word word) {

        int nonEmptyWordCount = 0;

        for (int i=COLUMN_INDEX_S_N; i<=COLUMN_INDEX_P_P; i++) {
            String nextWordForm = word.getWordForm(i);

            if (!nextWordForm.equals("")) {
                nonEmptyWordCount++;
            }
        }

        return nonEmptyWordCount <= 1;
    }

    public int getGender(Word word) {
        String genderStr = word.getWordForm(COLUMN_INDEX_GENDER);

        if (genderStr.equals("m")) return GENDER_M;
        else if (genderStr.equals("f")) return GENDER_F;
        else if (genderStr.equals("n")) return GENDER_N;

        return GENDER_UNKNOWN;
    }
}
