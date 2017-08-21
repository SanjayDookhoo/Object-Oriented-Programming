import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
	
public class MiniWindow extends JFrame
{
	WindowsGUI parentWindow;
	public MiniWindow(){
		setTitle("Mini Window");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500, 400);		
		setResizable(false);	// ensure that window cannot be resized
		setLocationRelativeTo(null);	// position window at center of screen
		
		JLabel label = new JLabel("Hello, this is a mini window");
		add(label);
		
		
		setVisible(true);
	}	
}