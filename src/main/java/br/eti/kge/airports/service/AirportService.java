/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.kge.airports.service;

import br.eti.kge.airports.dto.AirportMinDTO;
import br.eti.kge.airports.entities.Airport;
import br.eti.kge.airports.repositories.AirportRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */

@Service //implementa a regra de negocio (consulta/atualiza o banco usando repository que e a base dos dados
public class AirportService {

    /*ele é uma abreviação do framework para nao precisar instanciar um objeto
    como por exemplo new airport                                      
     */
    @Autowired
    private AirportRepository airportRepository; //dados encapsulados do AirpotRepository.java

    public List<Airport> findAll() {
        List<Airport> result = airportRepository.findAll();
        return result;/* retorna DTO.
        DTO serve para buscar apenas os campos que eu quero da base de dados
        que é os dados encapsulados apenas transfere
        e encapsul. O findAll() "encontra todos" busca tudo da base de dados o List<Airport>*/
        
    }
    
    /**
     * Carrega lista de aeroportos
     * @param City
     * @return 
     */
    public List<Airport>findByCity(String City){
            List<Airport> result = airportRepository.findByCityIgnoreCase(City);
            return result;
    
     
    
}
    public List<AirportMinDTO> findByContry(String country){
        List<Airport> resultAirport = airportRepository.findByCountryIgnoreCase(country);
        
        List<AirportMinDTO> resultDTO = resultAirport.stream()
               .map(x -> new AirportMinDTO(x)).toList();
        
        return resultDTO;
    }

    public  Airport findByIataCode(String iataCode){
        
    Airport result = airportRepository.findByIataCode(iataCode);
    return result;    
    }
}
    
    
      


