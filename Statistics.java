/**
 * The class  <b>Statistics</b> accumulates information about a series of games:
 * <ol>
 * <li>Number of game played</li>
 * <li>Number of times the switching strategy was successful</li>
 * <li>Number of times the switching strategy was not successful</li>
 * <li>Number of times each door has the prize behind it</li>
 * <li>Number of times each door was chosen by the player</li>
 * <li>Number of times each door was open by the host</li>
 * </ol>*/
import java.io.FileWriter;
import java.io.IOException;

public class Statistics {
	/**Declare variables*/
	private int gamesPlayed,switchStrategy,stayStrategy;
	private int [] prizeDoors,playerDoors,hostDoors;
	
	public Statistics() {
		/**Initialize variables*/
		this.gamesPlayed = 0;
		this.switchStrategy = 0;
		this.stayStrategy = 0;
		/**Create an array for doors*/
		prizeDoors = new int [] {0,0,0};
		playerDoors = new int [] {0,0,0};
		hostDoors = new int [] {0,0,0};
	
	}
	/** 
     * Updates statistics after one game.
     *   @param door1 the first door in the game
     *   @param door2 the second door in the game
     *   @param door3 the third door in the game
     */
	public void updateStatistics(Door doors[]){
		/**For each door, assign an index
		 * Increment games played
		 */
		for(int i = 0; i < doors.length; i++)
		{
			oneDoor(doors[i],i);
		}
		
		gamesPlayed++;
	}
	
	private void oneDoor(Door door, int index){
		/**Implement prizeDoors,hostDoors,playerDoors*/
		/**If the door is the prizeDoor, increment prizeDoors*/
		if(door.hasPrize()) {
			prizeDoors[index]++;
		}
		/**If the door is the hostDoor, increment hostDoors*/
		if(door.isOpen()){
			hostDoors[index]++;
		}
		/**If the door is the playerDoors, increment playerDoors*/
		if(door.isChosen()){
			playerDoors[index]++;
		}
		
		/**If the door that has the prize == the door that has been chosen, increment Stay strategy
		 * 
		 */
		if (door.hasPrize() && door.isChosen()){
			stayStrategy++;
		}
		

	}
	/** 
    *  @return Returns the complete statistics information
    */
	public String toString(){
		/**Obtain # of times switchStrategy would have won */
		switchStrategy = gamesPlayed - stayStrategy; 
		
		/**Declare string variables*/
		String winningDoors ="";
		String selectedDoors = "";
		String openDoors = "";
		
		/**Keep track(+increment) of how many times each has a prize,is chosen by the player, and is chosen by the host*/
		for(int i = 0;i < prizeDoors.length; i++)
		{ 
			winningDoors += "door "+ (i+1) +" : "+prizeDoors[i]+ " (" +(int)((100)*(float)prizeDoors[i]/(float)gamesPlayed)+"%)\n"; 
	
		}
		
		for(int i = 0;i < playerDoors.length; i++)
		{
			selectedDoors+= "door "+ (i+1) +" : "+playerDoors[i]+ " (" +(int)((100)*(float)playerDoors[i]/(float)gamesPlayed)+"%)\n";
	
			
		}
		for(int i =0; i < hostDoors.length; i++)
		{
			openDoors  +="door "+ (i+1) +" : "+hostDoors[i]+ " (" +(int)((100)*(float)hostDoors[i]/(float)gamesPlayed)+"%)\n"; 
			
		}
		/**Get the percentage for switching/staying wins*/
		String switchPercentage = " ("+ (int)((100)*(float)switchStrategy/(float)gamesPlayed)+"%)\n";
		String stayPercentage = " ("+ (int)((100)*(float)stayStrategy/(float)gamesPlayed)+"%)\n";
		
		String a = toCSV();
		/**Return to user*/
		return "Number of games played: " + gamesPlayed + "\n" + "Stay strategy won: " + String.valueOf(stayStrategy) + " games"  + stayPercentage 
		 + "Switch strategy won: "+  String.valueOf(switchStrategy) + " games"  + switchPercentage 
		+"\n"+ winningDoors+ "\n" + selectedDoors + "\n"+ openDoors;
	}
	
	/** 
     *  @return Returns the complete statistics information in CSV format
     */
	public String toCSV(){
		String outputFile = 
		"Number of games"+","+gamesPlayed+","+"\n"+
		"NUmber of doors"+","+ 3 +","+"\n";
		try
		{
		FileWriter writer = new FileWriter("Results.csv");
		writer.append(outputFile);
		writer.flush();
		writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return outputFile;
		
	}
	/*
	//base template from http://www.mkyong.com/java/how-to-export-data-to-csv-file-java/
	private static void generateCsvFile(String sFileName)
	{
	try
	{
		FileWriter writer = new FileWriter(sFileName);
		try
		{
		writer.append("DisplayName");
		
	    //generate whatever data you want
		
	    writer.flush();
	    writer.close();
	}
	catch(IOException e)
	{
	     e.printStackTrace();
	} 
    }
}*/

}
