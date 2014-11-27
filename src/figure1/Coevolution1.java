package figure1;
import java.util.List;


public class Coevolution1 {

	Population pop1;
	Population pop2;
	
	private static final double mutationRate = 0.005;
	
	public Coevolution1() {
		pop1 = new Population("allZeros");
		pop2 = new Population("allOnes");
		
		int count = 0;
		while (count < 600){
			executeGeneration();
			count++;
		}
		
		System.out.println(pop1);
		System.out.println(pop2);
	}
	
	public void executeGeneration() {
		
		for (int i=0; i < pop1.getSize() ; i++){
			pop1.setIndividual(i, mutateIndiv(pop1.getIndividual(i)));
			pop2.setIndividual(i, mutateIndiv(pop2.getIndividual(i)));
		}
		/*
		Individual a;
		Individual b;
		Individual parent;
		Individual child;
		List<Individual> Sa;
		List<Individual> Sb;
		
		//Population1
		a = pop1.getRandomIndividual();
		b = pop1.getRandomIndividual();
		Sa = pop2.getSample();
		Sb = pop2.getSample();
		
		//TODO ">=" ?
		if (calculateFitness(a, Sa) >= calculateFitness(b, Sb)) { parent = a; }
		else { parent = b; }
		
		child = mutateIndiv(parent);
		
		replaceIntoPopulation(pop1, child);
		
		//--------------------------------------
		//Population2
		a = pop2.getRandomIndividual();
		b = pop2.getRandomIndividual();
		Sa = pop1.getSample();
		Sb = pop1.getSample();
		
		//TODO ">=" ?
		if (calculateFitness(a, Sa) >= calculateFitness(b, Sb)) { parent = a; }
		else { parent = b; }
		
		child = mutateIndiv(parent);
		
		replaceIntoPopulation(pop2, child);
		*/
	}
	
	/**
	 * Selects an individual from the population through a tournament of size two (takes the worst one).
	 * The new individual replaces the selected one into the population.
	 */
	public void replaceIntoPopulation(Population pop, Individual newIndiv) {
		// Choose an individual to be replaced
		//TODO Tournament??
		int a = pop.getRandomIndex(pop.getSize());
		pop.setIndividual(a, newIndiv);
	}
	
	public int calculateFitness(Individual a, List<Individual> Sa) {
		int tot = 0;
		for(int i=0 ; i < Sa.size() ; i++) {
			//TODO ">=" ?
			if (a.getValue() >= Sa.get(i).getValue()){
				tot++;
			}
		}
		return tot;
	}
	
	public Individual mutateIndiv(Individual oldIndiv) {
		Individual newIndiv = new Individual(oldIndiv);
		
		for (int i=0 ; i < oldIndiv.getLength() ; i++) {
			if (Math.random() < mutationRate) {
				newIndiv.flipBit(i);
			}
		}
		
		return newIndiv;
	}

}
