

package com.tenonno.mod;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class LunarFragment extends Block {
    public LunarFragment() {
        super(Material.ROCK);

        setCreativeTab(CreativeTabs.MISC);/*クリエイティブタブの選択*/


        setUnlocalizedName("lunarFragment");/*システム名の設定*/


        this.disableStats();



        this.disableStats();
        this.setLightLevel(20.0F);/*明るさ 1.0F = 15*/


        /*this.setDefaultState(getBlockState().getBaseState());*//*初期BlockStateの設定*/
    }




}
