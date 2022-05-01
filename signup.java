import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border; 
import java.sql.*;
import java.io.*;

class signup
{
    	JFrame fr;
    	JLabel l_user,l_pass,l_operator_num,lclose;
    	JTextField f_operator_name,f_operator_num;
    	JPasswordField f_operator_pass;
    	String operator_name,operator_password,operator_num;                       // variables having value entered by user of username and password
    	JButton submit,reset;
	JCheckBox cb;
    
	public signup()
	{
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        fr=new JFrame("Signup");
        fr.setBounds((dim.width-504)/2,(dim.height-300)/2,504,300);
	fr.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
        fr.setContentPane(new JLabel(new ImageIcon("images/signup.png"))); //adding image to frame background
        fr.setLayout(null);
        fr.setResizable(false);
        Border border = BorderFactory.createLineBorder(Color.red);

	
	lclose = new JLabel(new ImageIcon("images/close1.png"));
	lclose.setBounds(470,5,32,32);
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
			fr.dispose();
		}
		
	});

    	f_operator_name = new JTextField();              //textfield for entering username
	f_operator_name.setBounds(180,70,250,30);
	f_operator_name.setFont(new Font("verdana",Font.BOLD,20));
	f_operator_name.setForeground(Color.BLUE);
   	f_operator_name.setOpaque(false);
    	f_operator_name.setCaretColor(Color.BLACK);
    	f_operator_name.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));
	fr.add(f_operator_name);
		
    	l_user= new JLabel("Username");
	l_user.setBounds(60,70,130,30);
	l_user.setFont(new Font("Verdana",Font.BOLD,18));
	l_user.setForeground(Color.BLACK);
    	l_user.setOpaque(false);
	fr.add(l_user);

	l_pass= new JLabel("Password");
	l_pass.setBounds(60,110,130,30);
	l_pass.setFont(new Font("Verdana",Font.BOLD,18));
	l_pass.setForeground(Color.BLACK);
    	l_pass.setOpaque(false);
    	fr.add(l_pass);

	cb = new JCheckBox(" as Admin");
	cb.setBounds(60,210,130,30);
	cb.setFont(new Font("Verdana",Font.BOLD,15));
	cb.setForeground(Color.blue);
    	cb.setOpaque(false);
    	fr.add(cb);

    	f_operator_pass = new JPasswordField();            //textfield to enter password
    	f_operator_pass.setBounds(180,110,250,30);
    	f_operator_pass.setFont(new Font("verdana",Font.BOLD,20));
    	f_operator_pass.setForeground(Color.BLUE);
    	f_operator_pass.setOpaque(false);
    	f_operator_pass.setCaretColor(Color.BLACK);
    	f_operator_pass.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));
    	fr.add(f_operator_pass);
  
    	submit=new JButton("SUBMIT");
    	submit.setBounds(330,210,100,30);
    	submit.setForeground(Color.red);	
    	fr.add(submit);
    	submit.setFont(new Font("Verdana",Font.BOLD,14));
	submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	submit.addActionListener(new ActionListener()
	{
            
        	public void actionPerformed(ActionEvent me)
		{
            		operator_name=f_operator_name.getText().trim();
            		operator_password=f_operator_pass.getText().trim();
            		operator_num=f_operator_num.getText().trim();
            
            		if(operator_name.length()==0)
			{//if user name is empty
                		JOptionPane.showMessageDialog(fr,"Please enter User Name");
                   		f_operator_name.requestFocus();
             		}	
			else if(operator_password.length()==0)
			{ //if password is entered
                    		JOptionPane.showMessageDialog(fr,"Please enter Password");
                    		f_operator_pass.requestFocus();
                	}
            		else if(operator_num.length()==0 || operator_num.equals("only digits"))
			{//if id number is empty
                 		JOptionPane.showMessageDialog(fr,"Please enter Operator Number");
                 		f_operator_num.requestFocus();
             		}
                       	else
			{
                                          
                     		//no field is empty                     
                         	try
				{
					if(cb.isSelected()==true)
					{
						Connection con = dao.connect();
                               			PreparedStatement ps=con.prepareStatement("select * from user where role='Admin'"); 
                               			ResultSet rs = ps.executeQuery();
						if(rs.next()==true)
						{
							JOptionPane.showMessageDialog(fr,"Sorry! Admin Already Exists");
                                                }
						else
						{
							PreparedStatement ps1=con.prepareStatement("insert into user values(?,?,?,?)"); 
                               				ps1.setString(1,operator_num);
                               				ps1.setString(2,operator_name);
                               				ps1.setString(3,operator_password);
							ps1.setString(4,"Admin");
                               				int z = ps1.executeUpdate();
                                        		if(z>0)
                                			{
                                       				JOptionPane.showMessageDialog(fr,"Signup Successfull !");
                                                        
                               				}
                               				else
                               				{
                                     				JOptionPane.showMessageDialog(fr,"Oops Something Went Wrong!");
                               				}
						}
					}  
					else
					{ 
                            			Connection con = dao.connect();
                               			PreparedStatement ps=con.prepareStatement("insert into user values(?,?,?,?)"); 
                               			ps.setString(1,operator_num);
                               			ps.setString(2,operator_name);
                               			ps.setString(3,operator_password);
						ps.setString(4,"Operator");
                               			int z = ps.executeUpdate();
                                        	if(z>0)
                                		{
                                       			JOptionPane.showMessageDialog(fr,"Signup Successfull !");
                                                        
                               			}
                               			else
                               			{
                                     			JOptionPane.showMessageDialog(fr,"Oops Something Went Wrong!");
                               			}
                                		con.close();
					}                
                            	}
                            	catch(Exception e)
                            	{
                                	JOptionPane.showMessageDialog(fr,"Operator ID already exists...");
                            	}            
                                         
                	}

            	}
        });

	reset = new JButton("RESET");
    	reset.setBounds(220,210,100,30);
    	reset.setForeground(Color.red);	
	reset.setFont(new Font("verdana",Font.BOLD,14));
	fr.add(reset );
	reset.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			f_operator_num.setText("only digits");
			f_operator_num.setFont(new Font("verdana",Font.PLAIN,12));
			f_operator_num.setForeground(Color.black);
			f_operator_pass.setText("");
			f_operator_name.setText("");
		}
	});
       
        l_operator_num= new JLabel("Operator Number");
		l_operator_num.setBounds(60,150,200,30);
		l_operator_num.setFont(new Font("Verdana",Font.BOLD,18));
		l_operator_num.setForeground(Color.BLACK);
        l_operator_num.setOpaque(false);
		fr.add(l_operator_num);

        f_operator_num= new JTextField("only digits");              //textfield for entering admission number
		f_operator_num.setBounds(260,150,170,30);
		f_operator_num.setFont(new Font("verdana",Font.PLAIN,12));
		f_operator_num.setForeground(Color.black);
        f_operator_num.setOpaque(false);
        f_operator_num.setCaretColor(Color.BLACK);
        f_operator_num.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));
		fr.add(f_operator_num);

	f_operator_num.addKeyListener(new KeyAdapter()
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

	f_operator_num.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent ke)
		{
			f_operator_num.setText("");
			f_operator_num.setFont(new Font("verdana",Font.PLAIN,18));
			f_operator_num.setForeground(Color.blue);
		}
	});
        
	fr.setUndecorated(true);
        fr.setVisible(true);
    }
    public static void main(String args[]){
        new signup();
    }
}