package banking;
import java.awt.*;

import javax.swing.*;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
class CashWithdrawl  
{
Label l1,l2,l3,l4,l5,l6;
TextField tf1,tf2,tf3,tf4;
Button b1,b2;
Panel p1,p2,p3,p4,p5,p6;
final JFrame f;
CashWithdrawl(String cname,int w,int h)
{
	f=new JFrame("Cash Withdrawl Counter");
f.setLayout(new GridLayout(6,2));
f.setSize(w ,h );
f.setLocationRelativeTo(null);
f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
l1=new Label("Name:");
l2=new Label("Account No.:");
l3=new Label(" Withdrawl Amount:");
l4=new Label(" Remark:");
l5=new Label(" Press yes to continue");
l6=new Label(" ");
//l5=new Label("Press yes to withdrawl");
b1=new Button("No");
b2=new Button("Yes");
b1.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new Action(cname,w,h);
	}
});
b2.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		String name=tf1.getText();
		int AccNo=Integer.parseInt(tf2.getText());
	    try
	       {
			Class.forName("com.mysql.jdbc.Driver");
			try
			{
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","akshay", "password");
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
		    	 
		         String sql2 = "UPDATE Details SET Balance = Balance - ? Where Ac_Holder_name=?";
		    	 int b=Integer.parseInt(tf3.getText());
		    	 PreparedStatement statement = (PreparedStatement)con.prepareStatement(sql2);
		    	 statement.setInt(1,b);
		    	 statement.setString(2,cname);
		    	 //statement.setString(2, "William Henry Bill Gates");
		    	 //statement.setString(3, "bill.gates@microsoft.com");
		    	 //statement.setString(4, "bill");
		    	  
		    	 int rowsUpdated = statement.executeUpdate();
		    	 if (rowsUpdated > 0) {
		    		 
		    		 String dialogtitle="Amount Withdrwal";
		    	 		String dialogmessage="Amount withdrwal Successfully! ";
		    	 		int dialogtype = JOptionPane.PLAIN_MESSAGE;
		    	 		JOptionPane.showMessageDialog((Component)null,dialogmessage,dialogtitle,dialogtype);
		    	 	con.close();
		               st.close(); 
		    	 		new AccountBalance(cname,w,h);
		    			f.dispose();
		    			
		    	 }
		    	 
		     //   new AccountBalance(cname);
		       //   f.dispose();
		         // new Action(cname);
		     
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
						new Action(cname,w,h);
					}
		     }
			}
			}  catch(Exception e)
		{
			System.out.println(e);
		}
		}catch(ClassNotFoundException e)
		{
			System.out.println(e);
		}
	   
		}
	});	
		
		
p1=new Panel();p2=new Panel();p3=new Panel();p4=new Panel();p5=new Panel();p6=new Panel();
tf1=new TextField(10);tf2=new TextField(10);tf3=new TextField(10);tf4=new TextField(10);
p1.add(l1);p1.add(tf1);
p2.add(l2);p2.add(tf2);
p3.add(l3);p3.add(tf3);
p4.add(l4);p4.add(tf4);
p5.add(l5);p5.add(l6);
p6.add(b1);p6.add(b2);
f.add(p1);f.add(p2);f.add(p3);f.add(p4);
f.add(p5);f.add(p6);
f.setResizable(true);

f.setVisible(true);
}
}