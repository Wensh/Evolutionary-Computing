import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import selection.Selection;
import variation.GaussianPerturbation;
import variation.Mutation;
import variation.Recombination;
import individual.Individual;
import initialization.Initializer;
import initialization.RandomInitializer;

import java.util.Random;
import java.util.Properties;

/**
 * EA Submission for team 18:
 *
 * - Wenjie Zhong
 * - Roald Stolte
 * - Ove Danner
 *
 */
public class player18 implements ContestSubmission {
	/*
	 * Random number generator.
	 */
	private Random random;

	/*
	 * The current ContestEvaluation our submission
	 * has to optimize.
	 */
	private ContestEvaluation evaluation;

	/*
	 * The maximum number of evaluations we have for
	 * the current ContestEvaluation.
	 */
	private int evaluations_limit;

	/*
	 * The (initial) population size.
	 */
	private final int POPULATION_SIZE = 100;

	/*
	 * These EA components will be set at runtime,
	 * depending on the ContestEvaluation we have
	 * to optimize.  
	 */
	private Initializer initializer;
	private Selection parentSelection;
	private Recombination crossover;
	private Mutation mutation;
	private Selection survivorSelection;
	
	Individual[] population;

	/*
	 * Initialize the random number generator.
	 */
	public player18() {
		random = new Random();
	}

	/*
	 * Set the seed for the random number generator.
	 */
	public void setSeed(long seed) {
		random.setSeed(seed);
	}

	/*
	 * Set the ContestEvaluation our EA will try to optimize.
	 * Here we also determine other properties of our EA based
	 * on properties of the ContestEvaluation.
	 */
	public void setEvaluation(ContestEvaluation eval) {
		evaluation = eval;

		Properties props = evaluation.getProperties();
		evaluations_limit = Integer.parseInt(props.getProperty("Evaluations"));
		// Multimodal might favour mutation.
		boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
		boolean hasStructure = Boolean.parseBoolean(props.getProperty("GlobalStructure"));
		boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		// Use a random initializer.
		initializer = new RandomInitializer();
		
		// TODO: set selection/variation operators based on
		// the booleans above.
		if(isMultimodal) {
			
		} else{
			
		}
	}

	/*
	 * Run our EA for the current ContestEvaluation.
	 */
	public void run() {
		// Create initial population.
		population = initializer.intialize(POPULATION_SIZE, random);
		System.out.println("First child: ");
		for (int i = 0; i < 10; i++) {
			System.out.println(population[0].getGenotype()[i]);	
		}

		// Evaluate population fitness.
		// Choose parents.
		// Recombine / mutate
		GaussianPerturbation m = new GaussianPerturbation(this.random);
		m.mutate(population[0]);
		for (int i = 0; i < 10; i++) {
			System.out.println(population[0].getGenotype()[i]);	
		}

		// Choose survivors
		
	}
	
}