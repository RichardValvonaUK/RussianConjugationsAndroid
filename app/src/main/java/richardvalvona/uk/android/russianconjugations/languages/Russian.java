package richardvalvona.uk.android.russianconjugations.languages;

import java.util.HashSet;
import java.util.regex.Pattern;

public final class Russian extends Language {

	// Next letter always и and never ы.
	public final HashSet<Character> spellingRule7Letters = new HashSet<>();
	
	// Next letter always е and never о if next letter unstressed.
	public final HashSet<Character> spellingRule5Letters = new HashSet<>();

	public final HashSet<Character> vowels = new HashSet<>();
	public final HashSet<Character> consonants = new HashSet<>();

	public final char accentChar = "и́".charAt(1);

	private int genderCount = 0;
	public final int GENDER_M = genderCount++;
	public final int GENDER_F = genderCount++;
	public final int GENDER_N = genderCount++;
	public final int GENDER_NA = genderCount++;
	public final int GENDER_UNKNOWN = genderCount++;

	private int numberOfNumbers = 0;
	public final int NUMBER_S = numberOfNumbers++;
	public final int NUMBER_P = numberOfNumbers++;
	
	private int numberOfCases = 0;
	public final int CASE_N = numberOfCases++;
	public final int CASE_A = numberOfCases++;
	public final int CASE_G = numberOfCases++;
	public final int CASE_D = numberOfCases++;
	public final int CASE_I = numberOfCases++;
	public final int CASE_P = numberOfCases++;
	
	Russian() {
		spellingRule7Letters.add('г');
		spellingRule7Letters.add('к');
		spellingRule7Letters.add('х');
		spellingRule7Letters.add('ж');
		spellingRule7Letters.add('ш');
		spellingRule7Letters.add('щ');
		spellingRule7Letters.add('ч');

		spellingRule5Letters.add('ж');
		spellingRule5Letters.add('ч');
		spellingRule5Letters.add('ш');
		spellingRule5Letters.add('щ');
		spellingRule5Letters.add('ц');

		vowels.add('а');
		vowels.add('э');
		vowels.add('ы');
		vowels.add('о');
		vowels.add('у');
		vowels.add('я');
		vowels.add('е');
		vowels.add('и');
		vowels.add('ё');
		vowels.add('ю');

		consonants.add('б');
		consonants.add('в');
		consonants.add('г');
		consonants.add('д');
		consonants.add('ж');
		consonants.add('з');
		consonants.add('к');
		consonants.add('л');
		consonants.add('м');
		consonants.add('н');
		consonants.add('п');
		consonants.add('р');
		consonants.add('с');
		consonants.add('т');
		consonants.add('ф');
		consonants.add('х');
		consonants.add('ц');
		consonants.add('ш');
		consonants.add('щ');
		//А, Б, В, Г, Д, Е, Ё, Ж, З, И, Й, К, Л, М, Н, О, П, Р, С, Т, У, Ф, Х, Ц, Ч, Ш, Щ, Ъ, Ы, Ь, Э, Ю, Я
		//а, б, в, г, д, е, ё, ж, з, и, й, к, л, м, н, о, п, р, с, т, у, ф, х, ц, ч, ш, щ, ъ, ы, ь, э, ю, я

	}

	public boolean isConsonant(char c) {
		return consonants.contains(c);
	}

	public boolean isVowel(char c) {
		return vowels.contains(c);
	}

	public String do7And5LetterRuleModifications(boolean is7LetterRuleLetter, boolean is5LetterRuleLetter, String suffix, boolean accentLastCharInStem) {
		if (is7LetterRuleLetter && suffix.startsWith("ы")) {
			suffix = "и" + suffix.substring(1);
		}
		else if (is5LetterRuleLetter && !accentLastCharInStem && suffix.startsWith("о")) {
			suffix = "е" + suffix.substring(1);
		}
		return suffix;
	}
	
	public String removePronunciation(String word) {
		return word.replace("" + accentChar, "");
	}

	private final String russianVowelsAndSilentLettersRegex = "[аэыоуяеийёюьъ]";
	
	public String removeVowelsAndSilentLetters(String word) {
		return word.replaceAll(russianVowelsAndSilentLettersRegex, "");
	}

	public char[] getAlternativeChars(char c) {
		switch (c) {

			case 'п': return new char[] {c,'б'}; case 'б': return new char[] {c,'п'};
			case 'ф': return new char[] {c,'в'}; case 'в': return new char[] {c,'ф'};
			case 'к': return new char[] {c,'г'}; case 'г': return new char[] {c,'к'};
			case 'щ': return new char[] {c,'ш','ж'};
			case 'ш': return new char[] {c,'щ','ж'};
			case 'ж': return new char[] {c,'ш','щ'};
			case 'т': return new char[] {c,'д'}; case 'д': return new char[] {c,'т'};
			case 'с': return new char[] {c,'з'}; case 'з': return new char[] {c,'с'};
			case 'а': return new char[] {c,'я'}; case 'я': return new char[] {c,'а'};
			case 'э': return new char[] {c,'е'}; case 'е': return new char[] {c,'э'};
			case 'ы': return new char[] {c,'и'}; case 'и': return new char[] {c,'ы'};
			case 'о': return new char[] {c,'ё'}; case 'ё': return new char[] {c,'о'};
			case 'у': return new char[] {c,'ю'}; case 'ю': return new char[] {c,'у'};
			default: return new char[] {c};
		}
	}

