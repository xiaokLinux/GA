package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Data {
    int tasks[];
    int nodes[];

    int task_length = 0;
    int node_length = 0;
    int task_random_range[] = {10,100};
    int node_random_range[] = {10,100};

    public Data() {
        task_length = 100;
        node_length = 10;
    }

    public Data(int task_length, int node_length) {
        this.task_length = task_length;
        this.node_length = node_length;
    }

    public void random_tasks_data() {
        tasks = new int[task_length];
        Random random = new Random();
        for (int i=0;i<task_length;i++){
            tasks[i]=random.nextInt(task_random_range[1]-task_random_range[0])+task_random_range[0];
        }
    }
    public void random_nodes_data() {
        nodes = new int[node_length];
        Random random = new Random();
        for (int i=0;i<node_length;i++){
            nodes[i]=random.nextInt(node_random_range[1]-node_random_range[0])+node_random_range[0];
        }
    }
    /**
     * 创建随机数据
     */
    public void initRandomData() {
        random_tasks_data();
        random_nodes_data();
    }

    public int[] getTasks() {
        return tasks;
    }

    public int[] getNodes() {
        return nodes;
    }
}
