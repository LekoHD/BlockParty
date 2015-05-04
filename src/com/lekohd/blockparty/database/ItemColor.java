package com.lekohd.blockparty.database;

import org.bukkit.Material;

/*
 * Copyright (C) 2014 Leon167, XxChxppellxX, ScriptJunkie and CPx1989
 */
public class ItemColor {

    public static String getColorCode(Byte b, Material material)
    {
        if (material == Material.STAINED_CLAY) {
            if (b.byteValue() == 0) {
                return "§f"; //White
            }
            if (b.byteValue() == 1) {
                return "§6"; //Orange
            }
            if (b.byteValue() == 2) {
                return "§5"; //Magenta?
            }
            if (b.byteValue() == 3) {
                return "§b"; //Light Blue
            }
            if (b.byteValue() == 4) {
                return "§e"; //Yellow
            }
            if (b.byteValue() == 5) {
                return "§a"; //Lime
            }
            if (b.byteValue() == 6) {
                return "§d"; //Pink
            }
            if (b.byteValue() == 7) {
                return "§8"; //Gray
            }
            if (b.byteValue() == 8) {
                return "§7"; //Light Gray
            }
            if (b.byteValue() == 9) {
                return "§3"; //Cyan
            }
            if (b.byteValue() == 10) {
                return "§5"; //Purple
            }
            if (b.byteValue() == 11) {
                return "§1"; //Blue
            }
            if (b.byteValue() == 12) {
                return "§4"; //Brown?
            }
            if (b.byteValue() == 13) {
                return "§2"; //Green
            }
            if (b.byteValue() == 14) {
                return "§4"; //Red
            }
            if (b.byteValue() == 15) {
                return "§0"; //Black
            }
            return null;
        }
        if (material == Material.WOOL) {
            if (b.byteValue() == 0) {
                return "§f"; //White
            }
            if (b.byteValue() == 1) {
                return "§6"; //Orange
            }
            if (b.byteValue() == 2) {
                return "§5"; //Magenta?
            }
            if (b.byteValue() == 3) {
                return "§b"; //Light Blue
            }
            if (b.byteValue() == 4) {
                return "§e"; //Yellow
            }
            if (b.byteValue() == 5) {
                return "§a"; //Lime
            }
            if (b.byteValue() == 6) {
                return "§d"; //Pink
            }
            if (b.byteValue() == 7) {
                return "§8"; //Gray
            }
            if (b.byteValue() == 8) {
                return "§7"; //Light Gray
            }
            if (b.byteValue() == 9) {
                return "§3"; //Cyan
            }
            if (b.byteValue() == 10) {
                return "§5"; //Purple
            }
            if (b.byteValue() == 11) {
                return "§1"; //Blue
            }
            if (b.byteValue() == 12) {
                return "§4"; //Brown?
            }
            if (b.byteValue() == 13) {
                return "§2"; //Green
            }
            if (b.byteValue() == 14) {
                return "§4"; //Red
            }
            if (b.byteValue() == 15) {
                return "§0"; //Black
            }
            return null;
        }
        return "";
    }

    public static String getColorName(Byte b, Material material)
    {
        if (material == Material.STAINED_CLAY) {
            if (b.byteValue() == 0) {
                return "White"; //White
            }
            if (b.byteValue() == 1) {
                return "Orange6"; //Orange
            }
            if (b.byteValue() == 2) {
                return "Magenta"; //Magenta?
            }
            if (b.byteValue() == 3) {
                return "Light Blue"; //Light Blue
            }
            if (b.byteValue() == 4) {
                return "Yellow"; //Yellow
            }
            if (b.byteValue() == 5) {
                return "Lime"; //Lime
            }
            if (b.byteValue() == 6) {
                return "Pink"; //Pink
            }
            if (b.byteValue() == 7) {
                return "Gray"; //Gray
            }
            if (b.byteValue() == 8) {
                return "Light Gray"; //Light Gray
            }
            if (b.byteValue() == 9) {
                return "Cyan"; //Cyan
            }
            if (b.byteValue() == 10) {
                return "Purple"; //Purple
            }
            if (b.byteValue() == 11) {
                return "Blue"; //Blue
            }
            if (b.byteValue() == 12) {
                return "Brown"; //Brown?
            }
            if (b.byteValue() == 13) {
                return "Green"; //Green
            }
            if (b.byteValue() == 14) {
                return "Red"; //Red
            }
            if (b.byteValue() == 15) {
                return "Black"; //Black
            }
            return null;
        }
        if (material == Material.WOOL) {
            if (b.byteValue() == 0) {
                return "White"; //White
            }
            if (b.byteValue() == 1) {
                return "Orange6"; //Orange
            }
            if (b.byteValue() == 2) {
                return "Magenta"; //Magenta?
            }
            if (b.byteValue() == 3) {
                return "Light Blue"; //Light Blue
            }
            if (b.byteValue() == 4) {
                return "Yellow"; //Yellow
            }
            if (b.byteValue() == 5) {
                return "Lime"; //Lime
            }
            if (b.byteValue() == 6) {
                return "Pink"; //Pink
            }
            if (b.byteValue() == 7) {
                return "Gray"; //Gray
            }
            if (b.byteValue() == 8) {
                return "Light Gray"; //Light Gray
            }
            if (b.byteValue() == 9) {
                return "Cyan"; //Cyan
            }
            if (b.byteValue() == 10) {
                return "Purple"; //Purple
            }
            if (b.byteValue() == 11) {
                return "Blue"; //Blue
            }
            if (b.byteValue() == 12) {
                return "Brown"; //Brown?
            }
            if (b.byteValue() == 13) {
                return "Green"; //Green
            }
            if (b.byteValue() == 14) {
                return "Red"; //Red
            }
            if (b.byteValue() == 15) {
                return "Black"; //Black
            }
            return null;
        }
        return "";
    }

}
