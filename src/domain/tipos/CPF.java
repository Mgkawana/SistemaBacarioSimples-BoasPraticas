package src.domain.tipos;

import java.util.Objects;

public final class CPF implements Comparable<CPF> {
    //formato padrão XXX.XXX.XXX-XX
    private final String Valor;

    private CPF(String valor) {
        Valor = valor;
    }

    public static CPF of(String input)
    {
        if(input == null) {
            throw new IllegalArgumentException("O campo número do CPF precisa ser preenchido.");
        }
        String digits = input.trim().replaceAll("\\D" , "");
        if(digits.length() !=11){
            throw new IllegalArgumentException("O número do CPF deve ter exatos 11 dígitos no formato(XXX.XXX.XXX-XX ou XXXXXXXXXXX)");
        }
        return new CPF(digits);
        //
    }
    public String valorNormalizado(){return Valor;}

    @Override public boolean equals(Object o){
        if(this == o){return true;}
        if(!(o instanceof CPF that)){return false;}
        return Valor.equals(that.Valor);

    }
    @Override public int hashCode() { return Objects.hash(Valor); }
    @Override public int compareTo(CPF o) { return this.Valor.compareTo(o.Valor); }

}
