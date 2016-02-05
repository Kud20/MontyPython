/**Import java utilities */

import java.util.Random;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class MontyHall {
	/**Declare variables*/
	Door d1;
	Door d2;
	Door d3;
	public MontyHall(){
		/**Initialize class variables*/
		d1 = new Door('A');
		d2 = new Door('B');
		d3 = new Door('C');
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
 	
 		if(commandLine == true) {
			System.out.println(stats.toString());
		} else {
			JOptionPane.showMessageDialog (null,stats.toString(), "Enter a valid input: ", JOptionPane.INFORMATION_MESSAGE);
		}
 		System.out.println(stats.toString());
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
		d1.reset();
		d2.reset();
		d3.reset();
		
		Door prizeDoor = pickADoor();
		Door playerDoor = pickADoor();
		Door hostDoor  = openOtherDoor(prizeDoor,playerDoor);
		
		/**Print output to user*/
		System.out.println("The prize was behind " + prizeDoor.doorName);
		System.out.println("The player selected " + playerDoor.doorName);
		System.out.println("The host opened " + hostDoor.doorName);
		
		if(playerDoor != prizeDoor) {
			System.out.println("Switching strategy would have won");
		}
		else {
			System.out.println("Switching strategy would have lost");
		}
	}
	/** 
     * Simulates a random selection of one of the three doors.
     * @return the door randomly selected  
     */
	private Door pickADoor(){
		
		Random randomDoor = new Random();
		
		if(randomDoor.nextInt(3) == 0){
			return d1;
		} else if(randomDoor.nextInt(3) == 1) {
			return d2;
		}else {
			return d3;
		}
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
	private Door openOtherDoor(Door prizeDoor, Door selectedDoor) {
		Door randomizedDoor;
		do {
			randomizedDoor = pickADoor();
		} while (randomizedDoor == prizeDoor || randomizedDoor == selectedDoor);
		
		return randomizedDoor;
	}
	
	/**Simulates one game of Monty Hall
	*/
	public static void main(String[] args) {
		MontyHall montyHall;
		int numberOfGames;
		boolean commandLine = false;
		
		if (args.length == 1) {
			numberOfGames = Integer.parseInt(args[0]);
			commandLine = true;
		} else {
			numberOfGames = Integer.parseInt(JOptionPane.showInputDialog("Input the number of games to play", "1000"));
		}
		StudentInfo.main(args);
		montyHall = new MontyHall();		
		montyHall.runGames(numberOfGames, commandLine);
	}
}
