import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.time.*;
import java.text.SimpleDateFormat;
import java.util.regex.*;
import java.util.Date;
import java.io.*;
import javax.swing.text.MaskFormatter;

public class dashboard implements ActionListener , MouseListener
{
	JFrame fr;
	JPanel dash, parking;
	JLabel car_type, mobile, num, email ,entry, exit, vehicle_num, l_search, l_amt, l_duration, head;
	JTextField f_mobile, f_duration, f_amt , temail;
	JFormattedTextField f_num , f_vehicle_num; 
	JButton b_entry, b_exit;
	static int car = 0;
	JComboBox car_list;
	String list[] = { "Small", "Medium", "Large" };
	JLabel small[] = new JLabel[12], medium[] = new JLabel[6], large[] = new JLabel[4];
	static boolean search = false, found_in_large = false, found_in_medium = false, found_in_small = false;
	static int s[] = new int[12], m[] = new int[6], l[] = new int[4];
	String to;
	/*
	 * public void large_state() { for(int i=0;i<4;i++) { //finding space in small
	 * car slot if(s[i]==0) {
	 * 
	 * } else{} } }
	 * 
	 * public void medium_state() { for(int i=0;i<6;i++) { //finding space in small
	 * car slot
	 * 
	 * } }
	 * 
	 * public void small_state() { for(int i=0;i<12;i++) { //finding space in small
	 * car slot
	 * 
	 * } }
	 */
	public void manageSmallSlots(){
		for(int i=0;i<12;i++){
			if(s[i]==1){
				small[i].setIcon(new ImageIcon("images/small3.png"));
			}
		}	
	}
	
	public void manageMediumSlots(){
		for(int i=0;i<6;i++){
			if(m[i]==1){
				medium[i].setIcon(new ImageIcon("images/medium2.png"));
			}else if(m[i]==2 || m[i]==3){
				medium[i].setIcon(new ImageIcon("images/medium3.png"));
			}
		}
	}
	
	public void manageLargeSlots(){
		for(int i=0;i<4;i++){
			if(l[i]==1 || l[i]==2 || l[i]==4){
				large[i].setIcon(new ImageIcon("images/large2.png"));
			}else if(l[i]==3 || l[i]==5 || l[i]==6){
				large[i].setIcon(new ImageIcon("images/large3.png"));
			}
		}
	}
	
