package com.example.eladgofer.project.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.eladgofer.project.R;
import com.example.eladgofer.project.utils.TodoList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodoFragment extends Fragment {

    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    Unbinder unbinder;
    RecyclerView rvTodoList;
    RelativeLayout content;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        rvTodoList = (RecyclerView) view.findViewById(R.id.rvShppingList);
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        rvTodoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        DatabaseReference ref = mDatabase.getReference("TodoList").
                child(mUser.getUid());
        rvTodoList.setAdapter(new TodoAdapter(ref, getChildFragmentManager(), getActivity()));


        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fabAdd)
    public void onFabClicked() {

        AddTodoListFragment dialog = new AddTodoListFragment();
        dialog.show(getFragmentManager(), "addListDialog");
    }

    public static class TodoAdapter extends FirebaseRecyclerAdapter<TodoList, TodoAdapter.TodoViewHolder> {
        private FragmentManager fm;
        private FragmentActivity fActivity;

        public TodoAdapter(Query ref, FragmentManager fm, FragmentActivity fActivity) {
            super(TodoList.class, R.layout.todo_list_names, TodoViewHolder.class, ref);
            this.fm = fm;
            this.fActivity = fActivity;
        }

        @Override
        protected void populateViewHolder(TodoViewHolder viewHolder, final TodoList model, int position) {
            viewHolder.tvTitle.setText(model.getTitle() + " " + model.getUpdateTime());

            Picasso.with(viewHolder.tvTitle.getContext()).
                    load(model.getProfileImage()).
                    into(viewHolder.ivProfile);
            viewHolder.todoList = model;
            String key = getRef(position).getKey();
            viewHolder.modelKey = key;
            viewHolder.fm = fm;
            viewHolder.fActivity = fActivity;
            viewHolder.btnDeleteList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase.getInstance().getReference("TodoList").child(model.getOwner()).child(model.getListId()).removeValue();
                    FirebaseDatabase.getInstance().getReference("TodoItems").child(model.getListId()).removeValue();

                }
            });

        }

        public static class TodoViewHolder extends RecyclerView.ViewHolder {

            TextView tvTitle;
            ImageView ivProfile;
            Button btnDeleteList;
            TodoList todoList;
            String modelKey;
            FragmentManager fm;
            FragmentActivity fActivity;

            public TodoViewHolder(View itemView) {
                super(itemView);
                tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
                ivProfile = (ImageView) itemView.findViewById(R.id.ivItemIcon);
                btnDeleteList = (Button) itemView.findViewById(R.id.btnDeleteList);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TodoListItemOnListFragment todoListItemOnList =
                                TodoListItemOnListFragment.newInstance(todoList);
                        fActivity.getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.frame, todoListItemOnList).
                                addToBackStack("thetodolist").
                                commit();
                    }
                });
            }
        }
    }


}
