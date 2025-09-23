package org.foodust.utilitymanager.BaseModule;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.Vector3d;

/**
 * 디스플레이 엔티티 관련 유틸리티 모듈
 * ItemDisplay, BlockDisplay, TextDisplay 생성 및 관리 기능을 제공합니다.
 */
public class DisplayModule {
    
    /**
     * 아이템 디스플레이를 생성합니다 (World 기준, Material)
     * 
     * @param world 월드
     * @param location 생성 위치
     * @param material 아이템 재료
     * @param size 크기 (균등 스케일)
     * @return 생성된 ItemDisplay
     */
    public ItemDisplay makeItemDisplay(World world, Location location, Material material, Double size) {
        ItemDisplay itemDisplay = world.spawn(location, ItemDisplay.class);
        itemDisplay.setItemStack(new ItemStack(material));
        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(size);
        itemDisplay.setTransformation(transformation);
        return itemDisplay;
    }

    /**
     * 아이템 디스플레이를 생성합니다 (World 기준, ItemStack)
     * 
     * @param world 월드
     * @param location 생성 위치
     * @param itemStack 아이템 스택
     * @param size 크기 (균등 스케일)
     * @return 생성된 ItemDisplay
     */
    public ItemDisplay makeItemDisplay(World world, Location location, ItemStack itemStack, Double size) {
        ItemDisplay itemDisplay = world.spawn(location, ItemDisplay.class);
        itemDisplay.setItemStack(itemStack);
        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(size);
        itemDisplay.setTransformation(transformation);
        return itemDisplay;
    }

    /**
     * 아이템 디스플레이를 생성합니다 (Entity 기준, Material)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param material 아이템 재료
     * @param size 크기 (균등 스케일)
     * @return 생성된 ItemDisplay
     */
    public ItemDisplay makeItemDisplay(Entity entity, Location location, Material material, Double size) {
        ItemDisplay itemDisplay = entity.getWorld().spawn(location, ItemDisplay.class);
        itemDisplay.setItemStack(new ItemStack(material));
        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(size);
        itemDisplay.setTransformation(transformation);
        return itemDisplay;
    }

    /**
     * 아이템 디스플레이를 생성합니다 (Entity 기준, Billboard 설정)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param itemStack 아이템 스택
     * @param size 크기 (균등 스케일)
     * @param billboard 빌보드 설정
     * @return 생성된 ItemDisplay
     */
    public ItemDisplay makeItemDisplay(Entity entity, Location location, ItemStack itemStack, Double size, Display.Billboard billboard) {
        ItemDisplay itemDisplay = entity.getWorld().spawn(location, ItemDisplay.class);
        itemDisplay.setItemStack(itemStack);
        itemDisplay.setBillboard(billboard);
        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(size);
        itemDisplay.setTransformation(transformation);
        return itemDisplay;
    }

    /**
     * 아이템 디스플레이를 생성합니다 (Entity 기준, ItemStack)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param itemStack 아이템 스택
     * @param size 크기 (균등 스케일)
     * @return 생성된 ItemDisplay
     */
    public ItemDisplay makeItemDisplay(Entity entity, Location location, ItemStack itemStack, Double size) {
        ItemDisplay itemDisplay = entity.getWorld().spawn(location, ItemDisplay.class);
        itemDisplay.setItemStack(itemStack);
        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(size);
        itemDisplay.setTransformation(transformation);
        return itemDisplay;
    }

    /**
     * 아이템 디스플레이를 생성합니다 (Entity 기준, 개별 크기 설정)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param itemStack 아이템 스택
     * @param x X축 크기
     * @param y Y축 크기
     * @param z Z축 크기
     * @return 생성된 ItemDisplay
     */
    public ItemDisplay makeItemDisplay(Entity entity, Location location, ItemStack itemStack, float x, float y, float z) {
        ItemDisplay itemDisplay = entity.getWorld().spawn(location, ItemDisplay.class);
        itemDisplay.setItemStack(itemStack);
        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(x, y, z);
        itemDisplay.setTransformation(transformation);
        return itemDisplay;
    }

    /**
     * 아이템 디스플레이를 생성합니다 (Entity 기준, Vector3d 크기)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param itemStack 아이템 스택
     * @param size 크기 벡터
     * @return 생성된 ItemDisplay
     */
    public ItemDisplay makeItemDisplay(Entity entity, Location location, ItemStack itemStack, Vector3d size) {
        ItemDisplay itemDisplay = entity.getWorld().spawn(location, ItemDisplay.class);
        itemDisplay.setItemStack(itemStack);
        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(size);
        itemDisplay.setTransformation(transformation);
        return itemDisplay;
    }

