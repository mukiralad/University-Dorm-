package university.management.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class updatestudent extends Frame
{
	Button updatestudentButton;
	List sidList;
	TextField sidText,snameText,roll_numberText,genderText,hostel_nameText,room_numberText;
	TextArea errorText;
	Connection connection;
	Statement statement;
	ResultSet rs;
	public updatestudent()
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
	private void loadstudent()
	{
		try
		{
			rs = statement.executeQuery("SELECT sid FROM student");
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
		loadstudent();
		add(sidList);
		sidList.addItemListener(new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						try
						{
							rs = statement.executeQuery("SELECT * FROM student where sid ='"+sidList.getSelectedItem()+"'");
							rs.next();
							sidText.setText(rs.getString("sid"));
							snameText.setText(rs.getString("sname"));
							roll_numberText.setText(rs.getString("roll_number"));
							genderText.setText(rs.getString("gender"));
							hostel_nameText.setText(rs.getString("hostel_name"));
							room_numberText.setText(rs.getString("room_number"));
						}
						catch (SQLException selectException)
						{
							displaySQLErrors(selectException);
						}
					}
				});
		updatestudentButton = new Button("Update");
		updatestudentButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							 
							Connection con =
								DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737040","vasavi");
							Statement statement1 = con.createStatement();
							int i = statement1.executeUpdate("UPDATE student "+ "SET sname='" + snameText.getText() + "', "+ "roll_number='" + roll_numberText.getText() + "', "+ "gender ='"+ genderText.getText()+"',"+"hostel_name='" +hostel_nameText.getText()+"',"+"room_number='" +room_numberText.getText() + "' WHERE sid= '"+ sidList.getSelectedItem()+"'");
							errorText.append("\nUpdated " + i + " rows successfully");
							sidList.removeAll();
							loadstudent();
						}
						catch (SQLException insertException)
						{
							displaySQLErrors(insertException);
						}
					}
				});
		sidText = new TextField(15);
		sidText.setEditable(false);
		snameText = new TextField(15);
		roll_numberText = new TextField(15);
		genderText = new TextField(15);
		hostel_nameText = new TextField(15);
		room_numberText = new TextField(15);
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		Panel first = new Panel();
		first.setLayout(new GridLayout(6, 2));
		first.add(new Label("Sid : "));
		first.add(sidText);
		first.add(new Label("Name : "));
		first.add(snameText);
		first.add(new Label("Roll Number: "));
		first.add(roll_numberText);
		first.add(new Label("Gender : "));
		first.add(genderText);
		first.add(new Label("hostel name: "));
		first.add(hostel_nameText);
		first.add(new Label("Room Number: "));
		first.add(room_numberText);
		Panel second = new Panel(new GridLayout(6, 1));
		second.add(updatestudentButton);
		Panel third = new Panel();
		third.add(errorText);
		add(first);
		add(second);
		add(third);
		setTitle("Update student ");
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
		updatestudent ups = new updatestudent();
		ups.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		ups.buildGUI();
	}
}