package net.tnemc.item.data;

/*
 * The New Economy Minecraft Server Plugin
 *
 * Copyright (C) 2022 Daniel "creatorfromhell" Vidmar
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

import net.tnemc.item.ParsingUtil;
import net.tnemc.item.SerialItemData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BukkitBookData extends BookData<ItemStack> {

  /**
   * This method is used to convert from the implementation's ItemStack object to a valid
   * {@link SerialItemData} object.
   *
   * @param stack The locale itemstack object of the implementation.
   */
  @Override
  public void of(ItemStack stack) {
    final BookMeta meta = (BookMeta)stack.getItemMeta();

    if(meta != null) {
      this.title = meta.getTitle();
      this.author = meta.getAuthor();
      this.generation = meta.getGeneration().name();
      this.pages = meta.getPages();
    }
  }

  /**
   * This method is used to apply the data to the implementation's locale itemstack format.
   *
   * @param stack The locale itemstack object of the implementation.
   */
  @Override
  public ItemStack apply(ItemStack stack) {

    final BookMeta meta = (BookMeta)ParsingUtil.buildFor(stack, BookMeta.class);

    meta.setTitle(title);
    meta.setAuthor(author);
    meta.setGeneration(BookMeta.Generation.valueOf(generation));
    meta.setPages(pages);
    stack.setItemMeta(meta);

    return stack;
  }
}