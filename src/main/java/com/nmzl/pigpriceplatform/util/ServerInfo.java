package com.nmzl.pigpriceplatform.util;

import oshi.SystemInfo;
import oshi.hardware.*;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ServerInfo {

    public ServerInfoBean getServerInfo() throws InterruptedException {
        SystemInfo systemInfo = new SystemInfo();

        HardwareAbstractionLayer hal = systemInfo.getHardware();
        GlobalMemory memory = hal.getMemory();
        CentralProcessor processor = hal.getProcessor();
        HWDiskStore diskStore = hal.getDiskStores()[0];

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage headMemory = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonheadMemory = memoryMXBean.getNonHeapMemoryUsage();

        return new ServerInfoBean().setTotalMemory(memory.getTotal())
                .setFreeMemory(memory.getAvailable())
                .setDiskSize(getDiskInfo().get("total"))
                .setUsedDisk(getUnixDiskUsage())
                .setInitHeapMemory(headMemory.getInit())
                .setMaxHeapMemory(headMemory.getMax())
                .setUsedHeapMemory(headMemory.getUsed())
                .setInitNonHeapMemory(nonheadMemory.getInit())
                .setMaxNonHeapMemory(nonheadMemory.getMax())
                .setUsedNonHeapMemory(nonheadMemory.getUsed())
                .setCpuRatio(printlnCpuInfo());
    }

    public static Double printlnCpuInfo() throws InterruptedException {
        //System.out.println("----------------cpu信息----------------");
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 睡眠1s
        TimeUnit.SECONDS.sleep(1);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
/*        System.out.println("----------------cpu信息----------------");
        System.out.println("cpu核数:" + processor.getLogicalProcessorCount());
        System.out.println("cpu系统使用率:" + new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu));
        System.out.println("cpu用户使用率:" + new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
        System.out.println("cpu当前等待率:" + new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu));
        System.out.println("cpu当前使用率:" + new DecimalFormat("#.##%").format(1.0-(idle * 1.0 / totalCpu)));*/
        return Double.parseDouble(new DecimalFormat("#.##").format(1.0-(idle * 1.0 / totalCpu)));
    }

    /**
     * 获取linux 磁盘使用率
     *
     * @return 磁盘使用率
     */
    private static double getUnixDiskUsage() {
        String ioCmdStr = "df -h /";
        String resultInfo = runCommand(ioCmdStr);
        String[] data = resultInfo.split(" +");
        HashMap<String, Double> map = getDiskInfo();
        double total = map.get("total");
        double used = map.get("used");
        return (total - used) / total;
    }

    private static HashMap<String, Double> getDiskInfo() {
        String ioCmdStr = "df -h /";
        String resultInfo = runCommand(ioCmdStr);
        String[] data = resultInfo.split(" +");
        double total = Double.parseDouble(data[10].replace("Gi", ""));
        double used = Double.parseDouble(data[12].replace("Gi", ""));
        HashMap<String, Double> map = new HashMap<String, Double>();
        map.put("total", total);
        map.put("used", used);
        return map;
    }

    /**
     * 执行系统命令
     *
     * @param CMD 命令
     * @return 字符串结果
     */
    private static String runCommand(String CMD) {
        StringBuilder info = new StringBuilder();
        try {
            Process pos = Runtime.getRuntime().exec(CMD);
            pos.waitFor();
            InputStreamReader isr = new InputStreamReader(pos.getInputStream());
            LineNumberReader lnr = new LineNumberReader(isr);
            String line;
            while ((line = lnr.readLine()) != null) {
                info.append(line).append("\n");
            }
        } catch (Exception e) {
            info = new StringBuilder(e.toString());
        }
        return info.toString();
    }

    public static void printMemoryInfo(){
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage headMemory = memory.getHeapMemoryUsage();
        System.out.println("head堆:");
        System.out.println("\t初始(字节):"+headMemory.getInit());
        System.out.println("\t最大(上限)(字节):"+headMemory.getMax());
        System.out.println("\t当前(已使用)(字节):"+headMemory.getUsed());
        System.out.println("\t提交的内存(已申请)(字节):"+headMemory.getCommitted());
        System.out.println("\t使用率:"+headMemory.getUsed()*100/headMemory.getCommitted()+"%");

        System.out.println("non-head非堆:");
        MemoryUsage nonheadMemory = memory.getNonHeapMemoryUsage();
        System.out.println("\t初始(字节):"+nonheadMemory.getInit());
        System.out.println("\t最大(上限)(字节):"+nonheadMemory.getMax());
        System.out.println("\t当前(已使用)(字节):"+nonheadMemory.getUsed());
        System.out.println("\t提交的内存(已申请)(字节):"+nonheadMemory.getCommitted());
        System.out.println("\t使用率:"+nonheadMemory.getUsed()*100/nonheadMemory.getCommitted()+"%");
    }
}

