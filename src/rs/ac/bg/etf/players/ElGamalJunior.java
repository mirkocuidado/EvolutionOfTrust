package rs.ac.bg.etf.players;

public class ElGamalJunior extends Player {

		private int counter = 0;
		
		private char whoIsMyOponent = '?';
		
		private int potential = -1;
		private int flagForgiverCopyCat = 0;
		
		private int error = 0;
		
		@Override
		public Move getNextMove() {
			
			if(counter==0) { 
				counter++; 
				return Move.PUT1COIN; 
			}
			
			Player.Move move1 = opponentMoves.get(opponentMoves.size()-1);
			
			if(whoIsMyOponent == 'S') return Move.DONTPUTCOINS;
			else if(whoIsMyOponent == 'G') return Move.DONTPUTCOINS;
			else if(whoIsMyOponent == 'A') return Move.DONTPUTCOINS;
			else if(whoIsMyOponent == 'W') return Move.PUT2COINS;
			else if(whoIsMyOponent =='C') {
				if(flagForgiverCopyCat == 0) { 
					flagForgiverCopyCat = 1; 
					return Move.DONTPUTCOINS; 
				}
				else if(flagForgiverCopyCat == 1) { 
					flagForgiverCopyCat = 0; 
					return Move.PUT2COINS; 
				}
			}
			
			
			else {
				
				/********** STINGER **********/
				if( counter==1 && move1 == Player.Move.DONTPUTCOINS) { // Stinger -> GiveHim 0
					counter++;
					potential = 0;
					return Move.DONTPUTCOINS;
				}
				else if(counter==2 && potential == 0 && move1==Player.Move.DONTPUTCOINS) {
					whoIsMyOponent = 'S';
					return Move.DONTPUTCOINS;
				}
				
				/********** GOODY OR AVENGER **********/
				else if(move1 == Player.Move.PUT2COINS && counter==1 && error == 0) { // Goody or Avenger -> Give them 0
					counter++;
					potential = 2;
					return Move.DONTPUTCOINS;
				}
				else if(move1 == Player.Move.PUT2COINS && counter == 2 && potential == 2 && error == 0) { // Goody
					counter++;
					whoIsMyOponent = 'G';
					return Move.DONTPUTCOINS; 
				}
				else if(move1 == Player.Move.PUT1COIN && counter == 2 && potential == 2 && error == 0) { // Avenger
					counter ++;
					whoIsMyOponent = 'A';
					return Move.DONTPUTCOINS; 
				}
				
				/********** COPYCAT OR FORGIVER OR MYSELF **********/
				else if(counter==1 && error == 0 && move1 == Player.Move.PUT1COIN) { // Copycat / Forgiver / ME
					counter++;
					potential = 1;
					return Move.DONTPUTCOINS;
				}
				else if( counter == 2 && potential == 1 && error == 0 && move1 == Player.Move.DONTPUTCOINS) { // ME
					counter++;
					whoIsMyOponent = 'W';
					return Move.PUT2COINS;
				}
				/********** COPYCAT OR FORGIVER **********/
				else if(counter == 2 && potential == 1 && error == 0 && move1 == Player.Move.PUT1COIN) { // Copycat / Forgiver
					counter++;
					whoIsMyOponent = 'C';
					return Move.PUT2COINS;
				}
			}
			
			/********** UNFORSEEN ERRORS! **********/
			return Move.DONTPUTCOINS; 
		}
		
		public void resetPlayerState() {
			counter = 0;
			whoIsMyOponent = '?';
			potential = -1;
			flagForgiverCopyCat = 0;
			error = 0;
		}
	}