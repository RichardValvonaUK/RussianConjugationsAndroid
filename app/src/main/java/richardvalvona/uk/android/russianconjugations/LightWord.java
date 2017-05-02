package richardvalvona.uk.android.russianconjugations;

import richardvalvona.uk.android.russianconjugations.languages.Language;

public class LightWord {

	public final String wordStr;
	public final String wordWithoutPronunciation;
	public final String wordWithoutVowelsSimplifiedConsonants;

	public LightWord(Language lang, String wordStr) {
		this.wordStr = wordStr;
		
		wordWithoutPronunciation = lang.removePronunciation(wordStr);
		wordWithoutVowelsSimplifiedConsonants = lang.removeVowelsSimplifyConsonants(wordWithoutPronunciation);
	}
}
