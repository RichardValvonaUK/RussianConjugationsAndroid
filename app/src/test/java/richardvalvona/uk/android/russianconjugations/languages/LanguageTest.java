package richardvalvona.uk.android.russianconjugations.languages;

import org.junit.Test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by richard on 03/04/17.
 */

public class LanguageTest {


    @Test
    public void language_containsLatinLetters() {

        char c = 'a'-1;
        if (c == 'Z') assertTrue(Language.containsLatinLetters("" + c));
        else assertFalse(Language.containsLatinLetters("" + c));

        c = 'z'+1;
        if (c == 'A') assertTrue(Language.containsLatinLetters("" + c));
        else assertFalse(Language.containsLatinLetters("" + c));

        c = 'A'-1;
        if (c == 'z') assertTrue(Language.containsLatinLetters("" + c));
        else assertFalse(Language.containsLatinLetters("" + c));

        c = 'Z'+1;
        if (c == 'a') assertTrue(Language.containsLatinLetters("" + c));
        else assertFalse(Language.containsLatinLetters("" + c));

        assertTrue(Language.containsLatinLetters("az"));
        assertTrue(Language.containsLatinLetters("AZ"));
        assertTrue(Language.containsLatinLetters("a"));
        assertTrue(Language.containsLatinLetters("z"));
        assertTrue(Language.containsLatinLetters("A"));
        assertTrue(Language.containsLatinLetters("Z"));
        assertTrue(Language.containsLatinLetters("Z #'["));
        assertTrue(Language.containsLatinLetters("ALL CAPS"));
        assertTrue(Language.containsLatinLetters("no caps"));
        assertTrue(Language.containsLatinLetters("Rule Forever"));
        assertTrue(Language.containsLatinLetters(" u menя"));
        assertTrue(Language.containsLatinLetters(" кдац ya"));
        assertTrue(Language.containsLatinLetters(" klyuch"));
        assertTrue(Language.containsLatinLetters("book"));

        assertFalse(Language.containsLatinLetters(""));
        assertFalse(Language.containsLatinLetters("    "));
        assertFalse(Language.containsLatinLetters("[{##}]"));
        assertFalse(Language.containsLatinLetters("лыф"));
        assertFalse(Language.containsLatinLetters("дЪ(щэ"));
        assertFalse(Language.containsLatinLetters("пондв"));
        assertFalse(Language.containsLatinLetters(" боол"));
        assertFalse(Language.containsLatinLetters(null));

    }

    @Test
    public void language_tranliterateLatinToCyrillic() {
        //А, Б, В, Г, Д, Е, Ё, Ж, З, И, Й, К, Л, М, Н, О, П, Р, С, Т, У, Ф, Х, Ц, Ч, Ш, Щ, Ъ, Ы, Ь, Э, Ю, Я
        assertEquals("а", Language.tranliterateLatinToCyrillic("a"));
        assertEquals("б", Language.tranliterateLatinToCyrillic("b"));
        assertEquals("в", Language.tranliterateLatinToCyrillic("v"));
        assertEquals("г", Language.tranliterateLatinToCyrillic("g"));
        assertEquals("д", Language.tranliterateLatinToCyrillic("d"));
        assertEquals("е", Language.tranliterateLatinToCyrillic("e"));
        assertEquals("е", Language.tranliterateLatinToCyrillic("ye"));
        assertEquals("ё", Language.tranliterateLatinToCyrillic("yo"));
        assertEquals("ж", Language.tranliterateLatinToCyrillic("zh"));
        assertEquals("з", Language.tranliterateLatinToCyrillic("z"));
        assertEquals("и", Language.tranliterateLatinToCyrillic("i"));
        assertEquals("и", Language.tranliterateLatinToCyrillic("yi"));
        assertEquals("й", Language.tranliterateLatinToCyrillic("j"));
        assertEquals("к", Language.tranliterateLatinToCyrillic("k"));
        assertEquals("л", Language.tranliterateLatinToCyrillic("l"));
        assertEquals("м", Language.tranliterateLatinToCyrillic("m"));
        assertEquals("н", Language.tranliterateLatinToCyrillic("n"));
        assertEquals("о", Language.tranliterateLatinToCyrillic("o"));
        assertEquals("п", Language.tranliterateLatinToCyrillic("p"));
        assertEquals("р", Language.tranliterateLatinToCyrillic("r"));
        assertEquals("с", Language.tranliterateLatinToCyrillic("s"));
        assertEquals("т", Language.tranliterateLatinToCyrillic("t"));
        assertEquals("у", Language.tranliterateLatinToCyrillic("u"));
        assertEquals("у", Language.tranliterateLatinToCyrillic("w"));
        assertEquals("ф", Language.tranliterateLatinToCyrillic("f"));
        assertEquals("х", Language.tranliterateLatinToCyrillic("x"));
        assertEquals("ц", Language.tranliterateLatinToCyrillic("ts"));
        assertEquals("тс", Language.tranliterateLatinToCyrillic("t.s"));
        assertEquals("ч", Language.tranliterateLatinToCyrillic("ch"));
        assertEquals("ш", Language.tranliterateLatinToCyrillic("sh"));
        assertEquals("щ", Language.tranliterateLatinToCyrillic("shh"));
        assertEquals("ъ", Language.tranliterateLatinToCyrillic("''"));
        assertEquals("ы", Language.tranliterateLatinToCyrillic("i'"));
        assertEquals("ь", Language.tranliterateLatinToCyrillic("'"));
        assertEquals("э", Language.tranliterateLatinToCyrillic("e'"));
        assertEquals("ю", Language.tranliterateLatinToCyrillic("yu"));
        assertEquals("я", Language.tranliterateLatinToCyrillic("ya"));
        assertEquals("э", Language.tranliterateLatinToCyrillic("eh"));
        assertEquals("ы", Language.tranliterateLatinToCyrillic("ih"));

        /* Some words maybe made up or misspelt to quickly think of letter combinations. The testing
            is simply for transliterating letters
         */
        assertEquals("это", Language.tranliterateLatinToCyrillic("e'to"));
        assertEquals("это", Language.tranliterateLatinToCyrillic("ehto"));
        assertEquals("съем", Language.tranliterateLatinToCyrillic("s''em"));
        assertEquals("съем", Language.tranliterateLatinToCyrillic("s.hem"));
        assertEquals("между", Language.tranliterateLatinToCyrillic("myezhdu"));
        assertEquals("его", Language.tranliterateLatinToCyrillic("ego"));
        assertEquals("стол", Language.tranliterateLatinToCyrillic("stol"));
        assertEquals("дверь", Language.tranliterateLatinToCyrillic("dvyer'"));
        assertEquals("вы", Language.tranliterateLatinToCyrillic("vi'"));
        assertEquals("вы", Language.tranliterateLatinToCyrillic("vih"));
        assertEquals("чёрный", Language.tranliterateLatinToCyrillic("chyorni'j"));
        assertEquals("чёрный", Language.tranliterateLatinToCyrillic("chyornihj"));
        assertEquals("бещое", Language.tranliterateLatinToCyrillic("byeshhoye"));
        assertEquals("большой", Language.tranliterateLatinToCyrillic("bol'shoj"));
        assertEquals("пью", Language.tranliterateLatinToCyrillic("p'yu"));
        assertEquals("пью", Language.tranliterateLatinToCyrillic("pyyu"));
        assertEquals("идти", Language.tranliterateLatinToCyrillic("idti"));
        assertEquals("сэм", Language.tranliterateLatinToCyrillic("se'm"));
        assertEquals("район", Language.tranliterateLatinToCyrillic("rajon"));
        assertEquals("дедушка", Language.tranliterateLatinToCyrillic("dyedushka"));
        assertEquals("песаешь", Language.tranliterateLatinToCyrillic("pesayesh'"));
        assertEquals("июн", Language.tranliterateLatinToCyrillic("iyun"));
        assertEquals("афгуст", Language.tranliterateLatinToCyrillic("afgust"));
        assertEquals("етсин", Language.tranliterateLatinToCyrillic("et.sin"));
        assertEquals("ецин", Language.tranliterateLatinToCyrillic("etsin"));
    }