	public String removeVowelsSimplifyConsonants(String word) {

		if (word == null) return null;

		StringBuilder sb = new StringBuilder();

		int wordLen = word.length();

		for (int i=0; i<wordLen; i++) {
			char nextChar = word.charAt(i);

			switch (nextChar) {
				case 'ч': sb.append("тш"); break;
				case 'ц': sb.append("тс"); break;
				case 'п': case 'б': sb.append("б"); break;
				case 'ф': case 'в': sb.append("в"); break;
				case 'к': case 'г': sb.append("г"); break;
				case 'щ': case 'ш': case 'ж': sb.append("ж"); break;
				case 'т': case 'д': sb.append("д"); break;
				case 'с': case 'з': sb.append("з"); break;
				case 'л': sb.append("л"); break;
				case 'м': sb.append("м"); break;
				case 'н': sb.append("н"); break;
				case 'х': sb.append("х"); break;
//				case 'р':
//				case 'а':
//				case 'э':
//				case 'ы':
//				case 'о':
//				case 'у':
//				case 'я':
//				case 'е':
//				case 'и':
//				case 'ё':
//				case 'ю':
//				case 'ь':
//				case 'ъ':
//					break;
			}
		}

//		int sbLen = sb.length();
//
//		StringBuilder sb2 = new StringBuilder(sbLen);
//
//		char lastChar = ' ';
//		for (int i=0; i<sbLen; i++) {
//			char nextChar = sb.charAt(i);
//
//			if (nextChar != lastChar) {
//				sb2.append(nextChar);
//			}
//
//			lastChar = nextChar;
//		}

		return sb.toString();
	}

	public String simplifyConsonants(String word) {
		word = word.replaceAll("[ч]", "тш");
		word = word.replaceAll("[ц]", "тс");
		word = word.replaceAll("[п]", "б");
		word = word.replaceAll("[ф]", "в");
		word = word.replaceAll("[к]", "г");
		word = word.replaceAll("[щш]", "ж");
		word = word.replaceAll("[т]", "д");
		word = word.replaceAll("[с]", "з");
		word = word.replaceAll("[р]", "");
		word = word.replaceAll("(.)\\1", "$1");

		return word;
	}
	
	public int positionOfFirstVowel(String word) {
		word = word.toLowerCase();
		
		int wordLength = word.length();
		
		for (int i=0; i<wordLength; i++) {
			char nextChar = word.charAt(i);
			
			if (vowels.contains(nextChar)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public int positionOfFirstVowelOnLastSyllable(String word) {
		word = word.toLowerCase();
		
		int wordLength = word.length();
		
		Character c = null;
		
		for (int i=wordLength-1; i>=0; i--) {
			char nextChar = word.charAt(i);
			
			// If this is a char then mark it
			if (vowels.contains(nextChar)) {
				c = nextChar;
			}
			// Otherwise if previous character is a char return position
			else if (c != null) {
				return i+1;
			}
			else {
				c = null;
			}
		}
		
		return c==null ? -1 : 0;
	}
	
	/**
	 * Soft vowels are replaced with equivalent soft vowels, hard and soft signs removed and о's with а's and е's with и's for improved matches
	 * 
	 * @param word
	 * @return
	 */
	public String simplifyVowels(String word) {
		word = word.replaceAll("[яоёюу]", "а");
		word = word.replaceAll("[еиэй]", "ы");
		word = word.replaceAll("[ьъ]", "");
		word = word.replaceAll("(.)\\1", "$1");
		
		return word;
	}
	
	public final static String LITERAL_PATTERN_ya = Pattern.quote("ya");
	public final static String LITERAL_PATTERN_e = Pattern.quote("e'");
	public final static String LITERAL_PATTERN_i = Pattern.quote("i'");
	public final static String LITERAL_PATTERN_yo = Pattern.quote("yo");
	public final static String LITERAL_PATTERN_yu = Pattern.quote("yu");
	
	public final static String LITERAL_PATTERN_shh = Pattern.quote("shh");
	public final static String LITERAL_PATTERN_sh = Pattern.quote("sh");
	public final static String LITERAL_PATTERN_zh = Pattern.quote("zh");
	public final static String LITERAL_PATTERN_ch = Pattern.quote("ch");
	public final static String LITERAL_PATTERN_tsя = Pattern.quote("tsя");
	public final static String LITERAL_PATTERN_tsо = Pattern.quote("tsо");
	public final static String LITERAL_PATTERN_tsю = Pattern.quote("tsю");
	public final static String LITERAL_PATTERN_ts = Pattern.quote("ts");
	public final static String LITERAL_Ъ = Pattern.quote("''");

	public String getLetterPattern(String word) {
		if (word != null) {

			int letterIndex = 0;

			StringBuilder sb = new StringBuilder(word.length());

			char lastLetter = ' ';

			while (letterIndex < word.length()) {
				char currentLetter = word.charAt(letterIndex);

				if (currentLetter != lastLetter) {
					switch (currentLetter) {
						case 'ъ':case 'ь':case 'й': break;
						case 'р': {
							if (lastLetter == ' ' || isConsonant(lastLetter)) { sb.append('c'); }
							break;
						}
						default: {
							if (isConsonant(currentLetter)) { sb.append('c'); break; }
							else if (isVowel(currentLetter)) { sb.append('v'); break; }
						}
					}
				}

				letterIndex++;
				lastLetter = currentLetter;
			}


			return sb.toString();
		}

		return null;
	}
}
