package DB;
// Generated Jan 18, 2016 2:42:42 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Status generated by hbm2java
 */
public class Status  implements java.io.Serializable {


     private Integer idstatus;
     private String status;
     private Set<StudentPreference> studentPreferencesForStatus4 = new HashSet<StudentPreference>(0);
     private Set<StudentPreference> studentPreferencesForStatus1 = new HashSet<StudentPreference>(0);
     private Set<StudentPreference> studentPreferencesForStatus2 = new HashSet<StudentPreference>(0);
     private Set<StudentPreference> studentPreferencesForStatus3 = new HashSet<StudentPreference>(0);

    public Status() {
    }

    public Status(String status, Set<StudentPreference> studentPreferencesForStatus4, Set<StudentPreference> studentPreferencesForStatus1, Set<StudentPreference> studentPreferencesForStatus2, Set<StudentPreference> studentPreferencesForStatus3) {
       this.status = status;
       this.studentPreferencesForStatus4 = studentPreferencesForStatus4;
       this.studentPreferencesForStatus1 = studentPreferencesForStatus1;
       this.studentPreferencesForStatus2 = studentPreferencesForStatus2;
       this.studentPreferencesForStatus3 = studentPreferencesForStatus3;
    }
   
    public Integer getIdstatus() {
        return this.idstatus;
    }
    
    public void setIdstatus(Integer idstatus) {
        this.idstatus = idstatus;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public Set<StudentPreference> getStudentPreferencesForStatus4() {
        return this.studentPreferencesForStatus4;
    }
    
    public void setStudentPreferencesForStatus4(Set<StudentPreference> studentPreferencesForStatus4) {
        this.studentPreferencesForStatus4 = studentPreferencesForStatus4;
    }
    public Set<StudentPreference> getStudentPreferencesForStatus1() {
        return this.studentPreferencesForStatus1;
    }
    
    public void setStudentPreferencesForStatus1(Set<StudentPreference> studentPreferencesForStatus1) {
        this.studentPreferencesForStatus1 = studentPreferencesForStatus1;
    }
    public Set<StudentPreference> getStudentPreferencesForStatus2() {
        return this.studentPreferencesForStatus2;
    }
    
    public void setStudentPreferencesForStatus2(Set<StudentPreference> studentPreferencesForStatus2) {
        this.studentPreferencesForStatus2 = studentPreferencesForStatus2;
    }
    public Set<StudentPreference> getStudentPreferencesForStatus3() {
        return this.studentPreferencesForStatus3;
    }
    
    public void setStudentPreferencesForStatus3(Set<StudentPreference> studentPreferencesForStatus3) {
        this.studentPreferencesForStatus3 = studentPreferencesForStatus3;
    }




}


