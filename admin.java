import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class admin
{
	JFrame fr;
	JLabel l_forget,l_signup,l_user,l_pass,lclose,ladmin;
	JTextField f_user;
 	JPasswordField f_pass;
	JCheckBox cb;
	JButton login,reset;
	
	public admin()
	{
	
	Border border = BorderFactory.createLineBorder(Color.gray);
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	
	
	fr = new JFrame(" Admin Login");
	fr.setContentPane(new JLabel(new ImageIcon("images/admin.png")));
	fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fr.setBounds((dim.width-504)/2,(dim.height-300)/2,504,300);
	fr.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
	fr.setResizable(false);
	fr.setLayout(null);	

	lclose = new JLabel(new ImageIcon("images/close1.png"));
		lclose.setBounds(468,7,32,32);
		lclose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(lclose);
		lclose.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lclose.setIcon(new ImageIcon("images/close2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				lclose.setIcon(new ImageIcon("images/close1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				System.exit(0);
			}
		
		});

	ladmin=new JLabel("<html><u>Operator Login</u></html>");
	ladmin.setBounds(50,175,100,20);
	ladmin.setForeground(Color.pink);
	ladmin.setFont(new Font("verdana",Font.BOLD,12));
	ladmin.setToolTipText("Operator Login");
	ladmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	fr.add(ladmin);
	ladmin.addMouseListener(new MouseAdapter(){
		public void mouseEntered(MouseEvent me){
			ladmin.setForeground(Color.MAGENTA);
		}
		public void mouseExited(MouseEvent me){
			ladmin.setForeground(Color.pink);
		}
		public void mouseClicked(MouseEvent me)
		{
			new login();
		}
	});
	
	l_signup=new JLabel("<html><u>New SignUp</u></html>");
	l_signup.setBounds(50,200,85,20);
	l_signup.setForeground(Color.pink);
	l_signup.setFont(new Font("verdana",Font.BOLD,12));
	l_signup.setToolTipText("Register Yourself!");
	l_signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	fr.add(l_signup);
	l_signup.addMouseListener(new MouseAdapter(){
		public void mouseEntered(MouseEvent me){
			l_signup.setForeground(Color.MAGENTA);
		}
		public void mouseExited(MouseEvent me){
			l_signup.setForeground(Color.pink);
		}
		public void mouseClicked(MouseEvent me){
				new signup();
		}
	});
		
	l_forget=new JLabel("<html><u>Forgot Password ?</u></html>");
	l_forget.setBounds(50,225,125,20);
	l_forget.setForeground(Color.pink);
	l_forget.setToolTipText("Recover your password");
	l_forget.setFont(new Font("verdana",Font.BOLD,12));
	l_forget.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	fr.add(l_forget);
	l_forget.addMouseListener(new MouseAdapter(){
		public void mouseEntered(MouseEvent me){
			l_forget.setForeground(Color.MAGENTA);
		}
		public void mouseExited(MouseEvent me){
			l_forget.setForeground(Color.pink);
		}
		public void mouseClicked(MouseEvent me){
					new forget();
		}
	});			
		
	l_user=new JLabel("Enter Admin ID");
	l_user.setBounds(50,80,180,30);
	l_user.setFont(new Font("Georgia",Font.BOLD,18));
	l_user.setForeground(Color.cyan);
	fr.add(l_user);
			
	f_user=new JTextField("only digits");
	f_user.setBounds(250,80,200,30);
	f_user.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));
	f_user.setFont(new Font("verdana",Font.PLAIN,12));
	f_user.setOpaque(false);
	f_user.setCaretColor(Color.green);
	f_user.setForeground(Color.gray);
	fr.add(f_user);	
	f_user.addKeyListener(new KeyAdapter()
	{
		public void keyTyped(KeyEvent ke)
		{
			char ch = ke.getKeyChar();
			if(ch<'0' || ch>'9')
			{
				ke.consume();
			}
		}
	});

	f_user.addFocusListener(new FocusAdapter()
	{
		public void focusGained(FocusEvent ke)
		{
			if(f_user.getText().equals("only digits"))
			{
				f_user.setText("");
				f_user.setFont(new Font("verdana",Font.PLAIN,18));
				f_user.setForeground(Color.green);
			}
		}
		public void focusLost(FocusEvent ke)
		{
			if(f_user.getText().length()==0)
			{
				f_user.setText("only digits");
				f_user.setFont(new Font("verdana",Font.PLAIN,12));
				f_user.setForeground(Color.gray);
			}
			else
			{
				f_user.setFont(new Font("verdana",Font.PLAIN,18));
				f_user.setForeground(Color.green);
			}
		}
	});	
	
	l_pass=new JLabel("Enter Password");
	l_pass.setBounds(50,120,180,30);
	l_pass.setFont(new Font("Georgia",Font.BOLD,18));
	l_pass.setForeground(Color.cyan);
	fr.add(l_pass);
		
	f_pass=new JPasswordField();
	f_pass.setBounds(250,120,200,30);
	f_pass.setEchoChar('*');
	f_pass.setFont(new Font("Georgia",Font.PLAIN,20));
	f_pass.setCaretColor(Color.green);
	f_pass.setForeground(Color.green);
	f_pass.setOpaque(false);
	f_pass.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));
	fr.add(f_pass);
			
	cb=new JCheckBox("Show Password");
	cb.setBounds(330,165,140,20);
	cb.setFont(new Font("Georgia",Font.BOLD,12));
	cb.setOpaque(false);
	fr.add(cb);
	cb.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			if(cb.isSelected()){
				f_pass.setEchoChar((char)0);
			}
			else{
				f_pass.setEchoChar('*');
			}
		}
	});

	reset = new JButton("Reset");
	reset.setBounds(250,210,90,30);
	reset.setForeground(Color.blue);	
	reset.setFont(new Font("verdana",Font.PLAIN,17));
	fr.add(reset );
	reset.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			f_user.setText("only digits");
			f_user.setFont(new Font("verdana",Font.PLAIN,12));
			f_user.setForeground(Color.gray);
			f_pass.setText("");
		}
	});

	login = new JButton("Login");
	login.setBounds(360,210,90,30);
	login.setForeground(Color.blue);	
	login.setFont(new Font("verdana",Font.PLAIN,17));
	fr.add(login);
	login.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			String un=f_user.getText().trim();
			String pass=f_pass.getText().trim();
			if(un.length()==0 || un.equals("only digits"))
			{
				JOptionPane.showMessageDialog(fr,"Please Enter Admin ID");
        			f_user.requestFocus();
			}
			else if(pass.length()==0)
			{
				JOptionPane.showMessageDialog(fr,"Please Enter Password");
            			f_pass.requestFocus();
			}
			else
			{
				try
				{
					Connection con =dao.connect();

                    			PreparedStatement ps=con.prepareStatement("select * from user where id=? and password=?"); 
                    			ps.setString(1,un);
                    			ps.setString(2,pass);
                    
                    			ResultSet rs= ps.executeQuery();
                                            
                    			if(rs.next())
					{
						JOptionPane.showMessageDialog(fr,"Welcome Admin : "+rs.getString("username"));
						fr.dispose();
						new display_record();                               
                    			}
					else
					{
						JOptionPane.showMessageDialog(fr,"Invalid Admin ID or Password !");
                    			}
                    			con.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(fr,"Oops ! Something went wrong .");
				}
			}
		}
	});
	
	fr.setUndecorated(true);	
	
	fr.setVisible(true);

	fr.addWindowListener( new WindowAdapter() 
        { 
            	public void windowOpened( WindowEvent e ) 
                { 
                	login.requestFocus(); 
         	} 
         }); 

	}
}