//STUDENT NAME: JAY ABI-SAAD
//STUDENT ID: 260801368

//Ladies, Gentlemen and whoever will correct me, I present you my little baby, my calculator.
//I worked very hard on it and I got a bit addicted to this assignment. Before letting you start the correcting
//I would like to specify the characteristics of my calculator:

//1) All unary operators (EXCEPT UNARY MINUS) must be applied AFTER inputting the number:
//example: (4)sin rather then sin(4), (2.5)ln rather than ln(2.5), and (4)sqrt rather than sqrt(4). HOWEVER for unary minus, -3 AND NOT 3-.
//This was made by design, because I personally believe that its more intuitive to input the number before the unary operator (except unary minus).
//An advantage of this method is that if you do an operation, ex. 2+2, the calculator returns 4. Instead of having to clear the calculator and write
//again sqrt4, you can simply press the sqrt button, and you'll be able to calculate (4)sqrt. (try it out :) ).

//2) Sin, cos, tan, arcsin, arccos, arctan, ln and square root can only be applied to a single number:
//example: you can do (4)sin, but you can't do (2+2)sin, you can do (2.5)ln, but can't do (5/2)ln and 
//you can do (4)sqrt, but can't do (2+2)sqrt.

//3) Toggle the degrees/radians button on the bottom left corner to switch from degrees to radians calculations.

//4) If you calculate a number with a lot of decimals, use the precision slider on the bottom to chose how much decimals you want to display.
// Default position of the slider is set to 6 decimals, however, you can choose to display from 1 to 16 decimals depending on your mood.

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.StringTokenizer;

public class JCalc extends JPanel implements ActionListener, KeyListener {

	public static void main (String [] args) {	//Main class defines and sets the frame of the window of the program.
		JFrame frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(new JCalc(), BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		JCalc calculator = new JCalc();
		calculator.setVisible(true);
	}

	private static final long serialVersionUID = 1L;	//Defines the dimensions of the window of the program.
	public static final int WIDTH = 333;
	public static final int HEIGHT = 703;

	private GridBagLayout layout;
	private GridBagConstraints gbc;

	private JButton[] numberButtons;
	private JButton[] operator_Buttons;
	private JTextField field;
	private JTextField decimal;
	private JSlider slider;
	private JToggleButton deg_rad;

	private int[][] numConstraints = new int[][] {	//Coordinates and dimensions of the number buttons contained in the grid layout.
		{0, 8, 1, 1},	//0
		{0, 7, 1, 1},	//1
		{1, 7, 1, 1},	//2
		{2, 7, 1, 1},	//3
		{0, 6, 1, 1},	//4
		{1, 6, 1, 1},	//5
		{2, 6, 1, 1},	//6
		{0, 5, 1, 1},	//7
		{1, 5, 1, 1},	//8
		{2, 5, 1, 1},	//9
	};

	private int[][] operator_Constraints = new int[][] {	//Coordinates and dimensions of the operator buttons contained in the grid layout.
		{1, 8, 1, 1},	//"."
		{2, 8, 1, 1},	//"="
		{3, 8, 1, 1},	//"+"
		{3, 7, 1, 1},	//"-"
		{3, 6, 1, 1},	//"×"
		{3, 5, 1, 1},	//"÷"
		{2, 3, 1, 1},   //"("
		{1, 3, 1, 1},	//")"
		{3, 2, 1, 1},	//"^"
		{0, 3, 1, 1},	//"C"
		{0, 4, 1, 1},	//"π"
		{1, 4, 1, 1},	//"e"
		{3, 3, 1, 1},	//"!"
		{3, 4, 1, 1},	//"√"
		{0, 1, 1, 1},	//"sin"
		{1, 1, 1, 1},	//"cos"
		{2, 1, 1, 1},	//"tan"
		{2, 4, 1, 1},	//"ln"
		{0, 2, 1, 1},	//"sin"
		{1, 2, 1, 1},	//"cos"
		{2, 2, 1, 1},	//"tan"
		{3, 1, 1, 1},	//"^2"
	};

	public JCalc() {

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();


		layout = new GridBagLayout();
		layout.columnWidths = new int[] {80, 80, 80, 80};	//Size (in pixels) of the width for each of the four column of the layout.
		layout.rowHeights = new int[] {80, 40, 40, 80, 80, 80, 80, 80, 80, 40};	//Size (in pixels) of the heights for each of the six rows of the layout.
		setLayout(layout);
		gbc = new GridBagConstraints();

		numberButtons = new JButton[10];	//Add the number buttons (with respect to their constraints) to the layout (grid).
		for(int i=0;i<numberButtons.length;i++) {
			numberButtons[i] = new JButton("" + i);
			numberButtons[i].addActionListener(this);
			gbc.gridx = numConstraints[i][0];
			gbc.gridy = numConstraints[i][1];
			gbc.gridwidth = numConstraints[i][2];
			gbc.gridheight = numConstraints[i][3];
			gbc.fill = GridBagConstraints.BOTH;
			gbc .insets = new Insets(2, 2, 2, 2); //Separates the buttons with 3 pixels from each side.
			add(numberButtons[i],gbc);
		}

		operator_Buttons = new JButton[22];	//Create and define the operator buttons.
		operator_Buttons[0] = new JButton(".");
		operator_Buttons[1] = new JButton("=");
		operator_Buttons[2] = new JButton("+");
		operator_Buttons[3] = new JButton("-");
		operator_Buttons[4] = new JButton("×");
		operator_Buttons[5] = new JButton("÷");
		operator_Buttons[6] = new JButton(")");
		operator_Buttons[7] = new JButton("(");
		operator_Buttons[8] = new JButton("^");
		operator_Buttons[9] = new JButton("C");
		operator_Buttons[10] = new JButton("π");
		operator_Buttons[11] = new JButton("e");
		operator_Buttons[12] = new JButton("!");
		operator_Buttons[13] = new JButton("√");
		operator_Buttons[14] = new JButton("sin");
		operator_Buttons[15] = new JButton("cos");
		operator_Buttons[16] = new JButton("tan");
		operator_Buttons[17] = new JButton("ln");
		operator_Buttons[18] = new JButton("sin\u207B\u00B9");	//arcsin
		operator_Buttons[19] = new JButton("cos\u207B\u00B9");	//arccos
		operator_Buttons[20] = new JButton("tan\u207B\u00B9");	//arctan
		operator_Buttons[21] = new JButton("\u00B1");	//unary minus

		for(int i = 0; i<operator_Buttons.length;i++) {	//Add the operator buttons (with respect to their constraints) to the layout (grid).
			gbc.gridx = operator_Constraints[i][0];
			gbc.gridy = operator_Constraints[i][1];
			gbc.gridwidth = operator_Constraints[i][2];
			gbc.gridheight = operator_Constraints[i][3];
			operator_Buttons[i].addActionListener(this);
			add(operator_Buttons[i],gbc);
			Font font2 = new Font("Helvetica Neue", Font.PLAIN, 30);	//Changes the font type, size and color.
			Font font3 = new Font("Helvetica Neue", Font.PLAIN, 18);
			Font font4 = new Font("Helvetica Neue", Font.PLAIN, 16);

			for (Component comp : getComponents()) {
				if (comp instanceof JButton) {
					((JButton)comp).setFont(font2);
					operator_Buttons[14].setFont(font4);	//Setting the font smaller for those puppies so their label text won't change the size of the buttons.
					operator_Buttons[15].setFont(font4);
					operator_Buttons[16].setFont(font4);
					operator_Buttons[18].setFont(font4);
					operator_Buttons[19].setFont(font4);
					operator_Buttons[20].setFont(font4);
					operator_Buttons[8].setFont(font3);
					operator_Buttons[21].setFont(font3);
				}
			}
		}

		//Creating and defining a JTextfield that will serve as the display of the calculator.
		field = new JTextField();
		field.setEditable(false);
		field.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));	//Changes the font type, size and colour.
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		add(field, gbc);

