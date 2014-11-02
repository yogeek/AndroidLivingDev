package com.yogidev.android.livingroom;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yogidev.android.livingroom.data.bean.Reference;

public class ReferenceListAdapter extends ArrayAdapter<Reference> {

	private List<Reference> values;
	private Context context;

	public ReferenceListAdapter(Context context, List<Reference> values) {
		super(context, R.layout.reference_list, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;

		if (convertView != null) {
			view = convertView;
		} else {
			// Ensure sorted values
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.reference_list, parent, false);
			
			Reference ref = values.get(position);
			
			// Icon
			ImageView imgView = (ImageView) view.findViewById(R.id.icon);
//			imgView.setImageURI(Uri.parse(ref.getVignette()));
			imgView.setImageResource(R.drawable.maison);
			// Title
			TextView textView = (TextView) view.findViewById(R.id.secondLine);
			textView.setText(ref.getVille() + " - " + ref.getQuartier());
			// Details
			TextView textView2 = (TextView) view.findViewById(R.id.firstLine);
			textView2.setText(ref.getTitreRef() + " / " + ref.getLoyerOuPrix());	
		}
		return view;
	}
}
