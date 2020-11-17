package linux.terminal;

import java.util.ArrayList;

/**
 * @author Abdlkhalek Alalimi.
 * @version 1.0
 * @since  11 - 2020
 * */
public class Parser {
	
	private String speratedLine[];
	private String command; // just inside this class we don't need it outside.
	
	public void setCommand() {
		this.command = speratedLine[0];
	}
	
	public String getCommand() {
		return command;
	}
	
	public String[] getSperatedLine() { // will use it in the main class.
		return speratedLine;
	}
	
	public ArrayList<String> getCommands(){
		ArrayList<String> commands = new ArrayList<>();
		commands.add("help");/*0*/ commands.add("ls");/*1*/commands.add("clear"); /*2*/
        commands.add("date"); /*3*/commands.add("pwd");/*4*/commands.add("rm");/*5*/
        commands.add("mkdir");/*6*/commands.add("rmdir");/*7*/commands.add("args");/*8*/ 
        commands.add("more");/*9*/commands.add("cp");/*10*/commands.add("mv");/*11*/ 
        commands.add("cat");/*12*/commands.add("exit");/*13*/
        return commands;
	}
	Parser(){}
	
	public boolean parser(String input) {
		this.speratedLine = input.split(" ");
		setCommand();
		if (command.toLowerCase().equals("clear") && speratedLine.length!=1) return false; // 1 // done 
		
		if (command.toLowerCase().equals("date") && speratedLine.length!=1)return false; //2 //done 
		
		if (command.toLowerCase().equals("exit") && speratedLine.length!=1)return false; //3 // done 
		
		if (command.toLowerCase().equals("help") && speratedLine.length!=1)return false; //4 //done 
		
		if (command.toLowerCase().equals("pwd") && speratedLine.length!=1)return false; //5 // done 
		
		if (command.toLowerCase().equals("ls") && speratedLine.length!=1)return false; // 6 //done
		
		if (command.toLowerCase().equals("cd") && speratedLine.length!=2)return false; // 7 //done 
		
		if (command.toLowerCase().equals("args") && speratedLine.length!=2)return false; // 8 // done
		
		if (command.toLowerCase().equals("mkdir") && speratedLine.length!=2)return false; // 9 // done 
		
		if (command.toLowerCase().equals("rmdir") && speratedLine.length!=2)return false; // 10 // done 
		
		if (command.toLowerCase().equals("more") && speratedLine.length!=2)return false; // 11 // done 
		if (command.toLowerCase().equals("rm") && speratedLine.length!=2)return false; // 12 // done 
		
		if (command.toLowerCase().equals("mv") && speratedLine.length!=3)return false; // 13 //done 
		
		if (command.toLowerCase().equals("cat") && (speratedLine.length<2 || speratedLine.length>4))return false;  // done 
		
		return true;
	}
}
