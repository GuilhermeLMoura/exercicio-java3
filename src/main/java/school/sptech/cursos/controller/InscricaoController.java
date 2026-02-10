package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.cursos.entity.Inscricao;
import school.sptech.cursos.service.InscricaoService;

import java.util.List;

@RestController
@RequestMapping("/inscricoes")
@RequiredArgsConstructor
public class InscricaoController {

    private final InscricaoService inscricaoService;
    @GetMapping
    public ResponseEntity<List<Inscricao>> findAll() {
        List<Inscricao> inscricoes = inscricaoService.findAll();
        if(inscricoes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(inscricoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> findById(@PathVariable Integer id) {
        Inscricao inscricao = inscricaoService.findById(id);
        if (inscricao == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(inscricao);
    }

    @PostMapping("/cursos/{idCurso}")
    public ResponseEntity<Inscricao> save(
            @PathVariable Integer idCurso,
            @RequestBody @Valid Inscricao inscricao
    ) {
        Inscricao novaInscricao = inscricaoService.save(inscricao,idCurso);
        if(novaInscricao == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(null).body(novaInscricao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Inscricao inscricao = inscricaoService.findById(id);
        inscricaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
