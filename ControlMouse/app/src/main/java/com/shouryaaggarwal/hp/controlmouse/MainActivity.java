package com.shouryaaggarwal.hp.controlmouse;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    boolean checkSensor = false;

    MyButton top, topLeft, topRight, left, right, down, downLeft, downRight, shoot, reload;
    TextView textX, textY, textZ;
    SensorManager sensorManager;
    Sensor sensor;
    Handler handler = new Handler();

    float x,y,z;
    int moveForward, moveBack, moveLeft, moveRight, Fire, Rel;

    public static String host;
    private String resultant = "";
    private TextView ip_textview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top = findViewById(R.id.top);
        topLeft = findViewById(R.id.topLeft);
        topRight = findViewById(R.id.topRight);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        down = findViewById(R.id.down);
        downLeft = findViewById(R.id.downLeft);
        downRight = findViewById(R.id.downRight);
        shoot = findViewById(R.id.shoot);
        reload = findViewById(R.id.reload);

        //addListenerOnButton();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        textX = findViewById(R.id.textX);
        textY = findViewById(R.id.textY);
        textZ = findViewById(R.id.textZ);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ip_textview = findViewById(R.id.ip);
    }

    public void performAction() {
        resultant = String.format("%.1f", x)+" "+String.format("%.1f", y);
//                    +" "+Fire+" "+Rel+" "+moveForward+" "+moveBack+" "+moveLeft+" "+moveRight;
        new send_message().execute(resultant);
    }

    public void addListenerOnButton() {

        top.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    moveForward = 1;
                    performAction();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveForward = 0;
                    performAction();
                }

                return true;
            }
        });
        topLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    moveForward = 1;
                    moveLeft = 1;
                    performAction();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveForward = 0;
                    moveLeft = 0;
                    performAction();
                }

                return true;
            }
        });
        topRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    moveForward = 1;
                     moveRight = 1;
                    performAction();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveForward = 0;
                    moveRight = 0;
                    performAction();
                }

                return true;
            }
        });
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    moveLeft = 1;
                    performAction();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveLeft = 0;
                    performAction();
                }

                return true;
            }
        });
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    moveRight = 1;
                    performAction();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveRight = 0;
                    performAction();
                }

                return true;
            }
        });
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    moveBack = 1;
                    performAction();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveBack = 0;
                    performAction();
                }

                return true;
            }
        });
        downLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    moveBack = 1;
                    moveLeft = 1;
                    performAction();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveBack = 0;
                    moveLeft = 0;
                    performAction();
                }

                return true;
            }
        });
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    moveBack = 1;
                    moveRight = 1;
                    performAction();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveBack = 0;
                    moveRight = 0;
                    performAction();
                }

                return true;
            }
        });
        shoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Fire = 1;
                    performAction();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Fire = 0;
                    performAction();
                }

                return true;
            }
        });
        reload.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Rel = 1;
                    performAction();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Rel = 0;
                    performAction();
                }

                return true;
            }
        });
    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkSensor = true;
            }
        }, 100);
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(gyroListener);
    }

    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) {
        }

        public void onSensorChanged(SensorEvent event) {
            if (checkSensor) {
                checkSensor = false;
                x = event.values[0];
                y = event.values[1];
                z = event.values[2];

                textX.setText("X : " + String.format("%.1f", x) + " rad/s");
                textY.setText("Y : " + String.format("%.1f", y) + " rad/s");
                textZ.setText("Z : " + String.format("%.1f", z) + " rad/s");

                performAction();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkSensor = true;
                    }
                }, 100);
            }
        }
    };

    private class send_message extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Switch switch_view = findViewById(R.id.connect_switch);
            if (switch_view.isChecked()) {
                Log.d("Button", "sending data");
                try {
                    Socket socket;
                    DataOutputStream oos = null;

                    host = ip_textview.getText().toString();
                    socket = new Socket(host, 12346);

                    oos = new DataOutputStream(socket.getOutputStream());
                    oos.writeUTF(params[0]);
                    oos.flush();

                    oos.close();
                    socket.close();

                } catch (Exception e) {
                    Log.d("Exception", e.toString());
                }
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
