# Kotlin Modding Skeleton
Provides an example mod written in Kotlin using Kotlin for Forge.

# Créer un projet Minecraft

Utiliser [le template de base sur Github](https://github.com/thedarkcolour/KotlinModdingSkeleton)


1. Ouvrir dans votre IDE préféré (de préférence IntelliJ).

2. Si on est sur VSCODE, installer les extensions suivantes (pas testé donc à vos risques et périls):
- Kotlin
- Maven
- Project Manager for Java
- Gradle for Java

3. Modifier `gradle.properties` selon les besoins, changer notamment le mod id (par exemple: `mod_id=swordmod`)

4. Renommer les dossiers "example/examplemod" avec un groupid.
Par exemple, si le modid est "org.john.swordmod" alors la structure doit être "src/main/kotlin/org/john/swordmod"


## Commencer à coder

1. Renommer la classe principale (par exemple SwordMod)
2. Changer la valeur constante "ID" avec un identifiant unique du mod (exemple: `swordmod`). 
Tous les autres bouts de code du mod doivent l'utiliser. 

## Le premier Item du mod

Créer une classe statique qui répertorie les items de votre mod:
```kotlin
// En général, dans item/ModItems.kt
object ModItems {
    // Le registre fait en sorte que Forge reconnaisse les items du mod.
    val REGISTRY: DeferredRegister<Item> = DeferredRegister.create(
        ForgeRegistries.ITEMS,

        // Ne pas écrire une chaine de caractère directement, utiliser la constante défini dans la classe principale
        SwordMod.ID
    )

    val RUBY_SWORD = REGISTRY.registerObject(
        // Le nom de l'item, par exemple `/give Dev swordmod:ruby_sword`
        "ruby_sword",
        // Quand Forge commencera à charger le mod, il exécutera cette méthode
        { 
            // On peut faire `Item(Item.Properties())` et enchaîner les méthodes pour créer un item
            // Mais on peut aussi créer une classe pour l'item
            // Comme c'est un épée, il faut plutôt créer et inhériter SwordItem
            RubySword()
        }
    )
}
```

Il ne faut pas oublier de demander à Forge d'ajouter le registre dans le bus:
```kotlin
// Dans le constructeur de la classe principale
// MOD_BUS c'est une bus de Forge qui peut être importé automatiquement
ModItems.REGISTRY.register(MOD_BUS)
```

## Tester
1. Dans Gradle, lancer gen(nom de l'ide)Runs
2. Une fois les runs générés, il faut ensuite exécuter runClient