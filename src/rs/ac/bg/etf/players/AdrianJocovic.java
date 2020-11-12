package rs.ac.bg.etf.players;

public class AdrianJocovic extends Player {

		private int counter = 0;
		
		private char whoIsMyOponent = '?';
		
		private int potential = -1;
		
		private int flagCopyCat = 0;
		private int flagForgiver = 0;
		
		@Override
		public Move getNextMove() {
			if(counter==0) { 
				counter++; 
				return Move.PUT1COIN; 
			}
			else if(whoIsMyOponent == 'S') return Move.DONTPUTCOINS;
			else if(whoIsMyOponent == 'G') return Move.DONTPUTCOINS;
			else if(whoIsMyOponent == 'A') return Move.DONTPUTCOINS;
			else if(whoIsMyOponent == 'W') return Move.PUT2COINS;
			else if(whoIsMyOponent == 'C') {
				if(flagForgiver == 0) { flagForgiver = 1; return Move.PUT2COINS; }
				else if(flagForgiver == 1) { flagForgiver = 0; return Move.DONTPUTCOINS; }
			}
			else if(whoIsMyOponent =='F') {
				if(flagForgiver == 0) { flagForgiver = 1; return Move.PUT2COINS; }
				else if(flagForgiver == 1) { flagForgiver = 0; return Move.DONTPUTCOINS; }
			}
			else {
				
				Player.Move move1 = opponentMoves.get(opponentMoves.size()-1);
				
				// optimizovati if-ove posle
				
				if(move1 == Player.Move.DONTPUTCOINS && counter==1) { // Stinger -> GiveHim 0
					counter++;
					potential = 0;
					whoIsMyOponent = 'S';
					return Move.DONTPUTCOINS;
				}
				
				
				
				else if(move1 == Player.Move.PUT1COIN && counter==1) { // Copycat / Forgiver / ME
					counter++;
					potential = 1;
					return Move.DONTPUTCOINS;
				}
				else if(move1 == Player.Move.DONTPUTCOINS && counter == 2 && potential == 1) {
					counter++;
					whoIsMyOponent = 'W';
					return Move.PUT2COINS;
				}
				else if(move1 == Player.Move.PUT1COIN && counter == 2 && potential == 1) {
					counter++;
					return Move.PUT2COINS;
				}
				else if(move1 == Player.Move.PUT1COIN && counter == 3 && potential == 1) {
					counter++;
					whoIsMyOponent = 'F';
					return Move.DONTPUTCOINS;
				}
				else if(move1 == Player.Move.DONTPUTCOINS && counter==3 && potential == 1) {
					counter++;
					whoIsMyOponent = 'C';
					return Move.DONTPUTCOINS;
				}
				
				else if(move1 == Player.Move.PUT2COINS && counter==1) { // Goody / Avenger -> GiveHim 0
					counter++;
					potential = 2;
					return Move.DONTPUTCOINS;
				}
				else if(move1 == Player.Move.PUT2COINS && counter == 2 && potential == 2) { // Goody
					counter++;
					whoIsMyOponent = 'G';
					return Move.DONTPUTCOINS; 
				}
				else if(move1 == Player.Move.PUT1COIN && counter == 2 && potential == 2) { // Avenger
					counter ++;
					whoIsMyOponent = 'A';
					return Move.DONTPUTCOINS; 
				}
				
				
				
				
				
				
				else if(move1 == Player.Move.PUT1COIN && counter==2 && potential == 1) { // Copycat / Forgiver / ME
					counter++;
					return Move.PUT2COINS;
				}
				
				else if(move1 == Player.Move.PUT2COINS && counter==2 && potential == 1) { // Copycat / ME
					counter++;
					whoIsMyOponent = 'C';
					return Move.PUT2COINS;
				}
				
				
				else if(move1 == Player.Move.PUT1COIN && counter==2 && potential == 1) { // Forgiver / ME
					counter++;
					whoIsMyOponent = 'F';
					return Move.PUT2COINS;
				}
				
				
				
			}
			return Move.PUT2COINS; // izmeni
		}
		
		public void resetPlayerState() {
			counter = 0;
			whoIsMyOponent = '?';
			potential = -1;
			flagCopyCat = 0;
			flagForgiver = 0;
		}

	}