package com.skaggsm.dynmapfabric;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;
import org.dynmap.DynmapChunk;
import org.dynmap.DynmapWorld;
import org.dynmap.common.DynmapListenerManager;
import org.dynmap.common.DynmapPlayer;
import org.dynmap.common.DynmapServerInterface;
import org.dynmap.utils.MapChunkCache;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by Mitchell Skaggs on 12/30/2019.
 */

public class FabricServer extends DynmapServerInterface {
    private final MinecraftServer minecraftServer;

    public FabricServer(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
    }

    @Override
    public void scheduleServerTask(Runnable run, long delay) {
        System.out.printf("Task requested with delay %d%n", delay);
    }

    @Override
    public <T> Future<T> callSyncMethod(Callable<T> task) {
        System.out.println("Sync method requested");
        return null;
    }

    @Override
    public DynmapPlayer[] getOnlinePlayers() {
        return minecraftServer.getPlayerManager().getPlayerList().stream().map(FabricPlayer::new).toArray(FabricPlayer[]::new);
    }

    @Override
    public void reload() {

    }

    @Override
    public DynmapPlayer getPlayer(String name) {
        return null;
    }

    @Override
    public DynmapPlayer getOfflinePlayer(String name) {
        return null;
    }

    @Override
    public Set<String> getIPBans() {
        return new HashSet<>(Arrays.asList(minecraftServer.getPlayerManager().getIpBanList().getNames()));
    }

    @Override
    public String getServerName() {
        String sn = minecraftServer.getServerMotd();
        if (sn == null) return "Unknown Server";
        return sn;
    }

    @Override
    public boolean isPlayerBanned(String pid) {
        return false;
    }

    @Override
    public String stripChatColor(String s) {
        return null;
    }

    @Override
    public boolean requestEventNotification(DynmapListenerManager.EventType type) {
        return false;
    }

    @Override
    public boolean sendWebChatEvent(String source, String name, String msg) {
        return false;
    }

    @Override
    public void broadcastMessage(String msg) {

    }

    @Override
    public String[] getBiomeIDs() {
        return new String[0];
    }

    @Override
    public double getCacheHitRate() {
        return 0;
    }

    @Override
    public void resetCacheStats() {

    }

    @Override
    public DynmapWorld getWorldByName(String wname) {
        return new FabricWorld(minecraftServer.getWorld(DimensionType.byId(Identifier.tryParse(wname))));
    }

    @Override
    public Set<String> checkPlayerPermissions(String player, Set<String> perms) {
        return null;
    }

    @Override
    public boolean checkPlayerPermission(String player, String perm) {
        return false;
    }

    @Override
    public MapChunkCache createMapChunkCache(DynmapWorld w, List<DynmapChunk> chunks, boolean blockdata, boolean highesty, boolean biome, boolean rawbiome) {
        return null;
    }

    @Override
    public int getMaxPlayers() {
        return minecraftServer.getMaxPlayerCount();
    }

    @Override
    public int getCurrentPlayers() {
        return minecraftServer.getCurrentPlayerCount();
    }

    @Override
    public int getBlockIDAt(String wname, int x, int y, int z) {
        return 0;
    }

    @Override
    public int isSignAt(String wname, int x, int y, int z) {
        return 0;
    }

    @Override
    public double getServerTPS() {
        return 1 / minecraftServer.getTickTime();
    }

    @Override
    public String getServerIP() {
        return minecraftServer.getServerIp();
    }
}
