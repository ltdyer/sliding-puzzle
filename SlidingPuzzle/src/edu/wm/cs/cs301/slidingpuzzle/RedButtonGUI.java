package edu.wm.cs.cs301.slidingpuzzle; // classes are organized in packages, this line gives the one for our class here
// note that the . notation matches with a directory structure to organize our code
// such that RedButtonGUI.java is in a directory slidingpuzzle which is a subdirectory of cs301 and so forth.

// The following import statements provide prefixes for class names that show up in the code below
// for example we use the JButton class whose full name is javax.swing.JButton
// to save a lot of typing and keep code more readable we let the compiler know that
// it should do some autocompletion with the following prefixes to find code for other classes
// that we use 
// the * in javax.swing.* is another shortcut to avoid listing individual members of the 
// large swing package.
import java.awt.*; // awt = abstract windowing toolkit is a large package for graphics/user interface programming
import java.awt.event.*; // event is a particular package within awt about event handling like mouse clicks
import javax.swing.*; // swing is an additional graphics / user interface programming package

///// About comments in Java:
// comments are either for the rest of the line like for this line
// or they look like the text below
/**
 * This class contains a trivial GUI that extends from the classic Hello World example.
 * Its main purpose is to demo various concepts of Java.
 * Note that the huge amount of comments are for the sole purpose of explaining Java features,
 * this is in no way representative of regular code documentation
 * 
 * @author Kemper
 *
 */

// class is a keyword to describe a block of text start enclosed in { and } that has a name such as "RedButtonGUI". 
// A class can benefit from another existing class by saying it "extends" another class, here this class is JPanel.
// Inheritance means that RedButtonGUI obtains or "inherits" all code that is in the JPanel class.
// RedButtonGUI can then 
// a) extend the JPanel code by adding further attributes or methods to or
// b) vary an inherited method from JPanel by providing a new method of same signature (signature=methodname+parameters)
//
class RedButtonGUI extends JPanel {
	
