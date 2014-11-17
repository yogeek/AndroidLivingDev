/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yogidev.android.livingroom;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class CollectionGalleryActivity extends FragmentActivity {
	
	public static final List<String> photosList =   new ArrayList<String>(Arrays.asList(
			"http://agence-livingroom.com/references/1100/photo_1037.jpg",
			"http://agence-livingroom.com/references/1100/photo_1038.jpg",
			"http://agence-livingroom.com/references/1100/photo_1040.jpg",
			"http://agence-livingroom.com/references/1100/photo_1039.jpg",
			"http://agence-livingroom.com/references/1100/photo_1041.jpg"
			));
	
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments representing
     * each object in a collection. We use a {@link android.support.v4.app.FragmentStatePagerAdapter}
     * derivative, which will destroy and re-create fragments as needed, saving and restoring their
     * state in the process. This is important to conserve memory and is a best practice when
     * allowing navigation between objects in a potentially large collection.
     */
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will display the object collection.
     */
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reference_collection_gallery);

        // Create an adapter that when requested, will return a fragment representing an object in
        // the collection.
        // 
        // ViewPager and its adapters use support library fragments, so we must use
        // getSupportFragmentManager.
        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());

        // Set up action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, ReferenceDescriptionActivity.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
     * representing an object in the collection.
     */
    public static class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new DemoObjectFragment();
            Bundle args = new Bundle();
            args.putString(DemoObjectFragment.PHOTO_OBJECT, photosList.get(i)); // Our object is the URL of the photo
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            // For this contrived example, we have a 10-object collection.
            return photosList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Photo " + (position + 1);
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DemoObjectFragment extends Fragment {

        public static final String PHOTO_OBJECT = "photo";
        Bitmap bitmap;
    	ProgressDialog pDialog;
    	ImageView imgView;
    	String imgUrl = "";
    	Context context;
    	Drawable defaultBackground;
    	
    	public void onAttach(Activity activity) {
    	    // TODO Auto-generated method stub
    	    super.onAttach(activity);
    	    context = activity;
    	}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_collection_photo, container, false);
            Bundle args = getArguments();
            imgView = (ImageView)rootView.findViewById(android.R.id.text1);
            imgUrl = args.getString(PHOTO_OBJECT);
            new DownloadImageTask(imgView).execute(imgUrl);
            
            // add button listener
            imgView.setOnClickListener(new OnClickListener() {
			
				@Override
				public void onClick(View view) {
			
					//view.getRootView().setBackground(new BitmapDrawable(getBitmapFromURL(imgUrl)));
					defaultBackground = view.getRootView().getBackground();
					new LoadImage().execute(imgUrl);
				}
			
			});
            
            return rootView;
        }
        
        public static Bitmap getBitmapFromURL(String src) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        
        private class LoadImage extends AsyncTask<String, String, Bitmap> {
        	@Override
        	protected void onPreExecute() {
        		super.onPreExecute();
        		pDialog = new ProgressDialog(context);
        		pDialog.setMessage("Loading Image ....");
        		pDialog.show();
        	}
        	protected Bitmap doInBackground(String... args) {
        		try {
        			bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        		return bitmap;
        	}
        	protected void onPostExecute(Bitmap image) {
        		if(image != null){
        			//imgView.setImageBitmap(image);
        			System.out.println("root View = " + imgView.getRootView());
        			imgView.getRootView().setBackground(new BitmapDrawable(image));
        			imgView.setVisibility(View.GONE);
        			pDialog.dismiss();
        			
        			imgView.getRootView().setOnClickListener(new OnClickListener() {
        				
        				@Override
        				public void onClick(View view) {
        			
        					view.getRootView().setBackground(defaultBackground);
        					imgView.setVisibility(View.VISIBLE);
        				}
        			
        			});
        			
        		}else{
        			pDialog.dismiss();
        			Toast.makeText(context, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
        		}
        	}
        }
    }
    
    
}
