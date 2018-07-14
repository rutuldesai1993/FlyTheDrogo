package com.example.hamza.flythedrogo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.content.Intent;


public class GameView extends View {

    // Canvas

    private int canvasWidth;
    private int canvasHeight;

    private boolean dialogDisplayed; // whether the game has ended
    //private Bitmap bird;
    private Bitmap dino[] = new Bitmap[2];
    private Bitmap prey[] = new Bitmap[3];
    private Bitmap antiprey[] = new Bitmap[2];
    private Bitmap meteor[] = new Bitmap[2];
    private int dinoX = 10;
    private int dinoY;
    private int dinoSpeed;


    private android.widget.RelativeLayout relativeLayout; // displays spots

   private TextView ScoreTextView; // displays current score
    private TextView levelTextView; // displays current level



    // Prey:
    private int preyX;
    private int preyY;
    private int preySpeed = 15;

    // Prey2:
    private int prey2X;
    private int prey2Y;
    private int prey2Speed = 17;

       // Prey3:
    //private int prey3X;
    //private int prey3Y;
   // private int prey3Speed = 25;

    // AntiPrey:
    private int apreyX;
    private int apreyY;
    private int apreySpeed = 15;

    //Meteor Ball
    private int meteorX;
    private int meteorY;
    private int meteorSpeed = 20;

    // Blue Meteor Ball
    private int bmeteorX;
    private int bmeteorY;
    private int bmeteorSpeed = 22;

    // Background
    private Bitmap bgImage;
    private Bitmap mBitmap;
    private Canvas mCanvas;

    // Score
    private Paint scorePaint = new Paint();
    private int score;

    // Level
    private Paint levelPaint = new Paint();
    private int level = 1;

    // Life
    private Bitmap life[] = new Bitmap[2];
    private int life_count;

    // Status Check
    private boolean touch_flg = false;


private void init(Context context) {

        dino[0] = BitmapFactory.decodeResource(getResources(), R.drawable.dino3);
        dino[1] = BitmapFactory.decodeResource(getResources(), R.drawable.dino4);

        meteor[0] = BitmapFactory.decodeResource(getResources(), R.drawable.meteor);
        meteor[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bmeteor);

        prey[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        prey[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bird2);
        //prey[2] = BitmapFactory.decodeResource(getResources(), R.drawable.bird2);

        antiprey[0] = BitmapFactory.decodeResource(getResources(), R.drawable.antiprey);

        bgImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);


        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(40);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        levelPaint.setColor(Color.BLACK);
        levelPaint.setTextSize(40);
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setTextAlign(Paint.Align.CENTER);
        levelPaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_g);

        // First position.
        dinoY = 500;
        score = 0;
        life_count = 3;
}

public GameView(Context context, android.util.AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(context);
}

public GameView(Context context, android.util.AttributeSet attrs ) {
    super(context, attrs);
    init(context);
}


    public GameView(Context context) {
        super(context);

        init(context);


    }


