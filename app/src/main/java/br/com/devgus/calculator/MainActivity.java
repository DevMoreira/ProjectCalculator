package br.com.devgus.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Objects;

import static com.google.android.material.snackbar.Snackbar.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AppCompatButton btCompleteBackSpace,btClear,
                            btPow,btRaiz,btDiv,btMult,
                            btSubt,btAdd,btEqual,btPoint,
                            btNeg,bt9,bt8,bt7,bt6,
                            bt5,bt4,bt3,bt2,bt1,bt0,
                            btClosedParenthesis, btOpenParenthesis;
    private AppCompatTextView viewExpression, viewResult;
    private ImageView btBackSpace;
    String[] mensagens = {" EXPRESSÃO INVÁLIDA. IMPOSSÍVEL CALCULAR ", " NÃO EXISTE RAIZ QUADRADA DE NÚMERO NEGATIVO !", " NÃO EXISTE DIVISÃO CUJO DENOMINADOR É ZERO ! "};

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingComponents();
        Objects.requireNonNull(getSupportActionBar()).hide();
        applyingContext();
    }

    public void loadingComponents(){
        viewExpression = findViewById(R.id.View_Expression);
        viewResult = findViewById(R.id.View_Result);
        btBackSpace = findViewById(R.id.backspace);
        btCompleteBackSpace = findViewById(R.id.field_backspace);
        btClear = findViewById(R.id.bt_Clear);
        btOpenParenthesis = findViewById(R.id.open_parenthesis);
        btClosedParenthesis = findViewById(R.id.closed_parenthesis);
        btPow = findViewById(R.id.bt_Pow);
        btRaiz = findViewById(R.id.bt_Raiz);
        btDiv = findViewById(R.id.bt_Div);
        btMult = findViewById(R.id.bt_Mult);
        btSubt = findViewById(R.id.bt_Subt);
        btAdd = findViewById(R.id.bt_Add);
        btEqual = findViewById(R.id.bt_Equal);
        btPoint = findViewById(R.id.bt_Point);
        btNeg = findViewById(R.id.bt_Neg);
        bt9 = findViewById(R.id.bt_9);
        bt8 = findViewById(R.id.bt_8);
        bt7 = findViewById(R.id.bt_7);
        bt6 = findViewById(R.id.bt_6);
        bt5 = findViewById(R.id.bt_5);
        bt4 = findViewById(R.id.bt_4);
        bt3 = findViewById(R.id.bt_3);
        bt2 = findViewById(R.id.bt_2);
        bt1 = findViewById(R.id.bt_1);
        bt0 = findViewById(R.id.bt_0);
    }

    public void applyingContext(){
        viewExpression.setOnClickListener(this);
        viewResult.setOnClickListener(this);
        btBackSpace.setOnClickListener(this);
        btCompleteBackSpace.setOnClickListener(this);
        btClear.setOnClickListener(this);
        btOpenParenthesis.setOnClickListener(this);
        btClosedParenthesis.setOnClickListener(this);
        btPow.setOnClickListener(this);
        btRaiz.setOnClickListener(this);
        btDiv.setOnClickListener(this);
        btMult.setOnClickListener(this);
        btSubt.setOnClickListener(this);
        btAdd.setOnClickListener(this);
        btEqual.setOnClickListener(this);
        btPoint.setOnClickListener(this);
        btNeg.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt0.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void AddingAnExpression(String str, boolean clearData){

        String  valueExpression = viewExpression.getText().toString(),
                valueResult = viewResult.getText().toString();

        if(!viewResult.getText().toString().isEmpty()){
            viewExpression.setText("");
            viewResult.setText("");
        }

            if(clearData){
                viewResult.setText("");
                viewExpression.append(str);
            }else{
                if(valueResult.equals("RESULTADO") && valueExpression.equals("EXPRESSÃO")){
                    viewExpression.setText("");
                    viewExpression.append(str);
                }else{
                    viewExpression.append(valueResult);
                    viewExpression.append(str);
                }
                viewResult.setText("");
            }
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View view) {

        String valueButton = "";

        switch (view.getId()){
            case R.id.bt_Clear:
                viewExpression.setText("EXPRESSÃO");
                viewResult.setText("RESULTADO");
                btNeg.setText("+/-");
                btNeg.setBackgroundResource(R.drawable.buttons_symbols_clicked);
                btRaiz.setText("√x");
                btRaiz.setBackgroundResource(R.drawable.buttons_symbols_clicked);
                break;
            case R.id.open_parenthesis:
            case R.id.closed_parenthesis:
            case R.id.bt_Subt:
            case R.id.bt_Add:
                valueButton = ((AppCompatButton)view).getText().toString();
                AddingAnExpression(valueButton,false);
                break;
            case R.id.backspace:
            case R.id.field_backspace:
                clearExpression();
                break;
            case R.id.bt_Pow:
                btPow.setText("^");
                valueButton = ((AppCompatButton)view).getText().toString();
                AddingAnExpression(valueButton,false);
                btPow.setText("aⁿ");
                break;
            case R.id.bt_Raiz:
                valueButton = ((AppCompatButton)view).getText().toString();
                if(valueButton.equals("√x")){
                    btRaiz.setText("sqrt(");
                }
                valueButton = ((AppCompatButton)view).getText().toString();
                AddingAnExpression(valueButton,false);
                if(valueButton.equals("sqrt(")){
                    btRaiz.setText(")");
                    btRaiz.setBackgroundResource(R.color.red);
                }
                if(valueButton.equals(")")){
                    btRaiz.setText("√x");
                    btRaiz.setBackgroundResource(R.drawable.buttons_symbols_clicked);
                }
                break;
            case R.id.bt_Mult:
                btMult.setText("*");
                valueButton = ((AppCompatButton)view).getText().toString();
                AddingAnExpression(valueButton,false);
                btMult.setText("x");
                break;
            case R.id.bt_Div:
                btDiv.setText("/");
                valueButton = ((AppCompatButton)view).getText().toString();
                AddingAnExpression(valueButton,false);
                btDiv.setText("÷");
                break;
            case R.id.bt_Equal:
                showResult(view);
                break;
            case R.id.bt_Point:
            case R.id.bt_9:
            case R.id.bt_7:
            case R.id.bt_5:
            case R.id.bt_0:
            case R.id.bt_2:
            case R.id.bt_8:
            case R.id.bt_4:
            case R.id.bt_6:
            case R.id.bt_3:
            case R.id.bt_1:
                valueButton = ((AppCompatButton)view).getText().toString();
                AddingAnExpression(valueButton,true);
                break;
            case R.id.bt_Neg:
                valueButton = ((AppCompatButton)view).getText().toString();
                if(valueButton.equals("+/-")){
                    btNeg.setText("(-");
                }
                valueButton = ((AppCompatButton)view).getText().toString();
                AddingAnExpression(valueButton,false);
                if(valueButton.equals("(-")){
                    btNeg.setText(")");
                    btNeg.setBackgroundResource(R.color.red);
                }
                if(valueButton.equals(")")){
                    btNeg.setText("+/-");
                    btNeg.setBackgroundResource(R.drawable.buttons_symbols_clicked);
                }
                break;
        }
    }

    private void clearExpression(){
        String text = viewExpression.getText().toString();

        if(!text.isEmpty()){
            if(text.equals("EXPRESSÃO")){
                viewExpression.setText("");
            }else{
                byte var0 = 0;
                int var1 = text.length()-1;
                String txtSubExpression = text.substring(var0,var1);
                viewExpression.setText(txtSubExpression);
            }
        }
        viewResult.setText("");
    }

    private void showResult(View v){

        try {

            Expression exp = new ExpressionBuilder(viewExpression.getText().toString()).build();
            double result = exp.evaluate();
            long longResult = (long) result;

            if(result == (double) longResult){
                viewResult.setText((CharSequence) String.valueOf(longResult));
            }else{
                viewResult.setText((CharSequence) String.valueOf(result));
            }

        }catch (ArithmeticException ex){
            Snackbar snack = make(v, mensagens[2], LENGTH_LONG);
            snack.setBackgroundTint(Color.BLACK);
            snack.setTextColor(Color.WHITE);
            snack.show();
            viewResult.setText("");
            viewExpression.setText("");
            btRaiz.setText("√x");
            btRaiz.setBackgroundResource(R.drawable.buttons_symbols_clicked);
            btNeg.setText("+/-");
            btNeg.setBackgroundResource(R.drawable.buttons_symbols_clicked);
        }catch (Exception e){
            Snackbar snack = make(v, mensagens[0], LENGTH_LONG);
            snack.setBackgroundTint(Color.BLACK);
            snack.setTextColor(Color.WHITE);
            snack.show();
            viewResult.setText("");
            viewExpression.setText("");
            btRaiz.setText("√x");
            btRaiz.setBackgroundResource(R.drawable.buttons_symbols_clicked);
            btNeg.setText("+/-");
            btNeg.setBackgroundResource(R.drawable.buttons_symbols_clicked);
        }

        String valueResult = viewResult.getText().toString();

        if(valueResult.toLowerCase().equals("nan")){
            Snackbar snack = make(v, mensagens[1], LENGTH_LONG);
            snack.setBackgroundTint(Color.BLACK);
            snack.setTextColor(Color.WHITE);
            snack.show();
            viewResult.setText("");
            viewExpression.setText("");
            btRaiz.setText("√x");
            btRaiz.setBackgroundResource(R.drawable.buttons_symbols_clicked);
            btNeg.setText("+/-");
            btNeg.setBackgroundResource(R.drawable.buttons_symbols_clicked);
        }
    }
}