
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Message {
    private String message;
    private ArrayList<Word> wordList = new ArrayList<>();

    String getMessage () {
        return message;
    }

    // Save the indexes of the words in the message to word objects (but not the actual string) for rebuilding later. When making a word object it checks if the wordbank has a word.
    // If the wordbank does not have a word, it adds it to the wordbank. If it does, it just makes an index.
    private void setWordList(ArrayList<String> wordStringArrayList, WordBank wordBank) {
        for (String wordString : wordStringArrayList) {
            this.wordList.add(new Word(wordString, wordBank));
        }
    }

    // Build a message you already know already know for reMessageButton
    Message (MessageBank messageBank, int messageNumber, WordBank wordBank) {
        this.message = ""; // make a blank message string

        Message message = messageBank.getMessageObjList().get(messageNumber); // pass combobox selection to rebuild message from the messageBank

        // for every word object with a saved index in the message, get that index and then build up the reMessage from words in the wordBank
        for (Word word : message.wordList) {
            if (this.message.equals("")) {
                this.message = wordBank.wordArrayList.get(word.wordIndex);
            } else {
                this.message = this.message + " " + wordBank.wordArrayList.get(word.wordIndex);
            }
        }
    }

    // Create Messages for MessageBank
    Message(String message, WordBank wordBank) {
        // Save the whole message (requirements did not specify to index the messages for repeats)
        this.message = message;

        //Temporarily breakup the message into a list
        List<String> wordStringList = Arrays.asList(message.split("\\W+"));

        //Temporarily put the words into an ArrayList
        ArrayList<String> wordStringArrayList = new ArrayList<>(wordStringList);

        //Send to wordBank for processing (to lower case) and storage
        wordBank.addMessageToWordArrayList(wordStringArrayList);

        //Save indexes of words to a wordList containing Word objects that contain an index of the word to rebuild message later
        setWordList(wordStringArrayList, wordBank);
    }
}
