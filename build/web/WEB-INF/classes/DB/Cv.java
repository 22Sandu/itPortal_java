package DB;
// Generated Jan 18, 2016 2:42:42 AM by Hibernate Tools 4.3.1



/**
 * Cv generated by hbm2java
 */
public class Cv  implements java.io.Serializable {


     private Integer idcv;
     private String name;

    public Cv() {
    }

    public Cv(String name) {
       this.name = name;
    }
   
    public Integer getIdcv() {
        return this.idcv;
    }
    
    public void setIdcv(Integer idcv) {
        this.idcv = idcv;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }




}


