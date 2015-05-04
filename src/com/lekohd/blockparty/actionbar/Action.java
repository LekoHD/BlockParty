package com.lekohd.blockparty.actionbar;

import com.lekohd.blockparty.BlockParty;
import com.lekohd.blockparty.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/**
 * Created by Leon on 04.05.2015.
 * Project BlockParty
 * <p/>
 * Copyright (C) 2014 Leon167 { LekoHD
 */
public class Action
{
    public BlockParty plugin;

    public Action(BlockParty plugin)
    {
        this.plugin = plugin;
    }

    public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
    }

    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }

    public static void sendAction(Player p, String msg)
    {

        try
        {
            if (getServerVersion().equalsIgnoreCase("v1_8_R2")) {
                Object icbc = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{'text': '" + msg + "'}" });

                Object ppoc = getNmsClass("PacketPlayOutChat").getConstructor(new Class[] { getNmsClass("IChatBaseComponent"), Byte.TYPE }).newInstance(new Object[] { icbc, Byte.valueOf("2") });

                Object nmsp = p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);

                Object pcon = nmsp.getClass().getField("playerConnection").get(nmsp);

                pcon.getClass().getMethod("sendPacket", new Class[] { getNmsClass("Packet") }).invoke(pcon, new Object[] { ppoc });
            } else {
                Object icbc = getNmsClass("ChatSerializer").getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{'text': '" + msg + "'}" });

                Object ppoc = getNmsClass("PacketPlayOutChat").getConstructor(new Class[] { getNmsClass("IChatBaseComponent"), Byte.TYPE }).newInstance(new Object[] { icbc, Byte.valueOf("2") });

                Object nmsp = p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);

                Object pcon = nmsp.getClass().getField("playerConnection").get(nmsp);

                pcon.getClass().getMethod("sendPacket", new Class[] { getNmsClass("Packet") }).invoke(pcon, new Object[] { ppoc });
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
