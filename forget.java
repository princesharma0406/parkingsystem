import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border; 
import java.sql.*;
import java.io.*;

class forget{
    JFrame fr;
    JLabel l_user,l_operator_num,lclose;
    JTextField f_operator_name,f_operator_num;
    String operator_name,operator_password,operator_num;                       // variables having value entered by user of username and password
    JButton submit;
    public forget(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        fr=new JFrame("Password Recovery");
        fr.setBounds((dim.width-504)/2,(dim.height-300)/2,504,300);
	    fr.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
        fr.setContentPane(new JLabel(new ImageIcon("images/forget.png"))); //adding image to frame background
        fr.setLayout(null);
        fr.setResizable(false);
        Border border = BorderFactory.createLineBorder(Color.red);

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
			fr.dispose();
		}
		
	});

    f_operator_name = new JTextField();              //textfield for entering username
	f_operator_name.setBounds(190,80,250,30);
	f_operator_name.setFont(new Font("verdana",Font.BOLD,20));
	f_operator_name.setForeground(Color.BLUE);
    f_operator_name.setOpaque(false);
    f_operator_name.setCaretColor(Color.BLACK);
    f_operator_name.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));
	fr.add(f_operator_name);
		
    l_user= new JLabel("User Name");
	l_user.setBounds(60,80,130,30);
	l_user.setFont(new Font("Verdana",Font.BOLD,18));
	l_user.setForeground(Color.BLACK);
    l_user.setOpaque(false);
	fr.add(l_user);
  
    submit=new JButton("SUBMIT");
    submit.setBounds(330,170,100,30);
    submit.setForeground(Color.red);
    fr.add(submit);
    submit.setFont(new Font("Verdana",Font.BOLD,14));
	submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    submit.addActionListener(new ActionListener(){
            
        public void actionPerformed(ActionEvent me){
            operator_name=f_operator_name.getText().trim();
            operator_num=f_operator_num.getText().trim();
            
            if(operator_name.length()==0){//if user name is empty
                JOptionPane.showMessageDialog(fr,"Please Enter User Name");
                   f_operator_name.requestFocus();
             }
            else if(operator_num.length()==0){//if admission number is empty
                 JOptionPane.showMessageDialog(fr,"Please enter Operator Number");
                 f_operator_num.requestFocus();
             }
                
                else{
                                          
                     //no field is empty                     
                         try{   
                            Connection con =dao.connect();

                               PreparedStatement ps=con.prepareStatement("select * from user where username=? and id=?"); 
                               ps.setString(1,operator_name);
                               ps.setString(2,operator_num);
                               ResultSet rs = ps.executeQuery();
                                            
                                if(rs.next())
                                {
                                    
                                    JOptionPane.showMessageDialog(fr,"Password :\n"+ rs.getString("password"));
                                    f_operator_name.setText("");
                                    f_operator_num.setText("");
                               }
                               else
                               {
                                     JOptionPane.showMessageDialog(fr,"Invalid Username or Password !");
                               }
                                con.close();                
                            }
                            catch(Exception e)
                            {
                                JOptionPane.showMessageDialog(fr,"Oops! Something went wrong .");
                            }            
                    
                                
                }

            }
        });

       
        l_operator_num= new JLabel("Operator Number");
		l_operator_num.setBounds(60,120,200,30);
		l_operator_num.setFont(new Font("Verdana",Font.BOLD,18));
		l_operator_num.setForeground(Color.BLACK);
        l_operator_num.setOpaque(false);
		fr.add(l_operator_num);

        f_operator_num= new JTextField("only digits");              //textfield for entering admission number
		f_operator_num.setBounds(270,120,170,30);
		f_operator_num.setFont(new Font("verdana",Font.BOLD,12));
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
        new forget();
    }
}