package de.pro_crafting.worldfuscator.Core;

import com.comphenix.example.State;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class BlockTranslater {
    private Configuration configuration;

    public BlockTranslater() {
    }

    public BlockTranslater(Configuration configuration) {
        this.configuration = configuration;
    }

    protected boolean hasRights(Player player, int x, int y, int z, World world) {
        return false;
    }

    public int translateBlockID(World world, int x, int y, int z, Player player, State block) {
        if (configuration.getHideIds().contains(block.getId())) {
            if (!this.hasRights(player, x, y, z, world)) {
                if (configuration.isDebugEnabled()) {
                    System.out.println("No Rights: Translation for " + x + "|" + y + "|" + z + " for " + player.getName() + " block " + block);
                    new Exception().printStackTrace();
                }
                return this.configuration.getObfuscationBlock();
            }
        }
        if (configuration.isDebugEnabled()) {
            System.out.println("Passed: Translation for " + x + "|" + y + "|" + z + " for " + player.getName() + " block " + block);
        }
        return block.getId();
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
