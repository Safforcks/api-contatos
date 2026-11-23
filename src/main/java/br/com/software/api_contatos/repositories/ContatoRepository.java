package br.com.software.api_contatos.repositories;

import br.com.software.api_contatos.entities.Contato;
import br.com.software.api_contatos.factories.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;

public class ContatoRepository {

    public void inserir(Contato contato) throws Exception {
        var factory = new ConnectionFactory();

        try (var connection = factory.createConnection()) {
            var sql = "INSERT INTO contatos (nome, email, telefone) VALUES (?, ?, ?)";

            var statement = connection.prepareStatement(sql);

            statement.setString(1, contato.getNome());
            statement.setString(2, contato.getEmail());
            statement.setString(3, contato.getTelefone());
            statement.execute();
        }
    }

    public List<Contato> obterTodos() throws Exception {
        var factory = new ConnectionFactory();

        try (var connection = factory.createConnection()) {
            var sql = "SELECT idcontato, nome, email, telefone FROM contatos WHERE ativo = 1";

            var statement = connection.prepareStatement(sql);
            var result = statement.executeQuery();

            var lista = new ArrayList<Contato>();

            while (result.next()) {
                var contato = new Contato();

                contato.setIdContato(result.getInt("idcontato"));
                contato.setNome(result.getString("nome"));
                contato.setEmail(result.getString("email"));
                contato.setTelefone(result.getString("telefone"));

                lista.add(contato);
            }

            return lista;
        }
    }

    public Contato obterPorid(Integer idContato) throws Exception {
        var factory = new ConnectionFactory();

        try (var connection = factory.createConnection()) {
            var sql = "SELECT idcontato, nome, email, telefone FROM contatos WHERE idcontato = ? AND ativo = 1";

            var statement = connection.prepareStatement(sql);

            statement.setInt(1, idContato);
            var result = statement.executeQuery();

            if (result.next()) {
                var contato = new Contato();

                contato.setIdContato(result.getInt("idcontato"));
                contato.setNome(result.getString("nome"));
                contato.setEmail(result.getString("email"));
                contato.setTelefone(result.getString("telefone"));

                return contato;
            }

            return null;
        }
    }

    public boolean atualizar(Contato contato) throws Exception{
        var factory = new ConnectionFactory();

        try (var connection = factory.createConnection()) {
            var sql = "UPDATE contatos SET nome = ?, email = ?, telefone = ? WHERE idcontato = ? AND ativo = 1";

            var statement = connection.prepareStatement(sql);

            statement.setString(1, contato.getNome());
            statement.setString(2, contato.getEmail());
            statement.setString(3, contato.getTelefone());
            statement.setInt(4, contato.getIdContato());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean excluir(Integer idContato) throws Exception {
        var factory = new ConnectionFactory();

        try (var connection = factory.createConnection()) {
            var sql = "UPDATE contatos SET ativo = 0 WHERE idcontato = ? AND ativo = 1";

            var statement = connection.prepareStatement(sql);

            statement.setInt(1, idContato);

            return statement.executeUpdate() > 0;
        }
    }
}
