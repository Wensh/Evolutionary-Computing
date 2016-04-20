package initialization;

import individual.Individual;

import java.util.Random;

/**
 * Interface for population initializers.
 */
public interface Initializer {
	public Individual[] intialize(int populationSize, Random random);
}
