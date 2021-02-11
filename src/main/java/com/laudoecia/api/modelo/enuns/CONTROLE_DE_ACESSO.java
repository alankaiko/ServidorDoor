package com.laudoecia.api.modelo.enuns;

public enum CONTROLE_DE_ACESSO {
	SEM_CONTROLE("SEM CONTROLE", Descricao.SEM_CONTROLE),
	CONFORME_USUARIO_LOGADO("CONFORME USUÁRIO LOGADO", Descricao.CONFORME_USUARIO_LOGADO),
	SEMPRE_EXIGE_SENHA("SEMPRE EXIGE SENHA", Descricao.SEMPRE_EXIGE_SENHA);

	private final String controle;
	private final String descricao;

	private CONTROLE_DE_ACESSO(String controle, String descricao) {
		this.controle = controle;
		this.descricao = descricao;
	}

	public String getControle() {
		return controle;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return controle;
	}

	private class Descricao {
		private static final String SEM_CONTROLE = "<html><body><div align=\"justify\"><font size=\"2\"><b>SEM CONTROLE - INFORMAÇÔES</b><BR /><BR />Neste tipo de controle de acesso, não há controle de acesso nas rotinas e operações do sistema.<BR />Indicado para locais onde não há necessidade de ter auditoria de ações.<BR /><BR />Ao abrir o sistema não será exigido dados de acesso do usuário. Qualquer pessoa que fizer a execução do sistema terá total controle (adicionar, alterar, apagar e consultar) sobre as rotinas e operações.<BR /><BR /><b>Obs.:</b> É necessário recarregar o sistema para que as alterações tenham efeito.</font></div></body></html>";
		private static final String SEMPRE_EXIGE_SENHA = "<html><body><div align=\"justify\"><font size=\"2\"><b>SEMPRE EXIGE SENHA - INFORMAÇÔES</b><BR /><BR />Neste tipo de controle de acesso, o controle é feito de acordo com a configuração de cada usuário. A diferença do tipo CONFORME USUÁRIO LOGADO é que em toda ação do usuário será exigida a senha.<BR />Indicado para locais onde a mesma estação de trabalho é utilizada por mais de um usuário ao mesmo tempo.<BR /><BR />Esta opção habilita os cadastros de usuário, grupo de usuários e auditoria de ações. Ao abrir o sistema será exigido os dados de acesso do usuário (login e senha). O controle de ações do usuário (adicionar, alterar, apagar e consultar) sobre as rotinas e operações do sistema é definido pelo grupo de usuário em que o mesmo está configurado.<BR /><BR />Para o primeiro acesso utilizar o usuário <b>ADM</b> sem senha. Para garantir maior segurança, é importante que, após definir os grupos de usuários e usuários do sistema, você altere o nome e senha deste usuário.<BR /><BR /><b>Obs.:</b> É necessário recarregar o sistema para que as alterações tenham efeito.</font></div></body></html>";
		private static final String CONFORME_USUARIO_LOGADO = "<html><body><div align=\"justify\"><font size=\"2\"><b>CONFORME USUÁRIO LOGADO - INFORMAÇÔES</b><BR /><BR />Neste tipo de controle de acesso, o controle é feito de acordo com a configuração de cada usuário.<BR />Indicado para locais onde o sistema é acessado por vários usuários.<BR /><BR />Esta opção habilita os cadastros de usuário, grupo de usuários e auditoria de ações. Ao abrir o sistema será exigido os dados de acesso do usuário (login e senha). O controle de ações do usuário (adicionar, alterar, apagar e consultar) sobre as rotinas e operações do sistema é definido pelo grupo de usuário em que o mesmo está configurado.<BR /><BR />Para o primeiro acesso utilizar o usuário <b>ADM</b> sem senha. Para garantir maior segurança, é importante que, após definir os grupos de usuários e usuários do sistema, você altere o nome e senha deste usuário.<BR /><BR /><b>Obs.:</b> É necessário recarregar o sistema para que as alterações tenham efeito.</font></div></body></html>";
	}
}
