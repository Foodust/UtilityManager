package org.foodust.utilitymanager.BaseModule;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * 아이템 관련 유틸리티 모듈
 * Bukkit ItemStack 생성, 비교, 검증 등의 기능을 제공합니다.
 */
public class ItemModule {
    
    /** 에러 상황에서 사용할 기본 아이템 - 흰색 양털 */
    public final String ERROR_WOOL = Material.WHITE_WOOL.name();
    
    /** 에러 상황에서 사용할 기본 아이템 - 썩은 살점 */
    public final String ERROR_ROTTEN = Material.ROTTEN_FLESH.name();

    /**
     * 기본 아이템을 생성합니다.
     * 
     * @param material 아이템 재료
     * @param amount 개수
     * @return 생성된 ItemStack
     */
    public ItemStack createItem(Material material, Integer amount) {
        return createItemWithMeta(material, amount, null, null, null);
    }

    /**
     * 이름이 있는 아이템을 생성합니다.
     * 
     * @param material 아이템 재료
     * @param title 아이템 이름
     * @param amount 개수
     * @return 생성된 ItemStack
     */
    public ItemStack createItem(Material material, String title, Integer amount) {
        return createItemWithMeta(material, amount, title, null, null);
    }

    /**
     * 이름과 설명이 있는 아이템을 생성합니다.
     * 
     * @param material 아이템 재료
     * @param title 아이템 이름
     * @param lore 아이템 설명 (여러 줄)
     * @param amount 개수
     * @return 생성된 ItemStack
     */
    public ItemStack createItem(Material material, String title, List<String> lore, Integer amount) {
        return createItemWithMeta(material, amount, title, lore, null);
    }

    /**
     * CustomModelData가 있는 커스텀 아이템을 생성합니다.
     * 
     * @param material 아이템 재료
     * @param title 아이템 이름
     * @param lore 아이템 설명 (여러 줄)
     * @param customModelData 커스텀 모델 데이터
     * @param amount 개수
     * @return 생성된 ItemStack
     */
    public ItemStack createCustomItem(Material material, String title, List<String> lore, 
                                    Integer customModelData, Integer amount) {
        return createItemWithMeta(material, amount, title, lore, customModelData);
    }

    /**
     * CustomModelData가 있는 커스텀 아이템을 생성합니다 (설명 없음).
     * 
     * @param material 아이템 재료
     * @param title 아이템 이름
     * @param customModelData 커스텀 모델 데이터
     * @param amount 개수
     * @return 생성된 ItemStack
     */
    public ItemStack createCustomItem(Material material, String title, Integer customModelData, Integer amount) {
        return createItemWithMeta(material, amount, title, null, customModelData);
    }

    /**
     * 메타 정보와 함께 아이템을 생성하는 내부 메서드
     * 
     * @param material 아이템 재료
     * @param amount 개수
     * @param title 아이템 이름 (선택사항)
     * @param lore 아이템 설명 (선택사항)
     * @param customModelData 커스텀 모델 데이터 (선택사항)
     * @return 생성된 ItemStack
     */
    private ItemStack createItemWithMeta(Material material, Integer amount, String title, 
                                       List<String> lore, Integer customModelData) {
        // AIR 타입인 경우 임시로 다른 재료 사용
        ItemStack item = new ItemStack(material == Material.AIR ? Material.ROTTEN_FLESH : material, amount);
        
        // 메타 정보가 하나라도 있으면 설정
        if (title != null || lore != null || customModelData != null) {
            ItemMeta itemMeta = item.getItemMeta();
            if (itemMeta != null) {
                if (title != null) {
                    itemMeta.setDisplayName(title);
                }
                if (lore != null) {
                    itemMeta.setLore(lore);
                }
                if (customModelData != null) {
                    itemMeta.setCustomModelData(customModelData);
                }
                item.setItemMeta(itemMeta);
            }
        }
        
        // 원래 AIR였다면 다시 AIR로 변경
        if (material == Material.AIR) {
            item.setType(Material.AIR);
        }
        
        return item;
    }

    /**
     * 두 아이템이 내구도를 제외하고 동일한지 비교합니다.
     * 강화 시스템에서 내구도가 닳은 아이템도 같은 아이템으로 취급하기 위해 사용됩니다.
     * CustomModelData가 없는 아이템들은 Material만 비교합니다.
     *
     * @param item1 비교할 첫 번째 아이템
     * @param item2 비교할 두 번째 아이템
     * @return 내구도를 제외하고 동일하면 true, 아니면 false
     */
    public boolean isSimilarIgnoreDurability(ItemStack item1, ItemStack item2) {
        if (item1 == null || item2 == null) {
            return item1 == item2;
        }

        // 재료 타입이 다르면 false
        if (item1.getType() != item2.getType()) {
            return false;
        }

        // 둘 다 메타가 없으면 true (Material만 같으면 됨)
        if (!item1.hasItemMeta() && !item2.hasItemMeta()) {
            return true;
        }

        // 한쪽만 메타가 있으면서, 메타가 있는 쪽에 CustomModelData가 없다면 Material만 비교
        if (item1.hasItemMeta() != item2.hasItemMeta()) {
            ItemMeta existingMeta = item1.hasItemMeta() ? item1.getItemMeta() : item2.getItemMeta();
            return existingMeta != null && !existingMeta.hasCustomModelData();
        }

        ItemMeta meta1 = item1.getItemMeta();
        ItemMeta meta2 = item2.getItemMeta();
        if (meta1 == null || meta2 == null) {
            return false;
        }

        // 둘 다 CustomModelData가 없으면 Material만 비교하여 true
        if (!meta1.hasCustomModelData() && !meta2.hasCustomModelData()) {
            return true;
        }

        // 한쪽만 CustomModelData가 있으면 false
        if (meta1.hasCustomModelData() != meta2.hasCustomModelData()) {
            return false;
        }

        // 둘 다 CustomModelData가 있으면 값과 재료 타입 비교
        return meta1.getCustomModelData() == meta2.getCustomModelData() &&
               item1.getType() == item2.getType();
    }

