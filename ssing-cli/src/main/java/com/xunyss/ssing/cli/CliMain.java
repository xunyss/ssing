package com.xunyss.ssing.cli;

import java.io.IOException;
import java.io.PrintWriter;

import jline.console.ConsoleReader;

/**
 * 
 * @author XUNYSS
 */
public class CliMain {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("ssing start.");
		
		ConsoleReader reader = new ConsoleReader();
		
		reader.setPrompt("prompt> ");
		
		String line;
		PrintWriter out = new PrintWriter(reader.getOutput());
		
		while ((line = reader.readLine()) != null) {
			out.println("==========> " + line);
			out.flush();
			
			if (line.equalsIgnoreCase("exit")) {
				break;
			}
			else if (line.equalsIgnoreCase("cls")) {
				reader.clearScreen();
			}
			else if (line.equalsIgnoreCase("show")) {
				
			}
		}
	}
}
