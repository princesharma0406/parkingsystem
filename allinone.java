import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

class allinone implements ActionListener
{
	JFrame fr,fn;
	JLabel lexit,l1,l2,l3;
	JTable tab;
	JScrollPane jsp;
	JComboBox cb; 
	JButton b1;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	ResultSetMetaData rsmd;

	public allinone(JFrame f)
	{
		fn = f;

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		fr = new JFrame("Database Table");
		fr.setBounds((dim.width-1200)/2,(dim.height-600)/2,1200,600);
		fr.setContentPane(new JLabel(new ImageIcon("images/allinone.png")));
		fr.setLayout(null);

		lexit = new JLabel(new ImageIcon("images/exit1.png"));
		lexit.setBounds(1150,20,32,32);
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

		
		l1 = new JLabel("Select Month");
		l1.setFont(new Font("verdana",Font.PLAIN,20));
		l1.setForeground(Color.cyan);
		l1.setBounds(525,20,140,30);
		fr.add(l1);

		l2 = new JLabel("Total Amount : 0");
		l2.setFont(new Font("verdana",Font.PLAIN,20));
		l2.setForeground(Color.cyan);
		l2.setBounds(495,500,200,30);
		fr.add(l2);
		
		cb = new JComboBox();
		cb.addItem("Jan");
		cb.addItem("Feb");
		cb.addItem("Mar");
		cb.addItem("Apr");
		cb.addItem("May");
		cb.addItem("Jun");
		cb.addItem("Jul");
		cb.addItem("Aug");
		cb.addItem("Sep");
		cb.addItem("Oct");
		cb.addItem("Nov");
		cb.addItem("Dec");
		cb.setFont(new Font("verdana",Font.PLAIN,18));
		cb.setForeground(Color.black);
		cb.setBounds(525,55,70,30);
		fr.add(cb);

		b1 = new JButton("Go");
		b1.setFont(new Font("verdana",Font.PLAIN,18));
		b1.setForeground(Color.red);
		b1.setBounds(600,55,60,30);
		b1.addActionListener(this);
		fr.add(b1);

		fr.setUndecorated(true);
		fr.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			con = dao.connect();
			ps = con.prepareStatement("select * from data where month=?");
			ps.setString(1,cb.getSelectedItem().toString());
			rs = ps.executeQuery();
		
			rsmd = rs.getMetaData();
		
			int col = rsmd.getColumnCount();	//count number of columns in table

			Vector<String> headings = new Vector<String>();
			for(int i=1 ; i<col ; i++)
			{
				headings.add(rsmd.getColumnName(i).toUpperCase());
			}		

			Vector<Vector> rows = new Vector<Vector>();
			int am=0;
			while(rs.next())
			{
				am = am + Integer.parseInt(rs.getString("amount"));
				Vector<Object> data = new Vector<Object>();
				for(int i=1 ; i<col ; i++)
				{
					data.add(rs.getObject(i));
				}
				rows.add(data);
			}
			l2.setText("Total Amount : " + am);

			con.close();

			tab = new JTable(rows,headings);
			jsp = new JScrollPane(tab);
			jsp.setBounds(15,130,1170,300);
			fr.add(jsp);
		}
		catch(Exception e)
		{
		}		
	}

	public static void main(String args[])
	{
		new allinone(new JFrame());
	}
}