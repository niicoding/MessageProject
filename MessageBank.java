
import java.util.ArrayList;

class MessageBank {
    private ArrayList<Message> messageObjList;

    // Build the message bank (ArrayList of Message Objects (has message string) with an ArrayList of Word Objects (has word index)
    MessageBank(ArrayList<String> messageList, WordBank wordBank) {
        setMessageObjList (messageList, wordBank);
    }

    // Return the ArrayList of Message Objects
    ArrayList<Message> getMessageObjList() {
        return messageObjList;
    }

    // initialize messageObjList, add in the messages from the file
    private void setMessageObjList (ArrayList<String> messageList, WordBank wordBank) {
        this.messageObjList = new ArrayList<>();

        for (String message : messageList) {
            this.messageObjList.add(new Message(message, wordBank));
        }
    }
}
