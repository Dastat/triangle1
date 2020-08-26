package work.alex.triangle;

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

import androidx.appcompat.app.AppCompatActivity;

public class TriangleSelect extends AppCompatActivity {
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triangle_select);

        //Убираем строку состояния - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Убираем строку состояния - конец

        //работа в картинками -начало
        final ImageView img_triangle = (ImageView)findViewById(R.id.img_triangle);//находим картинку
        img_triangle.setImageResource(R.drawable.main_img_triangle);//заменяем картинку
        img_triangle.setClipToOutline(true);//скугляем углы у картинок
        final Animation a = AnimationUtils.loadAnimation(TriangleSelect.this, R.anim.alpha);//подключаем анимацию
        //работа в картинками -конец

        //работа в кнопками - начало
        Button btn_triangle_base_height = (Button) findViewById(R.id.btn_method1);//находим кнопку
        Button btn_triangle_sides_angle = (Button) findViewById(R.id.btn_method2);//находим кнопку
        Button btn_triangle_Heron = (Button) findViewById(R.id.btn_method3);//находим кнопку
        //работа в кнопками - конец

        //обрабатываем нажатие кнопок -начало
        //переход на окно основание/высота - начало
        btn_triangle_base_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TriangleSelect.this, TriangleBaseHeight.class);
                startActivity(intent);
                finish();
            }
        });
        //переход на окно основание/высота - конец

        //переход на окно 2 стороны и угол - начало
        btn_triangle_sides_angle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TriangleSelect.this, TriangleSidesAngle.class);
                startActivity(intent);
                finish();
            }
        });
        //переход на окно 2 стороны и угол - конец

        //переход на окно по 3м сторонам - начало
        btn_triangle_Heron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TriangleSelect.this, TriangleThreeSides.class);
                startActivity(intent);
                finish();
            }
        });
        //переход на окно по 3м сторонам - конец


        //обрабатываем нажатие кнопок -конец

        Button btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(TriangleSelect.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){
                }
            }
        });
    }
    //Системная кнопка "Назад" - начало
    @Override
    public void onBackPressed() {
        try{
            Intent intent = new Intent(TriangleSelect.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e){
        }
    }
    //Системная кнопка "Назад" - конец
}