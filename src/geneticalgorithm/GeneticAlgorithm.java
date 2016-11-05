/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jirkazbor
 */
public class GeneticAlgorithm {

    public static String target = "zkouska test";
    public static int maxPopulation = 200;
    public static int mutationRate = 1;
    public Population population;
    public NewJFrame frame;
    public AIDatabase database;
    
    /**
     * @param args the command line arguments
     */
    
    public GeneticAlgorithm(NewJFrame frame){
        this.frame = frame;
        population = new Population(target, maxPopulation, mutationRate,frame);
        
//        try {
//            database = new AIDatabase();
//        } catch (SQLException ex) {
//            Logger.getLogger(GeneticAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
//        }
         
        population.createPopulation();
        population.createWord();
        this.frame.population().setText(Integer.toString(maxPopulation));
        this.frame.mutation().setText(Integer.toString(mutationRate));
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                while (!population.isMaximumEvolved()){                    
                    population.calculateFitness();
                    population.matingPool();
                    population.reproduction();
                    population.allPhrases();
                }
            }
        });
        thread.start();
    }
}
