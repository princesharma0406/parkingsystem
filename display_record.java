import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class display_record
{
	JFrame fr,fn;
	JLabel lexit,lone,lall;

	public display_record()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		fr = new JFrame("Display Record");
		fr.setBounds((dim.width-1000)/2,(dim.height-600)/2,1000,600);
		fr.setContentPane(new JLabel(new ImageIcon("images/display.png")));
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
				System.exit(0);
			}
		});

		lone = new JLabel(new ImageIcon("images/one1.png"));
		lall = new JLabel(new ImageIcon("images/all1.png"));
		lone.setBounds(350,200,128,200);
		lall.setBounds(500,200,128,200);
		lone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lone.setText("One by One");
		lone.setHorizontalTextPosition(JLabel.CENTER);
		lone.setVerticalTextPosition(JLabel.BOTTOM);
		lall.setText("All at Once");
		lall.setHorizontalTextPosition(JLabel.CENTER);
		lall.setVerticalTextPosition(JLabel.BOTTOM);
		lone.setFont(new Font("verdana",Font.PLAIN,15));	
		lall.setFont(new Font("verdana",Font.PLAIN,15));	
		lone.setForeground(Color.green);
		lall.setForeground(Color.green);
		fr.add(lone);
		fr.add(lall);		
		lone.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lone.setIcon(new ImageIcon("images/one2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				lone.setIcon(new ImageIcon("images/one1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				fr.setEnabled(false);
				new onebyone(fr);
			}
		});
		lall.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lall.setIcon(new ImageIcon("images/all2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				lall.setIcon(new ImageIcon("images/all1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				fr.setEnabled(false);
				new allinone(fr);
			}
		});
		fr.setUndecorated(true);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}