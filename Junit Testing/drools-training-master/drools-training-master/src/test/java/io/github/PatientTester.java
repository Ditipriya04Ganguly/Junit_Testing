package io.github;


import model.Patient;
import model.PatientDisease;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import java.time.LocalDate;
import java.util.*;

public class PatientTester {
    static Map<Integer,Patientd> map= new HashMap<>();
    MapCreator mapCreator;

    public void setMapCreator(){
        mapCreator=new MapCreator(Arrays.asList(new Pair(1, new HashSet<String>(Arrays.asList("Dysphagia")))
        ,new Pair(3, new HashSet<String>(Arrays.asList("GERD","Weight Loss")))));

    }

    static  {

        map.put(1,new Patientd(Arrays.asList(
                new Patient(1,"R13.1", "Active",LocalDate.of(2022,06,30), LocalDate.of(2000,9,04)),
                new Patient(3,"R63.4", "Active",LocalDate.of(2022,05,30), LocalDate.of(1980,9,8)),
                new Patient(3,"K21.0", "Active",LocalDate.of(2022,04,30), LocalDate.of(1972,9,23)),
                new Patient(3,"K40", "Active",LocalDate.of(2022,04,01), LocalDate.of(1972,9,23))
                ),Arrays.asList("Y","Y"),Arrays.asList("Y"),null,Arrays.asList("Hiatal Hernia"),Arrays.asList("Dysphagia","GERD","Weight Loss")));
    }

    @Test
    public void testForPatientDisease() {
        PatientTester pt= new PatientTester();
        pt.setMapCreator();
        PatientDisease patientDisease = new PatientDisease();
        KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("diseaserules");
        for (int tid : pt.map.keySet()) {
            for (Patient p : pt.map.get(tid).pt) {
                ksession.insert(p);
            }
            ksession.insert(LocalDate.now());
            ksession.insert(patientDisease);
            ksession.fireAllRules();
            ksession.dispose();

            pt.validatorDisease(patientDisease, pt.mapCreator);

        }

    }

    public void validatorDisease(PatientDisease pd,MapCreator mapCreator1){
        for (Patient patient: pd.getMaplist().keySet()){
            assert(pd.getMaplist().get(patient).containsAll(mapCreator1.map.get(patient.getId())));

        }
    }
}
