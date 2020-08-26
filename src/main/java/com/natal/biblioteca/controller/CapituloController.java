//package com.natal.biblioteca.controller;
//
//import com.natal.biblioteca.controller.model.Capitulo;
//import com.natal.biblioteca.controller.model.Editora;
//import com.natal.biblioteca.controller.model.Livro;
//import com.natal.biblioteca.infrastructure.entities.CapituloEntity;
//import com.natal.biblioteca.infrastructure.entities.EditoraEntity;
//import com.natal.biblioteca.infrastructure.entities.LivroEntity;
//import com.natal.biblioteca.infrastructure.repository.CapituloRepository;
//
//import javax.ws.rs.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Path("/capitulo-controller")
//public class CapituloController {
//
//    private CapituloRepository repository = new CapituloRepository();
//
//    @GET
//    @Produces("application/json; charset=UTF-8")
//    @Path("/busca-capitulos")
//    public List<Capitulo> todosCapituloes(){
//
//        List<Capitulo> capitulos =  new ArrayList<Capitulo>();
//
//        List<CapituloEntity> listaCapituloEntity = repository.buscaTodos();
//
//        for (CapituloEntity entity : listaCapituloEntity) {
//            capitulos.add(
//                    new Capitulo(
//                            entity.getId(),
//                            new Livro(
//                                    entity.getLivro().getId(),
//                                    new Editora(
//                                            entity.getLivro().getEditora().getId(),
//                                            entity.getLivro().getEditora().getNome(),
//                                            entity.getLivro().getEditora().getPais()
//                                    ),
//                                    entity.getLivro().getIsbn()
//                            )
//                    )
//            );
//        }
//        return capitulos;
//    }
//
//
//    @GET
//    @Consumes("application/json; charset=UTF-8")
//    @Produces("application/json; charset=UTF-8")
//    @Path("/deletar")
//    public Capitulo buscaCapitulo(Long id){
//        CapituloEntity entity = repository.getCapitulo(id);
//        return new Capitulo(
//                entity.getId(),
//                new Livro(
//                        entity.getLivro().getId(),
//                        new Editora(
//                                entity.getLivro().getEditora().getId(),
//                                entity.getLivro().getEditora().getNome(),
//                                entity.getLivro().getEditora().getPais()
//                        ),
//                        entity.getLivro().getIsbn()
//                )
//        );
//    }
//
//    @POST
//    @Consumes("application/json; charset=UTF-8")
//    @Produces("application/json; charset=UTF-8")
//    @Path("/cadastrar")
//    public String cadastrar(Capitulo capitulo){
//        CapituloEntity entity;
//        try {
//            entity = new CapituloEntity(
//                    new LivroEntity(
//                            new EditoraEntity(
//                                    capitulo.getLivroEntity().getEditora().getNome(),
//                                    capitulo.getLivroEntity().getEditora().getPais()
//                            ),
//                            capitulo.getLivroEntity().getIsbn()
//                    )
//            );
//            repository.salvar(entity);
//            return "Registro cadastrado com sucesso!";
//        } catch (Exception e) {
//            return "Erro ao cadastrar um registro " + e.getMessage();
//        }
//    }
//
//    @DELETE
//    @Consumes("application/json; charset=UTF-8")
//    @Produces("application/json; charset=UTF-8")
//    @Path("/deletar")
//    public String deletar(Capitulo capitulo){
//        try {
//            repository.excluir(capitulo.getId());
//            return "Capitulo removido com sucesso!";
//        } catch (Exception e) {
//            return "Erro ao remover o registro " + e.getMessage();
//        }
//    }
//
//}
