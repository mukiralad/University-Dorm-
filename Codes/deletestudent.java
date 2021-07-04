package university.management.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class deletestudent extends Frame
{
Button deletestudentButton;
List sidList;
TextField sidText,snameText,roll_numberText,genderText,hostel_nameText,room_numberText;
TextArea errorText;
Connection connection;
Statement statement;
ResultSet rs;
public deletestudent()
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
private void loadstudent()
{
try
{
rs = statement.executeQuery("SELECT * FROM student");
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
while (rs.next())
{
if
(rs.getString("sid").equals(sidList.getSelectedItem()))
break;
}
if (!rs.isAfterLast())
{
	sidText.setText(rs.getString("sid"));
	snameText.setText(rs.getString("sname"));
	roll_numberText.setText(rs.getString("roll_number"));
	genderText.setText(rs.getString("gender"));
	hostel_nameText.setText(rs.getString("hostel_name"));
	room_numberText.setText(rs.getString("room_number"));
}
}
catch (SQLException selectException)
{
displaySQLErrors(selectException);
}
}
});
deletestudentButton = new Button("Delete student");
deletestudentButton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
Connection con= DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737040","vasavi");
Statement statement1 = con.createStatement();
int i = statement1.executeUpdate("DELETE FROM student WHERE sid = '"+sidList.getSelectedItem()+"' and sname='"+snameText.getText()+"' and  roll_number='"+roll_numberText.getText()+"' and gender ='"+genderText.getText()+"' and  hostel_name='"+hostel_nameText.getText()+"' and  room_number='"+room_numberText.getText()+"'");
errorText.append("\nDeleted " + i + " rows successfully");
snameText.setText(null);
roll_numberText.setText(null);
genderText.setText(null);
hostel_nameText.setText(null);
room_numberText.setText(null);
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
second.add(deletestudentButton);
Panel third = new Panel();
third.add(errorText);
add(first);
add(second);
add(third);
setTitle("Remove  student ");
setSize(450, 600);
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
		deletestudent del = new deletestudent();
		del.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		del.buildGUI();
	}
}