    /**
     * 아이템 디스플레이를 생성합니다 (Entity 기준, Billboard + Vector3d 크기)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param itemStack 아이템 스택
     * @param billboard 빌보드 설정
     * @param size 크기 벡터
     * @return 생성된 ItemDisplay
     */
    public ItemDisplay makeItemDisplay(Entity entity, Location location, ItemStack itemStack, Display.Billboard billboard, Vector3d size) {
        ItemDisplay itemDisplay = entity.getWorld().spawn(location, ItemDisplay.class);
        itemDisplay.setItemStack(itemStack);
        itemDisplay.setBillboard(billboard);
        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(size);
        itemDisplay.setTransformation(transformation);
        return itemDisplay;
    }

    /**
     * 블록 디스플레이를 생성합니다 (Entity 기준)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param material 블록 재료
     * @param size 크기 (균등 스케일)
     * @return 생성된 BlockDisplay
     */
    public BlockDisplay makeBlockDisplay(Entity entity, Location location, Material material, Double size) {
        BlockDisplay blockDisplay = entity.getWorld().spawn(location, BlockDisplay.class);
        blockDisplay.setBlock(material.createBlockData());
        Transformation transformation = blockDisplay.getTransformation();
        transformation.getScale().set(size);
        blockDisplay.setTransformation(transformation);
        return blockDisplay;
    }

    /**
     * 블록 디스플레이를 생성합니다 (World 기준)
     * 
     * @param world 월드
     * @param location 생성 위치
     * @param material 블록 재료
     * @param size 크기 (균등 스케일)
     * @return 생성된 BlockDisplay
     */
    public BlockDisplay makeBlockDisplay(World world, Location location, Material material, Double size) {
        BlockDisplay blockDisplay = world.spawn(location, BlockDisplay.class);
        blockDisplay.setBlock(material.createBlockData());
        Transformation transformation = blockDisplay.getTransformation();
        transformation.getScale().set(size);
        blockDisplay.setTransformation(transformation);
        return blockDisplay;
    }

    /**
     * 블록 디스플레이를 생성합니다 (Entity 기준, 개별 크기 설정)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param material 블록 재료
     * @param x X축 크기
     * @param y Y축 크기
     * @param z Z축 크기
     * @return 생성된 BlockDisplay
     */
    public BlockDisplay makeBlockDisplay(Entity entity, Location location, Material material, float x, float y, float z) {
        BlockDisplay blockDisplay = entity.getWorld().spawn(location, BlockDisplay.class);
        blockDisplay.setBlock(material.createBlockData());
        Transformation transformation = blockDisplay.getTransformation();
        transformation.getScale().set(x, y, z);
        blockDisplay.setTransformation(transformation);
        return blockDisplay;
    }

    /**
     * 블록 디스플레이를 생성합니다 (World 기준, 개별 크기 설정)
     * 
     * @param world 월드
     * @param location 생성 위치
     * @param material 블록 재료
     * @param x X축 크기
     * @param y Y축 크기
     * @param z Z축 크기
     * @return 생성된 BlockDisplay
     */
    public BlockDisplay makeBlockDisplay(World world, Location location, Material material, float x, float y, float z) {
        BlockDisplay blockDisplay = world.spawn(location, BlockDisplay.class);
        blockDisplay.setBlock(material.createBlockData());
        Transformation transformation = blockDisplay.getTransformation();
        transformation.getScale().set(x, y, z);
        blockDisplay.setTransformation(transformation);
        return blockDisplay;
    }

    /**
     * 블록 디스플레이를 생성합니다 (Entity 기준, Vector3d 크기)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param material 블록 재료
     * @param size 크기 벡터
     * @return 생성된 BlockDisplay
     */
    public BlockDisplay makeBlockDisplay(Entity entity, Location location, Material material, Vector3d size) {
        BlockDisplay blockDisplay = entity.getWorld().spawn(location, BlockDisplay.class);
        blockDisplay.setBlock(material.createBlockData());
        Transformation transformation = blockDisplay.getTransformation();
        transformation.getScale().set(size);
        blockDisplay.setTransformation(transformation);
        return blockDisplay;
    }

