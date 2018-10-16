import java.util.*;
import java.util.Map.Entry;
//Patrick Phillips
//CSC 172

public class URCalculator {

	private static String infix;
	private static boolean assignment = false; //keeps track of if input is an assignment
	private static Scanner scan = new Scanner(System.in);
	private static Map<String, String> mathLogic = new HashMap<String, String>(){ //used to simplify infix
		{
			put("++", "+");
			put("--", "+");
			put("+-", "-");
			put("-+", "-");
		}
	};
	private static Map<String, Integer> operations = new HashMap<String, Integer>(){ //Keeps track of operators
		{
			put("+", 16);
			put("-", 17);
			put("*", 18);
			put("/", 19);
			put("%", 20);
			put("^", 21);
			put("=", 5);
			put("(", 30);
			put(")", 30);

			put("{", 40);
			put("}", 40);

			put("[", 45);
			put("]", 45);
		}
	};
	private static ArrayList<String> RightParentheses = new ArrayList<String>() {
		{
			add(")");
			add("}");
			add("]");
		}
	};
	private static ArrayList<String> LeftParentheses = new ArrayList<String>() {
		{
			add("(");
			add("{");
			add("[");
		}
	};
	private static Map<String, Double> variables = new HashMap<String, Double>() { //Used to store variables
		{

		}
	};


	public static void newInput() { //prompts new input
		System.out.print("Enter input: ");
		infix = scan.nextLine();
		if(infix.equals("clear all")) {
			variables = new HashMap<String, Double>() {
				{
				}
			};
		}
		else if(infix.matches("clear(.*)")) {
			String[] arr = infix.split(" ");
			if(variables.containsKey(arr[1])) {
				variables.remove(arr[1]);
			}
			else {
				System.out.println("That variable doesn't exist!");
			}
		}
		else if(infix.equals("show all")) {
			variables.forEach((key, value) -> System.out.println(key + " = " + value));
		}
		else if(infix.equals("exit")) {
			System.exit(0);
		}
		else {
			assignmentAndSpaces(infix);
		}
	}

	public static void assignmentAndSpaces(String input) { //adds spaces to help tokenize and checks to see if the input is an assignment
		String temp = "";
		String output = "";

		for(int i = 0; i<input.length(); i++) {
			temp = input.substring(i, i+1);
			if(operations.containsKey(temp)) {
				output = output + " " + temp + " ";
			}
			else {
				output = output + temp;
			}
		}
		while(output.contains("=")) {
			for(int i = 0; i<output.length(); i++) {
				if (output.substring(i, i+1).equals("=")){
					if(output.substring(0, i).trim().contains(" ")){
						assignment = true;
						System.out.println("Variable name can't contain spaces!");
						output = output.substring(i+1, output.length());
					}
					else {
						assignment = true;
						if(calc(output.substring(output.lastIndexOf("=") + 1, output.length()).trim()) != null) {
							variables.put(output.substring(0, i).trim(), calc(output.substring(output.lastIndexOf("=") + 1, output.length()).trim()));
						}
						output = output.substring(i+1, output.length());
						i=0;
					}
				}
			}
		}
		if(!assignment) {
			System.out.println(calc(output));
		}
	}
	public static Double calc(String infix) { //primary functional method, takes infix to a postfix expression in the form of an arraylist and then calculates the value
		ArrayList<String> postfix = new ArrayList<>();
		Deque<String> stack = new LinkedList<>();		
		Deque<Double> calculate = new LinkedList<>();
		double first;
		double second;
		String[] infixArray = toStringArray(infix);

		for(int i = 0; i<infixArray.length; i++) {
			if(operations.containsKey(infixArray[i])) {
				try {
					if(infixArray[i].equals("(") || infixArray[i].equals("{") || infixArray[i].equals("[")) {
						stack.push(infixArray[i]);
						//System.out.println(stack.peek());
					}
					else if(infixArray[i].equals(")") || infixArray[i].equals("}") || infixArray[i].equals("]")) {
						while (!(operations.get(stack.peek()) == (operations.get(infixArray[i])))) {
							postfix.add(stack.pop());
						}
						stack.pop();
					}
					else {
						while((!LeftParentheses.contains(stack.peek()) && (!stack.isEmpty()) && (operations.get(infixArray[i])<operations.get(stack.peek())))) {
							postfix.add(stack.pop());
						}
						stack.push(infixArray[i]);
					}
				}
				catch(NoSuchElementException e) {
					System.out.println("Parantheses Mismatch!");
					return null;
				}
			}
			else {
				postfix.add(infixArray[i]);
			}
		}


		while(!stack.isEmpty()) {
			postfix.add(stack.pop());
		}

		if(!check(postfix, infixArray)) {
			return null;
		}
		try { //Trys all of the mathematical operations, will catch if there are more operators than operands
			for(int i = 0; i < postfix.size(); i++) {
				if(operations.containsKey(postfix.get(i))) {
					second = calculate.pop();
					first = calculate.pop();
					if(postfix.get(i).equals("%")) {
						double temp = first % second;
						calculate.push(temp);
					}
					if(postfix.get(i).equals("^")) {
						double temp = Math.pow(first, second);
						calculate.push(temp);
					}
					if(postfix.get(i).equals("+")) {
						double temp = first + second;
						calculate.push(temp);
					}
					else if(postfix.get(i).equals("*")) {
						double temp = first * second;
						calculate.push(temp);
					}
					else if(postfix.get(i).equals("/")) {
						double temp = first / second;
						calculate.push(temp);
					}
					else if(postfix.get(i).equals("-")) {
						double temp = first - second;
						calculate.push(temp);
					}

				}
				else {
					try {
						calculate.push(Double.parseDouble((postfix.get(i))));
					}
					catch(NumberFormatException e) {
						System.out.println("Invalid Expression!");
					}
				}
			}
			if(calculate.peek().isInfinite()) {
				System.out.println("Can't divide by 0!");
				return null;
			}
			return calculate.pop();
		}catch(NoSuchElementException e) {
			System.out.println("Invalid Expression!");
			return null;
		}
	}