	public int findSlot() 
	{
		int book = -1;
		if (car == 0) 
		{
			// small car entering
			for (int i = 0; i < 12; i++) 
			{
				// finding space in small car slot
				if (s[i] == 0) 
				{
					book = i;
					s[i] = 1;
					// parking_code=1;
					//small[i].setToolTipText("1 Small Car !");
					found_in_small = true;
					break;
				}
			}

			if (book == -1) 
			{
				// finding space in medium car slot
				for (int i = 0; i < 6; i++) {
					if (m[i] == 0 || m[i] == 1) {
						book = i;
						if (m[i] == 0) {
							m[i] = 1;
							//medium[i].setToolTipText("1 Small Car !");
							// parking_code=1;
						} else if (m[i] == 1) {
							m[i] = 2;
							//medium[i].setToolTipText("2 Small Cars !");
							// parking_code=2;
						}
						found_in_medium = true;
						break;
					}
				}
			}

			if (book == -1) 
			{
				// finding car in large car slot
				for (int i = 0; i < 4; i++) 
				{
					if (l[i] == 0 || l[i] == 1 || l[i] == 2 || l[i] == 4) {
						book = i;
						if (l[i] == 0) 
						{
							l[i] = 1;
							//large[i].setToolTipText("1 Small Car !");
							// parking_code=1;
						} 
						else if (l[i] == 1) 
						{
							l[i] = 2;
							//large[i].setToolTipText("2 Small Cars !");
							// parking_code=2;
						} 
						else if (l[i] == 2) 
						{
							l[i] = 3;
							//large[i].setToolTipText("3 Small Cars !");
							// parking_code=3;
						} 
						else if (l[i] == 4) 
						{
							l[i] = 5;
							//large[i].setToolTipText("1 Small AND 1 Medium Car !");
							// parking_code=5;
						}
						found_in_large = true;
						break;
					}
				}
			}
		} else if (car == 1) {
			// medium car entering

			for (int i = 0; i < 6; i++) {
				// searching in medium
				if (m[i] == 0) {
					book = i;
					m[i] = 3;
					// parking_code=4;
					//medium[i].setToolTipText("1 Medium Car !");
					found_in_medium = true;
					break;
				}
			}
			if (book == -1) {
				// searching in large
				for (int i = 0; i < 4; i++) {
					if (l[i] == 0 || l[i] == 1) {
						book = i;
						if (l[i] == 0) {
							l[i] = 4;
							//large[i].setToolTipText("1 Medium Car !");
							// parking_code=4;
						} else if (l[i] == 1) {
							l[i] = 5;
							//large[i].setToolTipText("1 Medium And 1 Small Car !");
							// parking_code=5;
						}
						found_in_large = true;
						break;
					}
				}
			}

			if (book == -1) {
				// searching in small
				for (int i = 0; i < 11; i++) {
					if (s[i] == 0 && s[i + 1] == 0) {
						book = i;
						s[i] = 1;
						s[i + 1] = 1;
						// parking_code=1;
						//small[i].setToolTipText("1 Medium Car !");
						//small[i + 1].setToolTipText("1 Medium Car !");
						found_in_small = true;
						break;
					}
				}
			}
		} else if (car == 2) {
			// large car entering
			for (int i = 0; i < 4; i++) {
				// searching in large
				if (l[i] == 0) {
					book = i;
					l[i] = 6;
					//large[i].setToolTipText("1 Large Car !");
					// parking_code=6;
					found_in_large = true;
					break;
				}
			}

			if (book == -1) {
				// searching in small
				for (int i = 0; i < 10; i++) {
					if (s[i] == 0 && s[i + 1] == 0 && s[i + 2] == 0) {
						book = i;
						s[i] = 1;
						s[i + 1] = 1;
						s[i + 2] = 1;
						//small[i].setToolTipText("1 Large Car !");
						//small[i + 1].setToolTipText("1 Large Car !");
						//small[i + 2].setToolTipText("1 Large Car !");
						// parking_code=1;
						found_in_small = true;
						break;
					}
				}
			}

		}

		return book;
	}

	public void mouseEntered(MouseEvent me)
	{
		JLabel lbl = (JLabel)me.getComponent();
		String slotnum = lbl.getText();
		try
		{
			Connection con = dao.connect();
			PreparedStatement ps1 = con.prepareStatement("select * from slips");
			ResultSet rs1 = ps1.executeQuery();

			StringBuffer sb = new StringBuffer("");
			while(rs1.next()==true)
			{
				String dbslot = rs1.getString("slots").substring(0,3);
				if(dbslot.equals(slotnum))
				{
					sb.append("<html>"+rs1.getString("veh_num") +"<br>"+ rs1.getString("car_type") + "<br>" + rs1.getString("slots")+"</html>");
				}
				else
				{
					lbl.setToolTipText("");
				}
			}
			
			if(sb.length()!=0)
			{			
				lbl.setToolTipText(sb.toString());
			}	
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseExited(MouseEvent me){}

	public dashboard() 
	{
		Border border = BorderFactory.createLineBorder(Color.black);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame("Parking System - Dashboard");
		fr.setLayout(null);
		fr.setSize(dim.width, dim.height);
		fr.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
		fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// code for reading array from files named small.txt / medium.txt / large.txt
		try {
			FileReader fr1 = new FileReader("small.txt");
			FileReader fr2 = new FileReader("medium.txt");
			FileReader fr3 = new FileReader("large.txt");

			for (int i = 0; i < 12; i++) {
				s[i] = fr1.read();
			}
			for (int i = 0; i < 6; i++) {
				m[i] = fr2.read();
			}
			for (int i = 0; i < 4; i++) {
				l[i] = fr3.read();
			}
			fr1.close();
			fr2.close();
			fr3.close();
		} catch (Exception e) {
		}

		fr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				// code for writing array to files named small.txt / medium.txt / large.txt
				try {
					FileWriter fw1 = new FileWriter("small.txt");
					FileWriter fw2 = new FileWriter("medium.txt");
					FileWriter fw3 = new FileWriter("large.txt");

					for (int i = 0; i < 12; i++) {
						fw1.write(s[i]);
					}
					for (int i = 0; i < 6; i++) {
						fw2.write(m[i]);
					}
					for (int i = 0; i < 4; i++) {
						fw3.write(l[i]);
					}
					fw1.close();
					fw2.close();
					fw3.close();
					System.exit(0);
				} catch (Exception e) {
				}
			}
		});

