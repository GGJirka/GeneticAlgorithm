/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jirkazbor
 */
public class Population {
    
    public ArrayList<DNA> matingPool = new ArrayList<>();
    public NewJFrame frame;
    public DNA[] population;
    public String target;
    public Random random;
    public int maxPopulation;
    public int mutationRate;
    public int generations;
  
    public Population(String target, int maxPopulation, int mutationRate,NewJFrame frame){
        this.frame = frame;
        this.target = target;
        this.maxPopulation = maxPopulation;
        this.mutationRate = mutationRate;
        this.population = new DNA[maxPopulation];
        this.random = new Random();
    }
    
    public void createPopulation(){
        for(int i=0;i<maxPopulation;i++){
            population[i] = new DNA(target);
        }
    }
    
    public void createWord(){
        for(int i=0;i<maxPopulation;i++){
            DNA dna = population[i];
            dna.createWord();
        }
    }
    public void calculateFitness(){
        DNA dna = null;
        for(int i=0;i<maxPopulation;i++){            
            dna = population[i];
            dna.calculateFitness();
        }
    }

    public void matingPool(){
        for(int i=0;i<maxPopulation;i++){
            DNA dna = population[i];
            for(int j=0;j<dna.fitness+1;j++){
                matingPool.add(dna);
            }
        }
    }
    
    public void reproduction(){
        for(int i=0;i<this.population.length;i++){
            DNA subjectA = this.matingPool.get(random.nextInt(matingPool.size()));
            DNA subjectB = this.matingPool.get(random.nextInt(matingPool.size()));
            DNA newSubject = subjectA.crossover(subjectB);
            newSubject.mutate(mutationRate);           
            this.population[i] = newSubject;          
        }
        generations++;
        this.frame.generation().setText(Integer.toString(generations));
        this.frame.fitness().setText(Double.toString(averageFitness())+" %");
    }
    public boolean isMaximumEvolved(){
        for(int i=0;i<this.population.length;i++){
            if(population[i].isMaximumEvolved()){
                return true;
            }
        }
        return false;
    }
    public void allPhrases(){
        for(int i=0;i<this.population.length;i++){
            population[i].word();
            System.out.println();
            this.frame.textArea().append(population[i].toString()+"\n");
        }       
        matingPool.removeAll(matingPool);
        this.frame.phrase().setText(mostEvolved().toString());
    }
    public DNA mostEvolved(){
        int fitness = 0;
        DNA dna = null;
        for(int i=0;i<population.length;i++){
            if(fitness<population[i].calculateFitness()){
                fitness = population[i].calculateFitness();
                dna = population[i];
            }
        }       
        return dna;
    }
    
    public double averageFitness(){
        double fitness = 0;
        for(int i=0;i<population.length;i++){
            fitness += population[i].calculatePercentFitness();            
        }
        return fitness/population.length;
    }
}
