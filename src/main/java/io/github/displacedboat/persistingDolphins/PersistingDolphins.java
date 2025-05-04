package io.github.displacedboat.persistingDolphins;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PersistingDolphins extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
        // Prevent drowning, dryout, or suffocation damage to a named dolphin
        Entity entity = e.getEntity();
        EntityDamageEvent.DamageCause cause = e.getCause();

        // There may be a more performant order to do this checks in
        if ((entity.getType() == EntityType.DOLPHIN)
                && (entity.getCustomName() != null)
                && ((cause == EntityDamageEvent.DamageCause.SUFFOCATION)
                    || (cause == EntityDamageEvent.DamageCause.DROWNING)
                    || (cause == EntityDamageEvent.DamageCause.DRYOUT))) {
            e.setCancelled(true);
        }
    }
}
