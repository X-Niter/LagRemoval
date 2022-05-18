package com.github.xniter.commands.events;

import com.github.xniter.LagRemoval;
import net.minecraft.ChatFormatting;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

public class StrictLagRemoval {

    public long l1;
    public int seconds;
    public boolean w60;
    public boolean w30;
    public boolean w5;
    public boolean w3;
    public boolean w2;
    public boolean w1;

    @SubscribeEvent
    public void StrictLagRemovalEvent(TickEvent.ServerTickEvent event) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (this.l1 != System.currentTimeMillis() / 1000) {
            this.l1 = System.currentTimeMillis() / 1000;
            this.seconds++;
        }
        if (LagRemoval.CONFIG.enable_strict_lagremoval()) {
            if (this.seconds == LagRemoval.CONFIG.strict_timer() * 60 - 30 && !this.w30) {
                LagRemoval.sendToAll(server, "" + ChatFormatting.BLUE + "[Lag Removal] " + ChatFormatting.GREEN + "Entities will be removed in 30 seconds");
                this.w30 = true;
            }
            if (this.seconds == LagRemoval.CONFIG.strict_timer() * 60 - 29) {
                this.w30 = false;
            }


            if (this.seconds == LagRemoval.CONFIG.strict_timer() * 60 - 5 && !this.w5) {
                LagRemoval.sendToAll(server, "" + ChatFormatting.BLUE + "[Lag Removal] " + ChatFormatting.GREEN + "Entities will be removed in" + ChatFormatting.RED + " 5 " + ChatFormatting.GREEN + "seconds");
                this.w5 = true;
            }
            if (this.seconds == LagRemoval.CONFIG.strict_timer() * 60 - 4) {
                this.w5 = false;
            }

            if (this.seconds == LagRemoval.CONFIG.strict_timer() * 60 - 3 && !this.w3) {
                LagRemoval.sendToAll(server, "" + ChatFormatting.BLUE + "[Lag Removal] " + ChatFormatting.GREEN + "Entities will be removed in" + ChatFormatting.RED + " 3 " + ChatFormatting.GREEN + "seconds");
                this.w3 = true;
            }
            if (this.seconds == LagRemoval.CONFIG.strict_timer() * 60 - 2.9) {
                this.w3 = false;
            }


            if (this.seconds == LagRemoval.CONFIG.strict_timer() * 60 - 2 && !this.w2) {
                LagRemoval.sendToAll(server, "" + ChatFormatting.BLUE + "[Lag Removal] " + ChatFormatting.GREEN + "Entities will be removed in" + ChatFormatting.RED + " 2 " + ChatFormatting.GREEN + "seconds");
                this.w2 = true;
            }
            if (this.seconds == LagRemoval.CONFIG.strict_timer() * 60 - 1.9) {
                this.w2 = false;
            }

            if (this.seconds == LagRemoval.CONFIG.strict_timer() * 60 - 1 && !this.w1) {
                LagRemoval.sendToAll(server, "" + ChatFormatting.BLUE + "[Lag Removal] " + ChatFormatting.GREEN + "Entities will be removed in" + ChatFormatting.RED + " 1 " + ChatFormatting.GREEN + "second");
                this.w1 = true;
            }
            if (this.seconds == LagRemoval.CONFIG.strict_timer() * 60 - 0.9) {
                this.w1 = false;
            }

            if (this.seconds == LagRemoval.CONFIG.strict_timer() * 60 && !this.w60) {
                server.getCommands().performCommand(server.createCommandSourceStack(),"lr clear all");
                this.w60 = true;
                this.seconds = 0;
            }

        }
    }
}
