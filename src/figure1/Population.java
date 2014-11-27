package figure1;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Population {

	private List<Individual> pop = new ArrayList<Individual>();
	private static final int populationSize = 25;
	private static final int sampleSize = 15;
	
	public Population(String type) {
		if ("allZeros".equals(type)){
			for(int count=0; count < this.populationSize ; count++){ pop.add(new Individual("allZeros")); }
		}
		else if ("allOnes".equals(type)){
			for(int count=0; count < this.populationSize ; count++){ pop.add(new Individual("allOnes")); }			
		}
	}
	
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
	
	public int getSize(){
		return populationSize;
	}

	public void setIndividual(int a, Individual newIndiv) {
		pop.set(a, newIndiv);
	}
	
	public Individual getIndividual(int a){
		return pop.get(a);
	}
}
