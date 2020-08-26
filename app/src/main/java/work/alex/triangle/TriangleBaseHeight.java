package work.alex.triangle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
//площадь треугольника через высоту и основание
public class TriangleBaseHeight extends AppCompatActivity {
    private long backPressedTime;

    @Override //переопределяем метод
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);//указываем Java файлу с каким Layout'ом он будет работать

        //Убираем строку состояния - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Убираем строку состояния - конец

        //убираем ненужные элементы , которые не нужны для этого окна - начало
        TextView parameter3 = (TextView)findViewById(R.id.parameter3);//находим текст
        parameter3.setVisibility(View.GONE);//скрываем текст
        EditText edit3 = (EditText)findViewById(R.id.edit_3);//находим строку ввода
        edit3.setVisibility(View.GONE);//скрываем строку ввода
        TextView parameter4 = (TextView)findViewById(R.id.parameter4);//находим текст
        parameter4.setVisibility(View.GONE);//скрываем текст
        EditText edit4 = (EditText)findViewById(R.id.edit_4);//находим строку ввода
        edit4.setVisibility(View.GONE);//скрываем строку ввода
        //убираем ненужные элементы , которые не нужны для этого окна - конец

        //Работа с картинками - начало
        ImageView img_triangle = (ImageView)findViewById(R.id.img_universal);//находим нужную картинку
        img_triangle.setImageResource(R.drawable.main_img_triangle_base_height);
        img_triangle.setClipToOutline(true);//скугляем углы у картини
        //Работа с картинками - конец

        //Работа с текстом - начало
        TextView side1 = (TextView)findViewById(R.id.parameter1);//находим нужный текст
        side1.setText(R.string.base_a);//меняем на нужный  текст
        TextView side2 = (TextView)findViewById(R.id.parameter2);//находим нужный текст
        side2.setText(R.string.height_h);//меняем на нужный  текст

        //подключаем кнопку назад - конец
        final Button btn_calculate = (Button)findViewById(R.id.btn_calculate);
        final  EditText variable1 = (EditText)findViewById(R.id.edit_1);//находим стороку для ввода данных
        final EditText variable2 = (EditText)findViewById(R.id.edit_2);//находим стороку для ввода данных
        btn_calculate.setOnClickListener(new View.OnClickListener() {//устанавливаем "слушатель" на кнопку расчитать
            @Override
            public void onClick(View view) {

                if(variable1.getText().toString().isEmpty() || variable2.getText().toString().isEmpty() ){//если поля ввода пустые
                    btn_calculate.setClickable(true);//кнопка проболжает быть активной
                }else {//еслм в полях что-то есть
                    calculate(btn_calculate);//вызываем метод calculate на кнопку расчитать
                }
            }
        });
        //подключаем кнопку расчитать - конец

        //кнопка назад - начало
        Button btn_back_cal1 = (Button)findViewById(R.id.btn_back_cal1);//находим нужную кнопку
        btn_back_cal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(TriangleBaseHeight.this, TriangleSelect.class);//намеренье перейки из одного окна в другое
                    startActivity(intent);//разрешаем намеренье
                    finish();
            }
        });
        //кнопка назад - конец

        //скрываем кнопку "подробно"" - начало
        Button btn_detail = (Button)findViewById(R.id.btn_detail);
        btn_detail.setVisibility(View.GONE);
        //скрываем кнопку "подробно" - конец

    }

    //вычислительная часть - начало
    public void calculate (View view)   {
        final EditText variable1 = (EditText)findViewById(R.id.edit_1);//находим стороку для ввода данных
        final EditText variable2 = (EditText)findViewById(R.id.edit_2);//находим стороку для ввода данных
        final TextView result = (TextView)findViewById(R.id.text_result);//находим стороку для вывода данных
        float num1 = Float.parseFloat(variable1.getText().toString());//преобразовываем введеную паеременную в число
        float num2 = Float.parseFloat(variable2.getText().toString());//преобразовываем введеную паеременную в число
        float res = num1*num2/2;//формула для вычисления полощади
        DecimalFormat decimalFormat = new DecimalFormat("#.###");//устанавливаем (форматируем) количество точек после запятой
        final String form_res = decimalFormat.format(res);//применяем форматирование к результату
        result.setText(form_res);// выводим результат

        //обрабатываем нажатие кнопки "reset" - начало
        final Button btn_reset = (Button) findViewById(R.id.btn_reset);//объявляем кнопку обновить
        btn_reset.setOnClickListener(new View.OnClickListener() { //устанавливаем слушатель
            @Override
            public void onClick(View view) {
                    variable1.setText("");//
                    variable2.setText("");
                    result.setText("");
            }
        });
        //обрабатываем нажатие кнопки "reset" - конец

    }
    //вычислительная часть - конец

    //Системная кнопка "Назад" - начало
    @Override //переопределяем метод
    public void onBackPressed() {
        try{
            Intent intent = new Intent(TriangleBaseHeight.this, TriangleSelect.class);//намеренье перейти из одного окна в другое
            startActivity(intent);//разрераем намеренье
            finish();
        }
        catch (Exception e){//ловим ошибки
            //пусто
        }
    }
    //Системная кнопка "Назад" - конец
}