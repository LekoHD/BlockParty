package com.lekohd.blockparty.statistics;

/*
 * Copyright (C) 2014 Leon167, XxChxppellxX, ScriptJunkie and CPx1989
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import com.lekohd.blockparty.BlockParty;

public class StatsManager {
    private File file;
    private FileConfiguration config;
    public Map<String, Integer> gamesPlayed;
    public Map<String, Integer> eliminations;  // Points will be added soon!
    public Map<String, Integer> placings;
    public Map<String, Integer> victories;
    public Map<String, Integer> points;
    public Map<String, Integer> roundsSurvived;
    public List<String> playerList;
    //private Map<Player, Location> locs;

    public StatsManager(BlockParty blockparty) {
        this.file = new File("plugins//BlockParty//stats.yml");

        this.gamesPlayed = new HashMap<String, Integer>();
        this.eliminations = new HashMap<String, Integer>();
        this.placings = new HashMap<String, Integer>();
        this.victories = new HashMap<String, Integer>();

        this.points = new HashMap<String, Integer>();
        this.roundsSurvived = new HashMap<String, Integer>();

        this.playerList = new ArrayList<String>();
    }

    public void storeStats(Player p) {
        try {
            config = YamlConfiguration.loadConfiguration(file);
            config.set("player." + p.getName() + ".GamesPlayed", gamesPlayed.get(p.getName()));
            config.set("player." + p.getName() + ".Eliminations", eliminations.get(p.getName()));
            config.set("player." + p.getName() + ".Placings", placings.get(p.getName()));
            config.set("player." + p.getName() + ".Victories", victories.get(p.getName()));
            config.set("player." + p.getName() + ".Points", points.get(p.getName()));
            config.set("player." + p.getName() + ".RoundsSurvived", roundsSurvived.get(p.getName()));
            List<String> pList = new ArrayList<String>();
            pList = (List) config.getList("playersListed");
            if (!(pList == null)){
                if (!pList.contains(p.getName())) {
                    pList.add(p.getName());
                }
                for (String name : pList) {
                    if (!this.playerList.contains(name))
                        this.playerList.add(name);
                }
            }
            config.set("playersListed", this.playerList);

            config.save(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getStats(Player p) {
        try {
            config = YamlConfiguration.loadConfiguration(file);

            if (file.exists()) {
                config.load(file);
                gamesPlayed.put(p.getName(), 0);
                eliminations.put(p.getName(), 0);
                placings.put(p.getName(), 0);
                victories.put(p.getName(), 0);
                points.put(p.getName(), 0);
                roundsSurvived.put(p.getName(), 0);
                List<String> pList = new ArrayList<String>();
                pList = (List) config.getList("playersListed");
                if (!(pList == null)){
                    if (!pList.contains(p.getName())) {
                        pList.add(p.getName());
                    }
                    for (String name : pList) {
                        if (!this.playerList.contains(name))
                            this.playerList.add(name);
                    }
                }
                else
                {
                    if (!this.playerList.contains(p.getName()))
                        this.playerList.add(p.getName());
                }
                if(!(config.getInt("player." + p.getName() + ".GamesPlayed") == -1))
                {
                    gamesPlayed.put(p.getName(), config.getInt("player." + p.getName() + ".GamesPlayed"));
                    eliminations.put(p.getName(), config.getInt("player." + p.getName() + ".Eliminations"));
                    placings.put(p.getName(), config.getInt("player." + p.getName() + ".Placings"));
                    victories.put(p.getName(), config.getInt("player." + p.getName() + ".Victories"));
                    points.put(p.getName(), config.getInt("player." + p.getName() + ".Points"));
                    roundsSurvived.put(p.getName(), config.getInt("player." + p.getName() + ".RoundsSurvived"));
                    return;
                }
                else
                {
                    gamesPlayed.put(p.getName(), 0);
                    eliminations.put(p.getName(), 0);
                    placings.put(p.getName(), 0);
                    victories.put(p.getName(), 0);
                    points.put(p.getName(), 0);
                    roundsSurvived.put(p.getName(), 0);
                }
            }
            gamesPlayed.put(p.getName(), 0);
            eliminations.put(p.getName(), 0);
            placings.put(p.getName(), 0);
            victories.put(p.getName(), 0);
            points.put(p.getName(), 0);
            roundsSurvived.put(p.getName(), 0);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
    }

    public void loadAllPlayers()
    {
        try {
            config = YamlConfiguration.loadConfiguration(file);
            if (file.exists()) {
                config.load(file);

                List<String> pList = new ArrayList<String>();
                pList = (List) config.getList("playersListed");
                if (!(pList == null)){
                    for (String name : pList) {
                        if (!this.playerList.contains(name))
                            this.playerList.add(name);
                    }
                    for(String name : this.playerList) {
                        if(gamesPlayed.get(name) == null)
                        {
                            gamesPlayed.put(name, config.getInt("player." + name + ".GamesPlayed"));
                        }
                        else if(gamesPlayed.get(name) < config.getInt("player." + name + ".GamesPlayed"))
                        {
                            gamesPlayed.put(name, config.getInt("player." + name + ".GamesPlayed"));
                        }

                        if(eliminations.get(name) == null)
                        {
                            eliminations.put(name, config.getInt("player." + name + ".Eliminations"));
                        }
                        else if(eliminations.get(name) < config.getInt("player." + name + ".Eliminations"))
                        {
                            eliminations.put(name, config.getInt("player." + name + ".Eliminations"));
                        }

                        if(placings.get(name) == null)
                        {
                            placings.put(name, config.getInt("player." + name + ".Placings"));
                        }
                        else if(placings.get(name) < config.getInt("player." + name + ".Placings"))
                        {
                            placings.put(name, config.getInt("player." + name + ".Placings"));
                        }

                        if(victories.get(name) == null)
                        {
                            victories.put(name, config.getInt("player." + name + ".Victories"));
                        }
                        else if(victories.get(name) < config.getInt("player." + name + ".Victories"))
                        {
                            victories.put(name, config.getInt("player." + name + ".Victories"));
                        }

                        if(points.get(name) == null)
                        {
                            points.put(name, config.getInt("player." + name + ".Points"));
                        }
                        else if(points.get(name) < config.getInt("player." + name + ".Points"))
                        {
                            points.put(name, config.getInt("player." + name + ".Points"));
                        }

                        if(roundsSurvived.get(name) == null)
                        {
                            roundsSurvived.put(name, config.getInt("player." + name + ".RoundsSurvived"));
                        }
                        else if(roundsSurvived.get(name) < config.getInt("player." + name + ".RoundsSurvived"))
                        {
                            roundsSurvived.put(name, config.getInt("player." + name + ".RoundsSurvived"));
                        }
                    }
                }
                else
                {
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
    }

    public ArrayList getSortedPlayersByPoints()
    {
        this.loadAllPlayers();
        List<String> unsortedPlayers = this.playerList;
        ArrayList<String> sortedPlayers = new ArrayList<String>();
        if(unsortedPlayers.size() > 0) {
            if(unsortedPlayers.size() == 1)
            {
                sortedPlayers.add(unsortedPlayers.get(0));
                unsortedPlayers.remove(sortedPlayers.get(0));
                return sortedPlayers;
            }
            for (String name : unsortedPlayers) {
                if (sortedPlayers.isEmpty())
                    sortedPlayers.add(0, name);
                if (this.points.get(sortedPlayers.get(0)) < this.points.get(name))
                    sortedPlayers.set(0, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(0));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(1, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(1).equalsIgnoreCase(" "))
                    sortedPlayers.set(1, name);
                if (this.points.get(sortedPlayers.get(1)) < this.points.get(name))
                    sortedPlayers.set(1, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(1));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(2, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(2).equalsIgnoreCase(" "))
                    sortedPlayers.set(2, name);
                if (this.points.get(sortedPlayers.get(2)) < this.points.get(name))
                    sortedPlayers.set(2, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(2));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(3, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(3).equalsIgnoreCase(" "))
                    sortedPlayers.set(3, name);
                if (this.points.get(sortedPlayers.get(3)) < this.points.get(name))
                    sortedPlayers.set(3, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(3));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(4, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(4).equalsIgnoreCase(" "))
                    sortedPlayers.set(4, name);
                if (this.points.get(sortedPlayers.get(4)) < this.points.get(name))
                    sortedPlayers.set(4, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(4));
        }

        return sortedPlayers;
    }



    public ArrayList getSortedPlayersByVictories()
    {
        this.loadAllPlayers();
        List<String> unsortedPlayers = this.playerList;
        ArrayList<String> sortedPlayers = new ArrayList<String>();
        if(unsortedPlayers.size() > 0) {
            if(unsortedPlayers.size() == 1)
            {
                sortedPlayers.add(unsortedPlayers.get(0));
                unsortedPlayers.remove(sortedPlayers.get(0));
                return sortedPlayers;
            }
            for (String name : unsortedPlayers) {
                if (sortedPlayers.isEmpty())
                    sortedPlayers.add(0, name);
                if (this.victories.get(sortedPlayers.get(0)) < this.victories.get(name))
                    sortedPlayers.set(0, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(0));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(1, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(1).equalsIgnoreCase(" "))
                    sortedPlayers.set(1, name);
                if (this.victories.get(sortedPlayers.get(1)) < this.victories.get(name))
                    sortedPlayers.set(1, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(1));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(2, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(2).equalsIgnoreCase(" "))
                    sortedPlayers.set(2, name);
                if (this.victories.get(sortedPlayers.get(2)) < this.victories.get(name))
                    sortedPlayers.set(2, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(2));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(3, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(3).equalsIgnoreCase(" "))
                    sortedPlayers.set(3, name);
                if (this.victories.get(sortedPlayers.get(3)) < this.victories.get(name))
                    sortedPlayers.set(3, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(3));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(4, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(4).equalsIgnoreCase(" "))
                    sortedPlayers.set(4, name);
                if (this.victories.get(sortedPlayers.get(4)) < this.victories.get(name))
                    sortedPlayers.set(4, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(4));
        }

        return sortedPlayers;
    }

    public ArrayList getSortedPlayersByRoundsSurvived()
    {
        this.loadAllPlayers();
        List<String> unsortedPlayers = this.playerList;
        ArrayList<String> sortedPlayers = new ArrayList<String>();
        if(unsortedPlayers.size() > 0) {
            if(unsortedPlayers.size() == 1)
            {
                sortedPlayers.add(unsortedPlayers.get(0));
                unsortedPlayers.remove(sortedPlayers.get(0));
                return sortedPlayers;
            }
            for (String name : unsortedPlayers) {
                if (sortedPlayers.isEmpty())
                    sortedPlayers.add(0, name);
                if (this.roundsSurvived.get(sortedPlayers.get(0)) < this.roundsSurvived.get(name))
                    sortedPlayers.set(0, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(0));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(1, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(1).equalsIgnoreCase(" "))
                    sortedPlayers.set(1, name);
                if (this.roundsSurvived.get(sortedPlayers.get(1)) < this.roundsSurvived.get(name))
                    sortedPlayers.set(1, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(1));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(2, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(2).equalsIgnoreCase(" "))
                    sortedPlayers.set(2, name);
                if (this.roundsSurvived.get(sortedPlayers.get(2)) < this.roundsSurvived.get(name))
                    sortedPlayers.set(2, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(2));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(3, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(3).equalsIgnoreCase(" "))
                    sortedPlayers.set(3, name);
                if (this.roundsSurvived.get(sortedPlayers.get(3)) < this.roundsSurvived.get(name))
                    sortedPlayers.set(3, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(3));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(4, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(4).equalsIgnoreCase(" "))
                    sortedPlayers.set(4, name);
                if (this.roundsSurvived.get(sortedPlayers.get(4)) < this.roundsSurvived.get(name))
                    sortedPlayers.set(4, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(4));
        }

        return sortedPlayers;
    }

    public ArrayList getSortedPlayersByGamesPlayed()
    {
        this.loadAllPlayers();
        List<String> unsortedPlayers = this.playerList;
        ArrayList<String> sortedPlayers = new ArrayList<String>();
        if(unsortedPlayers.size() > 0) {
            if(unsortedPlayers.size() == 1)
            {
                sortedPlayers.add(unsortedPlayers.get(0));
                unsortedPlayers.remove(sortedPlayers.get(0));
                return sortedPlayers;
            }
            for (String name : unsortedPlayers) {
                if (sortedPlayers.isEmpty())
                    sortedPlayers.add(0, name);
                if (this.gamesPlayed.get(sortedPlayers.get(0)) < this.gamesPlayed.get(name))
                    sortedPlayers.set(0, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(0));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(1, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(1).equalsIgnoreCase(" "))
                    sortedPlayers.set(1, name);
                if (this.gamesPlayed.get(sortedPlayers.get(1)) < this.gamesPlayed.get(name))
                    sortedPlayers.set(1, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(1));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(2, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(2).equalsIgnoreCase(" "))
                    sortedPlayers.set(2, name);
                if (this.gamesPlayed.get(sortedPlayers.get(2)) < this.gamesPlayed.get(name))
                    sortedPlayers.set(2, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(2));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(3, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(3).equalsIgnoreCase(" "))
                    sortedPlayers.set(3, name);
                if (this.gamesPlayed.get(sortedPlayers.get(3)) < this.gamesPlayed.get(name))
                    sortedPlayers.set(3, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(3));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(4, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(4).equalsIgnoreCase(" "))
                    sortedPlayers.set(4, name);
                if (this.gamesPlayed.get(sortedPlayers.get(4)) < this.gamesPlayed.get(name))
                    sortedPlayers.set(4, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(4));
        }

        return sortedPlayers;
    }

    public ArrayList getSortedPlayersByPlacings()
    {
        this.loadAllPlayers();
        List<String> unsortedPlayers = this.playerList;
        ArrayList<String> sortedPlayers = new ArrayList<String>();
        if(unsortedPlayers.size() > 0) {
            if(unsortedPlayers.size() == 1)
            {
                sortedPlayers.add(unsortedPlayers.get(0));
                unsortedPlayers.remove(sortedPlayers.get(0));
                return sortedPlayers;
            }
            for (String name : unsortedPlayers) {
                if (sortedPlayers.isEmpty())
                    sortedPlayers.add(0, name);
                if (this.placings.get(sortedPlayers.get(0)) < this.placings.get(name))
                    sortedPlayers.set(0, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(0));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(1, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(1).equalsIgnoreCase(" "))
                    sortedPlayers.set(1, name);
                if (this.placings.get(sortedPlayers.get(1)) < this.placings.get(name))
                    sortedPlayers.set(1, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(1));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(2, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(2).equalsIgnoreCase(" "))
                    sortedPlayers.set(2, name);
                if (this.placings.get(sortedPlayers.get(2)) < this.placings.get(name))
                    sortedPlayers.set(2, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(2));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(3, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(3).equalsIgnoreCase(" "))
                    sortedPlayers.set(3, name);
                if (this.placings.get(sortedPlayers.get(3)) < this.placings.get(name))
                    sortedPlayers.set(3, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(3));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(4, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(4).equalsIgnoreCase(" "))
                    sortedPlayers.set(4, name);
                if (this.placings.get(sortedPlayers.get(4)) < this.placings.get(name))
                    sortedPlayers.set(4, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(4));
        }

        return sortedPlayers;
    }

    public ArrayList getSortedPlayersByEliminations()
    {
        this.loadAllPlayers();
        List<String> unsortedPlayers = this.playerList;
        ArrayList<String> sortedPlayers = new ArrayList<String>();
        if(unsortedPlayers.size() > 0) {
            if(unsortedPlayers.size() == 1)
            {
                sortedPlayers.add(unsortedPlayers.get(0));
                unsortedPlayers.remove(sortedPlayers.get(0));
                return sortedPlayers;
            }
            for (String name : unsortedPlayers) {
                if (sortedPlayers.isEmpty())
                    sortedPlayers.add(0, name);
                if (this.eliminations.get(sortedPlayers.get(0)) < this.eliminations.get(name))
                    sortedPlayers.set(0, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(0));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(1, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(1).equalsIgnoreCase(" "))
                    sortedPlayers.set(1, name);
                if (this.eliminations.get(sortedPlayers.get(1)) < this.eliminations.get(name))
                    sortedPlayers.set(1, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(1));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(2, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(2).equalsIgnoreCase(" "))
                    sortedPlayers.set(2, name);
                if (this.eliminations.get(sortedPlayers.get(2)) < this.eliminations.get(name))
                    sortedPlayers.set(2, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(2));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(3, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(3).equalsIgnoreCase(" "))
                    sortedPlayers.set(3, name);
                if (this.eliminations.get(sortedPlayers.get(3)) < this.eliminations.get(name))
                    sortedPlayers.set(3, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(3));
        }

        if(unsortedPlayers.size() > 0) {
            sortedPlayers.add(4, " ");
            for (String name : unsortedPlayers) {
                if (sortedPlayers.get(4).equalsIgnoreCase(" "))
                    sortedPlayers.set(4, name);
                if (this.eliminations.get(sortedPlayers.get(4)) < this.eliminations.get(name))
                    sortedPlayers.set(4, name);
            }
            unsortedPlayers.remove(sortedPlayers.get(4));
        }
        return sortedPlayers;
    }

    public void setGamesPlayed(Player p, int v)
    {
        gamesPlayed.put(p.getName(), v);
    }
    public void setEliminations(Player p, int v)
    {
        eliminations.put(p.getName(), v);
    }
    public void setPlacings(Player p, int v)
    {
        placings.put(p.getName(), v);
    }
    public void setVictories(Player p, int v)
    {
        victories.put(p.getName(), v);
    }
    public void setPoints(Player p, int v)
    {
        points.put(p.getName(), v);
    }
    public void setRoundsSurvived(Player p, int v)
    {
        roundsSurvived.put(p.getName(), v);
    }

    public Integer getGamesPlayed(Player p)
    {
        return gamesPlayed.get(p.getName());
    }
    public Integer getEliminations(Player p)
    {
        return eliminations.get(p.getName());
    }
    public Integer getPlacings(Player p)
    {
        return placings.get(p.getName());
    }
    public Integer getVictories(Player p)
    {
        return victories.get(p.getName());
    }
    public Integer getPoints(Player p)
    {
        return points.get(p.getName());
    }
    public Integer getRoundsSurvived(Player p)
    {
        return roundsSurvived.get(p.getName());
    }

    public Integer getGamesPlayed(String name)
    {
        return gamesPlayed.get(name);
    }
    public Integer getEliminations(String name)
    {
        return eliminations.get(name);
    }
    public Integer getPlacings(String name)
    {
        return placings.get(name);
    }
    public Integer getVictories(String name)
    {
        return victories.get(name);
    }
    public Integer getPoints(String name)
    {
        return points.get(name);
    }
    public Integer getRoundsSurvived(String name)
    {
        return roundsSurvived.get(name);
    }

    public List getPlayers()
    {
        return this.playerList;
    }
}
