package com.strixmc.powerups.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.StringJoiner;

public class LocationUtils {

    public static Location[] getFaces(Location start) {
        Location[] faces = new Location[4];
        faces[0] = new Location(start.getWorld(), start.getX() + 1, start.getY(), start.getZ());
        faces[1] = new Location(start.getWorld(), start.getX() - 1, start.getY(), start.getZ());
        faces[2] = new Location(start.getWorld(), start.getX(), start.getY() + 1, start.getZ());
        faces[3] = new Location(start.getWorld(), start.getX(), start.getY() - 1, start.getZ());
        return faces;
    }

    public static String serialize(Location location) {
        if (location == null) return "null";
        return new StringJoiner(":")
                .add(location.getWorld().getName())
                .add(String.valueOf(location.getX()))
                .add(String.valueOf(location.getY()))
                .add(String.valueOf(location.getZ()))
                .add(String.valueOf(location.getYaw()))
                .add(String.valueOf(location.getPitch()))
                .toString();
    }

    public static Location deserialize(String source) {
        if (source == null) return null;
        String[] split = source.split(":");

        World world = Bukkit.getServer().getWorld(split[0]);
        if (world == null)
            throw new IllegalArgumentException("Location deserialization went wrong, world doesn't exist.");

        return new Location(world, Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
    }

    public static boolean isSameBlockLocation(Location l1, Location l2) {
        return ((l1.getBlockX() == l2.getBlockX()) && (l1.getBlockY() == l2.getBlockY()) && (l1.getBlockZ() == l2.getBlockZ()));
    }

    public static Location getGroundLocation(Location location) {
        World world = location.getWorld();

        Block highest = world != null ? world.getHighestBlockAt(location).getRelative(BlockFace.UP) : null;
        Block block = highest != null && highest.getY() < location.getY() ? highest : location.getBlock();

        while (!block.getType().isSolid() && block.getLocation().getY() >= 0 && block.getLocation().getY() <= 255) {
            block = block.getRelative(BlockFace.DOWN);
        }

        return new Location(location.getWorld(), location.getX(), block.getY() >= 0 ? block.getY() + 1 : location.getY(), location.getZ());
    }
}
