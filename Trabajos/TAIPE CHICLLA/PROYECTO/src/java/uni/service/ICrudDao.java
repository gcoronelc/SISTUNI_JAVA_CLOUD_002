/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.service;

import java.util.List;


/**
 *
 * @author Alumno
 */
public interface ICrudDao <T>{
    //definir las firmas
    
    void create(T t) throws Exception;
    void update(T t) throws Exception;
    void delete(T t) throws Exception;
    T find(T t) throws Exception;
    List<T> readAll() throws Exception;
    
}
