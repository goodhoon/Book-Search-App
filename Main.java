/*
 *Author : KHP
 */

 
// Confirm that the jdbc driver of the line 19 is installed in Eclipse.
// Set the password in the line 21 to the one for root of MySql

 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.*;    //import to declare JTableHeader header


public class Main extends JFrame {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String USER = "root"; 
	static final String PASS = "12345678";
	static final String URL = "jdbc:mysql://35.244.78.219:3306/bookshelf";

	//declare and create GUI components
	//declare a pane to include a panel and scroll panes
	private Container pane;
	
	//declare components for a panel
	private JLabel label;
	private JTextField titleTextField;
	private JButton searchButton;
	//declare a panel to include the components
	private JPanel topPanel;

	//declare a table
	private BookTableModel bookTableModel;
	private JTable bookTable;
	private JTableHeader header;
	//declare scroll pane to include the table
	private JScrollPane scrollPane;

	//declare a text area
	private JTextArea content;
	//declare a pane to include the text area
	private JScrollPane scrollPane2;

	
  public Main() {
	//set JFrame type and title
  	setType(Type.NORMAL);
  	setTitle("Book Search");
  	
    //make a pane object to include a panel and scroll panes
  	pane = getContentPane();
    pane.setLayout(new BorderLayout());
    
    //make components
    label = new JLabel("Enter title");
    label.setFont(new Font("Tahoma", Font.BOLD, 16));
    titleTextField = new JTextField();
  	titleTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
	titleTextField.setColumns(10);
	searchButton = new JButton("Search");
	searchButton.setBackground(UIManager.getColor("Button.background"));
	searchButton.setFont(new Font("Tahoma", Font.BOLD, 16));
  	//make a panel object to include the components
    topPanel = new JPanel();
    topPanel.setBackground(Color.WHITE);

	//make a table object
	bookTableModel = new BookTableModel(JDBC_DRIVER, URL, USER, PASS);
	bookTable = new JTable(bookTableModel);
	bookTable.setFillsViewportHeight(true);
	bookTable.setBackground(Color.WHITE);
	bookTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
	bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	bookTable.setRowHeight(25);
	//make a scroll pane object to include the table object
	scrollPane = new JScrollPane(bookTable);
	scrollPane.setPreferredSize(new Dimension(700, 175));
	//make a table header object to set the header font BOLD
	header = bookTable.getTableHeader();
	header.setFont(new Font("Tahoma", Font.BOLD, 16));
	
	//make a text area object
	content = new JTextArea(5, 20);
	content.setLineWrap(true);
	content.setFont(new Font("Monospaced", Font.PLAIN, 18));
	//make a scroll pane object to include the text area object
	scrollPane2 = new JScrollPane(content);
	scrollPane2.setPreferredSize(new Dimension(700, 155));
	
	//set layout of the panel object and add the components
    topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	topPanel.add(label);
	topPanel.add(titleTextField);
	topPanel.add(searchButton);
	//add the panel and scroll panes to the pane
  	pane.add(topPanel, BorderLayout.PAGE_START);
  	pane.add(scrollPane, BorderLayout.CENTER);
	pane.add(scrollPane2, BorderLayout.PAGE_END);

	
    titleTextField.addActionListener(new ActionListener() {
		// This method is invoked when the user hits ENTER in the field
		public void actionPerformed(ActionEvent e) {
		    //get the input text from the text field and populate a new table
			bookTableModel.populateTable(titleTextField.getText());
		    bookTableModel.fireTableDataChanged();
		}
	});
	    
    searchButton.addActionListener(new ActionListener() {
			// This method is invoked when the user hits ENTER in the field
		public void actionPerformed(ActionEvent e) {
			//get the input text from the text field and populate a new table
			bookTableModel.populateTable(titleTextField.getText());
			bookTableModel.fireTableDataChanged();

		}
    });
    	
		bookTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			//This method is invoked when the user hits a row of the retrieved list of the table
		public void valueChanged(ListSelectionEvent event) {
			//if-else clause is implemented since the getSelectedRow() method returns -1 when searching another title after selecting a row
			if (bookTable.getSelectedRow() == -1) {
				bookTableModel.populateTable(titleTextField.getText());
				bookTableModel.fireTableDataChanged();
				content.setText(null);
			} else {
			//set the details of the selected row(a book) into the text area
			content.setText(bookTableModel.getCurrentBook(bookTable.getSelectedRow()).toString());
			}
	    }

    });
  }

   /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    //Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);
    //Create and set up the window.
    Main frame = new Main();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
	//Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}