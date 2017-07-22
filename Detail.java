package banking;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
class Detail   
{
Label l1,l2,l3,l4,l5,l6,l7;
//TextField tf1,tf2,tf3,tf4;
//TextField tf5,tf6;
Button b1,b2;
//CheckboxGroup cg1,cg2;
final JFrame f;
Panel p1;
Detail( String dtname,int w,int h)
{
	f=new JFrame("Customer Details");
f.setLayout(new FlowLayout());
f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
try
{
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","akshay", "password");
String sql = "Select * FROM Details";

Statement statement = con.createStatement();
ResultSet result = statement.executeQuery(sql);
int f=1;
while(result.next())
{
if(dtname.equalsIgnoreCase(result.getString(1)))
{	f=0;
break;
}
}
if(f==0){
	
     l1=new Label("Detailed Entered Successfully");
     l2=new Label("Name:-    "+result.getString(1) );
     l3=new Label("DOB:-    "+result.getString(3));
     l4=new Label("Account Type:-      "+result.getString(7));
     l5=new Label("Minimum Balance Scheme:-   "+result.getInt(8));
     l6=new Label("Contact No.-   "+result.getInt(5));
     l7=new Label("Email:-      "+result.getString(6));

con.close();
statement.close();
}
else
{}
}catch(Exception e)
{
}
b1=new Button("Back");b2=new Button("Login");
b1.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new Signup(w,h);
	}
});
b2.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new Action(dtname,w,h);
	}
});

p1=new Panel();
p1.add(b1);p1.add(b2);

f.add(l1);f.add(l2);f.add(l3);f.add(l4);f.add(l5);f.add(l6);f.add(l7);f.add(p1);//add(p2);add(p3);add(p4);add(p5);add(p6);add(p7);add(p8);add(p9);
f.setResizable(true);

//f.setSize(300,400);
f.setVisible(true);
}
}