package banking;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Banking
{
     JFrame f;
	Label l1,l2;
	JLabel l3;
	Button b1,b2,b3;
	Panel p1;
	//Image bgimage;
	Banking()
	{
		
		
	 f=new JFrame("Welcome to A J Banking Solutions");
	 //l3=new JLabel(new ImageIcon("C:\\Project\\images.jpg"));
	// Image bgimage = Toolkit.getDefaultToolkit().getImage("a.jpg");
	   
	     
	    Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		final int w = screenSize.width;
		final int h = screenSize.height;
		f.setSize(w ,h );
		f.setLocationRelativeTo(null);
		
	f.setLayout(new BorderLayout());
	l1=new Label("Welocme to the new way of Banking");
	l2=new Label("To continue select option");
	b1=new Button("Exit");
	b2=new Button("Signup");
	b3=new Button("Login");
	
	f.add(l3);
	f.setLayout(new FlowLayout());
	f.add(l1);
	f.add(l2);
	f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	p1=new Panel();
	p1.add(b1);
	p1.add(b2);
	p1.add(b3);
	f.add(p1);
	f.setVisible(true);
	b1.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent ae)
		{
			int result=JOptionPane.showConfirmDialog((Component)null,"Are you sure you want to Quit!","Exit",JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION)
			{	
	 		f.dispose();
			}
			else
			{
				
			}
		}
	});
	b2.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent ae)
		{
			new Signup(w,h);
			f.dispose();
		}
	});
	b3.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent ae)
		{
			new Login(w,h);
			f.dispose();
		}
	});
	}
	public static void main(String args[])
	{
	new Banking();
	}
	}
