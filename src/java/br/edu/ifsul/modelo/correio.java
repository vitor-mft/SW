
package br.edu.ifsul.modelo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author V_M_FT
 */
@XmlRootElement
public class correio implements Serializable {
    
    
   // private String endereco;
    private Double valorCompra;
   // private String cepOrigem;
    private String cepDestino;
    private Double valorFrete;
    private Integer PrazoDeEntrega;
    private String TipodeFrete;
    private String peso;    
//    private Integer formato; // 1 CAIXA, 2 ROLO PRISMA; 3 ENVELOPE
//    private String comprimento;
//    private String altura;
//    private String largura;
//    private String diametro;
 //   private String maoPropria; //serviço adicional
    private String valorDeclarado; //???
 //   private String avisoRecebimento; // S ou N  

    
    
    public correio() {
        
        
    }

      public correio(Double valorCompra, String cepDestino, String dataEntrega ,String TipodeFrete, String peso, 
              Double valorFrete, Integer PrazoDeEntrega) {
        this.TipodeFrete = TipodeFrete;
        this.peso = peso;
        this.valorCompra = valorCompra;
        this.cepDestino = cepDestino;
        this.PrazoDeEntrega = PrazoDeEntrega;
        this.valorFrete = valorFrete;
      
        
        
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    public Integer getPrazoDeEntrega() {
        return PrazoDeEntrega;
    }

    public void setPrazoDeEntrega(Integer PrazoDeEntrega) {
        this.PrazoDeEntrega = PrazoDeEntrega;
    }

    public String getTipodeFrete() {
        return TipodeFrete;
    }

    public void setTipodeFrete(String TipodeFrete) {
        this.TipodeFrete = TipodeFrete;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(String valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }
    
       
    
  
    
    
    
}