		dash = new JPanel(null);
		dash.setBounds((dim.width - 1000) / 2, 0, 1000, 200);
		fr.add(dash);

		entry = new JLabel("On - Entry");
		entry.setBounds(100, 10, 130, 30);
		entry.setFont(new Font("Georgia", Font.BOLD, 22));
		entry.setForeground(Color.BLUE);
		dash.add(entry);

		car_type = new JLabel("Car Type");
		car_type.setBounds(20, 50, 120, 25);
		car_type.setFont(new Font("Georgia", Font.BOLD, 16));
		dash.add(car_type);

		mobile = new JLabel("Phone No.");
		mobile.setBounds(20, 80, 120, 25);
		mobile.setFont(new Font("Georgia", Font.BOLD, 16));
		dash.add(mobile);

		num = new JLabel("Vehicle Number");
		num.setBounds(20, 110, 140, 25);
		num.setFont(new Font("Georgia", Font.BOLD, 16));
		dash.add(num);

		email = new JLabel("Email-ID");
		email.setBounds(20, 140, 140, 25);
		email.setFont(new Font("Georgia", Font.BOLD, 16));
		dash.add(email);

		car_list = new JComboBox(list);
		car_list.setBounds(170, 50, 150, 25);
		car_list.setForeground(new Color(76, 50, 168));
		car_list.setFont(new Font("Georgia", Font.BOLD, 14));
		dash.add(car_list);
		car_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				car = car_list.getSelectedIndex();
			}
		});

		f_mobile = new JTextField();
		f_mobile.setBounds(170, 80, 150, 25);
		f_mobile.setForeground(new Color(76, 50, 168));
		f_mobile.setFont(new Font("verdana", Font.BOLD, 14));
		dash.add(f_mobile);
		f_mobile.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent ke) 
			{
				char ch = ke.getKeyChar();

				if (ch<'0' || ch>'9') 
				{
					ke.consume();
				} 
				if(f_mobile.getText().length()>9) 
				{
					ke.consume();
				}
			}
		});

		f_mobile.addFocusListener(new FocusAdapter()
		{
			public void focusLost(FocusEvent fe)
			{
				if(f_mobile.getText().length()!=0)
				{
					if(f_mobile.getText().length()<10)
					{
						JOptionPane.showMessageDialog(fr,"Please Enter 10 digits Mobile Number");
						f_mobile.requestFocus();
					}	
				}
			}
		});
		
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
		f_num.setBounds(170, 110, 150, 25);
		f_num.setForeground(new Color(76, 50, 168));
		f_num.setFont(new Font("verdana", Font.BOLD, 14));
		dash.add(f_num);

		temail = new JTextField();
		temail.setBounds(170, 140, 150, 25);
		temail.setForeground(new Color(76, 50, 168));
		temail.setFont(new Font("verdana", Font.BOLD, 14));
		dash.add(temail);
		temail.addFocusListener(new FocusAdapter()
		{
			public void focusLost(FocusEvent fe)
			{
				if(temail.getText().length()==0)
				{
				}
				else if(temail.getText().length()!=0)
				{
					try
					{
						String estr = temail.getText();
						String pstr = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
						
						Pattern ptrn = Pattern.compile(pstr);
						Matcher mtch = ptrn.matcher(estr);

						if(mtch.matches()==false)
						{
							JOptionPane.showMessageDialog(fr,"Invalid Email-ID");
							temail.requestFocus();
						}
					}
					catch(Exception e)
					{
					}
				}
			}
		});
		

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

		b_entry = new JButton("Get Slip");
		b_entry.setBounds(220, 170, 100, 25);
		dash.add(b_entry);
		b_entry.addActionListener(this);

		exit = new JLabel("On - Exit");
		exit.setBounds(700, 10, 130, 30);
		exit.setFont(new Font("Georgia", Font.BOLD, 22));
		exit.setForeground(Color.BLUE);
		dash.add(exit);

		vehicle_num = new JLabel("Vehicle Number");
		vehicle_num.setBounds(620, 50, 140, 25);
		vehicle_num.setFont(new Font("Georgia", Font.BOLD, 16));
		dash.add(vehicle_num);

		l_duration = new JLabel("Time (in hrs.)");
		l_duration.setBounds(620, 80, 140, 25);
		l_duration.setFont(new Font("Georgia", Font.BOLD, 16));
		dash.add(l_duration);

		l_amt = new JLabel("Amount (in Rs.)");
		l_amt.setBounds(620, 110, 140, 25);
		l_amt.setFont(new Font("Georgia", Font.BOLD, 16));
		dash.add(l_amt);

		f_vehicle_num = new JFormattedTextField(mask);
		f_vehicle_num.setBounds(780, 50, 150, 25);
		f_vehicle_num.setForeground(new Color(76, 50, 168));
		f_vehicle_num.setFont(new Font("verdana", Font.BOLD, 14));
		f_vehicle_num.setEditable(false);
		dash.add(f_vehicle_num);

		f_duration = new JTextField();
		f_duration.setBounds(780, 80, 150, 25);
		f_duration.setForeground(new Color(76, 50, 168));
		f_duration.setFont(new Font("verdana", Font.BOLD, 14));
		f_duration.setEditable(false);
		dash.add(f_duration);

		f_amt = new JTextField();
		f_amt.setBounds(780, 110, 150, 25);
		f_amt.setForeground(new Color(76, 50, 168));
		f_amt.setFont(new Font("verdana", Font.BOLD, 14));
		f_amt.setEditable(false);
		dash.add(f_amt);

		b_exit = new JButton("Exit");
		b_exit.setBounds(830, 140, 100, 25);
		b_exit.setToolTipText("Accept Payemnt and confirm exit.");
		dash.add(b_exit);
		b_exit.addActionListener(this);

		l_search = new JLabel(new ImageIcon("images/ser1.png"));
		l_search.setToolTipText("Click and enter slip number .");
		l_search.setBounds(940, 45, 32, 32);
		l_search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dash.add(l_search);
		l_search.addMouseListener(new MouseAdapter() 
		{
			public void mouseEntered(MouseEvent me) 
			{
				l_search.setIcon(new ImageIcon("Images/ser2.png"));
			}

			public void mouseExited(MouseEvent me) 
			{
				l_search.setIcon(new ImageIcon("Images/ser1.png"));
			}

			public void mouseClicked(MouseEvent me) 
			{
				String res = JOptionPane.showInputDialog(fr, "Please Enter Vehicle Number with space like (UP 15 AB 1111)");
				if (res == null) 
				{
					JOptionPane.showMessageDialog(fr, "Canceled by User");
				} 
				else if (res.equals("")) 
				{
					JOptionPane.showMessageDialog(fr, "Please dont leave the field empty.");
				} 
				else 
				{
					String resdup = res;
					//res = res.replaceAll(" ", "");
					search = true;
					try 
					{
						Connection con = dao.connect();
						PreparedStatement ps = con.prepareStatement("select * from slips where veh_num=?");
						ps.setString(1, res);
						ResultSet rs = ps.executeQuery();
						if (rs.next()) 
						{
							f_vehicle_num.setText(res);
													
							float l = (System.currentTimeMillis() - Long.parseLong(rs.getString("time"))) / 3600000;
							long tim = 0;
							if (l % 1 == 0.0f) 
							{
								tim = (long) l / 1;
							} 
							else 
							{
								tim = (long) l / 1 + 1;
							}
							f_duration.setText((long) l + "");
							String cc = rs.getString("car_type");
							long amt = 0;

							if (cc.equals("Small")) 
							{
								amt = 40;
							} 
							else if (cc.equals("Medium")) 
							{
								amt = 80;
							} 
							else 
							{
								amt = 120;
							}
							amt *= tim;
							if (amt == 0) 
							{
								amt = 20;
							}
							f_amt.setText(amt + "");
						} 
						else 
						{
							JOptionPane.showMessageDialog(fr, "There is no Car of this Number");
						}
						con.close();
					} 
					catch (Exception e) 
					{
						JOptionPane.showMessageDialog(fr, e);
					}
				}
			}
		});

		parking = new JPanel(null);
		parking.setBounds((dim.width - 1233) / 2, 200, 1233, 400);
		fr.add(parking);

		head = new JLabel(new ImageIcon("images/head.png"));
		head.setBounds((1233 - 400) / 2, 0, 400, 50);
		parking.add(head);

		int top = 60;
		for (int i = 0; i < 12; i++) 
		{
			// for small sized cars
			small[i] = new JLabel("S-" + (i + 1), new ImageIcon("images/small1.png"), JLabel.LEFT)
			{
      				public Point getToolTipLocation(MouseEvent event) 
				{
        				return new Point(0,0);
      				}
    			};

			if (i == 0) {
				small[i].setBounds(i * 100, top, 100, 100);
			} else {
				small[i].setBounds(i * (103), top, 100, 100);
			}
			small[i].setHorizontalTextPosition(JLabel.CENTER);
			small[i].setVerticalTextPosition(JLabel.CENTER);
			//small[i].setToolTipText("Empty");
			small[i].addMouseListener(this);
			parking.add(small[i]);
		}
		top += 110;
		for (int j = 0; j < 6; j++) {
			// for medium sized cars
			medium[j] = new JLabel("M-" + (j + 1), new ImageIcon("images/medium1.png"), JLabel.LEFT)
			{
      				public Point getToolTipLocation(MouseEvent event) 
				{
        				return new Point(0,0);
      				}
    			};
			if (j == 0)
				medium[j].setBounds(j * 200, top, 203, 100);
			else
				medium[j].setBounds(j * (206), top, 203, 100);

			medium[j].setHorizontalTextPosition(JLabel.CENTER);
			medium[j].setVerticalTextPosition(JLabel.CENTER);
			//medium[j].setToolTipText("Empty");
			medium[j].addMouseListener(this);
			parking.add(medium[j]);
		}
		top += 110;
		for (int k = 0; k < 4; k++) 
		{
			// for large sized cars
			large[k] = new JLabel("L-" + (k + 1), new ImageIcon("images/large1.png"), JLabel.LEFT)
			{
      				public Point getToolTipLocation(MouseEvent event) 
				{
        				return new Point(0,0);
      				}
    			};
			if (k == 0)
				large[k].setBounds(0, top, 300, 100);
			else
				large[k].setBounds(k * 310, top, 300, 100);

			large[k].setHorizontalTextPosition(JLabel.CENTER);
			large[k].setVerticalTextPosition(JLabel.CENTER);
			//large[k].setToolTipText("Empty");
			large[k].addMouseListener(this);
			parking.add(large[k]);
		}

		this.manageSmallSlots();
		this.manageMediumSlots();
		this.manageLargeSlots();
		fr.setVisible(true);

	}// end of dashboard

	public void actionPerformed(ActionEvent ae) 
	{

		if (ae.getSource() == b_exit) 
		{
			if (search == false) 
			{
				JOptionPane.showMessageDialog(fr, "Please search the vehicle number first.");
			} 
			else 
			{

				try 
				{
					Connection con = dao.connect();
					PreparedStatement ps = con.prepareStatement("select * from slips where veh_num=?");
					String vehnum = f_vehicle_num.getText();
					ps.setString(1, vehnum);
					ResultSet rs = ps.executeQuery();

					if (rs.next()) 
					{
						String a1 = rs.getString("car_type");
						String sl = rs.getString("slots");
						char d = sl.charAt(0);
						int index = -1;
						if (a1.equals("Small")) 
						{
							index = Integer.parseInt(sl.substring(2, sl.length())) - 1;
							if (d == 'S') 
							{
								small[index].setIcon(new ImageIcon("images/small1.png"));
								s[index] = 0;
								//small[index].setToolTipText("Empty !");
							} 
							else if (d == 'M') 
							{
								if (m[index] == 1) 
								{
									medium[index].setIcon(new ImageIcon("images/medium1.png"));
									m[index] = 0;
									//medium[index].setToolTipText("Empty !");
								
								} 
								else if (m[index] == 2) 
								{
									medium[index].setIcon(new ImageIcon("images/medium2.png"));
									m[index] = 1;
									//medium[index].setToolTipText("1 Small Car !");
								}
							} 
							else 
							{
								if (l[index] == 1) 
								{
									large[index].setIcon(new ImageIcon("images/large1.png"));
									l[index] = 0;
									//large[index].setToolTipText("Empty !");
								} 
								else if (l[index] == 2) 
								{
									large[index].setIcon(new ImageIcon("images/large2.png"));
									l[index] = 1;
									//large[index].setToolTipText("1 Small Car !");
								} 
								else if (l[index] == 3) 
								{
									large[index].setIcon(new ImageIcon("images/large2.png"));
									l[index] = 2;
									//large[index].setToolTipText("2 Small Cars !");
								} else if (l[index] == 5) {
									large[index].setIcon(new ImageIcon("images/large2.png"));
									l[index] = 4;
									//large[index].setToolTipText("1 Medium Car !");
								}
							}
						} 
						else if (a1.equals("Medium")) 
						{
							if (d == 'S') 
							{
								index = Integer.parseInt(sl.substring(2, sl.indexOf(","))) - 1;
								small[index].setIcon(new ImageIcon("images/small1.png"));
								small[index + 1].setIcon(new ImageIcon("images/small1.png"));
								s[index] = 0;
								s[index + 1] = 0;
								//small[index].setToolTipText("Empty !");
								//small[index + 1].setToolTipText("Empty !");
							} 
							else if (d == 'M') 
							{
								index = Integer.parseInt(sl.substring(2, sl.length())) - 1;
								medium[index].setIcon(new ImageIcon("images/medium1.png"));
								m[index] = 0;
								//medium[index].setToolTipText("Empty !");
							} 
							else 
							{
								index = Integer.parseInt(sl.substring(2, sl.length())) - 1;
								if (l[index] == 4) 
								{
									large[index].setIcon(new ImageIcon("images/large1.png"));
									l[index] = 0;
									//large[index].setToolTipText("Empty !");
								} 
								else if (l[index] == 5) 
								{
									large[index].setIcon(new ImageIcon("images/large2.png"));
									l[index] = 1;
									//large[index].setToolTipText("1 Small Car !");
								}
							}
						} 
						else if (a1.equals("Large")) 
						{
							if (d == 'S') 
							{
								index = Integer.parseInt(sl.substring(2, sl.indexOf(","))) - 1;
								small[index].setIcon(new ImageIcon("images/small1.png"));
								small[index + 1].setIcon(new ImageIcon("images/small1.png"));
								small[index + 2].setIcon(new ImageIcon("images/small1.png"));
								s[index] = 0;
								s[index + 1] = 0;
								s[index + 2] = 0;
								//small[index].setToolTipText("Empty !");
								//small[index + 1].setToolTipText("Empty !");
								//small[index + 2].setToolTipText("Empty !");
							} 
							else 
							{
								index = Integer.parseInt(sl.substring(2, sl.length())) - 1;
								large[index].setIcon(new ImageIcon("images/large1.png"));
								l[index] = 0;
								//large[index].setToolTipText("Empty !");
							}
						}
						// JOptionPane.showMessageDialog(fr,"Index : "+index+"\nCar Type Code : "+d);
					} 
					else 
					{
						JOptionPane.showMessageDialog(fr, "No match found.");
					}

					PreparedStatement ps1 = con.prepareStatement("insert into data values(?,?,?,?,?,?,?,?,?)");

					ps1.setString(1, rs.getString("veh_num"));
					ps1.setString(2, rs.getString("mobile"));
					ps1.setString(3, rs.getString("car_type"));
					ps1.setString(4, rs.getString("date"));
					ps1.setString(5, rs.getString("slots"));
					ps1.setString(6, f_duration.getText());
					ps1.setString(7, f_amt.getText());
					ps1.setString(8, rs.getString("email"));
					ps1.setString(9, rs.getString("month"));
					int z = ps1.executeUpdate();
					if (z < 0) 
					{
						JOptionPane.showMessageDialog(fr, "Problem connecting to database.");
					}
					ps1 = con.prepareStatement("delete from slips where veh_num=?");
					ps1.setString(1, rs.getString("veh_num"));
					int gg = ps1.executeUpdate();
					if (gg < 0) 
					{
						JOptionPane.showMessageDialog(fr, "Problem while connecting to database.");
					}
					File fe = new File("QRCodes/"+rs.getString("veh_num")+".png");
					fe.delete();
					search = false;
					f_vehicle_num.setText("");
					f_duration.setText("");
					f_amt.setText("");
					con.close();
				} 
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(fr, "Problem while connecting to database."+e);
				}
			}
		} 
		else if (ae.getSource() == b_entry) 
		{
			String var_mob = f_mobile.getText().trim();
			String var_num = f_num.getText().trim();
			String slots = "";
			if (var_mob.length() == 0) 
			{
				JOptionPane.showMessageDialog(fr, "Please enter your 10 digits Mobile Number");
				f_mobile.requestFocus();
			} 
			else if (var_num.length() == 0) 
			{
				JOptionPane.showMessageDialog(fr, "Please enter your Vehicle Number");
				f_num.requestFocus();
			} 
			else 
			{
				boolean bn = false;
				if(temail.getText().trim().length()==0)
				{
					bn = true;
				}

				if(temail.getText().trim().length()!=0)
				{
					try
					{
						String estr = temail.getText();
						String pstr = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
						
						Pattern ptrn = Pattern.compile(pstr);
						Matcher mtch = ptrn.matcher(estr);

						if(mtch.matches()==false)
						{
							JOptionPane.showMessageDialog(fr,"Invalid Email-ID");
							temail.requestFocus();
						}
						else
						{
							bn = true;
						}
					}
					catch(Exception e)
					{
					}
				}	

				if(bn==true)
				{
				int choice = 0;
				try 
				{
					Connection con1 = dao.connect();
					PreparedStatement pss = con1.prepareStatement("select * from slips where veh_num=?");
					pss.setString(1, var_num);
					ResultSet rss = pss.executeQuery();
					if (rss.next() == true) 
					{
						choice = 1;
						JOptionPane.showMessageDialog(fr, "Vehicle number already exists ! ok");
					}
					else
					{
						choice = 0;
					}
					con1.close();
				} 
				catch (Exception e)
				{
				}

				if(choice == 0) 
				{
					int booking = findSlot();
					// JOptionPane.showMessageDialog(fr,"Slot No. :"+booking);
					if (booking == -1) 
					{
						JOptionPane.showMessageDialog(fr, "Parking is Full !");
					} 
					else 
					{
						if (car == 0) 
						{
							if (found_in_small == true) 
							{
								slots = small[booking].getText();
								small[booking].setIcon(new ImageIcon("images/small3.png"));
							} 
							else if (found_in_medium == true)
							{
								slots = medium[booking].getText();
								if (m[booking] == 1)
								{
									medium[booking].setIcon(new ImageIcon("images/medium2.png"));
								} 
								else if (m[booking] == 2)
								{
									medium[booking].setIcon(new ImageIcon("images/medium3.png"));
								}
							}
							else if (found_in_large == true) 
							{
								slots = large[booking].getText();
								if (l[booking] == 1 || l[booking] == 2)
								{
									large[booking].setIcon(new ImageIcon("images/large2.png"));
								}
								else if (l[booking] == 3 || l[booking] == 5)
								{
									large[booking].setIcon(new ImageIcon("images/large3.png"));
								}
							}
						} 
						else if (car == 1)
						{

							if (found_in_small == true)
							{
								slots = small[booking].getText() + "," + small[booking + 1].getText();
								small[booking].setIcon(new ImageIcon("images/small3.png"));
								small[booking + 1].setIcon(new ImageIcon("images/small3.png"));
							}
							else if (found_in_medium == true)
							{
								slots = medium[booking].getText();
								medium[booking].setIcon(new ImageIcon("images/medium3.png"));
							}
							else if (found_in_large == true)
							{
								slots = large[booking].getText();
								if (l[booking] == 4)
								{
									large[booking].setIcon(new ImageIcon("images/large2.png"));
								}
								else if (l[booking] == 5)
								{
									large[booking].setIcon(new ImageIcon("images/large3.png"));
								}
							}
						}
						else if (car == 2)	
						{
							if (found_in_small == true)
							{
								slots = small[booking].getText() + "," + small[booking + 1].getText() + ","+ small[booking + 2].getText();
								small[booking].setIcon(new ImageIcon("images/small3.png"));
								small[booking + 1].setIcon(new ImageIcon("images/small3.png"));
								small[booking + 2].setIcon(new ImageIcon("images/small3.png"));
							}
							else if (found_in_large == true)
							{
								slots = large[booking].getText();
								large[booking].setIcon(new ImageIcon("images/large3.png"));
							}
						}

						LocalTime time = LocalTime.now();
						Date date = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdf = new SimpleDateFormat("MMM");
						String strDate = formatter.format(date);
						String strMonth = sdf.format(date);
						
						String c = list[car];

						try 
						{
							Connection con = dao.connect();
							PreparedStatement ps = con.prepareStatement("insert into slips values(?,?,?,?,?,?,?,?)");
							ps.setString(1, var_num);
							ps.setString(2, var_mob);
							ps.setString(3, c);
							ps.setString(4, strDate);
							ps.setString(5, new String(System.currentTimeMillis() + ""));
							ps.setString(6, slots);
							String ss = temail.getText().trim();
							if(ss.length()==0)
							{
								ss = "NA";
							}							
							ps.setString(7,ss); 
							ps.setString(8,strMonth); 

							int z = ps.executeUpdate();
							if (z > 0) 
							{
								JOptionPane.showMessageDialog(fr, "Slip generated successfully !");
								QRcode.createCode(c, var_mob, var_num, time.toString(), strDate, slots , temail.getText());
								new Slip(f_num.getText().trim());
							}

							f_mobile.setText("");
							f_num.setText("");
							temail.setText("");
							car_list.setSelectedIndex(0);

							found_in_large = false;
							found_in_medium = false;
							found_in_small = false;
							car = 0;
							con.close();

						} 
						catch (Exception e) 
						{
							JOptionPane.showMessageDialog(fr, "Vehicle number already exists !" + e);
						}
					}
				} // end of choice
				} // end of boolean value true
			}
		}

	}// end of actionperformed

	public static void main(String args[]) 
	{
		new dashboard();
	}
}