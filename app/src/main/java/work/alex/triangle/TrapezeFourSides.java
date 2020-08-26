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

//площадь трапеции по четырем сторонам
public class TrapezeFourSides extends AppCompatActivity {
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
        img_triangle.setImageResource(R.drawable.main_img_trapeze_four_sides);//заменяем на нужную картинку
        //Работа с картинками - конец

        //Работа с текстом - начало
        TextView side1 = (TextView)findViewById(R.id.parameter1);//находим нужный текст
        side1.setText(R.string.side_a);//меняем на нужный  текст
        TextView side2 = (TextView)findViewById(R.id.parameter2);//находим нужный текст
        side2.setText(R.string.side_b);//меняем на нужный  текст
        TextView side3 = (TextView)findViewById(R.id.parameter3);//находим нужный текст
        side3.setText(R.string.side_c);//меняем на нужный  текст
        TextView side4 = (TextView)findViewById(R.id.parameter4);//находим нужный текст
        side4.setText(R.string.side_d);//меняем на нужный  текст
        //Работа с текстом - конец

        //подключаем кнопку расчитать - начало
        final Button btn_calculate = (Button)findViewById(R.id.btn_calculate);//находим строку которая запускает расчет
        final  EditText variable1 = (EditText)findViewById(R.id.edit_1);//находим стороку для ввода данных
        final EditText variable2 = (EditText)findViewById(R.id.edit_2);//находим стороку для ввода данных
        final  EditText variable3 = (EditText)findViewById(R.id.edit_3);//находим стороку для ввода данных
        final EditText variable4 = (EditText)findViewById(R.id.edit_4);//находим стороку для ввода данных
        btn_calculate.setOnClickListener(new View.OnClickListener() {//устанавливаем "слушатель" на кнопку расчитать
            @Override
            public void onClick(View view) {

                if(variable1.getText().toString().isEmpty() || variable2.getText().toString().isEmpty() ||
                        variable3.getText().toString().isEmpty() || variable4.getText().toString().isEmpty()){//если поля ввода пустые
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
                    Intent intent = new Intent(TrapezeFourSides.this, TrapezeSelect.class);//намеренье перейки из одного окна в другое
                    startActivity(intent);//разрешаем намеренье
                    finish();
            }
        });
        //кнопка назад - конец

        //Вызов окна с подробностями - начало
        details = new Dialog(this);//создаем диалоговое окно
        details.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрываем заголовок диалогового окна
        details.setContentView(R.layout.details_trapeze);//путь к макету далогового окна
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
        final EditText num1 = (EditText)findViewById(R.id.edit_1);//находим стороку для ввода данных. диагональ (Известно)
        final EditText num2 = (EditText)findViewById(R.id.edit_2);//находим стороку для ввода данных. угол alpha (Известно)
        final EditText num3= (EditText)findViewById(R.id.edit_3);//находим стороку для ввода данных.Сторона c (Известно)
        final EditText num4= (EditText)findViewById(R.id.edit_4);//находим стороку для ввода данных.Сторона c (Известно)
        final TextView result = (TextView)findViewById(R.id.text_result);//находим стороку для вывода данных (Ответ)
        final TextView detail_alpha = (TextView)details.findViewById(R.id.textView_detail_1);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_betta = (TextView)details.findViewById(R.id.textView_detail_2);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_gamma = (TextView)details.findViewById(R.id.textView_detail_3);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_delta = (TextView)details.findViewById(R.id.textView_detail_4);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_side_a = (TextView)details.findViewById(R.id.textView_detail_5);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_side_b = (TextView)details.findViewById(R.id.textView_detail_6);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_side_c = (TextView)details.findViewById(R.id.textView_detail_7);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_side_d = (TextView)details.findViewById(R.id.textView_detail_8);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_height_h = (TextView)details.findViewById(R.id.textView_detail_9);//находим стороку для вывода данных в диалоговое окно
        final TextView detail_perimeter = (TextView)details.findViewById(R.id.textView_detail_10);//находим стороку для вывода данных в диалоговое окно

        final double side_a = Float.parseFloat(num1.getText().toString());//преобразовываем введеную паеременную в число
        final double side_b = Float.parseFloat(num2.getText().toString());//преобразовываем введеную паеременную в число
        final double side_c = Float.parseFloat(num3.getText().toString());//преобразовываем введеную паеременную в число
        final double side_d = Float.parseFloat(num4.getText().toString());//преобразовываем введеную паеременную в число

        double perimeter = side_a+side_b+side_c+side_d;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");//устанавливаем (форматируем) количество точек после запятой
        detail_perimeter.setText(decimalFormat.format(perimeter));

        if (side_a < side_b) {
            double res = ((side_a+side_b)/2)*sqrt(pow(side_c,2)-(pow((pow((side_b-side_a),2)+pow(side_c,2)-pow(side_d,2))/(2*(side_b-side_a)),2)));
            final String form_res = decimalFormat.format(res);//применяем форматирование к результату и преобразуем его в строку
            result.setText(form_res);// выводим результат на экран
            double h = 2*res/(side_a+side_b);
            double diagonal_delta=sqrt((pow(side_d,2)+side_a*side_b)-((side_a*(pow(side_d,2)-pow(side_c,2)))/(side_a-side_b)));
            double diagonal_gamma=sqrt((pow(side_c,2)+side_a*side_b)-((side_a*(pow(side_c,2)-pow(side_d,2)))/(side_a-side_b)));
            double cosAlpha = (pow(side_c,2)+pow(side_b,2)-pow(diagonal_delta,2))/(2*side_c*side_b);
            double cosBetta = (pow(side_d,2)+pow(side_b,2)-pow(diagonal_gamma,2))/(2*side_d*side_b);
            double alpha = Math.toDegrees(Math.acos(cosAlpha));
            double betta = Math.toDegrees(Math.acos(cosBetta));
            double delta = 180-alpha;
            double gamma = 180-betta;
            detail_gamma.setText(decimalFormat.format(gamma));
            detail_delta.setText(decimalFormat.format(delta));
            detail_height_h.setText(decimalFormat.format(h));
            detail_alpha.setText(decimalFormat.format(alpha));
            detail_betta.setText(decimalFormat.format(betta));

            detail_side_a.setText(decimalFormat.format(diagonal_delta));
            detail_side_b.setText(decimalFormat.format(diagonal_gamma));

        } else if (side_a > side_b) {
            double res = ((side_a+side_b)/2)*sqrt(pow(side_c,2)-(pow((pow((side_a-side_b),2)+pow(side_c,2)-pow(side_d,2))/(2*(side_a-side_b)),2)));
            final String form_res = decimalFormat.format(res);//применяем форматирование к результату и преобразуем его в строку
            result.setText(form_res);// выводим результат на экран
            double h = 2*res/(side_a+side_b);
            double diagonal_delta=sqrt((pow(side_d,2)+side_a*side_b)-((side_a*(pow(side_d,2)-pow(side_c,2)))/(side_a-side_b)));
            double diagonal_gamma=sqrt((pow(side_c,2)+side_a*side_b)-((side_a*(pow(side_c,2)-pow(side_d,2)))/(side_a-side_b)));
            double cosAlpha = (pow(side_c,2)+pow(side_b,2)-pow(diagonal_delta,2))/(2*side_c*side_b);
            double cosBetta = (pow(side_d,2)+pow(side_b,2)-pow(diagonal_gamma,2))/(2*side_d*side_b);
            double alpha = Math.toDegrees(Math.acos(cosAlpha));
            double betta = Math.toDegrees(Math.acos(cosBetta));
            double delta = 180-alpha;
            double gamma = 180-betta;
            detail_gamma.setText(decimalFormat.format(gamma));
            detail_delta.setText(decimalFormat.format(delta));
            detail_alpha.setText(decimalFormat.format(alpha));
            detail_betta.setText(decimalFormat.format(betta));
            detail_height_h.setText(decimalFormat.format(h));

            detail_side_a.setText(decimalFormat.format(diagonal_delta));
            detail_side_b.setText(decimalFormat.format(diagonal_gamma));
        }

        detail_side_c.setText(decimalFormat.format(side_c));
        detail_side_d.setText(decimalFormat.format(side_d));

        //обрабатываем нажатие кнопки "reset" - начало
        final Button btn_reset = (Button) findViewById(R.id.btn_reset);//объявляем кнопку обновить
        btn_reset.setOnClickListener(new View.OnClickListener() { //устанавливаем слушатель
            @Override
            public void onClick(View view) {
                num1.setText("");
                num2.setText("");
                num3.setText("");
                num4.setText("");
                detail_side_a.setText("");
                detail_side_b.setText("");
                detail_side_c.setText("");
                detail_side_d.setText("");
                detail_alpha.setText("");
                detail_betta.setText("");
                detail_gamma.setText("");
                detail_delta.setText("");
                detail_height_h.setText("");
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
            Intent intent = new Intent(TrapezeFourSides.this, SquareSelect.class);//намеренье перейти из одного окна в другое
            startActivity(intent);//разрераем намеренье
            finish();
        }
        catch (Exception e){//ловим ошибки
            //пусто
        }
    }
    //Системная кнопка "Назад" - конец
}