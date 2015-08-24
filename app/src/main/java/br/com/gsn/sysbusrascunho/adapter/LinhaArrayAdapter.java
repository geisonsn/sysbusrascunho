package br.com.gsn.sysbusrascunho.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import br.com.gsn.sysbusrascunho.domain.LinhaDTO;

/**
 * Created by Geison on 20/08/2015.
 */
public class LinhaArrayAdapter extends ArrayAdapter<LinhaDTO> {

    public LinhaArrayAdapter(Context context, List<LinhaDTO> linhas) {
        super(context, android.R.layout.simple_dropdown_item_1line, linhas);
    }
}
