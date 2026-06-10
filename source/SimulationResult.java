import java.util.List;

/**
 * Holds all results from a single simulation run.
 * Passed from each algorithm back to SimulationMain for display and charting.
 */
public class SimulationResult {

    private List<String> logs;
    private double throughput;
    private double avgResponseTime;
    private double completionRate;
    private double makespan;
    private double cpuUtilization;
    private double[] vmLoads;

    public SimulationResult(List<String> logs, double throughput, double avgResponseTime,
                            double completionRate, double makespan, double cpuUtilization,
                            double[] vmLoads) {
        this.logs = logs;
        this.throughput = throughput;
        this.avgResponseTime = avgResponseTime;
        this.completionRate = completionRate;
        this.makespan = makespan;
        this.cpuUtilization = cpuUtilization;
        this.vmLoads = vmLoads;
    }

    public List<String> getLogs()        { return logs; }
    public double getThroughput()        { return throughput; }
    public double getAvgResponseTime()   { return avgResponseTime; }
    public double getCompletionRate()    { return completionRate; }
    public double getMakespan()          { return makespan; }
    public double getCpuUtilization()    { return cpuUtilization; }
    public double[] getVmLoads()         { return vmLoads; }
}
