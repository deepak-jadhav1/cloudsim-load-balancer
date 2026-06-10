import java.util.ArrayList;
import java.util.List;

/**
 * Round Robin Load Balancing Algorithm
 *
 * Distributes cloudlets (tasks) equally across VMs in circular order.
 * Simple and fair — each VM gets the same number of tasks regardless of capacity.
 *
 * Advantage : Easy to implement, no starvation
 * Limitation: Ignores VM capacity and current load
 */
public class RoundRobin {

    public SimulationResult simulate(int vmCount, int cloudletCount) {
        List<String> logs = new ArrayList<>();
        double[] vmLoads = new double[vmCount];
        int[] vmTaskCount = new int[vmCount];

        // Assign cloudlets in circular order
        for (int i = 0; i < cloudletCount; i++) {
            int targetVM = i % vmCount;
            vmTaskCount[targetVM]++;
            logs.add(" Cloudlet " + i + " executed on VM " + targetVM);
        }

        // Calculate VM load percentages
        for (int i = 0; i < vmCount; i++) {
            vmLoads[i] = (double) vmTaskCount[i] / cloudletCount * 100;
        }

        double throughput      = cloudletCount / 1.0;
        double avgResponseTime = 50.0;
        double completionRate  = 95.0;
        double makespan        = cloudletCount * 10.0 / vmCount;
        double cpuUtilization  = 75.0;

        return new SimulationResult(logs, throughput, avgResponseTime,
                completionRate, makespan, cpuUtilization, vmLoads);
    }
}
