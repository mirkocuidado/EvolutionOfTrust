package rs.ac.bg.etf.players;

public class Helper_1212 extends Player{

	int counter = 0;
	
	@Override
	public Move getNextMove() {
		
		if(counter == 0) { 
			counter = 1; 
			return Move.PUT1COIN;
		}
		else {
			counter = 0;
			return Move.PUT2COINS;
		}
	}

	public void resetPlayerState() {
		counter = 0;
	}
	
}
