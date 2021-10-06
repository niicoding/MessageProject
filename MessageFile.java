
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class MessageFile {
    private String fileName;
    private ArrayList<String> messageList;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private boolean twentyOrMore = true;

    boolean getTwentyOrMore() {
        return twentyOrMore;
    }

    private String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    ArrayList<String> getMessageList() {
        return messageList;
    }

    private void setMessageList(ArrayList<String> messageList) {
        // check if the file has 20 or more messages to kick error later
        if (messageList.size() < 20) {
            twentyOrMore = false;
        }
        this.messageList = messageList;
    }

    //Take the filename (whatever.txt) and build up an ArrayList of unprocessed strings. Will convert to Message Objects later when building the message bank.
    void readMessageFile (String fileName) throws IOException {
        setFileName(fileName);
        Path path = Paths.get(getFileName());
        setMessageList((ArrayList<String>) Files.readAllLines(path, ENCODING)); // UTF_8 is smallest so use this

    }
}
