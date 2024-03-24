package br.com.fiap.hackathon.ponto.core.ports.out;

import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;

public interface NotificaSolicitanteRelatorioPontoOuputPort {
    void notificaClienteStatusPedido(RelatorioPontoDTO relatorioPontoDTO);
}