    /**
     * 블록 디스플레이를 생성합니다 (World 기준, Vector3d 크기)
     * 
     * @param world 월드
     * @param location 생성 위치
     * @param material 블록 재료
     * @param size 크기 벡터
     * @return 생성된 BlockDisplay
     */
    public BlockDisplay makeBlockDisplay(World world, Location location, Material material, Vector3d size) {
        BlockDisplay blockDisplay = world.spawn(location, BlockDisplay.class);
        blockDisplay.setBlock(material.createBlockData());
        Transformation transformation = blockDisplay.getTransformation();
        transformation.getScale().set(size);
        blockDisplay.setTransformation(transformation);
        return blockDisplay;
    }

    /**
     * 블록 디스플레이를 생성합니다 (Entity 기준, Billboard 설정)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param material 블록 재료
     * @param size 크기 (균등 스케일)
     * @param billboard 빌보드 설정
     * @return 생성된 BlockDisplay
     */
    public BlockDisplay makeBlockDisplay(Entity entity, Location location, Material material, Double size, Display.Billboard billboard) {
        BlockDisplay blockDisplay = entity.getWorld().spawn(location, BlockDisplay.class);
        blockDisplay.setBlock(material.createBlockData());
        blockDisplay.setBillboard(billboard);
        Transformation transformation = blockDisplay.getTransformation();
        transformation.getScale().set(size);
        blockDisplay.setTransformation(transformation);
        return blockDisplay;
    }

    /**
     * 블록 디스플레이를 생성합니다 (World 기준, Billboard 설정)
     * 
     * @param world 월드
     * @param location 생성 위치
     * @param material 블록 재료
     * @param size 크기 (균등 스케일)
     * @param billboard 빌보드 설정
     * @return 생성된 BlockDisplay
     */
    public BlockDisplay makeBlockDisplay(World world, Location location, Material material, Double size, Display.Billboard billboard) {
        BlockDisplay blockDisplay = world.spawn(location, BlockDisplay.class);
        blockDisplay.setBlock(material.createBlockData());
        blockDisplay.setBillboard(billboard);
        Transformation transformation = blockDisplay.getTransformation();
        transformation.getScale().set(size);
        blockDisplay.setTransformation(transformation);
        return blockDisplay;
    }

    /**
     * 텍스트 디스플레이를 생성합니다 (Entity 기준)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param text 표시할 텍스트
     * @param size 크기 (균등 스케일)
     * @return 생성된 TextDisplay
     */
    public TextDisplay makeTextDisplay(Entity entity, Location location, String text, Double size) {
        TextDisplay textDisplay = entity.getWorld().spawn(location, TextDisplay.class);
        textDisplay.setText(text);
        Transformation transformation = textDisplay.getTransformation();
        transformation.getScale().set(size);
        textDisplay.setTransformation(transformation);
        return textDisplay;
    }

    /**
     * 텍스트 디스플레이를 생성합니다 (World 기준)
     * 
     * @param world 월드
     * @param location 생성 위치
     * @param text 표시할 텍스트
     * @param size 크기 (균등 스케일)
     * @return 생성된 TextDisplay
     */
    public TextDisplay makeTextDisplay(World world, Location location, String text, Double size) {
        TextDisplay textDisplay = world.spawn(location, TextDisplay.class);
        textDisplay.setText(text);
        Transformation transformation = textDisplay.getTransformation();
        transformation.getScale().set(size);
        textDisplay.setTransformation(transformation);
        return textDisplay;
    }

    /**
     * 텍스트 디스플레이를 생성합니다 (Entity 기준, 개별 크기 설정)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param text 표시할 텍스트
     * @param x X축 크기
     * @param y Y축 크기
     * @param z Z축 크기
     * @return 생성된 TextDisplay
     */
    public TextDisplay makeTextDisplay(Entity entity, Location location, String text, float x, float y, float z) {
        TextDisplay textDisplay = entity.getWorld().spawn(location, TextDisplay.class);
        textDisplay.setText(text);
        Transformation transformation = textDisplay.getTransformation();
        transformation.getScale().set(x, y, z);
        textDisplay.setTransformation(transformation);
        return textDisplay;
    }

    /**
     * 텍스트 디스플레이를 생성합니다 (World 기준, 개별 크기 설정)
     * 
     * @param world 월드
     * @param location 생성 위치
     * @param text 표시할 텍스트
     * @param x X축 크기
     * @param y Y축 크기
     * @param z Z축 크기
     * @return 생성된 TextDisplay
     */
    public TextDisplay makeTextDisplay(World world, Location location, String text, float x, float y, float z) {
        TextDisplay textDisplay = world.spawn(location, TextDisplay.class);
        textDisplay.setText(text);
        Transformation transformation = textDisplay.getTransformation();
        transformation.getScale().set(x, y, z);
        textDisplay.setTransformation(transformation);
        return textDisplay;
    }

