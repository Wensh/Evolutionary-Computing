package variation;

import individual.Individual;

/**
 * Interface for recombination operators. Recombination can
 * possibly take any number of parents and return any
 * number of children.
 */
public interface Recombination {
	public Individual[] recombine(Individual[] parents);
}
