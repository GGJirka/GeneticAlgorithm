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
public class DNA{
    
    public String target;
        
    public char[] genes;
    
    public int fitness = 0;
    
    public Random random;
    
    public DNA(String target){
        this.target = target;
        genes = new char [target.length()];
        fitness = calculateFitness();
        random = new Random();
    }
    
    public void createWord(){
        for(int i=0;i<target.length();i++){
            if(target.charAt(i) != ' '){
                genes[i] = newChar();
            }else{
                genes[i] = ' ';
            }
        }
    }
    
    public int calculateFitness(){
        for(int i=0;i<target.length();i++){
            if(genes[i] == target.charAt(i) && target.charAt(i) != ' '){
                fitness++;
            }
        }
        return fitness;
    }
    public double calculatePercentFitness(){
        double percent = 0;
        for(int i=0;i<target.length();i++){
            if(genes[i] == target.charAt(i) && target.charAt(i) != ' '){
                percent++;
            }
        }
        return percent/target.length()*100;
    }
    private char newChar () {
        int rnd = (int) (Math.random() * 52);
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);
    }
    
    public DNA crossover(DNA subjectB){
        DNA child = new DNA(target);
        int midpoint = target.length()/2;
        for(int i=0;i<this.genes.length;i++){
            if(i > midpoint){
                child.genes[i] = this.genes[i];
            }else{
                child.genes[i] = subjectB.genes[i];
            }
        }
        return child;
    }
    public void word(){
        for(int i=0;i<genes.length;i++){
            System.out.print(genes[i]);
        }
    }
      
    public boolean mutate(int mutationRate){
        for(int i=0;i<genes.length;i++){           
            int n = random.nextInt(100);            
            if(n>0 && n<=mutationRate && this.genes[i] != ' '){
                this.genes[i] = newChar();                
                return true;
            }
        }
        return false;
    }
    
    public String toString(){
       return new String(genes); 
    }
    
    public boolean isMaximumEvolved(){
        String finalString = new String(genes);
        if(finalString.equalsIgnoreCase(target)){
            return true;
        }
        return false;
    }
}