	// Note the order of methods does not matter
	// We start with the main method here for educational purposes only. In most production code
	// it would be located more towards the bottom and the constructor method(s) would come first.
	// The main method is a natural starting point to start the execution of a program.
	// In Java each class can provide its own main method if one likes to do this
	// but usually very few classes in a code base have a main method.
	// It is always
	// public: which means anyone can call it from outside
	// static: which means one can call this method without instantiating an object of type RedButtonGUI
	// void: which means that the method does not return any result values
	// main: is the reserved name for this method
	// String[] args is a single parameter of array type with individual entries of type String
	public static void main(String[] args) {
		
		// The following line is the classic Hello World example code
		// the dot notation accesses something inside class "System" that is named "out" and that has a method "println" 
		// that we can call it this way
		// a string constant like Hello World! has " as delimiter
		// the ; shows the end of the command
		// the level of indentation has no semantics, it is only used to help a human reader
		System.out.println("Hello World!") ;
		
		// Let's move on to something more interesting and print the command line parameters instead
		// check if there are parameters given
		if (0 < args.length) {
			// then branch: lets show a for-loop and show how to access array entries
			for (int i = 0 ; i < args.length ; i++) {
				// note that variable i of type integer is defined for the scope of the loop body only!
				// arrays are zero indexed, so 0 is the starting value, we need to stop below the length of the array
				// an array has an attribute length that we can access with the dot notation args.length
				// We can declare a String variable inside the loop and assign a value to it that is concatenated ("+") from
				// other strings like the value of i, a constant string and the string in the args array at position i.
				String str = i + "-th command line parameter: " + args[i] ;
				System.out.println(str);
			}
			//as we passed the closing } local variables i and str are not defined anymore.
			// for demonstration purposes, let's show a while loop that does the same
			int i = 0 ; // yes, we can define integer variable i here and it is different to the one above
			// because of the local scope that the for-loop with { } creates.
			// a value of zero is the starting position in the array
			// i = 0 is the starting situation for the loop
			// i < args.length is the termination condition
			while (i < args.length) {
				System.out.println(i + "-th command line parameter: " + args[i]);
				i++ ; // progress made towards termination by incrementing variable i
				// i = i+1 ; // is a valid alternative to increment i
			}
			// there is also an do-while loop that checks the condition at the end
			// note that variable i is declared in this scope
			i = 0 ; // reset i
			do {
				System.out.println(i + "-th command line parameter: " + args[i]);
				i = i + 1 ;
			} while(i < args.length) ;
			// so these are all the loop structures, let's move on
		}
		else {
			// (optional) else branch, just for demonstration purposes
			System.out.println("No parameters given") ;
		}
		
		// let's do something if the user type "red" as a parameter
		if (0 < args.length && ("red".equals(args[0]) || "green".equals(args[0]))) {
			// instantiate an object of type JFrame
			// new is the operator to allocate memory large enough for a JFrame object (a data structure)
			// JFrame(String x) is a constructor that initializes the object (the data structure)
			// "The Red Button Frame" is the string parameter value that we want to give to that method
			// window is the name of the variable
			// JFrame is its type as the compiler keeps track of type information to enforce consistent use of variables
			// final means that this variable gets a value only once
			final JFrame window = new JFrame("The Button Frame");
			// the JFrame object supports a number of methods that we can call
			// we call method setDefaultCloseOperation to configure the window object to close and exit if asked to
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// we need to provide something to draw on the content pane of the frame so we call setContentPane
			// and provide it with a newly instantiated RedButtonGUI object
			RedButtonGUI gui = new RedButtonGUI() ;
			gui.setColor(args[0]) ;
			window.setContentPane(gui);
			// in order to render this frame for screen display we need to make it calculate and render its content
			// here: the RedButtonGUI object we handed it
			window.pack(); 
			// let need to call a method to switch the internal state of the frame such that it becomes visible
			// true/false are values of boolean variables
			boolean b = true ; // just to show a boolean variable
			window.setVisible(b);  
			// let's allow for the window to be resizable 
			window.setResizable(true);
			// done with it, the window should be on the screen now and will stay there till the user clicks
			// on the little cross symbol in the top right corner to close and terminate our program
			// note that the main method returns but the program will run a little longer till
			// the last of its tasks is done, here an internal thread operating the JFrame we created.
		}
	}
	///////////// class internal attributes ////////////////
	// The following variables are declared within the scope of the class.
	// This means each instance of this class,i.e. object, has its own private, individual set of these variables.
	// The values settings for its attributes constitute the "state" of an object.
	// For this example we define
	private Color color ; // a reference to a Color object in order to memorize the color setting of our button
	private JButton button ; // a reference to a Button object that this class puts on the screen
	private int count = 0; // a counter that remembers how often our button was clicked, it is initialized to zero
	// note that one can initialize variables like count with an assignment or one can leave this out.
	// Also note that in Java (different from many other languages) variables like color and button are
	// automatically initialized with null (zero) values unless we assign a particular value (as for count).


	
	////////// class specific methods //////////////////////
	/**
	 * Constructor creates graphic component, a button and adds it to the JPanel this class inherits from.
	 */
	// a constructor is a method that has the class name as its method name. It can have a parameters but 
	// it does not return anything.
	// a constructor method can be public = accessible from anywhere outside of the class 
	// or private = accessible only from inside the class which is unusual (see Singleton Pattern for an example)
	// The purpose of a constructor method is to initialize internal class attributes such that an object
	// after instantiation is fully functional and ready to use, it is in a valid initial state
	public RedButtonGUI() {
		// see above, our variables are count which is already initialized to zero, button and color.
		// we are not happy with null as a value for button, so
		// we instantiate a button object of class JButton by calling its default constructor
		button = new JButton();
		// we want to have our own code being executed if the user clicks the button
		// the way to do this is to program code for an ActionListener (see Listener class below)
		// we instantiate a listener object
		// and then register it with the button
		ActionListener listener = new Listener(); // instantiation of the listener
		button.addActionListener(listener); // registration of listener with the button
		// now we have a usable button and add it to the overall screen, which is the JPanel that
		// this RedButtonGUI class is and extends.
		// so add(Object) is a method that our class inherits from JPanel thanks to the "extends JPanel" 
		// statement on top of the class
		add(button) ;
		// at this point, attribute color == null which is not necessarily a problem 
		// but let's give it default color to make sure our RedButtionGUI object is fully functional
		color = Color.CYAN ; // the chosen color has no deeper meaning and is for demonstration purposes.
	}

	// a class can have private and public methods
	// public = method can be called from outside with the .notation, e.g. myvar.setColor("pink") 
	// if myvar is a variable the references (points to) a RedButtonGUI object
	// the method sets the internal color attribute and the text label shown on the button 
	public void setColor(String string) {
		// "red" will become a reference to String object 
		// equals(Object) is a method of the String class and it compares 2 strings based on their content
		// note that a comparison like ("red" == string) is possible as well but has a different meaning
		// == compares addresses or memory locations which is not what we want here.
		// The boolean condition below should be true if the string variable points to string that has "red" as its value
		// regardless where that string resides in memory
		if ("red".equals(string)) {
			color = Color.RED ; // we assign one of the existing color constants from the existing Color class.
			button.setText("RED BUTTON"); // we configure the text label on the button 
		}
		// below is the corresponding case for green.
		// note that we switched the roles of the strings. 
		// This is equivalent to "green".equals(string) if string is not null. 
		// For the special case of a variable that does not refer to a valid string
		// i.e. it points to "null", calling a method such as equals() on a null reference is not valid
		// and causes a runtime exception (the infamous Null Pointer Exception) and the 
		// program typically stops its execution.
		// The code below is also an example if nested if then else if statements.
		else if (string.equals("green")) {
			color = Color.GREEN ; // analogously to case for red above
			button.setText("GREEN BUTTON");
		}
		else {
			color = Color.CYAN ;
			button.setText("DEFAULT BUTTON");
		}
		// finally we set the color for the text label on the button object
		// by calling one of the public methods of the JButton class.
		button.setForeground(color);
	}



