package richardvalvona.uk.android.russianconjugations.languages;

public class WordWithInfo {

	public final Word word;
	private int resultIndex;
	private int matchedWordFormIndex;


	public WordWithInfo(Word word) {
		this.word = word;
	}

	public void setResultIndex(int resultIndex) {
		this.resultIndex = resultIndex;
	}

	public int getResultIndex() {
		return resultIndex;
	}

	public void setMatchedWordFormIndex(int matchedWordFormIndex) {
		this.matchedWordFormIndex = matchedWordFormIndex;
	}

	public int getMatchedWordFormIndex() {
		return matchedWordFormIndex;
	}
}