@Override
protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);

    // your Canvas will draw onto the defined Bitmap
    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    mCanvas = new Canvas(mBitmap);
}



    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
         //Paint paint2 = new Paint();
         //paint2.setColor(Color.WHITE);
         //paint2.setStyle(Paint.Style.FILL);
         //canvas.drawPaint(paint2);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();



        //canvas.drawBitmap(bgImage, 0, 0, null);

        // Dino:
        int minDinoY = dino[0].getHeight();
        int maxDinoY = canvasHeight - dino[0].getHeight() * 3;

        dinoY += dinoSpeed;
        if (dinoY < minDinoY) dinoY = minDinoY;
        if (dinoY > maxDinoY) dinoY = maxDinoY;
        dinoSpeed += 2;

        if (touch_flg) {
            // Flap wings.
            canvas.drawBitmap(dino[1], dinoX, dinoY, null);
            touch_flg = false;
        } else {
            canvas.drawBitmap(dino[0], dinoX, dinoY, null);
        }


        // prey1:
         if (level < 3)
        {
        preyX -= preySpeed;
        if (hitCheck(preyX, preyY)) {
            score += 20;
            if (score > 250)
            {
                 level = 2;
                 if (score > 500)
                 {
                     level = 3;
                       if (score > 1000)
                 {
                     level = 4;

                  }
                  }

             }
            preyX = -100;
        }
        if (preyX < 0) {
            preyX = canvasWidth + 20;
            preyY = (int) Math.floor(Math.random() * (maxDinoY - minDinoY)) + minDinoY;
        }
        canvas.drawBitmap(prey[0], preyX, preyY, null);
        }

        // prey2:
        if (level >= 2)
        {
        prey2X -= prey2Speed;
        if (hitCheck(prey2X, prey2Y)) {
            score += 30;

            if (score > 250)
            {
                 level = 2;
                 if (score > 500)
                 {
                     level = 3;
                           if (score > 1000)
                 {
                     level = 4;

                  }
                  }

             }
            prey2X = -100;
        }
        if (prey2X < 0) {
            prey2X = canvasWidth + 20;
            prey2Y = (int) Math.floor(Math.random() * (maxDinoY - minDinoY)) + minDinoY;
        }
        canvas.drawBitmap(prey[1], prey2X, prey2Y, null);

        }


        // prey2Double:

        /*
        if (level >= 2)
        {
        prey3X -= prey3Speed;
        if (hitCheck(prey3X, prey3Y)) {
            score += 30;

            if (score > 300)
            {
                 level = 2;
                 if (score > 600)
                 {
                     level = 3;
                  }

             }
            prey3X = -100;
        }
        if (prey3X < 0) {
            prey3X = canvasWidth + 20;
            prey3Y = (int) Math.floor(Math.random() * (maxDinoY - minDinoY)) + minDinoY;
        }
        canvas.drawBitmap(prey[2], prey3X, prey3Y, null);

        }
       */

        // anti-prey:
         if (level >= 3)
        {
        apreyX -= apreySpeed;
        if (hitCheck(apreyX, apreyY)) {
            score -= 10;

            apreyX = -100;
        }
        if (apreyX < 0) {
            apreyX = canvasWidth + 20;
            apreyY = (int) Math.floor(Math.random() * (maxDinoY - minDinoY)) + minDinoY;
        }
        canvas.drawBitmap(antiprey[0], apreyX, apreyY, null);
        }


        //Meteor:
        meteorX -= meteorSpeed;
        if (hitCheck(meteorX, meteorY)) {
            meteorX = -100;
            life_count--;
            if (life_count == 0)
            {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                dialogBuilder.setCancelable(false); // not SetCancelable(false). Case sensitive
                dialogBuilder.setTitle("Hard Luck!");
                dialogBuilder.setMessage("Drogo will fly again!"  + " Score:" + " " + score);

                AlertDialog.Builder restart = dialogBuilder.setPositiveButton("Restart",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                score = 0;
                                life_count = 3;
                                level =1;
                            } // end method onClick
                        } // end DialogInterface
                );// end call to dialogBuilder.setPositiveButton
                dialogDisplayed = true;

                dialogBuilder.show(); // display the reset game dialog
            }


        }

                if (meteorX < 0) {
            meteorX = canvasWidth + 200;
            meteorY = (int) Math.floor(Math.random() * (maxDinoY - minDinoY)) + minDinoY;
        }
        canvas.drawBitmap(meteor[0], meteorX, meteorY, null);


        //Blue-Meteor:
        if (level == 4)
            {

        bmeteorX -= bmeteorSpeed;
        if (hitCheck(bmeteorX, bmeteorY)) {
            bmeteorX = -100;
            life_count--;
            if (life_count == 0)
            {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                dialogBuilder.setCancelable(false);
                dialogBuilder.setTitle("Hard Luck!");
                dialogBuilder.setMessage("Drogo will fly again!"  + " Score:" + " " + score);
                AlertDialog.Builder restart = dialogBuilder.setPositiveButton("Restart",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                score = 0;
                                life_count = 3;
                                level =1;
                            } // end method onClick
                        } // end DialogInterface
                );// end call to dialogBuilder.setPositiveButton
                dialogDisplayed = true;
                dialogBuilder.show(); // display the reset game dialog
            }



        }
        if (bmeteorX < 0) {
            bmeteorX = canvasWidth + 200;
            bmeteorY = (int) Math.floor(Math.random() * (maxDinoY - minDinoY)) + minDinoY;
        }
        canvas.drawBitmap(meteor[1], bmeteorX, bmeteorY, null);
        }








        // Score
        canvas.drawText("Score: " + score, 500, 60, scorePaint);

        // Level
        canvas.drawText("Level: " + level, 300, 60, levelPaint);

        // Life
        for (int i = 0; i < 3; i++) {
            int x = (int) (20+ life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < life_count) {
                canvas.drawBitmap(life[0], x, y, null);
            } else {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }
    }

    public boolean hitCheck(int x, int y) {
        if (dinoX < x && x < (dinoX + dino[0].getWidth()) &&
                dinoY < y && y < (dinoY + dino[0].getHeight())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch_flg = true;
            dinoSpeed = -20;
        }
        return true;
    }

}

