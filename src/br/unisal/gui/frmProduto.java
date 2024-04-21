package br.unisal.gui;

import java.awt.BorderLayout;

/*
 * Atividade avaliação Paulo Barreto
 * @autor Caio Rodrigues da Silva
 * @data 21/04/2024
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class frmProduto extends JFrame {
	private JTextField txtCodigo, txtDescricao, txtLargura, txtComprimento, txtLocalizacao;
	private JComboBox<String> cbUnidade, cbSituacao;
	private JButton btnIncluir, btnAlterar, btnExcluir, btnPesquisar, btnLimpar;
	private HashMap<String, String[]> produtos;

	public frmProduto() {
		setTitle("Cadastro de Produtos");
		setSize(540, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		produtos = new HashMap<>();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(10, 3));

		panel.add(new JLabel("Código do Produto:"));
		txtCodigo = new JTextField();
		panel.add(txtCodigo);

		panel.add(new JLabel("Descricão:"));
		txtDescricao = new JTextField();
		panel.add(txtDescricao);

		panel.add(new JLabel("Unidade de Medida:"));
		cbUnidade = new JComboBox<>(new String[] { "Unidade", "Kg", "g", "L", "mL" });
		panel.add(cbUnidade);

		panel.add(new JLabel("Largura:"));
		txtLargura = new JTextField();
		panel.add(txtLargura);

		panel.add(new JLabel("Comprimento:"));
		txtComprimento = new JTextField();
		panel.add(txtComprimento);

		panel.add(new JLabel("Situação:"));
		cbSituacao = new JComboBox<>(new String[] { "Ativo", "Inativo", "Pendente" });
		panel.add(cbSituacao);

		panel.add(new JLabel("Localização:"));
		txtLocalizacao = new JTextField();
		panel.add(txtLocalizacao);

		btnIncluir = new JButton("Incluir");
		btnAlterar = new JButton("Alterar");
		btnExcluir = new JButton("Excluir");
		btnPesquisar = new JButton("Pesquisar");
		btnLimpar = new JButton("Limpar");

		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarProduto();
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirProduto();
			}
		});

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProduto();
			}
		});

		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});

		JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		buttonPanel.add(btnIncluir);
		buttonPanel.add(btnAlterar);
		buttonPanel.add(btnExcluir);
		buttonPanel.add(btnPesquisar);
		buttonPanel.add(btnLimpar);

		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void cadastrarProduto() {
		String codigo = txtCodigo.getText();
		String descricao = txtDescricao.getText();
		String unidade = (String) cbUnidade.getSelectedItem();
		String largura = txtLargura.getText();
		String comprimento = txtComprimento.getText();
		String situacao = (String) cbSituacao.getSelectedItem();
		String localizacao = txtLocalizacao.getText();

		String[] produto = { descricao, unidade, largura, comprimento, situacao, localizacao };
		produtos.put(codigo, produto);

		JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
		limparCampos();
	}

	private void pesquisarProduto() {
		String codigo = txtCodigo.getText();
		String[] produto = produtos.get(codigo);
		if (produto != null) {
			txtDescricao.setText(produto[0]);
			cbUnidade.setSelectedItem(produto[1]);
			txtLargura.setText(produto[2]);
			txtComprimento.setText(produto[3]);
			cbSituacao.setSelectedItem(produto[4]);
			txtLocalizacao.setText(produto[5]);
		} else {
			JOptionPane.showMessageDialog(this, "Produto não encontrado! ");
		}
	}

	private void excluirProduto() {
		String codigo = txtCodigo.getText();
		if (produtos.containsKey(codigo)) {
			produtos.remove(codigo);
			JOptionPane.showMessageDialog(this, "Produto excluido com sucesso!");
			limparCampos();
		} else {
			JOptionPane.showMessageDialog(this, "Produto não encontrado para excluir!");
		}
	}

	private void limparCampos() {
		txtCodigo.setText("");
		txtDescricao.setText("");
		txtLargura.setText("");
		txtComprimento.setText("");
		txtLocalizacao.setText("");
	}

	public static void main(String[] args) {
		new frmProduto();
	}
}