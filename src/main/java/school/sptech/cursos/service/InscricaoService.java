package school.sptech.cursos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.cursos.entity.Curso;
import school.sptech.cursos.entity.Inscricao;
import school.sptech.cursos.repository.InscricaoRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final CursoService cursoService;

    public List<Inscricao> findAll() {
        return inscricaoRepository.findAll();
    }

    public Inscricao findById(Integer id) {
        return inscricaoRepository.findById(id)
                .orElse(null);
    }

    public Inscricao save(Inscricao inscricao, Integer idCurso) {
        Curso curso = cursoService.findById(idCurso);
        if(curso == null){
            return null;
        }
        if(inscricaoRepository.existsByEmailIgnoreCase(inscricao.getEmail())){
            return null;
        }

        if( inscricao.getDataNascimento().plusYears(18).isAfter(inscricao.getDataInscricao())){
            return null;
        }

        inscricao.setCurso(curso);
        return inscricaoRepository.save(inscricao);
    }

    public void delete(Integer id) {
        inscricaoRepository.deleteById(id);
    }
}
