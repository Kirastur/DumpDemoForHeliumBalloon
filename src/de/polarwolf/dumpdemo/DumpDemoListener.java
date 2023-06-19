package de.polarwolf.dumpdemo;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import de.polarwolf.heliumballoon.config.ConfigManager;
import de.polarwolf.heliumballoon.config.ConfigSection;
import de.polarwolf.heliumballoon.events.BalloonRebuildConfigEvent;
import de.polarwolf.heliumballoon.exception.BalloonException;

public class DumpDemoListener implements Listener {

	protected static final String PLUGIN_HELIUMBALLOON = "HeliumBalloon";
	protected static final String SECTION_HELIUMBALLOON = "HeliumBalloon";

	protected final Plugin plugin;

	public DumpDemoListener(Plugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	// This event reloads our custom config
	// from our own config.yml
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBalloonRebuildConfigEvent(BalloonRebuildConfigEvent event) {
		try {

			// Don't load our config if the Rebuild is already cancelled
			if (event.isCancelled()) {
				return;
			}

			// reload our config.yml
			plugin.reloadConfig();

			if (!plugin.getConfig().isConfigurationSection(SECTION_HELIUMBALLOON)) {
				plugin.getLogger().info(
						"My configuration section is empty. Please see the description in my config.yml how to fill it.");
				return;
			}

			Plugin heliumBalloonPlugin = plugin.getServer().getPluginManager().getPlugin(PLUGIN_HELIUMBALLOON);
			if (heliumBalloonPlugin == null) {
				plugin.getLogger().warning("Cannot find the HeliumBalloon plugin.");
				return;
			}

			if (heliumBalloonPlugin.getConfig().getConfigurationSection(ConfigManager.SECTION_STARTUP)
					.getBoolean(ConfigManager.PARAM_LOAD_LOCAL_CONFIG)) {
				plugin.getLogger()
						.warning("You must disable loadLocalConfig. Please see the description in my config.yml");
				return;
			}

			// Build our Balloon ConfigSection using the standard syntax
			ConfigurationSection fileSection = plugin.getConfig().getConfigurationSection(SECTION_HELIUMBALLOON);
			ConfigSection newSection = new ConfigSection(plugin.getName(), event.getConfigHelper(), fileSection);

			// Add my new ConfigSection to the list of valid sections
			event.addSection(newSection);

			plugin.getLogger().info("Your DemoDump was successfull");

		} catch (BalloonException be) {
			be.printStackTrace();
			event.cancelWithReason(be);
		} catch (Exception e) {
			e.printStackTrace();
			event.cancelWithReason(new BalloonException(null, BalloonException.JAVA_EXCEPTION, null, e));
		}
	}

}
