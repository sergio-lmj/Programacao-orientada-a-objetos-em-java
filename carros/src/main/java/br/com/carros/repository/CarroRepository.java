package br.com.carros.repository;

import br.com.carros.model.Carro;
import org.springframework.data.repository.CrudRepository;


public interface CarroRepository extends CrudRepository<Carro,Long>{
    
}
