package work.alex.triangle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TrapezeSelect extends AppCompatActivity {
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triangle_select);

        //Убираем строку состояния - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Убираем строку состояния - конец

        //работа с картинками - начало
        final ImageView img_triangle = (ImageView)findViewById(R.id.img_triangle);//находим картинку
        img_triangle.setImageResource(R.drawable.main_img_trapeze);//заменяем катринку
        img_triangle.setClipToOutline(true);//скугляем углы у картинок
        final Animation a = AnimationUtils.loadAnimation(TrapezeSelect.this, R.anim.alpha);//подключаем анимацию
        //работа с картинками - начало

        //работа с кнопками - начало
        Button btn_method1 = (Button) findViewById(R.id.btn_method1);//находим кнопку
        btn_method1.setText(R.string.trapezeBasesHeight);//заменяем текст кнопки
        Button btn_method2 = (Button) findViewById(R.id.btn_method2);//находим кнопку
        btn_method2.setText(R.string.trapezeFourSides);//заменяем текст кнопки
        Button btn_method3 = (Button) findViewById(R.id.btn_method3);//находим кнопку
        btn_method3.setText(R.string.trapezeBasesAngles);//заменяем текст кнопки
        //работа с кнопками - конец

        //обрабатываем нажатие кнопок -начало

        //переход метод 1 -начало
        btn_method1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrapezeSelect.this, TrapezeBasesHeight.class);
                startActivity(intent);
                finish();
            }
        });
        //переход на метод 1 - конец

        //переход на метод 2 - начало
        btn_method2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrapezeSelect.this, TrapezeFourSides.class);
                startActivity(intent);
                finish();
            }
        });
        //переход метод 2 - конец

        //переход на метод 3 - начало
        btn_method3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrapezeSelect.this, TrapezeBasesAngle.class);
                startActivity(intent);
                finish();
            }
        });
        //переход метод 3 - конец

        //обрабатываем нажатие кнопок -конец

        //конопка назад - начало
        Button btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(TrapezeSelect.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){
                }
            }
        });
        //конопка назад - конец

    }
    //Системная кнопка "Назад" - начало
    @Override
    public void onBackPressed() {
        try{
            Intent intent = new Intent(TrapezeSelect.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e){
        }
    }
    //Системная кнопка "Назад" - конец
}