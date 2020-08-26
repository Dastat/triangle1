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

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

//площадь прямокгольника через 2 диагонали и угол
public class SquareAngleDiagonals extends AppCompatActivity {
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

        //Работа с картинками - начало
        ImageView img_triangle = (ImageView)findViewById(R.id.img_universal);//находим нужную картинку
        img_triangle.setImageResource(R.drawable.main_img_square_angle_diagonals);//заменяем на нужную картинку
        img_triangle.setClipToOutline(true);//скугляем углы у картини
        //Работа с картинками - конец

        //убираем ненужные элементы , которые не нужны для данного расчета - начало
        TextView parameter3 = (TextView)findViewById(R.id.parameter3);//находим текст
        parameter3.setVisibility(View.GONE);//скрываем текст
        EditText edit3 = (EditText)findViewById(R.id.edit_3);//находим строку ввода
        edit3.setVisibility(View.GONE);//скрываем строку ввода
        TextView parameter4 = (TextView)findViewById(R.id.parameter4);//находим текст
        parameter4.setVisibility(View.GONE);//скрываем текст
        EditText edit4 = (EditText)findViewById(R.id.edit_4);//находим строку ввода
        edit4.setVisibility(View.GONE);//скрываем строку ввода
        //убираем ненужные элементы , которые не нужны для данного расчета - конец

        //Работа с текстом - начало
        TextView side1 = (TextView)findViewById(R.id.parameter1);//находим нужный текст
        side1.setText(R.string.diagonal);//меняем на нужный  текст
        TextView side2 = (TextView)findViewById(R.id.parameter2);//находим нужный текст
        side2.setText(R.string.corner_alpha);//меняем на нужный  текст
        //Работа с текстом - конец

