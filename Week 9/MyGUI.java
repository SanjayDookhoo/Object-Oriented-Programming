import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
	
public class MyGUI extends JFrame
{
	JTextField txt_fName;
	JTextField txt_lName;
	JTextArea txt_address;
	JComboBox cb_countries;
	JRadioButton rbtn_yes, rbtn_no;
	JCheckBox[] products;
		
	public MyGUI() {
		setTitle("My Form");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);		
		setResizable(false);	// ensure that window cannot be resized
		setLocationRelativeTo(null);	// position window at center of screen
					
		JPanel mainPanel = new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		mainPanel.setLayout(flowLayout);
		
		JPanel outerPanel = new JPanel();
		GridLayout outerLayout = new GridLayout(3,1);
		outerPanel.setLayout(outerLayout);
		
		/*********************Top Panel***************************/
		JPanel topPanel = new JPanel();
		GridLayout topLayout = new GridLayout(2,2);	
		topPanel.setLayout(topLayout);
		
		JLabel lbl_fName = new JLabel("First Name:");
		topPanel.add(lbl_fName);
		
		txt_fName = new JTextField(15);
		topPanel.add(txt_fName);
		
		JLabel lbl_lName = new JLabel("Last Name:");
		topPanel.add(lbl_lName);
		
		txt_lName = new JTextField(15);
		topPanel.add(txt_lName);
		
		outerPanel.add(topPanel);
		
		
		/********************Address Panel****************************/
		JPanel addressPanel = new JPanel();
		GridLayout addressLayout = new GridLayout(2,1);	
		addressPanel.setLayout(addressLayout);
		
		JLabel lbl_address = new JLabel("Address:");
		addressPanel.add(lbl_address);		
		
		txt_address = new JTextArea(5, 10);
		JScrollPane sp = new JScrollPane(txt_address); 
		addressPanel.add(sp);
		
		outerPanel.add(addressPanel);
		
		
		/***********************Bottom Panel**************************/
		JPanel bottomPanel = new JPanel();
		GridLayout bottomLayout = new GridLayout(5,2);	
		bottomPanel.setLayout(bottomLayout);
		
		JLabel lbl_country = new JLabel("Country:");
		bottomPanel.add(lbl_country);
		
		
		String[] countries = {"Barbados", "Grenada", "Guyana", "Jamaica", "Trinidad & Tobago"};
		cb_countries = new JComboBox(countries);
		cb_countries.setSelectedItem(4);
		bottomPanel.add(cb_countries);
		
		/******Alternative implementation of combo box
		
		JComboBox cb_countries = new JComboBox();
		cb_countries.addItem("Barbados");
		cb_countries.addItem("Grenada");
		cb_countries.addItem("Guyana");
		cb_countries.addItem("Jamaica");*/
		
		JLabel lbl_getEmails = new JLabel("Would you like to receive update emails?");
		bottomPanel.add(lbl_getEmails);
		
		// declare radio buttons

		
		ButtonGroup email;	   // to group radio buttons together

		// create radio buttons

		rbtn_yes = new JRadioButton("Yes");
		rbtn_no = new JRadioButton("No");
		email = new ButtonGroup();// create instance of ButtonGroup
		
		email.add(rbtn_yes);
		email.add(rbtn_no);
		rbtn_yes.setSelected(true);
		
		JPanel radioPanel = new JPanel();
		GridLayout radioLayout = new GridLayout(1,2);
		radioPanel.setLayout(radioLayout);	
			
		radioPanel.add(rbtn_yes);
		radioPanel.add(rbtn_no);
		bottomPanel.add(radioPanel);
		
		
		JLabel lbl_products = new JLabel("Select your products?");
		bottomPanel.add(lbl_products);
		
		products = new JCheckBox[6];	
							// array of 6 check boxes

		products[0] = new JCheckBox("Pencil");
		products[1] = new JCheckBox("Notebook");
		products[2] = new JCheckBox("Bag");
		products[3] = new JCheckBox("Pen");
		products[4] = new JCheckBox("Ruler");
		products[5] = new JCheckBox("Eraser");
		
		JPanel cboxPanel = new JPanel();
		GridLayout cboxLayout = new GridLayout(2,3);
		cboxPanel.setLayout(cboxLayout);
		
		for (int i=0; i<products.length; i++){
			cboxPanel.add(products[i]);
		}
		bottomPanel.add(cboxPanel);
		
		JButton btn_submit = new JButton("Submit");
		bottomPanel.add(btn_submit);
		
		outerPanel.add(bottomPanel);
		mainPanel.add(outerPanel);
		add(mainPanel);
		setVisible(true);	// make the window visible
   }	
      
   
	public static void main(String[] args) {
		JFrame window = new MyGUI();	// MyGUI "is-a" JFrame, by inheritance
	}

}
