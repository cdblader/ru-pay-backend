package rupay.api.servico;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rupay.api.modelo.RespostaModelo;
import rupay.api.modelo.ClienteModelo;
import rupay.api.repositorio.ClienteRepositorio;

@Service
public class ClienteServico {
    
    @Autowired
    private ClienteRepositorio repo;

    @Autowired
    private RespostaModelo resp;

    //Cadastrar e Editar Clientes
    public ResponseEntity<?> cadastrarAlterar(ClienteModelo model,String acao){
        if(model.getLogin() == null || model.getLogin().equals("")){
            resp.setMensagem("O login é obrigatório");
            return new ResponseEntity<RespostaModelo>(resp,HttpStatus.BAD_REQUEST);
        }else if(model.getSenha() == null || model.getSenha().equals("")){
            resp.setMensagem("A senha é obrigatória");
            return new ResponseEntity<RespostaModelo>(resp,HttpStatus.BAD_REQUEST);
        }else if(model.getEmail() == null || model.getEmail().equals("")){
            resp.setMensagem("O email é obrigatório");
            return new ResponseEntity<RespostaModelo>(resp,HttpStatus.BAD_REQUEST);
        }else if(model.getMatricula() == null || model.getMatricula().equals("")){
            resp.setMensagem("A matrícula é obrigatória");
            return new ResponseEntity<RespostaModelo>(resp,HttpStatus.BAD_REQUEST);
        }else if(model.getNome() == null || model.getNome().equals("")){
            resp.setMensagem("O nome é obrigatório");
            return new ResponseEntity<RespostaModelo>(resp,HttpStatus.BAD_REQUEST);
        }else{
            if(acao.equals("cadastrar")){
                return new ResponseEntity<ClienteModelo>(repo.save(model),HttpStatus.CREATED);
            } else{
                return new ResponseEntity<ClienteModelo>(repo.save(model),HttpStatus.OK);
            }
        }
    }

    //Listar Clientes
    public Iterable<ClienteModelo> listar(){
        return repo.findAll();
    }

    //Buscar Clientes
    public Optional<ClienteModelo> buscar(long id){
        return repo.findById(id);
    }

    //Remover Clientes
    public ResponseEntity<RespostaModelo> remover(long id){

        if (repo.findById(id).isPresent()) {
            repo.deleteById(id);
            resp.setMensagem("O cliente foi removido com sucesso!");
            return new ResponseEntity<RespostaModelo>(resp,HttpStatus.OK);
        }else{
            resp.setMensagem("Cliente não encontrado!");
            return new ResponseEntity<RespostaModelo>(resp,HttpStatus.BAD_REQUEST);
        }
    }    
}
