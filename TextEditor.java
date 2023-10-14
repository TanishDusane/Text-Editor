import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextEditor implements ActionListener {

    JFrame frame; 
    JMenuBar menubar; 
    JMenu file, edit; 
    JMenuItem newfile, openfile, savefile; 
    JMenuItem copy, cut, paste, selectall, close; 
    JTextArea textarea; 

    TextEditor() {
        // Create a new JFrame (window) for the text editor
        frame = new JFrame();

        // Create a menu bar to hold the File and Edit menus
        menubar = new JMenuBar();

        textarea = new JTextArea();

        // Create File and Edit menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Add File and Edit menus to the menu bar
        menubar.add(file);
        menubar.add(edit);

        // Create menu items for File menu
        newfile = new JMenuItem("New File");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save File");

        // Add ActionListener to file Menu items
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);

        // Add menu items to the File menu
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        // Create menu items for Edit menu
        copy = new JMenuItem("Copy");
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Add menu items to the Edit menu
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        // Add menu items to the Edit menu
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);

        // Set the menu bar for the frame
        frame.setJMenuBar(menubar);
        
        // Create a panel to hold the text area with a border layout
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(textarea, BorderLayout.CENTER);
        
        // Add a scroll pane to the panel
        JScrollPane scrollPane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        
        // Add the panel to the frame
        frame.add(panel);

        // Set the position and size of the frame
        frame.setBounds(150, 200, 900, 490);

        // Make the frame visible
        frame.setVisible(true);

        // Use a null layout for the frame (not recommended for complex GUIs)
        frame.setLayout(null);

        frame.setTitle("My Text Editor");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        // Check if the source of the action event is the "Copy" menu item
        if (actionEvent.getSource() == copy) {
            // If it is, perform the "Copy" operation on the text area
            textarea.copy();
        }

        // Check if the source of the action event is the "Cut" menu item
        if (actionEvent.getSource() == cut) {
            // If it is, perform the "Cut" operation on the text area
            textarea.cut();
        }

        // Check if the source of the action event is the "Paste" menu item
        if (actionEvent.getSource() == paste) {
            // If it is, perform the "Paste" operation on the text area
            textarea.paste();
        }

        // Check if the source of the action event is the "Select All" menu item
        if (actionEvent.getSource() == selectall) {
            // If it is, select all the text in the text area
            textarea.selectAll();
        }

        // Check if the source of the action event is the "Close" menu item
        if (actionEvent.getSource() == close) {
           // If it is, exit the application by closing the window
            System.exit(0);
        }

        if (actionEvent.getSource() == openfile) {
            // Open a file dialog to choose a file to open
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filepath = file.getPath();
                try {
                    // Read the selected file and display its content in the text area
                    FileReader fileReader = new FileReader(filepath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate, output = "";
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }

                    textarea.setText(output);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if (actionEvent.getSource() == savefile) {
            // Open a file dialog to choose a location to save the file
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // Create a new file with a ".txt" extension and write the text area content to it
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textarea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioexception) {
                    ioexception.printStackTrace();
                }
            }
        }

        if (actionEvent.getSource() == newfile) {
            // Create a new instance of the TextEditor class to open a new window
            TextEditor texteditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        // Create an instance of the TextEditor class to start the application
        TextEditor texteditor = new TextEditor();
    }
}