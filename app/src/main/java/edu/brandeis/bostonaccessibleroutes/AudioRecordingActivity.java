package edu.brandeis.bostonaccessibleroutes;


/**
 * Created by macy on 11/8/16.
 */

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AudioRecordingActivity extends AppCompatActivity {

    ImageButton buttonStart, buttonStop, buttonPlayLastRecordAudio, buttonStopPlayingRecording;
    Button buttonDone;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder;
    Random random;
    String RandomAudioFileName = "ABCD1234";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recording);

        buttonStart = (ImageButton) findViewById(R.id.record);
        buttonStop = (ImageButton) findViewById(R.id.stop);
        buttonStop.setEnabled(false);
        buttonStop.setVisibility(View.INVISIBLE);
        buttonPlayLastRecordAudio = (ImageButton) findViewById(R.id.play);
        buttonPlayLastRecordAudio.setEnabled(false);

        buttonStopPlayingRecording = (ImageButton) findViewById(R.id.stopPlay);
        buttonStopPlayingRecording.setEnabled(false);
        buttonStopPlayingRecording.setVisibility(View.INVISIBLE);
        buttonDone = (Button) findViewById(R.id.done);

        buttonStop.setEnabled(false);
        buttonPlayLastRecordAudio.setEnabled(false);
        buttonStopPlayingRecording.setEnabled(false);
        random = new Random();
        buttonDone.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i=new Intent();
                i.putExtra(TakePhotoActivity.VOICE_PATH_KEY,AudioSavePathInDevice);
                setResult(RESULT_OK,i);
                finish();
            }
        });
        
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkPermission()) {

                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                    CreateRandomAudioFileName(5) + "AudioRecording.3gp";

                    MediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    buttonStart.setEnabled(false);
                    buttonStart.setVisibility(View.INVISIBLE);
                    buttonStop.setEnabled(true);
                    buttonStop.setVisibility(View.VISIBLE);

                    Toast.makeText(AudioRecordingActivity.this, "Recording started",
                            Toast.LENGTH_LONG).show();
                } else {
                    requestPermission();
                }

            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                buttonStop.setEnabled(false);
                buttonStop.setVisibility(View.INVISIBLE);
                buttonPlayLastRecordAudio.setEnabled(true);
                buttonPlayLastRecordAudio.setVisibility(View.VISIBLE);
                buttonStart.setEnabled(true);
                buttonStart.setVisibility(View.VISIBLE);
                buttonStopPlayingRecording.setEnabled(false);
                buttonStopPlayingRecording.setVisibility(View.INVISIBLE);

                Toast.makeText(AudioRecordingActivity.this, "Recording Completed",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {

                buttonStop.setEnabled(false);
                buttonStop.setVisibility(View.INVISIBLE);
                buttonStart.setEnabled(false);
                buttonStart.setVisibility(View.INVISIBLE);
                buttonStopPlayingRecording.setEnabled(true);
                buttonStopPlayingRecording.setVisibility(View.VISIBLE);
                buttonPlayLastRecordAudio.setEnabled(false);
                buttonPlayLastRecordAudio.setVisibility(View.INVISIBLE);

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(AudioRecordingActivity.this, "Recording Playing",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonStop.setEnabled(false);
                buttonStop.setVisibility(View.INVISIBLE);
                buttonStart.setEnabled(true);
                buttonStart.setVisibility(View.VISIBLE);
                buttonStopPlayingRecording.setEnabled(false);
                buttonStopPlayingRecording.setVisibility(View.INVISIBLE);
                buttonPlayLastRecordAudio.setEnabled(true);
                buttonPlayLastRecordAudio.setVisibility(View.VISIBLE);

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    MediaRecorderReady();
                }
            }
        });

    }

    public void MediaRecorderReady() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        //mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    public String CreateRandomAudioFileName(int string) {
//        StringBuilder stringBuilder = new StringBuilder(string);
//        int i = 0;
//        while (i < string) {
//            stringBuilder.append(RandomAudioFileName.
//                    charAt(random.nextInt(RandomAudioFileName.length())));
//
//            i++;
//        }
//        return stringBuilder.toString();

        SecureRandom random = new SecureRandom();
        String randomString=new BigInteger(130, random).toString(32).toUpperCase().substring(0,string);
        return randomString;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(AudioRecordingActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(AudioRecordingActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AudioRecordingActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }
}