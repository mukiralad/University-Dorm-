package university.management.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class deletedepartment extends Frame
{
Button deletedepartmentButton;
List sidList;
TextField sidText,dept_nameText,faculty_advisorText;
TextArea errorText;
Connection connection;
Statement statement;
ResultSet rs;
public deletedepartment()
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
private void loaddepartment()
{
try
{
rs = statement.executeQuery("SELECT * FROM department");
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
while (rs.next())
{
if
(rs.getString("sid").equals(sidList.getSelectedItem()))
break;
}
if (!rs.isAfterLast())
{
	sidText.setText(rs.getString("sid"));
	dept_nameText.setText(rs.getString("dept_name"));
	faculty_advisorText.setText(rs.getString("faculty_advisor"));
}
}
catch (SQLException selectException)
{
displaySQLErrors(selectException);
}
}
});
deletedepartmentButton = new Button("Delete department");
deletedepartmentButton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
Connection con= DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737040","vasavi");
Statement statement1 = con.createStatement();
int i = statement1.executeUpdate("DELETE FROM department WHERE sid = '"+sidList.getSelectedItem()+"' and dept_name='"+dept_nameText.getText()+"' and  faculty_advisor='"+faculty_advisorText.getText()+"'");
errorText.append("\nDeleted " + i + " rows successfully");
dept_nameText.setText(null);
faculty_advisorText.setText(null);
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
second.add(deletedepartmentButton);
Panel third = new Panel();
third.add(errorText);
add(first);
add(second);
add(third);
setTitle("Remove  department ");
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
		deletedepartment del = new deletedepartment();
		del.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		del.buildGUI();
	}
}