	/**
	 * Internal class to set up a listener for a button.
	 */
	// A class can have an internal class, i.e. a class description where the class name is not the filename.
	// private = can only be accessed from inside the class (kind of secret/invisible that way)
	// class = code can be instantiated with "new" 
	// implements = this class contains a list of methods that matches (or exceeds) a list of methods promised 
	// in some external specification, in some "interface" description given in a file ActionListener.java
	// the ActionListener interface asks specifically for a method "public void actionPerformed(ActionEvent e)"
	// to be implemented here.
	private class Listener implements ActionListener {
		// actionPerformed is a method used to be envoked if a button is clicked.
		// The mechanism works as follows: an object of type JButton accepts objects of type ActionListener
		// and internally keeps a set of those. If the button is clicked, it goes through all
		// elements in that set and calls the actionPerformed() method of each element.
		// In this way it is possible to make the JButton object execute code that it does not
		// really know itself and we can provide the specific code in our own Listener class here.
		public void actionPerformed(ActionEvent e) {
			// the ActionEvent parameter allows us to figure out what kind of event happened here
			// for example we can check the root cause of the click event and make sure 
			// it was our button that was clicked.
			// Note that we perform a straight comparison of references here with the == operation
			// because we want to make sure that the source of the mouse click is the very instance of our button.
			if (e.getSource() == button) {
				// we check our current color attribute setting to see if it is red
				// there is no deeper meaning to this but to demonstrate that in a separate inner class of RedButtonGUI
				// we can access a private attribute of RedButtonGUI, namely color
				// note that we check here if the value setting of color is equivalent to the red color constant in the Color class
				if (color.equals(Color.RED)) 
					// just another example for if-then-else in Java
					// note that for a one-line statement for "then" or "else" the { } can be omitted
					// as demonstrated here
					// however it is common practice to write { } to avoid mistakes if someone
					// later adds more code to this case
					redButtonResponse(); // let's call a private inner method of this class
				else 
					System.out.println("Only a red button matters to me ...");
			}
		}
		// an example of a private, internal method of a class
		// private= can only be called from inside the class
		// void = nothing returned by this method
		// capitalization: method names start lower case, a concatenation of multiple words uses capitalization
		// to separate out individual words like Button and Response here
		private void redButtonResponse() {
			// we access the internal RedButtonGUI attribute here
			// we want to respond differently to a different total of mouse clicks on this button
			// the switch statement allows use to distinguish individual, disjoint cases
			switch(count) {
			case 0 : // each case starts with a keyword case and a possible value of variable count
				// if count == 0 then the following code is executed, till we either hit a "break" statement
				// or the end of the switch block
				// therefore every case should have its own break statement for clarity!
				System.out.println("Please do not press this button again!"); // our specific response
				count++; // we increment attribute count to keep track of the total number of mouse clicks
				break; // we stop the execution here and jump to the end of the switch statement.
			case 1: // similar to case 0
				System.out.println("Sure, you are one of those guys who couldn't resist!");
				count++;
				break;
			case 2:
				System.out.println("Still pressing the red button?");
				System.out.println("One more time and you will execute a call for /bin/rm -rf ~ in the background!");
				count++;
				break;
			case 3: // note there is no break statement, no actual code here, however if count == 3 we start executing here
				// till we hit the break statement below case 5
				System.out.print("You are really not easy to scare ");
			case 4:
			case 5: // the last 3 lines have the effect that the same code is executed for count == 3 or 4 or 5
				System.out.println("... but isn't this getting boring after a while?");
				count++;
				break;
			default: // this covers the situation when count does not match with any other cases above
				System.out.println("Why don't you check the origin of this at\n https://www.youtube.com/watch?feature=player_detailpage&v=kLC8qPNU6_0");
				System.out.println("Thank you for making a simple program really happy, good bye!");
				System.exit(ABORT); // one way to directly stop execution of the program.
				break; // this break statement is not required but it is good practice to have a break statement in each case.
			}
		}
		// we could have as much additional public or private methods as we like
		// but let's leave it as that.
	} //end inner class Listener
	// room for further methods in the RedButtonGUI class, but let's leave it as that
}