    /**
     * 텍스트 디스플레이를 생성합니다 (Entity 기준, Vector3d 크기)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param text 표시할 텍스트
     * @param size 크기 벡터
     * @return 생성된 TextDisplay
     */
    public TextDisplay makeTextDisplay(Entity entity, Location location, String text, Vector3d size) {
        TextDisplay textDisplay = entity.getWorld().spawn(location, TextDisplay.class);
        textDisplay.setText(text);
        Transformation transformation = textDisplay.getTransformation();
        transformation.getScale().set(size);
        textDisplay.setTransformation(transformation);
        return textDisplay;
    }

    /**
     * 텍스트 디스플레이를 생성합니다 (World 기준, Vector3d 크기)
     * 
     * @param world 월드
     * @param location 생성 위치
     * @param text 표시할 텍스트
     * @param size 크기 벡터
     * @return 생성된 TextDisplay
     */
    public TextDisplay makeTextDisplay(World world, Location location, String text, Vector3d size) {
        TextDisplay textDisplay = world.spawn(location, TextDisplay.class);
        textDisplay.setText(text);
        Transformation transformation = textDisplay.getTransformation();
        transformation.getScale().set(size);
        textDisplay.setTransformation(transformation);
        return textDisplay;
    }

    /**
     * 텍스트 디스플레이를 생성합니다 (Entity 기준, Billboard 설정)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param text 표시할 텍스트
     * @param size 크기 (균등 스케일)
     * @param billboard 빌보드 설정
     * @return 생성된 TextDisplay
     */
    public TextDisplay makeTextDisplay(Entity entity, Location location, String text, Double size, Display.Billboard billboard) {
        TextDisplay textDisplay = entity.getWorld().spawn(location, TextDisplay.class);
        textDisplay.setText(text);
        textDisplay.setBillboard(billboard);
        Transformation transformation = textDisplay.getTransformation();
        transformation.getScale().set(size);
        textDisplay.setTransformation(transformation);
        return textDisplay;
    }

    /**
     * 텍스트 디스플레이를 생성합니다 (World 기준, Billboard 설정)
     * 
     * @param world 월드
     * @param location 생성 위치
     * @param text 표시할 텍스트
     * @param size 크기 (균등 스케일)
     * @param billboard 빌보드 설정
     * @return 생성된 TextDisplay
     */
    public TextDisplay makeTextDisplay(World world, Location location, String text, Double size, Display.Billboard billboard) {
        TextDisplay textDisplay = world.spawn(location, TextDisplay.class);
        textDisplay.setText(text);
        textDisplay.setBillboard(billboard);
        Transformation transformation = textDisplay.getTransformation();
        transformation.getScale().set(size);
        textDisplay.setTransformation(transformation);
        return textDisplay;
    }

    /**
     * 텍스트 디스플레이를 생성합니다 (Entity 기준, Billboard + Vector3d 크기)
     * 
     * @param entity 기준 엔티티
     * @param location 생성 위치
     * @param text 표시할 텍스트
     * @param billboard 빌보드 설정
     * @param size 크기 벡터
     * @return 생성된 TextDisplay
     */
    public TextDisplay makeTextDisplay(Entity entity, Location location, String text, Display.Billboard billboard, Vector3d size) {
        TextDisplay textDisplay = entity.getWorld().spawn(location, TextDisplay.class);
        textDisplay.setText(text);
        textDisplay.setBillboard(billboard);
        Transformation transformation = textDisplay.getTransformation();
        transformation.getScale().set(size);
        textDisplay.setTransformation(transformation);
        return textDisplay;
    }

    /**
     * 텍스트 디스플레이를 생성합니다 (World 기준, Billboard + Vector3d 크기)
     * 
     * @param world 월드
     * @param location 생성 위치
     * @param text 표시할 텍스트
     * @param billboard 빌보드 설정
     * @param size 크기 벡터
     * @return 생성된 TextDisplay
     */
    public TextDisplay makeTextDisplay(World world, Location location, String text, Display.Billboard billboard, Vector3d size) {
        TextDisplay textDisplay = world.spawn(location, TextDisplay.class);
        textDisplay.setText(text);
        textDisplay.setBillboard(billboard);
        Transformation transformation = textDisplay.getTransformation();
        transformation.getScale().set(size);
        textDisplay.setTransformation(transformation);
        return textDisplay;
    }
}
