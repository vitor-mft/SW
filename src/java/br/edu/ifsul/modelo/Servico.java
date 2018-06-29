package br.edu.ifsul.modelo;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import org.tempuri.CResultado;
import org.tempuri.CalcPrecoPrazoWS;

/**
 *
 * @author V_M_FT
 */
@Stateless
@ApplicationPath("servicos")
@Path("servico")
public class Servico extends Application implements Serializable {

    //acesso  http://localhost:8080/ServicoTrabalhoSW/servicos/application.wadl
    private Gson gson;
    private CalcPrecoPrazoWS freteCorreios;

    public Servico() {
        gson = new Gson();

    }

    @POST
    @Produces("application/json; charset=ISO-8859-1")
    @Consumes("application/json; charset=ISO-8859-1")
    public Response CalCorreio(correio objeto) {

        freteCorreios = new CalcPrecoPrazoWS();
        
        try {
            System.out.println("Chegou aqui no Calcula Frere RESULTADO : ");
            if (objeto.getCepDestino() == null) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

            CResultado resultado = freteCorreios.getCalcPrecoPrazoWSSoap()
                    .calcPrecoPrazo(
                            "",
                            "",
                            objeto.getTipodeFrete(),
                            "99150000",
                            objeto.getCepDestino(),
                            objeto.getPeso(),
                            1,//formato 01 caixa
                            new BigDecimal(30),//comprimento
                            new BigDecimal(30),//altura
                            new BigDecimal(30), //largura
                            new BigDecimal(0.0),//diametro
                            "n",//Mao Propria
                            new BigDecimal(objeto.getValorDeclarado()),//valor declarado
                            "n");//aviso de recebimento       

            if (!resultado.getServicos().getCServico().get(0).getMsgErro().isEmpty()) {
                String res = resultado.getServicos().getCServico().get(0).getMsgErro();
                System.out.println("" + res);
                if (res.equals("CEP de origem invalido.")) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                } else {
                    return Response.status(Response.Status.PRECONDITION_FAILED).build();
                }

            } else {

                objeto.setValorFrete(Double.parseDouble(resultado.getServicos().getCServico().get(0).getValor().replace(",", ".")));
                objeto.setPrazoDeEntrega(Integer.parseInt(resultado.getServicos().getCServico().get(0).getPrazoEntrega()));

            }
        } catch (NumberFormatException ex) {
            System.out.println("" + ex);
        }
        return Response.ok(gson.toJson(objeto)).build();
    }
}
