package com.skaggsm.dynmapfabric;

import org.dynmap.DynmapChunk;
import org.dynmap.DynmapWorld;
import org.dynmap.utils.MapChunkCache;
import org.dynmap.utils.MapIterator;
import org.dynmap.utils.VisibilityLimit;

import java.util.List;

/**
 * Created by Mitchell Skaggs on 12/31/2019.
 */

public class FabricMapChunkCache extends MapChunkCache {
    public FabricMapChunkCache(List<DynmapChunk> chunks) {
    }

    @Override
    public boolean setChunkDataTypes(boolean blockdata, boolean biome, boolean highestblocky, boolean rawbiome) {
        return false;
    }

    @Override
    public int loadChunks(int maxToLoad) {
        return 0;
    }

    @Override
    public boolean isDoneLoading() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void unloadChunks() {

    }

    @Override
    public boolean isEmptySection(int sx, int sy, int sz) {
        return false;
    }

    @Override
    public MapIterator getIterator(int x, int y, int z) {
        return null;
    }

    @Override
    public void setHiddenFillStyle(HiddenChunkStyle style) {

    }

    @Override
    public void setVisibleRange(VisibilityLimit limit) {

    }

    @Override
    public void setHiddenRange(VisibilityLimit limit) {

    }

    @Override
    public DynmapWorld getWorld() {
        return null;
    }
}
