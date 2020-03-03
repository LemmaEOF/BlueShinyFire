package space.bbkr.blueshinyfire.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoulFireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoulFireBlock.class)
public class MixinSoulFireBlock {
	@Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
	private void injectBlueFirePersistence(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		info.setReturnValue(!world.getBlockState(pos.down()).isAir());
	}
}
