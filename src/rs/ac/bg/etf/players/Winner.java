package rs.ac.bg.etf.players;

public class Winner extends Player {
	
	private int counter = 0;
	
	private char whoIsMyOponent = '?';
	
	private int potential = -1;
	
	private int flagCopyCat = 0;
	private int flagForgiver = 0;
	
	@Override
	public Move getNextMove() {
		if(counter==0) { 
			counter++; 
			return Move.PUT2COINS; 
		}
		else if(whoIsMyOponent == 'S') return Move.DONTPUTCOINS;
		else if(whoIsMyOponent == 'G') return Move.DONTPUTCOINS;
		//else if(whoIsMyOponent == 'A') return Move.PUT1COIN;
		else if(whoIsMyOponent == 'A') return Move.DONTPUTCOINS;
		else if(whoIsMyOponent == 'W') return Move.PUT2COINS;
		
		 
		 
		 
		else if(whoIsMyOponent == 'C') {
			if(flagCopyCat == 0) { flagCopyCat = 1; return Move.PUT2COINS; }
			else { flagCopyCat = 0; return Move.PUT1COIN; }
		}
		
		/*
		 * //ZA OVO IDE ONO DOLE GDE JE whoIsMyOponent = 'C'; STO JE ZAKOMENTARISANO 
		else if(whoIsMyOponent == 'C') {
			if(flagCopyCat == 0) { flagCopyCat = 1; return Move.PUT2COINS; }
			else { flagCopyCat = 0; return Move.DONTPUTCOINS; }
		}*/
		
		/* 2->0->2->0->2->0->2->0->2->0->2->0->2->0
		 
		  	else if(whoIsMyOponent =='F') {
			if(flagForgiver == 0) { flagForgiver = 1; return Move.PUT2COINS; }
			else { flagForgiver = 0; return Move.DONTPUTCOINS; }
		}*/
		
		// 1->2->0->1->2->0->1->2->0->1->2->0->1->2->0->1->2->0
		
		else if(whoIsMyOponent =='F') {
			if(flagForgiver == 0) { flagForgiver = 1; return Move.PUT1COIN; }
			else if(flagForgiver == 1) { flagForgiver = 2; return Move.PUT2COINS; }
			else { flagForgiver = 0; return Move.DONTPUTCOINS; }
		}
		/*else if(whoIsMyOponent =='F') {
			if(flagForgiver == 0) { flagForgiver = 1; return Move.DONTPUTCOINS; }
			else if(flagForgiver == 1) { flagForgiver = 2; return Move.PUT2COINS; }
			else { flagForgiver = 0; return Move.DONTPUTCOINS; }
		}*/
		else {
			
			Player.Move move1 = opponentMoves.get(opponentMoves.size()-1);
			
			// optimizovati if-ove posle
			
			if(move1 == Player.Move.DONTPUTCOINS && counter==1) {
				counter++;
				potential = 0;
				whoIsMyOponent = 'S';
				return Move.DONTPUTCOINS;
			}
			else if(move1 == Player.Move.PUT1COIN && counter==1) {
				counter++;
				potential = 1;
				return Move.DONTPUTCOINS;
			}
			else if(move1 == Player.Move.PUT2COINS && counter==1) {
				counter++;
				potential = 2;
				return Move.PUT1COIN;
			}
			else if(move1 == Player.Move.PUT2COINS && counter==2 && potential == 1) {
				counter++;
				return Move.PUT2COINS;
			}
			else if(move1 == Player.Move.DONTPUTCOINS && counter==3 && potential == 1) {
				counter++;
				whoIsMyOponent = 'C';
				return Move.PUT1COIN;
				//return Move.DONTPUTCOINS;
			}
			else if(move1 == Player.Move.PUT2COINS && counter == 3 && potential == 1) {
				counter++;
				whoIsMyOponent = 'F';
				return Move.DONTPUTCOINS;
			}
			else if(move1 == Player.Move.PUT2COINS && counter == 2 && potential == 2) {
				counter++;
				return Move.PUT1COIN; 
			}
			else if(move1 == Player.Move.PUT2COINS && counter == 3 && potential == 2) {
				counter ++;
				whoIsMyOponent = 'G';
				return Move.DONTPUTCOINS;
			}
			else if(move1 == Player.Move.PUT1COIN && counter == 3 && potential == 2) {
				counter ++;
				return Move.PUT2COINS;
			}
			else if(move1 == Player.Move.PUT2COINS && counter == 4 && potential == 2) {
				counter ++;
				whoIsMyOponent = 'W';
				return Move.PUT2COINS;
			}
			else if(move1 == Player.Move.PUT1COIN && counter == 4 && potential == 2) {
				counter ++;
				whoIsMyOponent = 'A';
				return Move.DONTPUTCOINS; //PUT1 po planu
			}
		}
		return Move.PUT2COINS;
	}
	
	public void resetPlayerState() {
		counter = 0;
		whoIsMyOponent = '?';
		potential = -1;
		flagCopyCat = 0;
		flagForgiver = 0;
	}

}
