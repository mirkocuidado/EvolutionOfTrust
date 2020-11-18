package rs.ac.bg.etf.players;

public class CheckForErrors extends Player {

	int counter = 0;
	
	int cheated = 0;
	
	@Override
	public Move getNextMove() {
		
		counter ++;
		
		if(counter == 1) return Move.PUT1COIN;
		
		Move m = opponentMoves.get(opponentMoves.size()-1);
		
		if(m==Move.DONTPUTCOINS) cheated = 1;
		
		if(counter==2) return Move.DONTPUTCOINS;
		else if(counter ==3) return Move.PUT1COIN;
		if(counter==4) return Move.PUT1COIN;
		
		
		if(cheated==1) return opponentMoves.get(opponentMoves.size()-1);
		else return Move.DONTPUTCOINS;
	}

	public void resetPlayerState() {
		counter = 0;
		cheated = 0;
	}
	
	
}
