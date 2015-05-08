package br.com.gsn.sysbusrascunho.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by geison on 07/05/15.
 */
public class ClearFieldsError implements TextWatcher {

    private EditText field;

    public ClearFieldsError(EditText field) {
        this.field = field;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
       field.setError(null);
    }
}
