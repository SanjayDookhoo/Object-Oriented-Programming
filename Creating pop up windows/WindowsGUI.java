import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
	
public class WindowsGUI extends JFrame implements ActionListener
{
	private JButton btn_submit;
	
	public WindowsGUI(){
		setTitle("Main Windows");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);		
		setResizable(false);	// ensure that window cannot be resized
		setLocationRelativeTo(null);	// position window at center of screen
		
		btn_submit = new JButton("Submit");
		btn_submit.addActionListener(this);
		add(btn_submit);
		
		
		
		setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("Submit")){
			JFrame window = new MiniWindow();
		}
	}
	
	public static void main(String[] args) {
		JFrame window = new WindowsGUI();	// MyGUI "is-a" JFrame, by inheritance
	}

}