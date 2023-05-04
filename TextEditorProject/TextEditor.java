import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TextEditor implements ActionListener{
    //window frame variable
    JFrame frame;
    //declare menu-bar
    JMenuBar menuBar;
    // declare menus
    JMenu file,edit;
    // declare menuItems
    JMenuItem open,newWindow,save,cut,copy,paste,selectAll,close;
    //text area
    JTextArea textarea;
    //icon
    ImageIcon icon;

    TextEditor(){
        //initialize frame
        frame = new JFrame("TextEditor");
        //set icon
        icon = new ImageIcon("img.png");
        frame.setIconImage(icon.getImage());
        
        //initialize menubar
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.lightGray);
        //text area
        textarea = new JTextArea();
        
        // initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        
        // initialize menu items of file menu
        open = new JMenuItem("Open File");
        newWindow = new JMenuItem("New Window");
        save = new JMenuItem("Save File");

        // initialize menu items of edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        
        //add/tie action listener to menu items of file and edit
        open.addActionListener(this);
        newWindow.addActionListener(this);
        save.addActionListener(this);
        
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // adding menuItems to file menu
        file.add(open);
        file.add(save);
        file.add(newWindow);
        
        // adding menuItems to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Adding menus in menu-bar
        menuBar.add(file);
        menuBar.add(edit);
        
        //Adding menu-bar in frame/window
        frame.setJMenuBar(menuBar);
        frame.add(textarea);
        
        //initialize frame width and height and initial position
        frame.setVisible(true);
        frame.setBounds(100, 100, 800, 500);
        textarea.setFont(new Font("Arial",Font.PLAIN,20));
        
        //program termination on close(X) button
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frame.dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEventId){
        //performing actions 
        if (actionEventId.getSource()==open) {
            // create object of file-chooser
            JFileChooser fileChooser=new JFileChooser("c:");
            //cancel and open options
            int option = fileChooser.showOpenDialog(null);
            //approve option == open
            if (option==JFileChooser.APPROVE_OPTION) {
                //get selected file
                File f=fileChooser.getSelectedFile();
                // read all data from file and write all data in text editor
                try {
                    BufferedReader br = new BufferedReader(new FileReader(f.getPath()));
                    //read data line by line and store it,and then paste in text editor;
                    String sb=new String();
                    StringBuilder result=new StringBuilder();
                    while((sb=br.readLine())!=null){
                        result.append(sb+"\n");
                    }
                    textarea.setText(result.toString());
                    //name of file in top left corner
                    frame.setTitle(f.getName()+"  TextEditor");
                    br.close();
                } catch (Exception e) {
                    // pass
                }                
            }
        }
        if (actionEventId.getSource()==newWindow) {
            // TextEditor nTextEditor=new TextEditor();
            new TextEditor();
        }
        if (actionEventId.getSource()==save) {
            // create object of file chooser
            JFileChooser fileChooser=new JFileChooser("c:");
            //cancel and save options
            int option = fileChooser.showSaveDialog(null);
            //approve option == save
            if (option==JFileChooser.APPROVE_OPTION) {
                // create new file
                String path=fileChooser.getSelectedFile().getAbsolutePath();
                File file;
                if(path.endsWith(".txt")) file=new File(path);
                else file=new File(path+".txt");
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    textarea.write(bufferedWriter);
                    //name of file in top left corner
                    frame.setTitle(file.getName()+"  TextEditor");
                    bufferedWriter.close();
                } catch (Exception e) {
                    // 
                }
            }
        }
        if (actionEventId.getSource()==cut)     textarea.cut();
        if (actionEventId.getSource()==copy)    textarea.copy();
        if (actionEventId.getSource()==paste)   textarea.paste();
        if (actionEventId.getSource()==selectAll)   textarea.selectAll();
        if (actionEventId.getSource()==close) {
            // System.exit(0);
            frame.dispose();
        }
    }

    public static void main(String[] args) {
        // creating ui/object creation;
        TextEditor textEditor=new TextEditor();
    }
}