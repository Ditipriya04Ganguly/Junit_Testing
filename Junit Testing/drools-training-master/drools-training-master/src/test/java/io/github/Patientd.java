package io.github;

import model.Patient;

import java.util.*;

public class Patientd {
    List<Patient> pt;
    List<String> prefill;
    List<String> multiple;
    List<String>multiplerisk;
    List<String> risks;
    List<String> disease;

    public Patientd(List<Patient> pt, List<String> prefill, List<String> multiple, List<String> multiplerisk, List<String> risks, List<String> disease) {
        this.pt = pt;
        this.prefill = prefill;
        this.multiple = multiple;
        this.multiplerisk = multiplerisk;
        this.risks = risks;
        this.disease = disease;
    }
}
