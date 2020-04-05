package com.laudoecia.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.laudoecia.api.domain.enuns.TIPO_LICENCA;

@Entity
@Table
public class Licenciado implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String cnpj;
	private String cnes;
	private String cpf;
	private String licenciadopara;
	private String razaosocial;	
	private Endereco endereco;	
	private String telefone1;	
	private String telefone2;	
	private String email;	
	private String site;
	private String serial = "";
	private int qtdeacessos = 2;	
	private TIPO_LICENCA tipodelicenca = TIPO_LICENCA.LAUDO_E_IMAGEM;	
	private long version = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(length = 14)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Column(length = 7)
	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	@Column(length = 11)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLicenciadopara() {
		return licenciadopara;
	}

	public void setLicenciadopara(String licenciadopara) {
		this.licenciadopara = licenciadopara;
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	@Embedded
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Column(length = 15)
	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	@Column(length = 15)
	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	@Column(length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 50)
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Column(name = "serial", unique = true)
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	@Column(name = "qtdeacessos")
	public int getQtdeacessos() {
		return qtdeacessos;
	}
	
	public void setQtdeacessos(int qtdeacessos) {
		this.qtdeacessos = qtdeacessos;
	}

	@Enumerated(EnumType.STRING)
	public TIPO_LICENCA getTipodelicenca() {
		return tipodelicenca;
	}

	public void setTipodelicenca(TIPO_LICENCA tipodelicenca) {
		this.tipodelicenca = tipodelicenca;
	}

	@Version
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Licenciado other = (Licenciado) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
