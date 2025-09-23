package org.foodust.utilitymanager.BaseModule;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.foodust.utilitymanager.UtilityManager;

import java.io.File;
import java.io.IOException;

/**
 * 설정 파일 관련 유틸리티 모듈
 * YAML 설정 파일의 로드, 저장, 관리 기능을 제공합니다.
 */
public class ConfigModule {
    /**
     * 지정된 파일명의 설정 파일을 로드합니다.
     * 파일이 존재하지 않으면 플러그인 리소스에서 기본 파일을 복사합니다.
     * 
     * @param fileName 로드할 설정 파일명 (확장자 포함)
     * @return 로드된 FileConfiguration 객체
     */
    public FileConfiguration getConfig(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("파일명이 null이거나 비어있습니다.");
        }
        
        File configFile = new File(UtilityManager.getPlugin().getDataFolder(), fileName);
        
        // 파일이 존재하지 않으면 기본 설정을 생성
        if (!configFile.exists()) {
            try {
                UtilityManager.getPlugin().saveResource(fileName, false);
            } catch (Exception e) {
                Bukkit.getLogger().warning("기본 설정 파일을 생성할 수 없습니다: " + fileName);
            }
        }
        
        return YamlConfiguration.loadConfiguration(configFile);
    }

    /**
     * 설정 파일을 저장합니다.
     * 
     * @param config 저장할 FileConfiguration 객체
     * @param fileName 저장할 파일명 (확장자 포함)
     * @return 저장 성공 여부
     */
    public boolean saveConfig(FileConfiguration config, String fileName) {
        if (config == null) {
            Bukkit.getLogger().warning("저장할 설정이 null입니다.");
            return false;
        }
        
        if (fileName == null || fileName.trim().isEmpty()) {
            Bukkit.getLogger().warning("파일명이 null이거나 비어있습니다.");
            return false;
        }
        
        File configFile = new File(UtilityManager.getPlugin().getDataFolder(), fileName);
        
        // 부모 디렉토리가 없으면 생성
        File parentDir = configFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        
        try {
            config.save(configFile);
            return true;
        } catch (IOException e) {
            Bukkit.getLogger().severe("설정 파일 저장 중 오류 발생: " + fileName + " - " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 설정 파일이 존재하는지 확인합니다.
     * 
     * @param fileName 확인할 파일명
     * @return 파일이 존재하면 true, 아니면 false
     */
    public boolean configExists(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        
        File configFile = new File(UtilityManager.getPlugin().getDataFolder(), fileName);
        return configFile.exists();
    }
    
    /**
     * 설정 파일을 삭제합니다.
     * 
     * @param fileName 삭제할 파일명
     * @return 삭제 성공 여부
     */
    public boolean deleteConfig(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        
        File configFile = new File(UtilityManager.getPlugin().getDataFolder(), fileName);
        if (configFile.exists()) {
            return configFile.delete();
        }
        return false;
    }
    
    /**
     * 설정 파일을 백업합니다.
     * 
     * @param fileName 백업할 파일명
     * @return 백업 성공 여부
     */
    public boolean backupConfig(String fileName) {
        if (!configExists(fileName)) {
            return false;
        }
        
        String backupFileName = fileName + ".backup." + System.currentTimeMillis();
        FileConfiguration config = getConfig(fileName);
        return saveConfig(config, backupFileName);
    }
}
