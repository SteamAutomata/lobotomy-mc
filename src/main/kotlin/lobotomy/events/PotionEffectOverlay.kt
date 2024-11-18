package lobotomy.events

import com.mojang.blaze3d.systems.RenderSystem
import lobotomy.LobotomyMod
import lobotomy.potion.LobotomyEffect
import lobotomy.potion.LobotomyOverlayType
import lobotomy.potion.ModEffects
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent
import net.minecraftforge.client.gui.overlay.ForgeGui
import net.minecraftforge.client.gui.overlay.IGuiOverlay
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.runWhenOn
import kotlin.math.abs
import kotlin.math.absoluteValue


@Mod.EventBusSubscriber(modid=LobotomyMod.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value=[Dist.CLIENT])
object PotionEffectOverlay: IGuiOverlay {


    private val QUICK = ResourceLocation("lobotomy", "textures/quick.png")
    private val NADINEMOUK = ResourceLocation("lobotomy", "textures/nadinemouk.png")
    private val BLACKBEARD = ResourceLocation("lobotomy", "textures/blackbeard.png")
    private val LOBOTOMIES = arrayOf(
        ResourceLocation("lobotomy", "textures/lobotomy1.png"),
        ResourceLocation("lobotomy", "textures/lobotomy2.png")
    )


    private var currentRandomLobotomy: Int = 0



    private val mc: Minecraft = Minecraft.getInstance()

    @SubscribeEvent
    fun registerGuiOverlays(event: RegisterGuiOverlaysEvent) {
        event.registerAboveAll("lobotomy_overlay", this)

    }

    override fun render(
        gui: ForgeGui?,
        guiGraphics: GuiGraphics?,
        partialTick: Float,
        screenWidth: Int,
        screenHeight: Int
    ) {
        val effect = mc.player?.getEffect(ModEffects.LOBOTOMY.get())?.effect

        if (effect is LobotomyEffect) {
            val transparency = effect.currentOverlayTime.coerceIn(-1.0f,1.0f).absoluteValue
            val alpha = 1f - transparency
            val loc = when(effect.currentOverlayType) {
                LobotomyOverlayType.RANDOM -> LOBOTOMIES[currentRandomLobotomy]
                LobotomyOverlayType.BURGER -> QUICK
                LobotomyOverlayType.NADINE_MOUK -> NADINEMOUK
                LobotomyOverlayType.BLACKBEARD -> BLACKBEARD
            }

            if (alpha <= 0) {
                currentRandomLobotomy = LOBOTOMIES.indexOf(LOBOTOMIES.random())
            }

            RenderSystem.setShader { GameRenderer.getPositionTexShader() }
            RenderSystem.setShaderColor(1f,1f,1f,alpha)
            RenderSystem.setShaderTexture(0, loc)

            guiGraphics!!.blit(
                loc,
                0,0,
                0f,0f,
                screenWidth,screenHeight,
                screenWidth,screenHeight
            )
        }
    }
}