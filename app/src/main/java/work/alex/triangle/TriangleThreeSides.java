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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

//площадь треугольника через 2 стороны и угол между ними
public class TriangleThreeSides extends AppCompatActivity {
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
        img_triangle.setImageResource(R.drawable.main_img_triangle_three_sides);//заменяем на нужную картинку
        img_triangle.setClipToOutline(true);//скугляем углы у картини
        //Работа с картинками - конец

        //убираем ненужные элементы , которые не нужны для этого окна - начало
        TextView parameter4 = (TextView)findViewById(R.id.parameter4);//находим текст
        parameter4.setVisibility(View.GONE);//скрываем текст
        EditText edit4 = (EditText)findViewById(R.id.edit_4);//находим строку ввода
        edit4.setVisibility(View.GONE);//скрываем строку ввода
        //убираем ненужные элементы , которые не нужны для этого окна - конец

        //Работа с текстом - начало
        TextView side1 = (TextView)findViewById(R.id.parameter1);//находим нужный текст
        side1.setText(R.string.side_a);//меняем на нужный  текст
        TextView side2 = (TextView)findViewById(R.id.parameter2);//находим нужный текст
        side2.setText(R.string.side_b);//меняем на нужный  текст
        TextView corner = (TextView)findViewById(R.id.parameter3);//находим нужный текст
        corner.setText(R.string.side_c);//меняем на нужный  текст
        //Работа с текстом - конец

