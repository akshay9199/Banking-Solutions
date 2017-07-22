package banking;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
class Action  
{
Label l1;
final JFrame f;
//Label l2;
Button b1,b2,b3,b4,b5;
//Panel p1;
Action(String aname,int w,int h)
{
	//String aname=name;
f=new JFrame("Select Option");
f.setLayout(new FlowLayout());
f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension screenSize = tk.getScreenSize();
//final int w = screenSize.width;
//final int h = screenSize.height;
f.setSize(w ,h );
f.setLocationRelativeTo(null);


l1=new Label("Select action mode");
//l2=new Label("To continue select option");
b1=new Button("Cash Transfer");
b2=new Button("Deposit");
b3=new Button("Cash Withdrawl");
b4=new Button("Account Balance");
b5=new Button("Back");
b1.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new CashTransfer( aname,w,h);
	}
});
b2.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new Deposit(aname,w,h);
			}
});
b3.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new CashWithdrawl(aname,w,h);
	}
});
b4.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new AccountBalance(aname,w,h);
	}
});

b5.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
		f.dispose();
		new Login(w,h);
	}
});

f.add(l1);
//add(l2);
//p1=new Panel();
//add(l1);
f.add(b1);
f.add(b2);
f.add(b3);
f.add(b4);
f.add(b5);
f.setResizable(true);
f.setVisible(true);
}
/*
public static void main(String args[])
{
new Action(name);
}*/
}
