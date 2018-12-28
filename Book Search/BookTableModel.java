/*
 *Author : KHP
 */


import java.sql.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;


public class BookTableModel extends AbstractTableModel {
	private static int num;    // to use as the printed-out SEARCH number
	private ArrayList<Book> bookList;
	private int rows, cols = 3;
	private String[] columnName;
	private Connection conn = null;

	public BookTableModel(String driver, String url, String username, String password)  {
		columnName = new String[]{"ID", "Title", "Author"};
		System.out.println("Connecting...");
		bookList = new ArrayList<Book>();
		
		try {
			//connect the MySQL server using the installed JDBC_DRIVER and make a connection object
	      Class.forName(Main.JDBC_DRIVER);
	      System.out.println("... Driver loaded.");   
	      conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASS);
	      if (conn != null)
	    	System.out.println("...MySQL Server connected.");
	      else
	    	System.out.println("Failed to make connection!");
		} catch (ClassNotFoundException exp) {
			System.out.println(driver + " Not found");
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		populateTable("");    // A list of all books will be displayed when the program is started.
	}

	/**
	* Returns the value held at a particular point in the table
	* using the ArrayList<Book>
	* row = the element in the ArrayList
	* col = the particular property of the book
	*/
	public Object getValueAt(int row, int col) {
		//get specific fields from the book objects stored in the ArrayList to set the cells of table object
		if (col==0) {
			return bookList.get(row).getID();
		} else if (col==1) {
			return bookList.get(row).getTitle();
		} else if (col==2) {
			return bookList.get(row).getAuthor();
		} else {
			return null;
		}
	}

	/**
	* populate the table by executing a sql statement
	*/
	public void populateTable(String searchTitle) {
		searchTitle = searchTitle.replaceAll("%", "`%"); //to keep the search from retrieving all items when % is input
		searchTitle = searchTitle.replaceAll("_", "`_"); //to keep the search from retrieving all items when _ is input
		Statement statement = null;
		String sql = null;
		ResultSet rs = null;
		bookList.clear();    //clear the ArrayList
		try {
			//Searching with no input text in the text field, no table populates.
			//if(searchTitle.length() == 0) {    // If lines 74, 75, 77, 87 are activated, no list would be appear when the program is first started.
				//fireTableChanged(null);
			//Searching with an input text in the text field, a table of list including the input text string populates.
			//} else {
				sql = "select * from BOOKS where title like '%" + searchTitle + "%' escape '`'";    //sql String with escape for % and _
				statement = conn.createStatement();    //make a Statement object to execute sql command through JDBC
				rs = statement.executeQuery(sql);	    //get ResultSet object storing an object retrieved from the DB
		        if (rs != null) {
					while (rs.next())
					bookList.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3)));   //add book objects from the ResultSet object to the ArrayList
		        } else {			
				fireTableChanged(null);    // If the retrieved object is null, no table populates.
				}
			//}
			System.out.printf("SEARCH %d : The number of books is %d.\n", num, bookList.size()); //print out the number of books retrieved
			num++;
			rows = bookList.size();    //set the number of rows of the table based on the retrieved book objects stored in the ArrayList
		} catch (SQLException exp) {
			System.out.println("Error in : " + sql);
			exp.printStackTrace();
		}
	}

	/**
	* return a book's details by executing a sql statement
	*/
	public Book getCurrentBook(int row) {
		Statement statement = null;
		String sql = null;
		ResultSet rs = null;
		Book bk = bookList.get(row);
		int bookID = bk.getID();
		try {
			//retrieve a book object based on the bookID
			sql = "select * from BOOKS where book_id='" + bookID + "'";    //sql String
			statement = conn.createStatement();    //make a Statement object to execute sql command through JDBC
			rs = statement.executeQuery(sql);    //get ResultSet object storing an object retrieved from the DB
	        if (rs != null) {
	        	
				while (rs.next())
					//make a book object based on retrieved ResultSet object
					bk = new Book (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));	
							
	        } else {			
			fireTableChanged(null);    // If the retrieved object is null, no table populates.
				}

		} catch (SQLException exp) {
			System.out.println("Error in : " + sql);
			exp.printStackTrace();
		}
		
		return bk;
	}

	/**
	* Returns number of rows in table
	*/
	public int getRowCount() { return rows; }

	/**
	* Returns number of columns in table
	*/
	public int getColumnCount() { return cols; }

	/**
	* Returns the title of a particular column
	*/
	public String getColumnName(int col) {return columnName[col];}

	/**
	* This indicates to the table object whether the
	* specified cell is editable. by default the key column is
	* not editable. This could be changed to allow user to specify this..
	*/
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}