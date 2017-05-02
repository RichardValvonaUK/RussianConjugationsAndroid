package richardvalvona.uk.android.russianconjugations.languages;

public abstract class Language {

	public static final int MAX_NUMBER_OF_LETTERS_IN_TAG = 4;

	public abstract String simplifyVowels(String word);
	public abstract String simplifyConsonants(String word);
	public abstract String removePronunciation(String word);
	public abstract String removeVowelsAndSilentLetters(String word);
	public abstract String removeVowelsSimplifyConsonants(String word);

	public final static Russian russian = new Russian();
	public final static English english = new English();
	public final static NoLanguage noLanguage = new NoLanguage();
	public final static German german = new German();
	
	protected Language() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String firstNLetters(final String str, final int n) {
		final int strLen = str.length();

		if (strLen<=n) {
			return str;
		}
		else {
			return str.substring(0, n);
		}
	}
	
	public final String addStringTo(final String partToAdd, final String whatToAddTo, final int position) {
		return whatToAddTo.substring(0, position) + partToAdd + whatToAddTo.substring(position);
	}
	
	public final String addStringToReplaceCharAtPos(final String partToAdd, final String whatToAddTo, final int position) {
		return whatToAddTo.substring(0, position) + partToAdd + whatToAddTo.substring(position+1);
	}

    public static final boolean containsLatinLetters(String str) {

        if (str != null) {

            int strLen = str.length();

            for (int i=0; i<strLen; i++) {
                char nextChar = str.charAt(i);

                if (('a' <= nextChar && nextChar <= 'z') || ('A' <= nextChar && nextChar <= 'Z')) {
                    return true;
                }
            }
        }

        return false;
    }

	/*



        assertEquals("а", Language.tranliterateLatinToCyrillic("a"));
        assertEquals("б", Language.tranliterateLatinToCyrillic("b"));
        assertEquals("в", Language.tranliterateLatinToCyrillic("v"));
        assertEquals("г", Language.tranliterateLatinToCyrillic("g"));
        assertEquals("д", Language.tranliterateLatinToCyrillic("d"));
        assertEquals("е", Language.tranliterateLatinToCyrillic("e"));
        assertEquals("з", Language.tranliterateLatinToCyrillic("z"));
        assertEquals("и", Language.tranliterateLatinToCyrillic("i"));
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
        assertEquals("ь", Language.tranliterateLatinToCyrillic("'"));
	 */
	public static final String tranliterateLatinToCyrillic(String word) {
		if (word != null) {
			int letterIndex = 0;

			StringBuilder sb = new StringBuilder();

            int wordLength = word.length();

			while (letterIndex < wordLength) {
				char currentLetter = word.charAt(letterIndex);

				if (letterIndex < wordLength - 2) {
                    if (currentLetter == 's' && word.charAt(letterIndex+1) == 'h' && word.charAt(letterIndex+2) == 'h') {
                        sb.append("щ"); letterIndex += 3; continue;
                    }
				}
				if (letterIndex < wordLength - 1) {
					if (currentLetter == 'y') {
						switch(word.charAt(letterIndex+1)) {
							case 'a': sb.append("я"); letterIndex += 2; continue;
							case 'e': sb.append("е"); letterIndex += 2; continue;
							case 'i': sb.append("и"); letterIndex += 2; continue;
							case 'o': sb.append("ё"); letterIndex += 2; continue;
							case 'u': sb.append("ю"); letterIndex += 2; continue;
						}
					}
					else if (currentLetter == 't' && word.charAt(letterIndex+1) == 's') { sb.append("ц"); letterIndex += 2; continue; }
					else if (currentLetter == 'c' && word.charAt(letterIndex+1) == 'h') { sb.append("ч"); letterIndex += 2; continue; }
					else if (currentLetter == 's' && word.charAt(letterIndex+1) == 'h') { sb.append("ш"); letterIndex += 2; continue; }
					else if (currentLetter == 'z' && word.charAt(letterIndex+1) == 'h') { sb.append("ж"); letterIndex += 2; continue; }
					else if (currentLetter == '\'' && word.charAt(letterIndex+1) == '\'') { sb.append("ъ"); letterIndex += 2; continue; }
					else if (currentLetter == 'i' && word.charAt(letterIndex+1) == '\'') { sb.append("ы"); letterIndex += 2; continue; }
					else if (currentLetter == 'i' && word.charAt(letterIndex+1) == 'h') { sb.append("ы"); letterIndex += 2; continue; }
					else if (currentLetter == 'e' && word.charAt(letterIndex+1) == '\'') { sb.append("э"); letterIndex += 2; continue; }
					else if (currentLetter == 'e' && word.charAt(letterIndex+1) == 'h') { sb.append("э"); letterIndex += 2; continue; }
				}

				switch (currentLetter) {
					case 'a': sb.append("а"); break;
					case  'b': sb.append("б"); break;
					case  'v': sb.append("в"); break;
					case  'g': sb.append("г"); break;
					case  'd': sb.append("д"); break;
					case  'e': sb.append("е"); break;
					case  'z': sb.append("з"); break;
					case  'i': sb.append("и"); break;
					case  'j': sb.append("й"); break;
					case  'k': sb.append("к"); break;
					case  'l': sb.append("л"); break;
					case  'm': sb.append("м"); break;
					case  'n': sb.append("н"); break;
					case  'o': sb.append("о"); break;
					case  'p': sb.append("п"); break;
					case  'r': sb.append("р"); break;
					case  's': sb.append("с"); break;
					case  't': sb.append("т"); break;
					case  'u': sb.append("у"); break;
					case  'w': sb.append("у"); break;
					case  'f': sb.append("ф"); break;
					case  'x': sb.append("х"); break;
					case  '\'': sb.append("ь"); break;
					case  'y': sb.append("ь"); break;
					case  'h': sb.append("ъ"); break;
					case  '.': /* Add nothing */ break;
					default: sb.append(currentLetter); break;
				}

				letterIndex++;
			}


			return sb.toString();
		}

		return null;
	}

