package org.utilitymanager.BaseModule;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.utilitymanager.UtilityManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    public void runBukkitTaskLater(Runnable task, Long delay, TimeUnit timeUnit) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(task, 0, delay, timeUnit);
    }
}
