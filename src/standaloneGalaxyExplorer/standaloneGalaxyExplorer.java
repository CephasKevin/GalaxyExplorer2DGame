package standaloneGalaxyExplorer;


public class standaloneGalaxyExplorer {
	public static int xcd,ycd,universerows,universecols,preX,preY,badships;
	public static  String direcfacing,encounters = "";
	public static int foeshipcoords[];
	
	public static void Rotater()// This method rotates the ship 90 deg clockwise
	{
		switch(direcfacing)          
		{
		case "N":
			direcfacing = "E";
			break;
		case "E":
			direcfacing = "S";
			break;
		case "S":
			direcfacing = "W";
			break;
		case "W":
			direcfacing = "N";
			break;
		default:
			direcfacing = "N";
		}
	}
	public static void EnemyShipcoords(String badships)//This method takes every integer detected in the enemyShip string and places it into an array 
	{
		String enemycoord = "";
		int index = 0; 
		foeshipcoords = new int[badships.length() * 2];
		for(int i = 0;i<badships.length();i++){
			
			if(String.valueOf(badships.charAt(i)).contains("(")||String.valueOf(badships.charAt(i)).contains(",")){
				 for(int r = i+1;String.valueOf(badships.charAt(r)).matches("[0-9]*");r++)
				   {
					 enemycoord += String.valueOf(badships.charAt(r));       
				   }
				 foeshipcoords[index] = Integer.parseInt(enemycoord);
				 index++;
				 enemycoord = "";
				 				 
				 
			}
			}	
	}
	public static void warper()// the simple warper function allows the ship to warp in case the coordinates are greater than the universe dimensions
	{
		EnemyShipAvoid();
		if(ycd>(universecols-1))
		{
			ycd = 0;
		}
		else if(ycd<0)
		{
			ycd =  universecols-1;
		}
		if(xcd>(universerows-1))
		{
			xcd = 0;
		}
		else if(xcd<0)
		{
			xcd = universerows-1;
		}
		EnemyShipAvoid();
	}
	public static void EnemyShipAvoid()
	{
		for(int h = 0;h<foeshipcoords.length;h+=2)
		{
			if(foeshipcoords[h] == 0 && foeshipcoords[h+1] == 0) //Of course there will be blank elements in the array that has the enemyShip coordinates so if a combo of 0 and 0 are found it simply discards them
			{}
			else if(xcd == foeshipcoords[h] && ycd == foeshipcoords[h+1])
			{				
						if(!encounters.contains("("+xcd+";"+ycd+")")){encounters += "("+xcd+";"+ycd+")"; System.out.println("From other class: " + encounters);}//Check for repeats
						
						xcd = preX;
						ycd = preY;																	
			}
		}
	}
	public static String GalaxyExplorer(int x, int y, String enemyShips, String command){
		direcfacing = "N";
		encounters = "";
		xcd = 0;
		ycd = 0;
		universerows = x;//I used these variables(universe rows) so that I can refer to the galaxy dimensions throughout the program like in my warper method
		universecols = y;
		EnemyShipcoords(enemyShips);
		return executeCommand(command);
	}
	public static String executeCommand(String command){
		
		
			for(int i = 0;i<command.length();i++){
				char k = command.charAt(i);
				String currentcommand = String.valueOf(k);
				
				if(currentcommand.matches("r"))
				{
					Rotater();	//Provides the equivalent of one right turn 
				}
				else if(currentcommand.matches("l"))
				{
					Rotater();
					Rotater();	//Repeating a 90deg turn 3 times provides the equivalent of one left turn 
					Rotater();	
				}
				if((currentcommand.matches("f")&direcfacing.matches("N"))||(currentcommand.matches("b")&direcfacing.matches("S")))
				{
					preX=xcd;  //preX and preY represent the previous coordinates of the spaceship before making any changes, so that the coordinates can be reverted in case any unwanted movements are made
					preY=ycd;
					ycd++;
					warper();
				}
				if(currentcommand.matches("f")&direcfacing.matches("E")||currentcommand.matches("b")&direcfacing.matches("W"))
				{
					preX=xcd;
					preY=ycd;
					xcd++;
					warper();
				}
				if((currentcommand.matches("b")&direcfacing.matches("N"))||(currentcommand.matches("f")&direcfacing.matches("S")))
				{
					preX=xcd;
					preY=ycd;
					ycd--;
					warper();
				}
				if((currentcommand.matches("b")&direcfacing.matches("E"))||(currentcommand.matches("f")&direcfacing.matches("W")))
				{
					preX=xcd;
					preY=ycd;
					xcd--;
					warper();
				}
				
				}
		return "("+xcd+";"+ycd+";"+direcfacing+")"+encounters;
	}
}