package space.bbkr.blueshinyfire.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;
import net.minecraft.world.IWorld;

import java.util.Iterator;

public class SoulFlintAndSteelItem extends Item {
	public SoulFlintAndSteelItem(Settings settings) {
		super(settings);
	}
	
	public ActionResult useOnBlock(ItemUsageContext context) {
		PlayerEntity player = context.getPlayer();
		IWorld world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		BlockState state = world.getBlockState(pos);
		if (isIgnitable(state)) {
			world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, RANDOM.nextFloat() * 0.4F + 0.8F);
			world.setBlockState(pos, state.with(Properties.LIT, true), 11);
			if (player != null) {
				context.getStack().damage(1, player, (p) -> p.sendToolBreakStatus(context.getHand()));
			}
			
			return ActionResult.SUCCESS;
		} else {
			BlockPos firePos = pos.offset(context.getSide());
			if (canIgnite(world.getBlockState(firePos), world, firePos)) {
				world.playSound(player, firePos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, RANDOM.nextFloat() * 0.4F + 0.8F);
				BlockState fireState = Blocks.SOUL_FIRE.getDefaultState();
				world.setBlockState(firePos, fireState, 11);
				ItemStack stack = context.getStack();
				if (player instanceof ServerPlayerEntity) {
					Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)player, firePos, stack);
					stack.damage(1, player, (p) -> {
						p.sendToolBreakStatus(context.getHand());
					});
				}
				
				return ActionResult.SUCCESS;
			} else {
				return ActionResult.FAIL;
			}
		}
	}
	
	public static boolean isIgnitable(BlockState state) {
		return state.getBlock() == Blocks.CAMPFIRE && !state.get(Properties.WATERLOGGED) && !state.get(Properties.LIT);
	}
	
	public static boolean canIgnite(BlockState block, IWorld world, BlockPos pos) {
		BlockState state = AbstractFireBlock.getState(world, pos);
		boolean makePortal = false;
		for (Direction dir : Type.HORIZONTAL) {
			if (world.getBlockState(pos.offset(dir)).getBlock() == Blocks.OBSIDIAN && ((NetherPortalBlock)Blocks.NETHER_PORTAL).createAreaHelper(world, pos) != null) {
				makePortal = true;
			}
		}
		
		return block.isAir() && (state.canPlaceAt(world, pos) || makePortal);
	}
}
