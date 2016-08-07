package com.example.ridzi.helpteens1;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by Ridzi on 07-07-2016.
 */


/**
 * Created by hp pc on 7/14/2016.
 */
public class Setting extends PreferenceActivity {
    EditText msg, phone;
    Button sendsms;
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;
    //  Context context = this;
    // private static final int REQUEST_CODE = 0;
    // private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        // setContentView(R.layout.activity_main);
        initcontrol();
    }

    private void initcontrol() {
        try {
            volumeSeekbar = (SeekBar) findViewById(R.id.seekbar);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));


            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        addPreferencesFromResource(R.xml.preference);
        SwitchPreference switchPreference = (SwitchPreference) findPreference("notification");
        SwitchPreference switchPreference1 = (SwitchPreference) findPreference("sound");
        SwitchPreference switchPreference2 = (SwitchPreference) findPreference("vibrate");

        switchPreference2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean isVibrateOn = (Boolean) newValue;
                if (isVibrateOn) {
                    Vibrator v = (Vibrator) getBaseContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);
                }
                return true;
            }
        });
        if (switchPreference1 != null)
            switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean isNotificationOn = (Boolean) newValue;
                    if (isNotificationOn) {
                        NotificationManager Manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        //               Notification notification=new Notification(R.drawable.icon,"notification text will be shownon status bar",
                        //                    System.currentTimeMillis());

                        //  PendingIntent contentIntent = PendingIntent.getActivity(context,REQUEST_CODE,new Intent(this,MainActivity.class),0);
                        // notification.setLatestEventInfo(this,"Content text",contentIntent);
                        //           Manager.notify(NOTIFICATION_ID,notification);


                    }
                    return false;
                }
            });
        //----privacy policy----
        Preference preference, preference1;
        preference = findPreference("policy");
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Dialog dialog = new Dialog(Setting.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.policy);
                Toast.makeText(getBaseContext(), "clicked", Toast.LENGTH_LONG).show();
                dialog.show();
                return false;
            }
        });
        //----help----
        preference1 = findPreference("help");
        preference1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Dialog dialog = new Dialog(Setting.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.help);
                Toast.makeText(getBaseContext(), "clicked", Toast.LENGTH_LONG).show();
                dialog.show();
                return false;
            }
        });
        //---invite by email----
        Preference preference2;
        preference2 = findPreference("email");
        preference2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                mailme();
                return false;
            }
        });
        //---invite by sms---

        Preference preference3;
        preference3 = findPreference("sms");
        preference3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Dialog dialog = new Dialog(Setting.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.sms);
                phone = (EditText) dialog.findViewById(R.id.phone);
                msg = (EditText) dialog.findViewById(R.id.msg);
                sendsms = (Button) dialog.findViewById(R.id.button2);
                sendsms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sms();
                    }


                });
                dialog.show();
                return false;
            }
        });
    }

    private void sms() {
        Log.i("Message send", "");
        String p = phone.getText().toString();
        String m = msg.getText().toString();

        try
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(p, null, m, null, null);
            Toast.makeText(getBaseContext(), "message sent", Toast.LENGTH_LONG).show();
        }
        catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(getBaseContext(), "message sending failed", Toast.LENGTH_LONG).show();
        }
    }
    //  Toast.makeText(getBaseContext(), "sms sent", Toast.LENGTH_LONG).show();



    private void mailme() {
        String[] To = {""};
        String[] CC = {""};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, To);
        intent.putExtra(Intent.EXTRA_CC, CC);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "invite a frnd to use this app");
        try {
            startActivity(intent);
            finish();
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getBaseContext(), "message not send", Toast.LENGTH_LONG).show();
        }
    }
}




