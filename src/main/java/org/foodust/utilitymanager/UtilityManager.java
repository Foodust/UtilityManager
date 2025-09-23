package org.foodust.utilitymanager;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.logging.Logger;

public final class UtilityManager extends JavaPlugin {
    private BukkitAudiences adventure;
    public static Plugin plugin;
    public static Logger log = Bukkit.getLogger();

    public static Plugin getPlugin() {
        return plugin;
    }

    public static Logger getLog() {
        return log;
    }


    public @NonNull BukkitAudiences getAdventure() {
        if (this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    public BukkitAudiences getBukkitAudiences() {
        return this.adventure;
    }


    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
