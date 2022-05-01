import java.awt.*;
import javax.swing.*;

class splash{
	JFrame fr;
	JLabel name;
	JProgressBar jb;

	public splash(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();		
		fr=new JFrame();
		fr.setContentPane(new JLabel(new ImageIcon("images/splash.gif")));
		fr.setUndecorated(true);
		fr.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
		fr.setBounds((dim.width-768)/2 , (dim.height-370)/2,768,370);
				
		name=new JLabel(new ImageIcon("images/name.png"));
		name.setBounds(259,40,250,150);
		fr.add(name);
		
		jb=new JProgressBar(0,100);
		jb.setBounds(320,200,120,5);
		jb.setOpaque(false);
		jb.setForeground(Color.BLUE);
		jb.setBorderPainted(false);
		fr.add(jb);
		
		fr.setVisible(true);
		for(int i=0;i<=100;i++){
			jb.setValue(i);
			try{Thread.sleep(50);}catch(Exception e){}
		}
		fr.dispose();
		new login();	
	}
	public static void main(String args[]){
		new splash();
	}
}