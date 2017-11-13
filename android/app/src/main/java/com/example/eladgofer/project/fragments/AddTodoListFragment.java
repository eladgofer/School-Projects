package com.example.eladgofer.project.fragments;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eladgofer.project.R;
import com.example.eladgofer.project.utils.TodoList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.LocalTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTodoListFragment extends DialogFragment {


    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.etListTitle)
    EditText etListTitle;
    @BindView(R.id.btnDone)
    Button btnDone;
    Unbinder unbinder;
    @BindView(R.id.tvCategory)
    TextView tvCategory;

    RadioGroup rgCategory;
    int img;

    public AddTodoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_todo_list, container, false);
        rgCategory = (RadioGroup) view.findViewById(R.id.rgCategory);


        rgCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rbFix:
                        img = R.mipmap.new_fix_final;
                        break;
                    case R.id.rbShop:
                        img = R.mipmap.new_shop_final;
                        break;
                    case R.id.rbOther:
                        img = R.mipmap.new_homer_final;
                        break;
                }
            }
        });
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnDone)
    public void onViewClicked() {

        if (etListTitle.getText().length() < 1) {
            etListTitle.setError("Can't be empty...");

            return;
        }
        int id = rgCategory.getCheckedRadioButtonId();

        if (id <= 0) {
            btnDone.setError("choose");
            Toast.makeText(getContext(), "Must Choose A Category", Toast.LENGTH_SHORT).show();
            return;
        }


        String title = etListTitle.getText().toString();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //create a new row and get the id:
        //in order to do that, we get a ref to the table and push->
        DatabaseReference rowRef = FirebaseDatabase.getInstance().
                getReference("TodoList").
                child(user.getUid()).
                push();
        // new listId

        String listId = rowRef.getKey();
        String hours = String.valueOf(LocalTime.now().getHourOfDay());
        int min = LocalTime.now().getMinuteOfHour();
        String minutes;
        if (min < 10) {
            minutes = "0" + min;
        } else {
            minutes = String.valueOf(min);
        }

        TodoList list = new TodoList(title, user.getUid(), hours + ":" + minutes, img, listId);
        rowRef.setValue(list);
        //closes the dialog:
        dismiss();
    }
}
