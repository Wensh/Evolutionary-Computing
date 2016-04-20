package initialization;

import individual.Individual;

import java.util.Random;

public class RandomInitializer implements Initializer {

	@Override
	public Individual[] intialize(int populationSize, Random random) {
		Individual[] result = new Individual[populationSize];
		for (int i = 0; i < populationSize; i++) {
			double[] genotype = new double[10];
			for (int j = 0; j < 10; j++) {
				genotype[j] = (random.nextDouble() * 10) - 5;
			}
			result[i] = new Individual(genotype, 0.0, random.nextDouble());
		}
		return result;
	}

}