	private static String[] toStringArray(String infix2) {		
		String[] infix = infix2.trim().split("\\s+");
		ArrayList<String> temp = new ArrayList<>();
		for(int i =0; i<infix.length; i++) {
			temp.add(infix[i]);
		}
		boolean loop = true;
		while(loop) {
			loop = false;
			for(int i = 0; i<temp.size(); i++) {
				try {
					if(mathLogic.containsKey(temp.get(i)+temp.get(i+1))) {
						temp.set(i, mathLogic.get((temp.get(i)+temp.get(i+1))));
						temp.remove(i+1);
						loop = true;
					}
				}catch(IndexOutOfBoundsException e) {

				}
			}
		}

		for(int j = 0; j<temp.size()-1; j++) { //checks plus and minuses
			if(operations.containsKey(temp.get(j)) && operations.containsKey(temp.get(j+1)) && (!RightParentheses.contains(temp.get(j)))) {
				if(temp.get(j+1).equals("+")) {
					temp.remove(j+1);
				}
				if (temp.get(j+1).equals("-")) {
					temp.add(j+3, ")");
					temp.add(j+1, "0");
					temp.add(j+1, "(");
				}
			}
		}
		if(temp.get(0).equals("+")) {
			temp.remove(0);
		}
		if(temp.get(0).equals("-")) {
			temp.add(0, "0");
			temp.add(0, "(");
			temp.add(4,")");
		}
		String[] strings = temp.toArray(new String[0]);
		return strings;
	}

	public static boolean check(ArrayList<String> postfix, String[] infixArray) { //checks parentheses and plus and minus signs
		if(postfix.contains("(") || postfix.contains(")")){ //see if remaining parentheses
			System.out.println("Parentheses Mismatch!");
			return false;
		}
		for(int i = 0; i<infixArray.length-1; i++) { //makes sure there are no consecutive numbers
			try {
				Double.parseDouble(infixArray[i]);
				Double.parseDouble(infixArray[i+1]);
				System.out.println("Invalid Expression!");
				return false;
			}catch(NumberFormatException e) {

			}
		}

		if(operations.containsKey(infixArray[infixArray.length-1])) { //checks if trailing operator
			if((((operations.get(infixArray[infixArray.length-1])) <30))){
				System.out.println("Invalid Expression, trailing operator");
				return false;
			}
		}
		for(int i = 0; i<postfix.size(); i++) { //make sure everything is a valid input
			if(variables.containsKey(postfix.get(i))) {
				postfix.set(i, Double.toString(variables.get(postfix.get(i))));
			}
			else if(operations.containsKey(postfix.get(i))) {

			}
			else {
				try
				{
					Double.parseDouble(postfix.get(i));
				}
				catch(NumberFormatException e)
				{
					System.out.println("Not all of those were numbers and variables!");
					return false;
				}
			}
		}
		return true;

	}

	public static void main(String[] args) {
		System.out.println("Enter 'clear all' to clear all variables ");
		System.out.println("Enter 'show all' to show all variables");
		System.out.println("Enter 'clear varX' to clear a variable");
		System.out.println("Enter 'exit' to exit");
		System.out.println("Or enter a mathemtical expression or assignment");
		while(true) {
			assignment = false;
			newInput();
			System.out.println();
		}
	}
}
