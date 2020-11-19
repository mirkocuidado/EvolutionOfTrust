package rs.ac.bg.etf.players;

public class Helper_210210 extends Player{

	int counter = 0;
	
	@Override
	public Move getNextMove() {
		
		if(counter == 0) { 
			counter = 1; 
			return Move.PUT2COINS;
		}
		else if (counter == 1){
			counter = 2;
			return Move.PUT1COIN;
		}
		else if (counter == 2){
			counter = 0;
			return Move.DONTPUTCOINS;
		}
		return null;
	}

	public void resetPlayerState() {
		counter = 0;
	}
	
}
