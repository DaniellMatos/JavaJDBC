package contoller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Livro;
import model.DAO.LivroDAO;
import model.bean.Vendedor;

public class LivroController {
    private Livro livroSelecionado;
    private List<Livro> tabelaDelivros;
    private LivroDAO lDAO;

    public LivroController() {
        lDAO = new LivroDAO();
    }

    public void listarTodos(DefaultTableModel modeloTabela) {
        modeloTabela.setNumRows(0);
        List<Livro> listaLivros = lDAO.buscarTodos();

        for (Livro l : listaLivros) {
            modeloTabela.addRow(new Object[]{l.getId(), l.getTitulo(),
                l.getISBN(), l.getAutor(), l.getPaginas(), l.getPreco(), l.getCategoria(),
                l.isStatus()});
        }
    }
        public void listarPorId(DefaultTableModel modeloTabela, int id) {
        modeloTabela.setNumRows(0);
        Livro livroBuscado = lDAO.buscarPorId(id);

        modeloTabela.addRow(new Object[]{livroBuscado.getId(), livroBuscado.getTitulo(),
            livroBuscado.getISBN(), livroBuscado.getAutor(), livroBuscado.getPaginas(), livroBuscado.getPreco(),
            livroBuscado.getCategoria(), livroBuscado.isStatus()});
    }
     public void listarPorISBN(DefaultTableModel modeloTabela, int ISBN) {
        modeloTabela.setNumRows(0);
        Livro livroBuscado = lDAO.buscarPorISBN(ISBN);

        modeloTabela.addRow(new Object[]{livroBuscado.getId(), livroBuscado.getTitulo(),
            livroBuscado.getISBN(), livroBuscado.getAutor(), livroBuscado.getPaginas(), livroBuscado.getPreco(),
            livroBuscado.getCategoria(), livroBuscado.isStatus()});
    }

    public void salvar(DefaultTableModel modeloTabela, Livro livro, boolean novo) {
        if (novo) {
            lDAO.inserir(livro);
        } else {
            lDAO.atualizar(livro);
        }
        this.listarTodos(modeloTabela);
    }

    public void excluir(DefaultTableModel modeloTabela, Livro livro) {
        System.out.println("Excluindo livro No.: " + livro.getId());
        if (livro.getId() != 0) {
            lDAO.excluir(livro.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível excluir .\nLivro não foi localizado.", "Erro ao excluir o livro", JOptionPane.ERROR_MESSAGE);
        }
        this.listarTodos(modeloTabela);
    }

    public void listarPorISBN(DefaultTableModel tabelaModelo, String ISBN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
