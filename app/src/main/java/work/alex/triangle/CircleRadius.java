package work.alex.triangle;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

//площадь круга через радиус
public class CircleRadius extends AppCompatActivity {
    private long backPressedTime;
    Dialog details;

    @Override //переопределяем метод
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);//указываем Java файлу с каким Layout'ом он будет работать

        //Убираем строку состояния - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Убираем строку состояния - конец


        //убираем ненужные элементы , которые не нужны для этого окна - начало
        TextView parameter2 = (TextView)findViewById(R.id.parameter2);//находим текст
        parameter2.setVisibility(View.GONE);//скрываем текст
        EditText edit2 = (EditText)findViewById(R.id.edit_2);//находим строку ввода
        edit2.setVisibility(View.GONE);//скрываем строку ввода
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
        img_triangle.setImageResource(R.drawable.main_img_circle_radius);//заменяем на нужную картинку
        img_triangle.setClipToOutline(true);//скугляем углы у картини
        //Работа с картинками - конец

        //Работа с текстом - начало
        TextView side1 = (TextView)findViewById(R.id.parameter1);//находим нужный текст
        side1.setText(R.string.radius_r);//меняем на нужный  текст
        //Работа с текстом - конец

        //подключаем кнопку расчитать - начало
        final Button btn_calculate = (Button)findViewById(R.id.btn_calculate);//находим строку которая запускает расчет
        final  EditText variable1 = (EditText)findViewById(R.id.edit_1);//находим стороку для ввода данных

        btn_calculate.setOnClickListener(new View.OnClickListener() {//устанавливаем "слушатель" на кнопку расчитать
            @Override
            public void onClick(View view) {

                if(variable1.getText().toString().isEmpty()) {//если поля ввода пустые
                    btn_calculate.setClickable(true);//кнопка продолжает быть активной
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
                    Intent intent = new Intent(CircleRadius.this, CircleSelect.class);//намеренье перейки из одного окна в другое
                    startActivity(intent);//разрешаем намеренье
                    finish();
            }
        });
        //кнопка назад - конец

        //Вызов окна с подробностями - начало
        details = new Dialog(this);//создаем диалоговое окно
        details.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрываем заголовок диалогового окна
        details.setContentView(R.layout.details);//путь к макету далогового окна
        details.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
        details.setCancelable(false);//окно неляза закрыть системной кнопкой назад
        //Вызов окна с подробностями - конец


        //убираем ненужные элементы из диалогового окна , которые не нужны для данного расчета - начало
        TextView detail1_1 = (TextView)details.findViewById(R.id.detail_1);//находим текст
        detail1_1.setVisibility(View.GONE);//скрываем текст
        TextView detail1_2 = (TextView)details.findViewById(R.id.textView_detail_1);//находим текст с выводом результата
        detail1_2.setVisibility(View.GONE);//скрываем текст
        TextView detail2_1 = (TextView)details.findViewById(R.id.detail_2);//находим текст
        detail2_1.setVisibility(View.GONE);//скрываем текст
        TextView detail2_2 = (TextView)details.findViewById(R.id.textView_detail_2);//находим текст с выводом результата
        detail2_2.setVisibility(View.GONE);//скрываем текст
        TextView detail3_1 = (TextView)details.findViewById(R.id.detail_3);//находим текст
        detail3_1.setVisibility(View.GONE);//скрываем текст
        TextView detail3_2 = (TextView)details.findViewById(R.id.textView_detail_3);//находим текст с выводом результата
        detail3_2.setVisibility(View.GONE);//скрываем текст
        TextView detail7_1 = (TextView)details.findViewById(R.id.detail_7);//находим текст
        detail7_1.setVisibility(View.GONE);//скрываем текст
        TextView detail7_2 = (TextView)details.findViewById(R.id.textView_detail_7);//находим текст с выводом результата
        detail7_2.setVisibility(View.GONE);//скрываем текст
        //убираем ненужные элементы из диалогового окна , которые не нужны для данного расчета - конец

        //меняем текст и картинку в диалоговом окне - начало
        ImageView img_details = (ImageView)details.findViewById(R.id.img_details);//находим картинку
        img_details.setImageResource(R.drawable.main_img_circle_details);//заменяем катринку
        TextView detail4_1 = (TextView)details.findViewById(R.id.detail_4);
        detail4_1.setText(R.string.radius_r);
        TextView detail5_1 = (TextView)details.findViewById(R.id.detail_5);
        detail5_1.setText(R.string.diameter_D);
        TextView detail6_1 = (TextView)details.findViewById(R.id.detail_6);
        detail6_1.setText(R.string.circumference);
        //меняем текст и картинку в диалоговом окне - конец

        //открыть окно с подробностями - начало
        Button btn_detail = (Button)findViewById(R.id.btn_detail);
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details.show();
            }
        });
        //открыть окно с подробностями - конец

        //кнопка закрывающая окно с подробностями - начало
        Button btn_details = (Button)details.findViewById(R.id.btn_close_details);//находим нужную кнопку
        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details.dismiss();
            }
        });
        //кнопка закрывающая окно с подробностями - конец

}

    //вычислительная часть - начало
    public void calculate (View view)   {
        final EditText num1 = (EditText)findViewById(R.id.edit_1);//находим стороку для ввода данных. радиус (Известно)
        final TextView result = (TextView)findViewById(R.id.text_result);//находим стороку для вывода данных (Ответ)
        final TextView detail_radius = (TextView)details.findViewById(R.id.textView_detail_4);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_diameter = (TextView)details.findViewById(R.id.textView_detail_5);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_L = (TextView)details.findViewById(R.id.textView_detail_6);//находим стороку для вывода данных в диалоговое окно

        final double radius = Float.parseFloat(num1.getText().toString());//преобразовываем введеную паеременную в число

        double res = (PI*pow(radius,2)/4);//формула для вычисления полощади

        DecimalFormat decimalFormat = new DecimalFormat("#.##");//устанавливаем (форматируем) количество точек после запятой
        final String form_res = decimalFormat.format(res);//применяем форматирование к результату и преобразуем его в строку
        result.setText(form_res);// выводим результат на экран

        //работа с "деталями" - начало
        //расчитаваем детали - начало
        double diameter = radius*2;//находим неизвестную сторону через теорему косинусов
        double L = PI*diameter;

        //расчитаваем детали - конец

        //выыодим перменные в окно с подробностями - начало
        detail_radius.setText(decimalFormat.format(radius));//выводим результат на экран диалогового окна и применяем форматирование к результату и преобразовавая его в строку
        detail_diameter.setText(decimalFormat.format(diameter));//выводим результат на экран диалогового окна и применяем форматирование к результату и преобразовавая его в строку
        detail_L.setText(decimalFormat.format(L));//выводим результат на экран диалогового окна и применяем форматирование к результату и преобразовавая его в строку
        //выыодим переменные в окно с подробностями - конец
        //работа с "деталями" -  конец

        //обрабатываем нажатие кнопки "reset" - начало
        final Button btn_reset = (Button) findViewById(R.id.btn_reset);//объявляем кнопку обновить
        btn_reset.setOnClickListener(new View.OnClickListener() { //устанавливаем слушатель
            @Override
            public void onClick(View view) {
                num1.setText("");

                detail_diameter.setText("");
                detail_radius.setText("");
                detail_L.setText("");
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
            Intent intent = new Intent(CircleRadius.this, SquareSelect.class);//намеренье перейти из одного окна в другое
            startActivity(intent);//разрераем намеренье
            finish();
        }
        catch (Exception e){//ловим ошибки
            //пусто
        }
    }
    //Системная кнопка "Назад" - конец
}