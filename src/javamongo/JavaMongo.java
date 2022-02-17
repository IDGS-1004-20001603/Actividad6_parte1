package javamongo;

public class JavaMongo {
    
    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.read();
        //con.insert("Monterrey");
        System.out.println("-------------------------");
        //con.update("Monterrey", "Tigres");
        con.delete("Leon");
        con.read();
    }   
}