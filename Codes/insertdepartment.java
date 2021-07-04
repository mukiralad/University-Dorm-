package university.management.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class insertdepartment extends Frame
{
	Button insertdepartmentButton;
	TextField sid,dept_name,faculty_advisor;
	TextArea errorText;
	Connection connection;
	Statement statement;
	public insertdepartment()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception e)
		{
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
		connectToDB();
	}
	public void connectToDB()
	{
		try
		{    
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737040","vasavi");

			statement = con.createStatement();
			System.out.println("connected");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	public void buildGUI()
	{

		insertdepartmentButton = new Button("Insert");
		insertdepartmentButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{

							String query= "INSERT INTO department VALUES("+"'" +sid.getText() + "','" + dept_name.getText() + "','" + faculty_advisor.getText()  + "'" +")";
							int i = statement.executeUpdate(query);
							errorText.append("\nInserted " + i + " rows successfully");
						}
						catch (SQLException insertException)
						{
							displaySQLErrors(insertException);
						}
					}
				});
		sid = new TextField(15);
		dept_name = new TextField(15);
		faculty_advisor = new TextField(15);
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Sid : "));
		first.add(sid);
		first.add(new Label("dept_name : "));
		first.add(dept_name);
		first.add(new Label("faculty_advisor: "));
		first.add(faculty_advisor);
	
		first.setBounds(125,90,200,100);
		Panel second = new Panel(new GridLayout(3, 1));
		second.add(insertdepartmentButton);
		second.setBounds(125,220,150,100);
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		setLayout(null);
		add(first);
		add(second);
		add(third);
		setTitle("Insert Gets");
		setSize(500, 600);
		setVisible(true);
	}
	private void displaySQLErrors(SQLException e)
	{
		errorText.append("\nSQLException: " + e.getMessage() + "\n");
		errorText.append("SQLState: " + e.getSQLState() + "\n");
		errorText.append("VendorError: " + e.getErrorCode() + "\n");
	}

public static void main(String[] args)
	{
		insertdepartment i = new insertdepartment();
		i.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		i.buildGUI();
	}
}