	/**
	 *  Returns a string representing the consonant and vowel pattern of a string. This is
	 *  not the literal pattern, but a pattern which is more approximate to account for sounds
	 *  which may be difficult for English speakers to differentiate. For example, hard and soft
	 *  signs are ignored, double vowels treated as a single vowel, the letter r only being
	 *  treated as a consonant in certain positions, etc.
	 * @param word The Cyrillic String being inputed
	 * @return A pattern in a form like 'cvcvvvcvc'.
	 */
	public abstract String getLetterPattern(String word);

	/**
	 * Not checked for null or zero-length so will throw exceptions
	 * for these scenarios. Returns the final character of a Stringbuilder
	 *
	 * @param sb The StringBuilder to use
	 * @return The final character of the string.
	 */
	private static Character endCharOf(StringBuilder sb) {
		return sb.length() == 0 ? '\n' : sb.charAt(sb.length()-1);
	}

	private static void deleteEndCharOf(StringBuilder sb) {
		sb.deleteCharAt(sb.length()-1);
	}


	public static String cleanEnglishDescriptions(String str) {
		if (str != null && str.length() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(str.charAt(0));

			int strLen = str.length();

			for (int i=1; i<strLen; i++) {
				char currentChar = str.charAt(i);
				char endChar = endCharOf(sb);

				switch (currentChar) {
					case '[': case ']': {
						if (endChar == currentChar) {
							deleteEndCharOf(sb); removeTrailingSpaces(sb); sb.append(' ');
						}
						else  {
							sb.append(currentChar);
						}
						break;
					}
					case '~': {
						if (endChar == currentChar) {
							deleteEndCharOf(sb); removeTrailingSpaces(sb); sb.append('\n');
						}
						else  {
							sb.append(currentChar);
						}
						break;
					}
					case '|': {
						sb.append(' ');
						break;
					}
					case ')': case ',': {
						removeTrailingSpaces(sb); sb.append(currentChar);
						break;
					}
					case ' ': {
						removeTrailingSpaces(sb); sb.append(' ');
						break;
					}
					case ';': {
						if (sb.length()>=5 && sb.substring(sb.length()-5).equals("&quot")) {
							deleteEndCharOf(sb);deleteEndCharOf(sb);deleteEndCharOf(sb);deleteEndCharOf(sb);deleteEndCharOf(sb);
							sb.append('"');
						}
						else {
							sb.append(currentChar);
						}
						break;
					}
					default: {
						sb.append(currentChar);
						break;
					}
				}

				String endTwoChars = sb.length()<=2 ? sb.toString() : sb.substring(sb.length()-2);
				if (endTwoChars.equals("[ ") || endTwoChars.equals("{ ") || endTwoChars.equals("( ") || endTwoChars.equals("\n ") || endTwoChars.equals("  ")) {
					deleteEndCharOf(sb);
				}
			}

			return sb.toString().trim();
		}

		return str;
	}

	private static void removeTrailingSpaces(StringBuilder sb) {
		while (sb.length() > 0 && endCharOf(sb) == ' ') {
			deleteEndCharOf(sb);
		}
	}

	public static final String retainLatinCharsAndSpacesOnly(String word) {
		if (word != null) {
			int letterIndex = 0;

			StringBuilder sb = new StringBuilder();

			int wordLength = word.length();

			while (letterIndex < wordLength) {
				char currentLetter = word.charAt(letterIndex);

				if ( (currentLetter >= 'A' && currentLetter <= 'Z')
						|| (currentLetter >= 'a' && currentLetter <= 'z')
						|| (currentLetter >= '1' && currentLetter <= '9')
						|| currentLetter == '0'
						|| currentLetter == ' '
				) {
					sb.append(currentLetter);
				}

				letterIndex++;
			}


			return sb.toString();
		}

		return null;
	}

	public static final Object[] matchWithBeginningAndEnd(String strToSearchIn, String startSearch, String endSearch) {

		if (strToSearchIn != null && startSearch != null && endSearch != null) {
			int startStrPos = strToSearchIn.indexOf(startSearch);

			if (startStrPos != -1) {
				int endStrPos = strToSearchIn.indexOf(endSearch, startStrPos + startSearch.length());

				if (endStrPos != -1) {

					return new Object[]{startStrPos, strToSearchIn.substring(startStrPos, endStrPos + endSearch.length())};
				}
			}
		}

		return null;
	}
}
