package extension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Population {

	private List<Individual> pop = new ArrayList<Individual>();
	private static final int populationSize = 50;
	private static final int sampleSize = 10;
	private double[] score = new double[populationSize];
	private double[] distribProba = new double[populationSize];
	
	public Population() {}
	
	public Population(String type) {
		if ("allZeros".equals(type)){
			for(int count=0; count < this.populationSize ; count++){ pop.add(new Individual("allZeros")); }
		}
		else if ("allOnes".equals(type)){
			for(int count=0; count < this.populationSize ; count++){ pop.add(new Individual("allOnes")); }			
		}
		else if ("random".equals(type)){
			for(int count=0; count < this.populationSize ; count++){ pop.add(new Individual("random")); }
		}
	}
	
	public static int getSampleSize() {return sampleSize;}
	
	public String toString() {
		String res = "";
		int cnt = 1;
		for (Individual indiv : pop) {
			res += String.valueOf(cnt) + ". " + indiv.toString() + " (" + indiv.getValue() + ")\n";
			cnt++;
		}
		return res;
	}
	
	//TODO Can we take the same element multiple times in a sample?
	public List<Individual> getSample(){
		List<Individual> list = new ArrayList<Individual>();
		for (int i=0 ; i < sampleSize ; i++){
			list.add(pop.get(getRandomIndex(sampleSize)));
		}
		return list;
	}

	public Individual getRandomIndividual(){
		return pop.get(getRandomIndex(populationSize));
	}
	
	public int getRandomIndex(int max) {
		Random r = new Random();
		return r.nextInt(max);
	}
	
	public int size(){
		return populationSize;
	}

	public void setIndividual(int a, Individual newIndiv) {
		pop.set(a, newIndiv);
	}
	
	public void add(Individual indiv){
		pop.add(indiv);
	}
	
	public Individual getIndividual(int a){
		return pop.get(a);
	}
	
	public double getScore(int i) {
		return score[i];
	}
	
	public void setScore(int i, double d) {
		score[i] = d;
	}
	
	public double[] getScoreVector() {
		return score;
	}
	
	public double getDistribProba(int i) {
		return distribProba[i];
	}
	
	public double[] getDistribProbaVector(){
		return distribProba;
	}
	
	public void setDistribProba(int i, double value){
		distribProba[i] = value;
	}
	
	public double getAvrScore() {
		double total = 0;
		for (int i=0 ; i < populationSize ; i++){ total += (score[i]); }
		return total/populationSize;
	}
	
	public double getAvrSubjFitness() {
		return this.getAvrScore();
	}
	
	public double getAvrObjFitness() {
		double total = 0;
		for (int i=0 ; i < populationSize ; i++){ total += pop.get(i).getValue(); }
		return total/populationSize;
	}

}
