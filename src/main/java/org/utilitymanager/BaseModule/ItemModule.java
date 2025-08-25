package org.utilitymanager.BaseModule;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemModule {
    public final String errorWool = Material.WHITE_WOOL.name();
    public final String errorRotten = Material.ROTTEN_FLESH.name();

    public ItemStack setItem(Material material, Integer amount) {
        ItemStack item = new ItemStack(material, amount);
        if (material == Material.AIR) {
            item = new ItemStack(Material.ROTTEN_FLESH, amount);
        }
        return item;
    }

    public ItemStack setItem(Material material, String title, Integer amount) {
        ItemStack item = new ItemStack(material, amount);
        if (material == Material.AIR) {
            item = new ItemStack(Material.ROTTEN_FLESH, amount);
        }
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(title);
        item.setItemMeta(itemMeta);
        if (material == Material.AIR) {
            item.setType(Material.AIR);
        }
        return item;
    }

    public ItemStack setItem(Material material, String title, List<String> lore, Integer amount) {
        ItemStack item = new ItemStack(material, 1);
        if (material == Material.AIR) {
            item = new ItemStack(Material.ROTTEN_FLESH, amount);
        }
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(title);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        if (material == Material.AIR) {
            item.setType(Material.AIR);
        }
        return item;
    }

    public ItemStack setCustomItem(Material material, String title, List<String> lore, Integer code, Integer amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(title);
        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(code);
        item.setItemMeta(itemMeta);
        return item;
    }

    public ItemStack setCustomItem(Material material, String title, Integer code, Integer amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(title);
        itemMeta.setCustomModelData(code);
        item.setItemMeta(itemMeta);
        return item;
    }

    /**
     * 두 아이템이 내구도를 제외하고 동일한지 비교합니다.
     * 강화 시스템에서 내구도가 닳은 아이템도 같은 아이템으로 취급하기 위해 사용됩니다.
     * customModelData가 null인 아이템들은 Material만 비교합니다.
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
            return existingMeta != null && !existingMeta.hasCustomModelData(); // Material이 같고 CustomModelData가 없으므로 true
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

        // 둘 다 CustomModelData가 있으면 값 비교
        return (meta1.getCustomModelData() == meta2.getCustomModelData()) &&
                (item1.getType() == item2.getType());
    }
    /**
     * 두 아이템이 수량을 제외하고 동일한지 비교합니다.
     * 강화 모루 설치 시 아이템이 겹쳐있어도 인식할 수 있도록 사용됩니다.
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

        // 표시 이름 비교
        if (!java.util.Objects.equals(meta1.getDisplayName(), meta2.getDisplayName())) {
            return false;
        }

        // 설명 비교
        if (!java.util.Objects.equals(meta1.getLore(), meta2.getLore())) {
            return false;
        }

        // CustomModelData 비교
        if (meta1.hasCustomModelData() != meta2.hasCustomModelData()) {
            return false;
        }

        if (meta1.hasCustomModelData() && meta2.hasCustomModelData()) {
            return meta1.getCustomModelData() == meta2.getCustomModelData();
        }

        return true;
    }
}
