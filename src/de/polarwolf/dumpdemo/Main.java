package de.polarwolf.dumpdemo;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		saveDefaultConfig();
		new DumpDemoListener(this);
		this.getLogger().info("The Dump Demo for HeliumBalloon has started.");
		this.getLogger().info("Please read the description in the config.yml for more information.");
	}

}
