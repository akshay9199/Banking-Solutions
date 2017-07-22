package banking;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
 public class Login 
{
Label l3;
Label l4;
TextField tf1,tf2;
Button b1,b2,b3,b4;
Panel p3,p4,p5;
JFrame f;
Login(int w,int h)
{
f=new JFrame("Homepage");
f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
f.setLayout(new FlowLayout());




Toolkit tk = Toolkit.getDefaultToolkit();
Dimension screenSize = tk.getScreenSize();
//final int w = screenSize.width;
//final int h = screenSize.height;
f.setSize(w ,h );
f.setLocationRelativeTo(null);
l3=new Label("Name:");
l4=new Label("Account No.:");
b1=new Button("Cancel");
b2=new Button("Signup");
b3=new Button("Login");
b4=new Button("Back");
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
		
	}	
});
b2.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new Signup(w,h);
	}
});
b3.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		String name=tf1.getText();
		int AccNo=Integer.parseInt(tf2.getText());
		Connection con;
       try
       {
		Class.forName("com.mysql.jdbc.Driver");
		try
		{
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","akshay", "password");
			Statement st=con.createStatement();
			String sql="SELECT * from Details";
			ResultSet res=st.executeQuery(sql);
			int flag=0;
	          while(res.next())
		 {
               String Dname=res.getString(1);
               int Daccno=res.getInt(2);
	        	if(Dname.equalsIgnoreCase(name)&&( Daccno==AccNo))
	        	{
	        		flag=1;
	        		break;
	        	}
		}
	     if(flag==1)
	     {    con.close();st.close();
	          new Action(name,w,h);
	          f.dispose();
	     }
	     else
	     {	int dtt=JOptionPane.WARNING_MESSAGE;
	         String dt="Invalid Login",dm="Invalid Login !";
	    	 JOptionPane.showMessageDialog((Component)null,dm,dt,dtt);
	     }
		}catch(Exception e)
	{
		System.out.println(e);
	}
	}catch(ClassNotFoundException e)
	{
		System.out.println(e);
	}
	}
});
b4.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new Banking();	}
});
p3=new Panel();p4=new Panel();p5=new Panel();
tf1=new TextField(10);tf2=new TextField(10);
p3.add(l3);p3.add(tf1);
p4.add(l4);p4.add(tf2);
p5.add(b1);p5.add(b2);p5.add(b3);p5.add(b4);
f.add(p3);f.add(p4);f.add(p5);
f.setResizable(true);
//f.setSize(300,300);
f.setVisible(true);
}
}