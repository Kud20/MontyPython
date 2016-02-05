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

public class Statistics {
	/**Declare variables*/
	private int gamesPlayed,switchStrategy,stayStrategy;
	private int [] prizeDoors,playerDoors,hostDoors;
	
	public Statistics() {
		/**Initialize variables*/
		this.gamesPlayed = 0;
		this.switchStrategy = 0;
		this.stayStrategy = 0;
		prizeDoors = new int [] {0,0,0};
		playerDoors = new int [] {0,0,0};
		hostDoors = new int [] {0,0,0};
	
	}
	
	public void updateStatistics(Door door1,Door door2, Door door3){
		/**Increment played games*/
		
		gamesPlayed++;
		oneDoor(door1,0);
		oneDoor(door2,1);
		oneDoor(door3,2);
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
		
		/**Implement stayStrategy/switchStrategy counter*/
		if (door.hasPrize() == door.isChosen()){
			stayStrategy++;
		}else {
			switchStrategy++;
		}
	}
	public String toString(){
		return "The number of games played: " + gamesPlayed + "\n" +
				"Staying strategy won " + stayStrategy + " games" + "\n" +
				"Switching strategy won " + switchStrategy + " games" + "\n" +
				"Selected Doors: " + "\n";
	
	}

}
