package space.bbkr.blueshinyfire.mixin;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import space.bbkr.blueshinyfire.BlueShinyFire;

@Mixin(FireBlock.class)
public abstract class MixinFireBlock extends AbstractFireBlock {
	public MixinFireBlock(Settings settings, float damage) {
		super(settings, damage);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		super.onEntityCollision(state, world, pos, entity);

		if (!entity.isFireImmune()) {
			if (entity instanceof ItemEntity) {
				ItemEntity item = (ItemEntity)entity;
				if (BlueShinyFire.SOULIFYING.contains(item.getStack().getItem())) {
					world.setBlockState(pos, Blocks.SOUL_FIRE.getDefaultState());
				}
			}
		}
	}
}