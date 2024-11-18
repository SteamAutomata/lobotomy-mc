package lobotomy.sound

import lobotomy.LobotomyMod
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModSounds {
    val REGISTRY: DeferredRegister<SoundEvent> = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LobotomyMod.ID)

    val LOBOTOMY by REGISTRY.registerObject("lobotomy") {
        createSound("lobotomy", 16F)
    }
    val QUICK by REGISTRY.registerObject("quick") {
        createSound("quick", 16F)
    }
    val BLACKBEARD by REGISTRY.registerObject("blackbeard") {
        createSound("blackbeard", 16F)
    }
    val NADINEMOUK by REGISTRY.registerObject("nadinemouk") {
        createSound("nadinemouk", 16F)
    }

    private fun createSound(name: String, range: Float): SoundEvent {
        return SoundEvent.createFixedRangeEvent(
            ResourceLocation(LobotomyMod.ID, name),
            range
        )
    }
}



