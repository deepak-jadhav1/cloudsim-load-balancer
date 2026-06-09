# Dynamic Resource Allocation and Performance Analysis of Load Balancing Algorithms in Multi-Cloud Environments Using CloudSim

> Final Year Engineering Project | VIII Semester | Guide: Dr. Chetan J Shelke

---

## 👥 Team Members

| Name | Roll No |
|------|---------|
| Deepak | CCIT-021 |
| Dilip Dhanraj Rathod | CCIT-009 |
| G Varshini | CCIT-039 |
| G Rajitha | CCIT-007 |

---

## 📌 Project Overview

Cloud computing offers scalable, on-demand resources — but managing them efficiently across **multiple cloud environments** is a hard problem. Traditional load balancing strategies like Round Robin fail when workloads change dynamically, leading to resource waste, higher energy costs, and SLA violations.

This project builds a **custom simulation tool** using **CloudSim** as the backend and **JavaFX** for the GUI. It lets users configure multi-cloud environments (data centers, VMs, tasks) and compare how different load balancing algorithms perform — all visually, in real time.

---

## 🎯 Objectives

- Build a multi-cloud resource allocation simulator using CloudSim
- Implement 6 load balancing algorithms including one RL-based approach
- Integrate Hybrid Lyrebird Falcon Optimization (HLFO) for dynamic task scheduling
- Compare algorithms on: **makespan, cost, CPU utilization, energy efficiency**
- Analyze and optimize multi-cloud pricing models

---

## ⚙️ Tech Stack

| Category | Tools |
|----------|-------|
| Simulation Engine | CloudSim 3.0 |
| Programming Language | Java |
| GUI | JavaFX |
| IDE | Eclipse |
| Hardware Required | Min 8GB RAM, i5/i7, 50GB Storage |

---

## 🧩 Modules / Algorithms Implemented

1. **Round Robin** — distributes tasks equally across VMs in order
2. **Weighted Round Robin** — assigns tasks based on VM capacity weights
3. **Least Connection** — sends tasks to the VM with the fewest active connections
4. **Least Response Time** — routes to the fastest responding VM
5. **Throttled Algorithm** — maintains a VM index table; assigns only available VMs
6. **Source IP Hash** — maps client IP to a specific VM for session consistency

---

## 📊 Performance Metrics Evaluated

- **Response Time** — time from request submission to response
- **Throughput** — tasks processed per unit time
- **Makespan** — total time to finish all tasks
- **Resource Utilization** — % of CPU/memory/bandwidth used
- **Execution Time** — per-task/VM completion time
- **Cost Efficiency** — operational cost per task
- **Waiting Time** — queue time before VM assignment
- **Fault Tolerance** — behavior under VM failure scenarios

---

## 🗂️ Repository Structure

```
📦 cloudsim-load-balancer/
├── src/
│   ├── RoundRobin.java
│   ├── WeightedRoundRobin.java
│   ├── LeastConnection.java
│   ├── LeastResponseTime.java
│   ├── ThrottledAlgorithm.java
│   ├── SourceIPHash.java
│   └── SimulationMain.java
├── screenshots/
│   ├── round_robin_output.png
│   ├── weighted_rr_output.png
│   ├── least_connection_output.png
│   └── least_response_time_output.png
├── report/
│   └── project_review_presentation.pptx
├── docs/
│   └── architecture_diagram.png
└── README.md
```

---

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or higher
- Eclipse IDE
- CloudSim 3.0 JAR (add to build path)
- JavaFX SDK

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/your-username/cloudsim-load-balancer.git

# 2. Open in Eclipse
File → Import → Existing Java Project → Select cloned folder

# 3. Add CloudSim JAR to build path
Right-click project → Build Path → Add External JARs → select cloudsim-3.0.jar

# 4. Run the simulation
Right-click SimulationMain.java → Run As → Java Application
```

---

## 📈 Sample Output

```
🔄 Running simulation with strategy: Round Robin
✅ Cloudlet 0 executed on VM 0
✅ Cloudlet 1 executed on VM 1
...
📊 Summary:
• Throughput: 100.0 Cloudlets/sec
• Avg. Response Time: 50.0 ms
• Completion Rate: 95.0%
```

---

## 📚 Research Background

This project builds on published work comparing load balancing in cloud environments, including studies by Sabyasachi Patnaik (2024), Muhammad Asim Shahid (2023), and Ahmad Raza Khan (2024). It contrasts classical methods with AI-based and hybrid optimization approaches like HLFO.

---

## ⚠️ Limitations

- CloudSim abstracts real-world network unpredictability
- Real-time auto-scaling is not simulated in this version
- ML-based algorithms (Ant Colony, Genetic Algorithm) not included due to time constraints

---

## 🔮 Future Enhancements

- Integrate real AWS / Azure / GCP APIs for live simulation
- Add deep reinforcement learning-based load balancer
- Web-based interface for cross-platform access
- PDF/Excel export of performance summaries
- Fault tolerance and VM failure recovery simulation

---

## 📄 License

This project was developed for academic purposes. Free to use for learning and research with attribution.
