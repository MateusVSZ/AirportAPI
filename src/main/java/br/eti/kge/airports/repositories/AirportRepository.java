/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.eti.kge.airports.repositories;

import br.eti.kge.airports.entities.Airport;
import br.eti.kge.airports.projections.AirportNearMeProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author digma
 */
public interface AirportRepository extends JpaRepository <Airport , Long> {
    List<Airport> findByCityIgnoreCase(String city);
    List<Airport> findByCountryIgnoreCase(String country);
    
    Airport findByIataCode(String iataCode);
    


    @Query(nativeQuery = true, value = """
                                        SELECT
                                        airport.id,
                                        airport.name,
                                        airport.city,
                                        airport.iatacode,
                                        airport.latitude,
                                        airport.longitude,
                                        airport.altitude,
                                        SQRT(
                                        power(airport.latitude - :latOrigem, 2 ) +
                                        power(airport.longitude - :lonOrigem, 2)) * 60 * 1.852 as distanciaKM
                                                                              
                                       from AIRPORT
                                       order by distanciaKM
                                       limit 10;
                                       """
    )
    
    List<AirportNearMeProjection> findNearMe(double latOrigem, double lonOrigem);
    

}

