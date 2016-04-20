package variation;

import individual.Individual;

import java.util.Random;

/**
 * Generates N children individuals from N parent individuals
 * by randomly selecting two parent individuals from the given
 * population and flipping a coin for each allele to determine
 * which parent supplies the next allele. Sigma values are
 * created by taking the average of parent sigma values.
 */
public class DiscreteRecombination implements Recombination {
	
	public static final double CROSSOVER_PROBABILITY = 0.20;
	private Random random;
	
	public DiscreteRecombination(Random r) {
		random = r;
	}
	
	public boolean crossoverProbability() {
		return (random.nextDouble() < CROSSOVER_PROBABILITY); 
	}

	@Override
	public Individual[] recombine(Individual[] parents) {
		boolean coin;
		Individual[] children = new Individual[parents.length];
		for (int i = 0; i < parents.length; i++) {
			if (crossoverProbability()) {
				double sigma;
				double[] genotype = new double[10];
				Individual parentOne = parents[random.nextInt(parents.length)];
				Individual parentTwo = parents[random.nextInt(parents.length)];
				// Recombine genotype discretely.
				for (int j = 0; j < 10; j++) {
					coin = (random.nextInt() % 2 == 0);
					if (coin) {
						genotype[j] = parentOne.getGenotype()[j];
					} else {
						genotype[j] = parentTwo.getGenotype()[j];
					}
				}
				// Average parent sigma's.
				sigma = (parentOne.getSigma() + parentTwo.getSigma()) / 2;
				children[i] = new Individual(genotype, 0.0, sigma);
			} else {
				children[i] = new Individual(parents[i].getGenotype(), 0.0, parents[i].getSigma());
			}
		}
		return children;
	}

}
