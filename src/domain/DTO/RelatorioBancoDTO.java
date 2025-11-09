package src.domain.DTO;

import java.util.List;

public class RelatorioBancoDTO {
    public static final class TotaisPorTipo {
        private final String Tipo;   // "CORRENTE" ou "POUPANCA"
        private final int Quantidade;
        private final float SaldoTotal;

        public TotaisPorTipo(String tipo, int quantidade, float saldoTotal) {
            this.Tipo = tipo;
            this.Quantidade = quantidade;
            this.SaldoTotal = saldoTotal;
        }

        public String getTipo() { return Tipo; }
        public int getQuantidade() { return Quantidade; }
        public float getSaldoTotal() { return SaldoTotal; }
    }

    private final List<TotaisPorTipo> PorTipo;
    private final int TotalContas;
    private final float SaldoTotalBanco; // (no futuro: BigDecimal)

    public RelatorioBancoDTO(List<TotaisPorTipo> porTipo, int totalContas, float saldoTotalBanco) {
        this.PorTipo = porTipo;
        this.TotalContas = totalContas;
        this.SaldoTotalBanco = saldoTotalBanco;
    }

    public List<TotaisPorTipo> getPorTipo() { return PorTipo; }
    public int getTotalContas() { return TotalContas; }
    public float getSaldoTotalBanco() { return SaldoTotalBanco; }

}
