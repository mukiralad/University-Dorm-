package university.management.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class updatehostel extends Frame
{
	Button updatehostelButton;
	List hostel_idList;
	TextField hostel_idText,hostel_nameText,wardenText,genderText;
	TextArea errorText;
	Connection connection;
	Statement statement;
	ResultSet rs;
	public updatehostel()
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
	private void loadhostel()
	{
		try
		{
			rs = statement.executeQuery("SELECT hostel_id FROM hostel");
			while (rs.next())
			{
				hostel_idList.add(rs.getString("hostel_id"));
			}
		}
		catch (SQLException e)
		{
			displaySQLErrors(e);
		}
	}
	public void buildGUI()
	{
		hostel_idList = new List(10);
		loadhostel();
		add(hostel_idList);
		hostel_idList.addItemListener(new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						try
						{
							rs = statement.executeQuery("SELECT * FROM hostel where hostel_id ='"+hostel_idList.getSelectedItem()+"'");
							rs.next();
							hostel_idText.setText(rs.getString("hostel_id"));
							hostel_nameText.setText(rs.getString("hostel_name"));
							wardenText.setText(rs.getString("warden"));
							genderText.setText(rs.getString("gender"));
						
						}
						catch (SQLException selectException)
						{
							displaySQLErrors(selectException);
						}
					}
				});
		updatehostelButton = new Button("Update");
		updatehostelButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							 
							Connection con =
								DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737040","vasavi");
							Statement statement1 = con.createStatement();
							int i = statement1.executeUpdate("UPDATE hostel "+ "SET hostel_name='" + hostel_nameText.getText() + "', "+ "warden='" + wardenText.getText() + "', "+ "gender ='"+ genderText.getText() + "' WHERE hostel_id= '"+ hostel_idList.getSelectedItem()+"'");
							errorText.append("\nUpdated " + i + " rows successfully");
							hostel_idList.removeAll();
							loadhostel();
						}
						catch (SQLException insertException)
						{
							displaySQLErrors(insertException);
						}
					}
				});
		hostel_idText = new TextField(15);
		hostel_idText.setEditable(false);
		hostel_nameText = new TextField(15);
		wardenText = new TextField(15);
		genderText = new TextField(15);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("hostel_id : "));
		first.add(hostel_idText);
		first.add(new Label("hostel_name : "));
		first.add(hostel_nameText);
		first.add(new Label("warden: "));
		first.add(wardenText);
		first.add(new Label("Gender : "));
		first.add(genderText);
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updatehostelButton);
		Panel third = new Panel();
		third.add(errorText);
		add(first);
		add(second);
		add(third);
		setTitle("Update hostel ");
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
		updatehostel ups = new updatehostel();
		ups.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		ups.buildGUI();
	}
}