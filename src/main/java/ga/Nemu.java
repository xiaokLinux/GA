package ga;

import data.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Nemu {
    Data data;
    int lifeCount;
    GaCore ga;

    public Nemu(int genLength, int baseGenLength) {
        this.lifeCount = 100;
        this.ga=new GaCore(0.7, 0.02,100,genLength,baseGenLength);
        this.ga.initPopulation();
        this.data = new Data(100,10);
        data.initRandomData();
    }

    public double calc(int[] gen){
        double time_use_sum[] = new double[ga.baseGenLength];
        double max=0.0;
        for(int node=0;node<ga.baseGenLength;node++){ time_use_sum[node]=0.0;}
        for(int g=0;g<gen.length;g++){
            time_use_sum[gen[g]] += data.getTasks()[g]*1.0/data.getNodes()[gen[g]];
        }
        for(int timeIndex=0;timeIndex<ga.baseGenLength;timeIndex++){
            if(time_use_sum[timeIndex]> max){
                max=time_use_sum[timeIndex];
            }
        }
        return max;
    }

    public double matchFun(Life life){
        return 1.0/calc(life.gens);
    }

    public HashMap<Integer,Double> run(int n){
        HashMap<Integer,Double> map = new HashMap<>();
        for(int count=0;count<n;count++){
            next();
            double time = calc(ga.best.gens);
            map.put(count, time);
        }
        return map;
    }
    public void next() {
        judge();
        List<Life> newLives = new ArrayList<>();
        newLives.add(ga.best);
        while (newLives.size()<lifeCount){
            newLives.add(ga.newChild());
        }
        ga.lives = newLives;
        ga.generation++;
    }

    public void judge(){
        this.ga.bounds = 0.0;
        this.ga.best = this.ga.lives.get(0);
        for(int lifeIndex=0;lifeIndex<this.ga.lives.size();lifeIndex++){
            Life life = this.ga.lives.get(lifeIndex);
            life.score = matchFun(life);
            this.ga.bounds+=life.score;
            if(this.ga.best.score < life.score){
                this.ga.best=life;
            }
        }
    }
}
