package com.yogidev.android.livingroom;

import java.io.InputStream;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
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
		super(context, R.layout.reference_list_row, values);
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
			view = inflater.inflate(R.layout.reference_list_row, parent, false);
			
			Reference ref = values.get(position);
			
			// Icon
			ImageView imgView = (ImageView) view.findViewById(R.id.icon);
//			imgView.setImageURI(Uri.parse(ref.getVignette()));
//			imgView.setImageResource(R.drawable.vignette_reference);
			
			// here we will load the picture from the URL into the imageView
			new DownloadImageTask(imgView).execute(ref.getVignette());
			
			// Title
			TextView firstLine = (TextView) view.findViewById(R.id.firstLine);
			firstLine.setText(ref.getVille() + " - " + ref.getQuartier());
			// Details
			TextView secondLine = (TextView) view.findViewById(R.id.secondLine);
			secondLine.setText(ref.getTypeRef() + " - " + ref.getSurfaceInteger() + "m²");	
			// Prix
			TextView thirdLine = (TextView) view.findViewById(R.id.thirdLine);
			String prix = ref.getLoyerOuPrix() + "€" + (ref.isLocation()?"/mois":"");
			thirdLine.setText(prix);
			
		}
		return view;
	}
	
	// AsyckTask to download image (url given )
	 private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	    ImageView bmImage;
	    
	    //constructor
	    public DownloadImageTask(ImageView bmImage) {
	        this.bmImage = bmImage;
	    }
	    
	    // laoding picture and put it into bitmap 
	    protected Bitmap doInBackground(String... urls) {
	        String urldisplay = urls[0];
	        Bitmap mIcon11 = null;
	        try {
	          InputStream in = new java.net.URL(urldisplay).openStream();
	          mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }
	    
	    //after downloading
	    protected void onPostExecute(Bitmap result) {
	        bmImage.setImageBitmap(result);
	    }
	  }
}
