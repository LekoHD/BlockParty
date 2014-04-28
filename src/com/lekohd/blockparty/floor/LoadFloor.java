package com.lekohd.blockparty.floor;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.lekohd.blockparty.BlockParty;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class LoadFloor {
	private Vector size;
	public CuboidClipboard sh = null;
	public String floorName;
	
	public LoadFloor(String fn){
		floorName = fn;
		loadSchematic();
	}
	
	public void loadSchematic(){
		    //LocalConfiguration config = worldEdit.getConfiguration();
	        //String fileName = floorName;
	        String formatName = null;

	        /*if (args.argsLength() == 1) {
	            formatName = null;
	            fileName = args.getString(0);
	        } else {
	            formatName = args.getString(0);
	            fileName = args.getString(1);
	        }*/
	        File f = new File("plugins//BlockParty//Floors//" + floorName + ".schematic");
	        // File dir = we.getWorkingDirectoryFile(config.saveDir);
	       // File f = we.getSafeOpenFile(player, dir, fileName, "schematic", "schematic");

	        if (!f.exists()) {
	            // player.sendMessage("Schematic " + fileName + " does not exist!");
	            return;
	        }

	        SchematicFormat format = formatName == null ? null : SchematicFormat.getFormat(formatName);
	        if (format == null) {
	            format = SchematicFormat.getFormat(f);
	        }

	        if (format == null) {
	        	// player.sendMessage("Unknown schematic format: " + formatName);
	            return;
	        }

	       /* if (!format.isOfFormat(f) && !args.hasFlag('f')) {
	        	player.sendMessage(fileName + " is not of the " + format.getName() + " schematic format!");
	            return;
	        }*/

	        try {
	                size = format.load(f).getSize();
	                sh = format.load(f);
	        } catch (DataException e) {
	        	 //player.sendMessage("Load error: " + e.getMessage());
	        } catch (IOException e) {
	            //player.sendMessage("Schematic could not read or it does not exist: " + e.getMessage());
	     }
	}
	
	public void placeFloor(String arenaName){
		if(sh == null){
			this.loadSchematic();
			//this.placeFloor(arenaName);
			System.out.println("sh = null");
		} else {
			size = sh.getSize();
			//if(size.getBlockY() == 1 && size.getBlockX() == Main.getArena.get(arenaName).getFloorLength() && size.getBlockZ() == Main.getArena.get(arenaName).getFloorWidth())
            //{
			this.place(sh.getSize(), sh, arenaName);
            //}
			//else
            //{
            	//System.err.println("Floor " + floorName + " must be the same size as in the arena!");
            //}
		}
	}
	
	public String getFloorName(){
		return floorName;
	}
	
	public Vector getSize(){
		return size;
	}
	
	@SuppressWarnings({ "deprecation" })
	public void place(Vector size, CuboidClipboard cc, String arenaName) {
		//System.out.println(this.getFloorName() + " :FloorName");
		//Vector pos = new Vector();
        for (int x = 0; x < size.getBlockX(); x++) {
        	//System.out.println(x);
          // for (int y = 0; y < size.getBlockY(); y++) {
                for (int z = 0; z < size.getBlockZ(); z++) {
        		/*Vector pos = new Vector();
        		pos = pos.add(x, y, 1);*/
                   // BaseBlock block = data[x][y][z];
                   // block.setId(sh.getBlock(pos).getId());
                   
                    //if(block == null)
                    	//continue;
                    // block.setType(sh.getBlock(pos).getType());
                    World world = Bukkit.getWorld(BlockParty.getArena.get(arenaName).getWorld().getName());
                    world.getBlockAt(BlockParty.getArena.get(arenaName).getLocMin().getBlockX() + x, BlockParty.getArena.get(arenaName).getLocMin().getBlockY(), BlockParty.getArena.get(arenaName).getLocMin().getBlockZ() + z).setTypeId(cc.getBlock(new Vector(x ,0 ,z)).getId());
                    world.getBlockAt(BlockParty.getArena.get(arenaName).getLocMin().getBlockX() + x, BlockParty.getArena.get(arenaName).getLocMin().getBlockY(), BlockParty.getArena.get(arenaName).getLocMin().getBlockZ() + z).setData((byte) cc.getBlock(new Vector(x ,0 ,z)).getData());
                }
            //}
        }
    }
}
