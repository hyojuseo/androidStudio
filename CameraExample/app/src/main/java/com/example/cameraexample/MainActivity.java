package com.example.cameraexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 672;
    private String imageFilePath;
    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한체크
        TedPermission.with(getApplicationContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("카메라 권한이 필요합니다.") //카메라권한을 열때 메시지
                .setDeniedMessage("거부하셨습니다.")   //사용자가 카메라 권한을 거부했을때 나타나는 메시지
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)   // module gradle에서 적은 3가지 권한을 여기에 적음.
                .check();

        //촬영버튼 눌렀을때 액션들
        findViewById(R.id.btn_capture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //카메라 기본앱을 띄우는 구문
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null){
                    File photoFile = null;
                    try{    //file쓰기할때는 항상 try catch를 해야된다.
                        photoFile = createImageFile();
                    }catch(IOException e){

                    }

                    if(photoFile != null){
                        photoUri = FileProvider.getUriForFile(getApplicationContext(),getPackageName(),photoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        //
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });

    }

    private File createImageFile() throws IOException{
        //년월일시간단위로 파일이름을 중복되지않게 저장한다.
        String timeStamp = new SimpleDateFormat("yyyMMDD_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
            ExifInterface exif = null;


            try{
                exif = new ExifInterface(imageFilePath);
            }catch (IOException e){
                e.printStackTrace();
            }

            int exifOrientation;
            int exifDegree;

            if(exif != null){
                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifDegree = exifOrientationToDegress(exifOrientation);
            } else{
                exifDegree = 0;
            }

            ((ImageView) findViewById(R.id.iv_result)).setImageBitmap(rotate(bitmap,exifDegree));
        }
    }


    private Bitmap rotate(Bitmap bitmap, float degree){
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

    //이미지 회전
    private int exifOrientationToDegress(int exifOrientation){
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90;
        } else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180;
        }  else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270){
            return 270;
        }
        return 0;
    }



    PermissionListener permissionListener = new PermissionListener() {
        //permission을 허용했을 때 일어나는 액션
        @Override
        public void onPermissionGranted() {
            Toast.makeText(getApplicationContext(),"권한이 허용됨",Toast.LENGTH_SHORT).show();
        }

        //permission을 거부했을때 일어나는 액션
        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(getApplicationContext(),"권한이 거부됨",Toast.LENGTH_SHORT).show();
        }
    };
}