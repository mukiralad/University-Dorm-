package university.management.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class insertstudent extends Frame
{
	Button insertstudentButton;
	TextField sid,sname,roll_number,gender,hostel_name,room_number;
	TextArea errorText;
	Connection connection;
	Statement statement;
	public insertstudent()
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

		insertstudentButton = new Button("Insert");
		insertstudentButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{

							String query= "INSERT INTO student VALUES("+"'" +sid.getText() + "','" + sname.getText() + "','" + roll_number.getText() + "','" +gender.getText() + "','" +hostel_name.getText()+ "','" +room_number.getText() + "'" +")";
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
		sname = new TextField(15);
		roll_number = new TextField(15);
		gender = new TextField(15);
		hostel_name = new TextField(15);
		room_number = new TextField(15);
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		Panel first = new Panel();
		first.setLayout(new GridLayout(6, 2));
		first.add(new Label("Sid : "));
		first.add(sid);
		first.add(new Label("Name : "));
		first.add(sname);
		first.add(new Label("Roll Number: "));
		first.add(roll_number);
		first.add(new Label("Gender : "));
		first.add(gender);
		first.add(new Label("hostel name: "));
		first.add(hostel_name);
		first.add(new Label("Room Number: "));
		first.add(room_number);
		
		first.setBounds(125,90,200,100);
		Panel second = new Panel(new GridLayout(6, 1));
		second.add(insertstudentButton);
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
		insertstudent i = new insertstudent();
		i.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		i.buildGUI();
	}
}
