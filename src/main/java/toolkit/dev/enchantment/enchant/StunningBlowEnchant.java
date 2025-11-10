package toolkit.dev.enchantment.enchant;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import toolkit.dev.enchantment.object.CustomEnchant;

public class StunningBlowEnchant implements CustomEnchant {
    @Override
    public String id() {
        return "stunning_blow";
    }

    @Override
    public String name() {
        return "Stunning Blow";
    }

    @Override
    public boolean canApply(ItemStack item) {
        return item.getType().name().contains("SWORD") || item.getType().name().contains("AXE");
    }

    @Override
    public void onHit(Player attacker, Entity target, int level) {
        if (!(target instanceof LivingEntity entity)) return;

        int duration = 20 * level;
        int chance = 20 * level;

        if (Math.random() * 100 <= chance) {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, duration, 1));
            attacker.sendActionBar(Component.text("Stunning Blow!", NamedTextColor.YELLOW));
        }
    }
}
