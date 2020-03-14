package space.bbkr.blueshinyfire.mixin;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoulFireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import space.bbkr.blueshinyfire.BlueShinyFire;

@Mixin(SoulFireBlock.class)
public abstract class MixinSoulFireBlock extends AbstractFireBlock {
	@Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
	private void injectBlueFirePersistence(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		info.setReturnValue(!world.getBlockState(pos.down()).isAir());
	}
	
	public MixinSoulFireBlock(Settings settings, float damage) {
		super(settings, damage);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		super.onEntityCollision(state, world, pos, entity);
		
		if (!entity.isFireImmune()) {
			if (entity instanceof ItemEntity) {
				ItemEntity item = (ItemEntity)entity;
				if (BlueShinyFire.DESOULIFYING.contains(item.getStack().getItem())) {
					world.setBlockState(pos, Blocks.FIRE.getDefaultState());
				}
				if (BlueShinyFire.BRIGHTENING.contains(item.getStack().getItem())) {
					world.setBlockState(pos, BlueShinyFire.BRIGHT_SOUL_FIRE.getDefaultState());
				}
			}
		}
	}
}