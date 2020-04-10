package com.skaggsm.dynmapfabric;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.dynmap.DynmapChunk;
import org.dynmap.DynmapLocation;
import org.dynmap.DynmapWorld;
import org.dynmap.utils.MapChunkCache;

import java.util.List;

/**
 * Created by Mitchell Skaggs on 12/31/2019.
 */

class FabricWorld extends DynmapWorld {
    private final ServerWorld world;

    public FabricWorld(ServerWorld world) {
        super(world.dimension.getType().getSuffix(), world.getEffectiveHeight(), world.getSeaLevel());
        this.world = world;
    }

    @Override
    public boolean isNether() {
        return world.dimension.isNether();
    }

    @Override
    public DynmapLocation getSpawnLocation() {
        BlockPos spawn = world.getSpawnPos();
        return new DynmapLocation(this.getRawName(), spawn.getX(), spawn.getY(), spawn.getZ());
    }

    @Override
    public long getTime() {
        return world.getTime();
    }

    @Override
    public boolean hasStorm() {
        return world.isRaining();
    }

    @Override
    public boolean isThundering() {
        return world.isThundering();
    }

    @Override
    public boolean isLoaded() {
        return true;
    }

    @Override
    public void setWorldUnloaded() {
    }

    @Override
    public int getLightLevel(int x, int y, int z) {
        try (BlockPos.PooledMutable pos = BlockPos.PooledMutable.get(x, y, z)) {
            return world.getLightLevel(LightType.BLOCK, pos);
        }
    }

    @Override
    public int getHighestBlockYAt(int x, int z) {
        return world.getTopY(Heightmap.Type.WORLD_SURFACE, x, z);
    }

    @Override
    public boolean canGetSkyLightLevel() {
        return true;
    }

    @Override
    public int getSkyLightLevel(int x, int y, int z) {
        try (BlockPos.PooledMutable pos = BlockPos.PooledMutable.get(x, y, z)) {
            return world.getLightLevel(LightType.SKY, pos);
        }
    }

    @Override
    public String getEnvironment() {
        return world.getDimension().getType().getSuffix();
    }

    @Override
    public MapChunkCache getChunkCache(List<DynmapChunk> chunks) {
        return new FabricMapChunkCache(chunks);
    }

    public World getWorld() {
        return world;
    }
}
