package org.utilitymanager.BaseModule;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.utilitymanager.UtilityManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 작업 스케줄링 유틸리티 모듈
 * Bukkit 스케줄러와 Java 스케줄러를 이용한 작업 관리 기능을 제공합니다.
 */
public class TaskModule {
    private final UtilityManager plugin;

    public TaskModule(UtilityManager plugin) {
        this.plugin = plugin;
    }

    public BukkitTask runBukkitTask(Runnable task) {
        return Bukkit.getScheduler().runTask(plugin, task);
    }

    public BukkitTask runBukkitTaskLater(Runnable task, Long delay) {
        return Bukkit.getScheduler().runTaskLater(plugin, task, delay);
    }

    public BukkitTask runBukkitTaskLater(Runnable task, double delay) {
        return Bukkit.getScheduler().runTaskLater(plugin, task, (long) delay);
    }

    public BukkitTask runBukkitTaskLater(Runnable task, float delay) {
        return Bukkit.getScheduler().runTaskLater(plugin, task, (long) delay);
    }

    public BukkitTask runBukkitTaskTimer(Runnable task, Long delay, Long tick) {
        return Bukkit.getScheduler().runTaskTimer(plugin, task, delay, tick);
    }

    public void cancelBukkitTask(BukkitTask bukkitTask) {
        if (bukkitTask != null)
            Bukkit.getScheduler().cancelTask(bukkitTask.getTaskId());
    }

    public BukkitTask runBukkitTaskAsync(Runnable task) {
        return Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
    }

    public BukkitTask runBukkitTaskAsyncLater(Runnable task, Long delay) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delay);
    }

    public BukkitTask runBukkitTaskAsyncTimer(Runnable task, Long delay, Long tick) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, task, delay, tick);
    }

    public void runScheduledTask(Runnable task, Long delay, TimeUnit timeUnit) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(task, delay, timeUnit);
        scheduler.shutdown();
    }

    public void runScheduledTaskTimer(Runnable task, Long initialDelay, Long period, TimeUnit timeUnit) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(task, initialDelay, period, timeUnit);
    }

    public void cancelAllTasks() {
        Bukkit.getScheduler().cancelTasks(plugin);
    }

    public boolean isTaskRunning(BukkitTask task) {
        return task != null && !task.isCancelled();
    }
}
