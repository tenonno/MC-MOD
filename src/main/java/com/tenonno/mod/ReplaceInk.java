

package com.tenonno.mod;

import java.util.Random;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;

import net.minecraftforge.event.entity.player.*;

import net.minecraft.init.Blocks;

import net.minecraft.item.ItemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.world.IBlockAccess;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.List;
import java.util.ArrayList;




public class ReplaceInk extends Block {
    public ReplaceInk() {
        super(Material.ROCK);

        setCreativeTab(CreativeTabs.MISC);/*クリエイティブタブの選択*/


        setUnlocalizedName("replaceInk");/*システム名の設定*/


        this.disableStats();


        /*以下のものは消しても結構です*/
        //setHardness(1.5F);/*硬さ*/
        //setResistance(1.0F);/*爆破耐性*/
        /*setBlockUnbreakable();*//*ブロックを破壊不可に設定*/
        /*setTickRandomly(true);*//*ブロックのtick処理をランダムに。デフォルトfalse*/
        /*disableStats();*//*ブロックの統計情報を保存しない*/
        //setLightOpacity(1);/*ブロックの透過係数。デフォルト0(不透過)*/

        this.disableStats();
        this.setLightOpacity(1);
        this.setLightLevel(3.0F);/*明るさ 1.0F = 15*/


        /*this.setDefaultState(getBlockState().getBaseState());*//*初期BlockStateの設定*/
    }

    /*
       @Override
       public void onBlockClicked(World world, BlockPos pos, EntityPlayer player)
       {
        System.out.println("onBlockClicked");
        player.addChatComponentMessage(new TextComponentString("Click！: "));

       }
     */



    protected BlockPos storageBoxPos;


    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        return new ArrayList<ItemStack>();
    }


    /*
       @Override
       public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor)
       {


        System.out.println(
            neighbor.getX() + ", " +
            neighbor.getY() + ", " +
            neighbor.getZ());

        if (this.storageBoxPos == null) return;

        // world.setBlockState(neighbor, Blocks.OBSIDIAN.getBlockState().getBaseState(), 3);


       }


     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {

        if (heldItem == null) {
            return true;
        }

        Item item = heldItem.getItem();

        if (item == null) return true;

        // player.addChatComponentMessage(new TextComponentString(item.getUnlocalizedName()));
        // player.addChatComponentMessage(new TextComponentString(item.getRegistryName()));

        // System.out.println(item.getUnlocalizedName());


        if (item.getUnlocalizedName().equals("tile.lunarFragment")) {
            return true;
        }

        // 置かれるブロックを監視する
        if (item.getUnlocalizedName().equals("item.storagebox")) {

            this.storageBoxPos = pos.add(side.getDirectionVec());

            // player.addChatComponentMessage(new TextComponentString("監視を開始します"));

            /*

               IBlockState state2 = world.getBlockState(pos1);
               Block b = state2.getBlock();

               if (b == null) {

                player.addChatComponentMessage(new TextComponentString("ブロックはありません"));

               }
               else {
                player.addChatComponentMessage(new TextComponentString("ブロックがあります"));

               }

             */


            return false;
        }


        Block _block = Block.getBlockFromItem(item);

        if (_block == null) {

            // player.addChatComponentMessage(new TextComponentString("ブロックではありません"));

            return true;
        }


        // world.setBlockState(pos, blockState, 3);
        // world.setBlockState(pos.add(side.getDirectionVec()), Blocks.OBSIDIAN.getBlockState().getBaseState(), 3);



        if (_block == this) {


            return true;
        }


        this.AA(world, pos, _block.getBlockState().getBaseState());

        // player.addChatComponentMessage(new TextComponentString(_block.getLocalizedName()));

        return true;
    }


    // 他のブロックに置換する
    public void AA(World world, BlockPos pos, IBlockState blockState) {

        world.setBlockState(pos, blockState, 3);

        List<BlockPos> aa2 = this.getAA(world, pos);


        for (BlockPos pos1: aa2) {


            IBlockState state2 = world.getBlockState(pos1);

            if (!(state2.getBlock() == this)) continue;

            this.AA(world, pos1, blockState);
        }

    }


    // 周囲の同じブロックを取得する
    public List<BlockPos> getAA(World world, BlockPos pos) {


        List<BlockPos> result = new ArrayList<BlockPos>();


        BlockPos _pos[] = {
            // 周り
            pos.add(1, 0, 0),
            pos.add(-1, 0, 0),
            pos.add(0, 0, 1),
            pos.add(0, 0, -1),

            // 上下
            pos.add(0, 1, 0),
            pos.add(0, -1, 0),

            // 上の周囲
            pos.add(1, 1, 0),
            pos.add(-1, 1, 0),
            pos.add(0, 1, 1),
            pos.add(0, 1, -1),

            // 下の周囲
            pos.add(1, -1, 0),
            pos.add(-1, -1, 0),
            pos.add(0, -1, 1),
            pos.add(0, -1, -1)

        };

        for (BlockPos pos1: _pos) {


            IBlockState state2 = world.getBlockState(pos1);

            if (!(state2.getBlock() == this)) continue;


            result.add(pos1);

        }


        return result;
    }


    public void aa(World world, BlockPos pos) {

        world.setBlockToAir(pos);

        BlockPos _pos[] = {
            // 周り
            pos.add(1, 0, 0),
            pos.add(-1, 0, 0),
            pos.add(0, 0, 1),
            pos.add(0, 0, -1),

            // 上下
            pos.add(0, 1, 0),
            pos.add(0, -1, 0),

            // 上の周囲
            pos.add(1, 1, 0),
            pos.add(-1, 1, 0),
            pos.add(0, 1, 1),
            pos.add(0, 1, -1),

            // 下の周囲
            pos.add(1, -1, 0),
            pos.add(-1, -1, 0),
            pos.add(0, -1, 1),
            pos.add(0, -1, -1)

        };

        for (BlockPos pos1: _pos) {


            IBlockState state2 = world.getBlockState(pos1);

            if (!(state2.getBlock() == this)) continue;

            this.aa(world, pos1);

        }


    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(world, pos, state, rand);

        world.scheduleUpdate(pos, this, 10);


        if (this.storageBoxPos != null) {

            // 空気ブロックではないなら
            if (!world.isAirBlock(this.storageBoxPos)) {

                IBlockState storageBoxState = world.getBlockState(this.storageBoxPos);

                if (storageBoxState.getBlock() != this)
                {

                    this.AA(world, pos, storageBoxState);

                    world.setBlockToAir(this.storageBoxPos);

                    this.storageBoxPos = null;
                }



            }



        }



        BlockPos _pos[] = {
            pos.add(1, 0, 0),
            pos.add(-1, 0, 0),
            pos.add(0, 0, 1),
            pos.add(0, 0, -1),

            // 下
            pos.add(0, -1, 0),

        };

        for (BlockPos pos1: _pos) {


            IBlockState state2 = world.getBlockState(pos1);

            if (state2.getBlock() == this)
            {


                // System.out.println("is This");
                continue;
            }


            Material material = state2.getMaterial();


            boolean a = material == Material.WATER ||
                        material == Material.LAVA ||
                        state2.getBlock() == Blocks.OBSIDIAN;

            if (!a && !world.isAirBlock(pos1)) continue;


            world.setBlockState(pos1, this.getBlockState().getBaseState(), 3);
        }


        // world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);


    }


    @Override
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
    {

        aa(world, pos);

    }



    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {

        super.onBlockAdded(world, pos, state);

        world.scheduleUpdate(pos, this, 10);


        //System.out.println("onBlockAdded");
    }


}
