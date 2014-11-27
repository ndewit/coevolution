package figure2;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class Coevolution2 {

	Population pop1;
	Population pop2;
	
	Population newPop;
	
	private static final double mutationRate = 0.0025;
	
	Random r = new Random();
	
	int nbGenerations = 600;
	
	int count = 0;
	
	double[][] objectiveFitness = new double[600][2];
	double[][] subjectiveFitness = new double[600][2];
	
	public Coevolution2() {
		pop1 = new Population("allZeros");
		pop2 = new Population("allZeros");
		
		while (count < 600){
			executeGeneration();
			count++;
		}
		
		
		System.out.println("--------------------------FINAL RESULT-----------------");
		System.out.println(pop1);
		System.out.println(pop2);
	
		try { writeInFile(); } catch (Exception e) {	e.printStackTrace();}
	}
	
	public void printStatus() {
		String s = "Gen " + count;
		objectiveFitness[count][0] =  pop1.getAvrObjFitness();
		objectiveFitness[count][1] =  pop2.getAvrObjFitness();
		subjectiveFitness[count][0] = pop1.getAvrSubjFitness();
		subjectiveFitness[count][1] = pop2.getAvrSubjFitness();
		s += " // Pop 1 : Obj " + String.format("%.2f", pop1.getAvrObjFitness()) + " | Subj " + String.format("%.2f",pop1.getAvrSubjFitness());
		s += " // Pop 2 : Obj " + String.format("%.2f", pop2.getAvrObjFitness()) + " | Subj " + String.format("%.2f",pop2.getAvrSubjFitness());
		System.out.println(s);
	}
	
	public void writeInFile() throws FileNotFoundException, UnsupportedEncodingException {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.FRENCH);
		
		String number = "3";
		
		PrintWriter pop1File = new PrintWriter("coevolution" + number + "-obj1.txt", "UTF-8");
		for (int i=0 ; i < nbGenerations ; i++){
			pop1File.println(nf.format(objectiveFitness[i][0]));
		}
		pop1File.close();
		
		PrintWriter pop2File = new PrintWriter("coevolution" + number + "-obj2.txt", "UTF-8");
		for (int i=0 ; i < nbGenerations ; i++){
			pop2File.println(nf.format(objectiveFitness[i][1]));
		}
		pop2File.close();
	
		PrintWriter subj1File = new PrintWriter("coevolution" + number + "-subj1.txt", "UTF-8");
		for (int i=0 ; i < nbGenerations ; i++){
			subj1File.println(nf.format(subjectiveFitness[i][0]));
		}
		subj1File.close();
		
		PrintWriter subj2File = new PrintWriter("coevolution" + number + "-subj2.txt", "UTF-8");
		for (int i=0 ; i < nbGenerations ; i++){
			subj2File.println(nf.format(subjectiveFitness[i][1]));
		}
		subj2File.close();
	}
	
	public void executeGeneration() {
		Individual a; Individual b;
		List<Individual> Sa; List<Individual> Sb;
		
		//Fill with scores
		for (int i=0 ; i < pop1.size() ; i++) {
			a = pop1.getIndividual(i);
			Sa = pop2.getSample();
			pop1.setScore(i, calculateFitness(a, Sa)+1);
			
			b = pop2.getIndividual(i);
			Sb = pop1.getSample();
			pop2.setScore(i, calculateFitness(b, Sb)+1);
		}
		
		prepareRouletteSelection(pop1);
		prepareRouletteSelection(pop2);
		
		printStatus();
		
		Population tempPop1 = new Population();
		Population tempPop2 = new Population();
		
		for (int i=0 ; i < pop1.size() ; i++) {
			Individual select = getRouletteIndividual(pop1);
			tempPop1.add(mutateIndiv(select));
		}
		
		for (int i=0 ; i < pop1.size() ; i++) {
			Individual select = getRouletteIndividual(pop2);
			tempPop2.add(mutateIndiv(select));
		}
		pop1 = tempPop1;
		pop2 = tempPop2;
	}
	
	
	public void prepareRouletteSelection(Population pop) {
		int total = 0;
		for (int i=0 ; i < pop.size() ; i++) {
			total += pop.getScore(i);
		}
		
		double current = 0;
		for (int i=0 ; i < pop.size() ; i++) {
			current += (pop.getScore(i)*1.0)/total;
			pop.setDistribProba(i, current);
		}
		pop.setDistribProba(pop.size()-1, 1);
		
		//System.out.println("Scores : " + Arrays.toString(pop.getScoreVector()));
		//System.out.println("DistribProba : " + Arrays.toString(pop.getDistribProbaVector()));
	}
	
	public Individual getRouletteIndividual(Population pop) {
		double val = r.nextDouble();
		boolean found = false;
		int i = 0;
		while (!found) {
			if (val <= pop.getDistribProba(i)) {found = true;}
			else{ i++; }
		}
		
		return pop.getIndividual(i);
	}
	
	
	public int calculateFitness(Individual a, List<Individual> Sa) {
		int tot = 0;
		for(int i=0 ; i < Sa.size() ; i++) {
			if (a.getValue() > Sa.get(i).getValue()){
				tot++;
			}
		}
		return tot;
	}
	
	public Individual mutateIndiv(Individual oldIndiv) {
		Individual newIndiv = new Individual(oldIndiv);
		
		for (int i=0 ; i < oldIndiv.getLength() ; i++) {
			if (r.nextDouble() <= mutationRate) {
				newIndiv.flipBit(i);
			}
		}
		
		return newIndiv;
	}

}
