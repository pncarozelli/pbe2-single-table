package com.senai.escola.service;

import com.senai.escola.entity.Aluno;
import com.senai.escola.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Na classe AlunoService, inserimos as nossa regras de negócio, validações,
//consistência dos dados e tomadas de decisões antes da persistência
@Service
public class AlunoService {
    @Autowired //faz a injeção de dependência
    private AlunoRepository alunoRepository;

    @Transactional
    //a anotação @Transactionl faz a gestão da transação no banco (abre e finaliza)
    //e juntamente com o Hibernate, permite o "Dirty Checking" que, grosso modo,
    //liga o "modo gravação".
    public void adicionar(Aluno aluno){
        //valida a idade do aluno a ser inserido no banco
        if (aluno.getIdade() < 14){
            throw new RuntimeException("Idade abaixo do mínimo de 14 anos");
        } else {
            //o método save() já é implementado em JpaRepository
            this.alunoRepository.save(aluno);
        }
    }

    //não há necessidade da anotação @Transactional pois não há alteração no banco
    public List<Aluno> listar(){
        //o método findAll() já é implementado em JpaRepository
        return this.alunoRepository.findAll();
    }

    @Transactional
    public void atualizar(Long id, Aluno aluno){
        // orElseThrow lança uma excessão caso o objeto não seja encontrado
        // e encerra a execução do método, ou seja, não vai executar nada
        // que está abaixo da linha em que se encontra. O Spring é quem
        // devolve o erro automaticamente
        Aluno alunoAtualizado = this.alunoRepository.findById(id).orElseThrow();

        // se a execução chegou a esse ponto, faz as atualizações.
        // Por termos utilizado o @Transactional, o salvamento
        // das alterações acontece automaticamente no banco
        alunoAtualizado.setNome(aluno.getNome());
        alunoAtualizado.setSexo(aluno.getSexo());
        alunoAtualizado.setIdade(aluno.getIdade());
    }

        @Transactional
        public void remover(Long id){
            // verifica se o aluno existe no banco. Caso não exista,
            // o Spring automaticamente devolve um erro.
            Aluno alunoExistente = this.alunoRepository.findById(id).orElseThrow();

            this.alunoRepository.deleteById(id);
        }
}
