package model;

import java.sql.*;
import java.util.*;

public class LivroDAO {
	public void salvar(Livro livro) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO Livro (titulo, autor, genero, idioma, quantidade, preco) VALUES (?, ?, ?, ?, ?, ?";
	
		try {
			conn = Conexao.conectar();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, livro.getTitulo());
	        stmt.setString(2, livro.getAutor()); 
	        stmt.setString(3, livro.getGenero());
	        stmt.setString(4, livro.getIdioma());
	        stmt.setString(5, livro.getQuantidade());
	        stmt.setString(6, livro.getPreco());
	        stmt.executeUpdate();
		 } catch (Exception e) {
			 throw new RuntimeException("Erro ao cadastrar livro: " + e.getMessage());
		 } finally {
	            try {
	                if (stmt != null) {
	                    stmt.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
		public static void atualizar(Livro livro) {
			 Connection conn = null;
		        PreparedStatement stmt = null; 

		        String sql = "UPDATE livro SET titulo = ?, autor = ?, genero = ?, idioma = ?, quantidade = ?, preco = ? WHERE id = ?";

				try {
					conn = Conexao.conectar();
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, livro.getTitulo());
			        stmt.setString(2, livro.getAutor());
			        stmt.setString(3, livro.getGenero());
			        stmt.setString(4, livro.getIdioma());
			        stmt.setString(5, livro.getQuantidade());
			        stmt.setString(6, livro.getPreco());
			        stmt.executeUpdate();
				 } catch (Exception e) {
					 throw new RuntimeException("Erro ao atualizar livro: " + e.getMessage());
				 } finally {
			            try {
			                if (stmt != null) {
			                    stmt.close();
			                }
			                if (conn != null) {
			                    conn.close();
			                }
			            } catch (Exception e) {
			                e.printStackTrace();
			            }
			        }
			    }
		
		public void excluir (int id) {
			Connection conn = null;
	        PreparedStatement stmt = null;
	        
	        String sql = "DELETE FROM livro WHERE id = ?";
	        
	        try {
	            conn = Conexao.conectar();
	            stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, id);
	            stmt.executeUpdate();
	        } catch (Exception e) {
	            throw new RuntimeException("Erro ao apagar Livro: " + e.getMessage());
	        } finally {
	            try {
	                if (stmt != null) {
	                    stmt.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
		}
		
		public List<Livro> listar() {
	        List<Livro> lista = new ArrayList<Livro>();

	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        String sql = "SELECT id, titulo, autor, genero, idioma, quantidade, preco FROM Livro ORDER BY id DESC";

	        try {
	            conn = Conexao.conectar();
	            stmt = conn.prepareStatement(sql);
	            rs = stmt.executeQuery();

	            while (rs.next()) {
	            	Livro livro = new Livro();
	            	livro.setId(rs.getInt("id"));
	                livro.setTitulo(rs.getString("Título"));
	                livro.setAutor(rs.getString("Autor"));
	                livro.setGenero(rs.getString("Gênero"));
	                livro.setIdioma(rs.getString("Idioma"));
	                livro.setQuantidade(rs.getString("Quantidade"));
	                livro.setPreco(rs.getString("Preço"));
	                lista.add(livro);
	            }

	        } catch (Exception e) {
	            throw new RuntimeException("Erro ao listar livros: " + e.getMessage());
	        } finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (stmt != null) {
	                    stmt.close();
	                } 
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
			return lista;
		}
}
		
		

	
	
	
	

