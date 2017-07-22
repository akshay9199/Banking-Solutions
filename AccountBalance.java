package banking;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
class AccountBalance  
{
Label l1,l2,l3;
 final JFrame f;
Button b1,b2;
Panel p1;
AccountBalance( String abname,int w,int h)
{
	f=new JFrame("Account Balance");
f.setLayout(new GridLayout(4,1));


//Toolkit tk = Toolkit.getDefaultToolkit();
f.setSize(w ,h );
f.setLocationRelativeTo(null);
//f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
if(abname.equalsIgnoreCase(result.getString(1)))
{	f=0;
break;
}
}
if(f==0){
	l1=new Label(" A/C Holder Name:"+ result.getString(1));
     l2=new Label("Account No.:"+ result.getInt(2));
     l3=new Label(" Standing Balance-"+result.getInt(10));
//l4=new Label(" Remark:");
//l5=new Label("Press yes to withdrawl");
con.close();
statement.close();
}
else
{}
}catch(Exception e)
{
}
b1=new Button("Back");
b2=new Button("CLose");
p1=new Panel();
b1.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new Action(abname,w,h);
	}
});
b2.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		int result=JOptionPane.showConfirmDialog((Component)null,"Are you sure you want to Close!","Close",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION)
		{	
 		f.dispose();
		}
		else
		{
			
		}
	}
});
f.add(l1);f.add(l2);f.add(l3);
p1.add(b1);p1.add(b2);
f.add(p1);
f.setResizable(true);
f.setVisible(true);
}
}
