package br.com.sistema.dao;

import br.com.sistema.util.Conexao;
import br.com.sistema.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO (Data Access Object) concentra os comandos SQL de Cliente.
 * Connection representa a conexao, PreparedStatement envia SQL parametrizado
 * e ResultSet permite percorrer as linhas devolvidas por um SELECT.
 */
public class ClienteDAO {

  public void salvar(Cliente cliente) throws SQLException {
    String sql =
      "INSERT INTO cliente (NOME, CPF, EMAIL, DATA_NASCIMENTO, SENHA, ATIVO) VALUES (?, ?, ?, ?, ?, ?)";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preencher(stmt, cliente, false);
      stmt.executeUpdate();
      ResultSet chaves = null;
      try {
        chaves = stmt.getGeneratedKeys();
        if (chaves.next()) cliente.setId(chaves.getInt(1));
      } finally {
        Conexao.fechar(chaves);
      }
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public void atualizar(Cliente cliente) throws SQLException {
    String sql =
      "UPDATE cliente SET NOME=?, CPF=?, EMAIL=?, DATA_NASCIMENTO=?, SENHA=?, ATIVO=? WHERE ID=?";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql);
      preencher(stmt, cliente, true);
      if (stmt.executeUpdate() == 0) throw new SQLException(
        "Cliente nao encontrado."
      );
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  /** Preserva o historico: excluir um cliente significa inativa-lo. */
  public void excluir(int id) throws SQLException {
    alterarAtivo(id, false);
  }

  public void alterarAtivo(int id, boolean ativo) throws SQLException {
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement("UPDATE cliente SET ATIVO=? WHERE ID=?");
      stmt.setBoolean(1, ativo);
      stmt.setInt(2, id);
      if (stmt.executeUpdate() == 0) throw new SQLException(
        "Cliente nao encontrado."
      );
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public Cliente buscarPorId(int id) throws SQLException {
    List<Cliente> lista = consultar(
      "SELECT * FROM cliente WHERE ID=?",
      Integer.valueOf(id)
    );
    return lista.isEmpty() ? null : lista.get(0);
  }

  public List<Cliente> buscarPorNome(String nome) throws SQLException {
    return consultar(
      "SELECT * FROM cliente WHERE TRIM(NOME) LIKE ? ORDER BY NOME",
      "%" + nome.trim() + "%"
    );
  }

  public List<Cliente> listarTodos() throws SQLException {
    return consultar("SELECT * FROM cliente ORDER BY NOME", null);
  }

  public List<Cliente> listarAtivos() throws SQLException {
    return consultar("SELECT * FROM cliente WHERE ATIVO=1 ORDER BY NOME", null);
  }

  private List<Cliente> consultar(String sql, Object parametro)
    throws SQLException {
    List<Cliente> lista = new ArrayList<Cliente>();
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql);
      if (parametro instanceof Integer) stmt.setInt(
        1,
        ((Integer) parametro).intValue()
      );
      if (parametro instanceof String) stmt.setString(1, (String) parametro);
      rs = stmt.executeQuery();
      while (rs.next()) lista.add(mapear(rs));
      return lista;
    } finally {
      Conexao.fechar(conexao, stmt, rs);
    }
  }

  private Cliente mapear(ResultSet rs) throws SQLException {
    Cliente c = new Cliente();
    c.setId(rs.getInt("ID"));
    c.setNome(rs.getString("NOME"));
    c.setCpf(rs.getString("CPF"));
    c.setEmail(rs.getString("EMAIL"));
    c.setDdn(rs.getDate("DATA_NASCIMENTO"));
    c.setSenha(rs.getString("SENHA"));
    c.setAtivo(rs.getBoolean("ATIVO"));
    return c;
  }

  private void preencher(PreparedStatement stmt, Cliente c, boolean atualizacao)
    throws SQLException {
    stmt.setString(1, c.getNome().trim());
    stmt.setString(2, c.getCpf().trim());
    stmt.setString(3, c.getEmail());
    if (atualizacao) {
      stmt.setBoolean(4, c.isAtivo());
      stmt.setInt(5, c.getId());
    } else {
      Date data =
        c.getDdn() == null ? new Date() : c.getDdn();
      stmt.setDate(4, new java.sql.Date(data.getTime()));
      stmt.setBoolean(5, c.isAtivo());
    }
  }
}
