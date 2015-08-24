package br.com.gsn.sysbusrascunho.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.gsn.sysbusrascunho.domain.LinhaDTO;

/**
 * Created by Geison on 20/08/2015.
 */
public class LinhaAdapter extends BaseAdapter {

    private Context context;
    private List<LinhaDTO> linhas;

    public LinhaAdapter(Context context, List<LinhaDTO> linhas) {
        this.context = context;
        this.linhas = linhas;
    }

    @Override
    public int getCount() {
        return linhas.size();
    }

    @Override
    public Object getItem(int position) {
        return linhas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return linhas.get(position).getIdLinha();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
