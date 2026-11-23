package br.com.software.api_contatos.controller;

import br.com.software.api_contatos.entities.Contato;
import br.com.software.api_contatos.repositories.ContatoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/contatos")
public class ContatoController {

    @PostMapping
    public ResponseEntity<String> post (@RequestBody Map<String, String> dados) {
        var contato = new Contato();

        contato.setNome(dados.get("nome"));
        contato.setEmail(dados.get("email"));
        contato.setTelefone(dados.get("telefone"));

        try {
            var contatoRepository = new ContatoRepository();

            contatoRepository.inserir(contato);

            return ResponseEntity.ok("Contato cadastrado com sucesso.");
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            var contatoRepository = new ContatoRepository();
            var contatos = contatoRepository.obterTodos();

            return ResponseEntity.ok(contatos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("{idContato}")
    public ResponseEntity<?> getById(@PathVariable Integer idContato) {
        try {
            var contatoRepository = new ContatoRepository();
            var contato = contatoRepository.obterPorid(idContato);

            if (contato == null) return ResponseEntity.status(404).body("Contato não encotrado.");

            return ResponseEntity.ok(contato);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody Map<String, String> dados) {
        var contato = new Contato();

        contato.setIdContato(Integer.valueOf(dados.get("idContato")));
        contato.setNome(dados.get("nome"));
        contato.setEmail(dados.get("email"));
        contato.setTelefone(dados.get("telefone"));

        try {
            var contatoRepository = new ContatoRepository();

            if (contatoRepository.atualizar(contato)) return ResponseEntity.ok("Contato atualizado com sucesso.");

            return ResponseEntity.status(404).body("Contato não encontrado para edição.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("{idContato}")
    public ResponseEntity<?> delete(@PathVariable Integer idContato) {
        try {
            var contatoRepository = new ContatoRepository();

            if (contatoRepository.excluir(idContato)) return ResponseEntity.ok("Contato excluído com sucesso.");

            return ResponseEntity.status(404).body("Contato não encontrado para exclusão.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
