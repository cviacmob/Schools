package com.cviac.s4iApp.activities;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.cviac.s4iApp.Prefs;
import com.cviac.s4iApp.R;
import com.cviac.s4iApp.adapters.CircleTransform;
import com.cviac.s4iApp.adapters.ExpandableListAdapter;
import com.cviac.s4iApp.datamodel.Profile;
import com.cviac.s4iApp.datamodel.ProfilePicUpload;
import com.cviac.s4iApp.sfiapi.MyProfileInfo;
import com.cviac.s4iApp.sfiapi.SFIApi;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ProfileActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_CAMERA = 10;
    private static final int MY_PERMISSION_EXTERNAL_STORAGE = 11;
    private static final int RESULT_LOAD_IMG = 0;
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> defValuesCollection;
    ExpandableListView expListView;
    List<Profile> pro;

    Bitmap bm;

    final Context context = this;
    ImageView imageViewRound;
    ImageView viewImage;

    private int REQUEST_CAMERA = 2, SELECT_FILE = 1;
    private ImageButton btnSelect;

    private String userChoosenTask;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private String memId, memcoded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        memId = Prefs.getString("MemId", "");
        MyProfileInfo profileinfo = new MyProfileInfo();
        //  MyProfileInfo profileinfo = MyProfileInfo.updateProfileImageUrl(memId);
        setTitle("My Profile");
        ProfilePicUpload profileupload = new ProfilePicUpload();
        viewImage = (ImageView) findViewById(R.id.imageView1);
        Picasso.with(context).load(R.drawable.imggg).resize(220, 220).transform(new CircleTransform())
                .into(viewImage);
        btnSelect = (ImageButton) findViewById(R.id.LoadPicture1);
        Picasso.with(context).load(R.drawable.imggg).resize(80, 80).transform(new CircleTransform())
                .into(btnSelect);
        memcoded = profileupload.getMemID();

        if (!memId.equals(memcoded)) {

            btnSelect.setVisibility(View.INVISIBLE);   //this app not update profile picture now only including  default image but coding has implemented
        }

        Picasso.with(context).load(R.drawable.imggg).resize(80, 80).transform(new CircleTransform())
                .into(btnSelect);
        btnSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        String imgUrl = profileinfo.getImage_url();
        if (imgUrl != null && imgUrl.length() > 0) {
            Picasso.with(context).load(imgUrl).resize(220, 220).transform(new CircleTransform())
                    .into(viewImage);
        } else {
            Picasso.with(context).load(R.drawable.dppic).resize(220, 220).transform(new CircleTransform())
                    .into(viewImage);
        }
        createGroupList();

        createCollection();

        getProfileInof();

        MyProfileInfo Mpi = new MyProfileInfo();

        expListView = (ExpandableListView) findViewById(R.id.exp);

        //setGroupIndicatorToRight();
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(ProfileActivity.this, groupList, defValuesCollection, Mpi);
        expListView.setAdapter(expListAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();

                return true;
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void getProfileInof() {
        String memId = Prefs.getString("MemId", "");
        MyProfileInfo profile = new MyProfileInfo();
        profile.setMemID(memId);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://schoolsforindia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SFIApi api = retrofit.create(SFIApi.class);
        final Call<List<MyProfileInfo>> call = api.getmyprofile(memId);
        call.enqueue(new Callback<List<MyProfileInfo>>() {
            @Override
            public void onResponse(Response<List<MyProfileInfo>> response, Retrofit retrofit) {
                List<MyProfileInfo> minfolist = response.body();
                if (minfolist.size() > 0) {
                    MyProfileInfo Mpi = minfolist.get(0);
                    final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(ProfileActivity.this, groupList, defValuesCollection, Mpi);
                    expListView.setAdapter(expListAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("MEMBERSHIP INFORMATION");
        groupList.add("BASIC INFORMATION");
        groupList.add("RESIDENTIAL ADDRESS");
        groupList.add("OFFICE ADDRESS");


    }

    private void createCollection() {
        // preparing laptops collection(child)
        String[] mi = {"MEMBERSHIP TYPE", "MEMBERSHIP PERIOD"};
        String[] bi = {"NAME", "E MAIL", "MOBILE", "GENDER"};
        String[] ra = {"ADDRESS 1", "ADDRESS 2", "CITY/STATE", "ZIP CODE"};
        String[] oa = {"ADDRESS 1", "ADDRESS 2", "CITY/STATE", "ZIP CODE"};


        defValuesCollection = new LinkedHashMap<String, List<String>>();

        for (String laptop : groupList) {
            if (laptop.equals("MEMBERSHIP INFORMATION"))
                loadChild(mi);
            else if (laptop.equals("BASIC INFORMATION")) {
                loadChild(bi);
            } else if (laptop.equals("RESIDENTIAL ADDRESS"))
                loadChild(ra);
            else if (laptop.equals("OFFICE ADDRESS"))
                loadChild(oa);

            defValuesCollection.put(laptop, childList);
        }
    }

    private void loadChild(String[] laptopModels) {
        childList = new ArrayList<String>();
        for (String model : laptopModels)
            childList.add(model);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    dialog.dismiss();
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    dialog.dismiss();
                    galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void galleryIntent() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // Start the Intent
            startActivityForResult(galleryIntent, SELECT_FILE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_EXTERNAL_STORAGE);
        }
    }

    private void cameraIntent() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
            uploadProfileImage(destination.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewImage.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String targetPath = cursor.getString(columnIndex);
                cursor.close();

                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                viewImage.setImageBitmap(bm);
                uploadProfileImage(targetPath);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadProfileImage(String targetPath) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://schoolsforindia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final String memId = Prefs.getString("MemId", "");
        ProfilePicUpload profileupload = new ProfilePicUpload();
        profileupload.setMemID(memId);

        SFIApi api = retrofit.create(SFIApi.class);
        File file = new File(targetPath);
        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);
        Call<ProfilePicUpload> call = api.profileUpdate(memId, fbody);
        call.enqueue(new Callback<ProfilePicUpload>() {
            @Override
            public void onResponse(Response<ProfilePicUpload> response, Retrofit retrofit) {
                ProfilePicUpload rsp = response.body();
                if (rsp.getImage_url() != null) {
                    //  MyProfileInfo.updateProfileImageUrl(memId,rsp.getImage_url());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}