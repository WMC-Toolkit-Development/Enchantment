package toolkit.dev.enchantment.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;
import toolkit.dev.enchantment.object.CustomEnchant;
import toolkit.dev.enchantment.util.EnchantManager;

public class CombatListener implements Listener {

    private final EnchantManager manager;

    public CombatListener(@NotNull EnchantManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onHit(@NotNull EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof org.bukkit.entity.Player player)) return;

        var item = player.getInventory().getItemInMainHand();

        for (CustomEnchant enchant : manager.all().values()) {
            if (manager.has(item, enchant.id())) {
                int lvl = manager.level(item, enchant.id());
                enchant.onHit(player, e.getEntity(), lvl);
            }
        }
    }
}
