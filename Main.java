package linux.terminal;

/**
 * @author Abdlkhalek Alalimi.
 * @version 1.0
 * @since  11 - 2020
 * */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static Parser parser = new Parser();; 
	public static Terminal terminal = new Terminal("D:\\myChannelVideos");// set the default path
	public static String inputSperated [];// line of input will be sperated here.
	public static ArrayList<String > commands;
	public static String userInput;
	
	public void fillCommands() {
		commands = parser.getCommands();
//        commands.add("help");/*0*/ commands.add("ls");/*1*/commands.add("clear"); /*2*/
//        commands.add("date"); /*3*/commands.add("pwd");/*4*/commands.add("rm");/*5*/
//        commands.add("mkdir");/*6*/commands.add("rmdir");/*7*/commands.add("args");/*8*/ 
//        commands.add("more");/*9*/commands.add("cp");/*10*/commands.add("mv");/*11*/ 
//        commands.add("cat");/*12*/commands.add("exit");/*13*/
	}
	
//	public boolean available(String command) { // check if the input of command was correct first or not .
//		return commands.contains(command)?true:false;
//	}
	
	public void execute() throws FileNotFoundException, IOException {
		if (parser.parser(userInput)){
			inputSperated = parser.getSperatedLine();
			
			if (inputSperated[0].toLowerCase().equals("help"))terminal.help(); // return help 1 
			
			if (inputSperated[0].toLowerCase().equals("clear"))terminal.clear(); // 2 
			
			if (inputSperated[0].toLowerCase().equals("date"))terminal.date(); // 3 
			
			if (inputSperated[0].toLowerCase().equals("pwd"))System.out.println(terminal.pwd()); //4 
			
			if (inputSperated[0].toLowerCase().equals("ls"))terminal.ls(); // 5 
			
//			if (inputSperated[0].toLowerCase().equals("exit"))terminal.exit(); // 6 
			
			if (inputSperated[0].toLowerCase().equals("cd"))terminal.cd(inputSperated[1]);
			
			if (inputSperated[0].toLowerCase().equals("args"))terminal.args(inputSperated[1]);
			
			if (inputSperated[0].toLowerCase().equals("more"))terminal.more(inputSperated[1]);
			
			if (inputSperated[0].toLowerCase().equals("mkdir"))terminal.mkdir(inputSperated[1]);
			
			if (inputSperated[0].toLowerCase().equals("rmdir"))terminal.rmdir(inputSperated[1]);
			
			if (inputSperated[0].toLowerCase().equals("rm"))terminal.rm(inputSperated[1]);
			
			if (inputSperated[0].toLowerCase().equals("cat"))
				{
					if (inputSperated[1].equals(">") || inputSperated[1].equals(">>")) {
						terminal.redirect(inputSperated);
					}
					else {
						terminal.cat(inputSperated[1]);
					}
				}
			
			if (inputSperated[0].toLowerCase().equals("mv"))terminal.mv(inputSperated[1], inputSperated[2]);
			
		}else {System.out.println("Enter a correct command or write help to get help.");}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Main mainSystem = new Main();
		String pipeOperator[]; // to store | before and after.
		Scanner scan = new Scanner(System.in);
		System.out.print(terminal.pwd() + " >| ");
		userInput = scan.nextLine();
		while(!userInput.equals("exit")) {
			if (userInput.contains("|")) { // for pipe operator.
				pipeOperator = userInput.split("[|]"); 
				for (String newCommand:pipeOperator) {
					userInput = newCommand.replace("|", "");// special character with empty
					userInput = userInput.strip(); // then remove whitespace from both ends.
					mainSystem.execute(); // execute the program.
				}
			}
			else {
				mainSystem.execute();
			}
			System.out.print(terminal.pwd() + " >| ");
			userInput = scan.nextLine();
		}
		//System.out.print(terminal.pwd() + " >| ");// always print the path to the user.
		scan.close();
	}
}
