package university.management.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Mainframe extends JFrame{
 public static void main(String[] args) {
 new Mainframe().setVisible(true);
 }
 
 public Mainframe() {
 super("UNIVERSITY DORMITORY ALLOTEMENT PORTAL");
 initialize();
 }
 private void initialize() {
 setForeground(Color.CYAN);
 setLayout(null); 
 JLabel UniversityManagementSystem = new JLabel("UNIVERSITY DORMITORY ALLOTEMENT PORTAL");
 UniversityManagementSystem.setForeground(Color.RED);
 UniversityManagementSystem.setFont(new Font("Tahoma", Font.PLAIN, 45));
 UniversityManagementSystem.setBounds(260, 330, 1000, 55);
add(UniversityManagementSystem);
 JMenuBar menuBar = new JMenuBar();
setJMenuBar(menuBar);
 JMenu student = new JMenu("STUDENT           ");
 student.setForeground(Color.RED);
menuBar.add(student);
 
 JMenuItem insertstudent = new JMenuItem("Insert");
student.add(insertstudent);
 
 JMenuItem updatestudent = new JMenuItem("Modify");
student.add(updatestudent);
 
 JMenuItem deletestudent = new JMenuItem("Delete");
student.add(deletestudent);
JMenu department = new JMenu("DEPARTMENT             ");
department.setForeground(Color.RED);
menuBar.add(department);
JMenuItem insertdepartment = new JMenuItem("Insert");
department.add(insertdepartment);
 
 JMenuItem updatedepartment = new JMenuItem("Modify");
 department.add(updatedepartment);
 
 JMenuItem deletedepartment = new JMenuItem("Delete");
 department.add(deletedepartment);
 
 
 JMenu room = new JMenu("ROOM          ");
 room.setForeground(Color.RED);
menuBar.add(room);
 
 JMenuItem insertroom = new JMenuItem("Insert");
 room.add(insertroom);
 
 JMenuItem updateroom = new JMenuItem("Modify");
 room.add(updateroom);
 
 JMenuItem deleteroom = new JMenuItem("Delete");
 room.add(deleteroom);
 JMenu hostel = new JMenu("HOSTEL   ");
 hostel.setForeground(Color.RED);
menuBar.add(hostel);
 
 JMenuItem inserthostel = new JMenuItem("Insert");
 hostel.add(inserthostel);
 
 JMenuItem updatehostel = new JMenuItem("Modify");
 hostel.add(updatehostel);
 
 JMenuItem deletehostel = new JMenuItem("Delete");
 hostel.add(deletehostel);
 insertstudent.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 try {
 new insertstudent();
} catch (Exception e) {e.printStackTrace();
} 
 }
});
 
 updatestudent.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 try {
 new updatestudent();
} catch (Exception e) {
 e.printStackTrace();
} 
 }
});
 deletestudent.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 new deletestudent();
 }
});
 
insertdepartment.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 try {
 new insertdepartment();
} catch (Exception e) {
 e.printStackTrace();
} 
 }
});
 
 updatedepartment.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 try {
 new updatedepartment();
} catch (Exception e) {
 e.printStackTrace();
} 
 }
});
 
 
 deletedepartment.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 new deletedepartment();
 }});
 
 insertroom.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 try {
 new insertroom();
} catch (Exception e) {
 e.printStackTrace();
} 
 }
});
 
 updateroom.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 try {
 new updateroom();
} catch (Exception e) {
 e.printStackTrace();
} 
 }
});

 
 deleteroom.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 new deleteroom();
 }
});
 
 inserthostel.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 try {
 new inserthostel();
} catch (Exception e) {
 e.printStackTrace();
} 
 }
});
 
 updatehostel.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 try {
 new updatehostel();
} catch (Exception e) {
 e.printStackTrace();
}}
});
 
 
 
 deletehostel.addActionListener(new ActionListener(){
 public void actionPerformed(ActionEvent ae){
 new deletehostel();
 }
});
 setSize(700,500);
 setLocation(285,100);
setVisible(true);
 }
}
