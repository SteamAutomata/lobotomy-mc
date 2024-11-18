package lobotomy.potion

import lobotomy.LobotomyMod
import net.minecraft.world.effect.MobEffect
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModEffects {
    val REGISTRY: DeferredRegister<MobEffect> = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, LobotomyMod.ID)


    val LOBOTOMY = REGISTRY.registerObject(
        "lobotomy",
        { LobotomyEffect() }
    )
}