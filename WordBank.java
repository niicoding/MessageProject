import java.util.ArrayList;

class WordBank {
    ArrayList<String> wordArrayList = new ArrayList<>(); // this is the wordbank with the words

    WordBank () {
    }

    // This is called in Message when building a MessageBank, it takes the broken down message argument and builds up the wordbanks wordArrayList
    void addMessageToWordArrayList(ArrayList<String> wordStringArrayList) {
        for (String wordString : wordStringArrayList) {
            wordString = wordString.toLowerCase();

            //Check for duplicates and add
            if (!this.wordArrayList.contains(wordString)) {
                this.wordArrayList.add(wordString);
            }
        }
    }

    ArrayList<String> getWordArrayList() {
        return wordArrayList;
    }
}
