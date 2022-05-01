import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Slip extends JFrame
{
    	JFrame fr;
    	JLabel qr,logo,help;
    	public Slip(String bar_code_id)
	{

        	fr=this;
        	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        	this.setBounds((dim.width-300)/2,(dim.height-700)/2,300,500);
        	this.setTitle("Parking Ticket");
        	this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
        	this.setResizable(false);
        	this.setLayout(null);
        	this.addWindowListener(new WindowAdapter()
		{
            		public void windowClosed(WindowEvent we)
			{
            		    	fr.dispose();
            		}

	        });
        
		//System.out.println(bar_code_id);
        	//String data = QRRead.readCode(bar_code_id);
        	logo = new JLabel(new ImageIcon("images/name.png"));
        	logo.setBounds(25,30,250,150);
        	fr.add(logo);
        	qr = new JLabel(new ImageIcon("QRCodes/"+bar_code_id+".png"));
        	qr.setBounds(50,200,200,200);
        	fr.add(qr);
        
        	help=new JLabel("Please scan the above QR Code to know you details");
        	help.setBounds(0,420,300,30);
        	help.setHorizontalAlignment(JLabel.CENTER);
        	help.setFont(new Font("Georgia",Font.BOLD,10));
        	fr.add(help);
        	this.setVisible(true);
    	}
    
}