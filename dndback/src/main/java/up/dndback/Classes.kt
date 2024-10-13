import classes.Barbaro
import classes.Bardo
import classes.Bruxo
import classes.Clerigo
import classes.Druida
import classes.Feiticeiro
import classes.Guerreiro
import classes.Ladino
import classes.Mago
import classes.Monge
import classes.Paladino
import classes.Patrulheiro
import up.dndback.classes.iClasses

object Classes {
    fun get_classes(): List<iClasses> {
        return listOf(
            Barbaro(),
            Bardo(),
            Bruxo(),
            Clerigo(),
            Druida(),
            Feiticeiro(),
            Guerreiro(),
            Ladino(),
            Mago(),
            Monge(),
            Paladino(),
            Patrulheiro()
        )

    }
}