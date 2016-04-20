package variation;

import individual.Individual;

/**
 * Interface for mutation operators.
 */
public interface Mutation {
	public Individual mutate(Individual individual);
}
