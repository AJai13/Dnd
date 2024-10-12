import racas.AnaoDaMontanha
import racas.AnaoDaColina
import racas.Anao
import racas.Elfo
import racas.ElfoDaFloresta
import racas.ElfoAlto
import racas.MeioElfo
import racas.Humano
import racas.HalflingPesLeves
import racas.HalflingRobusto
import racas.Draconato
import racas.Gnomo
import racas.GnomoDaFloresta
import racas.GnomoDasRochas
import racas.MeioOrc
import racas.Tiefling
import racas.Drow
import racas.iRacas

object Racas {
    fun get_racas(): List<iRacas> {
        return listOf(
            Anao(),
            AnaoDaMontanha(),
            AnaoDaColina(),
            Elfo(),
            ElfoDaFloresta(),
            ElfoAlto(),
            MeioElfo(),
            Humano(),
            HalflingPesLeves(),
            HalflingRobusto(),
            Draconato(),
            Gnomo(),
            GnomoDaFloresta(),
            GnomoDasRochas(),
            MeioOrc(),
            Tiefling(),
            Drow()
        )
    }
}