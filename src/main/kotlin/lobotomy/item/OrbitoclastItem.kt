package lobotomy.item

import lobotomy.potion.ModEffects
import lobotomy.sound.ModSounds
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.Level

class OrbitoclastItem: Item(Item.Properties().rarity(Rarity.EPIC).stacksTo(1).durability(1)) {
    val random: RandomSource = RandomSource.create()

    override fun use(
        pLevel: Level,
        pPlayer: Player,
        pUsedHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {

        pLevel.playSound(
            pPlayer,
            pPlayer,
            ModSounds.LOBOTOMY,
            SoundSource.MASTER,
            1F,
            (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f
        )
        pPlayer.addEffect(MobEffectInstance(ModEffects.LOBOTOMY.get(), -1, 1, true, true))

        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand))
    }
}