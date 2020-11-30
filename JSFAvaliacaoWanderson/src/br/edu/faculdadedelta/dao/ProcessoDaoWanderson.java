package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.ProcessoWanderson;
import br.edu.faculdadedelta.util.Conexao;

public class ProcessoDaoWanderson {
	
	public void incluir (ProcessoWanderson proc) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "INSERT INTO processos("
				+ "	assunto_proc, numero_proc, interessado_proc, data_autuacao_proc, valor_proc)"
				+ "	VALUES (?, ?, ?, ?, ?);";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, proc.getAssunto().trim());
				ps.setString(2, proc.getNumeroProc().trim());
				ps.setString(3, proc.getInteressado().trim());
				ps.setDate(4, new java.sql.Date(proc.getDataAut().getTime()));
				ps.setDouble(5, proc.getValorProc());

				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void alterar (ProcessoWanderson proc) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "UPDATE public.processos"
				+ "	SET assunto_proc=?, numero_proc=?, interessado_proc=?, data_autuacao_proc=?, valor_proc=?"
				+ "	WHERE id_proc=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, proc.getAssunto().trim());
				ps.setString(2, proc.getNumeroProc().trim());
				ps.setString(3, proc.getInteressado().trim());
				ps.setDate(4, new java.sql.Date(proc.getDataAut().getTime()));
				ps.setDouble(5, proc.getValorProc());
				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void excluir (ProcessoWanderson proc) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql ="DELETE FROM processos"
				+ "	WHERE id_proc=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, proc.getId());
		
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
	}
	
	public static List <ProcessoWanderson> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "SELECT id_proc, assunto_proc, numero_proc, interessado_proc, data_autuacao_proc, valor_proc"
				+ "	FROM public.processos";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<ProcessoWanderson> listaRetorno = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			ProcessoWanderson proc = new ProcessoWanderson();
			proc.setId(rs.getLong("id_proc"));
			proc.setAssunto(rs.getString("assunto_proc").trim());
			proc.setNumeroProc(rs.getString("numero_proc").trim());
			proc.setInteressado(rs.getString("interessado_proc").trim());
			proc.setDataAut(rs.getDate("data_autuacao_proc"));
			proc.setValorProc(rs.getDouble("valor_proc"));


			listaRetorno.add(proc);
			
		}
		Conexao.fecharConexao(ps, conn, rs);
		
		return listaRetorno;
	}
	
	
}