    @Test
    public void language_getLetterPattern() {
        assertEquals("ccvccv", Language.russian.getLetterPattern("красний"));
        assertEquals("ccvccv", Language.russian.getLetterPattern("красный"));
        assertEquals("ccvccv", Language.russian.getLetterPattern("краснии"));
        assertEquals("ccv", Language.russian.getLetterPattern("дверь"));
        assertEquals("ccv", Language.russian.getLetterPattern("двер"));
        assertEquals("cvc", Language.russian.getLetterPattern("сем"));
        assertEquals("cvc", Language.russian.getLetterPattern("съем"));
        assertEquals("vcv", Language.russian.getLetterPattern("ета"));
        assertEquals("vcv", Language.russian.getLetterPattern("это"));
        assertEquals("cvcv", Language.russian.getLetterPattern("россии"));
        assertEquals("cvcv", Language.russian.getLetterPattern("роси"));
        assertEquals("cvvc", Language.russian.getLetterPattern("район"));
        assertEquals("cvvc", Language.russian.getLetterPattern("раён"));
        assertEquals("ccvc", Language.russian.getLetterPattern("брат"));
        assertEquals("cvc", Language.russian.getLetterPattern("бат"));
        assertEquals("cvc", Language.russian.getLetterPattern("морт"));
        assertEquals("cvc", Language.russian.getLetterPattern("мот"));
    }

    @Test
    public void language_currentLetter() {
        assertEquals("album", Language.retainLatinCharsAndSpacesOnly("album"));
        assertEquals("new car", Language.retainLatinCharsAndSpacesOnly("new car"));
        assertEquals(" is a number", Language.retainLatinCharsAndSpacesOnly("123 is a number"));
        assertEquals("markdreamcouk", Language.retainLatinCharsAndSpacesOnly("mark@dream.co.uk"));
        assertEquals("Russian word for book is ", Language.retainLatinCharsAndSpacesOnly("Russian word for 'book' is 'книга'"));
        assertEquals("", Language.retainLatinCharsAndSpacesOnly(""));
        assertEquals("these brackets are invisible", Language.retainLatinCharsAndSpacesOnly("(these brackets are invisible)"));
        assertEquals("  ", Language.retainLatinCharsAndSpacesOnly("+44 1234 567890"));
        assertEquals("all balney", Language.retainLatinCharsAndSpacesOnly("all bal00ney"));
        assertEquals("", Language.retainLatinCharsAndSpacesOnly("{}"));
    }

