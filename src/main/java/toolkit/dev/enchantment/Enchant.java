package toolkit.dev.enchantment;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import toolkit.dev.enchantment.enchant.StunningBlowEnchant;
import toolkit.dev.enchantment.listener.CombatListener;
import toolkit.dev.enchantment.util.EnchantManager;

public final class Enchant extends JavaPlugin {

    private final EnchantManager manager = new EnchantManager(this);

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // Register enchants
        manager.register(new StunningBlowEnchant());

        // Register listeners
        registerEvent(new CombatListener(manager));
    }

    private void registerEvent(@NotNull Listener listener) {
        registerEvent(listener, this);
    }

    private void registerEvent(@NotNull Listener listener, @NotNull Plugin plugin) {
        getServer().getPluginManager().registerEvents(listener, plugin);
    }

    public @NotNull EnchantManager manager() {
        return manager;
    }
}
