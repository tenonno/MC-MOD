
package com.tenonno.mod;


import net.minecraft.init.Blocks;
import net.minecraft.init.Items;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = TTT.MODID, version = TTT.VERSION, useMetadata = true)
public class TTT
{
    public static final String MODID = "mod";
    public static final String MOD_ID = "mod";
    public static final String VERSION = "1.0";

    public static Block sampleBlock;

    /** 前に読み込まれるべき前提MODをバージョン込みで指定 */
    public static final String MOD_DEPENDENCIES = "required-after:Forge@[1.9-12.16.0.1853,)";
    /** 起動出来るMinecraft本体のバージョン。記法はMavenのVersion Range Specificationを検索すること。 */
    public static final String MOD_ACCEPTED_MC_VERSIONS = "[1.9]";

    public static Block lunarFragmentBlock;



    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

        sampleBlock = new ReplaceInk();


        // LunarFragment
        ResourceLocation lunarFragmentResource = new ResourceLocation(MOD_ID, "lunarFragment");
        lunarFragmentBlock = new LunarFragment();
        ItemBlock lunarFragmentItemBlock = new LunarFragmentItem(lunarFragmentBlock);

        // lunarFragmentItemBlock.setUnlocalizedName("lunarFragment");

        GameRegistry.register(lunarFragmentBlock, lunarFragmentResource);
        GameRegistry.register(lunarFragmentItemBlock, lunarFragmentResource);



        // ReplaceInk
        ResourceLocation registryName = new ResourceLocation(MOD_ID, "replaceInk");


        ItemBlock itemBlock = new ReplaceInkItem(sampleBlock);

        GameRegistry.register(sampleBlock,registryName);
        GameRegistry.register(itemBlock, registryName);

        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(sampleBlock)),
                               "DSD",
                               "DBD",
                               "DDD",
                               'D', Items.DIAMOND,
                               'B', Items.BUCKET,
                               'S', Items.SLIME_BALL
                               );

        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(lunarFragmentBlock)),
                               "GGG",
                               "GGG",
                               "GGG",
                               'G', Blocks.GLOWSTONE
                               );




        //ブロックの登録。登録文字列はMOD内で被らなければ何でも良い。
        //ResourceLocation registryName = new ResourceLocation(MODID, "sampleblock");
        //ItemBlock sampleItemBlock = new ItemBlock(sampleBlock);

        // GameRegistry.register(sampleBlock, registryName);
        // GameRegistry.register(sampleItemBlock, registryName);
        //クライアントサイドでの処理。Proxyシステムを使ってもよい。
        if (event.getSide().isClient()) {




            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(sampleBlock), 0, new ModelResourceLocation(new ResourceLocation(TTT.MOD_ID, "replaceInk"), "inventory"));

            ModelLoader.setCustomModelResourceLocation(lunarFragmentItemBlock, 1, new ModelResourceLocation(new ResourceLocation(TTT.MOD_ID, "lunarFragment"), "inventory"));

        }


    }




    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        if (event.getSide().isClient()) {

            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();


            renderItem.getItemModelMesher().register(
                Item.getItemFromBlock(lunarFragmentBlock),
                0,
                new ModelResourceLocation(this.MODID + ":lunarFragment", "inventory"));


        }
    }


}
