package com.skaggsm.dynmapfabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.event.server.ServerStopCallback;
import net.fabricmc.loader.ModContainer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.registry.Registry;
import org.dynmap.DynmapCore;
import org.dynmap.common.BiomeMap;
import org.dynmap.renderer.DynmapBlockState;

import java.io.File;

public class DynmapFabric implements ModInitializer {
    public static final String MODID = "dynmap-fabric";

    private DynmapCore core;

    @Override
    public void onInitialize() {
        core = new DynmapCore();

        ServerStartCallback.EVENT.register(this::startServer);
        ServerStopCallback.EVENT.register(this::stopServer);
    }

    private void startServer(MinecraftServer minecraftServer) {
        initBlocks();

        core.setServer(new FabricServer(minecraftServer));
        //noinspection OptionalGetWithoutIsPresent
        ModContainer modContainer = (ModContainer) FabricLoader.getInstance().getModContainer(MODID).get();
        core.setPluginJarFile(new File(modContainer.getOriginUrl().getPath()));
        core.setDataFolder(new File("dynmap"));
        //noinspection ResultOfMethodCallIgnored
        core.getDataFolder().mkdirs();
        core.setMinecraftVersion("1.15.1");
        core.setPluginVersion("0.1.0", "Fabric");

        BiomeMap.loadWellKnownByVersion(core.getDynmapPluginPlatformVersion());

        boolean success = core.enableCore();

        if (!success)
            throw new RuntimeException("Dynmap failed to initialize");
    }

    private void initBlocks() {
        int id = 0;
        DynmapBlockState last = null;
        for (BlockState state : Block.STATE_IDS) {
            Block block = state.getBlock();

            int rawId = Registry.BLOCK.getRawId(block);

            if (last != null && last.legacyBlockID == rawId) {
                new DynmapBlockState(last, rawId, Registry.BLOCK.getId(block).toString(), state.toString(), state.getMaterial().toString(), id++);
            } else {
                last = new DynmapBlockState(null, rawId, Registry.BLOCK.getId(block).toString(), state.toString(), state.getMaterial().toString(), id++);
            }

        }
    }

    private void stopServer(MinecraftServer minecraftServer) {
    }
}
