package DB;
// Generated Jan 18, 2016 2:42:42 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * StudentPreference generated by hbm2java
 */
public class StudentPreference  implements java.io.Serializable {


     private String studid;
     private Company companyByPref2;
     private Company companyByPref1;
     private Company companyByPref4;
     private Company companyByPref3;
     private Status statusByStatus4;
     private Status statusByStatus1;
     private Status statusByStatus2;
     private Status statusByStatus3;
     private Set<Advisee> advisees = new HashSet<Advisee>(0);

    public StudentPreference() {
    }

	
    public StudentPreference(String studid, Company companyByPref2, Company companyByPref1, Company companyByPref4, Company companyByPref3, Status statusByStatus4, Status statusByStatus1, Status statusByStatus2, Status statusByStatus3) {
        this.studid = studid;
        this.companyByPref2 = companyByPref2;
        this.companyByPref1 = companyByPref1;
        this.companyByPref4 = companyByPref4;
        this.companyByPref3 = companyByPref3;
        this.statusByStatus4 = statusByStatus4;
        this.statusByStatus1 = statusByStatus1;
        this.statusByStatus2 = statusByStatus2;
        this.statusByStatus3 = statusByStatus3;
    }
    public StudentPreference(String studid, Company companyByPref2, Company companyByPref1, Company companyByPref4, Company companyByPref3, Status statusByStatus4, Status statusByStatus1, Status statusByStatus2, Status statusByStatus3, Set<Advisee> advisees) {
       this.studid = studid;
       this.companyByPref2 = companyByPref2;
       this.companyByPref1 = companyByPref1;
       this.companyByPref4 = companyByPref4;
       this.companyByPref3 = companyByPref3;
       this.statusByStatus4 = statusByStatus4;
       this.statusByStatus1 = statusByStatus1;
       this.statusByStatus2 = statusByStatus2;
       this.statusByStatus3 = statusByStatus3;
       this.advisees = advisees;
    }
   
    public String getStudid() {
        return this.studid;
    }
    
    public void setStudid(String studid) {
        this.studid = studid;
    }
    public Company getCompanyByPref2() {
        return this.companyByPref2;
    }
    
    public void setCompanyByPref2(Company companyByPref2) {
        this.companyByPref2 = companyByPref2;
    }
    public Company getCompanyByPref1() {
        return this.companyByPref1;
    }
    
    public void setCompanyByPref1(Company companyByPref1) {
        this.companyByPref1 = companyByPref1;
    }
    public Company getCompanyByPref4() {
        return this.companyByPref4;
    }
    
    public void setCompanyByPref4(Company companyByPref4) {
        this.companyByPref4 = companyByPref4;
    }
    public Company getCompanyByPref3() {
        return this.companyByPref3;
    }
    
    public void setCompanyByPref3(Company companyByPref3) {
        this.companyByPref3 = companyByPref3;
    }
    public Status getStatusByStatus4() {
        return this.statusByStatus4;
    }
    
    public void setStatusByStatus4(Status statusByStatus4) {
        this.statusByStatus4 = statusByStatus4;
    }
    public Status getStatusByStatus1() {
        return this.statusByStatus1;
    }
    
    public void setStatusByStatus1(Status statusByStatus1) {
        this.statusByStatus1 = statusByStatus1;
    }
    public Status getStatusByStatus2() {
        return this.statusByStatus2;
    }
    
    public void setStatusByStatus2(Status statusByStatus2) {
        this.statusByStatus2 = statusByStatus2;
    }
    public Status getStatusByStatus3() {
        return this.statusByStatus3;
    }
    
    public void setStatusByStatus3(Status statusByStatus3) {
        this.statusByStatus3 = statusByStatus3;
    }
    public Set<Advisee> getAdvisees() {
        return this.advisees;
    }
    
    public void setAdvisees(Set<Advisee> advisees) {
        this.advisees = advisees;
    }




}


