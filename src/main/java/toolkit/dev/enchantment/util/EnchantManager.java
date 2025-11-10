package toolkit.dev.enchantment.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import toolkit.dev.enchantment.Enchantment;
import toolkit.dev.enchantment.object.CustomEnchant;

import java.util.*;

public class EnchantManager {
    private final Enchantment plugin;
    private final Map<String, CustomEnchant> enchants = new HashMap<>();

    public EnchantManager(@NotNull Enchantment plugin) {
        this.plugin = plugin;
    }

    public void register(@NotNull CustomEnchant... enchants) {
        for (CustomEnchant enchant : enchants) {
            this.enchants.put(enchant.id(), enchant);
        }
    }

    public boolean has(ItemStack item, String id) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, id);
        return meta.getPersistentDataContainer().has(key, PersistentDataType.INTEGER);
    }

    public int level(ItemStack item, String id) {
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, id);
        Integer lvl = meta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
        return lvl == null ? 0 : lvl;
    }

    public void apply(@NotNull ItemStack item, @NotNull CustomEnchant enchant, int level) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        NamespacedKey key = new NamespacedKey(plugin, enchant.id());
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, level);

        List<Component> lore = meta.lore() == null ? new ArrayList<>() : new ArrayList<>(Objects.requireNonNull(meta.lore()));
        lore.add(Component.text(enchant.name() + " " + level, NamedTextColor.GRAY));

        meta.lore(lore);
        item.setItemMeta(meta);
    }

    public CustomEnchant get(String id) {
        return enchants.get(id);
    }

    public Map<String, CustomEnchant> all() {
        return Collections.unmodifiableMap(enchants);
    }
}