    /**
     * 두 아이템이 수량을 제외하고 동일한지 비교합니다.
     * 아이템이 겹쳐있어도 인식할 수 있도록 사용됩니다.
     *
     * @param item1 비교할 첫 번째 아이템
     * @param item2 비교할 두 번째 아이템
     * @return 수량을 제외하고 동일하면 true, 아니면 false
     */
    public boolean isSimilarIgnoreAmount(ItemStack item1, ItemStack item2) {
        if (item1 == null || item2 == null) {
            return item1 == item2;
        }

        // 재료 타입이 다르면 false
        if (item1.getType() != item2.getType()) {
            return false;
        }

        // 둘 다 메타가 없으면 true (Material만 같으면 됨)
        if (!item1.hasItemMeta() && !item2.hasItemMeta()) {
            return true;
        }

        // 한쪽만 메타가 있으면 false
        if (item1.hasItemMeta() != item2.hasItemMeta()) {
            return false;
        }

        ItemMeta meta1 = item1.getItemMeta();
        ItemMeta meta2 = item2.getItemMeta();
        if (meta1 == null || meta2 == null) {
            return false;
        }

        // 모든 메타 정보 비교 (표시 이름, 설명, CustomModelData)
        return java.util.Objects.equals(meta1.getDisplayName(), meta2.getDisplayName()) &&
               java.util.Objects.equals(meta1.getLore(), meta2.getLore()) &&
               meta1.hasCustomModelData() == meta2.hasCustomModelData() &&
               (!meta1.hasCustomModelData() || 
                meta1.getCustomModelData()==meta2.getCustomModelData());
    }

    /**
     * 문자열이 유효한 Material 이름인지 확인합니다.
     * 
     * @param materialName 확인할 Material 이름
     * @return 유효한 Material이면 true, 아니면 false
     */
    public boolean isValidMaterial(String materialName) {
        if (materialName == null || materialName.trim().isEmpty()) {
            return false;
        }
        try {
            Material.valueOf(materialName.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 아이템을 복사합니다.
     * 
     * @param original 복사할 원본 아이템
     * @return 복사된 아이템 (원본이 null이면 null 반환)
     */
    public ItemStack cloneItem(ItemStack original) {
        return original != null ? original.clone() : null;
    }

    /**
     * 아이템이 CustomModelData를 가지고 있는지 확인합니다.
     * 
     * @param item 확인할 아이템
     * @return CustomModelData가 있으면 true, 아니면 false
     */
    public boolean hasCustomModelData(ItemStack item) {
        return item != null && item.hasItemMeta() && 
               item.getItemMeta() != null && item.getItemMeta().hasCustomModelData();
    }

    /**
     * 아이템의 CustomModelData 값을 가져옵니다.
     * 
     * @param item 확인할 아이템
     * @return CustomModelData 값 (없으면 null)
     */
    public Integer getCustomModelData(ItemStack item) {
        if (hasCustomModelData(item)) {
            return item.getItemMeta().getCustomModelData();
        }
        return null;
    }

    /**
     * 아이템이 비어있는지 확인합니다.
     * null, AIR 타입, 또는 개수가 0 이하인 경우 비어있다고 판단합니다.
     * 
     * @param item 확인할 아이템
     * @return 비어있으면 true, 아니면 false
     */
    public boolean isEmpty(ItemStack item) {
        return item == null || item.getType() == Material.AIR || item.getAmount() <= 0;
    }

    /**
     * 두 아이템이 같은 타입인지 확인합니다.
     * 
     * @param item1 첫 번째 아이템
     * @param item2 두 번째 아이템
     * @return 같은 타입이면 true, 아니면 false
     */
    public boolean isSameType(ItemStack item1, ItemStack item2) {
        if (item1 == null || item2 == null) {
            return item1 == item2;
        }
        return item1.getType() == item2.getType();
    }

    /**
     * 아이템이 유효한지 확인합니다.
     * null이 아니고, AIR이 아니며, 개수가 1개 이상인 경우 유효하다고 판단합니다.
     * 
     * @param item 확인할 아이템
     * @return 유효하면 true, 아니면 false
     */
    public boolean isValidItem(ItemStack item) {
        return !isEmpty(item);
    }
}