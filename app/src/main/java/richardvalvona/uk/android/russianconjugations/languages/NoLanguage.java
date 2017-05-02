package richardvalvona.uk.android.russianconjugations.languages;

import java.util.HashSet;

public class NoLanguage extends Language {

	NoLanguage() {}

	@Override
	public String getLetterPattern(String word) {
		return word;
	}

	@Override
	public String simplifyVowels(String word) {
		return word;
	}

	@Override
	public String simplifyConsonants(String word) throws UnsupportedOperationException {
		return word;
	}

	@Override
	public String removePronunciation(String word) throws UnsupportedOperationException {
		return word;
	}

	@Override
	public String removeVowelsAndSilentLetters(String word) throws UnsupportedOperationException {
		return word;
	}

	@Override
	public String removeVowelsSimplifyConsonants(String word) throws UnsupportedOperationException {
		return word;
	}

}
