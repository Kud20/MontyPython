public class Door {
	/**Declare  variables*/
	private boolean open;
	private boolean prize;
	private boolean chosen;
	public char doorName;

	
	public Door(char name){
		 this.prize = false;
		 this.open = false;
		 this.chosen = false;
		 doorName = name;
	}
	public void reset(){ 
		 this.prize = false;
		 this.open = false;
		 this.chosen = false;
	}
	public void open(){
		this.open = true;
	}
	public boolean isOpen(){
		if(open == true){
			return true;
		} else {
			return false;
		}
	}
	public void setPrize(){
		if(prize == true ){
			this.prize = true;
		}
	}
	public boolean hasPrize(){
		return this.prize;
	}
	public void choose(){
		if (chosen == true){
			this.chosen = true;
		}
	}
	public boolean isChosen(){
		return this.chosen;
	}
	public char getName(){
		return this.doorName;
	}
}
