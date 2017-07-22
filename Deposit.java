package banking;
import java.awt.*;

import javax.swing.*;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
class Deposit  
{
Label l1,l2,l3,l4;
TextField tf1,tf2,tf3;
Button b1,b2,b3;
Panel p1,p2,p3,p4;
final JFrame f;
Deposit( String dname,int w,int h)
{
	f=new JFrame("Cash Deposit");
f.setLayout(new GridLayout(4,1));
f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
l1=new Label("Name:");
l2=new Label("  Enter your Account No.:");
l3=new Label("Enter Deposit Amount:");
l4=new Label("New Balance is");
b1=new Button("Back");
b2=new Button("Cancel");
b3=new Button("Add");
b1.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae){
	f.dispose();new Action(dname,w,h);
	}
});
b2.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae){
		int result=JOptionPane.showConfirmDialog((Component)null,"Cancel Deposit Prcess!","Cancel",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION)
		{	
 		f.dispose();
		}
		else
		{
			
		}
		
		
		//f.dispose();
	//new Detail();


	}
});
b3.addActionListener(new ActionListener(){

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
			
			String sql="SELECT * from Details ";
			//st.executeUpdate("insert into Details Values(tf1.getText(),tf2.getText(),tf3.getText(),tf4.getText(),tf5.getText(),tf6.getText(),tf7.getText(),tf8.getText())"); 
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
	        	
	        	  //System.out.println(res.getString(1)+"  "+res.getInt(2)+" "+res.getString(3));
			//}
		}
	         
	     if(flag==1)
	     {
	    	 
	         String sql2 = "UPDATE Details SET Balance=Balance + ? Where Ac_Holder_name= ?";
	    	 int b=Integer.parseInt(tf3.getText());
	    	 PreparedStatement statement = (PreparedStatement)con.prepareStatement(sql2);
	    	 statement.setInt(1,b);
	    	 statement.setString(2,dname);
	    	 //statement.setString(2, "William Henry Bill Gates");
	    	 //statement.setString(3, "bill.gates@microsoft.com");
	    	 //statement.setString(4, "bill");
	    	  
	    	 int rowsUpdated = statement.executeUpdate();
	    	 if (rowsUpdated > 0) {
	    		 
	    		 String dialogtitle="Amount Deposit";
	    	 		String dialogmessage="Amount deposited to account Successfully! ";
	    	 		int dialogtype = JOptionPane.PLAIN_MESSAGE;
	    	 		JOptionPane.showMessageDialog((Component)null,dialogmessage,dialogtitle,dialogtype);
	    	 }
	    	 
	         // new AccountBalance();
	          f.dispose();
	          new Action(dname,w,h);
	     }
	     else
	     {	
	    	 int result=JOptionPane.showConfirmDialog((Component)null,"Details are not matching Press yes to try again!","Error",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION)
				{	
		 	     //f.dispose();
		 	    // new Login();
		 	     //f.dispose();
				}
				else
				{
					new Login(w,h);
				}
	     }
		}catch(Exception e)
	{
		System.out.println(e);
	}
	}catch(ClassNotFoundException e)
	{
		System.out.println(e);
	}
      // new AccountBalance();
		//f.dispose();
	}
});
p1=new Panel();p2=new Panel();p3=new Panel();p4=new Panel();
tf1=new TextField(10);tf2=new TextField(10);tf3=new TextField(10);
p1.add(l1);p1.add(tf1);
p2.add(l2);p2.add(tf2);
p3.add(l3);p3.add(tf3);
//p4.add(l5);
p4.add(b1);p4.add(b2);p4.add(b3);
f.add(p1);f.add(p2);f.add(p3);f.add(p4);
f.setSize(w ,h );
f.setLocationRelativeTo(null);
f.setResizable(true);
//f.setSize(400,400);
f.setVisible(true);
}
/*
public static void main(String args[])
{
new Deposit();
}
*/
}