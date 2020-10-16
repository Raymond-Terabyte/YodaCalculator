/* Java implementation to convert infix expression to postfix*/


import java.util.Stack; 


//Class is taken from the link in the instruction. 
//this class changes infix to postfix position of operators.

class Test 
{ 
	// A utility function to return precedence of a given operator 
	// Higher returned value means higher precedence 
	static int Prec(char ch) 
	{ 
		switch (ch) 
		{ 
		case '+': 
		case '-': 
			return 1; 
	
		case '*': 
		case '/': 
			return 2; 
	
		case '^': 
			return 3; 
		} 
		return -1; 
	} 
	
	// The main method that converts given infix expression 
	// to postfix expression. 
	static String infixToPostfix(String exp) 
	{ 
		// initializing empty String for result 
		String result = new String(""); 
		String var = "";
		
		// initializing empty stack 
		Stack<Character> stack = new Stack<>(); 
		
		for (int i = 0; i<exp.length(); ++i) 
		{ 
			char c = exp.charAt(i); 
			
			// If the scanned character is an operand, add it to output. 
			if (Character.isLetterOrDigit(c)) 
				var += c;

			
			
				
				//result += c + "#"; 
			
			// If the scanned character is an '(', push it to the stack. 
			else if (c == '(') {
				stack.push(c); 
			
			if(var != ""){
					result += var + "#"; 
                    var ="";
                    
				}
			}
			// If the scanned character is an ')', pop and output from the stack 
			// until an '(' is encountered. 
			else if (c == ')') 
			{ 
				if(var != ""){
					result += var + "#"; 
					var ="";
				}
				while (!stack.isEmpty() && stack.peek() != '(') 
					result += stack.pop(); 
				
				if (!stack.isEmpty() && stack.peek() != '(') 
					return "Invalid Expression"; // invalid expression				 
				else
					stack.pop(); 
			} 
			else // an operator is encountered 
			{ 
				if(var != ""){
					result += var + "#"; 
					var ="";
				}
				while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())){ 
					if(stack.peek() == '(') 
						return "Invalid Expression"; 
					result += stack.pop(); 
			} 
				stack.push(c); 
			} 
	
		} 
	
		// pop all the operators from the stack 
		if(var != ""){
			result += var + "#"; 
			var ="";
		}
		while (!stack.isEmpty()){ 
			if(stack.peek() == '(') 
				return "Invalid Expression"; 
			result += stack.pop(); 
		} 
		return result; 
	} 
	//10*10
	public static int postFixCalc(String s)
	{
		Stack<Integer> st = new Stack<Integer>();
		int x = 0; 
		int y = 0;
		String numbers = ""; 
		char[] ch = s.toCharArray();
		for(char c: ch) {
			//push to stack if it is number
			if(c>='0' && c <='9') {
				numbers += c;
			}
			
			else if(c == '#'){	 
				st.push(Integer.parseInt(numbers));
				numbers = "";
			}
			else {//pop 2 values from stack if operator found.
				y = st.pop();
				x = st.pop();
				
				switch(c) {
				//do calculation according to operator and push back to stack. 
				
				case '+' :
					st.push(x+y);
					break;
				case '-':
					st.push(x-y);
					break;
				case '*':
					st.push(x*y);
					break;
				case '/':
					st.push(x/y);
					break;
				}
			}
		}
		//return top of stack for answer.
		return st.pop();
	}
	
	
	public static void main(String[] args) 
	{ 
		//Only works with one digit number. (I need Help with this)
		String exp = "10*1"; 
		String trimmed = "";
		for(int i = 0; i < exp.length(); i++)
		{
			if(exp.charAt(i) != ' ' )
			{
				trimmed += exp.charAt(i);
			}
		}
		exp = trimmed;
		System.out.println("User Input: " + exp);
		String a = infixToPostfix(exp);
		System.out.println("PostFix Expression: " + a);
		System.out.println("Answer: " + postFixCalc(a));
	} 
} 
