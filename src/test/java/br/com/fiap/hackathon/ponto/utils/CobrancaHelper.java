package br.com.fiap.hackathon.ponto.utils;

import br.com.fiap.hackathon.ponto.core.domain.entities.QrCode;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.StatusCobrancaEnum;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.hackathon.ponto.core.dtos.CobrancaDTO;
import br.com.fiap.hackathon.ponto.core.dtos.StatusPagamentoDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static br.com.fiap.hackathon.ponto.utils.PedidoHelper.getPedidoDTO;

public abstract class CobrancaHelper {

    private static final Long ID = 1L;
    private static final Long PEDIDO_ID = 1L;
    private static final BigDecimal VALOR = BigDecimal.valueOf(45.9);
    private static final StatusPedidoEnum STATUS = StatusPedidoEnum.RECEBIDO;
    private static final QrCode QR_CODE = new QrCode("e3BlZGlkb0lkOjEsdmFsb3I6NDUuOX0=");

    private static final LocalDateTime CREATED_AT = LocalDateTime.MAX;
    private static final LocalDateTime UPDATED_AT = LocalDateTime.MAX;

    public static CobrancaDTO getCobrancaDTO() {
        return new CobrancaDTO(ID, PEDIDO_ID, VALOR, STATUS, QR_CODE.getDecodedBase64Value());
    }

    public static StatusPagamentoDTO getStatusPagamentoPagoDTO() {
        return new StatusPagamentoDTO(1L, StatusPedidoEnum.RECEBIDO.getDescricao(), StatusCobrancaEnum.PAGO);
    }

    public static QrCode getQrCode() {
            var pedidoDTO = getPedidoDTO();
            return new QrCode("{pedidoId:"+pedidoDTO.id()+",valor:"+pedidoDTO.valorTotal()+"}");
    }

    public static  CobrancaDTO getCobrancaDTOcomStatusPendente() {
        var cobrancaDTO = getCobrancaDTO();
        return new CobrancaDTO(
                cobrancaDTO.id(),
                cobrancaDTO.pedidoId(),
                cobrancaDTO.valor(),
                StatusPedidoEnum.PENDENTE_DE_PAGAMENTO,
                cobrancaDTO.qrCode()
        );
    }

}
