package banking;
import java.awt.*;

import javax.swing.*;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
class CashTransfer
{
Label l1,l2,l3,l4,l5;
TextField tf1,tf2,tf3,tf4,tf5;
Button b1,b2,b3;
final JFrame f;
Panel p1,p2,p3,p4,p5,p6;
CashTransfer(String ctname,int w,int h)
{
	f=new JFrame("Cash Transfer Counter");
f.setLayout(new FlowLayout());
f.setSize(w ,h );
f.setLocationRelativeTo(null);
f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

p1=new Panel();p2=new Panel();p3=new Panel();p4=new Panel();p5=new Panel();p6=new Panel();
tf1=new TextField(10);tf2=new TextField(10);tf3=new TextField(10);tf4=new TextField(10);
tf5=new TextField(10);

l1=new Label("Enter Account No.:");
l2=new Label(" Enter A/C Holder Name.:");
l3=new Label("Enter Beneficiary Account Number");
l4=new Label("Enter Beneficiary Name");
l5=new Label("Enter Amount to transfer");
//l5=new Label(" Cash Transfered Succesfully");
//l5=new Label(" Account  has Insufficent balance to transfer ");
b1=new Button("Back");
b2=new Button("Cancel");b3=new Button("Transfer");
b1.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new Action(ctname,w,h);
	}
});
b2.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		int result=JOptionPane.showConfirmDialog((Component)null,"Are you sure you want to Cancel the Transaction!","Cancel Transaction",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION)
		{	
 		f.dispose();
		}
		else
		{
			
		}
	}
});
b3.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		int AccNo=Integer.parseInt(tf1.getText());
		String name=tf2.getText();
		
		Connection con=null;
       try
       {
		Class.forName("com.mysql.jdbc.Driver");
		try
		{
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","akshay", "password");
			Statement st=con.createStatement();
			//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//	System.out.println("Database name");
			//String database=br.readLine();
			String sql="SELECT * from Details";
			//st.executeUpdate("insert into Details Values(tf1.getText(),tf2.getText(),tf3.getText(),tf4.getText(),tf5.getText(),tf6.getText(),tf7.getText(),tf8.getText())"); 
			ResultSet res=st.executeQuery(sql);
			int flag=0;
			//ResultSetMetaData rsmd =res.getMetaData();
			//System.out.println(rsmd.getColumnName(1)+"  "+rsmd.getColumnName(2)+" "+rsmd.getColumnName(3));
	          while(res.next())
		 {
               //String Dname=res.getString(1);
               int Daccno=res.getInt(2);
               String Dname=res.getString(1);
	        if(( Daccno==AccNo)&& Dname.equalsIgnoreCase(name))
	        	{
	        		flag=1;
	        		break;
	        	}
	        	
	        	  //System.out.println(res.getString(1)+"  "+res.getInt(2)+" "+res.getString(3));
			//}
		}
	     if(flag==1)
	     {

	 		int AccNo1=Integer.parseInt(tf3.getText());
	 		String name1=tf4.getText();
	    	 while(res.next())
			 {
	               //String Dname=res.getString(1);
	               int Daccno=res.getInt(2);
	               String Dname=res.getString(1);
		        if(( Daccno==AccNo1)&& Dname.equalsIgnoreCase(name1))
		        	{
		        		flag=0;
		        		break;
		        	}
		        else
		   	     {	int dtt=JOptionPane.WARNING_MESSAGE;
		   	         String dt="Invalid Details",dm=" A/c Holder name and Account Number are not matching !";
		   	    	 JOptionPane.showMessageDialog((Component)null,dm,dt,dtt);
		   	     }

			 }
	     }
	     if(flag==0)
	     {      	
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","akshay", "password");
	    	 String sql2 = "UPDATE Details SET Balance = Balance - ? Where Ac_Holder_name = ?";
	    	 int b=Integer.parseInt(tf5.getText());
	    	 PreparedStatement statement = (PreparedStatement)con.prepareStatement(sql2);
	    	 statement.setInt(1,b);
	    	 statement.setString(2,tf2.getText());
	    	 int rowsUpdated = statement.executeUpdate();
             if(rowsUpdated>0)
            	 
             {
	    	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","akshay", "password");
	    	 String nm=tf4.getText();
	    	  String sql3 = "UPDATE Details SET Balance = Balance + ? Where Ac_Holder_name = ?";
		    	  int b1=Integer.parseInt(tf5.getText());
		    	 PreparedStatement statement1 = (PreparedStatement)con.prepareStatement(sql3);
		    	 statement1.setInt(1,b1);
		    	 statement1.setString(2,nm);
		    	int  rowsUpdated1 = statement1.executeUpdate();
                  if(rowsUpdated1>0)
                  {
		    	        con.close();
		    	            statement1.close();
		    	 
		    		 String dialogtitle="Amount Transfered";
		    	 		String dialogmessage="Amount Transfered to  Beneficiary account Successfully! ";
		    	 		int dialogtype = JOptionPane.PLAIN_MESSAGE;
		    	 		JOptionPane.showMessageDialog((Component)null,dialogmessage,dialogtitle,dialogtype);
		    	 		f.dispose();
		    	 		//con.close();
		    	 		//st.close();
		    			new AccountBalance(tf2.getText(),w,h);
            //new AccountBalance(ctname);
	       //f.dispose();
	                }
	     else
	     {	int dtt=JOptionPane.WARNING_MESSAGE;
	         String dt="Invalid Details",dm=" A/c Holder name and Account Number are not matching !";
	    	 JOptionPane.showMessageDialog((Component)null,dm,dt,dtt);
	     }
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
	
		
	}
});

p1.add(l1);p1.add(tf1);
p2.add(l2);p2.add(tf2);
p3.add(l3);p3.add(tf3);
p4.add(l4);p4.add(tf4);
p5.add(l5);p5.add(tf5);
p6.add(b1);p6.add(b2);p6.add(b3);
f.add(p1);f.add(p2);f.add(p3);f.add(p4);f.add(p5);f.add(p6);
f.setResizable(true);
f.setVisible(true);
}
}