
package uni.service;

public interface IDocumentoDao<T> {
    //definir la firma

    void registraDocumento(T d) throws Exception;
}
