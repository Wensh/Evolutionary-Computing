package selection;

import individual.Individual;

/**
 * Interface for all selection operators, both parent selection
 * and survivor selection.
 */
public interface Selection {
	public Individual[] select(Individual[] individuals);
}
