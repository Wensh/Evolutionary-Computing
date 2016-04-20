package variation;

import individual.Individual;
import java.util.Random;

/*
 * Evaluation Strategy
 * Mutation operator case 1:
 * 		Uncorrelated mutation operator with one sigma
 */
public class GaussianPerturbation implements Mutation {

	private boolean haveNextNextGaussian = false;
	private double nextNextGaussian;
	private Random rng;

	public static final double MIN_BOUNDARY = -5.0;
	public static final double MAX_BOUNDARY = 5.0;
	
	public static final double MIN_SIGMA_BOUNDARY = 1.0;
	public static final double MUTATION_PROBABILITY = 0.20; // 20%

	public GaussianPerturbation() {
		this(new Random());
	}

	public GaussianPerturbation(Random rng) {
		this.rng = rng;
	}

	public boolean mutationProbability() {
		return (rng.nextDouble() < MUTATION_PROBABILITY); 
	}

	public double applyConstraint(double d) {
		if (d > 0.0) {
			return StrictMath.min(d, MAX_BOUNDARY);
		}
		return StrictMath.max(d, MIN_BOUNDARY);
	}
	
	public Individual mutate(Individual individual) {
		double[] genotype = individual.getGenotype();
		int numberOfValues = genotype.length;

		double learningRate = 1.0 / StrictMath.sqrt(numberOfValues);

		double[] newGenotype = new double[numberOfValues];
		for(int i = 0; i < numberOfValues; i++) {
			if( mutationProbability() ) {
				double sigmaPrime = individual.getSigma() * StrictMath.exp(learningRate * rng.nextDouble());
				if (sigmaPrime < MIN_SIGMA_BOUNDARY) {
					sigmaPrime = MIN_SIGMA_BOUNDARY;
				}
				double newValue = applyConstraint(genotype[i] + gaussianNoise(sigmaPrime));

				/**
				 * Check if correct?
				 *
				 * individualPrime = new Individual(genotypePrime);
				 * fitness = eval.evaluate(individualPrime);
				 * if (fitness) use sigma prime?
				 **/

				newGenotype[i] = newValue;
				individual.setSigma(sigmaPrime);
			} else {
				newGenotype[i] = genotype[i];
			}
		}
		individual.setGenotype(newGenotype);
		return individual;
	}

	public double gaussianNoise(double sigma) {
		if (haveNextNextGaussian) {
			haveNextNextGaussian = false;
			return nextNextGaussian;
		} else {
			double v1, v2, s;
			do {
				v1 = 2.0 * sigma * rng.nextDouble() - sigma; // between -sigma and sigma
				v2 = 2.0 * sigma * rng.nextDouble() - sigma; // between -sigma and sigma
				s = v1 * v1 + v2 * v2;
			} while ( s >= 1 || s == 0 );
			double multiplier = StrictMath.sqrt(-2 * StrictMath.log(s) / s);
			nextNextGaussian = v2 * multiplier;
			haveNextNextGaussian = true;
			return v1 * multiplier;
		}
	}
}
