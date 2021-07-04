package university.management.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class updatedepartment extends Frame
{
	Button updatedepartmentButton;
	List sidList;
	TextField sidText,dept_nameText,faculty_advisorText;
	TextArea errorText;
	Connection connection;
	Statement statement;
	ResultSet rs;
	public updatedepartment()
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
			Connection con =
				DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737040","vasavi");
			statement = con.createStatement();
			System.out.println("connected");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	private void loaddepartment()
	{
		try
		{
			rs = statement.executeQuery("SELECT sid FROM department");
			while (rs.next())
			{
				sidList.add(rs.getString("sid"));
			}
		}
		catch (SQLException e)
		{
			displaySQLErrors(e);
		}
	}
	public void buildGUI()
	{
		sidList = new List(10);
		loaddepartment();
		add(sidList);
		sidList.addItemListener(new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						try
						{
							rs = statement.executeQuery("SELECT * FROM department where sid ='"+sidList.getSelectedItem()+"'");
							rs.next();
							sidText.setText(rs.getString("sid"));
							dept_nameText.setText(rs.getString("dept_name"));
							faculty_advisorText.setText(rs.getString("faculty_advisor"));
						}
						catch (SQLException selectException)
						{
							displaySQLErrors(selectException);
						}
					}
				});
		updatedepartmentButton = new Button("Update");
		updatedepartmentButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							 
							Connection con =
								DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737040","vasavi");
							Statement statement1 = con.createStatement();
							int i = statement1.executeUpdate("UPDATE department "+ "SET dept_name='" + dept_nameText.getText() + "', "+ "faculty_advisor='" + faculty_advisorText.getText()  + "' WHERE sid= '"+ sidList.getSelectedItem()+"'");
							errorText.append("\nUpdated " + i + " rows successfully");
							sidList.removeAll();
							loaddepartment();
						}
						catch (SQLException insertException)
						{
							displaySQLErrors(insertException);
						}
					}
				});
		sidText = new TextField(15);
		sidText.setEditable(false);
		dept_nameText = new TextField(15);
		faculty_advisorText = new TextField(15);
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Sid : "));
		first.add(sidText);
		first.add(new Label("dept_name : "));
		first.add(dept_nameText);
		first.add(new Label("faculty_advisor: "));
		first.add(faculty_advisorText);
		Panel second = new Panel(new GridLayout(3, 1));
		second.add(updatedepartmentButton);
		Panel third = new Panel();
		third.add(errorText);
		add(first);
		add(second);
		add(third);
		setTitle("Update department ");
		setSize(500, 600);
		setLayout(new FlowLayout());
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
		updatedepartment ups = new updatedepartment();
		ups.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		ups.buildGUI();
	}
}