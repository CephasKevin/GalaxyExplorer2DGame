package standaloneGalaxyExplorer;

import java.util.Random;
import java.util.Scanner;

public class runGame {
	
	private static Random rand;
	
	 static Scanner inputScanner = new Scanner(System.in);
	    
	
	private static void nextLevel(String target, String enemyShipLocations)
	{
		System.out.println("GalaxyExplorer spaceship at coordinates(0,0) facing North!\n");
		System.out.println("Enemy ships located at coordinates: " + enemyShipLocations + " You must avoid making contact with these");
		System.out.println("Your Target is located at coordinates: " + target + " Enter commands now!");
		
		String inputCommand = inputScanner.nextLine();//Take user input and pass it into a string
	    
	    
		
	    
	    
		String result = standaloneGalaxyExplorer.GalaxyExplorer(3, 3, enemyShipLocations, inputCommand);
		
		String resultCoordinates = String.valueOf("(" +result.charAt(1) + ";" + result.charAt(3) + ")");//Checking results 
		
		if(resultCoordinates.equals(target))
		{
			System.out.println("\nCongratulations, you have reached the target! ");
		}
		else
		{
			System.out.println("You failed to reach the target! :( = " + result);
			System.exit(0);
		}
	}
	
	private static int randomNumbrFrmRange(int max, int min)
	{
		if(rand == null)
		{
			rand = new Random();
			rand.setSeed(System.nanoTime());
		}
		
		int diff = max - min;
		
		int r = rand.nextInt(diff + 1);
		
		return r;
	}
	
	private static String generateEnemyCoords(int targetXcrd, int targetYcrd)
	{
		int enemyXcrd = randomNumbrFrmRange(2, 0);
		int enemyYcrd = randomNumbrFrmRange(2, 1);
		
		if(enemyXcrd == 0 && enemyYcrd == 0)
		{
			enemyYcrd = randomNumbrFrmRange(2, 1);
		}
		
		String enemyCoords = "(" + enemyXcrd + "," + enemyYcrd + ")";
		
		
		if(targetXcrd == (enemyXcrd) && targetYcrd == enemyYcrd || enemyXcrd == 0 && enemyYcrd == 0){ enemyCoords = generateEnemyCoords(targetXcrd,targetYcrd);} //Check for duplicates
		
		return enemyCoords;
	}

	public static void main(String[] args) {
		
		
		String str = "45678912";
		
		if(str.length() % 2 == 0)//THE LENGTH OF THE COORDINATES STRING SHOULD NEVER BE ODD!
		{
			for(int i = 0; i < str.length(); i+= 2)
			{
				System.out.println( "(" +str.charAt(i) + "," +str.charAt(i + 1) + ")");
			}
			
			System.out.println("\n---------------------------------------------------------\n");
		}
		
		
		System.out.println("GalaxyExplorer spaceship up and running!, it is in a 3 x 3 galaxy at coordinates(0,0) facing North!\n");
		System.out.println("2|_|_|_|");
		System.out.println("1|_|_|_|");
		System.out.println("0|^| | |");
		System.out.println("  0 1 2 ");
		System.out.println("Enter commands l = to turn left, r = to turn right, f = to move forward and b = to move back!");
		System.out.println("Provide a sequence of commands for your galaxyExplorer to execute, that will enable it to reach the target!");
		
		
		
		for(int i = 0 ; i < 3; i++)
		{
			int targetXcrd = randomNumbrFrmRange(2, 0);
			int targetYcrd = randomNumbrFrmRange(2, 0);
			
			if(targetXcrd == 0 && targetYcrd == 0)
			{
				targetXcrd = 1;
			}
			
			String target = "(" + targetXcrd + ";" + targetYcrd + ")";
			
//			System.out.println("target coords" + target);
//			System.out.println(generateEnemyCoords(targetXcrd, targetYcrd));
			
			nextLevel(target, generateEnemyCoords(targetXcrd, targetYcrd));
		}
		
		//nextLevel("(2;2)", "(2,1)");

		inputScanner.close();
		
	}

}
