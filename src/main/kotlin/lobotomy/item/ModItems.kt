package lobotomy.item

import lobotomy.LobotomyMod
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModItems {
    val REGISTRY: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, LobotomyMod.ID)

    val ORBITOCLAST = REGISTRY.registerObject(
        "orbitoclast",
        { OrbitoclastItem() }
    )
}