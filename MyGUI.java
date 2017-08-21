import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
	
public class MyGUI extends JFrame
{
	public MyGUI() {
		setTitle("My Form");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 700);		
		setResizable(false);	// ensure that window cannot be resized
		setLocationRelativeTo(null);	// position window at center of screen
		
		JPanel mainPanel = new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		mainPanel.setLayout(flowLayout);
		
		JPanel outerPanel = new JPanel();
		GridLayout outerLayout = new GridLayout(1,1);
		outerPanel.setLayout(outerLayout);
		
		/*********************Top Panel***************************/
		JPanel topPanel = new JPanel();
		GridLayout topLayout = new GridLayout(2,2);	
		topPanel.setLayout(topLayout);
		
		JLabel lbl_fName = new JLabel("First Name:");
		topPanel.add(lbl_fName);
		
		JTextField txt_fName = new JTextField(15);
		topPanel.add(txt_fName);
		
		JLabel lbl_lName = new JLabel("Last Name:");
		topPanel.add(lbl_lName);
		
		JTextField txt_lName = new JTextField(15);
		topPanel.add(txt_lName);
		
		outerPanel.add(topPanel);
		
		/*********************************************************/
		
		mainPanel.add(outerPanel);
		add(mainPanel);
		setVisible(true);	// make the window visible
   }	
   
   
	public static void main(String[] args) {
		JFrame window = new MyGUI();	// MyGUI "is-a" JFrame, by inheritance
	}

}
