package javamongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    DB BaseDatos;
    DBCollection coleccion;
    BasicDBObject documento = new BasicDBObject();
    
    public Conexion(){
        try{
            Mongo mongo = new Mongo("localhost",27017); //Dirección IP de la máquina y el purto de instalación de Mongo
            BaseDatos = mongo.getDB("equiposFutbol");
            coleccion = BaseDatos.getCollection("equiposFutbol");
            System.out.println("Conexión Establecida");
        }catch(UnknownHostException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    //Hacer el CRUD
    public boolean insert(String equipo){
        documento.put("equipo", equipo);
        coleccion.insert(documento);
        return true;
    }
    
    public void read(){
        DBCursor cursor = coleccion.find();
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }
    
    public boolean update(String equipoViejo, String equipoNuevo){
        documento.put("equipo", equipoViejo);
        BasicDBObject documentoNuevo = new BasicDBObject();
        documentoNuevo.put("accion", equipoNuevo);
        coleccion.findAndModify(documento, documentoNuevo);
        return true;
    }
    
    public boolean delete(String equipo){
        documento.put("equipo", equipo);
        coleccion.remove(documento);
        return true;
    }
}