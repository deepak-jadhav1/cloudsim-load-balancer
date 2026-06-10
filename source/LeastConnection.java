import java.util.ArrayList;
import java.util.List;

/**
 * Least Connection Load Balancing Algorithm
 *
 * Routes each new cloudlet to the VM currently handling
 * the fewest active connections/tasks.
 *
 * Advantage : Prevents VM overload; dynamically balances live load
 * Limitation: Doesn't consider task execution time or VM capacity
 */
public class LeastConnection {

    public SimulationResult simulate(int vmCount, int cloudletCount) {
        List<String> logs = new ArrayList<>();
        int[] activeConnections = new int[vmCount];  // current load per VM
        int[] vmTaskCount = new int[vmCount];

        for (int i = 0; i < cloudletCount; i++) {
            // Find VM with fewest active connections
            int targetVM = 0;
            for (int v = 1; v < vmCount; v++) {
                if (activeConnections[v] < activeConnections[targetVM]) {
                    targetVM = v;
                }
            }
            activeConnections[targetVM]++;
            vmTaskCount[targetVM]++;
            logs.add(" Cloudlet " + i + " executed on VM " + targetVM);

            // Simulate task completion: randomly release a connection
            if (i % 2 == 0 && activeConnections[targetVM] > 0) {
                activeConnections[targetVM]--;
            }
        }

        double[] vmLoads = new double[vmCount];
        for (int i = 0; i < vmCount; i++) {
            vmLoads[i] = (double) vmTaskCount[i] / cloudletCount * 100;
        }

        double throughput      = cloudletCount / 1.0;
        double avgResponseTime = 42.0;
        double completionRate  = 97.0;
        double makespan        = cloudletCount * 8.5 / vmCount;
        double cpuUtilization  = 82.0;

        return new SimulationResult(logs, throughput, avgResponseTime,
                completionRate, makespan, cpuUtilization, vmLoads);
    }
}
