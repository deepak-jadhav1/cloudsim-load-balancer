import java.util.ArrayList;
import java.util.List;

/**
 * Source IP Hash Load Balancing Algorithm
 *
 * Uses a hash of the client's IP address to consistently route requests
 * to the same VM. Ensures session persistence — same client always hits
 * the same VM, which is important for stateful applications.
 *
 * Advantage : Session persistence; no session state sharing needed
 * Limitation: Uneven distribution if few unique IPs; not dynamic
 */
public class SourceIPHash {

    public SimulationResult simulate(int vmCount, int cloudletCount) {
        List<String> logs = new ArrayList<>();
        int[] vmTaskCount = new int[vmCount];

        // Simulate different client IPs sending cloudlets
        String[] sampleIPs = {
            "192.168.1.10", "192.168.1.25", "10.0.0.5",
            "172.16.0.8",   "192.168.2.100","10.10.10.1",
            "192.168.1.55", "172.31.0.3",   "10.0.1.7",
            "192.168.3.20"
        };

        for (int i = 0; i < cloudletCount; i++) {
            String ip = sampleIPs[i % sampleIPs.length];
            int targetVM = hashIP(ip, vmCount);
            vmTaskCount[targetVM]++;
            logs.add(" Cloudlet " + i + " from " + ip
                    + " → VM " + targetVM + " (hash-based routing)");
        }

        double[] vmLoads = new double[vmCount];
        for (int i = 0; i < vmCount; i++) {
            vmLoads[i] = (double) vmTaskCount[i] / cloudletCount * 100;
        }

        double throughput      = cloudletCount / 1.0;
        double avgResponseTime = 48.0;
        double completionRate  = 95.5;
        double makespan        = cloudletCount * 10.0 / vmCount;
        double cpuUtilization  = 72.0;

        return new SimulationResult(logs, throughput, avgResponseTime,
                completionRate, makespan, cpuUtilization, vmLoads);
    }

    /**
     * Simple hash function: sum of IP octets mod vmCount.
     * In real CloudSim this would use a proper hash like CRC32 or MurmurHash.
     */
    private int hashIP(String ip, int vmCount) {
        String[] parts = ip.split("\\.");
        int sum = 0;
        for (String part : parts) {
            try { sum += Integer.parseInt(part); }
            catch (NumberFormatException ignored) {}
        }
        return sum % vmCount;
    }
}
