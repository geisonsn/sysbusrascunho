package br.com.gsn.sysbusrascunho.view;

import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Geison on 11/08/2015.
 */
public class FieldValidation implements EditText.OnEditorActionListener {

    private EditText editText;
    private String message;

    public FieldValidation(EditText editText, String message) {
        this.editText = editText;
        this.message = message;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        Toast.makeText(v.getContext(), "Entrou onEditor", Toast.LENGTH_SHORT).show();
        if (actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE
                || event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            Editable campoPesquisa = editText.getText();

            if (TextUtils.isEmpty(campoPesquisa)) {
                editText.setError(Html.fromHtml("<font color='red'>" +  message + "</font>"));
                editText.requestFocus();
                return false;
            }
        }
        return true;
    }
}
