package ioURL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Demonstrates the use of the URL class, BufferedReader, and BufferedWriter classes to read from a url and write to a text file. 
 * 
 * @author Brittany Pruneau 
 *
 */
public class DemoURLAccess
{
	public static void main(String[] args)
	{
		String urlString = "https://www.gutenberg.org/files/46768/46768-0.txt";
		String fileShakespere = "src/ioURL/Shakespear.txt";  
		try
		{
			URL url = new URL(urlString);
			try(InputStream stream = url.openStream();																																													//Since the class URL does not implement AutoClosable, we can't combine all three declarations in the try-with-resource statement. Instead, we nest the try-with-resource statement inside the existing try block.
					BufferedReader reader = new BufferedReader(new InputStreamReader(stream));  // input stream reader inherits from reader. 
																								// BufferedReader constructor takes parameter of type Reader
																								// InputStreamReader constructor takes an InputStream.
																								// Class URL constructor takes a String of the url.. 
																								// URL class has a method called .openStream() - Opens a 
																								// connection to this URL and returns an input stream for 
																								//reading from that connection. 
					
					PrintWriter writer = new PrintWriter(new FileWriter(fileShakespere)); // BufferedWriter takes a FileWriter 
																								// FileWriter takes a string to the txt file which it will write to.
																								
					Scanner scanShakeSpeare = new Scanner(System.in))		// Scanner is taking user input. "enter"
			{
			String line;
			int PAGE_LENGTH = 35;
			int pageLength = 1;
			while(((line = reader.readLine()) != null))  // BufferedReader is taking an InputStreamReader object who's 
														//constructor takes a URL of type input stream. BufferedReader is reading 
														// from the url https://www.gutenber.org.. 
			{
				//System.out.println(line);
				writer.println(line);   // BufferedWriter takes a FileWriter in its constructor with the .txt file we created.
				pageLength++;
				if (pageLength == 36)
				{
					System.out.println("- - - Press Enter to continue - - -");
					System.in.read();
					pageLength = 1;
				}
			}
		}
	}
		catch (MalformedURLException e)
		{
			System.out.println("We encoutered a problem regarding the following URL:"
					+ "\nhttps://www.gutenberg.org/files/46768/46768-0.txt \nEither no "
					+ "legal protocol could be found or the string could not be parsed.");
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			System.out.println("Attempting to open a stream from the following URL:\n"
					+ urlString
					+ "\ncaused a problem.");
			e.printStackTrace();
		}  
	}
}
