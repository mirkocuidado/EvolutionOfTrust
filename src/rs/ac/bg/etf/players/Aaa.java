package rs.ac.bg.etf.players;

public class Aaa extends Player {

		private int counter = 0;
		
		private char whoIsMyOponent = '?';
		
		private int potential = -1;
		
		private int flagCopyCat = 0;
		private int flagForgiver = 0;
		
		private int error = 0;
		
		private int counterOfBadness = 0;
		
		@Override
		public Move getNextMove() {
			
			if(counter==0) { 
				counter++; 
				return Move.PUT1COIN; 
			}
			
			Player.Move move1 = opponentMoves.get(opponentMoves.size()-1);
			
			if(move1 == Move.DONTPUTCOINS) {
				counterOfBadness ++;
				if(counterOfBadness >2) whoIsMyOponent = 'S';  // doesn't have to be Stinger, but he's mean 
			}
			else counterOfBadness = 0;
			
			
			if(whoIsMyOponent == 'S') return Move.DONTPUTCOINS;
			else if(whoIsMyOponent == 'G') return Move.DONTPUTCOINS;
			else if(whoIsMyOponent == 'A') return Move.DONTPUTCOINS;
			else if(whoIsMyOponent == 'W') return Move.PUT2COINS;
			else if(whoIsMyOponent == 'C') {
				// either play 2-2-2-2 with him or 2-0-2-0-2-0 or this version
				if(flagCopyCat == 0) { flagCopyCat = 1; return Move.DONTPUTCOINS; }
				else if(flagCopyCat == 1) { flagCopyCat = 0; return Move.DONTPUTCOINS; }
				
			}
			else if(whoIsMyOponent =='F') {
				if(flagForgiver == 0) { flagForgiver = 1; return Move.PUT2COINS; }
				else if(flagForgiver == 1) { flagForgiver = 0; return Move.DONTPUTCOINS; }
			}
			else {
				
				
				
				// optimizovati if-ove posle
				
				if(move1 == Player.Move.DONTPUTCOINS && counter==1) { // Stinger -> GiveHim 0
					counter++;
					potential = 0;
					return Move.DONTPUTCOINS;
				}
				else if(move1==Player.Move.DONTPUTCOINS && counter==2 && potential == 0) {
					whoIsMyOponent = 'S';
					return Move.DONTPUTCOINS;
				}
				
				
				
				else if(move1 == Player.Move.PUT1COIN && counter==1 && error == 0) { // Copycat / Forgiver / ME
					counter++;
					potential = 1;
					return Move.PUT2COINS;
				}
				else if(move1 == Player.Move.PUT2COINS && counter == 2 && potential == 1 && error == 0) { // Ja
					counter++;
					whoIsMyOponent = 'W';
					return Move.PUT2COINS;
				}
				else if(move1 == Player.Move.PUT1COIN && counter == 2 && potential == 1 && error == 0) { // Copycat / Forgiver
					counter++;
					return Move.DONTPUTCOINS;
				}
				
				
				
				
				
				// NASILNO DODATO!
				else if(move1 == Player.Move.DONTPUTCOINS && counter == 2 && potential == 1) { // ERROR IN STEP Forgiver/Copycat/ME
					System.out.println("OVDE 1!");
					counter++;
					error = 1;
					return Move.DONTPUTCOINS;
				}
				else if(move1 == Player.Move.PUT2COINS && error == 1 && counter == 2 && potential == 1) {
					System.out.println("OVDE 2!");
					counter++;
					return Move.PUT2COINS;
				}
				else if(move1 == Player.Move.DONTPUTCOINS && error == 1 && counter == 3 && potential == 1) {
					System.out.println("OVDE 3!");
					counter++;
					whoIsMyOponent = 'C';
					return Move.DONTPUTCOINS;
				}
				else if(move1 == Player.Move.PUT2COINS && error == 1 && counter == 3 && potential == 1) {
					System.out.println("OVDE 4!");
					counter++;
					return Move.DONTPUTCOINS;
				}
				else if(move1 == Player.Move.DONTPUTCOINS && error == 1 && counter == 4 && potential == 1) {
					System.out.println("OVDE 5!");
					counter++;
					whoIsMyOponent = 'W';
					return Move.PUT2COINS;
				}
				else if(move1 == Player.Move.PUT2COINS && error == 1 && counter == 4 && potential == 1) {
					System.out.println("OVDE 6!");
					counter++;
					whoIsMyOponent = 'F';
					return Move.PUT2COINS;
				}
				
				
				
				
				
				else if(move1 == Player.Move.PUT2COINS && counter == 3 && potential == 1 && error == 0) { // Copycat / Forgiver
					counter++;
					return Move.PUT2COINS;
				}
				else if(move1 == Player.Move.PUT2COINS && counter == 4 && potential == 1 && error == 0) { // Copycat / Forgiver
					counter++;
					whoIsMyOponent = 'F';
					return Move.PUT2COINS;
				}
				else if(move1 == Player.Move.DONTPUTCOINS && counter==4 && potential == 1 && error == 0) {
					counter++;
					whoIsMyOponent = 'C';
					return Move.DONTPUTCOINS;
				}
				// NASILNO DODATO! ---> ERROR IN STEP Forgiver/Copycat
				else if(move1 == Player.Move.PUT1COIN && counter==4 && potential == 1 && error == 0) {
					whoIsMyOponent = 'C'; // Play Copycat strategy
					return Move.DONTPUTCOINS;
				}
				
				
				
				
				
				
				else if(move1 == Player.Move.PUT2COINS && counter==1 && error == 0) { // Goody / Avenger -> GiveHim 0
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
				
				
				
				
				
				
				else if(move1 == Player.Move.PUT1COIN && counter==2 && potential == 1 && error == 0) { // Copycat / Forgiver / ME
					counter++;
					return Move.PUT2COINS;
				}
				
				else if(move1 == Player.Move.PUT2COINS && counter==2 && potential == 1 && error == 0) { // Copycat / ME
					counter++;
					whoIsMyOponent = 'C';
					return Move.PUT2COINS;
				}
				
				
				else if(move1 == Player.Move.PUT1COIN && counter==2 && potential == 1 && error == 0) { // Forgiver / ME
					counter++;
					whoIsMyOponent = 'F';
					return Move.PUT2COINS;
				}
				
				
				
			}
			return Move.DONTPUTCOINS; // izmeni
		}
		
		public void resetPlayerState() {
			counter = 0;
			whoIsMyOponent = '?';
			potential = -1;
			flagCopyCat = 0;
			flagForgiver = 0;
			error = 0;
			counterOfBadness = 0;
		}

	}