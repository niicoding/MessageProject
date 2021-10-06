
class Word {
    int wordIndex;

    // When building the word, check if its in the wordbank, if not, add it.
    // DOES NOT SAVE STRING HERE, ONLY THE INDEX INTO wordIndex!
    Word(String word, WordBank wordBank) {
        word = word.toLowerCase();

        if (wordBank.getWordArrayList().contains(word)) {
            this.wordIndex = wordBank.getWordArrayList().indexOf(word);
        }
    }
}
