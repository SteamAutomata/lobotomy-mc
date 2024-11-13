package lobotomy.item

import lobotomy.sound.ModSounds
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.Level

class OrbitoclastItem: Item(Item.Properties().rarity(Rarity.EPIC).stacksTo(1)) {
    val random: RandomSource = RandomSource.create()

    override fun use(
        pLevel: Level,
        pPlayer: Player,
        pUsedHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {

        if (pLevel.isClientSide()) {

        } else {
            pLevel.playSound(
                pPlayer,
                pPlayer,
                ModSounds.LOBOTOMY,
                SoundSource.MASTER,
                1F,
                (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f
            )
        }

        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand))
    }
}