        //подключаем кнопку назад - конец
        final Button btn_calculate = (Button)findViewById(R.id.btn_calculate);//находим строку которая запускает расчет
        final  EditText variable1 = (EditText)findViewById(R.id.edit_1);//находим стороку для ввода данных
        final EditText variable2 = (EditText)findViewById(R.id.edit_2);//находим стороку для ввода данных
        final EditText variable3 = (EditText)findViewById(R.id.edit_3);//находим стороку для ввода данных
        btn_calculate.setOnClickListener(new View.OnClickListener() {//устанавливаем "слушатель" на кнопку расчитать
            @Override
            public void onClick(View view) {

                if(variable1.getText().toString().isEmpty() || variable2.getText().toString().isEmpty()
                        || variable3.getText().toString().isEmpty()){//если поля ввода пустые
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
                    Intent intent = new Intent(TriangleThreeSides.this, TriangleSelect.class);//намеренье перейки из одного окна в другое
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
        final EditText num1 = (EditText)findViewById(R.id.edit_1);//находим стороку для ввода данных. Сторона а (Известно)
        final EditText num2 = (EditText)findViewById(R.id.edit_2);//находим стороку для ввода данных. Сторона в (Известно)
        final EditText num3= (EditText)findViewById(R.id.edit_3);//находим стороку для ввода данных.Сторона c (Известно)
        final TextView result = (TextView)findViewById(R.id.text_result);//находим стороку для вывода данных (Ответ)
        final TextView detail_side_a = (TextView)details.findViewById(R.id.textView_detail_4);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_side_b = (TextView)details.findViewById(R.id.textView_detail_6);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_side_c = (TextView)details.findViewById(R.id.textView_detail_5);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_corner1 = (TextView)details.findViewById(R.id.textView_detail_1);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_corner2 = (TextView)details.findViewById(R.id.textView_detail_2);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_corner3 = (TextView)details.findViewById(R.id.textView_detail_3);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_perimeter = (TextView)details.findViewById(R.id.textView_detail_7);//находим стороку для вывода данных в диалоговое окно

        double side_a = Float.parseFloat(num1.getText().toString());//преобразовываем введеную паеременную в число
        double side_b = Float.parseFloat(num2.getText().toString());//преобразовываем введеную паеременную в число
        double side_c = Float.parseFloat(num3.getText().toString());//преобразовываем введеную паеременную в число
        //double sinGamma = Math.sin(Math.toRadians(gamma));//находим синус угла. тут же преобразуеи градусы в радианы
        //double cosGamma = Math.cos(Math.toRadians(gamma));//находим косинус угла. тут же преобразуеи градусы в радианы
        double perimeter = side_a+side_b+side_c;//находим пририметр треугольника
        double p = perimeter/2;
        double res = sqrt(p*(p-side_a)*(p-side_b)*(p-side_c));//формула для вычисления полощади

        DecimalFormat decimalFormat = new DecimalFormat("#.##");//устанавливаем (форматируем) количество точек после запятой
        final String form_res = decimalFormat.format(res);//применяем форматирование к результату и преобразуем его в строку
        result.setText(form_res);// выводим результат на экран

        //работа с "деталями" - начало
        //расчитаваем детали - начало
//      double side_c = sqrt(pow(side_a,2)+pow(side_b,2)-2*side_a*side_b*cosGamma);//находим неизвестную сторону через теорему косинусов
        double cosAlpha = (pow(side_c,2)+pow(side_b,2)-pow(side_a,2))/(2*side_c*side_b);//находим угол через теорему косинусов
        double alpha = Math.toDegrees(Math.acos(cosAlpha));//возвращаем значение синуса через аркКосинус и переводим радианы в градусы
        double cosBetta = (pow(side_c,2)+pow(side_a,2)-pow(side_b,2))/(2*side_c*side_a);//находим угол через теорему косинусов
        double betta = Math.toDegrees(Math.acos(cosBetta));//возвращаем значение синуса через аркКосинус и переводим радианы в градусы
        double cosGamma = (pow(side_a,2)+pow(side_b,2)-pow(side_c,2))/(2*side_a*side_b);//находим угол через теорему косинусов
        double gamma = Math.toDegrees(Math.acos(cosGamma));//возвращаем значение синуса через аркКосинус и переводим радианы в градусы
        //расчитаваем детали - конец

        final String form_side_c = decimalFormat.format(side_c);//применяем форматирование к результату и преобразуем его в строку
        final String form_side_a = decimalFormat.format(side_a);//применяем форматирование к результату и преобразуем его в строку
        final String form_side_b = decimalFormat.format(side_b);//применяем форматирование к результату и преобразуем его в строку
        final String form_alpha = decimalFormat.format(alpha);//применяем форматирование к результату и преобразуем его в строку
        final String form_betta = decimalFormat.format(betta);//применяем форматирование к результату и преобразуем его в строку
        final String form_gamma = decimalFormat.format(gamma);//применяем форматирование к результату и преобразуем его в строку
        final String form_perimeter = decimalFormat.format(perimeter);//применяем форматирование к результату и преобразуем его в строку

        //выыодим перменные в окно с подробностями - начало
        detail_side_c.setText(form_side_c);//выводим результат на экран диалогового окна
        detail_side_a.setText(form_side_a);//выводим результат на экран диалогового окна
        detail_side_b.setText(form_side_b);//выводим результат на экран диалогового окна
        detail_corner1.setText(form_alpha);//выводим результат на экран диалогового окна
        detail_corner2.setText(form_betta);//выводим результат на экран диалогового окна
        detail_corner3.setText(form_gamma);//выводим результат на экран диалогового окна
        detail_perimeter.setText(form_perimeter);//выводим результат на экран диалогового окна
        //выыодим переменные в окно с подробностями - конец
        //работа с "деталями" - начало

        //обрабатываем нажатие кнопки "reset" - начало
        final Button btn_reset = (Button) findViewById(R.id.btn_reset);//объявляем кнопку обновить
        btn_reset.setOnClickListener(new View.OnClickListener() { //устанавливаем слушатель
            @Override
            public void onClick(View view) {
                num1.setText("");
                num2.setText("");
                num3.setText("");
                detail_side_a.setText("");
                detail_side_b.setText("");
                detail_side_c.setText("");
                detail_corner1.setText("");
                detail_corner2.setText("");
                detail_corner3.setText("");
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
            Intent intent = new Intent(TriangleThreeSides.this, TriangleSelect.class);//намеренье перейти из одного окна в другое
            startActivity(intent);//разрераем намеренье
            finish();
        }
        catch (Exception e){//ловим ошибки
            //пусто
        }
    }
    //Системная кнопка "Назад" - конец
}