package coda.astonishingaquariums.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AquariumBlock extends Block {
    public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 2);
    /** SHAPES:
     * 0 = full glass
     * 1 = east u
     * 2 = west u
     */

    public AquariumBlock() {
        super(BlockBehaviour.Properties.of(Material.GLASS).noOcclusion().strength(1.0F).requiresCorrectToolForDrops().sound(SoundType.GLASS));
        this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(TYPE);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (world.getBlockState(pos.above()).is(Blocks.GRASS_BLOCK)) {
            System.out.println("Balls!");
            world.setBlock(pos, state.setValue(TYPE, 2), 2);
            //world.setBlock(pos.above(), world.getBlockState(pos.above()).setValue(TYPE, 1), 2);
        }
        else {
            world.setBlock(pos, state.setValue(TYPE, 0), 2);
            //System.out.println("Balls!");
        }
    }

    @Override
    public VoxelShape getVisualShape(BlockState p_48735_, BlockGetter p_48736_, BlockPos p_48737_, CollisionContext p_48738_) {
        return Shapes.empty();
    }

    @Override
    public float getShadeBrightness(BlockState p_48731_, BlockGetter p_48732_, BlockPos p_48733_) {
        return 1.0F;
    }

    @Override
    public boolean skipRendering(BlockState p_53972_, BlockState p_53973_, Direction p_53974_) {
        return p_53973_.is(this) || super.skipRendering(p_53972_, p_53973_, p_53974_);
    }
}