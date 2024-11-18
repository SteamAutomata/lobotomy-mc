package lobotomy.potion

import lobotomy.sound.ModSounds
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import java.util.ArrayList

class LobotomyEffect() : MobEffect(MobEffectCategory.BENEFICIAL, 0x000000) {
    private var timer = 60;
    val random: RandomSource = RandomSource.create()

    var currentOverlayTime: Float = 0f
    var currentOverlayType: LobotomyOverlayType = LobotomyOverlayType.RANDOM

    private fun randomOverlay(player: Player) {
        currentOverlayTime = 0.5f;
        currentOverlayType = LobotomyOverlayType.RANDOM
        player.level().playSound(
            player,
            player,
            ModSounds.LOBOTOMY,
            SoundSource.MASTER,
            1F,
            (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f
        )
    }

    private fun burgerOverlay(player: Player) {
        currentOverlayTime = 0.5f;
        currentOverlayType = LobotomyOverlayType.BURGER
        player.level().playSound(
            player,
            player,
            ModSounds.QUICK,
            SoundSource.MASTER,
            1F,
            (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f
        )
    }

    private fun nadinemoukOverlay(player: Player) {
        currentOverlayTime = 0.5f;
        currentOverlayType = LobotomyOverlayType.NADINE_MOUK
        player.level().playSound(
            player,
            player,
            ModSounds.NADINEMOUK,
            SoundSource.MASTER,
            1F,
            (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f
        )
    }

    private fun blackbeardOverlay(player: Player) {
        currentOverlayTime = 0.5f;
        currentOverlayType = LobotomyOverlayType.BLACKBEARD
        player.level().playSound(
            player,
            player,
            ModSounds.BLACKBEARD,
            SoundSource.MASTER,
            1F,
            (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f
        )
    }


    override fun isDurationEffectTick(pDuration: Int, pAmplifier: Int): Boolean {
        return true
    }

    override fun applyEffectTick(pLivingEntity: LivingEntity, pAmplifier: Int) {
        timer -= 1;

        if (currentOverlayTime > -1f) {
            currentOverlayTime -= 0.05f
        }

        if (timer < 0) {
            timer = random.nextIntBetweenInclusive(60, 450)

            if (pLivingEntity is Player) {
                val overlays = arrayOf(
                    // lazy to use weights
                    {randomOverlay(pLivingEntity)},
                    {randomOverlay(pLivingEntity)},
                    {randomOverlay(pLivingEntity)},

                    {burgerOverlay(pLivingEntity)},
                    {blackbeardOverlay(pLivingEntity)},
                    {nadinemoukOverlay(pLivingEntity)}
                ).random().invoke()
            }
        }
    }
}