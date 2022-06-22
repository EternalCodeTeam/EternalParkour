package net.eternalcode.eternalparkour.util;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public final class LocationUtils {


    public static String fromLocationForPlayer(Location location){
        String worldName = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float pitch = location.getPitch();
        float yaw = location.getYaw();

        return worldName + "," + x + "," + y + "," + z + "," + yaw + "," + pitch;
    }


    public static String fromLocationForBlock(Location location){
        String worldName = location.getWorld().getName();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        return worldName + "," + x + "," + y + "," + z;
    }

    public static Location toPlayerLocation(String ctx){
        String[] values = ctx.split(",");

        World world = Bukkit.getWorld(values[0]);
        double x = Double.parseDouble(values[1]);
        double y = Double.parseDouble(values[2]);
        double z = Double.parseDouble(values[3]);
        float yaw = Float.parseFloat(values[4]);
        float pitch = Float.parseFloat(values[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }

    public static Location toBlockLocation(String ctx){
        String[] values = ctx.split(",");

        World world = Bukkit.getWorld(values[0]);
        int x = Integer.parseInt(values[1]);
        int y = Integer.parseInt(values[2]);
        int z = Integer.parseInt(values[3]);

        return new Location(world, x, y, z);
    }
}
