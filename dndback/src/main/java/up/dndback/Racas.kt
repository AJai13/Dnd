import racas.Anao
import racas.AnaoDaColina
import racas.AnaoDaMontanha
import racas.Draconato
import racas.Drow
import racas.Elfo
import racas.ElfoAlto
import racas.ElfoDaFloresta
import racas.Gnomo
import racas.GnomoDaFloresta
import racas.GnomoDasRochas
import racas.HalflingPesLeves
import racas.HalflingRobusto
import racas.Humano
import racas.MeioElfo
import racas.MeioOrc
import racas.Tiefling
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