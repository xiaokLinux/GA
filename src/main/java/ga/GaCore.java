package ga;

import data.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GaCore {
    // 交叉概率
    double crossRate;
    // 突变概率
    double mutationRate;
    // 种群数量
    int lifeCount;
    // 基因序列长度
    int geneLength;
    // 种群
    List<Life> lives;
    // 最好个体
    Life best;
    // 世代计数器
    int generation = 1;
    // 交叉计数器
    int crossCount = 0;
    // 突变计数器
    int mutationCount = 0;
    // 适配和
    double bounds = 0.0;
    // 基本基因长度
    int baseGenLength;

    public GaCore(double crossRate, double mutationRate, int lifeCount, int geneLength, int baseGenLength) {
        this.crossRate = crossRate;
        this.mutationRate = mutationRate;
        this.lifeCount = lifeCount;
        this.geneLength = geneLength;
        this.baseGenLength = baseGenLength;
    }

    public void initPopulation() {
        lives = new ArrayList<>();
        for(int life=0;life<lifeCount;life++){
            Random random = new Random();
            int gens[] = new int[geneLength];
            for(int g=0;g<geneLength;g++){
                gens[g]=random.nextInt(baseGenLength);
            }
            Life one = new Life(gens);
            lives.add(one);
        }
    }

    /**
     * 交叉
     * @param parent1
     * @param parent2
     * @return
     */
    public int[] cross(Life parent1, Life parent2) {
        Random random = new Random();
        int index1 = random.nextInt(geneLength-1);
        int index2 = random.nextInt(geneLength-1-index1) + index1;
        int newGen[] = new int[geneLength];
        for(int g=0;g<parent1.gens.length;g++){
            if(index1<=g && index2>=g) {
                newGen[g] = parent2.gens[g];
            }else{
                newGen[g] = parent1.gens[g];
            }
        }
        crossCount++;
        return newGen;
    }

    /**
     * 突变
     * @param gens
     * @return
     */
    public int[] mutation(int[] gens){
        Random random = new Random();
        int index1 = random.nextInt(geneLength-1);
        int index2 = random.nextInt(geneLength-1);
        int t;
        t = gens[index1];gens[index1]=gens[index2];gens[index2]=t;
        mutationCount++;
        return gens;
    }

    /**
     * 按照适配值选择一个个体繁衍
     * @return
     */
    public Life getOne(){
        double r =bounds * new Random().nextDouble();
        for(int lifIndex=0;lifIndex<lives.size();lifIndex++) {
            r-=lives.get(lifIndex).score;
            if(r<=0){
                return lives.get(lifIndex);
            }
        }
        return lives.get(0);
    }

    /**
     * 产生后代
     * @return
     */
    public Life newChild(){
        Life parent = getOne();
         double rate = new Random().nextDouble();
        int[] gens;
        if(rate<crossRate){
            Life parent2 = getOne();
            gens = cross(parent, parent2);
        }else{
            gens = parent.gens;
        }
        rate = new Random().nextDouble();
        if(rate<mutationRate){
            mutation(gens);
        }
        return new Life(gens);
    }

}
