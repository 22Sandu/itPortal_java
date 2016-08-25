package servletPack;

import DB.PoolManager;
import DB.StudentPreference;
import java.util.List;
import javax.ws.rs.core.Response;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class dbHandlingMethods {

    public int updateStudentPreferences(String student, int status, int state) {
        try {
            
            Session s = PoolManager.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Criteria c = s.createCriteria(DB.StudentPreference.class);
            c.add(Restrictions.eq("studid", student));
            c.setMaxResults(1);

            List<DB.StudentPreference> l = c.list();
            String id = "";
            if (l.size() > 0) {
                int currentStatus = 0;
                for (StudentPreference l1 : l) {
                    switch (status) {
                        case 1:
                            currentStatus = l1.getStatusByStatus1().getIdstatus();
                            break;
                        case 2:
                            currentStatus = l1.getStatusByStatus2().getIdstatus();
                            break;
                        case 3:
                            currentStatus = l1.getStatusByStatus3().getIdstatus();
                            break;
                        case 4:
                            currentStatus = l1.getStatusByStatus4().getIdstatus();
                            break;
                    }
                    id = l1.getStudid();
                }
                
                if (!(state == 3 && (currentStatus == 5 || currentStatus == 2 || currentStatus == 4 || currentStatus == 6)) && !id.equals("")) {
                    StudentPreference sp = s.load(DB.StudentPreference.class, id);
                        switch (status) {
                            case 1:
                                sp.setStatusByStatus1(s.load(DB.Status.class, state));
                                break;
                            case 2:
                                sp.setStatusByStatus2(s.load(DB.Status.class, state));
                                break;
                            case 3:
                                sp.setStatusByStatus3(s.load(DB.Status.class, state));
                                break;
                            case 4:
                                sp.setStatusByStatus4(s.load(DB.Status.class, state));
                                break;
                        }
                        s.update(sp);
                        t.commit();
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
