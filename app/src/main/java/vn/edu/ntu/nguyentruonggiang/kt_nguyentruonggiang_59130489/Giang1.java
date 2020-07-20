package vn.edu.ntu.nguyentruonggiang.kt_nguyentruonggiang_59130489;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import java.util.ArrayList;
import java.util.Calendar;

public class Giang1 extends Fragment implements View.OnClickListener{
    EditText e1,e2,e3;
    ImageView img1;
    RadioGroup radioGroup1;
    Button bt1,bt2;
    NavController navController;

    Bundle bundle= new Bundle();
    ArrayList<String> Giang= new ArrayList<String>();
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController= NavHostFragment.findNavController(this);
        ((MainActivity)getActivity()).navController= navController;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        e1= view.findViewById(R.id.editText1);
        e2= view.findViewById(R.id.editText2);
        e3= view.findViewById(R.id.editText3);
        img1= view.findViewById(R.id.imageView1);
        radioGroup1= view.findViewById(R.id.radiogroup);
        bt1= view.findViewById(R.id.button1);
        bt2= view.findViewById(R.id.button2);
        img1.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);


/*        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Giang1.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        int id= view.getId();
        switch (id){
            case R.id.imageView1: chonngay();break;
            case R.id.button1: Them();break;
            case R.id.button2: xemdanhsach();break;
        }
    }

    void chonngay(){
        Calendar calendar= Calendar.getInstance();

        DatePickerDialog.OnDateSetListener langnghesukienclickvaongay = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                StringBuilder stringBuilder= new StringBuilder();

                stringBuilder.append(i)
                        .append("/")
                        .append(i1)
                        .append("/")
                        .append(i2);
                e1.setText(stringBuilder.toString());
            }
        };

        DatePickerDialog datePickerDialog= new DatePickerDialog(
                getActivity(),
                langnghesukienclickvaongay,
                calendar.get(calendar.YEAR),
                calendar.get(calendar.MONTH),
                calendar.get(calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();

    }


    void Them(){
        String ngay= e1.getText().toString();
        String chontien= "";

        switch(radioGroup1.getCheckedRadioButtonId()){
            case R.id.radioButton: chontien="USD";break;
            case R.id.radioButton2: chontien="EUR";break;
            case R.id.radioButton3: chontien="CNY";break;

        }
        String mua= e2.getText().toString();
        String ban= e3.getText().toString();
        if(ngay.length()>0 && chontien.length()>0  && mua.length()>0 && ban.length()>0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" Ngày: ")
                    .append(ngay)
                    .append("\n loại tiền: ")
                    .append(chontien)
                    .append("\n Mua Vào: ")
                    .append(mua)
                    .append("\n Bán Ra: ")
                    .append(ban);


            Giang.add(stringBuilder.toString());
            bundle.putStringArrayList("giangg", Giang);
            Toast.makeText(getActivity(), "Ok Giang Pro Đã Thêm Được Thông Tin", Toast.LENGTH_SHORT).show();
        }
        else{ Toast.makeText(getActivity(), "Giang Pro Mau Nhập lại Đi", Toast.LENGTH_SHORT).show();}
    }


    void xemdanhsach(){
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment,bundle);
    }
}
