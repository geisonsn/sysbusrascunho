package br.com.gsn.sysbusrascunho.view;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.gsn.sysbusrascunho.util.Validator;

/**
 * Created by Geison on 15/08/2015.
 */
public class FieldsValidation {

    private List<Object> fields = new ArrayList<>();
    private List<Validator> validators = new ArrayList<>();

    public FieldsValidation() {
        this.fields = new ArrayList<>();
        this.validators = new ArrayList<>();
    }

    public FieldsValidation add(Object field, List<Validator> validators) {
        this.fields.add(field);
        this.validators = validators;
        return this;
    }

    /*public FieldsValidation add(Validator validator[]) {
        this.fields.add(validators);
        return this;
    }*/

    //campo obrigatorio
    //validacao especifica

    public void validate() {
        for (Object o : fields) {
            if (o instanceof EditText) {
                for (Validator v : validators) {
                    boolean validate = v.validate(o);
                }
                ((EditText) o).requestFocus();
            }
        }
    }
}