        //подключаем кнопку расчитать - начало
        final Button btn_calculate = (Button)findViewById(R.id.btn_calculate);//находим строку которая запускает расчет
        final  EditText variable1 = (EditText)findViewById(R.id.edit_1);//находим стороку для ввода данных
        final EditText variable2 = (EditText)findViewById(R.id.edit_2);//находим стороку для ввода данных
        btn_calculate.setOnClickListener(new View.OnClickListener() {//устанавливаем "слушатель" на кнопку расчитать
            @Override
            public void onClick(View view) {

                if(variable1.getText().toString().isEmpty() || variable2.getText().toString().isEmpty() ){//если поля ввода пустые
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
                    Intent intent = new Intent(SquareAngleDiagonals.this, SquareSelect.class);//намеренье перейки из одного окна в другое
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
        TextView detail3_1 = (TextView)details.findViewById(R.id.detail_3);//находим текст
        detail3_1.setVisibility(View.GONE);//скрываем текст
        TextView detail3_2 = (TextView)details.findViewById(R.id.textView_detail_3);//находим текст с выводом результата
        detail3_2.setVisibility(View.GONE);//скрываем текст
        //убираем ненужные элементы из диалогового окна , которые не нужны для данного расчета - конец

        //меняем текст и картинку в диалоговом окне - начало
        ImageView img_details = (ImageView)details.findViewById(R.id.img_details);//находим картинку
        img_details.setImageResource(R.drawable.main_img_square_details);//заменяем катринку
        TextView detail6_1 = (TextView)details.findViewById(R.id.detail_6);
        detail6_1.setText(R.string.diagonal);
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
        final EditText num1 = (EditText)findViewById(R.id.edit_1);//находим стороку для ввода данных. диагональ (Известно)
        final EditText num2 = (EditText)findViewById(R.id.edit_2);//находим стороку для ввода данных. угол alpha (Известно)
        final TextView result = (TextView)findViewById(R.id.text_result);//находим стороку для вывода данных (Ответ)
        final TextView detail_side_a = (TextView)details.findViewById(R.id.textView_detail_4);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_diagonal = (TextView)details.findViewById(R.id.textView_detail_6);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_side_b = (TextView)details.findViewById(R.id.textView_detail_5);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_corner1 = (TextView)details.findViewById(R.id.textView_detail_1);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_corner2 = (TextView)details.findViewById(R.id.textView_detail_2);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_perimeter = (TextView)details.findViewById(R.id.textView_detail_7);//находим стороку для вывода данных в диалоговое окно

        final double diagonal = Float.parseFloat(num1.getText().toString());//преобразовываем введеную паеременную в число
        final double alpha = Float.parseFloat(num2.getText().toString());//преобразовываем введеную паеременную в число
        double sinAlpha = Math.sin(Math.toRadians(alpha));//находим синус угла. тут же преобразуеи градусы в радианы
        double cosAlpha = Math.cos(Math.toRadians(alpha));//находим косинус угла. тут же преобразуеи градусы в радианы

        double res = (pow(diagonal,2)*sinAlpha)/2;//формула для вычисления полощади

        DecimalFormat decimalFormat = new DecimalFormat("#.##");//устанавливаем (форматируем) количество точек после запятой
        final String form_res = decimalFormat.format(res);//применяем форматирование к результату и преобразуем его в строку
        result.setText(form_res);// выводим результат на экран

        //работа с "деталями" - начало
        //расчитаваем детали - начало

        double side_a = sqrt(2*pow(diagonal/2,2)-pow(diagonal,2)/2*cosAlpha);
        double side_b = sqrt(pow(diagonal,2)-pow(side_a,2));
        double perimeter = 2*(side_a+side_b);//находим пририметр треугольника

        if (side_a<=side_b){
            double betta = 180-alpha;
            detail_corner1.setText(decimalFormat.format(alpha));//выводим результат на экран диалогового окна и применяем форматирование к результату и преобразовавая его в строку
            detail_corner2.setText(decimalFormat.format(betta));//выводим результат на экран диалогового окна и применяем форматирование к результату и преобразовавая его в строку
        } else if(side_a>side_b){
            double betta =180-alpha;
            detail_corner1.setText(decimalFormat.format(alpha));//выводим результат на экран диалогового окна и применяем форматирование к результату и преобразовавая его в строку
            detail_corner2.setText(decimalFormat.format(betta));//выводим результат на экран диалогового окна и применяем форматирование к результату и преобразовавая его в строку
        }

        detail_side_a.setText(decimalFormat.format(side_a));//выводим результат на экран диалогового окна и применяем форматирование к результату и преобразовавая его в строку
        detail_side_b.setText(decimalFormat.format(side_b));//выводим результат на экран диалогового окна и применяем форматирование к результату и преобразовавая его в строку
        detail_diagonal.setText(decimalFormat.format(diagonal));//выводим результат на экран диалогового окна и применяем форматирование к результату и преобразовавая его в строку
        detail_perimeter.setText(decimalFormat.format(perimeter));//выводим результат на экран диалогового окна и применяем форматирование к результату и преобразовавая его в строку
        //выыодим переменные в окно с подробностями - конец
        //работа с "деталями" -  конец

        //обрабатываем нажатие кнопки "reset" - начало
        final Button btn_reset = (Button) findViewById(R.id.btn_reset);//объявляем кнопку обновить
        btn_reset.setOnClickListener(new View.OnClickListener() { //устанавливаем слушатель
            @Override
            public void onClick(View view) {
                num1.setText("");
                num2.setText("");
                detail_side_a.setText("");
                detail_side_b.setText("");
                detail_corner1.setText("");
                detail_corner2.setText("");
                detail_diagonal.setText("");
                detail_perimeter.setText("");
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
            Intent intent = new Intent(SquareAngleDiagonals.this, SquareSelect.class);//намеренье перейти из одного окна в другое
            startActivity(intent);//разрераем намеренье
            finish();
        }
        catch (Exception e){//ловим ошибки
            //пусто
        }
    }
    //Системная кнопка "Назад" - конец
}