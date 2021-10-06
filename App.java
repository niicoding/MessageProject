/**
 * @author  Zachary Perales
 * Course #: CSC240-80
 * Assigned: Week of August 25, 2019
 * Description: This application takes in a text file and rebuilds messages from a word-bank.
 *
 */

// @TODO Build jar

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class App {
    private static JFrame frame;
    private JPanel mainPanel;
    private JButton readMessageFromFileButton;
    private JButton exitButton;
    private JComboBox messageNumberComboBox;
    private JButton reAssembleMessageButton;
    private JTextField reAssembledMessageField;
    private JTextField msgCountField;
    private JTextField messageCountField;
    private JTextField inputField;
    private JTextArea wordBankArea;
    private JTextField wordCountField;

    private WordBank wordBank = new WordBank();
    private MessageFile newFile = new MessageFile();
    private MessageBank messageBank;
    private Vector comboBoxItems = new Vector();
    private final DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);

    // Constructor for a new App object, contains action listeners
    private App() {
        wordCountField.setEditable(false);
        reAssembledMessageField.setEditable(false);
        msgCountField.setEditable(false);
        messageCountField.setEditable(false);
        wordBankArea.setEditable(false);

        // attach listener to read in button
        readMessageFromFileButton.addActionListener(new ActionListener() {
            // click button
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog d = new JDialog(frame, "Status", true);
                JLabel success = new JLabel("We are reading in the file");
                JLabel error = new JLabel("You haven't entered a file, or it doesn't exist, or it's under 20 messages. Check again. Close application and reopen .jar to use a new file. File must be in the same directory as the .jar you should be running. Include .txt extension.");

                // Validate the input and do not let user break program
                if (messageNumberComboBox.getModel().getSize() == 0 && !(inputField.getText() == null || inputField.getText().trim().isEmpty())) {
                    d.setLocationRelativeTo(frame);

                    // Read in the text file and build a message bank
                    try {
                        newFile.readMessageFile(inputField.getText());
                        if (newFile.getTwentyOrMore()) {
                            messageBank = new MessageBank(newFile.getMessageList(), wordBank);
                            d.add(success);
                            d.pack();
                            d.setVisible(true);

                            //Build the word bank
                            for (String word : wordBank.wordArrayList) {
                                wordBankArea.append(word + "\n");
                            }

                            // build up the combo box model
                            for (Message message : messageBank.getMessageObjList()) {
                                model.addElement(messageBank.getMessageObjList().indexOf(message) + 1);
                            }

                            messageNumberComboBox.setModel(model); // apply the built up combobox model
                            msgCountField.setText(String.valueOf(messageBank.getMessageObjList().size())); // set the total msg count field
                            wordCountField.setText(String.valueOf(wordBank.getWordArrayList().size()));
                            inputField.setEditable(false);
                            readMessageFromFileButton.setVisible(false);
                        } else {
                            d.add(error);
                            d.pack();
                            d.setVisible(true);
                        }

                    } catch (IOException ex) {
                        d.add(error);
                        d.pack();
                        d.setVisible(true);
                    }
                } else {
                    d.setLocationRelativeTo(frame);
                    d.add(error);
                    d.pack();
                    d.setVisible(true);
                }
            }
        });

        // attach listener
        reAssembleMessageButton.addActionListener(new ActionListener() {
            // click reassemble message button
            @Override
            public void actionPerformed(ActionEvent e) {
                if (messageNumberComboBox.getModel().getSize() != 0) {
                    String properMessage;
                    Message message = new Message(messageBank, messageNumberComboBox.getSelectedIndex(), wordBank);
                    properMessage = message.getMessage().substring(0, 1).toUpperCase() + message.getMessage().substring(1) + "."; // make first letter uppercase, add period

                    reAssembledMessageField.setText(properMessage); // put the message into the reassembled field

                    // set the current number of messages you have read, it goes up with each time you choose to read a message and keeps track
                    if (!messageCountField.getText().isEmpty()) {
                        int i = Integer.parseInt(messageCountField.getText());
                        i = i + 1;
                        messageCountField.setText(i + "");
                    } else {
                        messageCountField.setText("1");
                    }
                }
            }
        });

        //attach listener
        exitButton.addActionListener(new ActionListener() {
            @Override
            // click exit button to exit
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        // do swing initialization stuff
        frame = new JFrame("App");
        frame.setContentPane(new App().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 4, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label1 = new JLabel();
        label1.setText("Select Message Number");
        mainPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        messageNumberComboBox = new JComboBox();
        messageNumberComboBox.setEditable(false);
        mainPanel.add(messageNumberComboBox, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        reAssembleMessageButton = new JButton();
        reAssembleMessageButton.setText("ReAssemble Message");
        mainPanel.add(reAssembleMessageButton, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("ReAssembled Message");
        mainPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        reAssembledMessageField = new JTextField();
        reAssembledMessageField.setText("");
        mainPanel.add(reAssembledMessageField, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Msg Count");
        mainPanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        msgCountField = new JTextField();
        mainPanel.add(msgCountField, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Input Text Filename");
        mainPanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inputField = new JTextField();
        mainPanel.add(inputField, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        readMessageFromFileButton = new JButton();
        readMessageFromFileButton.setText("Read Message from File");
        mainPanel.add(readMessageFromFileButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        mainPanel.add(exitButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Words Found");
        mainPanel.add(label5, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 7, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        wordBankArea = new JTextArea();
        scrollPane1.setViewportView(wordBankArea);
        messageCountField = new JTextField();
        messageCountField.setText("");
        mainPanel.add(messageCountField, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Message Count");
        mainPanel.add(label6, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Word Count");
        mainPanel.add(label7, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wordCountField = new JTextField();
        mainPanel.add(wordCountField, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label1.setLabelFor(messageNumberComboBox);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
