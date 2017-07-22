package banking;
import java.awt.*;

import javax.swing.*;

import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.PreparedStatement;


import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
class Signup
{
Label l1,l2,l3,l4,l5,l6,l7,l8;
TextField tf1,tf2,tf3,tf4,tf5,tf6,tf7;
TextField tf8;
Button b1,b2,b3;
final JFrame f;
String sname;
Panel p1,p2,p3,p4,p5,p6,p7,p8,p9;
Signup(int w,int h)
{
	f=new JFrame("New Customer Signup Page");
f.setLayout(new GridLayout(9,2));
f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);



l1=new Label("A/C Holder Name");
l2=new Label("Enter 5 Digit A/C No.");
l3=new Label(" Date of Birth(dd-MM-yyyy)");
l4=new Label("Address:");
l5=new Label("Contact Number");
l6=new Label("Email:");
l7=new Label("Account Type(Saving/Current)");
l8=new Label("Minimum Balance Scheme(500/1000)");

//l5=new Label(" Cash Transfered Succesfully");
//l5=new Label(" Account  has Insufficent balance to transfer ");
b1=new Button("Exit");
b2=new Button("Signup Now");b3=new Button("Back");

b1.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		int result=JOptionPane.showConfirmDialog((Component)null,"Exit","Are you sure you want to exit|",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION)
		{	
 		f.dispose();
		}
		else
		{
			
		}
		
		//f.dispose();
		//new Action();
	}
});
b2.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","akshay", "password");
				//Statement stm=con.createStatement();
				
String sql = "INSERT INTO Details (Ac_Holder_Name, Acc_No,DOB,Address,Contact_No,Email,Acc_Type,MinBalScheme,Remark, Balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				 
				PreparedStatement statement = (PreparedStatement)con.prepareStatement(sql);
				statement.setString(1,tf1.getText());
				statement.setInt(2, Integer.parseInt(tf2.getText()));
				statement.setString(3, tf3.getText());
				statement.setString(4, tf4.getText());
				statement.setInt(5, Integer.parseInt(tf5.getText()));
				statement.setString(6,tf6.getText());
				statement.setString(7, tf7.getText());
				statement.setInt(8, Integer.parseInt(tf8.getText()));
				statement.setString(9," ");
				statement.setInt(10,0);
            sname=tf1.getText();
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
				    //System.out.println("A new user was inserted successfully!");
					String dialogtitle="New Customer added";
			 		String dialogmessage="New Customer Successfully Added!";
			 		int dialogtype = JOptionPane.PLAIN_MESSAGE;
			 		JOptionPane.showMessageDialog((Component)null,dialogmessage,dialogtitle,dialogtype);
				con.close();
				}			
				//String sql="INSERT INTO Details VALUES("'"+tf1.getText()+"',"'"+tf2.getText()+"',"'"+tf3.getText()+"',"'"+tf4.getText()+"',"'"+tf5.getText()+"',"'"+tf6.getText()+"',"'"+tf7.getText()+"',"'"+tf8.getText()+"' ,"'+"',"'"+"0"+"',"'"+);
                  	}catch(Exception e)
		{
		//e.printStackTrace();
		}
		//String ss=tf1.getText();
//f.dispose();
new Detail(sname,w,h);
f.dispose();
}
	
});
b3.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new Banking();
	}
});

p1=new Panel();p2=new Panel();p3=new Panel();p4=new Panel();p5=new Panel();p6=new Panel();
p7=new Panel();p8=new Panel();p9=new Panel();
tf1=new TextField(20);tf2=new TextField(20);tf3=new TextField(20);tf4=new TextField(20);
tf5=new TextField(20);tf6=new TextField(20);tf7=new TextField(20);tf8=new TextField(20);

p1.add(l1);p1.add(tf1);
p2.add(l2);p2.add(tf2);
p3.add(l3);p3.add(tf3);
p4.add(l4);p4.add(tf4);
p5.add(l5);p5.add(tf5);
p6.add(l6);p6.add(tf6);
p7.add(l7);p7.add(tf7);
p9.add(b3);p9.add(b2);p9.add(b1);
p8.add(l8);p8.add(tf8);
f.add(p1);f.add(p2);f.add(p3);f.add(p4);f.add(p5);f.add(p6);f.add(p7);f.add(p8);f.add(p9);

f.setSize(w,h);
f.setLocationRelativeTo(null);

f.setResizable(true);
f.setVisible(true);
}
/*
public static void main(String args[])
{
new Signup();
}*/
}