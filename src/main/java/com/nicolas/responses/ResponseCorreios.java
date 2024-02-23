package com.nicolas.responses;

public class ResponseCorreios {

   private String cep;
   private String uf;
   private Long numeroLocalidade;
   private String localidade;
   private String logradouro;
   private String tipoLogradouro;
   private String nomeLogradouro;
   private String complemento;
   private String bairro;
   private Integer tipoCEP;
   private String cepUnidadeOperacional;
   private String lado;
   private Integer numeroInicial;
   private Integer numeroFinal;

    public ResponseCorreios() {
    }

    public ResponseCorreios(String cep, String uf, Long numeroLocalidade, String localidade,
                            String logradouro, String tipoLogradouro, String nomeLogradouro,
                            String complemento, String bairro, Integer tipoCEP,
                            String cepUnidadeOperacional, String lado, Integer numeroInicial, Integer numeroFinal) {
        this.cep = cep;
        this.uf = uf;
        this.numeroLocalidade = numeroLocalidade;
        this.localidade = localidade;
        this.logradouro = logradouro;
        this.tipoLogradouro = tipoLogradouro;
        this.nomeLogradouro = nomeLogradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.tipoCEP = tipoCEP;
        this.cepUnidadeOperacional = cepUnidadeOperacional;
        this.lado = lado;
        this.numeroInicial = numeroInicial;
        this.numeroFinal = numeroFinal;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Long getNumeroLocalidade() {
        return numeroLocalidade;
    }

    public void setNumeroLocalidade(Long numeroLocalidade) {
        this.numeroLocalidade = numeroLocalidade;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getTipoCEP() {
        return tipoCEP;
    }

    public void setTipoCEP(Integer tipoCEP) {
        this.tipoCEP = tipoCEP;
    }

    public String getCepUnidadeOperacional() {
        return cepUnidadeOperacional;
    }

    public void setCepUnidadeOperacional(String cepUnidadeOperacional) {
        this.cepUnidadeOperacional = cepUnidadeOperacional;
    }

    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public Integer getNumeroInicial() {
        return numeroInicial;
    }

    public void setNumeroInicial(Integer numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public Integer getNumeroFinal() {
        return numeroFinal;
    }

    public void setNumeroFinal(Integer numeroFinal) {
        this.numeroFinal = numeroFinal;
    }
}
