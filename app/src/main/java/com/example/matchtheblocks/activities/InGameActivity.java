package com.example.matchtheblocks.activities;


import android.annotation.SuppressLint;
import android.app.Activity;

import android.app.ActivityOptions;
import android.content.ClipData;

//import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.matchtheblocks.R;


import java.util.ArrayList;


public class InGameActivity extends Activity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String One = "oneKey";
    public static final String Two = "towKey";
    public static final String Three = "threeKey";
    public static final String Four = "fourKey";
    public static final String Five = "fiveKey";
    public static final String Six = "sixKey";
    public static final String Seven = "sevenKey";
    public static final String Eight = "eightKey";
    public static final String Nine = "nineKey";

    public static final String OneMatch = "oneMatchKey";
    public static final String TwoMatch = "towMatchKey";
    public static final String ThreeMatch = "threeMatchKey";
    public static final String FourMatch = "fourMatchKey";
    public static final String FiveMatch = "fiveMatchKey";
    public static final String SixMatch = "sixMatchKey";
    public static final String SevenMatch = "sevenMatchKey";
    public static final String EightMatch = "eightMatchKey";
    public static final String NineMatch = "nineMatchKey";
    SharedPreferences sharedpreferences;

    TextView timer, resultTime;
    Button start, pause, reset;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;

    public static final String TAG = "MainActivity2";
    TextView imgM, imgL, imgR, imgP, imgQ, imgR1, imgS, imgT, imgU,
            imgCMP1, imgCMP2, imgCMP3, imgCMP4,  imgCMP5,  imgCMP6,  imgCMP7,  imgCMP8,  imgCMP9,
            gameRestartingInfo, startGameTextView, earnedTimeTextView;
    LottieAnimationView lottiewView1, lottiewView2, lottiewView3, lottiewView4, lottiewView5, lottiewView6, lottiewView7, lottiewView8, lottiewView9,
            backGroundAnim;
    CardView cardConfirm1, cardConfirm2, cardConfirm3, cardConfirm4, cardConfirm5, cardConfirm6, cardConfirm7, cardConfirm8, cardConfirm9,
            cardMatch1, cardMatch2, cardMatch3, cardMatch4, cardMatch5, cardMatch6, cardMatch7, cardMatch8, cardMatch9,
            homeAction, pauseMenuCardView, winMenuCardView, gameOverMenuCardView, homeMenuCardView, resumeOption, exitOption;

    boolean dragging = false;
    int draggingNumber;
    int onDragEndedNumber;
    int matchedNumber = 0;
    View draggingView;
    private ArrayList<Integer> randomNumberList;
    private ArrayList<Integer> suffledNumberList;
    private ArrayList<Integer> completedNumber;
    private ArrayList<View> viewsToMatch;
    private TextToSpeech textToSpeechSystem;
    private MediaPlayer backgroundMusic;
    private View mContentView;
    private android.widget.LinearLayout.LayoutParams layoutParams;
    private MediaPlayer effectsPlayer;
    private CountDownTimer gameRunningCountDownTimer;
    private long remainimgTime = 3000000;
    private long earnedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        timer = findViewById(R.id.tvTimer);
        resultTime = findViewById(R.id.total_time);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();

        bindingViews();
        hide();
        viewsToMatch = new ArrayList<>();

        viewsToMatch.add(cardMatch1);
        viewsToMatch.add(cardMatch2);
        viewsToMatch.add(cardMatch3);
        viewsToMatch.add(cardMatch4);
        viewsToMatch.add(cardMatch5);
        viewsToMatch.add(cardMatch6);
        viewsToMatch.add(cardMatch7);
        viewsToMatch.add(cardMatch8);
        viewsToMatch.add(cardMatch9);

        backgroundMusic = MediaPlayer.create(this, R.raw.background_music_2);
        backgroundMusic.setVolume(0f, 0.4f);
        backgroundMusic.setLooping(true);
        winMenuCardView.animate().translationY(1200);
        gameOverMenuCardView.animate().translationY(1200);
        homeMenuCardView.animate().translationY(1200);



        setUpNumbers();

        setingUpAllListners();

        //---------Registration button Click----------
        Button btnPlayAgin=findViewById(R.id.btnplayAgin);
        btnPlayAgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InGameActivity.this, Splash.class);
                startActivity(intent);
                finish();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
            }
        });// --------- end click button----------

        //---------Registration button Click----------
        Button btnGameOver=findViewById(R.id.btnGameOver);
        btnGameOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InGameActivity.this, Splash.class);
                startActivity(intent);
                finish();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
            }
        });// --------- end click button----------

        //---------Registration button Click----------
        Button btnExit=findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
            }
        });// --------- end click button----------

        //---------Registration button Click----------
        Button btnExit1=findViewById(R.id.btnExit1);
        btnExit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
            }
        });// --------- end click button----------

        //---------Registration button Click----------
        Button btnExit2=findViewById(R.id.btnExit2);
        btnExit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
            }
        });// --------- end click button----------

        //---------Registration button Click----------
        Button btnClose=findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeMenuCardView.animate().translationY(1200);
                handler=new Handler();
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                effectsPlayer = MediaPlayer.create(InGameActivity.this, R.raw.pause);
                effectsPlayer.setVolume(0f, 0.2f);
                effectsPlayer.start();
            }
        });// --------- end click button----------

        //---------Registration button Click----------
        Button btnHomePlayAgian=findViewById(R.id.btnHomePlayAgain);
        btnHomePlayAgian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InGameActivity.this, Splash.class);
                startActivity(intent);
                finish();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
            }
        });// --------- end click button----------




    }

    public  Runnable runnable = new Runnable() {
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 1000);
            timer.setText("Time: "+"" + Minutes + " : "
                    + String.format("%02d", Seconds) + " : "
                    + String.format("%03d", MilliSeconds));
            resultTime.setText("Total Time: "+"" + Minutes + " : "
                    + String.format("%02d", Seconds) + " : "
                    + String.format("%03d", MilliSeconds));
            handler.postDelayed(this, 0);
        }
    };


    private void hide() {
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }


    private void setingUpAllListners() {

        resumeOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeGame();
            }
        });
        exitOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        cardConfirm1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm1, 0);
                return true;
            }
        });
        cardConfirm2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm2, 1);
                return true;
            }
        });
        cardConfirm3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm3, 2);
                return true;
            }
        });

        cardConfirm4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm4, 3);
                return true;
            }
        });

        cardConfirm5.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm5, 4);
                return true;
            }
        });

        cardConfirm6.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm6, 5);
                return true;
            }
        });

        cardConfirm7.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm7, 6);
                return true;
            }
        });

        cardConfirm8.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm8, 7);
                return true;
            }
        });

        cardConfirm9.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm9, 8);
                return true;
            }
        });


        //---------------------------------------------------------------------------
        cardMatch1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch1, 0);
                return true;
            }
        });


        cardMatch1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch1, imgL);
            }
        });

        //---------------------------------------------------------------------------

        cardMatch2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch2, 1);
                return true;
            }
        });

        cardMatch2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch2, imgM);
            }
        });

        //---------------------------------------------------------------------------

        cardMatch3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch3, 2);
                return true;
            }
        });

        cardMatch3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch3, imgR);
            }
        });

        //---------------------------------------------------------------------------

        cardMatch4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch4, 3);
                return true;
            }
        });

        cardMatch4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch4, imgP);
            }
        });

        //---------------------------------------------------------------------------

        cardMatch5.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch5, 4);
                return true;
            }
        });

        cardMatch5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch5, imgQ);
            }
        });

        //---------------------------------------------------------------------------

        cardMatch6.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch6, 5);
                return true;
            }
        });

        cardMatch6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch6, imgR1);
            }
        });

        //---------------------------------------------------------------------------

        cardMatch7.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch7, 6);
                return true;
            }
        });

        cardMatch7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch7, imgS);
            }
        });

        //---------------------------------------------------------------------------

        cardMatch8.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch8, 7);
                return true;
            }
        });

        cardMatch8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch8, imgT);
            }
        });

        //---------------------------------------------------------------------------

        cardMatch9.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch9, 8);
                return true;
            }
        });

        cardMatch9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch9, imgU);
            }
        });

        //---------------------------------------------------------------------------




        homeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeMenuCardView.animate().translationY(0);
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);

                effectsPlayer = MediaPlayer.create(InGameActivity.this, R.raw.resume);
                effectsPlayer.setVolume(0f, 0.8f);
                effectsPlayer.start();
            }
        });
    }

    private void resumeGame() {
        effectsPlayer = MediaPlayer.create(InGameActivity.this, R.raw.resume);
        effectsPlayer.setVolume(0f, 0.8f);
        effectsPlayer.start();
        pauseMenuCardView.animate().translationY(1200);
        cardMatch1.setEnabled(true);
        cardMatch2.setEnabled(true);
        cardMatch3.setEnabled(true);
        cardMatch4.setEnabled(true);
        cardMatch5.setEnabled(true);
        cardMatch6.setEnabled(true);
        cardMatch7.setEnabled(true);
        cardMatch8.setEnabled(true);
        cardMatch9.setEnabled(true);

        homeAction.setEnabled(true);
        backgroundMusic.start();
        beginGameTimer(remainimgTime);

        handler=new Handler();
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);

    }

    private void beginGameTimer(long time) {
//        long levelTime = remainimgTime + earnedTime;
        gameRunningCountDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (gameRestartingInfo.getVisibility() == View.GONE)
                    gameRestartingInfo.setVisibility(View.VISIBLE);
                remainimgTime = millisUntilFinished;
                gameRestartingInfo.setText("Remaining: " + millisUntilFinished / 1000 + " sec");
            }

            @Override
            public void onFinish() {

                remainimgTime=10000;
                halt();
                //Toast.makeText(InGameActivity.this, "Game over", Toast.LENGTH_SHORT).show();
                gameOverMenuCardView.animate().translationY(0);
                gameRunningCountDownTimer.cancel();
                backgroundMusic.setVolume(0f, 0.8f);
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        halt();
    }

    private void halt() {
        backgroundMusic.pause();
        effectsPlayer = MediaPlayer.create(InGameActivity.this, R.raw.pause);
        effectsPlayer.setVolume(0f, 0.8f);
        effectsPlayer.start();
        startGameTextView.setText("Resume");


    }

    private void bindingViews() {
        mContentView = findViewById(R.id.parent_game_view);
        pauseMenuCardView = findViewById(R.id.pause_menu_card_view);
        winMenuCardView = findViewById(R.id.win_menu_card_view);
        gameOverMenuCardView = findViewById(R.id.game_over_menu_card_view);
        homeMenuCardView = findViewById(R.id.home_menu_card_view);
        resumeOption = findViewById(R.id.start_resume_Option);
        exitOption = findViewById(R.id.exit_option);
        startGameTextView = findViewById(R.id.start_game_text_view);
        earnedTimeTextView = findViewById(R.id.time_earned_test_view);

        imgM = findViewById(R.id.imageView1);
        imgL = findViewById(R.id.imageView2);
        imgR = findViewById(R.id.imageView3);
        imgP = findViewById(R.id.imageView4);
        imgQ = findViewById(R.id.imageView5);
        imgR1 = findViewById(R.id.imageView6);
        imgS = findViewById(R.id.imageView7);
        imgT = findViewById(R.id.imageView8);
        imgU = findViewById(R.id.imageView9);

        imgCMP1 = findViewById(R.id.compare_image_1);
        imgCMP2 = findViewById(R.id.compare_image_2);
        imgCMP3 = findViewById(R.id.compare_image_3);
        imgCMP4 = findViewById(R.id.compare_image_4);
        imgCMP5 = findViewById(R.id.compare_image_5);
        imgCMP6 = findViewById(R.id.compare_image_6);
        imgCMP7 = findViewById(R.id.compare_image_7);
        imgCMP8 = findViewById(R.id.compare_image_8);
        imgCMP9 = findViewById(R.id.compare_image_9);


        cardConfirm1 = findViewById(R.id.card_confirm_1);
        cardConfirm2 = findViewById(R.id.card_confirm_2);
        cardConfirm3 = findViewById(R.id.card_confirm_3);
        cardConfirm4 = findViewById(R.id.card_confirm_4);
        cardConfirm5 = findViewById(R.id.card_confirm_5);
        cardConfirm6 = findViewById(R.id.card_confirm_6);
        cardConfirm7 = findViewById(R.id.card_confirm_7);
        cardConfirm8 = findViewById(R.id.card_confirm_8);
        cardConfirm9 = findViewById(R.id.card_confirm_9);

        cardMatch1 = findViewById(R.id.card_match_1);
        cardMatch2 = findViewById(R.id.card_match_2);
        cardMatch3 = findViewById(R.id.card_match_3);
        cardMatch4 = findViewById(R.id.card_match_4);
        cardMatch5 = findViewById(R.id.card_match_5);
        cardMatch6 = findViewById(R.id.card_match_6);
        cardMatch7 = findViewById(R.id.card_match_7);
        cardMatch8 = findViewById(R.id.card_match_8);
        cardMatch9 = findViewById(R.id.card_match_9);

        lottiewView1 = findViewById(R.id.lottie_view_1);
        lottiewView2 = findViewById(R.id.lottie_view_2);
        lottiewView3 = findViewById(R.id.lottie_view_3);
        lottiewView4 = findViewById(R.id.lottie_view_4);
        lottiewView5 = findViewById(R.id.lottie_view_5);
        lottiewView6 = findViewById(R.id.lottie_view_6);
        lottiewView7 = findViewById(R.id.lottie_view_7);
        lottiewView8 = findViewById(R.id.lottie_view_8);
        lottiewView9 = findViewById(R.id.lottie_view_9);

        homeAction = findViewById(R.id.home_action);

        gameRestartingInfo = findViewById(R.id.game_restarting_info);
    }

    private void setUpNumbers() {
        randomNumberList = new ArrayList<>();
        suffledNumberList = new ArrayList<>();
        completedNumber = new ArrayList<>();

        while (randomNumberList.size() < 9) {
            int newNumber = (int) (Math.random() * 9 + 0);
            if (!randomNumberList.contains(newNumber)) {
                randomNumberList.add(newNumber);
                suffledNumberList.add(newNumber);

            }
        }
        //Collections.shuffle(randomNumberList);



        String num1 = String.valueOf(randomNumberList.get(0));
        String num2 = String.valueOf(randomNumberList.get(1));
        String num3 = String.valueOf(randomNumberList.get(2));
        String num4 = String.valueOf(randomNumberList.get(3));
        String num5 = String.valueOf(randomNumberList.get(4));
        String num6 = String.valueOf(randomNumberList.get(5));
        String num7 = String.valueOf(randomNumberList.get(6));
        String num8 = String.valueOf(randomNumberList.get(7));
        String num9 = String.valueOf(randomNumberList.get(8));

        imgCMP1.setText(num1);
        imgCMP2.setText(num2);
        imgCMP3.setText(num3);
        imgCMP4.setText(num4);
        imgCMP5.setText(num5);
        imgCMP6.setText(num6);
        imgCMP7.setText(num7);
        imgCMP8.setText(num8);
        imgCMP9.setText(num9);



        //---------------------------------------------------------
        if(num1.equals("0")) {
            String uri1 = "@drawable/img1";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.img1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num1.equals("1")){
            String uri1 = "@drawable/img2";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.img1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num1.equals("2")){
            String uri1 = "@drawable/img3";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.img1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num1.equals("3")){
            String uri1 = "@drawable/img4";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.img1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num1.equals("4")){
            String uri1 = "@drawable/img5";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.img1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num1.equals("5")){
            String uri1 = "@drawable/img6";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.img1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num1.equals("6")){
            String uri1 = "@drawable/img7";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.img1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num1.equals("7")){
            String uri1 = "@drawable/img8";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.img1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num1.equals("8")){
            String uri1 = "@drawable/img9";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.img1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }
        //---------------------------------------------------------

        //---------------------------------------------------------
        if(num2.equals("0")) {
            String uri2 = "@drawable/img1";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.img2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num2.equals("1")){
            String uri2 = "@drawable/img2";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.img2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num2.equals("2")){
            String uri2 = "@drawable/img3";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.img2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num2.equals("3")){
            String uri2 = "@drawable/img4";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.img2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num2.equals("4")){
            String uri2 = "@drawable/img5";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.img2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num2.equals("5")){
            String uri2 = "@drawable/img6";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.img2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num2.equals("6")){
            String uri2 = "@drawable/img7";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.img2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num2.equals("7")){
            String uri2 = "@drawable/img8";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.img2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num2.equals("8")){
            String uri2 = "@drawable/img9";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.img2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }
        //---------------------------------------------------------

        //---------------------------------------------------------
        if(num3.equals("0")) {
            String uri3 = "@drawable/img1";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.img3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num3.equals("1")){
            String uri3 = "@drawable/img2";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.img3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num3.equals("2")){
            String uri3 = "@drawable/img3";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.img3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num3.equals("3")){
            String uri3 = "@drawable/img4";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.img3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num3.equals("4")){
            String uri3 = "@drawable/img5";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.img3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num3.equals("5")){
            String uri3 = "@drawable/img6";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.img3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num3.equals("6")){
            String uri3 = "@drawable/img7";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.img3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num3.equals("7")){
            String uri3 = "@drawable/img8";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.img3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num3.equals("8")){
            String uri3 = "@drawable/img9";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.img3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }
        //---------------------------------------------------------

        //---------------------------------------------------------
        if(num4.equals("0")) {
            String uri4 = "@drawable/img1";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.img4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }else if(num4.equals("1")){
            String uri4 = "@drawable/img2";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.img4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }else if(num4.equals("2")){
            String uri4 = "@drawable/img3";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.img4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }else if(num4.equals("3")){
            String uri4 = "@drawable/img4";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.img4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }else if(num4.equals("4")){
            String uri4 = "@drawable/img5";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.img4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }else if(num4.equals("5")){
            String uri4 = "@drawable/img6";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.img4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }else if(num4.equals("6")){
            String uri4 = "@drawable/img7";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.img4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }else if(num4.equals("7")){
            String uri4 = "@drawable/img8";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.img4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }else if(num4.equals("8")){
            String uri4 = "@drawable/img9";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.img4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }
        //---------------------------------------------------------

        //------------------555---------------------------------------
        if(num5.equals("0")) {
            String uri5 = "@drawable/img1";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.img5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num5.equals("1")){
            String uri5 = "@drawable/img2";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.img5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num5.equals("2")){
            String uri5 = "@drawable/img3";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.img5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num5.equals("3")){
            String uri5 = "@drawable/img4";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.img5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num5.equals("4")){
            String uri5 = "@drawable/img5";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.img5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num5.equals("5")){
            String uri5 = "@drawable/img6";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.img5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num5.equals("6")){
            String uri5 = "@drawable/img7";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.img5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num5.equals("7")){
            String uri5 = "@drawable/img8";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.img5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num5.equals("8")){
            String uri5 = "@drawable/img9";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.img5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }
        //---------------------------------------------------------

        //------------------66---------------------------------------
        if(num6.equals("0")) {
            String uri6 = "@drawable/img1";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.img6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num6.equals("1")){
            String uri6 = "@drawable/img2";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.img6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num6.equals("2")){
            String uri6 = "@drawable/img3";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.img6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num6.equals("3")){
            String uri6 = "@drawable/img4";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.img6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num6.equals("4")){
            String uri6 = "@drawable/img5";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.img6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num6.equals("5")){
            String uri6 = "@drawable/img6";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.img6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num6.equals("6")){
            String uri6 = "@drawable/img7";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.img6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num6.equals("7")){
            String uri6 = "@drawable/img8";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.img6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num6.equals("8")){
            String uri6 = "@drawable/img9";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.img6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }

        //---------------------------------------------------------


        //-----------------77--------------------------------------
        if(num7.equals("0")) {
            String uri7 = "@drawable/img1";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.img7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num7.equals("1")){
            String uri7 = "@drawable/img2";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.img7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num7.equals("2")){
            String uri7 = "@drawable/img3";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.img7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num7.equals("3")){
            String uri7 = "@drawable/img4";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.img7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num7.equals("4")){
            String uri7 = "@drawable/img5";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.img7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num7.equals("5")){
            String uri7 = "@drawable/img6";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.img7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num7.equals("6")){
            String uri7 = "@drawable/img7";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.img7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num7.equals("7")){
            String uri7 = "@drawable/img8";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.img7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num7.equals("8")){
            String uri7 = "@drawable/img9";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.img7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }

        //---------------------------------------------------------

        //-----------------88--------------------------------------
        if(num8.equals("0")) {
            String uri8 = "@drawable/img1";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8 = findViewById(R.id.img8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num8.equals("1")){
            String uri8 = "@drawable/img2";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8= findViewById(R.id.img8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num8.equals("2")){
            String uri8 = "@drawable/img3";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8= findViewById(R.id.img8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num8.equals("3")){
            String uri8 = "@drawable/img4";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8= findViewById(R.id.img8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num8.equals("4")){
            String uri8 = "@drawable/img5";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8= findViewById(R.id.img8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num8.equals("5")){
            String uri8 = "@drawable/img6";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8= findViewById(R.id.img8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num8.equals("6")){
            String uri8 = "@drawable/img7";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8= findViewById(R.id.img8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num8.equals("7")){
            String uri8 = "@drawable/img8";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8= findViewById(R.id.img8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num8.equals("8")){
            String uri8 = "@drawable/img9";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8= findViewById(R.id.img8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }

        //---------------------------------------------------------

        //-----------------99--------------------------------------
        if(num9.equals("0")) {
            String uri9 = "@drawable/img1";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.img9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }else if(num9.equals("1")){
            String uri9 = "@drawable/img2";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.img9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }else if(num9.equals("2")){
            String uri9 = "@drawable/img3";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.img9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }else if(num9.equals("3")){
            String uri9 = "@drawable/img4";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.img9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }else if(num9.equals("4")){
            String uri9 = "@drawable/img5";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.img9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }else if(num9.equals("5")){
            String uri9 = "@drawable/img6";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.img9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }else if(num9.equals("6")){
            String uri9 = "@drawable/img7";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.img9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }else if(num9.equals("7")){
            String uri9 = "@drawable/img8";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.img9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }else if(num9.equals("8")){
            String uri9 = "@drawable/img9";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.img9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }

        //---------------------------------------------------------


        //Collections.shuffle(suffledNumberList);

        int num_match1 = suffledNumberList.get(0);
        int num_match2 = suffledNumberList.get(1);
        int num_match3 = suffledNumberList.get(2);
        int num_match4 = suffledNumberList.get(3);
        int num_match5 = suffledNumberList.get(4);
        int num_match6 = suffledNumberList.get(5);
        int num_match7 = suffledNumberList.get(6);
        int num_match8 = suffledNumberList.get(7);
        int num_match9 = suffledNumberList.get(8);



        imgL.setText(String.valueOf(num_match1));
        imgM.setText(String.valueOf(num_match2));
        imgR.setText(String.valueOf(num_match3));
        imgP.setText(String.valueOf(num_match4));
        imgQ.setText(String.valueOf(num_match5));
        imgR1.setText(String.valueOf(num_match6));
        imgS.setText(String.valueOf(num_match7));
        imgT.setText(String.valueOf(num_match8));
        imgU.setText(String.valueOf(num_match9));



        //--------------------11-------------------------------------
        if(num_match1 == 0) {
            String uri1 = "@drawable/img_match1";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.imgMatch1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);

        }else if(num_match1==1){
            String uri1 = "@drawable/img_match2";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.imgMatch1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num_match1==2){
            String uri1 = "@drawable/img_match3";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.imgMatch1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num_match1==3){
            String uri1 = "@drawable/img_match4";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.imgMatch1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num_match1==4){
            String uri1 = "@drawable/img_match5";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.imgMatch1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num_match1==5){
            String uri1 = "@drawable/img_match6";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.imgMatch1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num_match1==6){
            String uri1 = "@drawable/img_match7";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.imgMatch1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num_match1==7){
            String uri1 = "@drawable/img_match8";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.imgMatch1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }else if(num_match1==8){
            String uri1 = "@drawable/img_match9";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            ImageView imgView1 = findViewById(R.id.imgMatch1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            imgView1.setImageDrawable(res1);
        }
        //---------------------------------------------------------

        //------------------------22---------------------------------
        if(num_match2 == 0) {
            String uri2 = "@drawable/img_match1";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.imgMatch2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);

        }else if(num_match2==1){
            String uri2 = "@drawable/img_match2";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.imgMatch2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num_match2==2){
            String uri2 = "@drawable/img_match3";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.imgMatch2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num_match2==3){
            String uri2 = "@drawable/img_match4";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.imgMatch2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num_match2==4){
            String uri2 = "@drawable/img_match5";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.imgMatch2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num_match2==5){
            String uri2 = "@drawable/img_match6";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.imgMatch2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num_match2==6){
            String uri2 = "@drawable/img_match7";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.imgMatch2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num_match2==7){
            String uri2 = "@drawable/img_match8";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.imgMatch2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }else if(num_match2==8){
            String uri2 = "@drawable/img_match9";
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
            ImageView imgView2 = findViewById(R.id.imgMatch2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            imgView2.setImageDrawable(res2);
        }
        //---------------------------------------------------------

        //------------------------33---------------------------------
        if(num_match3 == 0) {
            String uri3 = "@drawable/img_match1";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.imgMatch3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);

        }else if(num_match3==1){
            String uri3 = "@drawable/img_match2";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.imgMatch3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num_match3==2){
            String uri3 = "@drawable/img_match3";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.imgMatch3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num_match3==3){
            String uri3 = "@drawable/img_match4";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.imgMatch3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num_match3==4){
            String uri3 = "@drawable/img_match5";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.imgMatch3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num_match3==5){
            String uri3 = "@drawable/img_match6";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.imgMatch3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num_match3==6){
            String uri3 = "@drawable/img_match7";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.imgMatch3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num_match3==7){
            String uri3 = "@drawable/img_match8";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.imgMatch3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }else if(num_match3==8){
            String uri3 = "@drawable/img_match9";
            int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
            ImageView imgView3 = findViewById(R.id.imgMatch3);
            Drawable res3 = getResources().getDrawable(imageResource3);
            imgView3.setImageDrawable(res3);
        }

        //---------------------------------------------------------

        //------------------------44---------------------------------
        if(num_match4 == 0) {
            String uri4 = "@drawable/img_match1";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.imgMatch4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);

        }else if(num_match4==1){
            String uri4 = "@drawable/img_match2";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.imgMatch4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }else if(num_match4==2){
            String uri4 = "@drawable/img_match3";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.imgMatch4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }
        else if(num_match4==3){
            String uri4 = "@drawable/img_match4";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.imgMatch4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }
        else if(num_match4==4){
            String uri4 = "@drawable/img_match5";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.imgMatch4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }
        else if(num_match4==5){
            String uri4 = "@drawable/img_match6";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.imgMatch4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }
        else if(num_match4==6){
            String uri4 = "@drawable/img_match7";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.imgMatch4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }
        else if(num_match4==7){
            String uri4 = "@drawable/img_match8";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.imgMatch4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }
        else if(num_match4==8){
            String uri4 = "@drawable/img_match9";
            int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
            ImageView imgView4 = findViewById(R.id.imgMatch4);
            Drawable res4 = getResources().getDrawable(imageResource4);
            imgView4.setImageDrawable(res4);
        }
        //---------------------------------------------------------

        //------------------------55---------------------------------
        if(num_match5 == 0) {
            String uri5 = "@drawable/img_match1";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.imgMatch5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);

        }else if(num_match5==1){
            String uri5 = "@drawable/img_match2";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.imgMatch5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num_match5==2){
            String uri5 = "@drawable/img_match3";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.imgMatch5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num_match5==3){
            String uri5 = "@drawable/img_match4";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.imgMatch5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num_match5==4){
            String uri5 = "@drawable/img_match5";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.imgMatch5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num_match5==5){
            String uri5 = "@drawable/img_match6";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.imgMatch5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num_match5==6){
            String uri5 = "@drawable/img_match7";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.imgMatch5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num_match5==7){
            String uri5 = "@drawable/img_match8";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.imgMatch5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }else if(num_match5==8){
            String uri5 = "@drawable/img_match9";
            int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
            ImageView imgView5 = findViewById(R.id.imgMatch5);
            Drawable res5 = getResources().getDrawable(imageResource5);
            imgView5.setImageDrawable(res5);
        }

        //---------------------------------------------------------

        //------------------------66--------------------------------
        if(num_match6 == 0) {
            String uri6 = "@drawable/img_match1";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.imgMatch6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);

        }else if(num_match6==1){
            String uri6 = "@drawable/img_match2";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.imgMatch6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num_match6==2){
            String uri6 = "@drawable/img_match3";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.imgMatch6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num_match6==3){
            String uri6 = "@drawable/img_match4";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.imgMatch6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num_match6==4){
            String uri6 = "@drawable/img_match5";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.imgMatch6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num_match6==5){
            String uri6 = "@drawable/img_match6";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.imgMatch6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num_match6==6){
            String uri6 = "@drawable/img_match7";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.imgMatch6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num_match6==7){
            String uri6 = "@drawable/img_match8";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.imgMatch6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }else if(num_match6==8){
            String uri6 = "@drawable/img_match9";
            int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
            ImageView imgView6 = findViewById(R.id.imgMatch6);
            Drawable res6 = getResources().getDrawable(imageResource6);
            imgView6.setImageDrawable(res6);
        }

        //---------------------------------------------------------

        //------------------------77--------------------------------
        if(num_match7 == 0) {
            String uri7 = "@drawable/img_match1";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.imgMatch7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);

        }else if(num_match7==1){
            String uri7 = "@drawable/img_match2";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.imgMatch7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num_match7==2){
            String uri7 = "@drawable/img_match3";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.imgMatch7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num_match7==3){
            String uri7 = "@drawable/img_match4";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.imgMatch7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num_match7==4){
            String uri7 = "@drawable/img_match5";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.imgMatch7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num_match7==5){
            String uri7 = "@drawable/img_match6";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.imgMatch7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num_match7==6){
            String uri7 = "@drawable/img_match7";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.imgMatch7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num_match7==7){
            String uri7 = "@drawable/img_match8";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.imgMatch7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }else if(num_match7==8){
            String uri7 = "@drawable/img_match9";
            int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
            ImageView imgView7 = findViewById(R.id.imgMatch7);
            Drawable res7 = getResources().getDrawable(imageResource7);
            imgView7.setImageDrawable(res7);
        }

        //---------------------------------------------------------

        //------------------------88--------------------------------
        if(num_match8== 0) {
            String uri8 = "@drawable/img_match1";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8 = findViewById(R.id.imgMatch8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);

        }else if(num_match8==1){
            String uri8 = "@drawable/img_match2";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8 = findViewById(R.id.imgMatch8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num_match8==2){
            String uri8 = "@drawable/img_match3";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8 = findViewById(R.id.imgMatch8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num_match8==3){
            String uri8 = "@drawable/img_match4";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8 = findViewById(R.id.imgMatch8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num_match8==4){
            String uri8 = "@drawable/img_match5";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8 = findViewById(R.id.imgMatch8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num_match8==5){
            String uri8 = "@drawable/img_match6";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8 = findViewById(R.id.imgMatch8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num_match8==6){
            String uri8 = "@drawable/img_match7";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8 = findViewById(R.id.imgMatch8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num_match8==7){
            String uri8 = "@drawable/img_match8";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8 = findViewById(R.id.imgMatch8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }else if(num_match8==8){
            String uri8 = "@drawable/img_match9";
            int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
            ImageView imgView8 = findViewById(R.id.imgMatch8);
            Drawable res8 = getResources().getDrawable(imageResource8);
            imgView8.setImageDrawable(res8);
        }

        //---------------------------------------------------------

        //-----------------------99--------------------------------
        if(num_match9== 0) {
            String uri9 = "@drawable/img_match1";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.imgMatch9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);

        }else if(num_match9==1){
            String uri9 = "@drawable/img_match2";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.imgMatch9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }else if(num_match9==2){
            String uri9 = "@drawable/img_match3";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.imgMatch9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }
        else if(num_match9==3){
            String uri9 = "@drawable/img_match4";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.imgMatch9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }
        else if(num_match9==4){
            String uri9 = "@drawable/img_match5";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.imgMatch9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }
        else if(num_match9==5){
            String uri9 = "@drawable/img_match6";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.imgMatch9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }
        else if(num_match9==6){
            String uri9 = "@drawable/img_match7";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.imgMatch9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }
        else if(num_match9==7){
            String uri9 = "@drawable/img_match8";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.imgMatch9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }
        else if(num_match9==8){
            String uri9 = "@drawable/img_match9";
            int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
            ImageView imgView9 = findViewById(R.id.imgMatch9);
            Drawable res9 = getResources().getDrawable(imageResource9);
            imgView9.setImageDrawable(res9);
        }


        cardMatch1.setAlpha(1);
        cardMatch2.setAlpha(1);
        cardMatch3.setAlpha(1);
        cardMatch4.setAlpha(1);
        cardMatch5.setAlpha(1);
        cardMatch6.setAlpha(1);
        cardMatch7.setAlpha(1);
        cardMatch8.setAlpha(1);
        cardMatch9.setAlpha(1);

    }

    private boolean onTouch(MotionEvent event, CardView cardView, TextView tv) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(cardView);
            int number = Integer.parseInt(tv.getText().toString());
            if (!completedNumber.contains(number))
                cardView.startDrag(data, shadowBuilder, cardView, View.DRAG_FLAG_OPAQUE);
            return true;
        } else {
            return false;
        }
    }

    private void onDrag(View v, DragEvent event, CardView cardView, int position) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
                Log.d(TAG, "DragEvent.ACTION_DRAG_STARTED");

                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                if (!dragging) {
                    dragging = true;
                    draggingView = cardView;
                    draggingNumber = suffledNumberList.get(position);
                    cardView.setVisibility(View.INVISIBLE);

                } else {
                    onDragEndedNumber = randomNumberList.get(position);
                    Log.d(TAG, " onDragEndedNumber- " + onDragEndedNumber);
                }
                Log.d(TAG, "DragEvent.ACTION_DRAG_ENTERED");
                Log.d(TAG, "dragging - " + draggingNumber);
                int x_cord = (int) event.getX();
                int y_cord = (int) event.getY();

                break;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.d(TAG, "DragEvent.ACTION_DRAG_EXITED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                layoutParams.leftMargin = x_cord;
                layoutParams.topMargin = y_cord;
                v.setLayoutParams(layoutParams);
                break;

            case DragEvent.ACTION_DRAG_LOCATION:
//                Log.d(msg, "DragEvent.ACTION_DRAG_LOCATION");
//                x_cord = (int) event.getX();
//                y_cord = (int) event.getY();
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                dragging = false;
                Log.d(TAG, "DragEvent.ACTION_DRAG_ENDED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                cardView.setVisibility(View.VISIBLE);
                // Do nothing
                break;

            case DragEvent.ACTION_DROP:
                Log.d(TAG, "ACTION_DROP");
                SharedPreferences.Editor editor = sharedpreferences.edit();

                int one = sharedpreferences.getInt(One, 0);
                int two = sharedpreferences.getInt(Two, 0);
                int three = sharedpreferences.getInt(Three, 0);
                int four = sharedpreferences.getInt(Four, 0);
                int five = sharedpreferences.getInt(Five, 0);
                int six = sharedpreferences.getInt(Six, 0);
                int seven = sharedpreferences.getInt(Seven, 0);
                int eight = sharedpreferences.getInt(Eight, 0);
                int nine = sharedpreferences.getInt(Nine, 0);

                int oneMatch = sharedpreferences.getInt(OneMatch, 0);
                int twoMatch = sharedpreferences.getInt(TwoMatch, 0);
                int threeMatch = sharedpreferences.getInt(ThreeMatch, 0);
                int fourMatch = sharedpreferences.getInt(FourMatch, 0);
                int fiveMatch = sharedpreferences.getInt(FiveMatch, 0);
                int sixMatch = sharedpreferences.getInt(SixMatch, 0);
                int sevenMatch = sharedpreferences.getInt(SevenMatch, 0);
                int eightMatch = sharedpreferences.getInt(EightMatch, 0);
                int nineMatch = sharedpreferences.getInt(NineMatch, 0);

                int TotCount = one+two+three+four+five+six+seven+eight+nine+1;
                int TotCountMatch = oneMatch+twoMatch+threeMatch+fourMatch+fiveMatch+sixMatch+sevenMatch+eightMatch+nineMatch+1;

                TextView dropCount = findViewById(R.id.total_score);
                dropCount.setText(String.valueOf("Total Score: "+TotCountMatch));

                if(TotCountMatch == 9){
                    String uri_win = "@drawable/win_text";
                    int imageResource_win = getResources().getIdentifier(uri_win, null, getPackageName());
                    ImageView imgView_win = findViewById(R.id.winMsg);
                    Drawable res_win = getResources().getDrawable(imageResource_win);
                    imgView_win.setImageDrawable(res_win);
                }else{
                    String uri_win = "@drawable/faild_text_img";
                    int imageResource_win = getResources().getIdentifier(uri_win, null, getPackageName());
                    ImageView imgView_win = findViewById(R.id.winMsg);
                    Drawable res_win = getResources().getDrawable(imageResource_win);
                    imgView_win.setImageDrawable(res_win);


                }

                if(TotCount == 9){
                    winMenuCardView.animate().translationY(0);
                    gameRunningCountDownTimer.cancel();

                    //pouse Timer;
                    TimeBuff += MillisecondTime;
                    handler.removeCallbacks(runnable);

                    if(TotCountMatch==9){
                        effectsPlayer = MediaPlayer.create(this, R.raw.win_sound);
                        effectsPlayer.setVolume(1, 1);
                        effectsPlayer.start();
                    }else{
                        effectsPlayer = MediaPlayer.create(this, R.raw.error);
                        effectsPlayer.setVolume(0f, 0.8f);
                        effectsPlayer.start();
                    }
                }

                if (draggingView != null && !viewsToMatch.contains(cardView)) {

                    int num = randomNumberList.get(position);
                    if (draggingNumber == num) {
                        Log.d(TAG, "on DROP event dragging no- " + draggingNumber + " num -" + num);
                        draggingView.setAlpha(0);
                        completedNumber.add(draggingNumber);
                        playTaskAchievedSoundEffect(true);
                        switch (position) {
                            case 0:

                                String uri1 = "@drawable/tik_img";
                                int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
                                ImageView imgView1 = findViewById(R.id.imgMatch1);
                                Drawable res1 = getResources().getDrawable(imageResource1);
                                imgView1.setImageDrawable(res1);
                                lottiewView1.playAnimation();

                                editor.putInt(One, 1);
                                editor.putInt(OneMatch, 1);
                                editor.apply();

                                break;
                            case 1:

                                String uri2 = "@drawable/tik_img";
                                int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
                                ImageView imgView2 = findViewById(R.id.imgMatch2);
                                Drawable res2 = getResources().getDrawable(imageResource2);
                                imgView2.setImageDrawable(res2);
                                lottiewView2.playAnimation();

                                editor.putInt(Two, 1);
                                editor.putInt(TwoMatch, 1);
                                editor.apply();

                                break;

                            case 2:

                                String uri3 = "@drawable/tik_img";
                                int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
                                ImageView imgView3= findViewById(R.id.imgMatch3);
                                Drawable res3 = getResources().getDrawable(imageResource3);
                                imgView3.setImageDrawable(res3);
                                lottiewView3.playAnimation();

                                editor.putInt(Three, 1);
                                editor.putInt(ThreeMatch, 1);
                                editor.apply();
                                break;

                            case 3:

                                String uri4 = "@drawable/tik_img";
                                int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
                                ImageView imgView4 = findViewById(R.id.imgMatch4);
                                Drawable res4 = getResources().getDrawable(imageResource4);
                                imgView4.setImageDrawable(res4);
                                lottiewView4.playAnimation();

                                editor.putInt(Four, 1);
                                editor.putInt(FourMatch, 1);
                                editor.apply();
                                break;

                            case 4:

                                String uri5 = "@drawable/tik_img";
                                int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
                                ImageView imgView5 = findViewById(R.id.imgMatch5);
                                Drawable res5 = getResources().getDrawable(imageResource5);
                                imgView5.setImageDrawable(res5);
                                lottiewView5.playAnimation();

                                editor.putInt(Five, 1);
                                editor.putInt(FiveMatch, 1);
                                editor.apply();
                                break;

                            case 5:

                                String uri6 = "@drawable/tik_img";
                                int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
                                ImageView imgView6 = findViewById(R.id.imgMatch6);
                                Drawable res6 = getResources().getDrawable(imageResource6);
                                imgView6.setImageDrawable(res6);
                                lottiewView6.playAnimation();

                                editor.putInt(Six, 1);
                                editor.putInt(SixMatch, 1);
                                editor.apply();
                                break;

                            case 6:

                                String uri7 = "@drawable/tik_img";
                                int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
                                ImageView imgView7 = findViewById(R.id.imgMatch7);
                                Drawable res7 = getResources().getDrawable(imageResource7);
                                imgView7.setImageDrawable(res7);
                                lottiewView7.playAnimation();

                                editor.putInt(Seven, 1);
                                editor.putInt(SevenMatch, 1);
                                editor.apply();
                                break;

                            case 7:

                                String uri8 = "@drawable/tik_img";
                                int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
                                ImageView imgView8 = findViewById(R.id.imgMatch8);
                                Drawable res8 = getResources().getDrawable(imageResource8);
                                imgView8.setImageDrawable(res8);
                                lottiewView8.playAnimation();

                                editor.putInt(Eight, 1);
                                editor.putInt(EightMatch, 1);
                                editor.apply();
                                break;

                            case 8:

                                String uri9 = "@drawable/tik_img";
                                int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
                                ImageView imgView9 = findViewById(R.id.imgMatch9);
                                Drawable res9 = getResources().getDrawable(imageResource9);
                                imgView9.setImageDrawable(res9);
                                lottiewView9.playAnimation();

                                editor.putInt(Nine, 1);
                                editor.putInt(NineMatch, 1);
                                editor.apply();
                                break;

                            default:
                                break;

                        }

                        cardView.setVisibility(View.INVISIBLE);
                        matchedNumber += 1;
                        TextView pointText = findViewById(R.id.point_view);
                        String pointTxt = "Your Point: " + String.valueOf(matchedNumber);
                        pointText.setText(pointTxt);


                    } else {

                        playTaskAchievedSoundEffect(true);
                        draggingView.setAlpha(0);
                        switch (position) {
                            case 0:

                                String uri1 = "@drawable/tik_img";
                                int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
                                ImageView imgView1 = findViewById(R.id.imgMatch1);
                                Drawable res1 = getResources().getDrawable(imageResource1);
                                imgView1.setImageDrawable(res1);
                                lottiewView1.playAnimation();

                                editor.putInt(One, 1);
                                editor.apply();

                                break;
                            case 1:

                                String uri2 = "@drawable/tik_img";
                                int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
                                ImageView imgView2 = findViewById(R.id.imgMatch2);
                                Drawable res2 = getResources().getDrawable(imageResource2);
                                imgView2.setImageDrawable(res2);
                                lottiewView2.playAnimation();

                                editor.putInt(Two, 1);
                                editor.apply();

                                break;

                            case 2:

                                String uri3 = "@drawable/tik_img";
                                int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
                                ImageView imgView3= findViewById(R.id.imgMatch3);
                                Drawable res3 = getResources().getDrawable(imageResource3);
                                imgView3.setImageDrawable(res3);
                                lottiewView3.playAnimation();

                                editor.putInt(Three, 1);
                                editor.apply();
                                break;

                            case 3:

                                String uri4 = "@drawable/tik_img";
                                int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
                                ImageView imgView4 = findViewById(R.id.imgMatch4);
                                Drawable res4 = getResources().getDrawable(imageResource4);
                                imgView4.setImageDrawable(res4);
                                lottiewView4.playAnimation();

                                editor.putInt(Four, 1);
                                editor.apply();
                                break;

                            case 4:

                                String uri5 = "@drawable/tik_img";
                                int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
                                ImageView imgView5 = findViewById(R.id.imgMatch5);
                                Drawable res5 = getResources().getDrawable(imageResource5);
                                imgView5.setImageDrawable(res5);
                                lottiewView5.playAnimation();

                                editor.putInt(Five, 1);
                                editor.apply();
                                break;

                            case 5:

                                String uri6 = "@drawable/tik_img";
                                int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
                                ImageView imgView6 = findViewById(R.id.imgMatch6);
                                Drawable res6 = getResources().getDrawable(imageResource6);
                                imgView6.setImageDrawable(res6);
                                lottiewView6.playAnimation();

                                editor.putInt(Six, 1);
                                editor.apply();
                                break;

                            case 6:

                                String uri7 = "@drawable/tik_img";
                                int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
                                ImageView imgView7 = findViewById(R.id.imgMatch7);
                                Drawable res7 = getResources().getDrawable(imageResource7);
                                imgView7.setImageDrawable(res7);
                                lottiewView7.playAnimation();

                                editor.putInt(Seven, 1);
                                editor.apply();
                                break;

                            case 7:

                                String uri8 = "@drawable/tik_img";
                                int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
                                ImageView imgView8 = findViewById(R.id.imgMatch8);
                                Drawable res8 = getResources().getDrawable(imageResource8);
                                imgView8.setImageDrawable(res8);
                                lottiewView8.playAnimation();

                                editor.putInt(Eight, 1);
                                editor.apply();
                                break;

                            case 8:

                                String uri9 = "@drawable/tik_img";
                                int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
                                ImageView imgView9 = findViewById(R.id.imgMatch9);
                                Drawable res9 = getResources().getDrawable(imageResource9);
                                imgView9.setImageDrawable(res9);
                                lottiewView9.playAnimation();

                                editor.putInt(Nine, 1);
                                editor.apply();
                                break;



                            default:
                                break;

                        }

                    }
                }
                break;
            default:
                break;
        }
    }

    private void restartGame(long levelTime) {

        if (gameRunningCountDownTimer != null) {
            gameRunningCountDownTimer.cancel();
        }
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                gameRestartingInfo.setText("Restarting in " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                earnedTimeTextView.setVisibility(View.GONE);
                gameRestartingInfo.setVisibility(View.GONE);
                setUpNumbers();
                beginGameTimer(levelTime + earnedTime);
            }

        }.start();
    }

    private void playTaskAchievedSoundEffect(boolean success) {
        if (success) {
            effectsPlayer = MediaPlayer.create(this, R.raw.correct);
            effectsPlayer.setVolume(0f, 1);
        } else {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }
            effectsPlayer = MediaPlayer.create(this, R.raw.error);
            effectsPlayer.setVolume(0f, 0.2f);
        }
        effectsPlayer.start();
    }


}