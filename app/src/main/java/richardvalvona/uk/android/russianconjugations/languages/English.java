package richardvalvona.uk.android.russianconjugations.languages;

import java.util.HashSet;

public class English extends Language {

	public final HashSet<Character> vowels = new HashSet<>();
	public final HashSet<Character> consonants = new HashSet<>();

	English() {
		// a b c d e f g h i j k l m n o p q r s t u v w x y z
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');

		consonants.add('b');
		consonants.add('c');
		consonants.add('d');
		consonants.add('f');
		consonants.add('g');
		consonants.add('h');
		consonants.add('j');
		consonants.add('k');
		consonants.add('l');
		consonants.add('m');
		consonants.add('n');
		consonants.add('p');
		consonants.add('q');
		consonants.add('r');
		consonants.add('s');
		consonants.add('t');
		consonants.add('v');
		consonants.add('w');
		consonants.add('x');
		consonants.add('y');
		consonants.add('z');
	}

	public boolean isConsonant(char c) {
		return consonants.contains(c);
	}
	public boolean isVowel(char c) {
		return vowels.contains(c);
	}

	@Override
	public String getLetterPattern(String word) {
//		if (word != null) {
//
//			int letterIndex = 0;
//
//			StringBuilder sb = new StringBuilder(word.length());
//
//			char lastLetter = ' ';
//
//			while (letterIndex < word.length()) {
//				char currentLetter = word.charAt(letterIndex);
//
//				if (currentLetter != lastLetter) {
//					switch (currentLetter) {
//						case 'y': break;
//						case 'r': case 'l': case 'w': {
//							if (lastLetter == ' ' || isConsonant(lastLetter)) { sb.append('c'); }
//							break;
//						}
//						default: {
//							if (isConsonant(currentLetter)) { sb.append('c'); break; }
//							else if (isVowel(currentLetter)) { sb.append('v'); break; }
//						}
//					}
//				}
//
//				letterIndex++;
//				lastLetter = currentLetter;
//			}
//
//
//			return sb.toString();
//		}
//
//		return null;
		throw new UnsupportedOperationException();
	}

	@Override
	public String simplifyVowels(String word) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String simplifyConsonants(String word) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String removePronunciation(String word) throws UnsupportedOperationException {
		return word;
	}

	@Override
	public String removeVowelsAndSilentLetters(String word) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String removeVowelsSimplifyConsonants(String word) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}
