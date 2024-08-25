package br.com.fiap.SmartCash.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.SmartCash.Assinatura.Assinatura;
import br.com.fiap.SmartCash.Assinatura.AssinaturaRepository;
import br.com.fiap.SmartCash.Empresa.Empresa;
import br.com.fiap.SmartCash.Empresa.EmpresaRepository;
import br.com.fiap.SmartCash.FluxoDeCaixa.FluxoCaixa;
import br.com.fiap.SmartCash.FluxoDeCaixa.FluxoCaixaRepository;
import br.com.fiap.SmartCash.RegistroAssinatura.RegistroAssinatura;
import br.com.fiap.SmartCash.RegistroAssinatura.RegistroAssinaturaRepository;
import br.com.fiap.SmartCash.Usuario.Usuario;
import br.com.fiap.SmartCash.Usuario.UsuarioRepository;
import br.com.fiap.SmartCash.UsuarioEmpresa.UsuarioEmpresa;
import br.com.fiap.SmartCash.UsuarioEmpresa.UsuarioEmpresaRepository;

@Configuration
public class DataBaseSeeder implements CommandLineRunner {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private RegistroAssinaturaRepository registroAssinaturaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FluxoCaixaRepository fluxoCaixaRepository;

    @Autowired
    private UsuarioEmpresaRepository usuarioEmpresaRepository;

    @Override
    public void run(String... args) throws Exception {
//        empresaRepository.saveAll(
//                List.of(
//                        Empresa.builder().ID_EMPRESA(1L).NOME("Kabum").CNPJ("64423918000126").RAMO("Eletrônico")
//                                .build(),
//                        Empresa.builder().ID_EMPRESA(2L).NOME("Ogilvy").CNPJ("67107516000100").RAMO("Publicidade")
//                                .build()));
//
//        assinaturaRepository.saveAll(
//                List.of(
//                        Assinatura.builder().ID_ASSINATURA(1L).TIPO("BASIC").VALOR(new BigDecimal("999.99")).build(),
//                        Assinatura.builder().ID_ASSINATURA(2L).TIPO("MEDIUM").VALOR(new BigDecimal("1999.99")).build(),
//                        Assinatura.builder().ID_ASSINATURA(3L).TIPO("PREMIUM").VALOR(new BigDecimal("2999.99")).build()
//                        )
//                );
//
//        usuarioRepository.saveAll(
//                List.of(
//                        Usuario.builder().ID_USUARIO(1L).NOME("Rafael").DOCUMENTO("123.456.789-00").SENHA("senha123")
//                                .LOGIN_USUARIO("R8900").build(),
//                        Usuario.builder().ID_USUARIO(2L).NOME("Gustavo").DOCUMENTO("987.654.321-00").SENHA("senha456")
//                                .LOGIN_USUARIO("G2100").build()));
//
//        registroAssinaturaRepository.saveAll(
//                List.of(
//                        RegistroAssinatura.builder()
//                                .DATA_AQUISICAO(LocalDate.now())
//                                .DATA_VENCIMENTO(LocalDate.now().plusMonths(3))
//                                .empresa(empresaRepository.findById(1L).orElse(null))
//                                .assinatura(assinaturaRepository.findById(1L).orElse(null))
//                                .TOKEN_EMPRESA("R2Vmf2s3@F")
//                                .build(),
//                        RegistroAssinatura.builder()
//                                .DATA_AQUISICAO(LocalDate.now())
//                                .DATA_VENCIMENTO(LocalDate.now().plusMonths(3))
//                                .empresa(empresaRepository.findById(1L).orElse(null))
//                                .assinatura(assinaturaRepository.findById(2L).orElse(null))
//                                .TOKEN_EMPRESA("xQtY2$Xaeq")
//                                .build()));
//
//        fluxoCaixaRepository.saveAll(
//                List.of(
//                        FluxoCaixa.builder()
//                                .TIPO("RECEITA")
//                                .VALOR(new BigDecimal("100.00"))
//                                .DESCRICAO("Recebimento de pagamento")
//                                .DATA_INCLUSAO(LocalDate.now())
//                                .empresa(empresaRepository.findById(1L).orElse(null))
//                                .usuario(usuarioRepository.findById(1L).orElse(null))
//                                .build(),
//                        FluxoCaixa.builder()
//                                .TIPO("DESPESA")
//                                .VALOR(new BigDecimal("50.00"))
//                                .DESCRICAO("Pagamento de fornecedor")
//                                .DATA_INCLUSAO(LocalDate.now())
//                                .empresa(empresaRepository.findById(2L).orElse(null))
//                                .usuario(usuarioRepository.findById(2L).orElse(null))
//                                .build()));
//
//        usuarioEmpresaRepository.saveAll(
//                List.of(
//                        UsuarioEmpresa.builder()
//                                .empresa(empresaRepository.findById(1L).orElse(null))
//                                .usuario(usuarioRepository.findById(1L).orElse(null))
//                                .build(),
//                        UsuarioEmpresa.builder()
//                                .empresa(empresaRepository.findById(2L).orElse(null))
//                                .usuario(usuarioRepository.findById(2L).orElse(null))
//                                .build()));
    }
}
