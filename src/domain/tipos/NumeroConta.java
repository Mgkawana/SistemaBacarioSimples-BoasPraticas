package src.domain.tipos;

import java.util.Objects;

public final class NumeroConta implements Comparable<NumeroConta>
{
    //formato padrão XXXXX-X
    private final String Valor;

    private NumeroConta(String valor) {
        Valor = valor;
    }

    public static NumeroConta of(String input)
    {
        if(input == null) {
            throw new IllegalArgumentException("O campo número da conta precisa ser preenchido.");
        }
        String digits = input.trim().replaceAll("\\D" , "");
        if(digits.length() !=6){
            throw new IllegalArgumentException("O número da conta deve ter exatos 6 dígitos no formato(XXXXX-X ou XXXXXX)");
        }
        return new NumeroConta(digits);

    }
    public String valorNormalizado(){return Valor;}

    @Override public boolean equals(Object o){
        if(this == o){return true;}
        if(!(o instanceof NumeroConta that)){return false;}
        return Valor.equals(that.Valor);

    }
    @Override public int hashCode() { return Objects.hash(Valor); }
    @Override public int compareTo(NumeroConta o) { return this.Valor.compareTo(o.Valor); }
    @Override public String toString(){return Valor;}
}
