import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.io.*;
import java.util.UUID;
import java.util.regex.*;
import java.sql.*;
import javax.swing.text.MaskFormatter;

class onebyone
{
	JFrame fr,fn;
	JLabel lexit,lnext,lprev,lfirst,llast,lb1,lsrch,lreset,ldel;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9;
	ButtonGroup bg;
	JComboBox c1,c2,c3;
	JFormattedTextField f_num; 
	int p=0;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public onebyone(JFrame f)
	{
		fn = f;

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		fr = new JFrame("Insert New Record");
		fr.setBounds((dim.width-1000)/2,(dim.height-600)/2,1000,600);
		fr.setContentPane(new JLabel(new ImageIcon("images/onebyone.png")));
		fr.setLayout(null);

		lexit = new JLabel(new ImageIcon("images/exit1.png"));
		lexit.setBounds(950,20,32,32);
		lexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lexit.setToolTipText("Exit");
		fr.add(lexit);
		lexit.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lexit.setIcon(new ImageIcon("images/exit2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				lexit.setIcon(new ImageIcon("images/exit1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					con.close();
				}
				catch(Exception e)
				{
				}
				fn.setEnabled(true);	//here mainform is going to enabled
				fr.dispose();
			}
		});

		lb1 = new JLabel("Enter Vehicle Number");
		lb1.setBounds(400,20,200,30);
		lb1.setForeground(Color.pink);
		lb1.setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(lb1);

		l1 = new JLabel("Vehicle Number");
		l2 = new JLabel("Mobile Number");
		l3 = new JLabel("Car Type");
		l4 = new JLabel("Parking Date");
		l5 = new JLabel("Slot Number");
		l6 = new JLabel("Duration (Hr)");
		l7 = new JLabel("Amount (Rs)");
		l8 = new JLabel("Email-ID");

		l1.setBounds(310,180,150,30);
		l2.setBounds(310,220,150,30);
		l3.setBounds(310,260,150,30);
		l4.setBounds(310,300,150,30);
		l5.setBounds(310,340,150,30);
		l6.setBounds(310,380,150,30);
		l7.setBounds(310,420,150,30);
		l8.setBounds(310,460,150,30);

		l1.setForeground(Color.green);
		l2.setForeground(Color.pink);
		l3.setForeground(Color.pink);
		l4.setForeground(Color.pink);
		l5.setForeground(Color.pink);
		l6.setForeground(Color.pink);
		l7.setForeground(Color.pink);
		l8.setForeground(Color.pink);

		l1.setFont(new Font("verdana",Font.PLAIN,16));
		l2.setFont(new Font("verdana",Font.PLAIN,16));
		l3.setFont(new Font("verdana",Font.PLAIN,16));
		l4.setFont(new Font("verdana",Font.PLAIN,16));
		l5.setFont(new Font("verdana",Font.PLAIN,16));
		l6.setFont(new Font("verdana",Font.PLAIN,16));
		l7.setFont(new Font("verdana",Font.PLAIN,16));
		l8.setFont(new Font("verdana",Font.PLAIN,16));
		
		fr.add(l1);
		fr.add(l2);
		fr.add(l3);
		fr.add(l4);
		fr.add(l5);
		fr.add(l6);
		fr.add(l7);
		fr.add(l8);

		Border border = BorderFactory.createLineBorder(Color.gray);
		
		t1 = new JTextField();
		t1.setEditable(false);
		t2 = new JTextField();
		t3 = new JTextField();
		t4 = new JTextField();
		t5 = new JTextField();
		t6 = new JTextField();
		t1.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t3.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t4.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t5.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t6.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t1.setBounds(450,180,230,30);
		t2.setBounds(450,220,230,30);
		t3.setBounds(450,260,230,30);
		t4.setBounds(450,380,230,30);
		t5.setBounds(450,420,230,30);
		t6.setBounds(450,460,230,30);
		t1.setCaretColor(Color.green);
		t2.setCaretColor(Color.green);
		t3.setCaretColor(Color.green);
		t4.setCaretColor(Color.green);
		t5.setCaretColor(Color.green);
		t6.setCaretColor(Color.green);
		t1.setForeground(Color.green);
		t2.setForeground(Color.green);
		t3.setForeground(Color.green);
		t4.setForeground(Color.green);
		t5.setForeground(Color.green);
		t6.setForeground(Color.green);
		t1.setFont(new Font("verdana",Font.PLAIN,16));
		t2.setFont(new Font("verdana",Font.PLAIN,16));
		t3.setFont(new Font("verdana",Font.PLAIN,16));
		t4.setFont(new Font("verdana",Font.PLAIN,16));
		t5.setFont(new Font("verdana",Font.PLAIN,16));
		t6.setFont(new Font("verdana",Font.PLAIN,16));
		t1.setOpaque(false);
		t2.setOpaque(false);
		t3.setOpaque(false);
		t4.setOpaque(false);
		t5.setOpaque(false);
		t6.setOpaque(false);
		fr.add(t1);
		fr.add(t2);
		fr.add(t3);
		fr.add(t4);
		fr.add(t5);
		fr.add(t6);

		t7 = new JTextField();
		t7.setBounds(450,300,230,30);
		t7.setOpaque(false);
		t7.setFont(new Font("verdana",Font.PLAIN,16));
		t7.setForeground(Color.green);
		t7.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
		fr.add(t7);

		t8 = new JTextField();
		t8.setBounds(450,340,230,30);
		t8.setOpaque(false);
		t8.setFont(new Font("verdana",Font.PLAIN,16));
		t8.setForeground(Color.green);
		t8.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
		fr.add(t8);

		MaskFormatter mask = null;
		try 
		{
			mask = new MaskFormatter("** ** ** ****");
			//mask.setValidCharacters("qwertyuiopasdfghjklzxcvbnm"+"QWERTYUIOPASDFGHJKLZXCVBNM");
    			mask.setPlaceholderCharacter(' ');
		} 
		catch (Exception e) 
		{
    			e.printStackTrace();
		}  

		f_num = new JFormattedTextField(mask);
		f_num.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
		f_num.setBounds(400,55,140,30);
		f_num.setForeground(new Color(76, 50, 168));
		f_num.setFont(new Font("verdana", Font.BOLD, 15));
		f_num.setOpaque(false);
		fr.add(f_num);	

		f_num.addFocusListener(new FocusAdapter()
		{
			public void focusLost(FocusEvent fe)
			{
				if(f_num.getText().trim().length()!=0)
				{
					try
					{
						String regx = "^[A-Z]{2}\\s[0-9]{2}\\s[A-Z]{2}\\s[0-9]{4}$";
						String vech = f_num.getText().toUpperCase();
													
						Pattern ptrn = Pattern.compile(regx);
						Matcher mtch = ptrn.matcher(vech);

						if(mtch.matches()==false)
						{
							JOptionPane.showMessageDialog(fr,"Please Enter Valid Vechile Number Like (UP 15 AB 5555)");
							f_num.requestFocus();
						}
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
			}
		});

		lsrch = new JLabel(new ImageIcon("images\\search1.png"));
		lsrch.setBounds(550,55,32,32);
		lsrch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lsrch.setToolTipText("search");
		fr.add(lsrch);
		lsrch.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lsrch.setIcon(new ImageIcon("images/search2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				lsrch.setIcon(new ImageIcon("images/search1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					if(f_num.getText().trim().length()==0)
					{
						JOptionPane.showMessageDialog(fr,"Please Enter Vehicle Number");
						f_num.requestFocus();
					}
					else
					{
						Connection con = dao.connect();
						PreparedStatement ps = con.prepareStatement("select * from data where veh_num=?");
						ps.setString(1,f_num.getText());
						ResultSet rs = ps.executeQuery();
						if(rs.next()==true)
						{
							t1.setText(rs.getString("veh_num"));
							t2.setText(rs.getString("mobile"));
							t3.setText(rs.getString("car_type"));
							t7.setText(rs.getString("date"));
							t8.setText(rs.getString("slots"));
							t4.setText(rs.getString("duration"));
							t5.setText(rs.getString("amount"));
							t6.setText(rs.getString("email"));
						}	
						else
						{
							JOptionPane.showMessageDialog(fr,"Invalid Vehicle Number");
						}
						con.close();
					}
				}
				catch(Exception e)
				{
				}
			}
		});

		
		lreset = new JLabel(new ImageIcon("images\\reset1.png"));
		lreset.setBounds(600,55,32,32);
		lreset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lreset.setToolTipText("reset");
		fr.add(lreset);
		lreset.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lreset.setIcon(new ImageIcon("images/reset2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				lreset.setIcon(new ImageIcon("images/reset1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				t6.setText("");
				t7.setText("");
				t8.setText("");
				f_num.setText("");
			}
		});

		ldel = new JLabel(new ImageIcon("images\\del1.png"));
		ldel.setBounds(650,55,32,32);
		ldel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ldel.setToolTipText("delete");
		fr.add(ldel);
		ldel.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				ldel.setIcon(new ImageIcon("images/del2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				ldel.setIcon(new ImageIcon("images/del1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					if(f_num.getText().trim().length()==0)
					{
						JOptionPane.showMessageDialog(fr,"Please Search Vehicle Number First");
						f_num.requestFocus();
					}
					else
					{
						Connection con = dao.connect();
						PreparedStatement ps = con.prepareStatement("delete from data where veh_num=?");
						ps.setString(1,f_num.getText());
						int z = ps.executeUpdate();
						if(z>0)
						{
							JOptionPane.showMessageDialog(fr,"Vehicle "+f_num.getText().toUpperCase()+" information has been removed");
							t1.setText("");
							t2.setText("");
							t3.setText("");
							t4.setText("");
							t5.setText("");
							t6.setText("");
							t7.setText("");
							t8.setText("");
							f_num.setText("");
						}	
						else
						{
							JOptionPane.showMessageDialog(fr,"Invalid Vehicle Number");
						}
						con.close();
					}
				}
				catch(Exception e)
				{
				}
			}
		});
		

		t1.setEditable(false);	
		t2.setEditable(false);	
		t3.setEditable(false);	
		t4.setEditable(false);	
		t5.setEditable(false);	
		t6.setEditable(false);	
		t7.setEditable(false);	
		t8.setEditable(false);	

		lnext = new JLabel(new ImageIcon("images/next1.png"));
		lprev = new JLabel(new ImageIcon("images/prev1.png"));
		lfirst = new JLabel(new ImageIcon("images/first1.png"));
		llast = new JLabel(new ImageIcon("images/last1.png"));
		lnext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lprev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lfirst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		llast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lnext.setBounds(445,503,48,48);
		lprev.setBounds(500,503,48,48);
		lfirst.setBounds(570,503,48,48);
		llast.setBounds(640,503,48,48);
		fr.add(lnext);
		fr.add(lprev);
		fr.add(lfirst);
		fr.add(llast);

		lnext.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lnext.setIcon(new ImageIcon("images/next2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				lnext.setIcon(new ImageIcon("images/next1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					rs.next();
					t1.setText(rs.getString("veh_num"));
					t2.setText(rs.getString("mobile"));
					t3.setText(rs.getString("car_type"));
					t7.setText(rs.getString("date"));
					t8.setText(rs.getString("slots"));
					t4.setText(rs.getString("duration"));
					t5.setText(rs.getString("amount"));
					t6.setText(rs.getString("email"));
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(fr,"You Are At Last Record");
				}
			}
		});

		lprev.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lprev.setIcon(new ImageIcon("images/prev2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				lprev.setIcon(new ImageIcon("images/prev1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					rs.previous();
					t1.setText(rs.getString("veh_num"));
					t2.setText(rs.getString("mobile"));
					t3.setText(rs.getString("car_type"));
					t7.setText(rs.getString("date"));
					t8.setText(rs.getString("slots"));
					t4.setText(rs.getString("duration"));
					t5.setText(rs.getString("amount"));
					t6.setText(rs.getString("email"));
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(fr,"You Are At First Record");
				}
			}
		});

		lfirst.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lfirst.setIcon(new ImageIcon("images/first2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				lfirst.setIcon(new ImageIcon("images/first1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					rs.first();
					t1.setText(rs.getString("veh_num"));
					t2.setText(rs.getString("mobile"));
					t3.setText(rs.getString("car_type"));
					t7.setText(rs.getString("date"));
					t8.setText(rs.getString("slots"));
					t4.setText(rs.getString("duration"));
					t5.setText(rs.getString("amount"));
					t6.setText(rs.getString("email"));
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(fr,"You Are At First Record");
				}
			}
		});

		llast.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				llast.setIcon(new ImageIcon("images/last2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				llast.setIcon(new ImageIcon("images/last1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					rs.last();
					t1.setText(rs.getString("veh_num"));
					t2.setText(rs.getString("mobile"));
					t3.setText(rs.getString("car_type"));
					t7.setText(rs.getString("date"));
					t8.setText(rs.getString("slots"));
					t4.setText(rs.getString("duration"));
					t5.setText(rs.getString("amount"));
					t6.setText(rs.getString("email"));
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(fr,"You Are At Last Record");
				}
			}
		});

		fr.setUndecorated(true);
		fr.setVisible(true);
		getData();
	}

	public void getData()
	{
		try
		{
			con = dao.connect();
			ps = con.prepareStatement("select * from data",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
		}
		catch(Exception e)
		{
		}
	}

	public static void main(String ag[])
	{
		new onebyone(new JFrame());
	}
}