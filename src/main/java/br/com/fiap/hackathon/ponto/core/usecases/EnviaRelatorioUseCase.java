package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.EnviaRelatorioInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.EnviaRelatorioFilaRelatoriosOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnviaRelatorioUseCase implements EnviaRelatorioInputPort {

    private final EnviaRelatorioFilaRelatoriosOutputPort enviaRelatorioFilaRelatoriosOutputPort;

    @Override
    public String enviaRelatorioPonto(RelatorioPontoDTO relatorioPontoDTO) {
        return enviaRelatorioFilaRelatoriosOutputPort.enviarRelatorio(relatorioPontoDTO);
    }
}