		//Creating and defining a JSlider that will be use to change the precision of the result.
		slider = new JSlider(1,16,6); //Minimum value of slider is 1, maximum is 16 and default value is 6.
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		slider.setSnapToTicks(true);
		add(slider, gbc);

		//Creating and defining a JTextfield that will show the live precision of the result.
		decimal = new JTextField();
		decimal.setEditable(false);
		decimal.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));	//Changes the font type, size and colour.
		gbc.gridx = 3;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 0;
		gbc .insets = new Insets(3, 3, 3, 3); //To space the keys by 3 pixels from each side.
		add(decimal, gbc);

		//Creating new toggle button that will toggle between degrees and radians mode.
		deg_rad = new JToggleButton();
		deg_rad.setText("deg");
		deg_rad.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));	//Changes the font type, size and colour.
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 0;
		gbc .insets = new Insets(3, 3, 3, 3); //To space the keys by 3 pixels from each side.
		add(deg_rad, gbc);

		//Listener updates the displayed value of the precision text field everytime the slider moves.
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				decimal.setText("   "+((JSlider)e.getSource()).getValue());
			}
		});

		//Listener changes the label of the degrees/radians toggle button from "deg" (degrees) to "rad" (radians).
		deg_rad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if(ev.getStateChange()==ItemEvent.SELECTED){
					deg_rad.setText("rad");
				} else if(ev.getStateChange()==ItemEvent.DESELECTED){
					deg_rad.setText("deg");
				}
			}
		});
	}

	Queue pre_input = new Queue(); //Initializing the new queue that will temporarly contain the numbers and the dot.
	Queue input = new Queue(); //Initializing the new queue that will contain the input values.
	Queue para = new Queue(); //Initializing the new queue that will temporarly contain the right parenthesis.
	Queue unary_minus = new Queue();	//Initializing the new queue that will contain the unary minus sign "m".

	//Runs when user interacts with the UI.
	public void actionPerformed(ActionEvent event) {

		try{

			Stack operator = new Stack(); //Initializing the new stack that will contain the operators.
			Stack postfix_evaluator = new Stack(); //Initializing stack that will be used in the evaluation of the postfix notation.
			Queue output = new Queue(); //Initializing the output queue that will output the final postfix notation.

			//If user clicked on the "0" number Jbutton, display it to the user and add it onto the pre_input queue.
			if(event.getSource() == numberButtons[0]) {
				field.setText(field.getText() + "0");
				pre_input.Enqueue("0");
			}

			//If user clicked on the "1" number Jbutton, display it to the user and add it onto the pre_input queue.
			if(event.getSource() == numberButtons[1]) {
				field.setText(field.getText() + "1");
				pre_input.Enqueue("1");
			}

			//If user clicked on the "2" number Jbutton, display it to the user and add it onto the pre_input queue.
			if(event.getSource() == numberButtons[2]) {
				field.setText(field.getText() + "2");
				pre_input.Enqueue("2");
			}

			//If user clicked on the "3" number Jbutton, display it to the user and add it onto the pre_input queue.
			if(event.getSource() == numberButtons[3]) {
				field.setText(field.getText() + "3");
				pre_input.Enqueue("3");
			}

			//If user clicked on the "4" number Jbutton, display it to the user and add it onto the pre_input queue.
			if(event.getSource() == numberButtons[4]) {
				field.setText(field.getText() + "4");
				pre_input.Enqueue("4");
			}

			//If user clicked on the "5" number Jbutton, display it to the user and add it onto the pre_input queue.
			if(event.getSource() == numberButtons[5]) {
				field.setText(field.getText() + "5");
				pre_input.Enqueue("5");
			}

			//If user clicked on the "6" number Jbutton, display it to the user and add it onto the pre_input queue.
			if(event.getSource() == numberButtons[6]) {
				field.setText(field.getText() + "6");
				pre_input.Enqueue("6");
			}

			//If user clicked on the "7" number Jbutton, display it to the user and add it onto the pre_input queue.
			if(event.getSource() == numberButtons[7]) {
				field.setText(field.getText() + "7");
				pre_input.Enqueue("7");
			}

			//If user clicked on the "8" number Jbutton, display it to the user and add it onto the pre_input queue.
			if(event.getSource() == numberButtons[8]) {
				field.setText(field.getText() + "8");
				pre_input.Enqueue("8");
			}

			//If user clicked on the "9" number Jbutton, display it to the user and add it onto the pre_input queue.
			if(event.getSource() == numberButtons[9]) {
				field.setText(field.getText() + "9");
				pre_input.Enqueue("9");
			}

			//If user clicked on the "." operator Jbutton, display it to the user and add it onto the pre_input queue.
			if(event.getSource() == operator_Buttons[0]) {
				field.setText(field.getText() + ".");
				pre_input.Enqueue(".");
			}

			//If user clicked on the "+" operator Jbutton, display it to the user and execute the following:
			if(event.getSource() == operator_Buttons[2]) {
				field.setText(field.getText() + "+");
				while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
					String pre_value="";
					for(int i=pre_input.size();i!=0;--i) {
						pre_value=pre_value+pre_input.Dequeue();
					}
					input.Enqueue(pre_value);	//Enqueue the sum of the strings onto the input queue.
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
				input.Enqueue("+");	//Enqueue the operator onto the input queue.
			}

			//If user clicked on the "-" operator Jbutton, display it to the user and execute the following:
			if(event.getSource() == operator_Buttons[3]) {
				field.setText(field.getText() + "-");
				while(!pre_input.isEmpty()){	//While pre_input is not empty, dequeue its elements and sum them up.
					String pre_value="";
					for(int i=pre_input.size();i!=0;--i) {
						pre_value=pre_value+pre_input.Dequeue();
					}
					input.Enqueue(pre_value);	//Enqueue the sum of the strings onto the input queue.
				}
				if(!para.isEmpty()) {
					String right_para = para.Dequeue();	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					input.Enqueue(right_para);
				}
				input.Enqueue("-");	//Enqueue the operator onto the input queue.
			}

			//If user clicked on the "×" operator Jbutton, display it to the user and execute the following:
			if(event.getSource() == operator_Buttons[4]) {
				field.setText(field.getText() + "×");
				while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
					String pre_value="";
					for(int i=pre_input.size();i!=0;--i){
						pre_value=pre_value+pre_input.Dequeue();
					}
					input.Enqueue(pre_value);	//Enqueue the sum of the strings onto the input queue.
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
				input.Enqueue("*");	//Enqueue the operator onto the input queue.
			}

			//If user clicked on the "÷" operator Jbutton, display it to the user and execute the following:
			if(event.getSource() == operator_Buttons[5]) {
				field.setText(field.getText() + "÷");
				while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
					String pre_value="";
					for(int i=pre_input.size();i!=0;--i) {
						pre_value=pre_value+pre_input.Dequeue();
					}
					input.Enqueue(pre_value);	//Enqueue the sum of the strings onto the input queue.
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
				input.Enqueue("/");	//Enqueue the operator onto the input queue.
			}

			//If user clicked on the ")" operator Jbutton, display it to the user and add it onto the right parenthesis (para) queue.
			if(event.getSource() == operator_Buttons[6]) {
				field.setText(field.getText() + ")");
				para.Enqueue(")");
			}


			//If user clicked on the "(" operator Jbutton, display it to the user and add it onto the input queue.
			if(event.getSource() == operator_Buttons[7]) {
				field.setText(field.getText() + "(");
				input.Enqueue("(");
			}

			//If user clicked on the "^" operator Jbutton, display it to the user and execute the following:
			if(event.getSource() == operator_Buttons[8]) {
				field.setText(field.getText() + "^");
				while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
					String pre_value="";
					for(int i=pre_input.size();i!=0;--i) {
						pre_value=pre_value+pre_input.Dequeue();
					}
					input.Enqueue(pre_value);	//Enqueue the sum of the strings onto the input queue.
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
				input.Enqueue("^");	//Enqueue the operator onto the input queue.
			}

			//If user clicked on the "C" clear operator Jbutton, Dequeue and pop ALL of the stacks and queues until they are ALL empty. .
			if(event.getSource() == operator_Buttons[9]) {
				field.setText("");
				while(!pre_input.isEmpty()) {
					pre_input.Dequeue();
				}
				while(!input.isEmpty()) {
					input.Dequeue();
				}
				while(!output.isEmpty()) {
					output.Dequeue();
				}
				while(!para.isEmpty()){
					para.Dequeue();
				}
				while(!unary_minus.isEmpty()) {
					unary_minus.Dequeue();
				}
				while(!operator.isEmpty()) {
					operator.pop();
				}
				while(!postfix_evaluator.isEmpty()) {
					postfix_evaluator.pop();
				}
			}

			//If user clicked on the "10" operator Jbutton, display PI to the user and add it onto the pre_input queue.
			if(event.getSource() == operator_Buttons[10]) {
				Double a = Math.PI;
				String pre_value = Double.toString(a);
				field.setText(field.getText() + "π");
				pre_input.Enqueue(pre_value);
			}

			//If user clicked on the "11" operator Jbutton, display e to the user and add it onto the pre_input queue.
			if(event.getSource() == operator_Buttons[11]) {
				Double a = Math.E;
				String pre_value = Double.toString(a);
				field.setText(field.getText() + "e");
				pre_input.Enqueue(pre_value);
			}

			//If user clicked on the "12" operator Jbutton, execute the following:
			if(event.getSource() == operator_Buttons[12]) {
				field.setText(field.getText() + "!");
				if(pre_input.isEmpty()) {
					String pre_value = input.Dequeue();
					double num1 = Double.parseDouble(pre_value);	
					if (num1==0.0) {	//If user entered the factorial of 0, always enqueue 1.
						input.Enqueue("1");
					}
					else if(num1!=0.0){	//In all other case, execute the following:
						Double factorial = num1;
						for(Double i = (num1-1.0); i > 1; i--) {
							factorial = factorial * i;
						} 
						String value = Double.toString(factorial);
						input.Enqueue(value); 
					}
				}
				if(!pre_input.isEmpty()) {
					while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
						String pre_value="";
						for(int i=pre_input.size();i!=0;--i) {
							pre_value=pre_value+pre_input.Dequeue();
						}
						double num1 = Double.parseDouble(pre_value);	
						if (num1==0.0) {	//If user entered the factorial of 0, always enqueue 1.
							input.Enqueue("1");
						}
						else if(num1!=0.0) {	//In all other case, execute the following:
							Double factorial = num1;
							for(Double i = (num1-1.0); i > 1; i--) {
								factorial = factorial * i;
							} 
							String value = Double.toString(factorial);
							input.Enqueue(value); 
						}
					}
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
			}

			//If user clicked on the "13" operator Jbutton, find the root of the previous number and enqueue it to the input queue.:
			if(event.getSource() == operator_Buttons[13]) {
				field.setText(field.getText() + "√");
				if(pre_input.isEmpty()) {
					String a = input.Dequeue();
					double num1 = Double.parseDouble(a);
					double root = Math.sqrt(num1);
					String value = Double.toString(root);
					input.Enqueue(value); 
				}
				else if(!pre_input.isEmpty()) {
					while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
						String pre_value="";
						for(int i=pre_input.size();i!=0;--i) {
							pre_value=pre_value+pre_input.Dequeue();
						}
						double num1 = Double.parseDouble(pre_value);	
						double root = Math.sqrt(num1);
						String value = Double.toString(root);
						input.Enqueue(value);
					}
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
			}

			//For operator_buttons 14(sin),15(cos),16(tan),18(arcsin),19(arccos) and 20(arctan), it's the exact same logic, so I just annotated operator_button14.
			//If user clicked on the "14" operator Jbutton, execute:
			if(event.getSource() == operator_Buttons[14]) {
				field.setText(field.getText() + "sin");
				if(pre_input.isEmpty()) {
					String a = input.Dequeue();	//Dequeue last number from the input queue if pre_input is empty.
					double num1 = Double.parseDouble(a);
					if(deg_rad.getText()=="rad") {	//If the toggle button's label is set to radians.
						double sin = Math.sin(num1);	
						if(sin<0)	{	//My calculator recognizes "m" for unary minus, so if result is negative, I remove the "-" and replace it with a "m".
							double neg_sin = sin*-1;
							String neg_sin_string = Double.toString(neg_sin);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_sin_string);
						}
						else if(sin>0) {	//If result is positibe, simply enqueue it to the input queue.
							String value = Double.toString(sin);
							input.Enqueue(value);
						}
					}
					else if(deg_rad.getText()=="deg") {	//If the toggle button's label is set to degrees.
						double sin = Math.sin(Math.toDegrees(num1));	
						if(sin<0) {	//My calculator recognizes "m" for unary minus, so if result is negative, I remove the "-" and replace it with a "m".
							double neg_sin = sin*-1;
							String neg_sin_string = Double.toString(neg_sin);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_sin_string);
						}
						else if(sin>0) {	//If result is positibe, simply enqueue it to the input queue.
							String value = Double.toString(sin);
							input.Enqueue(value);
						}
					}
				}
				//Same logic as the previous part, excepts it takes its values from the pre_input queue instead of the input queue.
				else if(!pre_input.isEmpty()) {
					while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
						String pre_value="";
						for(int i=pre_input.size();i!=0;--i) {
							pre_value=pre_value+pre_input.Dequeue();
						}
						double num1 = Double.parseDouble(pre_value);	
						if(deg_rad.getText()=="rad") {
							double sin = Math.sin(num1);	
							if(sin<0){
								double neg_sin = sin*-1;
								String neg_sin_string = Double.toString(neg_sin);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_sin_string);
							}
							else if(sin>0){
								String value = Double.toString(sin);
								input.Enqueue(value);
							}
						}
						else if(deg_rad.getText()=="deg") {
							double sin = Math.sin(Math.toDegrees(num1));	
							if(sin<0){
								double neg_sin = sin*-1;
								String neg_sin_string = Double.toString(neg_sin);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_sin_string);
							}
							else if(sin>0){
								String value = Double.toString(sin);
								input.Enqueue(value);
							}
						}
					}
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
			}

			//If user clicked on the "15" operator Jbutton, execute:
			if(event.getSource() == operator_Buttons[15]) {
				field.setText(field.getText() + "cos");
				if(pre_input.isEmpty()){
					String a = input.Dequeue();	//Dequeue last number from the input queue if pre_input is empty.
					double num1 = Double.parseDouble(a);
					if(deg_rad.getText()=="rad") {
						double cos = Math.cos(num1);
						if(cos<0) {
							double neg_cos = cos*-1;
							String neg_cos_string = Double.toString(neg_cos);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_cos_string);
						}
						else if(cos>0) {
							String value = Double.toString(cos);
							input.Enqueue(value);
						}
					}
					else if(deg_rad.getText()=="deg") {
						double cos = Math.cos(Math.toDegrees(num1));	
						if(cos<0) {
							double neg_cos = cos*-1;
							String neg_cos_string = Double.toString(neg_cos);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_cos_string);
						}
						else if(cos>0) {
							String value = Double.toString(cos);
							input.Enqueue(value);
						}
					}
				}
				else if(!pre_input.isEmpty()) {
					while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
						String pre_value="";
						for(int i=pre_input.size();i!=0;--i) {
							pre_value=pre_value+pre_input.Dequeue();
						}
						double num1 = Double.parseDouble(pre_value);	
						if(deg_rad.getText()=="rad") {
							double cos = Math.cos(num1);	
							if(cos<0){
								double neg_cos = cos*-1;
								String neg_cos_string = Double.toString(neg_cos);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_cos_string);
							}
							else if(cos>0) {
								String value = Double.toString(cos);
								input.Enqueue(value);
							}
						}
						else if(deg_rad.getText()=="deg") {
							double cos = Math.cos(Math.toDegrees(num1));	
							if(cos<0) {
								double neg_cos = cos*-1;
								String neg_cos_string = Double.toString(neg_cos);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_cos_string);
							}
							else if(cos>0) {
								String value = Double.toString(cos);
								input.Enqueue(value);
							}
						}
					}
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
			}

			//If user clicked on the "16" operator Jbutton, execute:
			if(event.getSource() == operator_Buttons[16]) {
				field.setText(field.getText() + "tan");
				if(pre_input.isEmpty()) {
					String a = input.Dequeue();	//Dequeue last number from the input queue if pre_input is empty.
					double num1 = Double.parseDouble(a);
					if(deg_rad.getText()=="rad") {
						double tan = Math.tan(num1);	
						if(tan<0){
							double neg_tan = tan*-1;
							String neg_tan_string = Double.toString(neg_tan);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_tan_string);
						}
						else if(tan>0) {
							String value = Double.toString(tan);
							input.Enqueue(value);
						}
					}
					else if(deg_rad.getText()=="deg") {
						double tan = Math.tan(Math.toDegrees(num1));	
						if(tan<0){
							double neg_tan = tan*-1;
							String neg_tan_string = Double.toString(neg_tan);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_tan_string);
						}
						else if(tan>0) {
							String value = Double.toString(tan);
							input.Enqueue(value);
						}
					}
				}
				else if(!pre_input.isEmpty()) {
					while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
						String pre_value="";
						for(int i=pre_input.size();i!=0;--i) {
							pre_value=pre_value+pre_input.Dequeue();
						}
						double num1 = Double.parseDouble(pre_value);	
						if(deg_rad.getText()=="rad") {
							double tan = Math.tan(num1);	
							if(tan<0) {
								double neg_tan = tan*-1;
								String neg_tan_string = Double.toString(neg_tan);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_tan_string);
							}
							else if(tan>0) {
								String value = Double.toString(tan);
								input.Enqueue(value);
							}
						}
						else if(deg_rad.getText()=="deg") {
							double tan = Math.tan(Math.toDegrees(num1));	
							if(tan<0){
								double neg_tan = tan*-1;
								String neg_tan_string = Double.toString(neg_tan);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_tan_string);
							}
							else if(tan>0) {
								String value = Double.toString(tan);
								input.Enqueue(value);
							}
						}
					}
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
			}

			//If user clicked on the "17" operator Jbutton, find the ln of the previous number and enqueue it to the input queue.:
			if(event.getSource() == operator_Buttons[17]) {
				field.setText(field.getText() + "ln");
				if(pre_input.isEmpty()) {
					String a = input.Dequeue();
					double num1 = Double.parseDouble(a);
					double ln = Math.log(num1);	//Converts from radians to strings.
					String value = Double.toString(ln);
					input.Enqueue(value); 
				}
				else if(!pre_input.isEmpty()) {
					while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
						String pre_value="";
						for(int i=pre_input.size();i!=0;--i) {
							pre_value=pre_value+pre_input.Dequeue();
						}
						double num1 = Double.parseDouble(pre_value);	
						double ln = Math.log(num1);	//Converts from radians to strings.
						String value = Double.toString(ln);
						input.Enqueue(value);
					}
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
			}

			//If user clicked on the "18" operator Jbutton, execute:
			if(event.getSource() == operator_Buttons[18]) {
				field.setText(field.getText() + "sin\u207B\u00B9");
				if(pre_input.isEmpty()) {
					String a = input.Dequeue();	//Dequeue last number from the input queue if pre_input is empty.
					double num1 = Double.parseDouble(a);
					if(deg_rad.getText()=="rad") {
						double arcsin = Math.asin(num1);	
						if(arcsin<0) {
							double neg_arcsin = arcsin*-1;
							String neg_arcsin_string = Double.toString(neg_arcsin);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_arcsin_string);
						}
						else if(arcsin>0) {
							String value = Double.toString(arcsin);
							input.Enqueue(value);
						}
					}
					else if(deg_rad.getText()=="deg") {
						double arcsin = Math.asin(Math.toDegrees(num1));	
						if(arcsin<0){
							double neg_arcsin = arcsin*-1;
							String neg_arcsin_string = Double.toString(neg_arcsin);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_arcsin_string);
						}
						else if(arcsin>0) {
							String value = Double.toString(arcsin);
							input.Enqueue(value);
						}
					}
				}
				else if(!pre_input.isEmpty()) {
					while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
						String pre_value="";
						for(int i=pre_input.size();i!=0;--i) {
							pre_value=pre_value+pre_input.Dequeue();
						}
						double num1 = Double.parseDouble(pre_value);	
						if(deg_rad.getText()=="rad") {
							double arcsin = Math.asin(num1);	
							if(arcsin<0) {
								double neg_arcsin = arcsin*-1;
								String neg_arcsin_string = Double.toString(neg_arcsin);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_arcsin_string);
							}
							else if(arcsin>0) {
								String value = Double.toString(arcsin);
								input.Enqueue(value);
							}
						}
						else if(deg_rad.getText()=="deg") {
							double arcsin = Math.asin(Math.toDegrees(num1));	
							if(arcsin<0) {
								double neg_arcsin = arcsin*-1;
								String neg_arcsin_string = Double.toString(neg_arcsin);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_arcsin_string);
							}
							else if(arcsin>0) {
								String value = Double.toString(arcsin);
								input.Enqueue(value);
							}
						}
					}
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
			}

			//If user clicked on the "19" operator Jbutton, execute
			if(event.getSource() == operator_Buttons[19]) {
				field.setText(field.getText() + "cos\u207B\u00B9");
				if(pre_input.isEmpty()){
					String a = input.Dequeue();	//Dequeue last number from the input queue if pre_input is empty.
					double num1 = Double.parseDouble(a);
					if(deg_rad.getText()=="rad"){
						double arccos = Math.acos(num1);	
						if(arccos<0) {
							double neg_arccos = arccos*-1;
							String neg_arccos_string = Double.toString(neg_arccos);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_arccos_string);
						}
						else if(arccos>0) {
							String value = Double.toString(arccos);
							input.Enqueue(value);
						}
					}
					else if(deg_rad.getText()=="deg") {
						double arccos = Math.acos(Math.toDegrees(num1));	
						if(arccos<0) {
							double neg_arccos = arccos*-1;
							String neg_arccos_string = Double.toString(neg_arccos);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_arccos_string);
						}
						else if(arccos>0) {
							String value = Double.toString(arccos);
							input.Enqueue(value);
						}
					}
				}
				else if(!pre_input.isEmpty()) {
					while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
						String pre_value="";
						for(int i=pre_input.size();i!=0;--i) {
							pre_value=pre_value+pre_input.Dequeue();
						}
						double num1 = Double.parseDouble(pre_value);	
						if(deg_rad.getText()=="rad") {
							double arccos = Math.acos(num1);	
							if(arccos<0) {
								double neg_arccos = arccos*-1;
								String neg_arccos_string = Double.toString(neg_arccos);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_arccos_string);
							}
							else if(arccos>0) {
								String value = Double.toString(arccos);
								input.Enqueue(value);
							}
						}
						else if(deg_rad.getText()=="deg") {
							double arccos = Math.acos(Math.toDegrees(num1));	
							if(arccos<0) {
								double neg_arccos = arccos*-1;
								String neg_arccos_string = Double.toString(neg_arccos);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_arccos_string);
							}
							else if(arccos>0) {
								String value = Double.toString(arccos);
								input.Enqueue(value);
							}
						}
					}
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
			}

			//If user clicked on the "20" operator Jbutton, execute:
			if(event.getSource() == operator_Buttons[20]) {
				field.setText(field.getText() + "tan\u207B\u00B9");
				if(pre_input.isEmpty()) {
					String a = input.Dequeue();	//Dequeue last number from the input queue if pre_input is empty.
					double num1 = Double.parseDouble(a);
					if(deg_rad.getText()=="rad") {
						double arctan = Math.atan(num1);	
						if(arctan<0) {
							double neg_arctan = arctan*-1;
							String neg_arctan_string = Double.toString(neg_arctan);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_arctan_string);
						}
						else if(arctan>0) {
							String value = Double.toString(arctan);
							input.Enqueue(value);
						}
					}
					else if(deg_rad.getText()=="deg") {
						double arctan = Math.atan(Math.toDegrees(num1));	
						if(arctan<0) {
							double neg_arctan = arctan*-1;
							String neg_arctan_string = Double.toString(neg_arctan);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_arctan_string);
						}
						else if(arctan>0) {
							String value = Double.toString(arctan);
							input.Enqueue(value);
						}
					}
				}
				else if(!pre_input.isEmpty()) {
					while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
						String pre_value="";
						for(int i=pre_input.size();i!=0;--i) {
							pre_value=pre_value+pre_input.Dequeue();
						}
						double num1 = Double.parseDouble(pre_value);	
						if(deg_rad.getText()=="rad") {
							double arctan = Math.atan(num1);	
							if(arctan<0) {
								double neg_arctan = arctan*-1;
								String neg_arctan_string = Double.toString(neg_arctan);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_arctan_string);
							}
							else if(arctan>0) {
								String value = Double.toString(arctan);
								input.Enqueue(value);
							}
						}
						else if(deg_rad.getText()=="deg") {
							double arctan = Math.atan(Math.toDegrees(num1));	
							if(arctan<0) {
								double neg_arctan = arctan*-1;
								String neg_arctan_string = Double.toString(neg_arctan);
								String neg_identifier_m = "m";
								input.Enqueue(neg_identifier_m);
								input.Enqueue(neg_arctan_string);
							}
							else if(arctan>0) {
								String value = Double.toString(arctan);
								input.Enqueue(value);
							}
						}
					}
				}
				if(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}
			}

			//If user clicked on the "21" operator Jbutton, add "m" to the input queue to symbolize the unary minus.
			if(event.getSource() == operator_Buttons[21]) {
				field.setText(field.getText() + "-");
				input.Enqueue("m");
			}

			//If user clicked on the "=" Jbutton, execute the following:
			if(event.getSource() == operator_Buttons[1]) {

				while(!pre_input.isEmpty()) {	//While pre_input is not empty, dequeue its elements and sum them up.
					String pre_value="";
					for(int i=pre_input.size();i!=0;--i) {	//Enqueue the sum of the strings onto the input queue.
						pre_value=pre_value+pre_input.Dequeue();
					}
					input.Enqueue(pre_value);
				}
				while(!para.isEmpty()) {	//If the para queue contains a right parenthesis, dequeue it and add it onto the input queue.
					String right_para = para.Dequeue();
					input.Enqueue(right_para);
				}

				//While the input queue is not empty, execute the following:
				while(!input.isEmpty()) {

					String str = input.Dequeue(); //Everytime the loop iterates, Dequeue an element from the input queue.
					StringTokenizer st = new StringTokenizer(str,"()+-*/^m",true);
					String token = st.nextToken();
					//If the next token to be read is equal to *,/,+,-,^: the next token is an operator.
					if (token.equals("*") || token.equals("+") || token.equals("-") || token.equals("/") || token.equals("^")) {
						//Assigning precedence (0 or 1, see details in precedence function below)
						//to next operator token that is being read.
						int p = operatorPrecedence(token);
						//While stack is not empty, AND top of operator stack is not a left bracket AND precedence assigned to operator
						//on the top of stack is greater or equal to the precedence assigned to the next operator
						//token that is being read, pop the top operator and enqueue it to the output queue.
						while (!operator.isEmpty() && !operator.peek().equals("(") && operatorPrecedence(operator.peek()) >= p) {
							String op = operator.pop();
							output.Enqueue(op);
						}
						operator.push(token); //Else, push the operator to the operator stack.
					}

					//if token is "m", then it's a unary minus so I push a "-" to the unary minus queue.
					else if(token.equals("m")) {
						unary_minus.Enqueue("-");
					}

					//If operator is a left bracket, push it on the operator stack.
					else if(token.equals("(")) {
						operator.push(token);
					}

					//If operator is right bracket, while the top of operator stack is not a left bracket,
					//and operator stack is not empty, pop items from operator stack onto output queue.
					else if(token.equals(")")) {
						String topStr = operator.peek();
						while(!topStr.equals("(") && topStr!= null) {
							String op = operator.pop();
							output.Enqueue(op);
							if(operator.isEmpty())
								break;
							else
								topStr = operator.peek();
						}
						//pop the left bracket from the stack and let it perish in hell.
						operator.pop();
					} //At this point, all brackets (left and right) have been discarded from the operator stack.

					//Else if the next token to be read is NOT equal to *,/,+,-,^ then the next token is a number.
					else if (!token.equals("*") || !token.equals("+") || !token.equals("-") || !token.equals("/") || !token.equals("^") ) {
						output.Enqueue(token); //Push number to the output queue.

						//If the unary minus queue is not empty, then dequeue the output queue from the back (last string that was added),
						//add a "m" (unary minus) string in front of it, which gives my program it's representation of a unary minus.
						//Enqueue the result to the output queue.
						if(!unary_minus.isEmpty()) {
							String m = unary_minus.Dequeue();
							String last_value_added = output.De();
							String unary_minus_conversion = m+last_value_added;
							output.Enqueue(unary_minus_conversion);
						}
					}
				}

				//If there are no more tokens to be read.
				if(input.isEmpty()) {
					//While the operator stack is not empty, empty it (pop) onto the output queue.
					while(!operator.isEmpty()) {
						String op = operator.pop();
						output.Enqueue(op);
					}
				}

				//output.print_my_queue();
				//While the output queue is not empty, execute the following while loop.
				while(!output.isEmpty()) {
					//output.print_my_queue();
					//Each time the loop runs, Initialize a string by removing the front element from the queue.
					String front=output.Dequeue();

					//If the front element dequeued is NOT equal to *,/,+,-,^ then the dequeued element is a number.
					if (!front.equals("*") && !front.equals("+") && !front.equals("-") && !front.equals("/") && !front.equals("^")) {
						postfix_evaluator.push(front); //Push the number to the postfix evaluating stack.
					}	

					//If the front element dequeued is equal to *,/,+,-,^ then the dequeued element is an operator.
					else if (front.equals("*")|| front.equals("+") || front.equals("-") || front.equals("/") || front.equals("^")) {

						//"Double.parseDoublet()" converts string to "double" data type.
						double num1 = Double.parseDouble(postfix_evaluator.pop()); //Pop the top number from the postfix evaluating stack.
						double num2 = Double.parseDouble(postfix_evaluator.pop()); //Pop the NEXT top number from the postfix evaluating stack.

						//If the dequeued element is a "+": add the two popped numbers and push the result onto the postfix evaluating stack.
						if (front.equals("+")) {
							double value = num2+num1;
							//Converts the result back to string format (Stack only accepts string format to be pushed).
							String lol = Double.toString(value);
							postfix_evaluator.push(lol);
						}

						//If the dequeued element is a "-": substract the two popped numbers and push the result onto the postfix evaluating stack.
						if (front.equals("-")) {
							Double value = num2-num1;
							//Converts the result back to string format (Stack only accepts string format to be pushed).
							String lol = Double.toString(value);
							postfix_evaluator.push(lol);
						}

						//If the dequeued element is a "*": multiply the two popped numbers and push the result onto the postfix evaluating stack.
						if (front.equals("*")) {
							Double value = num2*num1;
							//Converts the result back to string format (Stack only accepts string format to be pushed).
							String lol = Double.toString(value);
							postfix_evaluator.push(lol);
						}

						//If the dequeued element is a "/": divide the two popped numbers and push the result onto the postfix evaluating stack.
						if (front.equals("/")) {
							Double value = num2/num1;
							//Converts the result back to string format (Stack only accepts string format to be pushed).
							String lol = Double.toString(value);
							postfix_evaluator.push(lol);
						}

						//If the dequeued element is a "^":
						if (front.equals("^")) {
							//If the exponent is "0", then any number to the power of "0" is equal to "1".
							if(num1==0) {
								Double value = num2;
								//Converts the result back to string format (Stack only accepts string format to be pushed).
								String lol = Double.toString(value);
								postfix_evaluator.push(lol);
							}
							//If the exponent is not "0", execute:
							Double exp = StrictMath.pow(num2, num1);
							String lol = Double.toString(exp);
							postfix_evaluator.push(lol);
						}
					}
				}

				int decimal = slider.getValue();	//Get the current integer value of the precision JSlider.
				String unrounded_value = postfix_evaluator.pop();	//Get the answer from the postfix_evaluator stack.
				String rounded_value = unrounded_value;	//Makes a copy of the answer and convert it into a double.
				double rounded_result = Double.parseDouble(rounded_value);
				BigDecimal bd = new BigDecimal(rounded_result).setScale(decimal, RoundingMode.HALF_EVEN);	//Round the answer to specified decimal from the precision JSlider.
				rounded_result = bd.doubleValue();
				String result = Double.toString(rounded_result);
				field.setText(result);	//Display the answer to the user.

				//Again, my program representation of unary minus is "m", thus if the result is negative, I remove the minus and replace it 
				//with an "m", therefore my program can display and manipulate again the negative result.
				if(rounded_result<0) {
					double neg_result = rounded_result*-1;
					String neg_result_string = Double.toString(neg_result);
					String neg_identifier_m = "m";
					input.Enqueue(neg_identifier_m);
					input.Enqueue(neg_result_string);
				}

				//If result is positive, simply enqueue it back to the input queue so it can be reused in a new operation.
				else if(rounded_result>0) {
					input.Enqueue(unrounded_value);
				}

				while(!postfix_evaluator.isEmpty()) {	//Empty the postfix_evaluator stack.
					postfix_evaluator.pop();
				}
				while(!output.isEmpty()) {	//Empty the output queue.
					output.Dequeue();
				}

				//Listener updates the decimals of the answer displayed (and enqueued back to the input queue) whenever the user moves the JSlider.
				slider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						int decimal = slider.getValue();
						String unrounded_value = input.Dequeue();
						String rounded_value = unrounded_value;
						double rounded_result = Double.parseDouble(rounded_value);
						BigDecimal bd = new BigDecimal(rounded_result).setScale(decimal, RoundingMode.HALF_EVEN);
						rounded_result = bd.doubleValue();
						String result = Double.toString(rounded_result);
						field.setText(result);

						//Again, my program representation of unary minus is "m", thus if the result is negative, I remove the minus and replace it 
						//with an "m", therefore my program can display and manipulate again the negative result.
						if(rounded_result<0) {
							double neg_result = rounded_result*-1;
							String neg_result_string = Double.toString(neg_result);
							String neg_identifier_m = "m";
							input.Enqueue(neg_identifier_m);
							input.Enqueue(neg_result_string);
						}

						//If result is positive, simply enqueue it back to the input queue so it can be reused in a new operation.
						else if(rounded_result>0) {
							input.Enqueue(unrounded_value);
						}
					}
				});
			}
		}

		//If any error occurs, instead of displaying that ugly red text in the console, it simply says ERROR on the calculator's display.
		catch(Exception e) {
			field.setText("   "+"   "+"    "+"   "+"ERROR");
		}
	}

	//Assigns precedence to operator being read.
	private static int operatorPrecedence(String token) {

		//If operator being read is (,) return super duper mega boom boom pow very very high precedence (3).
		if(token.equals("(") || token.equals(")")) {
			return 3;
		}

		//If operator being read is ^ return higher precedence (2).
		else if (token.equals("^")) {
			return 2;
		}

		//If operator being read is *,/,% return medium precedence (1).
		else if (token.equals("*") || token.equals("/")) {
			return 1;	
		}

		//If operator being read is - or + return lower precedence (0).
		else if (token.equals("+") || token.equals("-")) {
			return 0;
		}

		//User has entered an "illegal" operator, which pisses my program off.
		else {
			throw new IllegalArgumentException("INVALID OPERATOR");
		}
	}

	//This last part of my code is to impliment the keyboard keys later. I plan to improve my calcualtor during winter break so I kept 
	//this code here. But for now it does nothing so pleas don't judge :)
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char a = e.getKeyChar();
		String b = Character.toString(a);
		pre_input.Enqueue(b);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}















