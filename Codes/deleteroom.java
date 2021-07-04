package university.management.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class deleteroom extends Frame
{
Button deleteroomButton;
List room_numberList;
TextField room_numberText,hostel_nameText,allotedText;
TextArea errorText;
Connection connection;
Statement statement;
ResultSet rs;
public deleteroom()
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
private void loadroom()
{
try
{
rs = statement.executeQuery("SELECT * FROM room");
while (rs.next())
{
room_numberList.add(rs.getString("room_number"));
}
}
catch (SQLException e)
{
displaySQLErrors(e);
}
}
public void buildGUI()
{
room_numberList = new List(10);
loadroom();
add(room_numberList);
room_numberList.addItemListener(new ItemListener()
{
public void itemStateChanged(ItemEvent e)
{
try
{
rs = statement.executeQuery("SELECT * FROM room where room_number ='"+room_numberList.getSelectedItem()+"'");
while (rs.next())
{
if
(rs.getString("room_number").equals(room_numberList.getSelectedItem()))
break;
}
if (!rs.isAfterLast())
{
	room_numberText.setText(rs.getString("room_number"));
	hostel_nameText.setText(rs.getString("hostel_name"));
	allotedText.setText(rs.getString("alloted"));
}
}
catch (SQLException selectException)
{
displaySQLErrors(selectException);
}
}
});
deleteroomButton = new Button("Delete room");
deleteroomButton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
Connection con= DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737040","vasavi");
Statement statement1 = con.createStatement();
int i = statement1.executeUpdate("DELETE FROM room WHERE room_number = '"+room_numberList.getSelectedItem()+"' and hostel_name='"+hostel_nameText.getText()+"' and  alloted='"+allotedText.getText()+"'");
errorText.append("\nDeleted " + i + " rows successfully");
hostel_nameText.setText(null);
allotedText.setText(null);
room_numberList.removeAll();
loadroom();
}
catch (SQLException insertException)
{
displaySQLErrors(insertException);
}
}
});
room_numberText = new TextField(15);
hostel_nameText = new TextField(15);
allotedText = new TextField(15);
errorText = new TextArea(10, 40);
errorText.setEditable(false);
Panel first = new Panel();
first.setLayout(new GridLayout(3, 2));
first.add(new Label("room_number : "));
first.add(room_numberText);
first.add(new Label("hostel_name : "));
first.add(hostel_nameText);
first.add(new Label("alloted: "));
first.add(allotedText);
Panel second = new Panel(new GridLayout(3, 1));
second.add(deleteroomButton);
Panel third = new Panel();
third.add(errorText);
add(first);
add(second);
add(third);
setTitle("Remove  room ");
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
		deleteroom del = new deleteroom();
		del.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		del.buildGUI();
	}
}

