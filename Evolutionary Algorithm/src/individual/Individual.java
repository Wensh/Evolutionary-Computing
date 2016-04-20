package individual;
/**
 * Class that represents an individual in the population.
 * Every individual has a genotype and an associated fitness.
 * (and later possibly an array of sigma values)
 * 
 * - Chromosome
 */
public class Individual {
	private double[] genotype;
	private double fitness;
	private double sigma;
	
	public Individual() {
		genotype = new double[10];
		fitness = 0.0;
		sigma = 0.0;
	}
	
	public Individual(double[] gen) {
		genotype = gen;
		fitness = 0.0;
		sigma = 0.0;
	}
	
	public Individual(double[] gen, double f, double s) {
		genotype = gen;
		fitness = f;
		sigma = s;
	}

	public double[] getGenotype() {
		return genotype;
	}

	public void setGenotype(double[] genotype) {
		this.genotype = genotype;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public double getSigma() {
		return sigma;
	}
	
	public void setSigma(double sigma) {
		this.sigma = sigma;
	}
}