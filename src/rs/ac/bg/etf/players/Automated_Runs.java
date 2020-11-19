package rs.ac.bg.etf.players;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import rs.ac.bg.etf.engine.Game;

public class Automated_Runs {
	private static final String input= "C:/Users/38164/OneDrive/Desktop/EvolutionOfTrust/project/Scenario1_A.txt"; 
	private static final String output= "C:/Users/38164/OneDrive/Desktop/EvolutionOfTrust/project/out2.txt";
	private static String[] argsMain= {input, output};
	private static String getWinner() {
		String winner =null;
		File f = new File(output);
		try (Scanner s = new Scanner(f);){
			
			boolean flag=false;
			while(s.hasNextLine()) {
				String line= s.nextLine();
				if(flag) {winner=line;
				   flag=false;
				}
				else 
				if(line.contains("TOTAL POINTS")) {
					flag=true;
				}
			
				
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return winner;
		
		
	}
	public static void main(String[] args) {
		for(String s:args) {
			System.out.println(s);
		}
	int max=1000; // number of runs
	int cnt=0;
	for(int i=0;i<max;i++) {
	Game.main(argsMain);
	if(getWinner().contains("ElGamalJunior")) cnt++; //your player's name
	}
		System.out.println(cnt+"/"+max);

	}

}
