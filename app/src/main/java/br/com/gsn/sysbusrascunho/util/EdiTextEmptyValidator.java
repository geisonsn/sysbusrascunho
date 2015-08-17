package br.com.gsn.sysbusrascunho.util;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by Geison on 15/08/2015.
 */
public class EdiTextEmptyValidator extends EmptyAbstractValidator<EditText> {

    @Override
    public boolean validate(EditText field) {
        if (TextUtils.isEmpty(field.getText())) {
            field.setError("<font color='red'>Campo obrigatório</font>");
            return false;
        }
        return true;
    }
}
