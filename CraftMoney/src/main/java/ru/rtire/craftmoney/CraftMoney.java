package ru.rtire.craftmoney;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CraftMoney extends JavaPlugin {

    @Override
    public void onEnable() {

        File config = new File(getDataFolder() + File.separator + "src/main/resources/config.yml");
        if(!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }

        recipeCommandBlock();

    }

    private void recipeCommandBlock() {
        ItemStack CommandBlock = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta meta = CommandBlock.getItemMeta();
        assert meta != null;
        meta.setDisplayName(getConfig().getString("displayName"));
        meta.addEnchant(Enchantment.MENDING, 10, true);
        meta.setLore(getConfig().getStringList("lore"));
        meta.setCustomModelData(getConfig().getInt("customModelData"));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        CommandBlock.setItemMeta(meta);

        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(this, "command_block"), CommandBlock);

        recipe.addIngredient(new RecipeChoice.MaterialChoice(Material.DEEPSLATE_COPPER_ORE, Material.COPPER_ORE));
        recipe.addIngredient(new RecipeChoice.MaterialChoice(Material.DEEPSLATE_DIAMOND_ORE, Material.DIAMOND_ORE));
        recipe.addIngredient(new RecipeChoice.MaterialChoice(Material.DEEPSLATE_GOLD_ORE, Material.GOLD_ORE));
        recipe.addIngredient(new RecipeChoice.MaterialChoice(Material.DEEPSLATE_IRON_ORE, Material.IRON_ORE));
        recipe.setCategory(CraftingBookCategory.MISC);
        getServer().addRecipe(recipe);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
