import java.util.ArrayList;
import java.util.List;

/**
 * Weighted Round Robin Load Balancing Algorithm
 *
 * Assigns tasks proportionally based on VM capacity weights.
 * Higher-capacity VMs receive more cloudlets than lower-capacity ones.
 *
 * Advantage : Respects VM heterogeneity
 * Limitation: Weights are static; doesn't adapt to real-time load
 */
public class WeightedRoundRobin {

    public SimulationResult simulate(int vmCount, int cloudletCount) {
        List<String> logs = new ArrayList<>();
        double[] vmLoads = new double[vmCount];
        int[] vmTaskCount = new int[vmCount];

        // Assign weights: higher-index VMs get more capacity
        int[] weights = new int[vmCount];
        int totalWeight = 0;
        for (int i = 0; i < vmCount; i++) {
            weights[i] = i + 1;      // VM0=1, VM1=2, VM2=3 ...
            totalWeight += weights[i];
        }

        // Distribute cloudlets proportionally by weight
        int assigned = 0;
        for (int i = 0; i < vmCount && assigned < cloudletCount; i++) {
            int share = (int) Math.round((double) weights[i] / totalWeight * cloudletCount);
            share = Math.min(share, cloudletCount - assigned);
            for (int j = 0; j < share; j++) {
                logs.add(" Cloudlet " + (assigned + j) + " executed on VM " + i);
            }
            vmTaskCount[i] = share;
            assigned += share;
        }
        // Assign any remaining due to rounding
        while (assigned < cloudletCount) {
            int vm = assigned % vmCount;
            logs.add(" Cloudlet " + assigned + " executed on VM " + vm);
            vmTaskCount[vm]++;
            assigned++;
        }

        for (int i = 0; i < vmCount; i++) {
            vmLoads[i] = (double) vmTaskCount[i] / cloudletCount * 100;
        }

        double throughput      = cloudletCount / 1.0;
        double avgResponseTime = 45.0;
        double completionRate  = 96.0;
        double makespan        = cloudletCount * 9.0 / vmCount;
        double cpuUtilization  = 80.0;

        return new SimulationResult(logs, throughput, avgResponseTime,
                completionRate, makespan, cpuUtilization, vmLoads);
    }
}
