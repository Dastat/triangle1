package work.alex.triangle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast; /* пременная, что бы всплывающее окно "Нажмите еще раз, что бы выйти"
                                закрывалось вместе с приложением*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//указываем Java файлу с каким Layout'ом он будет работать

        //Убираем строку состояния - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Убираем строку состояния - конец

        //работа с картинками - начало
        final ImageView img_triangle = (ImageView)findViewById(R.id.img_triangle);//находим нужную картинку
        img_triangle.setImageResource(R.drawable.main_img_triangle_neon);
        final ImageView img_square = (ImageView)findViewById(R.id.img_square);//находим нужную картинку
        img_square.setImageResource(R.drawable.main_img_square_neon);
        final ImageView img_trapeze = (ImageView)findViewById(R.id.img_trapeze);//находим нужную картинку
        img_trapeze.setImageResource(R.drawable.main_img_trapeze_neon);
        final ImageView img_circle = (ImageView)findViewById(R.id.img_circle);//находим нужную картинку
        img_circle.setImageResource(R.drawable.main_img_circle_neon);

        final Animation a = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);//подключаем анимацию
        //работа с картинками - конец


        //обрабатываем нажатие катринок - начало
        //переход на выбор окна с методами вачисленй площади треугольника - начало
        img_triangle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //условие касания кнопки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //если коснулся кнопки - начало
                    img_triangle.setImageResource(R.drawable.main_img_triangle_neon_press);
                    //если коснулся кнопки - конец
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    Intent intent = new Intent(MainActivity.this, TriangleSelect.class);//намеренье перейки из одного окна в другое
                    startActivity(intent);//разрешаем намеренье
                    finish();
                    //Если отпустил палец - конец
                }
                //условие касания картинки - конец
                return true;
            }
        });
        //переход на выбор окна с методами вачисленй площади треугольника - конец

        //переход на выбор окна с методами вачисленй площади прямоугольника - начало
        img_square.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //условие касания кнопки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //если коснулся кнопки - начало
                    img_square.setImageResource(R.drawable.main_img_square_neon_press);
                    //если коснулся кнопки - конец
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    Intent intent = new Intent(MainActivity.this, SquareSelect.class);//намеренье перейки из одного окна в другое
                    startActivity(intent);//разрешаем намеренье
                    finish();
                    //Если отпустил палец - конец
                }
                //условие касания картинки - конец
                return true;
            }
        });
        //переход на выбор окна с методами вачисленй площади прямоугольника - конец

        //переход на выбор окна с методами вачисленй площади трапеции - начало
        img_trapeze.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //условие касания кнопки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //если коснулся кнопки - начало
                    img_trapeze.setImageResource(R.drawable.main_img_trapeze_neon_press);
                    //если коснулся кнопки - конец
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    Intent intent = new Intent(MainActivity.this, TrapezeSelect.class);//намеренье перейки из одного окна в другое
                    startActivity(intent);//разрешаем намеренье
                    finish();
                    //Если отпустил палец - конец
                }
                //условие касания картинки - конец
                return true;
            }
        });
        //переход на выбор окна с методами вачисленй площади трапеции - конец

        //переход на выбор окна с методами вачисленй площади окружности - начало
        img_circle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //условие касания кнопки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //если коснулся кнопки - начало
                    img_circle.setImageResource(R.drawable.main_img_circle_neon_press);
                    //если коснулся кнопки - конец
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    Intent intent = new Intent(MainActivity.this, CircleSelect.class);//намеренье перейки из одного окна в другое
                    startActivity(intent);//разрешаем намеренье
                    finish();
                    //Если отпустил палец - конец
                }
                //условие касания картинки - конец
                return true;
            }
        });
        //переход на выбор окна с методами вачисленй площади окружности - конец
        //обрабатываем нажатие катринок -конец
    }




    //______________________________________________________________________________________________
    //Системная кнопка "Назад" - начало (выход из игры)
        @Override
        public void onBackPressed() {
            if (backPressedTime + 2000 > System.currentTimeMillis()) { // если текущее время + 2 сек.> текущее время (2000 = 2 секунды) то выполняеется условие
               backToast.cancel();
               super.onBackPressed();////эта команда зкрывает игру
                return;
            } else {
                backToast= Toast.makeText(getBaseContext(), "Нажмите еще раз, что бы выйти", Toast.LENGTH_SHORT);
                backToast.show();
            }
            backPressedTime = System.currentTimeMillis();
        }
        //Системная кнопка "Назад" - конец
}