/**Import java utilities */
import java.util.Random;
import javax.swing.JOptionPane;


public class MontyHall {
	/**Declare variables
	 * Use an array to hold the doors
	 */
	Door [] doors = new Door [10];
	
	public MontyHall(){
		/**Initialize class variables*/
		doors[0] = new Door('A');
		doors[1]= new Door('B');
		doors[2] = new Door('C');
		for(int i = 0; i < doors.length; i++)
		{
			doors[i] = new Door('Z');
		}
	}
	/** 
     * Runs a series of Monty Hall games and displays the resulting statistics in a 
     * message dialog or on the standard output  
     * 
     * @param numberOfGames the number of games to simulate
     * @param commandLine if true, sends the results to standard output, else uses a message dialog
     */
	public void runGames(int numberOfGames, boolean commandLine){
 		Statistics stats = new Statistics();
 		/**For each game call the updateStatistics function and oneGame*/
 		for(int i = 0; i < numberOfGames; i++)
 		{
 			oneGame();
 			stats.updateStatistics(doors);
 		}/**If there is input in the commandLine, print out the statistics, if not, display dialog box asking for valid input*/
 		if(commandLine == true) {
			System.out.println(stats.toString());
		} else {
			JOptionPane.showMessageDialog (null,stats.toString(), "Enter a valid input: ", JOptionPane.INFORMATION_MESSAGE);
		}
 		/**Print out statistics*/
 		//System.out.println(stats.toString());
	}
	/** 
     * Simulates one Monty Hall game.  
     * <ol>
     * <li>Resets all three doors</li>
	 * <li>Simulates the selection of one of the doors by the player</li>
	 * <li>Simulates opening of an empty door by the host</li>
	 * <li>prints the outcome for switching and not switching door to standard output</li>
	 */
	public void oneGame(){
		/**reset doors*/
		for(int i =0;i<doors.length;i++)
		{
			//System.out.println(i);
			doors[i].reset();
		}
		/**Sets a door for the prizeDoor*/
		Door prizeDoor = pickADoor();
		prizeDoor.setPrize();
		/**Sets a door for the playerDoor*/
		Door playerDoor = pickADoor();
		playerDoor.choose();
		/**Sets a door for the hostDoor*/
		Door[] hostDoor  = openOtherDoor(prizeDoor,playerDoor);
	}
	/** 
     * Simulates a random selection of one of the three doors.
     * @return the door randomly selected  
     */
	private Door pickADoor(){
		Random randomDoor = new Random();
		return doors[randomDoor.nextInt(doors.length)];
	}
	/** 
     * Simulates the opening of one of the other doors once the player selected one.
     * It should  open a door chosen randomly among the ones that don't have the prize and
     * that are not selected by the player
     * 
     *   @param prizeDoor the door that hides the prize
     *   @param selectedDoor the door that was selected by the player
     *   @return the door opened
     */
	private Door[] openOtherDoor(Door prizeDoor, Door selectedDoor) {
		
		int sum = 0;
		for(int i = 0; i < doors.length; i++)
		{
			if(doors[i].isChosen() || doors[i].hasPrize())
			{
				sum = sum + 1;
			}
		}
		Door[] doorsToOpean = new Door[doors.length-sum];
		sum = 0;
		for(int i = 0; i < doors.length; i++)
		{
			if(sum >= doorsToOpean.length)
			{
				break;
			}
			if(!doors[i].isChosen() && !doors[i].hasPrize())
			{
				doors[i].open();
				doorsToOpean[sum++] = doors[i];
			}
		}
		return doorsToOpean;
	}
	
	/**Simulates one game of Monty Hall
	*/
	public static void main(String[] args) {
		/**Declare variables*/
		MontyHall montyHall;
		int numberOfGames;
		boolean commandLine = false;
		
		if (args.length == 1) {
			numberOfGames = Integer.parseInt(args[0]);
			commandLine = true;
		} else {
			numberOfGames = Integer.parseInt(JOptionPane.showInputDialog("Input the number of games to play", "1000"));
		}
		//Run the game
		//StudentInfo.main(args);
		montyHall = new MontyHall();
		montyHall.runGames(numberOfGames, commandLine);
	}
}
