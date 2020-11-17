package linux.terminal;


/**
 * @author Abdlkhalek Alalimi.
 * @version 1.0
 * @since  11 - 2020
 * */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Terminal {
	private String currentPath ;
	
	Terminal(){}
	
	Terminal(String currentPath){
		this.currentPath = currentPath;
	}
	
	public void cd(String fileName) { // function 1 
		var file = new File(currentPath);
		if (fileName.equals("") || fileName.equals(null)) {
			System.out.println(pwd()); // print the current path of the system.
		}
		if (fileName.equalsIgnoreCase("..")) {
			currentPath = file.getParent();
			return;
		}
			
		file = new File(currentPath+"\\"+fileName);	
		if (file.exists()) {
		currentPath+=("\\"+fileName);
		}else {
			System.out.println("The System Can Not Find The Path Specified");
		}
	}
	
	
	public String pwd() { // function 2 
		return currentPath;
	} 
	
	public void ls() { // function 3 
		try {
			var file = new File(currentPath);
			var files = file.list();
			for (String currentFile:files) {
				System.out.println(currentFile);
			}
		}catch(Exception e) {
			System.out.println("No such a directory.");
		}
	}
	
	public void cp(String from , String to) throws FileNotFoundException,IOException { // function 4 
		if (from.equals("") || to.equals("")) {
			System.out.println("Enter valid source and destination.");
			return; // break function.
		}
		if (from.contains("\\") &&  to.contains("\\")){ // if each one was in a folder 
			var firstFile = new File(from);
			var secondFile = new File(to);
			if (firstFile.isFile() && secondFile.isFile()) { // check if they are files.
				var reader = new FileInputStream(firstFile);
				var writer = new FileOutputStream(secondFile);
				int contentOfFirstFile;
				while((contentOfFirstFile = reader.read())!=-1) {
					writer.write((char)contentOfFirstFile);
				}
				reader.close();
				writer.close();
				System.out.println(from + " successfully copied " + to);
				return;
			}
			System.out.println("those are not exist files.");
			return;
		}
		if (from.contains("\\") && !to.contains("\\")) { // if the source was in another folder
			var firstFile = new File(from);
			var secondFile = new File(currentPath+"\\"+to);
			if (firstFile.isFile() && secondFile.isFile()) {
				var reader = new FileInputStream(firstFile);
				var writer = new FileOutputStream(secondFile);
				int contentOfFirstFile;
				while((contentOfFirstFile = reader.read())!=-1) {
					writer.write((char)contentOfFirstFile);
				}
				reader.close();
				writer.close();
				System.out.println(from + " successfully copied " + to);
				return;
			}
			System.out.println("error ocuured when tried to copy.");
		}
		
		if (!from.contains("\\") && to.contains("\\")) { // if the destination was in another folder
			var firstFile = new File(currentPath + "\\" + from);
			var secondFile = new File(to);
			if (firstFile.isFile() && secondFile.isFile()) {
				var reader = new FileInputStream(firstFile);
				var writer = new FileOutputStream(secondFile);
				int contentOfFirstFile;
				while((contentOfFirstFile = reader.read())!=-1) {
					writer.write((char)contentOfFirstFile);
				}
				reader.close();
				writer.close();
				System.out.println(from + " successfully copied " + to);
				return;
			}
			System.out.println("error ocuured when tried to copy.");
		}
		
		if (!from.contains("\\") && !to.contains("\\")) { // if the source and destination was in the same folder.
			var firstFile = new File(currentPath + "\\" + from);
			var secondFile = new File(currentPath + "\\" + to);
			if (firstFile.isFile() && secondFile.isFile()) {
				var reader = new FileInputStream(firstFile);
				var writer = new FileOutputStream(secondFile);
				int contentOfFirstFile;
				while((contentOfFirstFile = reader.read())!=-1) {
					writer.write((char)contentOfFirstFile);
				}
				reader.close();
				writer.close();
				System.out.println(from + " successfully copied to " + to);
				return;
			}
			System.out.println("error ocuured when tried to copy.");
		}
		System.out.println("there is a problem with your paths check them out.");
	}
	
	public void more(String fileName) throws FileNotFoundException,IOException { // function 5.
		if (fileName.equals("") || fileName.equals(null)) {
			System.out.println("more function should have a parameter");
			return;
		}
		
		if (fileName.contains("\\")) {
			var file = new File(fileName);
			if (file.isFile()) {
				var reader = new FileInputStream(file);
				int content;
				while((content=reader.read())!=-1) {
					System.out.print((char)content);
				}
				reader.close();
				return;
			}else {
				System.out.println("No Such a File.");return;
			}
		}
		
		if (!fileName.contains("\\")) {
			var file = new File(currentPath+ "\\" + fileName);
			if (file.isFile()) {
				var reader = new FileInputStream(file);
				int content;
				while((content=reader.read())!=-1) {
					System.out.print((char)content);
				}
				reader.close();
				return;
			}else {
				System.out.println("No Such a File.");return;
			}
		}
		System.out.println("there is a problem check the file name.");
	}
	
	public void mkdir(String directoryName) { // function number 6.
		if (directoryName.equals("")) {
			System.out.println("Will not create a file with emoty Name Enter the Name.");return;
		}
		if (directoryName.contains("\\")) {
			var file = new File(directoryName);
			if (!file.exists()&& file.mkdir()) {
				System.out.println(directoryName + "successfully created.");return;
			}
			System.out.println(directoryName + " already exsit.");return;
		}
		if (!directoryName.contains("\\")) {
			var file = new File(currentPath+ "\\" + directoryName);
			if (!file.exists() && file.mkdir()) {
				System.out.println(directoryName + "successfully created.");return;
			}
			System.out.println(directoryName + " already exsit.");return;
		}
		System.out.println("problem occured.");
	}
	
	public void rmdir(String fileName) { // function 7.
		if (fileName.equals("")) {
			System.out.println("Enter the file path of name");return;
		}
		
		if (fileName.contains("\\")) {
			var file = new File(fileName);
			if (file.exists() && file.isDirectory()) {
				if (file.length() == 0) {
					file.delete();
					System.out.println(fileName + " successfully deleted");return;
				}
				System.out.println(fileName + " is not empty.");return;
			}
			System.out.println(fileName + " is not a file in the system.");return;
		}
		
		if (!fileName.contains("\\")) {
			var file = new File(currentPath+"\\" + fileName);
			if (file.exists() && file.isDirectory() ) {
				if (file.length() == 0) {
					file.delete();
					System.out.println(file.getName() + " successfully deleted.");return;
				}
				System.out.println(fileName + " is Not empty.");return;
			}
			System.out.println(fileName + " is not a file in the system.");return;
		}
	}
	
	public void rm(String fileName) {// function 8.#########
		if (fileName.equals("")) {
			System.out.println("No file in the system with no name Enter a correct Name.");return;
		}
		if (fileName.contains("\\")) {
			var file = new File(fileName);
			if (file.exists() && file.delete()) {
				System.out.println(file.getName() + " successfully deleted.");return;
			}
			System.out.println(file.getName() + " not exist.");return;
		}
		
		var file = new File(currentPath + "\\" + fileName); 
		if (file.exists() && file.delete()) {
			System.out.println(file.getName() + " successfully deleted.");return;
		}else System.out.println(file.getName() + " not such a directory.");
	} 
	
	public void mv(String source,String destination) throws IOException,NoSuchFileException { // only moves txt files // function 9.
		if (source.contains("\\") && destination.contains("\\")) {
			var file = Files.move(Paths.get(source + ".txt"),Paths.get(destination + ".txt")); // with type path.
			if (file!=null) { // will return the path
				System.out.println(file.getFileName() + " successfully moved");
			}else System.out.println("still there");
			return;
		}
		
		if (!source.contains("\\") && destination.contains("\\")) {
			var file = Files.move(Paths.get(currentPath+ "\\" +source+".txt"),Paths.get(destination + ".txt")); // type path
			if (file!=null) {
				System.out.println(file.getFileName() + " successfully moved.");
			}else System.out.println("File Still there.");
			return;
		}
		
		if (source.contains("\\") && !destination.contains("\\")) {
			var file = Files.move(Paths.get(source+".txt"),Paths.get(currentPath + "\\" + destination + ".txt"));
			if (file!=null) {
				System.out.println(file.getFileName() + " successfully moved.");
			}else System.out.println("fille still there.");
			return;
		}
		System.out.println("you can't move file from the path to the path itself.");
	}
	
	public void clear()	// function 10   
	{
		for (int moveUp=0;moveUp<15;++moveUp)
		{
			System.out.println();
		}
	}

	public void date()	// function 11
	{
		String DateTime = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss").
        format(Calendar.getInstance().getTime());
        System.out.println(DateTime);
	}

	public void exit()	// function 12  
	{
		System.exit(0); // built in function.
	}
	
	public void help() // function 13 
	{
		System.out.println(
		"help \t shows all system functin with description\n" + //done
		"cd   \t Change Directory\n" + // done
        "Ls   \t List of the file\n" + // done 
        "cp   \t Stands for copy\n" + // done 
        "mv   \t Remove file from place to another\n" + // done
        "rm   \t Delete file\n" +  // done
        "args \t List all parameters\n" + // done
        "date \t Output current Date\n" + // done 
        "pwd  \t Return current directory\n" + // done 
        "cat  \t >> append one file to another , > read from a file.\n" + // done 
        "more \t View text file in command\n" + // done
        "pipe operator \t help to get two command together\n" + // in main class.
        "mkdir \t Allows user to creat directories\n" + // done.
        "rmdir \t Remove empty directories from a filesystem\n" + // done
        "clear \t Clear the command\n"); // done
	}
	
	public void args(String command) // function 14 
	{
		if (command.toLowerCase().equals("date") || command.toLowerCase().equals("pwd") || command.toLowerCase().equals("clear")
				|| command.toLowerCase().equals("help") || command.toLowerCase().equals("ls")) { // then
				System.out.println("doesn't take any argument.");
		}
		if (command.toLowerCase().equals("cd") || command.toLowerCase().equals("mkdir") || command.toLowerCase().equals("rm")
				|| command.toLowerCase().equalsIgnoreCase("more") || command.toLowerCase().equalsIgnoreCase("rmdir")) { // then
			System.out.println(" take a directory."); 
		}
		if (command.toLowerCase().equalsIgnoreCase("args")) {
			System.out.println("one argument, which is the command you wanna know about.");
		}
		if (command.toLowerCase().equalsIgnoreCase("mv") || command.toLowerCase().equalsIgnoreCase("cp")) {
			System.out.println("it takes two arguments (SourcePath , destinationPath)");
		}
		
		if (command.toLowerCase().equalsIgnoreCase("cat")) {
			System.out.println("two or three arguments. , (path to read file from) , (> , nameOfTxtFileToBeCreated) ,(fileOne ,"
					+ " >> , Filetwo To append the content of file one) ");
		}
	}
	
	public void cat(String path) throws IOException  { // function 15. // for txt files only 
		if (path.contains("\\")) {
			var file = new File(path+".txt"); // add txt 
			if (file.exists()) {
				try(var reader = new FileInputStream(file)){
					int content;
					while((content= reader.read())!=-1) {
						System.out.print((char)content);
					}System.out.println();return;
				}
			}else{
				System.out.println(file.getName() + " not exist.");return;}
		}
		
		if (!path.contains("\\")) {
			var file = new File(currentPath+"\\" + path + ".txt");
			if (file.exists()) {
				try(var reader = new FileInputStream(file)){
					int content;
					while((content= reader.read())!=-1) {
						System.out.print((char)content);
					}System.out.println();return;
				}
			}else{
				System.out.println(file.getName() + " not exist.");return;}
			}
	}
	
	public void redirect(String arr[]) throws IOException{
		if (arr[1].equals(">")) {
			if (arr[2].contains("\\")) {
				var file = new File(arr[2]+".txt");
				if (!file.exists()) {
					if (file.createNewFile()) {
						System.out.println(file.getName() + "successfully created.");return;}
					else System.out.println("file wasn't created.");
				}else {
					System.out.println(file.getName() + " already exists.");return;
				}
			}
			if (!arr[2].contains("\\")) {
				var file = new File(currentPath + "\\"+arr[2]+".txt");
				if (!file.exists()) {
					if (file.createNewFile()) {
						System.out.println(file.getName() + "successfully created.");return;}
					else System.out.println("file wasn't created.");
				}else {
					System.out.println(file.getName() + " already exists.");return;
				}
			}
		}
		// 2 - 
		if (arr[2].equals(">>")) {
			if (arr[1].contains("\\") && arr[3].contains("\\")) {
				var file1 = new File(arr[1]+".txt");
				var file2 = new File(arr[3]+".txt");
				if (file1.exists() && file2.exists()) {
					try(var reader = new FileInputStream(file1);
						BufferedReader bufferin = new BufferedReader(new InputStreamReader(reader));
						var writer = new FileWriter(file2,true);
						var bufferOut = new BufferedWriter(writer)){
						String line;
						while((line = bufferin.readLine())!=null) {
							bufferOut.write(line);
							bufferOut.newLine();
						}
						bufferin.close();bufferOut.close();
						System.out.println("File successfully copied.");return;
					}
				}else {
					System.out.println("there was a problem.");return;
				}
			}
			
			if (!arr[1].contains("\\") && arr[3].contains("\\")) {
				var file1 = new File(currentPath+"\\"+arr[1]+".txt");
				var file2 = new File(arr[3]+".txt");
				if (file1.exists()&& file2.exists()) {
					try(var reader = new FileInputStream(file1);
							BufferedReader bufferin = new BufferedReader(new InputStreamReader(reader));
							var writer = new FileWriter(file2,true);
							var bufferOut = new BufferedWriter(writer)){
							String line;
							while((line = bufferin.readLine())!=null) {
								bufferOut.write(line);
								bufferOut.newLine();
							}
							bufferin.close();bufferOut.close();
							System.out.println("File successfully copied.");return;
						}
					}
				}else {
					System.out.println("there was a problem.");return;
				}
			}
		}
}
