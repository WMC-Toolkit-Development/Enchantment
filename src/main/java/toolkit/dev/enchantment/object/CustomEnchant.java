package toolkit.dev.enchantment.object;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface CustomEnchant {
    String id();
    String name();
    boolean canApply(ItemStack item);
    void onHit(Player attacker, org.bukkit.entity.Entity target, int level);
}
