package com.nmzl.pigpriceplatform.util;

public class ServerInfoBean {
    /**  可使用内存. */
    private long totalMemory;

    /** 剩余内存. */
    private long freeMemory;

    /** 最大可使用内存. */
    private long maxMemory;

    /** 操作系统. */
    private String osName;

    /** 总的物理内存. */
    private long totalMemorySize;

    /** 剩余的物理内存. */
    private long freePhysicalMemorySize;

    /** 已使用的物理内存. */
    private long usedMemory;

    /** 线程总数. */
    private int totalThread;

    /** cpu使用率. */
    private double cpuRatio;

    /** 磁盘大小. */
    private double diskSize;

    private double usedDisk;

    private long initHeapMemory;

    private long maxHeapMemory;

    private long usedHeapMemory;

    private long initNonHeapMemory;

    public long getInitHeapMemory() {
        return initHeapMemory;
    }

    public ServerInfoBean setInitHeapMemory(long initHeapMemory) {
        this.initHeapMemory = initHeapMemory;
        return this;
    }

    public long getMaxHeapMemory() {
        return maxHeapMemory;
    }

    public ServerInfoBean setMaxHeapMemory(long maxHeapMemory) {
        this.maxHeapMemory = maxHeapMemory;
        return this;
    }

    public long getUsedHeapMemory() {
        return usedHeapMemory;
    }

    public ServerInfoBean setUsedHeapMemory(long usedHeapMemory) {
        this.usedHeapMemory = usedHeapMemory;
        return this;
    }

    public long getInitNonHeapMemory() {
        return initNonHeapMemory;
    }

    public ServerInfoBean setInitNonHeapMemory(long initNonHeapMemory) {
        this.initNonHeapMemory = initNonHeapMemory;
        return this;
    }

    public long getMaxNonHeapMemory() {
        return maxNonHeapMemory;
    }

    public ServerInfoBean setMaxNonHeapMemory(long maxNonHeapMemory) {
        this.maxNonHeapMemory = maxNonHeapMemory;
        return this;
    }

    public long getUsedNonHeapMemory() {
        return usedNonHeapMemory;
    }

    public ServerInfoBean setUsedNonHeapMemory(long usedNonHeapMemory) {
        this.usedNonHeapMemory = usedNonHeapMemory;
        return this;
    }

    private long maxNonHeapMemory;

    private long usedNonHeapMemory;





    public double getDiskSize() {
        return diskSize;
    }

    public ServerInfoBean setDiskSize(double diskSize) {
        this.diskSize = diskSize;
        return this;
    }

    public double getUsedDisk() {
        return usedDisk;
    }

    public ServerInfoBean setUsedDisk(double usedDisk) {
        this.usedDisk = usedDisk;
        return this;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public ServerInfoBean setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
        return this;
    }

    public long getFreePhysicalMemorySize() {
        return freePhysicalMemorySize;
    }

    public ServerInfoBean setFreePhysicalMemorySize(long freePhysicalMemorySize) {
        this.freePhysicalMemorySize = freePhysicalMemorySize;
        return this;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public ServerInfoBean setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
        return this;
    }

    public String getOsName() {
        return osName;
    }

    public ServerInfoBean setOsName(String osName) {
        this.osName = osName;
        return this;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public ServerInfoBean setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
        return this;
    }

    public long getTotalMemorySize() {
        return totalMemorySize;
    }

    public ServerInfoBean setTotalMemorySize(long totalMemorySize) {
        this.totalMemorySize = totalMemorySize;
        return this;
    }

    public int getTotalThread() {
        return totalThread;
    }

    public ServerInfoBean setTotalThread(int totalThread) {
        this.totalThread = totalThread;
        return this;
    }

    public long getUsedMemory() {
        return usedMemory;
    }

    public ServerInfoBean setUsedMemory(long usedMemory) {
        this.usedMemory = usedMemory;
        return this;
    }

    public double getCpuRatio() {
        return cpuRatio;
    }

    public ServerInfoBean setCpuRatio(double cpuRatio) {
        this.cpuRatio = cpuRatio;
        return this;
    }
}
