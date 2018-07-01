
package br.edu.ifsul.modelo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *{"cepDestino":"95985000",
"TipodeFrete": "04510",
"peso" : "2",
"valorDeclarado" : "200,00"}
 * @author V_M_FT
 */
@XmlRootElement
public class correio implements Serializable{
    
    private String codigoServico;
    private String cepDestino;
    private String peso;
    private Double valorFrete;
    private Integer prazoEntrega;
    private Double valorDeclarado;

    public correio() {
    }

    public correio(String codigoServico, String cepDestino, String peso, Double valorFrete, Integer prazoEntrega,
    Double valorDeclarado) {
        this.codigoServico = codigoServico;
        this.cepDestino = cepDestino;
        this.peso = peso;
        this.valorFrete = valorFrete;
        this.prazoEntrega = prazoEntrega;
        this.valorDeclarado = valorDeclarado;
    }
    

    public String getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Integer getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(Integer prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public Double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(Double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }
    

}