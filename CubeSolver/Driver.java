package CubeSolver;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Driver {
	public static void main(String[] args) {		
		//initialization of objects required for the user interface
		JFrame f = new JFrame();
		Panel p = new Panel();
		JButton left = new JButton("<");
		JButton right = new JButton(">");
		JButton scram = new JButton("Scramble");
		JButton reset = new JButton("Reseet");

		//setting the font, size and location of the buttons
		left.setFont(new Font("Arial", Font.PLAIN, 40));
		right.setFont(new Font("Arial", Font.PLAIN, 40));
		scram.setFont(new Font("Arial", Font.PLAIN, 20));
		reset.setFont(new Font("Arial", Font.PLAIN, 20));
		left.setBounds(350,10,100,50);
		scram.setBounds(450,10,150,50);
		reset.setBounds(600,10,150,50);
		right.setBounds(750,10,100,50);

		//listener for right arrow button
		right.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				p.moveRight();
				p.repaint();
			}  
		});  

		//listener for the left arrow button
		left.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				p.moveLeft();
				p.repaint();
			}  
		});  

		//listener for scramble button
		scram.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
					p.scramble2();
					p.solve();
					p.repaint();
			}  
		});  

		//listener for reset button
		reset.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				p.reset();
				p.repaint();		
			}  
		});  
		
		//setting the panel into the frame, adding buttons to the frame, setting frame size, and displaying fram
		f.setContentPane(p);
		f.add(right);
		f.add(left);
		f.add(scram);
		f.add(reset);
		f.setSize(1226,1271);
		f.setLayout(null);
		f.setVisible(true);
	}

}