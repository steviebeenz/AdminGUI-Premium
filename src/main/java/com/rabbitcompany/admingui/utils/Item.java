package com.rabbitcompany.admingui.utils;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class Item {

	public static ItemStack create(Inventory inv, String material, int amount, int invSlot, String displayName, String... loreString) {
		ItemStack item;
		ArrayList<String> lore = new ArrayList<>();

		item = new ItemStack(XMaterial.matchXMaterial(material).get().get(), amount, XMaterial.matchXMaterial(material).get().getData());

		ItemMeta meta = item.getItemMeta();
		if (meta != null) {
			meta.setDisplayName(Message.chat(displayName));
			for (String s : loreString) lore.add(Message.chat(s));
			meta.setLore(lore);
			item.setItemMeta(meta);
		}

		inv.setItem(invSlot - 1, item);
		return item;
	}

	public static ItemStack createPlayerHead(Inventory inv, String player, int amount, int invSlot, String displayName, String... loreString) {
		ItemStack item = new ItemStack(XMaterial.PLAYER_HEAD.get(), amount);

		ArrayList<String> lore = new ArrayList<>();

		ItemMeta meta = item.getItemMeta();
		if (meta instanceof SkullMeta) {
			SkullMeta skullMeta = (SkullMeta) meta;

			try {
				skullMeta.setOwningPlayer(org.bukkit.Bukkit.getOfflinePlayer(player));
			} catch (Throwable ignored) {
				try {
					skullMeta.setOwner(player);
				} catch (Throwable ignoredAgain) {
					// Leave as plain player head if both fail
				}
			}

			skullMeta.setDisplayName(Message.chat(displayName));

			for (String s : loreString) {
				lore.add(Message.chat(s));
			}

			skullMeta.setLore(lore);
			item.setItemMeta(skullMeta);
		}

		inv.setItem(invSlot - 1, item);
		return item;
	}

	public static ItemStack pre_createPlayerHead(String player) {
		ItemStack item = new ItemStack(XMaterial.PLAYER_HEAD.get(), 1);

		ItemMeta meta = item.getItemMeta();
		if (meta instanceof SkullMeta) {
			SkullMeta skullMeta = (SkullMeta) meta;

			try {
				// Modern Bukkit/Paper method
				skullMeta.setOwningPlayer(org.bukkit.Bukkit.getOfflinePlayer(player));
			} catch (Throwable ignored) {
				// Legacy 1.8 fallback
				try {
					skullMeta.setOwner(player);
				} catch (Throwable ignoredAgain) {
					// Leave as plain player head if both fail
				}
			}

			skullMeta.setDisplayName(Message.chat(player));
			item.setItemMeta(skullMeta);
		}

		return item;
	}

	public static ItemStack after_createPlayerHead(Inventory inv, ItemStack item, int amount, int invSlot, String displayName, String... loreString) {

		ArrayList<String> lore = new ArrayList<>();

		ItemMeta meta = item.getItemMeta();
		if (meta != null) {
			meta.setDisplayName(Message.chat(displayName));
			for (String s : loreString) lore.add(Message.chat(s));
			meta.setLore(lore);
			item.setItemMeta(meta);
		}

		inv.setItem(invSlot - 1, item);
		return item;
	}

}
