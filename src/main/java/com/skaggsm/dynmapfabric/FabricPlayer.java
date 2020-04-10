package com.skaggsm.dynmapfabric;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import org.dynmap.DynmapLocation;
import org.dynmap.common.DynmapPlayer;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Created by Mitchell Skaggs on 12/31/2019.
 */

public class FabricPlayer implements DynmapPlayer {
    private final ServerPlayerEntity player;

    public FabricPlayer(ServerPlayerEntity player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return player.getName().getString();
    }

    @Override
    public String getDisplayName() {
        return player.getDisplayName().getString();
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public DynmapLocation getLocation() {
        return new DynmapLocation(this.getWorld(), player.getX(), player.getY(), player.getZ());
    }

    @Override
    public String getWorld() {
        return player.world.getDimension().getType().getSuffix();
    }

    @Override
    public InetSocketAddress getAddress() {
        SocketAddress address = player.networkHandler.getConnection().getAddress();
        if (address instanceof InetSocketAddress)
            return (InetSocketAddress) address;
        else
            return null;
    }

    @Override
    public boolean isSneaking() {
        return player.isSneaky();
    }

    @Override
    public double getHealth() {
        return player.getHealth();
    }

    @Override
    public int getArmorPoints() {
        return player.getArmor();
    }

    @Override
    public DynmapLocation getBedSpawnLocation() {
        BlockPos pos = player.getSpawnPosition();
        return new DynmapLocation(DimensionType.OVERWORLD.getSuffix(), pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public long getLastLoginTime() {
        return 0;
    }

    @Override
    public long getFirstLoginTime() {
        return 0;
    }

    @Override
    public boolean isInvisible() {
        return player.isInvisible();
    }

    @Override
    public int getSortWeight() {
        return 0;
    }

    @Override
    public void setSortWeight(int wt) {
    }

    @Override
    public boolean hasPrivilege(String privid) {
        return isOp();
    }

    @Override
    public void sendMessage(String msg) {

    }

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public boolean isOp() {
        return player.allowsPermissionLevel(1);
    }

    @Override
    public boolean hasPermissionNode(String node) {
        return isOp();
    }
}
