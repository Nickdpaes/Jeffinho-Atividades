package view;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.LivroController;
public class TelaCadastro extends JFrame {
	private static final long serialVersionUID = 1L;
	public JLabel lblID;
	public JLabel lblTitulo;
	public JLabel lblAutor;
	public JLabel lblGenero;
	public JLabel lblIdioma;
	public JLabel lblQuantidade;
	public JLabel lblPreco;
	
	public JTextField txtID;
	public JTextField txtTitulo;
	public JTextField txtAutor;
	public JTextField txtGenero;
	public JTextField txtIdioma;
	public JTextField txtQuantidade;
	public JTextField txtPreco;
	
	public JButton btnNovo;
	public JButton btnAdicionar;
	public JButton btnExcluir;
	public JButton btnLimpar;
	
	private JTable tabelaLivro;
	private DefaultTableModel modeloTabelinha;
	private LivroController Controller;
	
	public TelaCadastro () {
		setTitle("Cadastro de Livros");
		setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        criarComponentes();

        Controller = new LivroController(this); 
        configurarEventos();
        Controller.carregarTabela();
	}
	private void criarComponentes() {
        JPanel painelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Ficha técnica"));
	
        lblID = new JLabel("ID:");
        txtID = new JTextField();
        txtID.setEditable(false);
        
        lblTitulo = new JLabel("Titulo:");
        txtTitulo = new JTextField();
	
        lblAutor = new JLabel("Autor:");
        txtAutor = new JTextField();

        lblGenero = new JLabel("Gênero:");
        txtGenero = new JTextField();
        
        lblIdioma = new JLabel("Idioma:");
        txtIdioma = new JTextField();

        lblQuantidade = new JLabel("Quantidade:");
        txtQuantidade = new JTextField();

        lblPreco = new JLabel("Preço");
        txtPreco = new JTextField();

        painelFormulario.add(lblID);
        painelFormulario.add(txtID);
        painelFormulario.add(lblTitulo);
        painelFormulario.add(txtTitulo);
        painelFormulario.add(lblAutor);
        painelFormulario.add(txtAutor);
        painelFormulario.add(lblGenero);
        painelFormulario.add(txtGenero);
        painelFormulario.add(lblIdioma);
        painelFormulario.add(txtIdioma);
        painelFormulario.add(lblQuantidade);
        painelFormulario.add(txtQuantidade);
        painelFormulario.add(lblPreco);
        painelFormulario.add(txtPreco);
        
        add(painelFormulario, BorderLayout.NORTH);
        
        modeloTabelinha = new DefaultTableModel(new Object[] { "ID","Título", "Autor", "Gênero", "Quantidade", "Preço"}, 0) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaLivro = new JTable(modeloTabelinha);
        JScrollPane scrollPane = new JScrollPane(tabelaLivro);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnNovo = new JButton("Novo");
        btnAdicionar = new JButton("Salvar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        add(painelBotoes, BorderLayout.SOUTH);

		}
	
    private void configurarEventos() {
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Controller.limpar();
            }
        });

        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Controller.salvar();
            }
        });

        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Controller.excluir();
            }
        });

        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Controller.limpar();
            }
        });

        tabelaLivro.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Controller.preencherFormulario();
            }
        });
    }
	public JTextField getTxtID() {
		return txtID;
	}
	public void setTxtID(JTextField txtID) {
		this.txtID = txtID;
	}
	public JTextField getTxtTitulo() {
		return txtTitulo;
	}
	public void setTxtTitulo(JTextField txtTitulo) {
		this.txtTitulo = txtTitulo;
	}
	public JTextField getTxtAutor() {
		return txtAutor;
	}
	public void setTxtAutor(JTextField txtAutor) {
		this.txtAutor = txtAutor;
	}
	public JTextField getTxtGenero() {
		return txtGenero;
	}
	public void setTxtGenero(JTextField txtGenero) {
		this.txtGenero = txtGenero;
	}
	public JTextField getTxtIdioma() {
		return txtIdioma;
	}
	public void setTxtIdioma(JTextField txtIdioma) {
		this.txtIdioma = txtIdioma;
	}
	public JTextField getTxtQuantidade() {
		return txtQuantidade;
	}
	public void setTxtQuantidade(JTextField txtQuantidade) {
		this.txtQuantidade = txtQuantidade;
	}
	public JTextField getTxtPreco() {
		return txtPreco;
	}
	public void setTxtPreco(JTextField txtPreco) {
		this.txtPreco = txtPreco;
	}
	public JTable getTabelaLivro() {
		return tabelaLivro;
	}
	public void setTabelaLivro(JTable tabelaLivro) {
		this.tabelaLivro = tabelaLivro;
	}
}
	