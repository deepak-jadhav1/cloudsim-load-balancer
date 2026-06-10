import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SimulationMain extends Application {

    private TextArea outputArea;
    private ComboBox<String> algorithmBox;
    private TextField vmCountField;
    private TextField cloudletCountField;
    private BarChart<String, Number> barChart;
    private PieChart pieChart;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CloudSim Load Balancer - Multi-Cloud Simulator");

        // ── Top Title ──
        Label title = new Label("Dynamic Resource Allocation Simulator");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setPadding(new Insets(10));

        // ── Controls Panel ──
        Label algoLabel = new Label("Select Algorithm:");
        algorithmBox = new ComboBox<>();
        algorithmBox.getItems().addAll(
            "Round Robin",
            "Weighted Round Robin",
            "Least Connection",
            "Least Response Time",
            "Throttled",
            "Source IP Hash"
        );
        algorithmBox.setValue("Round Robin");

        Label vmLabel = new Label("Number of VMs:");
        vmCountField = new TextField("4");
        vmCountField.setPrefWidth(60);

        Label cloudletLabel = new Label("Number of Tasks (Cloudlets):");
        cloudletCountField = new TextField("10");
        cloudletCountField.setPrefWidth(60);

        Button runButton = new Button("Run Simulation");
        runButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8 20;");
        runButton.setOnAction(e -> runSimulation());

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> outputArea.clear());

        HBox controls = new HBox(15, algoLabel, algorithmBox, vmLabel, vmCountField,
                cloudletLabel, cloudletCountField, runButton, clearButton);
        controls.setPadding(new Insets(10));
        controls.setAlignment(Pos.CENTER_LEFT);
        controls.setStyle("-fx-background-color: #f0f0f0;");

        // ── Output Console ──
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setFont(Font.font("Courier New", 13));
        outputArea.setPrefHeight(250);
        outputArea.setStyle("-fx-background-color: #1e1e1e; -fx-text-fill: #00ff99;");

        // ── Bar Chart ──
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Algorithm");
        yAxis.setLabel("Value");
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Performance Comparison");
        barChart.setPrefHeight(280);

        // ── Pie Chart ──
        pieChart = new PieChart();
        pieChart.setTitle("VM Utilization");
        pieChart.setPrefHeight(280);

        HBox charts = new HBox(10, barChart, pieChart);
        charts.setPadding(new Insets(10));

        // ── Layout ──
        VBox root = new VBox(10, title, controls, outputArea, charts);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 1100, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Run default on launch
        runSimulation();
    }

    private void runSimulation() {
        String algorithm = algorithmBox.getValue();
        int vmCount, cloudletCount;

        try {
            vmCount = Integer.parseInt(vmCountField.getText().trim());
            cloudletCount = Integer.parseInt(cloudletCountField.getText().trim());
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Please enter valid numbers for VMs and Cloudlets.");
            return;
        }

        SimulationResult result;

        switch (algorithm) {
            case "Round Robin":
                result = new RoundRobin().simulate(vmCount, cloudletCount);
                break;
            case "Weighted Round Robin":
                result = new WeightedRoundRobin().simulate(vmCount, cloudletCount);
                break;
            case "Least Connection":
                result = new LeastConnection().simulate(vmCount, cloudletCount);
                break;
            case "Least Response Time":
                result = new LeastResponseTime().simulate(vmCount, cloudletCount);
                break;
            case "Throttled":
                result = new ThrottledAlgorithm().simulate(vmCount, cloudletCount);
                break;
            case "Source IP Hash":
                result = new SourceIPHash().simulate(vmCount, cloudletCount);
                break;
            default:
                result = new RoundRobin().simulate(vmCount, cloudletCount);
        }

        displayOutput(result, algorithm);
        updateCharts(result, algorithm);
    }

    private void displayOutput(SimulationResult result, String algorithm) {
        StringBuilder sb = new StringBuilder();
        sb.append(" Running simulation with strategy: ").append(algorithm).append("\n\n");

        for (String log : result.getLogs()) {
            sb.append(log).append("\n");
        }

        sb.append("\n Summary:\n");
        sb.append(" Throughput:        ").append(result.getThroughput()).append(" Cloudlets/sec\n");
        sb.append(" Avg. Response Time:").append(result.getAvgResponseTime()).append(" ms\n");
        sb.append(" Completion Rate:   ").append(result.getCompletionRate()).append("%\n");
        sb.append(" Makespan:          ").append(result.getMakespan()).append(" ms\n");
        sb.append(" Avg. CPU Usage:    ").append(result.getCpuUtilization()).append("%\n");

        outputArea.setText(sb.toString());
    }

    private void updateCharts(SimulationResult result, String algorithm) {
        // Bar Chart — metrics for current run
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(algorithm);
        series.getData().add(new XYChart.Data<>("Response Time", result.getAvgResponseTime()));
        series.getData().add(new XYChart.Data<>("Throughput", result.getThroughput()));
        series.getData().add(new XYChart.Data<>("Completion %", result.getCompletionRate()));
        series.getData().add(new XYChart.Data<>("CPU %", result.getCpuUtilization()));
        barChart.getData().clear();
        barChart.getData().add(series);

        // Pie Chart — VM utilization distribution
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        double[] vmLoads = result.getVmLoads();
        for (int i = 0; i < vmLoads.length; i++) {
            pieData.add(new PieChart.Data("VM " + i, vmLoads[i]));
        }
        pieChart.setData(pieData);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
