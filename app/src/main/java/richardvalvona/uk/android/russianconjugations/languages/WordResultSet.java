package richardvalvona.uk.android.russianconjugations.languages;

import java.util.ArrayList;

public class WordResultSet {

	private int numberOfWords = 0;
	private ArrayList<WordWithInfo>[] allWordsWithInfo;

	public WordResultSet(int resultIndex) {
		allWordsWithInfo = new ArrayList[resultIndex + 1];
		
		for (int i=0; i<=resultIndex; i++) {
			allWordsWithInfo[i] = new ArrayList<>();
		}
	}

	public void addWordResult(Word word, int resultIndex, int matchedWordFormIndex) {
		
		WordWithInfo wordWI = new WordWithInfo(word);
		wordWI.setResultIndex(resultIndex);
		wordWI.setMatchedWordFormIndex(matchedWordFormIndex);
		
		allWordsWithInfo[resultIndex].add(wordWI);
		
		numberOfWords++;
	}

	public WordWithInfo[] getAllWordsInOrderOfResultIndex() {
		return getAllWordsInOrderOfResultIndex(null);
	}
	
	public WordWithInfo[] getAllWordsInOrderOfResultIndex(Integer limit) {
		
		if (limit == null) limit = numberOfWords;
		
		int numberOfResultsToReturn = Math.min(numberOfWords, limit);
		
		int wordIndex = 0;
		
		int numberOfLists = allWordsWithInfo.length;
		
		WordWithInfo[] allWords = new WordWithInfo[numberOfResultsToReturn];
		
		outerLoop:
		for (int listIndex=0; listIndex<numberOfLists; listIndex++) {
			ArrayList<WordWithInfo> nextList = allWordsWithInfo[listIndex];
			
			int listSize = nextList.size();
			
			for (int i=0; i<listSize; i++) {
				allWords[wordIndex] = nextList.get(i);
				
				wordIndex++;
				if (wordIndex >= numberOfResultsToReturn) break outerLoop;
			}
		}
		
		return allWords;
	}
}
