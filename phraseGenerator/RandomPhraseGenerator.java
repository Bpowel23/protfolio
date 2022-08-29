package phraseGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
/**
 * Creates random phrase(s) based on grammar file.
 * @author Benjamin Powell  u1379684 & Sebastien Combes u1362580
 * @version April 20, 2022
 */
public class RandomPhraseGenerator 
{
	private static void buildRandomPhrase(String totalPhrasesString, File file) throws FileNotFoundException
	{
		int totalPhrases = Integer.parseInt(totalPhrasesString);
		StringBuilder phrase = new StringBuilder();
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		Scanner input = new Scanner(file);
		
		//Outer loop based on input of total phrases
		for(int i = 0; i < totalPhrases; i++)
		{
			while(input.hasNextLine())
			{
				String checkedLine = input.nextLine();
				//start of curly braces
				if(checkedLine.equals("{"))
				{
					checkedLine = input.nextLine();
					String key = checkedLine.substring(1, checkedLine.length()-1);
					
					ArrayList<String> valueArray = new ArrayList<String>();
					{
						//Create ArrayList and assign the checkedWord and ArrayList to the map
						checkedLine = input.nextLine();
						while(!checkedLine.equals("}"))
						{
							valueArray.add(checkedLine);
							checkedLine = input.nextLine();
						}
					}
					map.put(key, valueArray);
				}
			}
			
			//Construct Phrase
			Random random = new Random();
			ArrayList<String> wordCategory = map.get("start");
			String initialPhrase = wordCategory.get(random.nextInt(wordCategory.size()));
			String phraseSegment;
			//Checks to see if phrase has non-terminals and keeps repeating
			while(initialPhrase.contains("<"))
			{
				input = new Scanner(initialPhrase);
				input.useDelimiter("(<|>)");
				//Determines if first phrase segment has non-terminals
				boolean isNonTerminal = false;
				if(initialPhrase.substring(0, 1).equals("<"))
					isNonTerminal = true;
				//Runs through scanner using delimiter to find terminals/non-terminals
				while(input.hasNext())
				{
					phraseSegment = input.next();
					//If it is a non-terminal, pulls from our map of word categories and
					//ArrayLists to replace the non-terminal
					if(isNonTerminal)
					{
						wordCategory = map.get(phraseSegment);
						String addedWord = wordCategory.get(random.nextInt(wordCategory.size()));
						phrase.append(addedWord);
					}
					else
					{
						phrase.append(phraseSegment);
					}
					
					//Flips between terminals and non-terminals due to how delimiter is set up
					isNonTerminal = !isNonTerminal;
					
				}
				initialPhrase = phrase.toString();
				phrase = new StringBuilder();
			}
			System.out.println(initialPhrase);
		}
		input.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		File file = new File(args[0]);
		buildRandomPhrase(args[1], file);
		
		//non-command line (move to project folder)
		
//		File file = new File("abc.g");
//		buildRandomPhrase( "1" , file);
	}
}