    @Test
    public void language_cleanEnglishDescriptions() {
        assertEquals("A, amp, ampere\n(criminal slang) he, she or it (used to point to a particular person)", Language.cleanEnglishDescriptions("~~[[A]], [[amp]], [[ampere]]~~(criminal slang) [[he]], [[she]] or [[it]] (used to point to a particular person)"));
        assertEquals("(architecture) abat-voix (an overhang over a pulpit)\nнаве́с\nпотоло́к", Language.cleanEnglishDescriptions("~~(architecture)[[abat-voix]](an overhang over a pulpit)~~[[наве́с]]~~[[потоло́к]]"));
        assertEquals("abachi, obeche (an African hardwood of the tree w:Triplochiton scleroxylon Triplochiton scleroxylon).\nдревеси́на, де́рево", Language.cleanEnglishDescriptions("~~[[abachi]], [[obeche]] (an African [[hardwood]] of the tree [[w:Triplochiton scleroxylon|Triplochiton scleroxylon]]).~~[[древеси́на]],[[де́рево]]"));
        assertEquals("Abbasi, a Persian coin\nаба́з", Language.cleanEnglishDescriptions("~~[[Abbasi]], a Persian coin~~[[аба́з]]"));
        assertEquals("(initialism of абсци́зовая кислота́): ABA (abscisic acid)\nдормин", Language.cleanEnglishDescriptions("~~(initialism of|абсци́зовая кислота́): [[ABA]] ([[abscisic acid]])~~[[дормин]]"));
        assertEquals("air show (a public display of stunt flying, aerobatics, and historical aircraft)\nавиацио́нное шо́у\nаэрошо́у", Language.cleanEnglishDescriptions("~~[[air show]] (a public display of stunt flying, aerobatics, and historical aircraft)~~[[авиацио́нное шо́у]]~~[[аэрошо́у]]"));
        assertEquals("avocado", Language.cleanEnglishDescriptions("~~[[avocado]]"));
        assertEquals("(colloquial) car, wheels\nавтомоби́ль\nмаши́на\nта́чка\nавто-", Language.cleanEnglishDescriptions("~~(colloquial) [[car]], [[wheels]]~~[[автомоби́ль]]~~[[маши́на]]~~[[та́чка]]~~[[авто-]]"));
        assertEquals("auto show, motor show, car show", Language.cleanEnglishDescriptions("~~[[auto show]], [[motor show]], car show"));
        assertEquals("agouti", Language.cleanEnglishDescriptions("~~[[agouti]]"));
        assertEquals("a movement or piece that starts as adagio", Language.cleanEnglishDescriptions("~~a [[movement]] or [[piece]] that starts as [[adagio]]"));
        assertEquals("(initialism of авиа́ция да́льнего де́йствия tr=aviácija dálʹnevo déjstvija): strategic aviation, long-range aviation, long haul aircraft\n(initialism of артилле́рия да́льнего де́йствия tr=artillérija dálʹnevo déjstvija): long-range artillery", Language.cleanEnglishDescriptions("~~(initialism of|авиа́ция да́льнего де́йствия|tr=aviácija dálʹnevo déjstvija): [[strategic]] [[aviation]], [[long-range]] [[aviation]], [[long haul]] [[aircraft]]~~(initialism of|артилле́рия да́льнего де́йствия|tr=artillérija dálʹnevo déjstvija): [[long-range]] [[artillery]]"));
        assertEquals("ajapsandali (Georgian dish)", Language.cleanEnglishDescriptions("~~[[ajapsandali]] (Georgian dish)"));
        assertEquals("bourgeois (individual member of bourgeoisie)", Language.cleanEnglishDescriptions("~~[[bourgeois]] (individual member of bourgeoisie)"));
        assertEquals("bushido", Language.cleanEnglishDescriptions("~~[[bushido]]"));
        assertEquals("biennale (a biennial celebration or exhibition)", Language.cleanEnglishDescriptions("~~[[biennale]] (a biennial celebration or exhibition)"));
        assertEquals("The Cyrillic letter б#Russian Б, б#Russian б .\nThe Roman letter B, b .", Language.cleanEnglishDescriptions("~~The Cyrillic letter [[б#Russian|Б]], [[б#Russian|б]].~~The Roman letter [[B]], [[b]]."));
        assertEquals("office, bureau\n(furniture) bureau, writing desk, secretary\nконто́ра\nо́фис\nстол", Language.cleanEnglishDescriptions("~~[[office]], [[bureau]]~~(furniture) [[bureau]], [[writing desk]], [[secretary]]~~[[конто́ра]]~~[[о́фис]]~~[[стол]]"));
        assertEquals("lost and found, lost property (''department where items that are found can be claimed by their owners'')", Language.cleanEnglishDescriptions("~~[[lost and found]], [[lost property]] (''department where items that are found can be claimed by their owners'')"));
        assertEquals("(abbrev of восто́к east)\nЗ W, west\nС N, north\nЮ S, south", Language.cleanEnglishDescriptions("~~(abbrev of|восто́к||[[east]])~~[[З||[[W]], [[west]]]]~~[[С||[[N]], [[north]]]]~~[[Ю||[[S]], [[south]]]]"));
        assertEquals("V, volt", Language.cleanEnglishDescriptions("~~[[V]], [[volt]]"));
        assertEquals("(abbrev of век century)\n(abbrev of вольт volt)\n(abbrev of восто́к east)", Language.cleanEnglishDescriptions("~~(abbrev of|век||[[century]])~~(abbrev of|вольт||[[volt]])~~(abbrev of|восто́к||[[east]])"));
        assertEquals("east", Language.cleanEnglishDescriptions("~~[[east]]"));
        assertEquals("(abbrev of воинский во́инская часть military unit)", Language.cleanEnglishDescriptions("~~(abbrev of|[[воинский|во́инская]] [[часть]]||[[military unit]])"));
        assertEquals("higher education", Language.cleanEnglishDescriptions("~~[[higher education]]"));
        assertEquals("(abbrev of воинский во́инская часть military unit)", Language.cleanEnglishDescriptions("~~(abbrev of|[[воинский|во́инская]] [[часть]]||[[military unit]])"));
        assertEquals("diner, dining car (on a train)", Language.cleanEnglishDescriptions("~~[[diner]], [[dining car]] (on a train)"));
        assertEquals("(sports) water polo (a water sport)\nво́дное по́ло", Language.cleanEnglishDescriptions("~~(sports)[[water polo]](a water sport)~~[[во́дное по́ло]]"));
        assertEquals("centuries\nв. century", Language.cleanEnglishDescriptions("~~[[centuries]]~~[[в.||century]]"));
        assertEquals("(abbrev of вво́дное сло́во parenthetical word)\n(abbrev of вводный вво́дное словосочета́ние parenthetical word combination)", Language.cleanEnglishDescriptions("~~(abbrev of|[[вво́дное сло́во]]||parenthetical word)~~(abbrev of|[[вводный|вво́дное]] [[словосочета́ние]]||parenthetical word combination)"));
        assertEquals("GDP ; gross domestic product", Language.cleanEnglishDescriptions("~~[[GDP]]; [[gross domestic product]]"));
        assertEquals("on, to turn on (typical caption on electric switches, \"ON\") (neut)\ninclusion (neut)\non, to turn on (typical caption on electric switches, \"ON\") (neut)\nincluding (neut)\nв т. ч. (neut)\n(publishing) inset (fem)", Language.cleanEnglishDescriptions("~~[[on]], to [[turn]] [[on]] (typical caption on electric switches, &quot;ON&quot;) (neut)~~[[inclusion]] (neut)~~[[on]], to [[turn]] [[on]] (typical caption on electric switches, &quot;ON&quot;) (neut)~~[[including]] (neut)~~[[в т. ч.]] (neut)~~(publishing) [[inset]] (fem)"));
        assertEquals("navy", Language.cleanEnglishDescriptions("~~[[navy]]"));
        assertEquals("residence permit", Language.cleanEnglishDescriptions("~~[[residence permit]]"));
        assertEquals("GNP", Language.cleanEnglishDescriptions("~~[[GNP]]"));
        assertEquals("(acronym of Вели́кая Оте́чественная война́): the Great Patriotic War (WW II)", Language.cleanEnglishDescriptions("~~(acronym of|Вели́кая Оте́чественная война́): the [[Great Patriotic War]] (WW II)"));
        assertEquals("air force\nВВС", Language.cleanEnglishDescriptions("~~[[air force]]~~[[ВВС]]"));
        assertEquals("navy (sea force)\nВМС\nвое́нно-морско́й фло́т, ВМФ", Language.cleanEnglishDescriptions("~~[[navy]] (sea force)~~[[ВМС]]~~[[вое́нно-морско́й фло́т]],[[ВМФ]]"));
        assertEquals("navy\nВМФ\nвое́нно-морски́е си́лы, ВМС", Language.cleanEnglishDescriptions("~~[[navy]]~~[[ВМФ]]~~[[вое́нно-морски́е си́лы]],[[ВМС]]"));
        assertEquals("walkie-talkie (portable radio)", Language.cleanEnglishDescriptions("~~[[walkie-talkie]] (portable radio)"));
        assertEquals("W, watt", Language.cleanEnglishDescriptions("~~[[W]], [[watt]]"));
        assertEquals("voodoo (Afro-Caribbean religion)", Language.cleanEnglishDescriptions("~~[[voodoo]] (Afro-Caribbean religion)"));
        assertEquals("yesterday", Language.cleanEnglishDescriptions("~~[[yesterday]]"));
        assertEquals("The Cyrillic letter в В, в .\nThe Roman letter V, v .\nThe Roman letter W, w .", Language.cleanEnglishDescriptions("~~The Cyrillic letter [[в|В]],[[в]].~~The Roman letter [[V]], [[v]].~~The Roman letter [[W]], [[w]]."));
        assertEquals("(abbrev of грамм gram)\nhecto- (x 100)", Language.cleanEnglishDescriptions("~~(abbrev of|грамм||[[gram]])~~[[hecto-]] (x 100)"));
        assertEquals("(abbrev of год year) (masc)\n(abbrev of го́род city, town) (masc)\n(abbrev of гора́ mountain) (fem)", Language.cleanEnglishDescriptions("~~(abbrev of|год||[[year]]) (masc)~~(abbrev of|го́род||[[city]], [[town]]) (masc)~~(abbrev of|гора́||[[mountain]]) (fem)"));
        assertEquals("ha, hectare", Language.cleanEnglishDescriptions("~~[[ha]], [[hectare]]"));
        assertEquals("(abbrev of газе́та newspaper)", Language.cleanEnglishDescriptions("~~(abbrev of|газе́та||[[newspaper]])"));
        assertEquals("(military) flared breeches\nбрю́ки-галифе́ tr=brjúki-galifɛ́", Language.cleanEnglishDescriptions("~~(military) [[flared]] [[breeches]]~~[[брю́ки-галифе́|tr=brjúki-galifɛ́]]"));
        assertEquals("halo (atmospheric phenomenon)", Language.cleanEnglishDescriptions("~~[[halo]] (atmospheric phenomenon)"));
        assertEquals("gun - howitzer ''(a Russian weapon that combines characteristics of a gun and a howitzer, providing both a flat trajectory of fire and steep angles of ascent and descent).''\nпу́шка-га́убица", Language.cleanEnglishDescriptions("~~[[gun]]-[[howitzer]] ''(a Russian weapon that combines characteristics of a gun and a howitzer, providing both a flat trajectory of fire and steep angles of ascent and descent).''~~[[пу́шка-га́убица]]"));
        assertEquals("gaucho", Language.cleanEnglishDescriptions("~~[[gaucho]]"));
        assertEquals("gyoza\nцзя́оцзы (from Mandarin)", Language.cleanEnglishDescriptions("~~[[gyoza]]~~[[цзя́оцзы]] (from Mandarin)"));
        assertEquals("gay porn", Language.cleanEnglishDescriptions("~~[[gay]] [[porn]]"));
        assertEquals("(physics) henry", Language.cleanEnglishDescriptions("~~(physics) [[henry]]"));
        assertEquals("girlfriend\nподру́га, подру́жка (also: female friend)\nвозлю́бленная, люби́мая (sweetheart)\nде́вушка (also: (young) girl)\nневе́ста (fiancée)\nлюбо́вница (lover, mistress)\nпартнёрша ((female) partner)\nсожи́тельница ((female) cohabitant, concubine)\nда́ма се́рдца", Language.cleanEnglishDescriptions("~~[[girlfriend]]~~[[подру́га]],[[подру́жка]] (also: female friend)~~[[возлю́бленная]],[[люби́мая]] (sweetheart)~~[[де́вушка]] (also: (young) [[girl]])~~[[неве́ста]] (fiancée)~~[[любо́вница]] (lover, mistress)~~[[партнёрша]] ((female) partner)~~[[сожи́тельница]] ((female) cohabitant, concubine)~~[[да́ма се́рдца]]"));
        assertEquals("(abbrev of глава́ chapter)\n(abbrev of глаго́л verb)", Language.cleanEnglishDescriptions("~~(abbrev of|глава́||[[chapter]])~~(abbrev of|глаго́л||[[verb]])"));
        assertEquals("(abbrev of гре́ческий язы́к Greek language)\n(abbrev of сло́во греческий гре́ческого язык языка́ tr=slóvo gréčeskovo jazyká word of the Greek language): Greek words, in terms of etymology or philology", Language.cleanEnglishDescriptions("~~(abbrev of|[[гре́ческий]] [[язы́к]]||Greek language)~~(abbrev of|[[сло́во]] [[греческий|гре́ческого]] [[язык|языка́]]|tr=slóvo gréčeskovo jazyká||word of the Greek language): Greek words, in terms of etymology or philology"));
        assertEquals("grizzly bear", Language.cleanEnglishDescriptions("~~[[grizzly bear]]"));
        assertEquals("Literally: w:Ready for Labour and Defence of the USSR Ready for Labour and Defence (of the USSR) : physical culture training program in the USSR .", Language.cleanEnglishDescriptions("~~Literally: [[w:Ready for Labour and Defence of the USSR|Ready for Labour and Defence (of the USSR)]]: physical culture training program in the [[USSR]]."));
        assertEquals("Guanhua (language)", Language.cleanEnglishDescriptions("~~[[Guanhua]] (language)"));
        assertEquals("Gujarati (language)", Language.cleanEnglishDescriptions("~~[[Gujarati]] (language)"));
        assertEquals("kung fu\nкунфу, кунг-фу́, кун-фу", Language.cleanEnglishDescriptions("~~[[kung fu]]~~[[кунфу]],[[кунг-фу́]],[[кун-фу]]"));
        assertEquals("guru", Language.cleanEnglishDescriptions("~~[[guru]]"));
        assertEquals("Hz, hertz", Language.cleanEnglishDescriptions("~~[[Hz]], [[hertz]]"));
        assertEquals("The Cyrillic letter г#Russian Г, г#Russian г .\nThe Roman letter G, g, also called же .", Language.cleanEnglishDescriptions("~~The Cyrillic letter [[г#Russian|Г]], [[г#Russian|г]].~~The Roman letter [[G]], [[g]], also called [[же]]."));
        assertEquals("hydroelectric power station", Language.cleanEnglishDescriptions("~~[[hydroelectric]] [[power station]]"));
        assertEquals("geta", Language.cleanEnglishDescriptions("~~[[geta]]"));
        assertEquals("Dr., doctor (PhD or MD)", Language.cleanEnglishDescriptions("~~[[Dr.]], [[doctor]] ([[PhD]] or [[MD]])"));
        assertEquals("(abbrev of дом house, building)", Language.cleanEnglishDescriptions("~~(abbrev of|дом||[[house]], [[building]])"));
        assertEquals("The English letter W, w .\nвэ (''German'')\nдубль-вэ́ (''Latin, French, etc.'')", Language.cleanEnglishDescriptions("~~The English letter [[W]], [[w]].~~[[вэ]] (''German'')~~[[дубль-вэ́]] (''Latin, French, etc.'')"));
        assertEquals("daimyo (Japanese feudal lord)", Language.cleanEnglishDescriptions("~~[[daimyo]] (Japanese feudal lord)"));
        assertEquals("Dari", Language.cleanEnglishDescriptions("~~[[Dari]]"));
        assertEquals("(abbrev of децигра́мм decigram)", Language.cleanEnglishDescriptions("~~(abbrev of|децигра́мм||[[decigram]])"));
        assertEquals("Devanagari", Language.cleanEnglishDescriptions("~~[[Devanagari]]"));
        assertEquals("girl, teenage girl ''(at the age of puberty)''\nребёнок\nде́вочка\nде́вушка\nже́нщина\nстару́ха", Language.cleanEnglishDescriptions("~~[[girl]], [[teenage]] [[girl]] ''(at the age of [[puberty]])''~~[[ребёнок]]~~[[де́вочка]]~~[[де́вушка]]~~[[же́нщина]]~~[[стару́ха]]"));
        assertEquals("décolletage, décolleté", Language.cleanEnglishDescriptions("~~[[décolletage]], [[décolleté]]"));
        assertEquals("dandy, a man very concerned about his clothes and his appearance.\nфрант", Language.cleanEnglishDescriptions("~~[[dandy]], a man very concerned about his clothes and his appearance.~~[[франт]]"));
        assertEquals("(transport) depot, station, warehouse (company or facility that provides maintenance, repair and parking for rolling stock, usually rail)\nstation, firehouse, fire station (facility that accommodates fire trucks and special equipment)\nпожарное депо́", Language.cleanEnglishDescriptions("~~(transport) [[depot]], [[station]], [[warehouse]] (company or facility that provides maintenance, repair and parking for rolling stock, usually rail)~~[[station]], [[firehouse]], [[fire station]] (facility that accommodates fire trucks and special equipment)~~[[пожарное депо́]]"));
        assertEquals("Derby", Language.cleanEnglishDescriptions("~~[[Derby]]"));
        assertEquals("defile ''(thin canyon)''\n(military) defiling, marching, parade\nfashion parade", Language.cleanEnglishDescriptions("~~[[defile]] ''(thin canyon)''~~(military) [[defiling]], [[marching]], [[parade]]~~[[fashion]] [[parade]]"));
        assertEquals("(neologism) jerky (lean meat cured and preserved by cutting into thin strips and air-drying in the sun)", Language.cleanEnglishDescriptions("~~(neologism)[[jerky]](lean meat cured and preserved by cutting into thin strips and air-drying in the sun)"));
        assertEquals("jersey (garment knitted from wool, worn over the upper body)", Language.cleanEnglishDescriptions("~~[[jersey]] (garment knitted from wool, worn over the upper body)"));
        assertEquals("''(English letter)'' G, g", Language.cleanEnglishDescriptions("~~''(English letter)'' [[G]], [[g]]"));
        assertEquals("jujitsu (method of self-defence established in Japan)\nдзюдзю́цу (''based on standard Cyrillisation of Japanese'')", Language.cleanEnglishDescriptions("~~[[jujitsu]] (method of self-defence established in Japan)~~[[дзюдзю́цу]] (''based on standard Cyrillisation of Japanese'')"));
        assertEquals("(economics) zaibatsu (a Japanese conglomerate)\nчебо́ль (from the Korean equivalent)", Language.cleanEnglishDescriptions("~~(economics)[[zaibatsu]](a Japanese conglomerate)~~[[чебо́ль]](from the Korean equivalent)"));
        assertEquals("Dzongkha (''language'')", Language.cleanEnglishDescriptions("~~[[Dzongkha]] (''language'')"));
        assertEquals("judo", Language.cleanEnglishDescriptions("~~[[judo]]"));
        assertEquals("''(English letter)'' D, d", Language.cleanEnglishDescriptions("~~''(English letter)'' [[D]], [[d]]"));
        assertEquals("dynamo", Language.cleanEnglishDescriptions("~~[[dynamo]]"));
        assertEquals("dingo", Language.cleanEnglishDescriptions("~~[[dingo]]"));
        assertEquals("disco (''type of music'')", Language.cleanEnglishDescriptions("~~[[disco]] (''type of music'')"));
        assertEquals("''(musical note)'' C", Language.cleanEnglishDescriptions("~~''(musical note)'' [[C]]"));
        assertEquals("dominoes\ndomino (masquerade costume or the mask)", Language.cleanEnglishDescriptions("~~[[dominoes]]~~[[domino]] (masquerade costume or the mask)"));
        assertEquals("donburi", Language.cleanEnglishDescriptions("~~[[donburi]]"));
        assertEquals("dossier", Language.cleanEnglishDescriptions("~~[[dossier]]"));
        assertEquals("dragée (a sweet or confection, originally as used to administer drugs, medicine, etc.)", Language.cleanEnglishDescriptions("~~[[dragée]] (a sweet or confection, originally as used to administer drugs, medicine, etc.)"));
        assertEquals("The Roman letter W, w .\nвэ (''German'')\nда́бл-ю (''English'')", Language.cleanEnglishDescriptions("~~The Roman letter [[W]], [[w]].~~[[вэ]] (''German'')~~[[да́бл-ю]] (''English'')"));
        assertEquals("(historical) duce (nickname for Mussolini)\n(by extension) an authoritarian leader", Language.cleanEnglishDescriptions("~~(historical)[[duce]](nickname for Mussolini)~~(by extension) an [[authoritarian]] [[leader]]"));
        assertEquals("The Cyrillic letter д#Russian Д, д#Russian д .\nThe Roman letter D, d .", Language.cleanEnglishDescriptions("~~The Cyrillic letter [[д#Russian|Д]], [[д#Russian|д]].~~The Roman letter [[D]], [[d]]."));
        assertEquals("euro (€, currency)", Language.cleanEnglishDescriptions("~~[[euro]] ([[€]], currency)"));
        assertEquals("sole trader, sole proprietor", Language.cleanEnglishDescriptions("~~[[sole trader]], [[sole proprietor]]"));
        assertEquals("(aviation) strike-fighter", Language.cleanEnglishDescriptions("~~(aviation) [[strike-fighter]]"));
        assertEquals("(pre-reform spelling of иудаи́зм)", Language.cleanEnglishDescriptions("~~(pre-reform spelling of [[иудаи́зм]])"));
        assertEquals("yeti\nсне́жный челове́к\nбигфут", Language.cleanEnglishDescriptions("~~[[yeti]]~~[[сне́жный челове́к]]~~[[бигфут]]"));
        assertEquals("yo-yo (toy)", Language.cleanEnglishDescriptions("~~[[yo-yo]] (toy)"));
        assertEquals("Yoruba (a sub-Saharan people)\nYoruba (a person of the Yoruba tribe)\nYoruba (a sub-Saharan language)", Language.cleanEnglishDescriptions("~~[[Yoruba]] (a sub-Saharan people)~~[[Yoruba]] (a person of the Yoruba tribe)~~[[Yoruba]] (a sub-Saharan language)"));
        assertEquals("(euphemistic) dick (euphemism for хуй)\nerrative for юг", Language.cleanEnglishDescriptions("~~(euphemistic) [[dick]] (euphemism for [[хуй]])~~errative for [[юг]]"));
        assertEquals("(chess) Kt (knight)", Language.cleanEnglishDescriptions("~~(chess) [[Kt]] ([[knight]])"));
        assertEquals("(abbrev of копе́йка): kopek, copeck, kopeck ''(singular or plural)''", Language.cleanEnglishDescriptions("~~(abbrev of|копе́йка): [[kopek]], [[copeck]], [[kopeck]] ''(singular or plural)''"));
        assertEquals("cockatoo (a bird)", Language.cleanEnglishDescriptions("~~[[cockatoo]] (a bird)"));
        assertEquals("cocoa, cacao\nhot chocolate", Language.cleanEnglishDescriptions("~~[[cocoa]], [[cacao]]~~[[hot chocolate]]"));
        assertEquals("cal, calorie", Language.cleanEnglishDescriptions("~~[[cal]], [[calorie]]"));
        assertEquals("(obsolete) potash", Language.cleanEnglishDescriptions("~~(obsolete) [[potash]]"));
        assertEquals("cameo (brief appearance in a movie)", Language.cleanEnglishDescriptions("~~[[cameo]] (brief appearance in a movie)"));
        assertEquals("kamikaze\nкамика́дзэ", Language.cleanEnglishDescriptions("~~[[kamikaze]]~~[[камика́дзэ]]"));
        assertEquals("kanji\nхира́га́на\nката́ка́на", Language.cleanEnglishDescriptions("~~[[kanji]]~~[[хира́га́на]]~~[[ката́ка́на]]"));
        assertEquals("Kannada (language)", Language.cleanEnglishDescriptions("~~[[Kannada]] (language)"));
        assertEquals("canoe", Language.cleanEnglishDescriptions("~~[[canoe]]"));
        assertEquals("(music) country music", Language.cleanEnglishDescriptions("~~(music) [[country music]]"));
        assertEquals("karaoke\nкарао́кэ", Language.cleanEnglishDescriptions("~~[[karaoke]]~~[[карао́кэ]]"));
        assertEquals("karate\nкаратэ́", Language.cleanEnglishDescriptions("~~[[karate]]~~[[каратэ́]]"));
        assertEquals("karate\nкарате́ tr=karatɛ́", Language.cleanEnglishDescriptions("~~[[karate]]~~[[карате́|tr=karatɛ́]]"));
        assertEquals("(rare, special) cargo\nгруз g=m", Language.cleanEnglishDescriptions("~~(rare, special) [[cargo]]~~[[груз|g=m]]"));
        assertEquals("(military) square\n''(as an adjective)'' square\n''(women's haircut)'' bob\n(poker) four of a kind", Language.cleanEnglishDescriptions("~~(military) [[square]]~~''(as an adjective)'' [[square]]~~''(women's haircut)'' [[bob]]~~(poker) [[four of a kind]]"));
        assertEquals("karoshi", Language.cleanEnglishDescriptions("~~[[karoshi]]"));
        assertEquals("km, kilometer", Language.cleanEnglishDescriptions("~~[[km]], [[kilometer]]"));
        assertEquals("km/h, kilometers per hour", Language.cleanEnglishDescriptions("~~[[km/h]], [[kilometers]] per [[hour]]"));
        assertEquals("coati\nносу́ха", Language.cleanEnglishDescriptions("~~[[coati]]~~[[носу́ха]]"));
        assertEquals("gozinaki, a traditional Georgian confection made of nuts sliced into small pieces, usually walnuts, fried in honey, and served exclusively on New Year's Eve", Language.cleanEnglishDescriptions("~~[[gozinaki]], a traditional Georgian confection made of nuts sliced into small pieces, usually walnuts, fried in honey, and served exclusively on New Year's Eve"));
        assertEquals("hummingbird", Language.cleanEnglishDescriptions("~~[[hummingbird]]"));
        assertEquals("collie\nшотла́ндская овча́рка", Language.cleanEnglishDescriptions("~~[[collie]]~~[[шотла́ндская овча́рка]]"));
        assertEquals("Komi, a member of a Finno-Ugric people who live in the northwest Urals, Zyrians\nKomi, the Finno-Ugric language spoken by these people", Language.cleanEnglishDescriptions("~~[[Komi]], a member of a [[Finno-Ugric]] people who live in the northwest [[Urals]], Zyrians~~[[Komi]], the [[Finno-Ugric]] [[language]] spoken by these people"));
        assertEquals("confetti (small pieces of colored paper generally thrown about at festive occasions)", Language.cleanEnglishDescriptions("~~[[confetti]] (small pieces of colored paper generally thrown about at festive occasions)"));
        assertEquals("(abbrev of копе́йка): kopek, copeck, kopeck ''(singular or plural)''", Language.cleanEnglishDescriptions("~~(abbrev of|копе́йка): [[kopek]], [[copeck]], [[kopeck]] ''(singular or plural)''"));
        assertEquals("korokke (a Japanese dish)", Language.cleanEnglishDescriptions("~~[[korokke]] (a Japanese dish)"));
        assertEquals("Xhosa\nисикоса", Language.cleanEnglishDescriptions("~~[[Xhosa]]~~[[исикоса]]"));
        assertEquals("kotatsu\nфуто́н g=m", Language.cleanEnglishDescriptions("~~[[kotatsu]]~~[[футо́н|g=m]]"));
        assertEquals("cat café\nкоша́чье кафе́ tr=košáčʹje kafɛ́", Language.cleanEnglishDescriptions("~~[[cat café]]~~[[коша́чье кафе́|tr=košáčʹje kafɛ́]]"));
        assertEquals("coffee (in the form of a beverage)\ncoffee (in the form of beans)", Language.cleanEnglishDescriptions("~~[[coffee]] (in the form of a beverage)~~coffee (in the form of beans)"));
        assertEquals("pocket PC\nCommunist party of China", Language.cleanEnglishDescriptions("~~[[pocket]] [[PC]]~~[[Communist]] [[party]] of [[China]]"));
        assertEquals("checkpoint (masc)\ngearbox (fem)", Language.cleanEnglishDescriptions("~~[[checkpoint]] (masc)~~[[gearbox]] (fem)"));
        assertEquals("(chess) K (king)", Language.cleanEnglishDescriptions("~~(chess) [[K]] ([[king]])"));
        assertEquals("credo, creed", Language.cleanEnglishDescriptions("~~[[credo]], [[creed]]"));
        assertEquals("xi (Greek letter ξ)\nksi (early Cyrillic letter ѯ)", Language.cleanEnglishDescriptions("~~[[xi]] (Greek letter [[ξ]])~~[[ksi]] (early Cyrillic letter [[ѯ]])"));
        assertEquals("", Language.cleanEnglishDescriptions(""));
        assertEquals("HTTP cookie (packet of information sent by a server to a World Wide Web browser and then returned by the browser each time it accesses that server)", Language.cleanEnglishDescriptions("~~[[HTTP cookie]] (packet of information sent by a server to a World Wide Web browser and then returned by the browser each time it accesses that server)"));
        assertEquals("(ethnic slur, pejorative, slang) coolie", Language.cleanEnglishDescriptions("~~(ethnic slur, pejorative, slang) [[coolie]]"));
        assertEquals("kung fu\nгунфу́, кунфу, кун-фу", Language.cleanEnglishDescriptions("~~[[kung fu]]~~[[гунфу́]],[[кунфу]],[[кун-фу]]"));
        assertEquals("compartment (chamber), couchette", Language.cleanEnglishDescriptions("~~[[compartment]] (chamber), [[couchette]]"));
        assertEquals("purchase and sale", Language.cleanEnglishDescriptions("~~[[purchase]] and [[sale]]"));
        assertEquals("''(English letter)'' Q, q", Language.cleanEnglishDescriptions("~~''(English letter)'' [[Q]], [[q]]"));
        assertEquals("Chianti", Language.cleanEnglishDescriptions("~~[[Chianti]]"));
        assertEquals("''(English letter)'' K, k", Language.cleanEnglishDescriptions("~~''(English letter)'' [[K]], [[k]]"));
        assertEquals("kendo", Language.cleanEnglishDescriptions("~~[[kendo]]"));
        assertEquals("The Roman letter Q, q .", Language.cleanEnglishDescriptions("~~The Roman letter [[Q]], [[q]]."));
        assertEquals("(chess)(abbrev of ладья́ R (rook, castle))\n(metallurgy)(abbrev of лату́нь brass)\n(abbrev of ла́мпа lamp)\n(abbrev of лебёдка winch)\n(steam locomotives)(abbrev of Лебедянский Lebedyanskiy)\n(submarine series)(abbrev of « Ленинец » K-137 Leninets (Yankee Navaga))\n(abbrev of ле́вый left)\n(diesel oil marking)(abbrev of ле́тний summer)\n(shipping)(abbrev of легкове́сный lightweight (ship class))", Language.cleanEnglishDescriptions("~~(chess)(abbrev of|ладья́||[[R]] ([[rook]], [[castle]]))~~(metallurgy)(abbrev of|лату́нь||[[brass]])~~(abbrev of|ла́мпа||[[lamp]])~~(abbrev of|лебёдка||[[winch]])~~(steam locomotives)(abbrev of|Лебедянский||[[Lebedyanskiy]])~~(submarine series)(abbrev of|«[[Ленинец]]»||K-137 Leninets (Yankee Navaga))~~(abbrev of|ле́вый||left)~~(diesel oil marking)(abbrev of|ле́тний||[[summer]])~~(shipping)(abbrev of|легкове́сный||[[lightweight]] (ship class))"));
        assertEquals("(abbrev of литр liter)\n(abbrev of лёгкий lightweight)", Language.cleanEnglishDescriptions("~~(abbrev of|литр||[[liter]])~~(abbrev of|лёгкий||[[lightweight]])"));
        assertEquals("(maps)(abbrev of лес forest) (masc)\n(abbrev of литерату́ра literature) (fem)", Language.cleanEnglishDescriptions("~~(maps)(abbrev of|лес||[[forest]]) (masc)~~(abbrev of|литерату́ра||[[literature]]) (fem)"));
        assertEquals("(publishing)(abbrev of лист sheet, leaf, page)\n(grammar)(abbrev of лицо́ person of a verb)", Language.cleanEnglishDescriptions("~~(publishing)(abbrev of|лист||[[sheet]], [[leaf]], [[page]])~~(grammar)(abbrev of|лицо́||[[person]] of a verb)"));
        assertEquals("(abbrev of лошади́ная си́ла): HP, horsepower", Language.cleanEnglishDescriptions("~~(abbrev of|лошади́ная си́ла): [[HP]], [[horsepower]]"));
        assertEquals("(abbrev of лошади́ная си́ла): HP, horsepower", Language.cleanEnglishDescriptions("~~(abbrev of|лошади́ная си́ла): [[HP]], [[horsepower]]"));
        assertEquals("Labrador retriever", Language.cleanEnglishDescriptions("~~[[Labrador retriever]]"));
        assertEquals("(slang) money, dough", Language.cleanEnglishDescriptions("~~(slang) [[money]], [[dough]]"));
        assertEquals("(currency) lari (currency of Georgia)", Language.cleanEnglishDescriptions("~~(currency) [[lari]] (currency of [[Georgia]])"));
        assertEquals("lasso (a long rope with a sliding loop)", Language.cleanEnglishDescriptions("~~[[lasso]] (a long rope with a sliding loop)"));
        assertEquals("(initialism of лесбиянка лесбия́нки, гей ге́и, бисексуал бисексуа́лы и трансгендер трансге́ндеры tr=lesbijánki, géi, bisɛksuály i transgéndɛry): LGBT (Lesbian, Gay, Bisexual, Transgender/Transexual)", Language.cleanEnglishDescriptions("~~(initialism of|[[лесбиянка|лесбия́нки]], [[гей|ге́и]], [[бисексуал|бисексуа́лы]] [[и]] [[трансгендер|трансге́ндеры]]|tr=lesbijánki, géi, bisɛksuály i transgéndɛry): [[LGBT]](Lesbian, Gay, Bisexual, Transgender/Transexual)"));
        assertEquals("lady (title, woman of breeding and authority)", Language.cleanEnglishDescriptions("~~[[lady]] (title, woman of breeding and authority)"));
        assertEquals("libido (sexual urges or drives)", Language.cleanEnglishDescriptions("~~[[libido]] (sexual urges or drives)"));
        assertEquals("libretto", Language.cleanEnglishDescriptions("~~[[libretto]]"));
        assertEquals("Lingala (Bantu language)", Language.cleanEnglishDescriptions("~~[[Lingala]] (Bantu language)"));
        assertEquals("lumen", Language.cleanEnglishDescriptions("~~[[lumen]]"));
        assertEquals("(politics) lobby (group of people who try to lobby)", Language.cleanEnglishDescriptions("~~(politics) [[lobby]] (group of people who try to lobby)"));
        assertEquals("logo, logotype (symbol or emblem that acts as a trademark or a means of identification of an entity)\nлоготи́п", Language.cleanEnglishDescriptions("~~[[logo]], [[logotype]] (symbol or emblem that acts as a trademark or a means of identification of an entity)~~[[логоти́п]]"));
        assertEquals("lotto (game of chance similar to bingo)", Language.cleanEnglishDescriptions("~~[[lotto]] (game of chance similar to [[bingo]])"));
        assertEquals("LSD, acid (диэтилами́д d-лизерги́новой кислоты́)", Language.cleanEnglishDescriptions("~~[[LSD]], [[acid]] ([[диэтилами́д d-лизерги́новой кислоты́]])"));
        assertEquals("leek (vegetable)\nпоре́й", Language.cleanEnglishDescriptions("~~[[leek]] (vegetable)~~[[поре́й]]"));
        assertEquals("league (distance)", Language.cleanEnglishDescriptions("~~[[league]] (distance)"));
        assertEquals("(acronym of ли́ния электрический электро передача переда́чи): electric power transmission line", Language.cleanEnglishDescriptions("~~(acronym of|[[ли́ния]] [[электрический|электро]][[передача|переда́чи]]): [[electric power]] [[transmission line]]"));
        assertEquals("''(musical note)'' A", Language.cleanEnglishDescriptions("~~''(musical note)'' [[A]]"));
        assertEquals("(abbrev of метр meter)\n(abbrev of милли- milli-)((0.001))\nм.\n(abbrev of мужско́й род masculine gender)", Language.cleanEnglishDescriptions("~~(abbrev of|метр||[[meter]])~~(abbrev of|милли-||[[milli-]])((0.001))~~[[м.]]~~(abbrev of|мужско́й род||[[masculine]] [[gender]])"));
        assertEquals("(abbrev of метро́ subway (station)) (neut)\n(abbrev of мужска́я men's room, men’s toilet) (fem)\n(abbrev of Москва́ Moscow) (fem)", Language.cleanEnglishDescriptions("~~(abbrev of|метро́||[[subway]] (station)) (neut)~~(abbrev of|мужска́я||[[men's room]], men’s [[toilet]]) (fem)~~(abbrev of|Москва́||[[Moscow]]) (fem)"));
        assertEquals("(abbrev of мужско́й род masculine gender)\nм", Language.cleanEnglishDescriptions("~~(abbrev of|мужско́й род||[[masculine]] [[gender]])~~[[м]]"));
        assertEquals("(abbrev of метро́ subway (station)) (neut)\n(abbrev of мужска́я men's room, men’s toilet) (fem)\n(abbrev of Москва́ Moscow) (fem)", Language.cleanEnglishDescriptions("~~(abbrev of|метро́||[[subway]] (station)) (neut)~~(abbrev of|мужска́я||[[men's room]], men’s [[toilet]]) (fem)~~(abbrev of|Москва́||[[Moscow]]) (fem)"));
        assertEquals("(dated) madam (polite term of address to a woman)\nгоспожа́, суда́рыня\nмесье́", Language.cleanEnglishDescriptions("~~(dated)[[madam]](polite term of address to a woman)~~[[госпожа́]],[[суда́рыня]]~~[[месье́]]"));
        assertEquals("maiko (apprentice geisha)", Language.cleanEnglishDescriptions("~~[[maiko]] ([[apprentice]] [[geisha]])"));
        assertEquals("Maya person", Language.cleanEnglishDescriptions("~~[[Maya]] person"));
        assertEquals("Maya language", Language.cleanEnglishDescriptions("~~[[Maya]] language"));
        assertEquals("makgeolli (makkoli), a traditional alcoholic beverage in Korea, made of rice.", Language.cleanEnglishDescriptions("~~[[makgeolli]] (makkoli), a traditional alcoholic beverage in [[Korea]], made of rice."));
        assertEquals("makizushi\nролл g=m\nфутома́ки g=n\nхосома́ки g=n\nурама́ки g=n\nсу́ши g=n", Language.cleanEnglishDescriptions("~~[[makizushi]]~~[[ролл|g=m]]~~[[футома́ки|g=n]]~~[[хосома́ки|g=n]]~~[[урама́ки|g=n]]~~[[су́ши|g=n]]"));
        assertEquals("Malayalam (language)", Language.cleanEnglishDescriptions("~~[[Malayalam]] (language)"));
        assertEquals("(francophilic, fancy, emotional) mother, maman", Language.cleanEnglishDescriptions("~~(francophilic, fancy, emotional) [[mother]], [[maman]]"));
        assertEquals("marshmallow (confectionery)\nзефи́р", Language.cleanEnglishDescriptions("~~[[marshmallow]] (confectionery)~~[[зефи́р]]"));
        assertEquals("mass media (public communication that reaches a large audience)", Language.cleanEnglishDescriptions("~~[[mass media]] (public communication that reaches a large audience)"));
        assertEquals("single mother (a woman raising a child on her own)", Language.cleanEnglishDescriptions("~~[[single mother]] (a woman raising a child on her own)"));
        assertEquals("mafioso, mafiosi", Language.cleanEnglishDescriptions("~~[[mafioso]], [[mafiosi]]"));
        assertEquals("matsoni (a yoghurt-like dairy product made in Armenia and Georgia)", Language.cleanEnglishDescriptions("~~[[matsoni]] (a yoghurt-like dairy product made in Armenia and Georgia)"));
        assertEquals("machete", Language.cleanEnglishDescriptions("~~[[machete]]"));
        assertEquals("macho", Language.cleanEnglishDescriptions("~~[[macho]]"));
        assertEquals("maestro", Language.cleanEnglishDescriptions("~~[[maestro]]"));
        assertEquals("(initialism of мегаба́йт): MB (megabyte)", Language.cleanEnglishDescriptions("~~(initialism of|мегаба́йт): [[MB]] ([[megabyte]])"));
        assertEquals("ministry Ministry of Home Affairs, Interior Ministry", Language.cleanEnglishDescriptions("~~[[ministry|Ministry]] of Home Affairs, Interior Ministry"));
        assertEquals("IMF, International Monetary Fund", Language.cleanEnglishDescriptions("~~[[IMF]], [[International Monetary Fund]]"));
        assertEquals("mg, milligram", Language.cleanEnglishDescriptions("~~[[mg]], [[milligram]]"));
        assertEquals("megatsunami\nтелецуна́ми\nцуна́ми", Language.cleanEnglishDescriptions("~~[[megatsunami]]~~[[телецуна́ми]]~~[[цуна́ми]]"));
        assertEquals("media (means and institutions for publishing and broadcasting information)", Language.cleanEnglishDescriptions("~~[[media]] (means and institutions for publishing and broadcasting information)"));
        assertEquals("madrasah (''an Islamic school'')", Language.cleanEnglishDescriptions("~~[[madrasah]] (''an Islamic school'')"));
        assertEquals("(physiology) menarche (first menstruation)", Language.cleanEnglishDescriptions("~~(physiology) [[menarche]] (first menstruation)"));
        assertEquals("menu", Language.cleanEnglishDescriptions("~~[[menu]]"));
        assertEquals("(dated or French context) monsieur, sir\nмада́м", Language.cleanEnglishDescriptions("~~(dated or French context) [[monsieur]], [[sir]]~~[[мада́м]]"));
        assertEquals("subway, underground\nметрополите́н g=m", Language.cleanEnglishDescriptions("~~[[subway]], [[underground]]~~[[метрополите́н|g=m]]"));
        assertEquals("''(musical note)'' E", Language.cleanEnglishDescriptions("~~''(musical note)'' [[E]]"));
        assertEquals("mikado (emperor of Japan)\nтэнно", Language.cleanEnglishDescriptions("~~[[mikado]] (emperor of Japan)~~[[тэнно]]"));
        assertEquals("(historical) milady (English noblewoman)", Language.cleanEnglishDescriptions("~~(historical)[[milady]](English noblewoman)"));
        assertEquals("min., minute", Language.cleanEnglishDescriptions("~~[[min.]], [[minute]]"));
        assertEquals("", Language.cleanEnglishDescriptions(""));
        assertEquals("booby trap\nмл", Language.cleanEnglishDescriptions("~~[[booby trap]]~~[[мл]]"));
        assertEquals("(government) ministry of defense", Language.cleanEnglishDescriptions("~~(government) [[ministry]] of [[defense]]"));
        assertEquals("ISS, international space station", Language.cleanEnglishDescriptions("~~[[ISS]], [[international]] [[space station]]"));
        assertEquals("(initialism of миллили́тр ml, milliliter) (masc)\n(initialism of ми́на-лову́шка booby trap) (fem)", Language.cleanEnglishDescriptions("~~(initialism of|миллили́тр||[[ml]], [[milliliter]]) (masc)~~(initialism of|ми́на-лову́шка||[[booby trap]]) (fem)"));
        assertEquals("(grammar)(abbrev of мно́жественное число́ plural number)", Language.cleanEnglishDescriptions("~~(grammar)(abbrev of|мно́жественное число́||[[plural]] [[number]])"));
        assertEquals("(grammar)(abbrev of мно́жественное число́ plural number)", Language.cleanEnglishDescriptions("~~(grammar)(abbrev of|мно́жественное число́||[[plural]] [[number]])"));
        assertEquals("(music) a movement or piece that starts as moderato", Language.cleanEnglishDescriptions("~~(music) a [[movement]] or [[piece]] that starts as [[moderato]]"));
        assertEquals("momo, Tibetan dumpling", Language.cleanEnglishDescriptions("~~[[momo]], Tibetan [[dumpling]]"));
        assertEquals("(dated or French context) monsieur, sir\nмада́м", Language.cleanEnglishDescriptions("~~(dated or French context) [[monsieur]], [[sir]]~~[[мада́м]]"));
        assertEquals("STS (Space Transportation System)\nSpace Shuttle, shuttle\nспейс шаттл tr=spɛjs šattl\nшаттл", Language.cleanEnglishDescriptions("~~[[STS]] ([[Space Transportation System]])~~[[Space Shuttle]], [[shuttle]]~~[[спейс шаттл|tr=spɛjs šattl]]~~[[шаттл]]"));
        assertEquals("IPA, International Phonetic Alphabet", Language.cleanEnglishDescriptions("~~[[IPA]], [[International Phonetic Alphabet]]"));
        assertEquals("ministry Ministry of emergency Emergency situation Situations", Language.cleanEnglishDescriptions("~~[[ministry|Ministry]] [[of]] [[emergency|Emergency]] [[situation|Situations]]"));
        assertEquals("Navajo ''(indigenous North-American people)''\nNavajo ''(the Athabaskan language of this people)''", Language.cleanEnglishDescriptions("~~[[Navajo]] ''(indigenous North-American people)''~~[[Navajo]] ''(the Athabaskan language of this people)''"));
        assertEquals("(as used by the conservative right) God - bearing people — the Russian nation, seen as the embodiment of spirituality and the Christian faith, carrying out the sacred task of defending all that is good and holy .\n(ironic) God - bearing people — the Russian nation, perceived as the embodiment of any negative quality that the speaker is ascribe ascribing to the Russian people.\nправосла́вие\nдухо́вность\nРа́шка", Language.cleanEnglishDescriptions("~~(as used by the conservative right) [[God]]-[[bearing]] [[people]] — the [[Russian]] nation, seen as the [[embodiment]] of [[spirituality]] and the Christian [[faith]], carrying out the [[sacred]] task of defending all that is [[good]] and [[holy]].~~(ironic) [[God]]-[[bearing]] [[people]] — the Russian nation, perceived as the [[embodiment]] of any [[negative]] quality that the speaker is [[ascribe|ascribing]] to the Russian people.~~[[правосла́вие]]~~[[духо́вность]]~~[[Ра́шка]]"));
        assertEquals("(politics) Nazi\nнаци́ст, наци́стка\nфаши́ст, фаши́стка\nскинхе́д tr=skinxéd, skinxɛ́d", Language.cleanEnglishDescriptions("~~(politics) [[Nazi]]~~[[наци́ст]],[[наци́стка]]~~[[фаши́ст]],[[фаши́стка]]~~[[скинхе́д|tr=skinxéd, skinxɛ́d]]"));
        assertEquals("the name of the letter н in the Old Russian alphabet.", Language.cleanEnglishDescriptions("~~the name of the letter [[н]] in the Old Russian alphabet."));
        assertEquals("(initialism of нало́г на доба́вленную сто́имость): VAT, value-added tax", Language.cleanEnglishDescriptions("~~(initialism of|нало́г на доба́вленную сто́имость): [[VAT]], [[value-added tax]]"));
        assertEquals("nigirizushi\nсу́ши g=n\nгунка́н-ма́ки g=n", Language.cleanEnglishDescriptions("~~[[nigirizushi]]~~[[су́ши|g=n]]~~[[гунка́н-ма́ки|g=n]]"));
        assertEquals("(scientific) research institute", Language.cleanEnglishDescriptions("~~([[scientific]]) [[research]] [[institute]]"));
        assertEquals("ninja, shinobi", Language.cleanEnglishDescriptions("~~[[ninja]], [[shinobi]]"));
        assertEquals("non - commercial organisation, nonprofit organisation, NGO", Language.cleanEnglishDescriptions("~~[[non]]-[[commercial]] [[organisation]], [[nonprofit]] [[organisation]], [[NGO]]"));
        assertEquals("but (neut)\nNoh\nsame as но́-о-о (masc)", Language.cleanEnglishDescriptions("~~[[but]] (neut)~~[[Noh]]~~same as [[но́-о-о]] (masc)"));
        assertEquals("nori (red algae of the genus Pyropia ; used in the preparation of sushi in a form of thin sheets)", Language.cleanEnglishDescriptions("~~[[nori]] (red algae of the genus [[Pyropia]]; used in the preparation of [[sushi]] in a form of thin sheets)"));
        assertEquals("The Roman letter O, o .\noh\nоб (before vowels and in some other cases)\nобо (before some words such as ''мне'' or ''все'')\n''(+ prepositional case)'': about, of, on\n''(+ accusative case)'': against, upon", Language.cleanEnglishDescriptions("~~The Roman letter [[O]], [[o]].~~[[oh]]~~[[об]](before vowels and in some other cases)~~[[обо]](before some words such as ''мне'' or ''все'')~~''(+ [[prepositional case]])'': [[about]], [[of]], [[on]]~~''(+ [[accusative case]])'': [[against]], [[upon]]"));
        assertEquals("(Japan, Korea, etc.) miai, omiai (formal marriage interview)", Language.cleanEnglishDescriptions("~~(Japan, Korea, etc.)[[miai]], [[omiai]](formal marriage interview)"));
    }


