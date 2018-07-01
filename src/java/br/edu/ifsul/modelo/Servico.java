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
        freteCorreios = new CalcPrecoPrazoWS();
    }

    @POST
    @Produces("application/json; charset=ISO-8859-1")
    @Consumes("application/json; charset=ISO-8859-1")
    public Response CalCorreio(correio objeto) {
        try {
            if (objeto.getCepDestino() == null) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

            CResultado resultado = freteCorreios.getCalcPrecoPrazoWSSoap()
                    .calcPrecoPrazo(
                            "", 
                            "",
                            objeto.getCodigoServico(),
                            "99150000",
                            objeto.getCepDestino(),
                            objeto.getPeso(),
                            1,
                            new BigDecimal(30),
                            new BigDecimal(30),
                            new BigDecimal(30),
                            new BigDecimal(0.0),
                            "s",
                            new BigDecimal(objeto.getValorDeclarado()),
                            "s");

            if (!resultado.getServicos().getCServico().get(0).getMsgErro().isEmpty()) {
                String res = resultado.getServicos().getCServico().get(0).getMsgErro();

                System.out.println("" + res);

                if (res.equals("CEP de destino invalido.")) {
                    return Response.status(Response.Status.FOUND).build();//302
                } else if (res.equals("Peso excedido.")) {
                    return Response.status(Response.Status.FORBIDDEN).build();//403
                } else if (res.equals("Serviço indisponível para o trecho informado.")) {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();//500
                }else if (res.equals("Erro ao calcular tarifa no SGPB. ERP-031: Nao informado o peso, a quantidade ou o valor(-1).")) {
                    return Response.status(Response.Status.HTTP_VERSION_NOT_SUPPORTED).build();//505
                }
                
                else {
                    return Response.status(Response.Status.PRECONDITION_FAILED).build();//412
                }

            } else {

                objeto.setValorFrete(Double.parseDouble(resultado.getServicos().getCServico().get(0).getValor().replace(",", ".")));
                objeto.setPrazoEntrega(Integer.parseInt(resultado.getServicos().getCServico().get(0).getPrazoEntrega()));

            }

        } catch (NumberFormatException e) {
            System.out.println("" + e);
        }

        return Response.ok(gson.toJson(objeto)).build();
    }

//{"codigoServico":"04014","cepDestino":"99030040","peso":"1"}
}