    @Test
    public void language_matchWithBeginningAndEnd() {
        assertEquals("[[you here]]", Language.matchWithBeginningAndEnd("I know [[you here]]!", "[[yo", "]]")[1]);
        assertEquals(" you doing?", Language.matchWithBeginningAndEnd("What are you doing? I don't know!", " you", "?")[1]);
        assertEquals(null, Language.matchWithBeginningAndEnd("What are you doing? I don't know!", "know", "?")[1]);
        assertEquals("BA is here", Language.matchWithBeginningAndEnd("ABBA is here", "BA", "here")[1]);
        assertEquals(null, Language.matchWithBeginningAndEnd("ABBA is here", "here", "is")[1]);
        assertEquals(null, Language.matchWithBeginningAndEnd("ABBA is here", "ABBA", "ABBA")[1]);
        assertEquals(null, Language.matchWithBeginningAndEnd("Where is the hidden message?", "Where", "non-existant")[1]);
        assertEquals(null, Language.matchWithBeginningAndEnd("And it works", "Or it", "works")[1]);
        assertEquals("you on yourself", Language.matchWithBeginningAndEnd("Blame you on yourself", "you", "self")[1]);
        assertEquals("", Language.matchWithBeginningAndEnd("Be good", "", "")[1]);
        assertEquals(null, Language.matchWithBeginningAndEnd("Null is null", null, null)[1]);
        assertEquals(null, Language.matchWithBeginningAndEnd(null, null, null)[1]);
        assertEquals(null, Language.matchWithBeginningAndEnd(null, "A", "B")[1]);
        assertEquals("to play!", Language.matchWithBeginningAndEnd("Excellent time to play!!!!", "to", "!")[1]);
        assertEquals("III", Language.matchWithBeginningAndEnd("IIIIII", "II", "I")[1]);
        assertEquals("ABABAB", Language.matchWithBeginningAndEnd("ABABAB", "ABA", "AB")[1]);
        assertEquals("ABA", Language.matchWithBeginningAndEnd("ABABAB", "A", "A")[1]);
        assertEquals("ABABA", Language.matchWithBeginningAndEnd("ABABAB", "ABA", "A")[1]);
        assertEquals("A", Language.matchWithBeginningAndEnd("ABCD", "A", "")[1]);
        assertEquals("Bli", Language.matchWithBeginningAndEnd("Blip", "Bli", "")[1]);
        assertEquals("Hip hip", Language.matchWithBeginningAndEnd("Hip hip horray", "", "hip")[1]);
    }
}
